package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.data.AlphabetItem
import com.example.data.AlphabetRepository
import com.example.ui.theme.*

@Composable
fun AlphabetScreen(
    learnedLetters: Set<Char>,
    selectedAlphabet: AlphabetItem?,
    onLetterClick: (AlphabetItem) -> Unit,
    onCloseDetail: () -> Unit,
    onNextLetter: () -> Unit,
    onPrevLetter: () -> Unit,
    onSpeakPhonics: () -> Unit,
    onSpeakWord: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 14.dp)
        ) {
            // Header Banner
            Card(
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = PastelBlue),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(text = "🔤", fontSize = 28.sp)
                    Column {
                        Text(
                            text = "English Alphabet (A to Z)",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = KidsPrimary
                            )
                        )
                        Text(
                            text = "Tap any letter to hear its phonics sound & word!",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = KidsTextSecondary
                            )
                        )
                    }
                }
            }

            // Grid of 26 Alphabet Cards
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 100.dp),
                contentPadding = PaddingValues(bottom = 90.dp, top = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(AlphabetRepository.alphabetList, key = { it.letter }) { item ->
                    AlphabetCard(
                        item = item,
                        isLearned = learnedLetters.contains(item.letter),
                        onClick = { onLetterClick(item) }
                    )
                }
            }
        }

        // Letter Detail Dialog
        if (selectedAlphabet != null) {
            AlphabetDetailDialog(
                item = selectedAlphabet,
                onDismiss = onCloseDetail,
                onNext = onNextLetter,
                onPrev = onPrevLetter,
                onSpeakPhonics = onSpeakPhonics,
                onSpeakWord = onSpeakWord
            )
        }
    }
}

@Composable
fun AlphabetCard(
    item: AlphabetItem,
    isLearned: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = item.cardColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(125.dp)
            .clip(RoundedCornerShape(20.dp))
            .clickable(onClick = onClick)
            .testTag("alphabet_card_${item.letter}")
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Learned Star Badge on top right
            if (isLearned) {
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Learned",
                        tint = KidsTertiary,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = item.displayLetter,
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.ExtraBold,
                        color = item.badgeColor,
                        fontSize = 28.sp
                    )
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = item.emoji,
                    fontSize = 24.sp
                )
                Text(
                    text = item.word,
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = KidsTextPrimary,
                        fontSize = 11.sp
                    ),
                    maxLines = 1
                )
            }
        }
    }
}

@Composable
fun AlphabetDetailDialog(
    item: AlphabetItem,
    onDismiss: () -> Unit,
    onNext: () -> Unit,
    onPrev: () -> Unit,
    onSpeakPhonics: () -> Unit,
    onSpeakWord: () -> Unit
) {
    BackHandler(onBack = onDismiss)

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Card(
            shape = RoundedCornerShape(28.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 16.dp),
            modifier = Modifier
                .fillMaxWidth(0.92f)
                .wrapContentHeight()
                .padding(8.dp)
                .testTag("alphabet_detail_dialog")
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Top Action Bar in Modal: Prev, Close, Next
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = onPrev,
                        modifier = Modifier
                            .size(44.dp)
                            .clip(CircleShape)
                            .background(PastelBlue)
                            .testTag("prev_letter_button")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Previous Letter",
                            tint = KidsPrimary
                        )
                    }

                    Text(
                        text = "Letter ${item.letter}",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = KidsTextSecondary
                        )
                    )

                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        IconButton(
                            onClick = onNext,
                            modifier = Modifier
                                .size(44.dp)
                                .clip(CircleShape)
                                .background(PastelBlue)
                                .testTag("next_letter_button")
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                contentDescription = "Next Letter",
                                tint = KidsPrimary
                            )
                        }

                        IconButton(
                            onClick = onDismiss,
                            modifier = Modifier
                                .size(44.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFF1F5F9))
                                .testTag("close_detail_button")
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Close",
                                tint = KidsTextSecondary
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Big Letter & Animated Visual
                Box(
                    modifier = Modifier
                        .size(130.dp)
                        .clip(RoundedCornerShape(32.dp))
                        .background(item.cardColor)
                        .border(3.dp, item.badgeColor.copy(alpha = 0.5f), RoundedCornerShape(32.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = item.displayLetter,
                            style = MaterialTheme.typography.displayMedium.copy(
                                fontWeight = FontWeight.Black,
                                color = item.badgeColor,
                                fontSize = 42.sp
                            )
                        )
                        Text(
                            text = item.emoji,
                            fontSize = 38.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Word Header & Spelled out
                Text(
                    text = "${item.letter} for ${item.word}",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.ExtraBold,
                        color = KidsTextPrimary
                    )
                )

                // Syllables / Spelling Pills
                Row(
                    modifier = Modifier.padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    item.word.uppercase().forEach { char ->
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = PastelYellow,
                            border = androidx.compose.foundation.BorderStroke(1.dp, KidsTertiary.copy(alpha = 0.5f))
                        ) {
                            Text(
                                text = "$char",
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                style = MaterialTheme.typography.labelLarge.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF92400E)
                                )
                            )
                        }
                    }
                }

                // Phonics & Sentence description
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = Color(0xFFF8FAFC),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = item.phonetic,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = item.badgeColor
                            )
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "\"${item.sentence}\"",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Medium,
                                color = KidsTextPrimary,
                                textAlign = TextAlign.Center
                            )
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "💡 ${item.funFact}",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = KidsTextSecondary,
                                textAlign = TextAlign.Center
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Voice Action Buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Button(
                        onClick = onSpeakPhonics,
                        colors = ButtonDefaults.buttonColors(containerColor = item.badgeColor),
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp)
                            .testTag("speak_phonics_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.VolumeUp,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Phonics", fontWeight = FontWeight.Bold)
                    }

                    Button(
                        onClick = onSpeakWord,
                        colors = ButtonDefaults.buttonColors(containerColor = KidsPrimary),
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp)
                            .testTag("speak_word_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.VolumeUp,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Speak Word", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}
