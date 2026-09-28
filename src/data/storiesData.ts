export interface StoryPage {
  pageNumber: number;
  textEn: string;
  textHi: string;
  emoji: string;
}

export interface StoryItem {
  id: string;
  titleEn: string;
  titleHi: string;
  summaryEn: string;
  summaryHi: string;
  moralEn: string;
  moralHi: string;
  coverEmoji: string;
  themeColor: string;
  pages: StoryPage[];
}

export const STORIES_DATA: StoryItem[] = [
  {
    id: "thirsty_crow",
    titleEn: "The Thirsty Crow",
    titleHi: "प्यासा कौवा",
    summaryEn: "A clever crow finds an ingenious way to drink water from a tall pitcher.",
    summaryHi: "एक चतुर कौवा अपनी बुद्धि से घड़े में से पानी पीता है।",
    moralEn: "Where there is a will, there is always a way!",
    moralHi: "जहाँ चाह, वहाँ राह!",
    coverEmoji: "🦅",
    themeColor: "from-amber-400 to-orange-500",
    pages: [
      {
        pageNumber: 1,
        textEn: "Once upon a hot summer day, a thirsty crow flew all around the forest looking for water.",
        textHi: "एक गरम दोपहर में, एक प्यासा कौवा पानी की खोज में जंगल में इधर-उधर उड़ रहा था।",
        emoji: "☀️"
      },
      {
        pageNumber: 2,
        textEn: "At last, he saw a pitcher in a garden. He flew down eagerly to take a cool sip.",
        textHi: "आखिरकार, उसे एक बगीचे में एक घड़ा दिखाई दिया। वह खुशी से नीचे उतरा।",
        emoji: "🏺"
      },
      {
        pageNumber: 3,
        textEn: "The pitcher had very little water at the bottom. His beak could not reach it!",
        textHi: "घड़े में बहुत कम पानी था। उसकी चोंच पानी तक नहीं पहुँच पा रही थी।",
        emoji: "🥺"
      },
      {
        pageNumber: 4,
        textEn: "The clever crow saw small pebbles on the ground. He picked them up one by one and dropped them into the pitcher.",
        textHi: "चतुर कौवे ने जमीन पर पड़े कंकड़ देखे। उसने एक-एक करके कंकड़ घड़े में डाले।",
        emoji: "🪨"
      },
      {
        pageNumber: 5,
        textEn: "With each pebble, the water level rose higher until the crow could drink happily!",
        textHi: "पानी ऊपर आ गया! कौवे ने जी भर कर ठंडा पानी पिया और खुशी से उड़ गया।",
        emoji: "🎉"
      }
    ]
  },
  {
    id: "lion_and_mouse",
    titleEn: "The Lion and the Mouse",
    titleHi: "शेर और नन्हा चूहा",
    summaryEn: "A tiny mouse saves the king of the jungle from a hunter's strong net.",
    summaryHi: "एक छोटा सा चूहा जंगल के राजा शेर की जान बचाता है।",
    moralEn: "No act of kindness, however small, is ever wasted!",
    moralHi: "भलाई का काम कभी व्यर्थ नहीं जाता, छोटा जीव भी बड़ा काम कर सकता है!",
    coverEmoji: "🦁",
    themeColor: "from-orange-400 to-rose-500",
    pages: [
      {
        pageNumber: 1,
        textEn: "A mighty lion was sleeping peacefully under the shade of a big jungle tree.",
        textHi: "एक बड़ा और शक्तिशाली शेर जंगल में एक बड़े पेड़ की छांव में सो रहा था।",
        emoji: "😴"
      },
      {
        pageNumber: 2,
        textEn: "A playful little mouse accidentally ran over the sleeping lion's nose and woke him up!",
        textHi: "एक नन्हा चूहा खेलते-खेलते शेर की नाक पर कूद गया और शेर जाग गया!",
        emoji: "🐭"
      },
      {
        pageNumber: 3,
        textEn: "The lion grabbed the mouse angrily. 'Please spare me!' squeaked the mouse. 'One day I might help you!'",
        textHi: "शेर ने गुस्से में चूहे को पकड़ लिया। चूहे ने विनती की, 'मुझे छोड़ दो, कभी मैं भी तुम्हारी मदद करूंगा!'",
        emoji: "🦁"
      },
      {
        pageNumber: 4,
        textEn: "The lion laughed and let him go. A few days later, the lion was caught in a hunter's thick net!",
        textHi: "शेर हंसा और उसे छोड़ दिया। कुछ दिनों बाद शेर शिकारी के मोटे जाल में फंस गया!",
        emoji: "🕸️"
      },
      {
        pageNumber: 5,
        textEn: "Hearing the lion's roar, the mouse ran quickly and chewed through the ropes with his sharp teeth, setting the lion free!",
        textHi: "शेर की दहाड़ सुनकर चूहा दौड़ा आया और अपने तेज दांतों से जाल काट दिया। शेर आज़ाद हो गया!",
        emoji: "🤝"
      }
    ]
  },
  {
    id: "hare_and_tortoise",
    titleEn: "The Hare and the Tortoise",
    titleHi: "कछुआ और खरगोश",
    summaryEn: "A speedy hare learns a valuable lesson in patience and perseverance.",
    summaryHi: "तेज़ दौड़ने वाला घमंडी खरगोश धैर्यवान कछुए से सीख लेता है।",
    moralEn: "Slow and steady wins the race!",
    moralHi: "धैर्य और लगातार प्रयास करने से ही विजय मिलती है!",
    coverEmoji: "🐢",
    themeColor: "from-emerald-400 to-teal-500",
    pages: [
      {
        pageNumber: 1,
        textEn: "A speedy hare always boasted about how fast he could run, teasing the slow-moving tortoise.",
        textHi: "एक तेज़ खरगोश अपनी गति पर घमंड करता था और धीरे चलने वाले कछुए का मज़ाक उड़ाता था।",
        emoji: "🐇"
      },
      {
        pageNumber: 2,
        textEn: "The calm tortoise challenged the hare to a race. All the forest animals gathered to watch!",
        textHi: "शांत कछुए ने खरगोश को दौड़ की चुनौती दी। सभी जानवर देखने इकट्ठे हुए!",
        emoji: "🏁"
      },
      {
        pageNumber: 3,
        textEn: "The race began! The hare dashed ahead in seconds, leaving the tortoise far behind.",
        textHi: "दौड़ शुरू हुई! खरगोश पलक झपकते ही बहुत आगे निकल गया।",
        emoji: "💨"
      },
      {
        pageNumber: 4,
        textEn: "Feeling overly confident, the hare decided to take a short nap under a shady tree.",
        textHi: "घमंड में आकर खरगोश ने सोचा, 'कछुआ तो बहुत दूर है, थोड़ी देर सो लेता हूँ।' वह सो गया।",
        emoji: "🌳"
      },
      {
        pageNumber: 5,
        textEn: "The tortoise kept moving step by step without stopping. He crossed the finish line and won!",
        textHi: "कछुआ बिना रुके धीरे-धीरे चलता रहा और जीत की रेखा पार कर विजेता बन गया!",
        emoji: "🏆"
      }
    ]
  },
  {
    id: "honest_woodcutter",
    titleEn: "The Honest Woodcutter",
    titleHi: "ईमानदार लकड़हारा",
    summaryEn: "An honest poor woodcutter is rewarded for his truthfulness by a kind river fairy.",
    summaryHi: "एक गरीब लकड़हारे को उसकी ईमानदारी के लिए जल देवता से पुरस्कार मिलता है।",
    moralEn: "Honesty is always the best policy!",
    moralHi: "ईमानदारी ही सबसे अच्छी नीति है!",
    coverEmoji: "🪓",
    themeColor: "from-cyan-400 to-blue-500",
    pages: [
      {
        pageNumber: 1,
        textEn: "A poor woodcutter was chopping wood near a river when his iron axe slipped and fell in.",
        textHi: "एक गरीब लकड़हारे की लोहे की कुल्हाड़ी फिसल कर गहरी नदी में गिर गई।",
        emoji: "🌊"
      },
      {
        pageNumber: 2,
        textEn: "He sat weeping by the bank when a kind water fairy appeared to help him.",
        textHi: "वह किनारे बैठकर रोने लगा तभी एक दयालु जल देवता प्रकट हुए।",
        emoji: "🧚"
      },
      {
        pageNumber: 3,
        textEn: "The fairy brought out a glittering golden axe. The woodcutter said, 'No, that is not mine.'",
        textHi: "देवता ने सोने की कुल्हाड़ी निकाली। लकड़हारे ने कहा, 'नहीं, यह मेरी नहीं है।'",
        emoji: "✨"
      },
      {
        pageNumber: 4,
        textEn: "The fairy then brought a silver axe, but he refused again. Finally, the fairy brought his iron axe.",
        textHi: "देवता ने चांदी की कुल्हाड़ी निकाली, उसने फिर मना किया। अंत में लोहे की कुल्हाड़ी निकाली।",
        emoji: "🪓"
      },
      {
        pageNumber: 5,
        textEn: "'Yes! That is mine!' he smiled. Touched by his honesty, the fairy gifted him all three axes!",
        textHi: "उसकी ईमानदारी से खुश होकर देवता ने उसे सोने और चांदी की कुल्हाड़ियां भी उपहार में दे दीं!",
        emoji: "🎁"
      }
    ]
  }
];
