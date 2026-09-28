package com.example.ui.screens

import androidx.activity.compose.BackHandler
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.data.HindiAlphabetRepository
import com.example.data.HindiLetter
import com.example.ui.theme.*
import com.example.viewmodel.HindiTab

@Composable
fun HindiAlphabetScreen(
    currentTab: HindiTab,
    learnedLetters: Set<String>,
    selectedLetter: HindiLetter?,
    onTabSelected: (HindiTab) -> Unit,
    onLetterClick: (HindiLetter) -> Unit,
    onCloseDetail: () -> Unit,
    onNextLetter: () -> Unit,
    onPrevLetter: () -> Unit,
    onSpeakLetter: () -> Unit,
    modifier: Modifier = Modifier
) {
    val displayedLetters = if (currentTab == HindiTab.SWAR) {
        HindiAlphabetRepository.swarList
    } else {
        HindiAlphabetRepository.vyanjanList
    }

    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 14.dp)
        ) {
            // Header Banner
            Card(
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = PastelOrange),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(text = "🇮🇳", fontSize = 28.sp)
                    Column {
                        Text(
                            text = "हिन्दी वर्णमाला (Hindi Alphabet)",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = KidsSecondary
                            )
                        )
                        Text(
                            text = "स्वर (अ से अः) और व्यंजन (क से ज्ञ) सीखें बोलकर!",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = KidsTextSecondary
                            )
                        )
                    }
                }
            }

            // Swar vs Vyanjan Tabs
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Button(
                    onClick = { onTabSelected(HindiTab.SWAR) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (currentTab == HindiTab.SWAR) KidsSecondary else Color.White
                    ),
                    shape = RoundedCornerShape(16.dp),
                    border = if (currentTab == HindiTab.SWAR) null else androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E8F0)),
                    modifier = Modifier
                        .weight(1f)
                        .height(46.dp)
                        .testTag("tab_swar")
                ) {
                    Text(
                        text = "स्वर (Swar - 13)",
                        fontWeight = FontWeight.Bold,
                        color = if (currentTab == HindiTab.SWAR) Color.White else KidsTextPrimary
                    )
                }

                Button(
                    onClick = { onTabSelected(HindiTab.VYANJAN) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (currentTab == HindiTab.VYANJAN) KidsSecondary else Color.White
                    ),
                    shape = RoundedCornerShape(16.dp),
                    border = if (currentTab == HindiTab.VYANJAN) null else androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E8F0)),
                    modifier = Modifier
                        .weight(1f)
                        .height(46.dp)
                        .testTag("tab_vyanjan")
                ) {
                    Text(
                        text = "व्यंजन (Vyanjan - 36)",
                        fontWeight = FontWeight.Bold,
                        color = if (currentTab == HindiTab.VYANJAN) Color.White else KidsTextPrimary
                    )
                }
            }

            // Grid of Hindi Letters
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 92.dp),
                contentPadding = PaddingValues(bottom = 90.dp, top = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(displayedLetters, key = { it.letter }) { item ->
                    HindiLetterCard(
                        item = item,
                        isLearned = learnedLetters.contains(item.letter),
                        onClick = { onLetterClick(item) }
                    )
                }
            }
        }

        // Detail Dialog
        if (selectedLetter != null) {
            HindiLetterDetailDialog(
                item = selectedLetter,
                onDismiss = onCloseDetail,
                onNext = onNextLetter,
                onPrev = onPrevLetter,
                onSpeak = onSpeakLetter
            )
        }
    }
}

@Composable
fun HindiLetterCard(
    item: HindiLetter,
    isLearned: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = item.cardColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(118.dp)
            .clip(RoundedCornerShape(18.dp))
            .clickable(onClick = onClick)
            .testTag("hindi_card_${item.letter}")
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            if (isLearned) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Learned",
                    tint = KidsTertiary,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(6.dp)
                        .size(16.dp)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(6.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = item.letter,
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.ExtraBold,
                        color = KidsSecondary,
                        fontSize = 30.sp
                    )
                )
                Text(text = item.emoji, fontSize = 20.sp)
                Text(
                    text = item.wordHindi,
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
fun HindiLetterDetailDialog(
    item: HindiLetter,
    onDismiss: () -> Unit,
    onNext: () -> Unit,
    onPrev: () -> Unit,
    onSpeak: () -> Unit
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
                .testTag("hindi_detail_dialog")
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Top Navigation Bar
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
                            .background(PastelOrange)
                            .testTag("prev_hindi_button")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Previous Hindi Letter",
                            tint = KidsSecondary
                        )
                    }

                    Text(
                        text = "अक्षर: ${item.letter}",
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
                                .background(PastelOrange)
                                .testTag("next_hindi_button")
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                contentDescription = "Next Hindi Letter",
                                tint = KidsSecondary
                            )
                        }

                        IconButton(
                            onClick = onDismiss,
                            modifier = Modifier
                                .size(44.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFF1F5F9))
                                .testTag("close_hindi_dialog")
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

                // Big Hindi Letter Badge
                Box(
                    modifier = Modifier
                        .size(130.dp)
                        .clip(RoundedCornerShape(32.dp))
                        .background(item.cardColor)
                        .border(3.dp, KidsSecondary.copy(alpha = 0.4f), RoundedCornerShape(32.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = item.letter,
                            style = MaterialTheme.typography.displayMedium.copy(
                                fontWeight = FontWeight.Black,
                                color = KidsSecondary,
                                fontSize = 48.sp
                            )
                        )
                        Text(text = item.emoji, fontSize = 32.sp)
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Phrase: "अ से अनार"
                Text(
                    text = item.spokenText,
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.ExtraBold,
                        color = KidsTextPrimary,
                        fontSize = 26.sp
                    )
                )

                Spacer(modifier = Modifier.height(6.dp))

                // English meaning & romanization
                Surface(
                    shape = RoundedCornerShape(14.dp),
                    color = PastelYellow,
                    modifier = Modifier.padding(vertical = 4.dp)
                ) {
                    Text(
                        text = "${item.wordEnglish} (${item.meaning})",
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp),
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF854D0E)
                        )
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Pronunciation Audio Button
                Button(
                    onClick = onSpeak,
                    colors = ButtonDefaults.buttonColors(containerColor = KidsSecondary),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .testTag("speak_hindi_button")
                ) {
                    Icon(
                        imageVector = Icons.Default.VolumeUp,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("उच्चारण सुनें (Listen Voice)", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }
            }
        }
    }
}
