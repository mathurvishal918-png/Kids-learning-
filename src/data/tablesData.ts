export interface TableRow {
  multiplicand: number;
  multiplier: number;
  result: number;
  spokenEnglish: string;
  spokenHindi: string;
}

export interface TableQuizQuestion {
  question: string;
  options: number[];
  answer: number;
}

export interface MathTableItem {
  tableNumber: number;
  title: string;
  themeColor: string;
  rows: TableRow[];
  quiz: TableQuizQuestion[];
}

const TABLE_THEMES: Record<number, string> = {
  1: "from-blue-400 to-indigo-500",
  2: "from-emerald-400 to-teal-500",
  3: "from-amber-400 to-orange-500",
  4: "from-rose-400 to-pink-500",
  5: "from-purple-400 to-indigo-500",
  6: "from-cyan-400 to-blue-500",
  7: "from-fuchsia-400 to-pink-500",
  8: "from-lime-400 to-emerald-500",
  9: "from-sky-400 to-cyan-500",
  10: "from-yellow-400 to-amber-500"
};

export const MATH_TABLES: MathTableItem[] = Array.from({ length: 10 }, (_, i) => {
  const table = i + 1;
  const rows: TableRow[] = Array.from({ length: 10 }, (_, j) => {
    const mult = j + 1;
    const res = table * mult;
    return {
      multiplicand: table,
      multiplier: mult,
      result: res,
      spokenEnglish: `${table} times ${mult} is ${res}`,
      spokenHindi: `${table} गुणा ${mult} बराबर ${res}`
    };
  });

  const quiz: TableQuizQuestion[] = [
    {
      question: `${table} × 2 = ?`,
      options: [table * 2, table * 2 + 2, Math.max(1, table * 2 - 2)].sort(() => Math.random() - 0.5),
      answer: table * 2
    },
    {
      question: `${table} × 5 = ?`,
      options: [table * 5, table * 4, table * 6].sort(() => Math.random() - 0.5),
      answer: table * 5
    },
    {
      question: `${table} × 8 = ?`,
      options: [table * 8, table * 7, table * 9].sort(() => Math.random() - 0.5),
      answer: table * 8
    }
  ];

  return {
    tableNumber: table,
    title: `Table of ${table}`,
    themeColor: TABLE_THEMES[table],
    rows,
    quiz
  };
});
