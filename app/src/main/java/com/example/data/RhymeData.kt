package com.example.data

import androidx.compose.ui.graphics.Color
import com.example.ui.theme.*

data class RhymeItem(
    val id: String,
    val title: String,
    val subtitle: String,
    val isHindi: Boolean,
    val emoji: String,
    val cardColor: Color,
    val badgeColor: Color,
    val lines: List<String>
)

object RhymeRepository {
    val rhymes = listOf(
        RhymeItem(
            id = "twinkle_twinkle",
            title = "Twinkle Twinkle Little Star",
            subtitle = "Classic Lullaby Rhyme",
            isHindi = false,
            emoji = "⭐",
            cardColor = PastelYellow,
            badgeColor = KidsTertiary,
            lines = listOf(
                "Twinkle, twinkle, little star,",
                "How I wonder what you are!",
                "Up above the world so high,",
                "Like a diamond in the sky.",
                "When the blazing sun is gone,",
                "When he nothing shines upon,",
                "Then you show your little light,",
                "Twinkle, twinkle, all the night!"
            )
        ),
        RhymeItem(
            id = "titli_udi",
            title = "तितली उड़ी बस पर चढ़ी",
            subtitle = "मजेदार हिंदी बालगीत",
            isHindi = true,
            emoji = "🦋",
            cardColor = PastelPink,
            badgeColor = KidsPink,
            lines = listOf(
                "तितली उड़ी, बस पर चढ़ी,",
                "सीट ना मिली तो रोने लगी!",
                "ड्राइवर ने बोला, आजा मेरे पास,",
                "तितली बोली, ना बाबा ना!",
                "मेरा घर है पास!"
            )
        ),
        RhymeItem(
            id = "johny_johny",
            title = "Johny Johny Yes Papa",
            subtitle = "Playful Nursery Rhyme",
            isHindi = false,
            emoji = "🍬",
            cardColor = PastelTeal,
            badgeColor = KidsSkyBlue,
            lines = listOf(
                "Johny, Johny, Yes, Papa?",
                "Eating sugar? No, Papa!",
                "Telling lies? No, Papa!",
                "Open your mouth, Ha! Ha! Ha!"
            )
        ),
        RhymeItem(
            id = "chanda_mama",
            title = "चंदा मामा दूर के",
            subtitle = "सुप्रसिद्ध मीठी लोरी",
            isHindi = true,
            emoji = "🌙",
            cardColor = PastelPurple,
            badgeColor = KidsPurple,
            lines = listOf(
                "चंदा मामा दूर के,",
                "पुए पकाएं बूर के!",
                "आप खाएं थाली में,",
                "मुन्ने को दें प्याली में!",
                "प्याली गई टूट, मुन्ना गया रूठ,",
                "लाएंगे नई प्यालियां, बजा बजा के तालियां!"
            )
        ),
        RhymeItem(
            id = "baa_baa_sheep",
            title = "Baa Baa Black Sheep",
            subtitle = "Cheerful Farm Rhyme",
            isHindi = false,
            emoji = "🐑",
            cardColor = PastelBlue,
            badgeColor = KidsPrimary,
            lines = listOf(
                "Baa, baa, black sheep,",
                "Have you any wool?",
                "Yes, sir, yes, sir,",
                "Three bags full!",
                "One for the master,",
                "And one for the dame,",
                "And one for the little boy",
                "Who lives down the lane!"
            )
        ),
        RhymeItem(
            id = "machhli_jal",
            title = "मछली जल की रानी है",
            subtitle = "प्यारा जलीय बालगीत",
            isHindi = true,
            emoji = "🐟",
            cardColor = PastelTeal,
            badgeColor = KidsSkyBlue,
            lines = listOf(
                "मछली जल की रानी है,",
                "जीवन उसका पानी है!",
                "हाथ लगाओ तो डर जाएगी,",
                "बाहर निकालो तो मर जाएगी!",
                "पानी में डालो तो तैर जाएगी!"
            )
        ),
        RhymeItem(
            id = "humpty_dumpty",
            title = "Humpty Dumpty",
            subtitle = "Fun Classic Nursery Rhyme",
            isHindi = false,
            emoji = "🥚",
            cardColor = PastelOrange,
            badgeColor = KidsSecondary,
            lines = listOf(
                "Humpty Dumpty sat on a wall,",
                "Humpty Dumpty had a great fall!",
                "All the king's horses and all the king's men,",
                "Couldn't put Humpty together again!"
            )
        ),
        RhymeItem(
            id = "bandar_mama",
            title = "बंदर मामा पहन पजामा",
            subtitle = "मजेदार बंदर बालगीत",
            isHindi = true,
            emoji = "🐒",
            cardColor = PastelGreen,
            badgeColor = KidsGreen,
            lines = listOf(
                "बंदर मामा पहन पजामा,",
                "दावत खाने आए!",
                "ढीला कुरता, टोपी जूता,",
                "पहने बहुत इतराए!",
                "रसगुल्ले पर जी ललचाया,",
                "मुंह में रखा गरम-गरम,",
                "जीभ जल गई लप-लप-लप,",
                "मामा भागे दम दबाकर!"
            )
        )
    )
}
