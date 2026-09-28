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
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Quiz
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
import com.example.data.MathTable
import com.example.data.MathTableRepository
import com.example.data.TableRowItem
import com.example.ui.theme.*

@Composable
fun MathTablesScreen(
    selectedTable: MathTable,
    isSpeaking: Boolean,
    isQuizOpen: Boolean,
    quizIndex: Int,
    quizFeedback: String?,
    onSelectTable: (MathTable) -> Unit,
    onSpeakRow: (TableRowItem) -> Unit,
    onReadFullTable: () -> Unit,
    onStopReading: () -> Unit,
    onOpenQuiz: () -> Unit,
    onCloseQuiz: () -> Unit,
    onAnswerQuiz: (Int) -> Unit,
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
                colors = CardDefaults.cardColors(containerColor = PastelTeal),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(text = "✖️", fontSize = 28.sp)
                    Column {
                        Text(
                            text = "Math Tables (1 to 10)",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF0F766E)
                            )
                        )
                        Text(
                            text = "Choose a table, tap any row to listen, or take a quick quiz!",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = KidsTextSecondary
                            )
                        )
                    }
                }
            }

            // Tables Selector Row (1 to 10)
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                items(MathTableRepository.tables) { table ->
                    val isSelected = table.number == selectedTable.number
                    Surface(
                        shape = RoundedCornerShape(16.dp),
                        color = if (isSelected) KidsSecondary else table.color,
                        elevation = if (isSelected) 4.dp else 1.dp,
                        modifier = Modifier
                            .clickable { onSelectTable(table) }
                            .testTag("table_chip_${table.number}")
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp)
                        ) {
                            Text(
                                text = "Table",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = FontWeight.Medium,
                                    color = if (isSelected) Color.White else KidsTextSecondary
                                )
                            )
                            Text(
                                text = "${table.number}",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Black,
                                    color = if (isSelected) Color.White else KidsTextPrimary
                                )
                            )
                        }
                    }
                }
            }

            // Action Bar: "Read Full Table" & "Quiz"
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Button(
                    onClick = {
                        if (isSpeaking) onStopReading() else onReadFullTable()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isSpeaking) KidsSecondary else KidsPrimary
                    ),
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier
                        .weight(1f)
                        .height(46.dp)
                        .testTag("read_full_table_button")
                ) {
                    Icon(
                        imageVector = if (isSpeaking) Icons.Default.Stop else Icons.Default.PlayArrow,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = if (isSpeaking) "Stop Table" else "Read Table",
                        fontWeight = FontWeight.Bold
                    )
                }

                Button(
                    onClick = onOpenQuiz,
                    colors = ButtonDefaults.buttonColors(containerColor = KidsTertiary),
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier
                        .weight(1f)
                        .height(46.dp)
                        .testTag("practice_quiz_button")
                ) {
                    Icon(
                        imageVector = Icons.Default.Quiz,
                        contentDescription = null,
                        tint = Color(0xFF78350F),
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Practice Quiz", fontWeight = FontWeight.Bold, color = Color(0xFF78350F))
                }
            }

            // Full Multiplication Chart List (1 to 10)
            LazyColumn(
                contentPadding = PaddingValues(bottom = 90.dp, top = 4.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(selectedTable.rows) { row ->
                    TableRowCard(
                        row = row,
                        onClick = { onSpeakRow(row) }
                    )
                }
            }
        }

        // Quiz Dialog
        if (isQuizOpen) {
            TableQuizDialog(
                table = selectedTable,
                quizIndex = quizIndex,
                feedback = quizFeedback,
                onDismiss = onCloseQuiz,
                onAnswer = onAnswerQuiz
            )
        }
    }
}

@Composable
fun TableRowCard(
    row: TableRowItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .clickable(onClick = onClick)
            .testTag("table_row_${row.table}_${row.multiplier}")
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Formula display: 2 × 3 = 6
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Surface(
                    shape = CircleShape,
                    color = PastelBlue,
                    modifier = Modifier.size(34.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            text = "${row.multiplier}",
                            style = MaterialTheme.typography.labelLarge.copy(
                                fontWeight = FontWeight.Bold,
                                color = KidsPrimary
                            )
                        )
                    }
                }

                Text(
                    text = "${row.table}  ×  ${row.multiplier}  =",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = KidsTextPrimary,
                        fontSize = 18.sp
                    )
                )

                Text(
                    text = "${row.result}",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Black,
                        color = KidsSecondary,
                        fontSize = 22.sp
                    )
                )
            }

            // Speaker Icon
            IconButton(
                onClick = onClick,
                modifier = Modifier
                    .size(38.dp)
                    .clip(CircleShape)
                    .background(PastelYellow)
            ) {
                Icon(
                    imageVector = Icons.Default.VolumeUp,
                    contentDescription = "Speak row",
                    tint = Color(0xFF854D0E),
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}

@Composable
fun TableQuizDialog(
    table: MathTable,
    quizIndex: Int,
    feedback: String?,
    onDismiss: () -> Unit,
    onAnswer: (Int) -> Unit
) {
    BackHandler(onBack = onDismiss)

    val currentQuiz = table.quizQuestions.getOrNull(quizIndex)

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
                .testTag("table_quiz_dialog")
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Quiz: Table of ${table.number}",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = KidsPrimary
                        )
                    )

                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFF1F5F9))
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close quiz",
                            tint = KidsTextSecondary
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                if (currentQuiz != null) {
                    Text(
                        text = "Question ${quizIndex + 1} of ${table.quizQuestions.size}",
                        style = MaterialTheme.typography.labelMedium.copy(
                            color = KidsTextSecondary
                        )
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Surface(
                        shape = RoundedCornerShape(20.dp),
                        color = PastelYellow,
                        modifier = Modifier.padding(vertical = 8.dp)
                    ) {
                        Text(
                            text = currentQuiz.question,
                            style = MaterialTheme.typography.headlineMedium.copy(
                                fontWeight = FontWeight.Black,
                                color = Color(0xFF854D0E)
                            ),
                            modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // 3 Multiple Choice Options
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        currentQuiz.options.forEach { option ->
                            Button(
                                onClick = { onAnswer(option) },
                                colors = ButtonDefaults.buttonColors(containerColor = PastelBlue),
                                shape = RoundedCornerShape(16.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(50.dp)
                                    .testTag("quiz_option_$option")
                            ) {
                                Text(
                                    text = "$option",
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = KidsPrimary,
                                        fontSize = 20.sp
                                    )
                                )
                            }
                        }
                    }

                    // Feedback banner
                    if (feedback != null) {
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = feedback,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = if (feedback.contains("Correct")) KidsGreen else KidsSecondary,
                                textAlign = TextAlign.Center
                            )
                        )
                    }
                } else {
                    Text(
                        text = "🎉 Awesome! You mastered Table of ${table.number}!",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = KidsGreen,
                            textAlign = TextAlign.Center
                        )
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = onDismiss,
                        colors = ButtonDefaults.buttonColors(containerColor = KidsPrimary),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Text("Done")
                    }
                }
            }
        }
    }
}
