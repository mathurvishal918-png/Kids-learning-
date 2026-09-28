package com.example.data

import androidx.compose.ui.graphics.Color
import com.example.ui.theme.*

data class HindiLetter(
    val letter: String,
    val romanization: String,
    val wordHindi: String,
    val wordEnglish: String,
    val meaning: String,
    val emoji: String,
    val spokenText: String,
    val cardColor: Color
)

object HindiAlphabetRepository {
    val swarList = listOf(
        HindiLetter("अ", "a", "अनार", "Anaar", "Pomegranate", "🍎", "अ से अनार", PastelRed),
        HindiLetter("आ", "aa", "आम", "Aam", "Mango", "🥭", "आ से आम", PastelYellow),
        HindiLetter("इ", "i", "इमली", "Imli", "Tamarind", "🫘", "इ से इमली", PastelGreen),
        HindiLetter("ई", "ee", "ईख", "Eekh", "Sugarcane", "🎋", "ई से ईख", PastelTeal),
        HindiLetter("उ", "u", "उल्लू", "Ullu", "Owl", "🦉", "उ से उल्लू", PastelOrange),
        HindiLetter("ऊ", "oo", "ऊन", "Oon", "Wool", "🧶", "ऊ से ऊन", PastelPink),
        HindiLetter("ऋ", "ri", "ऋषि", "Rishi", "Sage", "🧘", "ऋ से ऋषि", PastelPurple),
        HindiLetter("ए", "e", "एड़ी", "Edi", "Heel", "🦶", "ए से एड़ी", PastelBlue),
        HindiLetter("ऐ", "ai", "ऐनक", "Ainak", "Spectacles", "👓", "ऐ से ऐनक", PastelYellow),
        HindiLetter("ओ", "o", "ओखली", "Okhli", "Mortar", "🥣", "ओ से ओखली", PastelTeal),
        HindiLetter("औ", "au", "औरत", "Aurat", "Woman", "👩", "औ से औरत", PastelPink),
        HindiLetter("अं", "am", "अंगूर", "Angoor", "Grapes", "🍇", "अं से अंगूर", PastelPurple),
        HindiLetter("अः", "ah", "खाली", "Khaali", "Aha! Happy Smile", "✨", "अः से खाली", PastelYellow)
    )

    val vyanjanList = listOf(
        HindiLetter("क", "ka", "कबूतर", "Kabutar", "Pigeon", "🕊️", "क से कबूतर", PastelBlue),
        HindiLetter("ख", "kha", "खरगोश", "Khargosh", "Rabbit", "🐇", "ख से खरगोश", PastelPink),
        HindiLetter("ग", "ga", "गमला", "Gamla", "Flower Pot", "🪴", "ग से गमला", PastelGreen),
        HindiLetter("घ", "gha", "घड़ी", "Ghadi", "Watch / Clock", "⏰", "घ से घड़ी", PastelYellow),
        HindiLetter("ङ", "nga", "खाली", "Khaali", "Empty sound", "💫", "ङ से खाली", PastelOrange),
        HindiLetter("च", "cha", "चम्मच", "Chammach", "Spoon", "🥄", "च से चम्मच", PastelTeal),
        HindiLetter("छ", "chha", "छतरी", "Chhatri", "Umbrella", "☂️", "छ से छतरी", PastelPurple),
        HindiLetter("ज", "ja", "जहाज", "Jahaaz", "Ship", "🚢", "ज से जहाज", PastelBlue),
        HindiLetter("झ", "jha", "झंडा", "Jhanda", "Flag", "🇮🇳", "झ से झंडा", PastelOrange),
        HindiLetter("ञ", "nya", "खाली", "Khaali", "Empty sound", "🌟", "ञ से खाली", PastelYellow),
        HindiLetter("ट", "ta", "टमाटर", "Tamatar", "Tomato", "🍅", "ट से टमाटर", PastelRed),
        HindiLetter("ठ", "tha", "ठठेरा", "Thathera", "Coppersmith", "🔨", "ठ से ठठेरा", PastelOrange),
        HindiLetter("ड", "da", "डमरू", "Damru", "Drum", "🪘", "ड से डमरू", PastelTeal),
        HindiLetter("ढ", "dha", "ढक्कन", "Dhakkan", "Lid", "🍲", "ढ से ढक्कन", PastelGreen),
        HindiLetter("ण", "nna", "खाली", "Khaali", "Empty sound", "✨", "ण से खाली", PastelYellow),
        HindiLetter("त", "ta", "तरबूज", "Tarbooz", "Watermelon", "🍉", "त से तरबूज", PastelRed),
        HindiLetter("थ", "tha", "थर्मस", "Thermos", "Flask", "🧴", "थ से थर्मस", PastelBlue),
        HindiLetter("द", "da", "दवात", "Dawaat", "Inkpot", "🖋️", "द से दवात", PastelPurple),
        HindiLetter("ध", "dha", "धनुष", "Dhanush", "Bow", "🏹", "ध से धनुष", PastelOrange),
        HindiLetter("न", "na", "नल", "Nal", "Water Tap", "🚰", "न से नल", PastelTeal),
        HindiLetter("प", "pa", "पतंग", "Patang", "Kite", "🪁", "प से पतंग", PastelYellow),
        HindiLetter("फ", "pha", "फल", "Phal", "Fruits", "🍎", "फ से फल", PastelRed),
        HindiLetter("ब", "ba", "बत्तख", "Battakh", "Duck", "🦆", "ब से बत्तख", PastelYellow),
        HindiLetter("भ", "bha", "भालू", "Bhaalu", "Bear", "🐻", "भ से भालू", PastelOrange),
        HindiLetter("म", "ma", "मछली", "Machhli", "Fish", "🐟", "म से मछली", PastelBlue),
        HindiLetter("य", "ya", "यज्ञ", "Yagya", "Sacred Fire", "🔥", "य से यज्ञ", PastelOrange),
        HindiLetter("र", "ra", "रथ", "Rath", "Chariot", "🛞", "र से रथ", PastelYellow),
        HindiLetter("ल", "la", "लट्टू", "Lattoo", "Spinning Top", "🪀", "ल से लट्टू", PastelGreen),
        HindiLetter("व", "va", "वक", "Vak", "Crane / Heron", "🦩", "व से वक", PastelPink),
        HindiLetter("श", "sha", "शलजम", "Shaljam", "Turnip", "🥕", "श से शलजम", PastelPurple),
        HindiLetter("ष", "ssa", "षटकोण", "Shatkon", "Hexagon", "⬡", "ष से षटकोण", PastelTeal),
        HindiLetter("स", "sa", "सेब", "Seb", "Apple", "🍏", "स से सेब", PastelGreen),
        HindiLetter("ह", "ha", "हाथी", "Haathi", "Elephant", "🐘", "ह से हाथी", PastelBlue),
        HindiLetter("क्ष", "ksha", "क्षत्रिय", "Kshatriya", "Warrior", "🛡️", "क्ष से क्षत्रिय", PastelOrange),
        HindiLetter("त्र", "tra", "त्रिशूल", "Trishool", "Trident", "🔱", "त्र से त्रिशूल", PastelYellow),
        HindiLetter("ज्ञ", "gya", "ज्ञानी", "Gyaani", "Wise Scholar", "📚", "ज्ञ से ज्ञानी", PastelPurple)
    )
}
