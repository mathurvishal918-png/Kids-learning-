package com.example.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.*
import com.example.tts.KidsTtsManager
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

enum class AppSection(val title: String, val icon: String) {
    ALPHABET("ABC English", "🔤"),
    COUNTING("1-100 Count", "🔢"),
    MATH_TABLES("Math Tables", "✖️"),
    HINDI_ALPHABET("हिन्दी वर्णमाला", "अ"),
    STORIES("Stories कहानियाँ", "📖"),
    RHYMES("Rhymes कविताएँ", "🎵")
}

enum class HindiTab {
    SWAR, VYANJAN
}

class KidsLearningViewModel(application: Application) : AndroidViewModel(application) {

    val tts = KidsTtsManager(application)

    // Current Navigation Tab
    private val _currentSection = MutableStateFlow(AppSection.ALPHABET)
    val currentSection: StateFlow<AppSection> = _currentSection.asStateFlow()

    // Stars / Rewards earned by toddler
    private val _totalStars = MutableStateFlow(5)
    val totalStars: StateFlow<Int> = _totalStars.asStateFlow()

    private val _showCelebration = MutableStateFlow(false)
    val showCelebration: StateFlow<Boolean> = _showCelebration.asStateFlow()

    // 1. English Alphabet State
    private val _selectedAlphabet = MutableStateFlow<AlphabetItem?>(null)
    val selectedAlphabet: StateFlow<AlphabetItem?> = _selectedAlphabet.asStateFlow()

    private val _learnedLetters = MutableStateFlow(setOf('A', 'B'))
    val learnedLetters: StateFlow<Set<Char>> = _learnedLetters.asStateFlow()

    // 2. Counting State
    private val _selectedNumberGroup = MutableStateFlow("1 - 10")
    val selectedNumberGroup: StateFlow<String> = _selectedNumberGroup.asStateFlow()

    private val _selectedNumber = MutableStateFlow<NumberItem?>(null)
    val selectedNumber: StateFlow<NumberItem?> = _selectedNumber.asStateFlow()

    private val _interactiveCount = MutableStateFlow(0)
    val interactiveCount: StateFlow<Int> = _interactiveCount.asStateFlow()

    // 3. Math Tables State
    private val _selectedTable = MutableStateFlow(MathTableRepository.tables[1]) // Table of 2
    val selectedTable: StateFlow<MathTable> = _selectedTable.asStateFlow()

    private val _isTableQuizOpen = MutableStateFlow(false)
    val isTableQuizOpen: StateFlow<Boolean> = _isTableQuizOpen.asStateFlow()

    private val _currentQuizIndex = MutableStateFlow(0)
    val currentQuizIndex: StateFlow<Int> = _currentQuizIndex.asStateFlow()

    private val _quizFeedback = MutableStateFlow<String?>(null)
    val quizFeedback: StateFlow<String?> = _quizFeedback.asStateFlow()

    private var tableSpeechJob: Job? = null

    // 4. Hindi Alphabet State
    private val _hindiTab = MutableStateFlow(HindiTab.SWAR)
    val hindiTab: StateFlow<HindiTab> = _hindiTab.asStateFlow()

    private val _selectedHindiLetter = MutableStateFlow<HindiLetter?>(null)
    val selectedHindiLetter: StateFlow<HindiLetter?> = _selectedHindiLetter.asStateFlow()

    private val _learnedHindiLetters = MutableStateFlow(setOf("अ", "आ"))
    val learnedHindiLetters: StateFlow<Set<String>> = _learnedHindiLetters.asStateFlow()

    // 5. Stories State
    private val _selectedStory = MutableStateFlow<StoryItem?>(null)
    val selectedStory: StateFlow<StoryItem?> = _selectedStory.asStateFlow()

    private val _storyPage = MutableStateFlow(0)
    val storyPage: StateFlow<Int> = _storyPage.asStateFlow()

    private val _storyLanguageHindi = MutableStateFlow(false)
    val storyLanguageHindi: StateFlow<Boolean> = _storyLanguageHindi.asStateFlow()

    // 6. Rhymes State
    private val _selectedRhyme = MutableStateFlow<RhymeItem?>(null)
    val selectedRhyme: StateFlow<RhymeItem?> = _selectedRhyme.asStateFlow()

    private val _rhymeFilter = MutableStateFlow("All")
    val rhymeFilter: StateFlow<String> = _rhymeFilter.asStateFlow()

    private var readingJob: Job? = null

    fun setSection(section: AppSection) {
        tts.stop()
        tableSpeechJob?.cancel()
        readingJob?.cancel()
        _currentSection.value = section
        when (section) {
            AppSection.ALPHABET -> tts.speak("English Alphabets!")
            AppSection.COUNTING -> tts.speak("Counting Numbers!")
            AppSection.MATH_TABLES -> tts.speak("Math Tables!")
            AppSection.HINDI_ALPHABET -> tts.speak("हिन्दी वर्णमाला", isHindi = true)
            AppSection.STORIES -> tts.speak("Kids Stories!")
            AppSection.RHYMES -> tts.speak("Poems and Rhymes!")
        }
    }

    // Alphabet actions
    fun openAlphabetDetail(item: AlphabetItem) {
        _selectedAlphabet.value = item
        tts.speak("${item.displayLetter}. ${item.word}. ${item.phonetic}")
        markLetterLearned(item.letter)
    }

    fun closeAlphabetDetail() {
        _selectedAlphabet.value = null
        tts.stop()
    }

    fun nextAlphabet() {
        val current = _selectedAlphabet.value ?: return
        val list = AlphabetRepository.alphabetList
        val nextIdx = (list.indexOf(current) + 1) % list.size
        openAlphabetDetail(list[nextIdx])
    }

    fun prevAlphabet() {
        val current = _selectedAlphabet.value ?: return
        val list = AlphabetRepository.alphabetList
        val prevIdx = (list.indexOf(current) - 1 + list.size) % list.size
        openAlphabetDetail(list[prevIdx])
    }

    fun speakCurrentAlphabetPhonics() {
        val current = _selectedAlphabet.value ?: return
        tts.speak("${current.phonetic}. ${current.sentence}")
    }

    fun speakCurrentAlphabetWord() {
        val current = _selectedAlphabet.value ?: return
        tts.speak("${current.letter} for ${current.word}!")
    }

    private fun markLetterLearned(letter: Char) {
        if (!_learnedLetters.value.contains(letter)) {
            _learnedLetters.value = _learnedLetters.value + letter
            addStarReward()
        }
    }

    // Counting actions
    fun setNumberGroup(group: String) {
        _selectedNumberGroup.value = group
    }

    fun openNumberDetail(item: NumberItem) {
        _selectedNumber.value = item
        _interactiveCount.value = 0
        tts.speak("${item.value}. ${item.word}. ${item.hindiWord}", isHindi = false)
    }

    fun closeNumberDetail() {
        _selectedNumber.value = null
        tts.stop()
    }

    fun tapInteractiveCount() {
        val current = _selectedNumber.value ?: return
        if (_interactiveCount.value < current.value) {
            val nextCount = _interactiveCount.value + 1
            _interactiveCount.value = nextCount
            val word = NumberRepository.getNumberWord(nextCount)
            tts.speak("$nextCount, $word")
            if (nextCount == current.value) {
                addStarReward()
            }
        }
    }

    fun nextNumber() {
        val current = _selectedNumber.value ?: return
        val list = NumberRepository.numbers1To100
        val nextIdx = (list.indexOf(current) + 1) % list.size
        openNumberDetail(list[nextIdx])
    }

    fun prevNumber() {
        val current = _selectedNumber.value ?: return
        val list = NumberRepository.numbers1To100
        val prevIdx = (list.indexOf(current) - 1 + list.size) % list.size
        openNumberDetail(list[prevIdx])
    }

    // Math Table actions
    fun selectTable(table: MathTable) {
        tableSpeechJob?.cancel()
        _selectedTable.value = table
        tts.speak(table.title)
    }

    fun speakTableRow(row: TableRowItem) {
        tts.speak(row.spokenEnglish)
    }

    fun readEntireTable() {
        tableSpeechJob?.cancel()
        val rows = _selectedTable.value.rows
        tableSpeechJob = viewModelScope.launch {
            for (row in rows) {
                tts.speak(row.spokenEnglish)
                delay(2200)
            }
            addStarReward()
        }
    }

    fun stopTableReading() {
        tableSpeechJob?.cancel()
        tts.stop()
    }

    fun openTableQuiz() {
        _isTableQuizOpen.value = true
        _currentQuizIndex.value = 0
        _quizFeedback.value = null
        val quiz = _selectedTable.value.quizQuestions.firstOrNull()
        if (quiz != null) {
            tts.speak("Quiz time! What is ${quiz.question}?")
        }
    }

    fun closeTableQuiz() {
        _isTableQuizOpen.value = false
    }

    fun answerQuiz(choice: Int) {
        val quiz = _selectedTable.value.quizQuestions.getOrNull(_currentQuizIndex.value) ?: return
        if (choice == quiz.correctAnswer) {
            _quizFeedback.value = "Correct! Super Star! ⭐"
            tts.speak("Awesome! Correct answer!")
            addStarReward()
            viewModelScope.launch {
                delay(1500)
                if (_currentQuizIndex.value + 1 < _selectedTable.value.quizQuestions.size) {
                    _currentQuizIndex.value += 1
                    _quizFeedback.value = null
                    val nextQuiz = _selectedTable.value.quizQuestions[_currentQuizIndex.value]
                    tts.speak(nextQuiz.question)
                } else {
                    _quizFeedback.value = "Great job! You finished the quiz!"
                }
            }
        } else {
            _quizFeedback.value = "Try again! You can do it! 💪"
            tts.speak("Almost! Try once more.")
        }
    }

    // Hindi Alphabet actions
    fun setHindiTab(tab: HindiTab) {
        _hindiTab.value = tab
        if (tab == HindiTab.SWAR) {
            tts.speak("स्वर, अ से अः", isHindi = true)
        } else {
            tts.speak("व्यंजन, क से ज्ञ", isHindi = true)
        }
    }

    fun openHindiLetterDetail(item: HindiLetter) {
        _selectedHindiLetter.value = item
        tts.speak(item.spokenText, isHindi = true)
        if (!_learnedHindiLetters.value.contains(item.letter)) {
            _learnedHindiLetters.value = _learnedHindiLetters.value + item.letter
            addStarReward()
        }
    }

    fun closeHindiLetterDetail() {
        _selectedHindiLetter.value = null
        tts.stop()
    }

    fun nextHindiLetter() {
        val current = _selectedHindiLetter.value ?: return
        val list = if (_hindiTab.value == HindiTab.SWAR) HindiAlphabetRepository.swarList else HindiAlphabetRepository.vyanjanList
        val nextIdx = (list.indexOf(current) + 1) % list.size
        openHindiLetterDetail(list[nextIdx])
    }

    fun prevHindiLetter() {
        val current = _selectedHindiLetter.value ?: return
        val list = if (_hindiTab.value == HindiTab.SWAR) HindiAlphabetRepository.swarList else HindiAlphabetRepository.vyanjanList
        val prevIdx = (list.indexOf(current) - 1 + list.size) % list.size
        openHindiLetterDetail(list[prevIdx])
    }

    // Stories actions
    fun openStory(story: StoryItem) {
        _selectedStory.value = story
        _storyPage.value = 0
        readCurrentStoryPage()
    }

    fun closeStory() {
        _selectedStory.value = null
        readingJob?.cancel()
        tts.stop()
    }

    fun toggleStoryLanguage() {
        _storyLanguageHindi.value = !_storyLanguageHindi.value
        readCurrentStoryPage()
    }

    fun nextStoryPage() {
        val story = _selectedStory.value ?: return
        if (_storyPage.value < story.pages.size - 1) {
            _storyPage.value += 1
            readCurrentStoryPage()
        } else {
            addStarReward()
            val moral = if (_storyLanguageHindi.value) story.moralHi else story.moralEn
            tts.speak(moral, isHindi = _storyLanguageHindi.value)
        }
    }

    fun prevStoryPage() {
        if (_storyPage.value > 0) {
            _storyPage.value -= 1
            readCurrentStoryPage()
        }
    }

    fun readCurrentStoryPage() {
        val story = _selectedStory.value ?: return
        val page = story.pages.getOrNull(_storyPage.value) ?: return
        val isHi = _storyLanguageHindi.value
        val text = if (isHi) page.textHi else page.textEn
        tts.speak(text, isHindi = isHi)
    }

    // Rhymes actions
    fun setRhymeFilter(filter: String) {
        _rhymeFilter.value = filter
    }

    fun openRhyme(rhyme: RhymeItem) {
        _selectedRhyme.value = rhyme
        tts.speak(rhyme.title, isHindi = rhyme.isHindi)
    }

    fun closeRhyme() {
        _selectedRhyme.value = null
        readingJob?.cancel()
        tts.stop()
    }

    fun readEntireRhyme() {
        readingJob?.cancel()
        val rhyme = _selectedRhyme.value ?: return
        readingJob = viewModelScope.launch {
            for (line in rhyme.lines) {
                tts.speak(line, isHindi = rhyme.isHindi)
                delay(2200)
            }
            addStarReward()
        }
    }

    fun speakRhymeLine(line: String) {
        val rhyme = _selectedRhyme.value ?: return
        tts.speak(line, isHindi = rhyme.isHindi)
    }

    fun stopRhymeSinging() {
        readingJob?.cancel()
        tts.stop()
    }

    private fun addStarReward() {
        _totalStars.value += 1
        _showCelebration.value = true
        viewModelScope.launch {
            delay(1800)
            _showCelebration.value = false
        }
    }

    override fun onCleared() {
        super.onCleared()
        tableSpeechJob?.cancel()
        readingJob?.cancel()
        tts.shutdown()
    }
}
