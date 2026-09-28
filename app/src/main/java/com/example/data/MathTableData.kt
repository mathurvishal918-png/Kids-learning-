package com.example.data

import androidx.compose.ui.graphics.Color
import com.example.ui.theme.*

data class TableRowItem(
    val table: Int,
    val multiplier: Int,
    val result: Int,
    val spokenEnglish: String,
    val spokenHindi: String
)

data class TableQuiz(
    val question: String,
    val options: List<Int>,
    val correctAnswer: Int
)

data class MathTable(
    val number: Int,
    val title: String,
    val color: Color,
    val rows: List<TableRowItem>,
    val quizQuestions: List<TableQuiz>
)

object MathTableRepository {
    private val numberWordSpoken = mapOf(
        1 to "one", 2 to "two", 3 to "three", 4 to "four", 5 to "five",
        6 to "six", 7 to "seven", 8 to "eight", 9 to "nine", 10 to "ten",
        11 to "eleven", 12 to "twelve", 13 to "thirteen", 14 to "fourteen", 15 to "fifteen",
        16 to "sixteen", 17 to "seventeen", 18 to "eighteen", 19 to "nineteen", 20 to "twenty",
        21 to "twenty one", 24 to "twenty four", 27 to "twenty seven", 28 to "twenty eight",
        30 to "thirty", 32 to "thirty two", 35 to "thirty five", 36 to "thirty six",
        40 to "forty", 42 to "forty two", 45 to "forty five", 48 to "forty eight", 49 to "forty nine",
        50 to "fifty", 54 to "fifty four", 56 to "fifty six", 60 to "sixty", 63 to "sixty three",
        64 to "sixty four", 70 to "seventy", 72 to "seventy two", 80 to "eighty", 81 to "eighty one",
        90 to "ninety", 100 to "one hundred"
    )

    private val colors = listOf(
        PastelRed, PastelOrange, PastelYellow,
        PastelGreen, PastelTeal, PastelBlue,
        PastelPurple, PastelPink, PastelOrange, PastelGreen
    )

    val tables: List<MathTable> by lazy {
        (1..10).map { t ->
            val rows = (1..10).map { m ->
                val res = t * m
                val resWord = numberWordSpoken[res] ?: "$res"
                val spokenEn = "$t times $m is $resWord"
                val spokenHi = "$t गुणा $m बराबर $res"
                TableRowItem(t, m, res, spokenEn, spokenHi)
            }

            val quiz = listOf(
                TableQuiz(
                    question = "$t × 2 = ?",
                    options = listOf(t * 2, t * 2 + 2, (t * 2 - 1).coerceAtLeast(1)).shuffled(),
                    correctAnswer = t * 2
                ),
                TableQuiz(
                    question = "$t × 5 = ?",
                    options = listOf(t * 5, t * 4, t * 6).shuffled(),
                    correctAnswer = t * 5
                ),
                TableQuiz(
                    question = "$t × 8 = ?",
                    options = listOf(t * 8, t * 7, t * 9).shuffled(),
                    correctAnswer = t * 8
                )
            )

            MathTable(
                number = t,
                title = "Table of $t",
                color = colors[t - 1],
                rows = rows,
                quizQuestions = quiz
            )
        }
    }
}
