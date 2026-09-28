package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.TouchApp
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
import com.example.data.NumberItem
import com.example.data.NumberRepository
import com.example.ui.theme.*

@Composable
fun CountingScreen(
    selectedGroup: String,
    selectedNumber: NumberItem?,
    interactiveCount: Int,
    onGroupSelected: (String) -> Unit,
    onNumberClick: (NumberItem) -> Unit,
    onCloseDetail: () -> Unit,
    onNextNumber: () -> Unit,
    onPrevNumber: () -> Unit,
    onTapCount: () -> Unit,
    onSpeakNumber: () -> Unit,
    modifier: Modifier = Modifier
) {
    val displayedNumbers = if (selectedGroup == "All 1-100") {
        NumberRepository.numbers1To100
    } else {
        val range = NumberRepository.groups.firstOrNull { it.first == selectedGroup }?.second ?: (1..10)
        NumberRepository.numbers1To100.filter { it.value in range }
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
                colors = CardDefaults.cardColors(containerColor = PastelYellow),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(text = "🔢", fontSize = 28.sp)
                    Column {
                        Text(
                            text = "Counting (1 to 100)",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF854D0E)
                            )
                        )
                        Text(
                            text = "Learn number names in English & Hindi with fun counting!",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = KidsTextSecondary
                            )
                        )
                    }
                }
            }

            // Grouping Chips (1-10, 11-20, ... 91-100, All)
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                items(NumberRepository.groups) { (groupName, _) ->
                    val isSelected = groupName == selectedGroup
                    Surface(
                        shape = RoundedCornerShape(16.dp),
                        color = if (isSelected) KidsPrimary else Color.White,
                        border = androidx.compose.foundation.BorderStroke(
                            1.dp,
                            if (isSelected) KidsPrimary else Color(0xFFE2E8F0)
                        ),
                        modifier = Modifier
                            .clickable { onGroupSelected(groupName) }
                            .testTag("group_chip_$groupName")
                    ) {
                        Text(
                            text = groupName,
                            modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                color = if (isSelected) Color.White else KidsTextPrimary
                            )
                        )
                    }
                }
            }

            // Number Grid
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 88.dp),
                contentPadding = PaddingValues(bottom = 90.dp, top = 6.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(displayedNumbers, key = { it.value }) { item ->
                    NumberCard(
                        item = item,
                        onClick = { onNumberClick(item) }
                    )
                }
            }
        }

        // Number Detail Dialog
        if (selectedNumber != null) {
            NumberDetailDialog(
                item = selectedNumber,
                interactiveCount = interactiveCount,
                onDismiss = onCloseDetail,
                onNext = onNextNumber,
                onPrev = onPrevNumber,
                onTapCount = onTapCount,
                onSpeak = onSpeakNumber
            )
        }
    }
}

@Composable
fun NumberCard(
    item: NumberItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = item.cardColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(98.dp)
            .clip(RoundedCornerShape(18.dp))
            .clickable(onClick = onClick)
            .testTag("number_card_${item.value}")
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(6.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "${item.value}",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Black,
                        color = KidsPrimary,
                        fontSize = 24.sp
                    )
                )
                Text(text = item.emoji, fontSize = 16.sp)
            }
            Text(
                text = item.word,
                style = MaterialTheme.typography.labelMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = KidsTextPrimary,
                    fontSize = 11.sp
                ),
                maxLines = 1
            )
            Text(
                text = item.hindiWord,
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.Medium,
                    color = KidsTextSecondary,
                    fontSize = 10.sp
                ),
                maxLines = 1
            )
        }
    }
}

@Composable
fun NumberDetailDialog(
    item: NumberItem,
    interactiveCount: Int,
    onDismiss: () -> Unit,
    onNext: () -> Unit,
    onPrev: () -> Unit,
    onTapCount: () -> Unit,
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
                .testTag("number_detail_dialog")
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
                            .background(PastelYellow)
                            .testTag("prev_number_button")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Previous Number",
                            tint = Color(0xFF854D0E)
                        )
                    }

                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = if (item.isEven) PastelBlue else PastelPink
                    ) {
                        Text(
                            text = if (item.isEven) "Even Number" else "Odd Number",
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = if (item.isEven) KidsPrimary else KidsPink
                            )
                        )
                    }

                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        IconButton(
                            onClick = onNext,
                            modifier = Modifier
                                .size(44.dp)
                                .clip(CircleShape)
                                .background(PastelYellow)
                                .testTag("next_number_button")
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                contentDescription = "Next Number",
                                tint = Color(0xFF854D0E)
                            )
                        }

                        IconButton(
                            onClick = onDismiss,
                            modifier = Modifier
                                .size(44.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFF1F5F9))
                                .testTag("close_number_dialog")
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

                // Giant Number Badge
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .background(item.cardColor)
                        .border(3.dp, KidsPrimary.copy(alpha = 0.4f), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "${item.value}",
                            style = MaterialTheme.typography.displayMedium.copy(
                                fontWeight = FontWeight.Black,
                                color = KidsPrimary,
                                fontSize = 44.sp
                            )
                        )
                        Text(text = item.emoji, fontSize = 24.sp)
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // English and Hindi Names
                Text(
                    text = item.word,
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.ExtraBold,
                        color = KidsTextPrimary
                    )
                )

                Text(
                    text = "हिंदी: ${item.hindiWord}",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = KidsSecondary
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Interactive Tap to Count Minigame
                Card(
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF8FAFC)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Tap to Count with Me! ($interactiveCount / ${item.value})",
                            style = MaterialTheme.typography.labelLarge.copy(
                                fontWeight = FontWeight.Bold,
                                color = KidsTextPrimary
                            )
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        // Visual display of items to count
                        val displayCount = item.value.coerceAtMost(10)
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onTapCount() }
                                .padding(vertical = 8.dp)
                                .testTag("tap_count_area")
                        ) {
                            (1..displayCount).forEach { i ->
                                val isCounted = i <= interactiveCount
                                Text(
                                    text = item.emoji,
                                    fontSize = 26.sp,
                                    modifier = Modifier
                                        .padding(horizontal = 2.dp)
                                        .clip(CircleShape)
                                        .background(if (isCounted) PastelYellow else Color.Transparent)
                                )
                            }
                        }

                        Button(
                            onClick = onTapCount,
                            colors = ButtonDefaults.buttonColors(containerColor = KidsSecondary),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.testTag("tap_count_button")
                        ) {
                            Icon(
                                imageVector = Icons.Default.TouchApp,
                                contentDescription = null,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Tap to Count (+1)", fontWeight = FontWeight.Bold)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Listen Audio Button
                Button(
                    onClick = onSpeak,
                    colors = ButtonDefaults.buttonColors(containerColor = KidsPrimary),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .testTag("speak_number_button")
                ) {
                    Icon(
                        imageVector = Icons.Default.VolumeUp,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Pronounce Number", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }
            }
        }
    }
}
