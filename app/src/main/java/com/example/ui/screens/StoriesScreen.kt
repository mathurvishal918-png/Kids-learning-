package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.MenuBook
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
import com.example.data.StoryItem
import com.example.data.StoryRepository
import com.example.ui.theme.*

@Composable
fun StoriesScreen(
    selectedStory: StoryItem?,
    currentPage: Int,
    isHindi: Boolean,
    onStoryClick: (StoryItem) -> Unit,
    onCloseStory: () -> Unit,
    onToggleLanguage: () -> Unit,
    onNextPage: () -> Unit,
    onPrevPage: () -> Unit,
    onReadPage: () -> Unit,
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
                colors = CardDefaults.cardColors(containerColor = PastelPink),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(text = "📖", fontSize = 28.sp)
                    Column {
                        Text(
                            text = "Kids Stories (कहानियाँ)",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = KidsPink
                            )
                        )
                        Text(
                            text = "Classic animated moral stories in English & Hindi with voice narration!",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = KidsTextSecondary
                            )
                        )
                    }
                }
            }

            // Stories List
            LazyColumn(
                contentPadding = PaddingValues(bottom = 90.dp, top = 4.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(StoryRepository.stories) { story ->
                    StoryCard(
                        story = story,
                        onClick = { onStoryClick(story) }
                    )
                }
            }
        }

        // Story Reader Dialog
        if (selectedStory != null) {
            StoryReaderDialog(
                story = selectedStory,
                currentPage = currentPage,
                isHindi = isHindi,
                onDismiss = onCloseStory,
                onToggleLanguage = onToggleLanguage,
                onNext = onNextPage,
                onPrev = onPrevPage,
                onRead = onReadPage
            )
        }
    }
}

@Composable
fun StoryCard(
    story: StoryItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(containerColor = story.cardColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(22.dp))
            .clickable(onClick = onClick)
            .testTag("story_card_${story.id}")
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Story Cover Emoji
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .background(Color.White)
                    .border(2.dp, story.badgeColor.copy(alpha = 0.3f), RoundedCornerShape(18.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = story.coverEmoji, fontSize = 34.sp)
            }

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = story.titleEn,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.ExtraBold,
                        color = KidsTextPrimary
                    )
                )
                Text(
                    text = story.titleHi,
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = story.badgeColor
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = story.summaryEn,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = KidsTextSecondary
                    ),
                    maxLines = 2
                )
                Spacer(modifier = Modifier.height(6.dp))
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = Color.White.copy(alpha = 0.8f)
                ) {
                    Text(
                        text = "⭐ Moral: ${story.moralEn}",
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = story.badgeColor,
                            fontSize = 10.sp
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun StoryReaderDialog(
    story: StoryItem,
    currentPage: Int,
    isHindi: Boolean,
    onDismiss: () -> Unit,
    onToggleLanguage: () -> Unit,
    onNext: () -> Unit,
    onPrev: () -> Unit,
    onRead: () -> Unit
) {
    BackHandler(onBack = onDismiss)

    val page = story.pages.getOrNull(currentPage)
    val isLastPage = currentPage == story.pages.size - 1

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
                .wrapContentHeight()
                .padding(8.dp)
                .testTag("story_reader_dialog")
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(18.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Header with title, language switch, and close
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = if (isHindi) story.titleHi else story.titleEn,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = KidsTextPrimary
                            ),
                            maxLines = 1
                        )
                        Text(
                            text = "Page ${currentPage + 1} of ${story.pages.size}",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = KidsTextSecondary
                            )
                        )
                    }

                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        Button(
                            onClick = onToggleLanguage,
                            colors = ButtonDefaults.buttonColors(containerColor = PastelYellow),
                            shape = RoundedCornerShape(12.dp),
                            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
                            modifier = Modifier.testTag("toggle_story_language_button")
                        ) {
                            Text(
                                text = if (isHindi) "English 🇬🇧" else "हिन्दी 🇮🇳",
                                style = MaterialTheme.typography.labelMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF854D0E)
                                )
                            )
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
                                contentDescription = "Close story",
                                tint = KidsTextSecondary
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Page Illustration Placeholder & Emoji
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(130.dp)
                        .clip(RoundedCornerShape(22.dp))
                        .background(story.cardColor),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = page?.emoji ?: story.coverEmoji,
                        fontSize = 58.sp
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Page Story Text
                Surface(
                    shape = RoundedCornerShape(18.dp),
                    color = Color(0xFFF8FAFC),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = if (isHindi) page?.textHi.orEmpty() else page?.textEn.orEmpty(),
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.Medium,
                                color = KidsTextPrimary,
                                fontSize = 17.sp,
                                lineHeight = 24.sp,
                                textAlign = TextAlign.Center
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }

                // If on final page: Show Moral Banner!
                if (isLastPage) {
                    Spacer(modifier = Modifier.height(12.dp))
                    Surface(
                        shape = RoundedCornerShape(16.dp),
                        color = PastelGreen,
                        border = androidx.compose.foundation.BorderStroke(1.dp, KidsGreen)
                    ) {
                        Column(
                            modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "🏆 Moral of the Story:",
                                style = MaterialTheme.typography.labelLarge.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = KidsGreen
                                )
                            )
                            Text(
                                text = if (isHindi) story.moralHi else story.moralEn,
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = KidsTextPrimary,
                                    textAlign = TextAlign.Center
                                )
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Bottom Controls: Prev, Read Aloud, Next
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = onPrev,
                        enabled = currentPage > 0,
                        modifier = Modifier
                            .size(46.dp)
                            .clip(CircleShape)
                            .background(if (currentPage > 0) PastelBlue else Color(0xFFF1F5F9))
                            .testTag("prev_story_page")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Previous Page",
                            tint = if (currentPage > 0) KidsPrimary else Color.LightGray
                        )
                    }

                    Button(
                        onClick = onRead,
                        colors = ButtonDefaults.buttonColors(containerColor = KidsPrimary),
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier
                            .height(46.dp)
                            .testTag("read_story_page_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.VolumeUp,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Read Aloud", fontWeight = FontWeight.Bold)
                    }

                    IconButton(
                        onClick = onNext,
                        modifier = Modifier
                            .size(46.dp)
                            .clip(CircleShape)
                            .background(PastelBlue)
                            .testTag("next_story_page")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = "Next Page",
                            tint = KidsPrimary
                        )
                    }
                }
            }
        }
    }
}
