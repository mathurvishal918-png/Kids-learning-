export interface HindiItem {
  letter: string;
  roman: string;
  wordHindi: string;
  wordEnglish: string;
  meaning: string;
  emoji: string;
  spokenText: string;
  colorClass: string;
}

export const SWAR_DATA: HindiItem[] = [
  { letter: "अ", roman: "a", wordHindi: "अनार", wordEnglish: "Anaar", meaning: "Pomegranate", emoji: "🍎", spokenText: "अ से अनार", colorClass: "bg-rose-50 border-rose-200 text-rose-600" },
  { letter: "आ", roman: "aa", wordHindi: "आम", wordEnglish: "Aam", meaning: "Mango", emoji: "🥭", spokenText: "आ से आम", colorClass: "bg-amber-50 border-amber-200 text-amber-600" },
  { letter: "इ", roman: "i", wordHindi: "इमली", wordEnglish: "Imli", meaning: "Tamarind", emoji: "🫘", spokenText: "इ से इमली", colorClass: "bg-lime-50 border-lime-200 text-lime-600" },
  { letter: "ई", roman: "ee", wordHindi: "ईख", wordEnglish: "Eekh", meaning: "Sugarcane", emoji: "🎋", spokenText: "ई से ईख", colorClass: "bg-emerald-50 border-emerald-200 text-emerald-600" },
  { letter: "उ", roman: "u", wordHindi: "उल्लू", wordEnglish: "Ullu", meaning: "Owl", emoji: "🦉", spokenText: "उ से उल्लू", colorClass: "bg-cyan-50 border-cyan-200 text-cyan-600" },
  { letter: "ऊ", roman: "oo", wordHindi: "ऊन", wordEnglish: "Oon", meaning: "Wool", emoji: "🧶", spokenText: "ऊ से ऊन", colorClass: "bg-indigo-50 border-indigo-200 text-indigo-600" },
  { letter: "ऋ", roman: "ri", wordHindi: "ऋषि", wordEnglish: "Rishi", meaning: "Sage", emoji: "🧘", spokenText: "ऋ से ऋषि", colorClass: "bg-purple-50 border-purple-200 text-purple-600" },
  { letter: "ए", roman: "e", wordHindi: "एड़ी", wordEnglish: "Edi", meaning: "Heel", emoji: "🦶", spokenText: "ए से एड़ी", colorClass: "bg-pink-50 border-pink-200 text-pink-600" },
  { letter: "ऐ", roman: "ai", wordHindi: "ऐनक", wordEnglish: "Ainak", meaning: "Spectacles", emoji: "👓", spokenText: "ऐ से ऐनक", colorClass: "bg-yellow-50 border-yellow-200 text-yellow-600" },
  { letter: "ओ", roman: "o", wordHindi: "ओखली", wordEnglish: "Okhli", meaning: "Mortar", emoji: "🥣", spokenText: "ओ से ओखली", colorClass: "bg-orange-50 border-orange-200 text-orange-600" },
  { letter: "औ", roman: "au", wordHindi: "औरत", wordEnglish: "Aurat", meaning: "Woman", emoji: "👩", spokenText: "औ से औरत", colorClass: "bg-teal-50 border-teal-200 text-teal-600" },
  { letter: "अं", roman: "am", wordHindi: "अंगूर", wordEnglish: "Angoor", meaning: "Grapes", emoji: "🍇", spokenText: "अं से अंगूर", colorClass: "bg-violet-50 border-violet-200 text-violet-600" },
  { letter: "अः", roman: "ah", wordHindi: "खाली", wordEnglish: "Khaali", meaning: "Aha! Smile", emoji: "✨", spokenText: "अः से खाली", colorClass: "bg-blue-50 border-blue-200 text-blue-600" }
];

export const VYANJAN_DATA: HindiItem[] = [
  { letter: "क", roman: "ka", wordHindi: "कबूतर", wordEnglish: "Kabutar", meaning: "Pigeon", emoji: "🕊️", spokenText: "क से कबूतर", colorClass: "bg-blue-50 border-blue-200 text-blue-600" },
  { letter: "ख", roman: "kha", wordHindi: "खरगोश", wordEnglish: "Khargosh", meaning: "Rabbit", emoji: "🐇", spokenText: "ख से खरगोश", colorClass: "bg-pink-50 border-pink-200 text-pink-600" },
  { letter: "ग", roman: "ga", wordHindi: "गमला", wordEnglish: "Gamla", meaning: "Flower Pot", emoji: "🪴", spokenText: "ग से गमला", colorClass: "bg-emerald-50 border-emerald-200 text-emerald-600" },
  { letter: "घ", roman: "gha", wordHindi: "घड़ी", wordEnglish: "Ghadi", meaning: "Watch / Clock", emoji: "⏰", spokenText: "घ से घड़ी", colorClass: "bg-amber-50 border-amber-200 text-amber-600" },
  { letter: "ङ", roman: "nga", wordHindi: "खाली", wordEnglish: "Khaali", meaning: "Empty sound", emoji: "💫", spokenText: "ङ से खाली", colorClass: "bg-yellow-50 border-yellow-200 text-yellow-600" },
  { letter: "च", roman: "cha", wordHindi: "चम्मच", wordEnglish: "Chammach", meaning: "Spoon", emoji: "🥄", spokenText: "च से चम्मच", colorClass: "bg-teal-50 border-teal-200 text-teal-600" },
  { letter: "छ", roman: "chha", wordHindi: "छतरी", wordEnglish: "Chhatri", meaning: "Umbrella", emoji: "☂️", spokenText: "छ से छतरी", colorClass: "bg-purple-50 border-purple-200 text-purple-600" },
  { letter: "ज", roman: "ja", wordHindi: "जहाज", wordEnglish: "Jahaaz", meaning: "Ship", emoji: "🚢", spokenText: "ज से जहाज", colorClass: "bg-sky-50 border-sky-200 text-sky-600" },
  { letter: "झ", roman: "jha", wordHindi: "झंडा", wordEnglish: "Jhanda", meaning: "Flag", emoji: "🇮🇳", spokenText: "झ से झंडा", colorClass: "bg-orange-50 border-orange-200 text-orange-600" },
  { letter: "ञ", roman: "nya", wordHindi: "खाली", wordEnglish: "Khaali", meaning: "Empty sound", emoji: "🌟", spokenText: "ञ से खाली", colorClass: "bg-amber-50 border-amber-200 text-amber-600" },
  { letter: "ट", roman: "ta", wordHindi: "टमाटर", wordEnglish: "Tamatar", meaning: "Tomato", emoji: "🍅", spokenText: "ट से टमाटर", colorClass: "bg-rose-50 border-rose-200 text-rose-600" },
  { letter: "ठ", roman: "tha", wordHindi: "ठठेरा", wordEnglish: "Thathera", meaning: "Coppersmith", emoji: "🔨", spokenText: "ठ से ठठेरा", colorClass: "bg-orange-50 border-orange-200 text-orange-600" },
  { letter: "ड", roman: "da", wordHindi: "डमरू", wordEnglish: "Damru", meaning: "Drum", emoji: "🪘", spokenText: "ड से डमरू", colorClass: "bg-lime-50 border-lime-200 text-lime-600" },
  { letter: "ढ", roman: "dha", wordHindi: "ढक्कन", wordEnglish: "Dhakkan", meaning: "Lid", emoji: "🍲", spokenText: "ढ से ढक्कन", colorClass: "bg-emerald-50 border-emerald-200 text-emerald-600" },
  { letter: "ण", roman: "nna", wordHindi: "खाली", wordEnglish: "Khaali", meaning: "Empty sound", emoji: "✨", spokenText: "ण से खाली", colorClass: "bg-yellow-50 border-yellow-200 text-yellow-600" },
  { letter: "त", roman: "ta", wordHindi: "तरबूज", wordEnglish: "Tarbooz", meaning: "Watermelon", emoji: "🍉", spokenText: "त से तरबूज", colorClass: "bg-red-50 border-red-200 text-red-600" },
  { letter: "थ", roman: "tha", wordHindi: "थर्मस", wordEnglish: "Thermos", meaning: "Flask", emoji: "🧴", spokenText: "थ से थर्मस", colorClass: "bg-cyan-50 border-cyan-200 text-cyan-600" },
  { letter: "द", roman: "da", wordHindi: "दवात", wordEnglish: "Dawaat", meaning: "Inkpot", emoji: "🖋️", spokenText: "द से दवात", colorClass: "bg-purple-50 border-purple-200 text-purple-600" },
  { letter: "ध", roman: "dha", wordHindi: "धनुष", wordEnglish: "Dhanush", meaning: "Bow", emoji: "🏹", spokenText: "ध से धनुष", colorClass: "bg-orange-50 border-orange-200 text-orange-600" },
  { letter: "न", roman: "na", wordHindi: "नल", wordEnglish: "Nal", meaning: "Water Tap", emoji: "🚰", spokenText: "न से नल", colorClass: "bg-blue-50 border-blue-200 text-blue-600" },
  { letter: "प", roman: "pa", wordHindi: "पतंग", wordEnglish: "Patang", meaning: "Kite", emoji: "🪁", spokenText: "प से पतंग", colorClass: "bg-yellow-50 border-yellow-200 text-yellow-600" },
  { letter: "फ", roman: "pha", wordHindi: "फल", wordEnglish: "Phal", meaning: "Fruits", emoji: "🍎", spokenText: "फ से फल", colorClass: "bg-rose-50 border-rose-200 text-rose-600" },
  { letter: "ब", roman: "ba", wordHindi: "बत्तख", wordEnglish: "Battakh", meaning: "Duck", emoji: "🦆", spokenText: "ब से बत्तख", colorClass: "bg-amber-50 border-amber-200 text-amber-600" },
  { letter: "भ", roman: "bha", wordHindi: "भालू", wordEnglish: "Bhaalu", meaning: "Bear", emoji: "🐻", spokenText: "भ से भालू", colorClass: "bg-orange-50 border-orange-200 text-orange-600" },
  { letter: "म", roman: "ma", wordHindi: "मछली", wordEnglish: "Machhli", meaning: "Fish", emoji: "🐟", spokenText: "म से मछली", colorClass: "bg-teal-50 border-teal-200 text-teal-600" },
  { letter: "य", roman: "ya", wordHindi: "यज्ञ", wordEnglish: "Yagya", meaning: "Sacred Fire", emoji: "🔥", spokenText: "य से यज्ञ", colorClass: "bg-red-50 border-red-200 text-red-600" },
  { letter: "र", roman: "ra", wordHindi: "रथ", wordEnglish: "Rath", meaning: "Chariot", emoji: "🛞", spokenText: "र से रथ", colorClass: "bg-amber-50 border-amber-200 text-amber-600" },
  { letter: "ल", roman: "la", wordHindi: "लट्टू", wordEnglish: "Lattoo", meaning: "Spinning Top", emoji: "🪀", spokenText: "ल से लट्टू", colorClass: "bg-lime-50 border-lime-200 text-lime-600" },
  { letter: "व", roman: "va", wordHindi: "वक", wordEnglish: "Vak", meaning: "Crane / Heron", emoji: "🦩", spokenText: "व से वक", colorClass: "bg-pink-50 border-pink-200 text-pink-600" },
  { letter: "श", roman: "sha", wordHindi: "शलजम", wordEnglish: "Shaljam", meaning: "Turnip", emoji: "🥕", spokenText: "श से शलजम", colorClass: "bg-purple-50 border-purple-200 text-purple-600" },
  { letter: "ष", roman: "ssa", wordHindi: "षटकोण", wordEnglish: "Shatkon", meaning: "Hexagon", emoji: "⬡", spokenText: "ष से षटकोण", colorClass: "bg-cyan-50 border-cyan-200 text-cyan-600" },
  { letter: "स", roman: "sa", wordHindi: "सेब", wordEnglish: "Seb", meaning: "Apple", emoji: "🍏", spokenText: "स से सेब", colorClass: "bg-emerald-50 border-emerald-200 text-emerald-600" },
  { letter: "ह", roman: "ha", wordHindi: "हाथी", wordEnglish: "Haathi", meaning: "Elephant", emoji: "🐘", spokenText: "ह से हाथी", colorClass: "bg-blue-50 border-blue-200 text-blue-600" },
  { letter: "क्ष", roman: "ksha", wordHindi: "क्षत्रिय", wordEnglish: "Kshatriya", meaning: "Warrior", emoji: "🛡️", spokenText: "क्ष से क्षत्रिय", colorClass: "bg-orange-50 border-orange-200 text-orange-600" },
  { letter: "त्र", roman: "tra", wordHindi: "त्रिशूल", wordEnglish: "Trishool", meaning: "Trident", emoji: "🔱", spokenText: "त्र से त्रिशूल", colorClass: "bg-yellow-50 border-yellow-200 text-yellow-600" },
  { letter: "ज्ञ", roman: "gya", wordHindi: "ज्ञानी", wordEnglish: "Gyaani", meaning: "Wise Scholar", emoji: "📚", spokenText: "ज्ञ से ज्ञानी", colorClass: "bg-violet-50 border-violet-200 text-violet-600" }
];
