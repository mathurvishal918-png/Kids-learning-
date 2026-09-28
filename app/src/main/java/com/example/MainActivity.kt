package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ui.components.KidsBottomNavBar
import com.example.ui.components.KidsCelebrationOverlay
import com.example.ui.components.KidsTopBar
import com.example.ui.screens.*
import com.example.ui.theme.KidsBackgroundLight
import com.example.ui.theme.MyApplicationTheme
import com.example.viewmodel.AppSection
import com.example.viewmodel.KidsLearningViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: KidsLearningViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                KidsApp(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun KidsApp(viewModel: KidsLearningViewModel) {
    val currentSection by viewModel.currentSection.collectAsStateWithLifecycle()
    val totalStars by viewModel.totalStars.collectAsStateWithLifecycle()
    val showCelebration by viewModel.showCelebration.collectAsStateWithLifecycle()
    val isSpeaking by viewModel.tts.isSpeaking.collectAsStateWithLifecycle()

    // Screen specific states
    val selectedAlphabet by viewModel.selectedAlphabet.collectAsStateWithLifecycle()
    val learnedLetters by viewModel.learnedLetters.collectAsStateWithLifecycle()

    val selectedNumberGroup by viewModel.selectedNumberGroup.collectAsStateWithLifecycle()
    val selectedNumber by viewModel.selectedNumber.collectAsStateWithLifecycle()
    val interactiveCount by viewModel.interactiveCount.collectAsStateWithLifecycle()

    val selectedTable by viewModel.selectedTable.collectAsStateWithLifecycle()
    val isTableQuizOpen by viewModel.isTableQuizOpen.collectAsStateWithLifecycle()
    val currentQuizIndex by viewModel.currentQuizIndex.collectAsStateWithLifecycle()
    val quizFeedback by viewModel.quizFeedback.collectAsStateWithLifecycle()

    val hindiTab by viewModel.hindiTab.collectAsStateWithLifecycle()
    val selectedHindiLetter by viewModel.selectedHindiLetter.collectAsStateWithLifecycle()
    val learnedHindiLetters by viewModel.learnedHindiLetters.collectAsStateWithLifecycle()

    val selectedStory by viewModel.selectedStory.collectAsStateWithLifecycle()
    val storyPage by viewModel.storyPage.collectAsStateWithLifecycle()
    val isHindiStory by viewModel.storyLanguageHindi.collectAsStateWithLifecycle()

    val selectedRhyme by viewModel.selectedRhyme.collectAsStateWithLifecycle()
    val rhymeFilter by viewModel.rhymeFilter.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(KidsBackgroundLight)
    ) {
        Scaffold(
            topBar = {
                KidsTopBar(
                    title = currentSection.title,
                    starsCount = totalStars,
                    isSpeaking = isSpeaking,
                    onStopAudio = { viewModel.tts.stop() }
                )
            },
            bottomBar = {
                KidsBottomNavBar(
                    currentSection = currentSection,
                    onSectionSelected = { viewModel.setSection(it) }
                )
            },
            containerColor = KidsBackgroundLight
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                Crossfade(
                    targetState = currentSection,
                    animationSpec = tween(durationMillis = 250),
                    label = "section_crossfade"
                ) { section ->
                    when (section) {
                        AppSection.ALPHABET -> AlphabetScreen(
                            learnedLetters = learnedLetters,
                            selectedAlphabet = selectedAlphabet,
                            onLetterClick = { viewModel.openAlphabetDetail(it) },
                            onCloseDetail = { viewModel.closeAlphabetDetail() },
                            onNextLetter = { viewModel.nextAlphabet() },
                            onPrevLetter = { viewModel.prevAlphabet() },
                            onSpeakPhonics = { viewModel.speakCurrentAlphabetPhonics() },
                            onSpeakWord = { viewModel.speakCurrentAlphabetWord() }
                        )
                        AppSection.COUNTING -> CountingScreen(
                            selectedGroup = selectedNumberGroup,
                            selectedNumber = selectedNumber,
                            interactiveCount = interactiveCount,
                            onGroupSelected = { viewModel.setNumberGroup(it) },
                            onNumberClick = { viewModel.openNumberDetail(it) },
                            onCloseDetail = { viewModel.closeNumberDetail() },
                            onNextNumber = { viewModel.nextNumber() },
                            onPrevNumber = { viewModel.prevNumber() },
                            onTapCount = { viewModel.tapInteractiveCount() },
                            onSpeakNumber = {
                                selectedNumber?.let { viewModel.openNumberDetail(it) }
                            }
                        )
                        AppSection.MATH_TABLES -> MathTablesScreen(
                            selectedTable = selectedTable,
                            isSpeaking = isSpeaking,
                            isQuizOpen = isTableQuizOpen,
                            quizIndex = currentQuizIndex,
                            quizFeedback = quizFeedback,
                            onSelectTable = { viewModel.selectTable(it) },
                            onSpeakRow = { viewModel.speakTableRow(it) },
                            onReadFullTable = { viewModel.readEntireTable() },
                            onStopReading = { viewModel.stopTableReading() },
                            onOpenQuiz = { viewModel.openTableQuiz() },
                            onCloseQuiz = { viewModel.closeTableQuiz() },
                            onAnswerQuiz = { viewModel.answerQuiz(it) }
                        )
                        AppSection.HINDI_ALPHABET -> HindiAlphabetScreen(
                            currentTab = hindiTab,
                            learnedLetters = learnedHindiLetters,
                            selectedLetter = selectedHindiLetter,
                            onTabSelected = { viewModel.setHindiTab(it) },
                            onLetterClick = { viewModel.openHindiLetterDetail(it) },
                            onCloseDetail = { viewModel.closeHindiLetterDetail() },
                            onNextLetter = { viewModel.nextHindiLetter() },
                            onPrevLetter = { viewModel.prevHindiLetter() },
                            onSpeakLetter = {
                                selectedHindiLetter?.let { viewModel.openHindiLetterDetail(it) }
                            }
                        )
                        AppSection.STORIES -> StoriesScreen(
                            selectedStory = selectedStory,
                            currentPage = storyPage,
                            isHindi = isHindiStory,
                            onStoryClick = { viewModel.openStory(it) },
                            onCloseStory = { viewModel.closeStory() },
                            onToggleLanguage = { viewModel.toggleStoryLanguage() },
                            onNextPage = { viewModel.nextStoryPage() },
                            onPrevPage = { viewModel.prevStoryPage() },
                            onReadPage = { viewModel.readCurrentStoryPage() }
                        )
                        AppSection.RHYMES -> RhymesScreen(
                            selectedRhyme = selectedRhyme,
                            currentFilter = rhymeFilter,
                            isSpeaking = isSpeaking,
                            onFilterSelected = { viewModel.setRhymeFilter(it) },
                            onRhymeClick = { viewModel.openRhyme(it) },
                            onCloseRhyme = { viewModel.closeRhyme() },
                            onSingRhyme = { viewModel.readEntireRhyme() },
                            onStopSinging = { viewModel.stopRhymeSinging() },
                            onSpeakLine = { viewModel.speakRhymeLine(it) }
                        )
                    }
                }
            }
        }

        // Star Reward Celebration Animation
        KidsCelebrationOverlay(visible = showCelebration)
    }
}
