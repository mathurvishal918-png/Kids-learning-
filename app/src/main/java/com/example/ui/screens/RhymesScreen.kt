package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Stop
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
import com.example.data.RhymeItem
import com.example.data.RhymeRepository
import com.example.ui.theme.*

@Composable
fun RhymesScreen(
    selectedRhyme: RhymeItem?,
    currentFilter: String,
    isSpeaking: Boolean,
    onFilterSelected: (String) -> Unit,
    onRhymeClick: (RhymeItem) -> Unit,
    onCloseRhyme: () -> Unit,
    onSingRhyme: () -> Unit,
    onStopSinging: () -> Unit,
    onSpeakLine: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val filters = listOf("All", "English", "हिन्दी")
    val displayedRhymes = when (currentFilter) {
        "English" -> RhymeRepository.rhymes.filter { !it.isHindi }
        "हिन्दी" -> RhymeRepository.rhymes.filter { it.isHindi }
        else -> RhymeRepository.rhymes
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
                colors = CardDefaults.cardColors(containerColor = PastelPurple),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(text = "🎵", fontSize = 28.sp)
                    Column {
                        Text(
                            text = "Poems & Rhymes (कविताएँ)",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = KidsPurple
                            )
                        )
                        Text(
                            text = "Sing along with favorite English nursery rhymes & Hindi balgeet!",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = KidsTextSecondary
                            )
                        )
                    }
                }
            }

            // Language Filters
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                items(filters) { filter ->
                    val isSelected = filter == currentFilter
                    Surface(
                        shape = RoundedCornerShape(16.dp),
                        color = if (isSelected) KidsPurple else Color.White,
                        border = androidx.compose.foundation.BorderStroke(
                            1.dp,
                            if (isSelected) KidsPurple else Color(0xFFE2E8F0)
                        ),
                        modifier = Modifier
                            .clickable { onFilterSelected(filter) }
                            .testTag("rhyme_filter_$filter")
                    ) {
                        Text(
                            text = filter,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                color = if (isSelected) Color.White else KidsTextPrimary
                            )
                        )
                    }
                }
            }

            // Rhymes List
            LazyColumn(
                contentPadding = PaddingValues(bottom = 90.dp, top = 4.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(displayedRhymes) { rhyme ->
                    RhymeCard(
                        rhyme = rhyme,
                        onClick = { onRhymeClick(rhyme) }
                    )
                }
            }
        }

        // Rhyme Modal Dialog
        if (selectedRhyme != null) {
            RhymeDetailDialog(
                rhyme = selectedRhyme,
                isSpeaking = isSpeaking,
                onDismiss = onCloseRhyme,
                onSing = onSingRhyme,
                onStop = onStopSinging,
                onSpeakLine = onSpeakLine
            )
        }
    }
}

@Composable
fun RhymeCard(
    rhyme: RhymeItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = rhyme.cardColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .clickable(onClick = onClick)
            .testTag("rhyme_card_${rhyme.id}")
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(58.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White)
                    .border(2.dp, rhyme.badgeColor.copy(alpha = 0.3f), RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = rhyme.emoji, fontSize = 28.sp)
            }

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = rhyme.title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.ExtraBold,
                        color = KidsTextPrimary
                    )
                )
                Text(
                    text = rhyme.subtitle,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = rhyme.badgeColor,
                        fontWeight = FontWeight.SemiBold
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = Color.White.copy(alpha = 0.8f)
                ) {
                    Text(
                        text = if (rhyme.isHindi) "🇮🇳 हिन्दी कविता" else "🇬🇧 English Rhyme",
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Medium,
                            fontSize = 10.sp,
                            color = KidsTextSecondary
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun RhymeDetailDialog(
    rhyme: RhymeItem,
    isSpeaking: Boolean,
    onDismiss: () -> Unit,
    onSing: () -> Unit,
    onStop: () -> Unit,
    onSpeakLine: (String) -> Unit
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
                .fillMaxWidth(0.94f)
                .fillMaxHeight(0.85f)
                .padding(8.dp)
                .testTag("rhyme_detail_dialog")
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(18.dp)
            ) {
                // Header Bar
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(text = rhyme.emoji, fontSize = 28.sp)
                        Column {
                            Text(
                                text = rhyme.title,
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.ExtraBold,
                                    color = KidsTextPrimary
                                ),
                                maxLines = 1
                            )
                            Text(
                                text = rhyme.subtitle,
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = rhyme.badgeColor
                                )
                            )
                        }
                    }

                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFF1F5F9))
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = KidsTextSecondary
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Action Bar: Sing / Read Aloud
                Button(
                    onClick = {
                        if (isSpeaking) onStop() else onSing()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isSpeaking) KidsSecondary else rhyme.badgeColor
                    ),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .testTag("sing_rhyme_button")
                ) {
                    Icon(
                        imageVector = if (isSpeaking) Icons.Default.Stop else Icons.Default.PlayArrow,
                        contentDescription = null,
                        modifier = Modifier.size(22.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = if (isSpeaking) "Stop Sing-Along" else "Sing Along With Me!",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "💡 Tap any line below to hear it spoken:",
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = KidsTextSecondary
                    ),
                    modifier = Modifier.padding(bottom = 6.dp)
                )

                // Verses List
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    items(rhyme.lines) { line ->
                        Surface(
                            shape = RoundedCornerShape(14.dp),
                            color = rhyme.cardColor,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onSpeakLine(line) }
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = line,
                                    style = MaterialTheme.typography.bodyLarge.copy(
                                        fontWeight = FontWeight.SemiBold,
                                        color = KidsTextPrimary,
                                        fontSize = 16.sp
                                    ),
                                    modifier = Modifier.weight(1f)
                                )
                                Icon(
                                    imageVector = Icons.Default.VolumeUp,
                                    contentDescription = "Speak line",
                                    tint = rhyme.badgeColor,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
