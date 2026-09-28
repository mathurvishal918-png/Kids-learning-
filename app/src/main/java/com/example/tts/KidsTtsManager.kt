package com.example.tts

import android.content.Context
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Locale

class KidsTtsManager(context: Context) : TextToSpeech.OnInitListener {

    private var tts: TextToSpeech? = null
    private var isReady = false

    private val _isSpeaking = MutableStateFlow(false)
    val isSpeaking: StateFlow<Boolean> = _isSpeaking.asStateFlow()

    private val _currentUtterance = MutableStateFlow("")
    val currentUtterance: StateFlow<String> = _currentUtterance.asStateFlow()

    private val hindiLocale = Locale("hi", "IN")
    private val englishLocale = Locale.US

    init {
        tts = TextToSpeech(context.applicationContext, this)
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            isReady = true
            tts?.setSpeechRate(0.88f) // Friendly, clear pacing for kids
            tts?.setPitch(1.15f)      // Cheerful, child-friendly pitch
            tts?.language = englishLocale

            tts?.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
                override fun onStart(utteranceId: String?) {
                    _isSpeaking.value = true
                    _currentUtterance.value = utteranceId ?: ""
                }

                override fun onDone(utteranceId: String?) {
                    _isSpeaking.value = false
                    _currentUtterance.value = ""
                }

                override fun onError(utteranceId: String?) {
                    _isSpeaking.value = false
                    _currentUtterance.value = ""
                }
            })
        }
    }

    fun speak(text: String, isHindi: Boolean = false) {
        if (!isReady || tts == null) return

        val targetLocale = if (isHindi) hindiLocale else englishLocale
        val avail = tts?.isLanguageAvailable(targetLocale)
        if (avail != null && avail >= TextToSpeech.LANG_AVAILABLE) {
            tts?.language = targetLocale
        } else {
            tts?.language = englishLocale
        }

        val utteranceId = text.hashCode().toString()
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, utteranceId)
    }

    fun stop() {
        tts?.stop()
        _isSpeaking.value = false
        _currentUtterance.value = ""
    }

    fun shutdown() {
        tts?.stop()
        tts?.shutdown()
        tts = null
        isReady = false
    }
}
