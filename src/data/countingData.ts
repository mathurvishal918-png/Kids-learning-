export interface NumberItem {
  value: number;
  word: string;
  hindiWord: string;
  emoji: string;
  isEven: boolean;
  colorClass: string;
}

const NUMBER_WORDS: Record<number, { en: string; hi: string }> = {
  1: { en: "One", hi: "एक" }, 2: { en: "Two", hi: "दो" }, 3: { en: "Three", hi: "तीन" },
  4: { en: "Four", hi: "चार" }, 5: { en: "Five", hi: "पाँच" }, 6: { en: "Six", hi: "छह" },
  7: { en: "Seven", hi: "सात" }, 8: { en: "Eight", hi: "आठ" }, 9: { en: "Nine", hi: "नौ" },
  10: { en: "Ten", hi: "दस" }, 11: { en: "Eleven", hi: "ग्यारह" }, 12: { en: "Twelve", hi: "बारह" },
  13: { en: "Thirteen", hi: "तेरह" }, 14: { en: "Fourteen", hi: "चौदह" }, 15: { en: "Fifteen", hi: "पंद्रह" },
  16: { en: "Sixteen", hi: "सोलह" }, 17: { en: "Seventeen", hi: "सत्रह" }, 18: { en: "Eighteen", hi: "अठारह" },
  19: { en: "Nineteen", hi: "उन्नीस" }, 20: { en: "Twenty", hi: "बीस" },
  30: { en: "Thirty", hi: "तीस" }, 40: { en: "Forty", hi: "चालीस" }, 50: { en: "Fifty", hi: "पचास" },
  60: { en: "Sixty", hi: "साठ" }, 70: { en: "Seventy", hi: "सत्तर" }, 80: { en: "Eighty", hi: "अस्सी" },
  90: { en: "Ninety", hi: "नब्बे" }, 100: { en: "One Hundred", hi: "एक सौ" }
};

const EMOJIS = ["⭐", "🎈", "🍎", "🍭", "🚗", "🌸", "🦋", "🐬", "🍓", "🧁"];

const COLORS = [
  "bg-rose-50 border-rose-200 text-rose-700",
  "bg-amber-50 border-amber-200 text-amber-700",
  "bg-yellow-50 border-yellow-200 text-yellow-700",
  "bg-emerald-50 border-emerald-200 text-emerald-700",
  "bg-sky-50 border-sky-200 text-sky-700",
  "bg-purple-50 border-purple-200 text-purple-700"
];

function getNumberWord(n: number): string {
  if (NUMBER_WORDS[n]) return NUMBER_WORDS[n].en;
  const tens = Math.floor(n / 10) * 10;
  const units = n % 10;
  return `${NUMBER_WORDS[tens]?.en || ""} ${NUMBER_WORDS[units]?.en || ""}`.trim();
}

function getHindiWord(n: number): string {
  if (NUMBER_WORDS[n]) return NUMBER_WORDS[n].hi;
  return `संख्या ${n}`;
}

export const COUNTING_DATA: NumberItem[] = Array.from({ length: 100 }, (_, i) => {
  const num = i + 1;
  return {
    value: num,
    word: getNumberWord(num),
    hindiWord: getHindiWord(num),
    emoji: EMOJIS[(num - 1) % EMOJIS.length],
    isEven: num % 2 === 0,
    colorClass: COLORS[(num - 1) % COLORS.length]
  };
});

export const NUMBER_GROUPS = [
  { label: "1 - 10", min: 1, max: 10 },
  { label: "11 - 20", min: 11, max: 20 },
  { label: "21 - 30", min: 21, max: 30 },
  { label: "31 - 40", min: 31, max: 40 },
  { label: "41 - 50", min: 41, max: 50 },
  { label: "51 - 60", min: 51, max: 60 },
  { label: "61 - 70", min: 61, max: 70 },
  { label: "71 - 80", min: 71, max: 80 },
  { label: "81 - 90", min: 81, max: 90 },
  { label: "91 - 100", min: 91, max: 100 },
  { label: "All 1-100", min: 1, max: 100 }
];
