package com.example.data

import androidx.compose.ui.graphics.Color
import com.example.ui.theme.*

data class NumberItem(
    val value: Int,
    val word: String,
    val hindiWord: String,
    val emoji: String,
    val cardColor: Color,
    val isEven: Boolean
)

object NumberRepository {
    private val numberWords = mapOf(
        1 to Pair("One", "एक"), 2 to Pair("Two", "दो"), 3 to Pair("Three", "तीन"),
        4 to Pair("Four", "चार"), 5 to Pair("Five", "पाँच"), 6 to Pair("Six", "छह"),
        7 to Pair("Seven", "सात"), 8 to Pair("Eight", "आठ"), 9 to Pair("Nine", "नौ"),
        10 to Pair("Ten", "दस"), 11 to Pair("Eleven", "ग्यारह"), 12 to Pair("Twelve", "बारह"),
        13 to Pair("Thirteen", "तेरह"), 14 to Pair("Fourteen", "चौदह"), 15 to Pair("Fifteen", "पंद्रह"),
        16 to Pair("Sixteen", "सोलह"), 17 to Pair("Seventeen", "सत्रह"), 18 to Pair("Eighteen", "अठारह"),
        19 to Pair("Nineteen", "उन्नीस"), 20 to Pair("Twenty", "बीस"), 30 to Pair("Thirty", "तीस"),
        40 to Pair("Forty", "चालीस"), 50 to Pair("Fifty", "पचास"), 60 to Pair("Sixty", "साठ"),
        70 to Pair("Seventy", "सत्तर"), 80 to Pair("Eighty", "अस्सी"), 90 to Pair("Ninety", "नब्बे"),
        100 to Pair("One Hundred", "एक सौ")
    )

    private val emojis = listOf("⭐", "🎈", "🍎", "🍭", "🚗", "🌸", "🦋", "🐬", "🍓", "🧁")

    fun getNumberWord(n: Int): String {
        if (numberWords.containsKey(n)) return numberWords[n]!!.first
        val tens = (n / 10) * 10
        val units = n % 10
        val tensWord = numberWords[tens]?.first ?: ""
        val unitWord = numberWords[units]?.first ?: ""
        return "$tensWord $unitWord".trim()
    }

    fun getHindiWord(n: Int): String {
        return numberWords[n]?.second ?: "संख्या $n"
    }

    val numbers1To100: List<NumberItem> by lazy {
        (1..100).map { n ->
            val colorIndex = (n - 1) % 6
            val color = when (colorIndex) {
                0 -> PastelRed
                1 -> PastelOrange
                2 -> PastelYellow
                3 -> PastelGreen
                4 -> PastelBlue
                else -> PastelPurple
            }
            val emoji = emojis[(n - 1) % emojis.size]
            NumberItem(
                value = n,
                word = getNumberWord(n),
                hindiWord = getHindiWord(n),
                emoji = emoji,
                cardColor = color,
                isEven = n % 2 == 0
            )
        }
    }

    val groups = listOf(
        "All 1-100" to (1..100),
        "1 - 10" to (1..10),
        "11 - 20" to (11..20),
        "21 - 30" to (21..30),
        "31 - 40" to (31..40),
        "41 - 50" to (41..50),
        "51 - 60" to (51..60),
        "61 - 70" to (61..70),
        "71 - 80" to (71..80),
        "81 - 90" to (81..90),
        "91 - 100" to (91..100)
    )
}
