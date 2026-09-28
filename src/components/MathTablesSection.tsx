import React, { useState } from 'react';
import { MATH_TABLES, MathTableItem, TableRow } from '../data/tablesData';
import { speechService } from '../utils/speech';
import { Volume2, Play, Square, Award, CheckCircle2, HelpCircle, X } from 'lucide-react';
import confetti from 'canvas-confetti';

interface MathTablesSectionProps {
  onEarnStar: () => void;
}

export const MathTablesSection: React.FC<MathTablesSectionProps> = ({ onEarnStar }) => {
  const [selectedTableNumber, setSelectedTableNumber] = useState<number>(2);
  const [isReadingAll, setIsReadingAll] = useState<boolean>(false);
  const [activeRowIndex, setActiveRowIndex] = useState<number | null>(null);

  // Quiz Modal State
  const [isQuizOpen, setIsQuizOpen] = useState<boolean>(false);
  const [quizIndex, setQuizIndex] = useState<number>(0);
  const [quizFeedback, setQuizFeedback] = useState<string | null>(null);

  const currentTable: MathTableItem = MATH_TABLES.find(t => t.tableNumber === selectedTableNumber) || MATH_TABLES[1];

  const handleSelectTable = (tableNum: number) => {
    speechService.playPop();
    speechService.stop();
    setIsReadingAll(false);
    setActiveRowIndex(null);
    setSelectedTableNumber(tableNum);
    speechService.speak(`Table of ${tableNum}!`);
  };

  const handleSpeakRow = (row: TableRow, index: number) => {
    speechService.playPop();
    setActiveRowIndex(index);
    speechService.speak(row.spokenEnglish, false, () => {
      setActiveRowIndex(null);
    });
  };

  const handleReadFullTable = () => {
    if (isReadingAll) {
      speechService.stop();
      setIsReadingAll(false);
      setActiveRowIndex(null);
      return;
    }

    setIsReadingAll(true);
    let curr = 0;

    const readNext = () => {
      if (curr >= currentTable.rows.length) {
        setIsReadingAll(false);
        setActiveRowIndex(null);
        onEarnStar();
        confetti({
          particleCount: 50,
          spread: 70,
          origin: { y: 0.6 }
        });
        return;
      }
      setActiveRowIndex(curr);
      speechService.speak(currentTable.rows[curr].spokenEnglish, false, () => {
        curr++;
        setTimeout(readNext, 300);
      });
    };

    readNext();
  };

  const handleAnswerQuiz = (choice: number) => {
    const currentQ = currentTable.quiz[quizIndex];
    if (!currentQ) return;

    if (choice === currentQ.answer) {
      speechService.playChime();
      setQuizFeedback("Awesome! Correct Answer! ⭐");
      speechService.speak("Awesome! Correct answer!");
      onEarnStar();
      confetti({
        particleCount: 40,
        spread: 60,
        origin: { y: 0.6 }
      });

      setTimeout(() => {
        if (quizIndex + 1 < currentTable.quiz.length) {
          setQuizIndex(quizIndex + 1);
          setQuizFeedback(null);
        } else {
          setQuizFeedback("Great job! You completed all questions! 🎉");
        }
      }, 1500);
    } else {
      speechService.playPop();
      setQuizFeedback("Almost there! Try once more! 💪");
      speechService.speak("Try again!");
    }
  };

  return (
    <div className="space-y-6">
      {/* Header Banner */}
      <div className="bg-gradient-to-r from-amber-500 via-orange-500 to-rose-500 rounded-3xl p-6 text-white shadow-lg shadow-orange-200">
        <div className="flex items-center gap-3">
          <span className="text-4xl animate-bounce-slow">✖️</span>
          <div>
            <h2 className="text-2xl md:text-3xl font-black">Math Tables (1 to 10)</h2>
            <p className="text-orange-100 text-sm md:text-base font-medium mt-1">
              Select any table, tap lines to listen, or challenge yourself with the fun Quiz!
            </p>
          </div>
        </div>
      </div>

      {/* Grid Buttons for Tables 1 to 10 */}
      <div className="bg-white rounded-3xl p-4 shadow-sm border border-slate-200">
        <p className="text-xs font-bold text-slate-400 uppercase tracking-wider mb-3 px-1">
          Select Multiplication Table:
        </p>
        <div className="grid grid-cols-5 sm:grid-cols-10 gap-2">
          {MATH_TABLES.map((t) => {
            const isSelected = t.tableNumber === selectedTableNumber;
            return (
              <button
                key={t.tableNumber}
                onClick={() => handleSelectTable(t.tableNumber)}
                className={`py-3 rounded-2xl flex flex-col items-center justify-center font-black transition-all ${
                  isSelected
                    ? 'bg-amber-500 text-white shadow-md shadow-amber-200 scale-105'
                    : 'bg-slate-50 text-slate-700 hover:bg-amber-50 hover:text-amber-700 border border-slate-200'
                }`}
              >
                <span className="text-xs text-slate-400 font-semibold">Table</span>
                <span className="text-xl md:text-2xl mt-0.5">{t.tableNumber}</span>
              </button>
            );
          })}
        </div>
      </div>

      {/* Action Controls for Current Table */}
      <div className="flex flex-wrap items-center justify-between gap-3 bg-amber-50/70 border border-amber-200 rounded-2xl p-4">
        <div>
          <h3 className="text-xl font-black text-slate-800">
            {currentTable.title}
          </h3>
          <p className="text-xs text-slate-500 font-medium">
            Tap any row below to hear it aloud!
          </p>
        </div>

        <div className="flex items-center gap-2.5">
          <button
            onClick={handleReadFullTable}
            className={`px-4 py-2.5 rounded-xl font-bold flex items-center gap-2 shadow-sm transition-all ${
              isReadingAll
                ? 'bg-rose-500 text-white hover:bg-rose-600 animate-pulse'
                : 'bg-amber-500 hover:bg-amber-600 text-white shadow-amber-200'
            }`}
          >
            {isReadingAll ? <Square className="w-4 h-4 fill-white" /> : <Play className="w-4 h-4 fill-white" />}
            <span>{isReadingAll ? "Stop Reading" : "Read Full Table"}</span>
          </button>

          <button
            onClick={() => {
              speechService.playPop();
              setIsQuizOpen(true);
              setQuizIndex(0);
              setQuizFeedback(null);
            }}
            className="px-4 py-2.5 rounded-xl font-bold bg-indigo-600 hover:bg-indigo-700 text-white flex items-center gap-2 shadow-sm shadow-indigo-200"
          >
            <Award className="w-4 h-4" />
            <span>Practice Quiz</span>
          </button>
        </div>
      </div>

      {/* Full Multiplication Chart */}
      <div className="grid grid-cols-1 sm:grid-cols-2 gap-3">
        {currentTable.rows.map((row, idx) => {
          const isActive = activeRowIndex === idx;
          return (
            <button
              key={idx}
              onClick={() => handleSpeakRow(row, idx)}
              className={`p-4 rounded-2xl border-2 flex items-center justify-between transition-all transform hover:-translate-y-0.5 text-left ${
                isActive
                  ? 'bg-amber-100 border-amber-400 shadow-md scale-102'
                  : 'bg-white border-slate-200 hover:border-amber-300 hover:bg-amber-50/40'
              }`}
            >
              <div className="flex items-center gap-4">
                <span className="w-9 h-9 rounded-xl bg-amber-100 text-amber-900 font-black flex items-center justify-center text-sm">
                  {row.multiplier}
                </span>
                <span className="text-lg md:text-xl font-extrabold text-slate-800 tracking-wide">
                  {row.multiplicand} × {row.multiplier} = <span className="text-amber-600 text-2xl font-black">{row.result}</span>
                </span>
              </div>

              <div className="w-8 h-8 rounded-full bg-slate-100 flex items-center justify-center text-slate-500 hover:bg-amber-100 hover:text-amber-800">
                <Volume2 className="w-4 h-4" />
              </div>
            </button>
          );
        })}
      </div>

      {/* Interactive Table Quiz Modal */}
      {isQuizOpen && (
        <div className="fixed inset-0 z-50 flex items-center justify-center p-4 bg-slate-900/60 backdrop-blur-sm animate-pop">
          <div className="bg-white rounded-3xl shadow-2xl max-w-md w-full overflow-hidden border-4 border-amber-300 relative">
            <div className="flex items-center justify-between p-4 border-b border-slate-100 bg-amber-50">
              <div className="flex items-center gap-2 text-amber-900 font-black">
                <HelpCircle className="w-5 h-5 text-amber-600" />
                <span>Quiz: Table of {currentTable.tableNumber}</span>
              </div>
              <button
                onClick={() => setIsQuizOpen(false)}
                className="w-9 h-9 rounded-full bg-white shadow-sm flex items-center justify-center text-slate-600 hover:bg-slate-100"
              >
                <X className="w-5 h-5" />
              </button>
            </div>

            <div className="p-6 text-center space-y-5">
              {quizIndex < currentTable.quiz.length ? (
                <>
                  <div className="text-xs font-bold text-slate-400">
                    Question {quizIndex + 1} of {currentTable.quiz.length}
                  </div>

                  <div className="py-4 px-6 bg-amber-100 border-2 border-amber-300 rounded-3xl text-3xl font-black text-amber-950 shadow-inner">
                    {currentTable.quiz[quizIndex].question}
                  </div>

                  <div className="grid grid-cols-1 gap-2.5 pt-2">
                    {currentTable.quiz[quizIndex].options.map((opt, i) => (
                      <button
                        key={i}
                        onClick={() => handleAnswerQuiz(opt)}
                        className="py-3.5 px-4 bg-slate-50 hover:bg-amber-100 border-2 border-slate-200 hover:border-amber-300 rounded-2xl text-xl font-black text-slate-800 transition-all active:scale-95"
                      >
                        {opt}
                      </button>
                    ))}
                  </div>

                  {quizFeedback && (
                    <div className="p-3 bg-amber-50 border border-amber-200 rounded-xl text-sm font-bold text-amber-900">
                      {quizFeedback}
                    </div>
                  )}
                </>
              ) : (
                <div className="space-y-4 py-4">
                  <div className="text-6xl animate-bounce-slow">🎉</div>
                  <h4 className="text-2xl font-black text-emerald-600">
                    Super Job!
                  </h4>
                  <p className="text-slate-600 font-medium">
                    You have mastered Table of {currentTable.tableNumber}!
                  </p>
                  <button
                    onClick={() => setIsQuizOpen(false)}
                    className="w-full py-3 bg-emerald-600 hover:bg-emerald-700 text-white rounded-2xl font-bold"
                  >
                    Done
                  </button>
                </div>
              )}
            </div>
          </div>
        </div>
      )}
    </div>
  );
};
