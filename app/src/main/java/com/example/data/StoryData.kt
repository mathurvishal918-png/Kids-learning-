package com.example.data

import androidx.compose.ui.graphics.Color
import com.example.ui.theme.*

data class StoryPage(
    val pageNumber: Int,
    val textEn: String,
    val textHi: String,
    val emoji: String
)

data class StoryItem(
    val id: String,
    val titleEn: String,
    val titleHi: String,
    val summaryEn: String,
    val summaryHi: String,
    val moralEn: String,
    val moralHi: String,
    val coverEmoji: String,
    val cardColor: Color,
    val badgeColor: Color,
    val pages: List<StoryPage>
)

object StoryRepository {
    val stories = listOf(
        StoryItem(
            id = "thirsty_crow",
            titleEn = "The Thirsty Crow",
            titleHi = "प्यासा कौवा",
            summaryEn = "A clever crow finds an ingenious way to drink water from a tall pitcher.",
            summaryHi = "एक चतुर कौवा अपनी बुद्धि से घड़े में से पानी पीता है।",
            moralEn = "Where there is a will, there is always a way!",
            moralHi = "जहाँ चाह, वहाँ राह!",
            coverEmoji = "🦅",
            cardColor = PastelYellow,
            badgeColor = KidsTertiary,
            pages = listOf(
                StoryPage(
                    1,
                    "Once upon a hot summer day, a thirsty crow flew all around the forest looking for water.",
                    "एक गरम दोपहर में, एक प्यासा कौवा पानी की खोज में जंगल में भटक रहा था।",
                    "☀️"
                ),
                StoryPage(
                    2,
                    "At last, he saw a pitcher in a garden. He flew down eagerly to take a sip.",
                    "आखिरकार, उसे एक बगीचे में एक घड़ा दिखाई दिया। वह खुशी से नीचे उतरा।",
                    "🏺"
                ),
                StoryPage(
                    3,
                    "The pitcher had very little water at the bottom. His beak could not reach it!",
                    "घड़े में बहुत कम पानी था। उसकी चोंच पानी तक नहीं पहुँच पा रही थी।",
                    "🥺"
                ),
                StoryPage(
                    4,
                    "The clever crow saw small pebbles on the ground. He picked them up one by one and dropped them into the pitcher.",
                    "चतुर कौवे ने जमीन पर कंकड़ देखे। उसने एक-एक करके कंकड़ घड़े में डाले।",
                    "🪨"
                ),
                StoryPage(
                    5,
                    "With each pebble, the water level rose higher and higher until the crow could drink happily!",
                    "पानी ऊपर आ गया! कौवे ने जी भर कर ठंडा पानी पिया और खुशी से उड़ गया।",
                    "🎉"
                )
            )
        ),
        StoryItem(
            id = "lion_and_mouse",
            titleEn = "The Lion and the Mouse",
            titleHi = "शेर और नन्हा चूहा",
            summaryEn = "A tiny mouse saves the king of the jungle from a hunter's net.",
            summaryHi = "एक छोटा सा चूहा जंगल के राजा शेर की जान बचाता है।",
            moralEn = "No act of kindness, no matter how small, is ever wasted!",
            moralHi = "भलाई का काम कभी व्यर्थ नहीं जाता, छोटा जीव भी बड़ा काम कर सकता है!",
            coverEmoji = "🦁",
            cardColor = PastelOrange,
            badgeColor = KidsSecondary,
            pages = listOf(
                StoryPage(
                    1,
                    "A mighty lion was sleeping peacefully under a shaded tree in the jungle.",
                    "एक बड़ा और शक्तिशाली शेर जंगल में एक पेड़ की छांव में सो रहा था।",
                    "😴"
                ),
                StoryPage(
                    2,
                    "A playful little mouse accidentally ran over the sleeping lion's nose and woke him up!",
                    "एक नन्हा चूहा खेलते-खेलते शेर की नाक पर कूद गया और शेर जाग गया!",
                    "🐭"
                ),
                StoryPage(
                    3,
                    "The lion grabbed the mouse angrily. 'Please spare me!' squeaked the mouse. 'One day I might help you!'",
                    "शेर ने गुस्से में चूहे को पकड़ लिया। चूहे ने प्रार्थना की, 'मुझे छोड़ दो, कभी मैं भी तुम्हारी मदद करूंगा!'",
                    "🦁"
                ),
                StoryPage(
                    4,
                    "The lion laughed and let him go. A few days later, the lion got caught in a hunter's strong rope net!",
                    "शेर हंस पड़ा और उसे छोड़ दिया। कुछ दिनों बाद शेर शिकारी के जाल में फंस गया!",
                    "🕸️"
                ),
                StoryPage(
                    5,
                    "Hearing the lion's roar, the mouse ran quickly and chewed through the ropes with his sharp teeth, setting the lion free!",
                    "शेर की दहाड़ सुनकर चूहा दौड़ा आया और अपने तेज दांतों से जाल काट दिया। शेर आज़ाद हो गया!",
                    "🤝"
                )
            )
        ),
        StoryItem(
            id = "hare_and_tortoise",
            titleEn = "The Hare and the Tortoise",
            titleHi = "कछुआ और खरगोश",
            summaryEn = "A speedy hare learns a valuable lesson in patience and perseverance.",
            summaryHi = "तेज़ दौड़ने वाला घमंडी खरगोश धैर्यवान कछुए से सीख लेता है।",
            moralEn = "Slow and steady wins the race!",
            moralHi = "लगातार और धैर्यपूर्वक प्रयास करने से ही सफलता मिलती है!",
            coverEmoji = "🐢",
            cardColor = PastelGreen,
            badgeColor = KidsGreen,
            pages = listOf(
                StoryPage(
                    1,
                    "A speedy hare always boasted about how fast he could run, teasing the slow-moving tortoise.",
                    "एक तेज़ खरगोश अपनी गति पर घमंड करता था और धीरे चलने वाले कछुए का मज़ाक उड़ाता था।",
                    "🐇"
                ),
                StoryPage(
                    2,
                    "The calm tortoise challenged the hare to a race. All the forest animals gathered to watch!",
                    "शांत कछुए ने खरगोश को दौड़ की चुनौती दी। सभी जानवर देखने इकट्ठे हुए!",
                    "🏁"
                ),
                StoryPage(
                    3,
                    "The race began! The hare dashed ahead in seconds, leaving the tortoise far behind.",
                    "दौड़ शुरू हुई! खरगोश पलक झपकते ही बहुत आगे निकल गया।",
                    "💨"
                ),
                StoryPage(
                    4,
                    "Feeling overly confident, the hare decided to take a short nap under a shady tree.",
                    "घमंड में आकर खरगोश ने सोचा, 'कछुआ तो बहुत दूर है, थोड़ी देर सो लेता हूँ।' वह सो गया।",
                    "🌳"
                ),
                StoryPage(
                    5,
                    "The tortoise kept moving step by step without stopping. He crossed the finish line and won!",
                    "कछुआ बिना रुके धीरे-धीरे चलता रहा और जीत की रेखा पार कर विजेता बन गया!",
                    "🏆"
                )
            )
        ),
        StoryItem(
            id = "honest_woodcutter",
            titleEn = "The Honest Woodcutter",
            titleHi = "ईमानदार लकड़हारा",
            summaryEn = "An honest poor woodcutter is rewarded for his truthfulness by a water spirit.",
            summaryHi = "एक गरीब लकड़हारे को उसकी ईमानदारी के लिए जल देवता से पुरस्कार मिलता है।",
            moralEn = "Honesty is always the best policy!",
            moralHi = "ईमानदारी ही सबसे अच्छी नीति है!",
            coverEmoji = "🪓",
            cardColor = PastelTeal,
            badgeColor = KidsSkyBlue,
            pages = listOf(
                StoryPage(
                    1,
                    "A poor woodcutter accidentally dropped his iron axe into a deep river.",
                    "एक गरीब लकड़हारे की लोहे की कुल्हाड़ी नदी में गिर गई।",
                    "🌊"
                ),
                StoryPage(
                    2,
                    "He sat crying by the bank when a kind water fairy appeared to help him.",
                    "वह किनारे बैठकर रोने लगा तभी एक दयालु जल देवता प्रकट हुए।",
                    "🧚"
                ),
                StoryPage(
                    3,
                    "The fairy brought out a glittering golden axe. The honest woodcutter said, 'No, that is not mine.'",
                    "देवता ने सोने की कुल्हाड़ी निकाली। ईमानदार लकड़हारे ने कहा, 'नहीं, यह मेरी नहीं है।' ",
                    "✨"
                ),
                StoryPage(
                    4,
                    "The fairy brought a silver axe, but he again refused. Finally she brought his iron axe.",
                    "देवता ने चांदी की कुल्हाड़ी निकाली, उसने फिर मना किया। अंत में लोहे की कुल्हाड़ी निकाली।",
                    "🪓"
                ),
                StoryPage(
                    5,
                    "'Yes! That is mine!' he beamed. Delighted by his honesty, the fairy gave him all three axes as a gift!",
                    "उसकी ईमानदारी से खुश होकर देवता ने उसे सोने और चांदी की कुल्हाड़ियां भी उपहार में दे दीं!",
                    "🎁"
                )
            )
        )
    )
}
