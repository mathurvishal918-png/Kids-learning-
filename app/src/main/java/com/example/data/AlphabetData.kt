package com.example.data

import androidx.compose.ui.graphics.Color
import com.example.ui.theme.*

data class AlphabetItem(
    val letter: Char,
    val displayLetter: String, // e.g. "Aa"
    val word: String,          // e.g. "Apple"
    val phonetic: String,      // e.g. "A says /æ/"
    val sentence: String,      // e.g. "A is for sweet red Apple"
    val funFact: String,       // e.g. "Apples float in water!"
    val emoji: String,         // e.g. "🍎"
    val cardColor: Color,
    val badgeColor: Color
)

object AlphabetRepository {
    val alphabetList = listOf(
        AlphabetItem('A', "Aa", "Apple", "A says /æ/", "A is for sweet red Apple", "Apples float because they are 25% air!", "🍎", PastelRed, KidsSecondary),
        AlphabetItem('B', "Bb", "Ball", "B says /b/", "B is for bouncy colorful Ball", "Balls have been played with for thousands of years!", "⚽", PastelBlue, KidsPrimary),
        AlphabetItem('C', "Cc", "Cat", "C says /k/", "C is for cute cuddly Cat", "Cats can jump up to 6 times their height!", "🐱", PastelYellow, KidsTertiary),
        AlphabetItem('D', "Dd", "Dog", "D says /d/", "D is for loyal friendly Dog", "A dog's sense of smell is 40 times better than ours!", "🐶", PastelGreen, KidsGreen),
        AlphabetItem('E', "Ee", "Elephant", "E says /e/", "E is for enormous gentle Elephant", "Elephants communicate with sounds we cannot hear!", "🐘", PastelPurple, KidsPurple),
        AlphabetItem('F', "Ff", "Fish", "F says /f/", "F is for swimming shiny Fish", "Fish have lived on Earth before dinosaurs!", "🐟", PastelTeal, KidsSkyBlue),
        AlphabetItem('G', "Gg", "Giraffe", "G says /g/", "G is for tall graceful Giraffe", "Giraffes have purple tongues to prevent sunburn!", "🦒", PastelOrange, KidsSecondary),
        AlphabetItem('H', "Hh", "Hat", "H says /h/", "H is for sunny stylish Hat", "Hats keep you cool in summer and warm in winter!", "👒", PastelPink, KidsPink),
        AlphabetItem('I', "Ii", "Ice Cream", "I says /aɪ/", "I is for yummy chilled Ice Cream", "Vanilla is the world's most popular ice cream flavor!", "🍦", PastelYellow, KidsTertiary),
        AlphabetItem('J', "Jj", "Juice", "J says /dʒ/", "J is for refreshing fresh Juice", "Fresh juice is full of vitamins to make you strong!", "🧃", PastelOrange, KidsSecondary),
        AlphabetItem('K', "Kk", "Kite", "K says /k/", "K is for flying high Kite", "Kites were invented over 2,000 years ago in China!", "🪁", PastelTeal, KidsSkyBlue),
        AlphabetItem('L', "Ll", "Lion", "L says /l/", "L is for brave roaring Lion", "A lion's roar can be heard from 5 miles away!", "🦁", PastelYellow, KidsTertiary),
        AlphabetItem('M', "Mm", "Monkey", "M says /m/", "M is for playful silly Monkey", "Monkeys use their tails like an extra arm or leg!", "🐵", PastelGreen, KidsGreen),
        AlphabetItem('N', "Nn", "Nest", "N says /n/", "N is for cozy warm bird Nest", "Birds weave twigs together carefully to make nests!", "🪺", PastelOrange, KidsSecondary),
        AlphabetItem('O', "Oo", "Orange", "O says /ɒ/", "O is for juicy citrus Orange", "Oranges are packed with vitamin C for healthy smiles!", "🍊", PastelOrange, KidsSecondary),
        AlphabetItem('P', "Pp", "Penguin", "P says /p/", "P is for waddling cute Penguin", "Penguins can slide on their bellies across the ice!", "🐧", PastelBlue, KidsPrimary),
        AlphabetItem('Q', "Qq", "Queen", "Q says /kw/", "Q is for royal kind Queen", "Queens wear sparkling crowns of gold and jewels!", "👑", PastelPurple, KidsPurple),
        AlphabetItem('R', "Rr", "Rainbow", "R says /r/", "R is for seven-colored Rainbow", "Sunlight and rain drops work together to make rainbows!", "🌈", PastelPink, KidsPink),
        AlphabetItem('S', "Ss", "Sun", "S says /s/", "S is for bright warm shining Sun", "The Sun gives energy and light to all living things!", "☀️", PastelYellow, KidsTertiary),
        AlphabetItem('T', "Tt", "Tiger", "T says /t/", "T is for striped roaring Tiger", "No two tigers have the exact same stripe pattern!", "🐯", PastelOrange, KidsSecondary),
        AlphabetItem('U', "Uu", "Umbrella", "U says /ʌ/", "U is for rainy day Umbrella", "Umbrellas protect us from both raindrops and bright sun!", "☂️", PastelPurple, KidsPurple),
        AlphabetItem('V', "Vv", "Van", "V says /v/", "V is for traveling family Van", "Vans carry happy kids to fun school picnics!", "🚐", PastelTeal, KidsSkyBlue),
        AlphabetItem('W', "Ww", "Watermelon", "W says /w/", "W is for sweet juicy Watermelon", "Watermelon is 92% refreshing sweet water!", "🍉", PastelRed, KidsSecondary),
        AlphabetItem('X', "Xx", "Xylophone", "X says /z/ or /ks/", "X is for musical bright Xylophone", "Each metal bar on a xylophone plays a different music note!", "🎵", PastelPink, KidsPink),
        AlphabetItem('Y', "Yy", "Yak", "Y says /j/", "Y is for furry gentle mountain Yak", "Yaks have long warm fur coats to stay warm in snow!", "🐂", PastelGreen, KidsGreen),
        AlphabetItem('Z', "Zz", "Zebra", "Z says /z/", "Z is for black and white Zebra", "Zebras stand together so lions get confused by stripes!", "🦓", PastelBlue, KidsPrimary)
    )
}
