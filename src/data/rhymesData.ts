export interface RhymeItem {
  id: string;
  title: string;
  subtitle: string;
  isHindi: boolean;
  emoji: string;
  colorClass: string;
  badgeClass: string;
  lines: string[];
}

export const RHYMES_DATA: RhymeItem[] = [
  {
    id: "twinkle_twinkle",
    title: "Twinkle Twinkle Little Star",
    subtitle: "Classic Lullaby Rhyme",
    isHindi: false,
    emoji: "⭐",
    colorClass: "bg-amber-50 border-amber-200 text-amber-900",
    badgeClass: "bg-amber-100 text-amber-800",
    lines: [
      "Twinkle, twinkle, little star,",
      "How I wonder what you are!",
      "Up above the world so high,",
      "Like a diamond in the sky.",
      "When the blazing sun is gone,",
      "When he nothing shines upon,",
      "Then you show your little light,",
      "Twinkle, twinkle, all the night!"
    ]
  },
  {
    id: "titli_udi",
    title: "तितली उड़ी बस पर चढ़ी",
    subtitle: "मजेदार हिंदी बालगीत",
    isHindi: true,
    emoji: "🦋",
    colorClass: "bg-pink-50 border-pink-200 text-pink-900",
    badgeClass: "bg-pink-100 text-pink-800",
    lines: [
      "तितली उड़ी, बस पर चढ़ी,",
      "सीट ना मिली तो रोने लगी!",
      "ड्राइवर ने बोला, आजा मेरे पास,",
      "तितली बोली, ना बाबा ना!",
      "मेरा घर है पास!"
    ]
  },
  {
    id: "johny_johny",
    title: "Johny Johny Yes Papa",
    subtitle: "Playful Nursery Rhyme",
    isHindi: false,
    emoji: "🍬",
    colorClass: "bg-teal-50 border-teal-200 text-teal-900",
    badgeClass: "bg-teal-100 text-teal-800",
    lines: [
      "Johny, Johny, Yes, Papa?",
      "Eating sugar? No, Papa!",
      "Telling lies? No, Papa!",
      "Open your mouth, Ha! Ha! Ha!"
    ]
  },
  {
    id: "chanda_mama",
    title: "चंदा मामा दूर के",
    subtitle: "मीठी हिंदी लोरी",
    isHindi: true,
    emoji: "🌙",
    colorClass: "bg-indigo-50 border-indigo-200 text-indigo-900",
    badgeClass: "bg-indigo-100 text-indigo-800",
    lines: [
      "चंदा मामा दूर के,",
      "पुए पकाएं बूर के!",
      "आप खाएं थाली में,",
      "मुन्ने को दें प्याली में!",
      "प्याली गई टूट, मुन्ना गया रूठ,",
      "लाएंगे नई प्यालियां, बजा बजा के तालियां!"
    ]
  },
  {
    id: "baa_baa_sheep",
    title: "Baa Baa Black Sheep",
    subtitle: "Cheerful Farm Rhyme",
    isHindi: false,
    emoji: "🐑",
    colorClass: "bg-sky-50 border-sky-200 text-sky-900",
    badgeClass: "bg-sky-100 text-sky-800",
    lines: [
      "Baa, baa, black sheep,",
      "Have you any wool?",
      "Yes, sir, yes, sir,",
      "Three bags full!",
      "One for the master,",
      "And one for the dame,",
      "And one for the little boy",
      "Who lives down the lane!"
    ]
  },
  {
    id: "machhli_jal",
    title: "मछली जल की रानी है",
    subtitle: "प्यारा जलीय बालगीत",
    isHindi: true,
    emoji: "🐟",
    colorClass: "bg-cyan-50 border-cyan-200 text-cyan-900",
    badgeClass: "bg-cyan-100 text-cyan-800",
    lines: [
      "मछली जल की रानी है,",
      "जीवन उसका पानी है!",
      "हाथ लगाओ तो डर जाएगी,",
      "बाहर निकालो तो मर जाएगी!",
      "पानी में डालो तो तैर जाएगी!"
    ]
  },
  {
    id: "humpty_dumpty",
    title: "Humpty Dumpty",
    subtitle: "Classic Nursery Rhyme",
    isHindi: false,
    emoji: "🥚",
    colorClass: "bg-orange-50 border-orange-200 text-orange-900",
    badgeClass: "bg-orange-100 text-orange-800",
    lines: [
      "Humpty Dumpty sat on a wall,",
      "Humpty Dumpty had a great fall!",
      "All the king's horses and all the king's men,",
      "Couldn't put Humpty together again!"
    ]
  },
  {
    id: "bandar_mama",
    title: "बंदर मामा पहन पजामा",
    subtitle: "मजेदार बंदर बालगीत",
    isHindi: true,
    emoji: "🐒",
    colorClass: "bg-emerald-50 border-emerald-200 text-emerald-900",
    badgeClass: "bg-emerald-100 text-emerald-800",
    lines: [
      "बंदर मामा पहन पजामा,",
      "दावत खाने आए!",
      "ढीला कुरता, टोपी जूता,",
      "पहने बहुत इतराए!",
      "रसगुल्ले पर जी ललचाया,",
      "मुंह में रखा गरम-गरम,",
      "जीभ जल गई लप-लप-लप,",
      "मामा भागे दम दबाकर!"
    ]
  }
];
