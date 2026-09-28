import React, { useState } from 'react';
import { ALPHABET_DATA, AlphabetItem } from '../data/alphabetData';
import { speechService } from '../utils/speech';
import { Volume2, ChevronLeft, ChevronRight, X, Sparkles } from 'lucide-react';
import confetti from 'canvas-confetti';

interface AlphabetSectionProps {
  onEarnStar: () => void;
}

export const AlphabetSection: React.FC<AlphabetSectionProps> = ({ onEarnStar }) => {
  const [selectedLetter, setSelectedLetter] = useState<AlphabetItem | null>(null);
  const [learnedLetters, setLearnedLetters] = useState<Set<string>>(new Set(['A', 'B']));

  const handleCardClick = (item: AlphabetItem) => {
    speechService.playPop();
    setSelectedLetter(item);
    speechService.speak(`${item.display}. ${item.word}. ${item.phonetic}.`);

    if (!learnedLetters.has(item.letter)) {
      setLearnedLetters(prev => new Set(prev).add(item.letter));
      onEarnStar();
      confetti({
        particleCount: 40,
        spread: 60,
        origin: { y: 0.7 }
      });
    }
  };

  const handleNext = () => {
    if (!selectedLetter) return;
    const currentIndex = ALPHABET_DATA.findIndex(item => item.letter === selectedLetter.letter);
    const nextIndex = (currentIndex + 1) % ALPHABET_DATA.length;
    const nextItem = ALPHABET_DATA[nextIndex];
    setSelectedLetter(nextItem);
    speechService.speak(`${nextItem.display}. ${nextItem.word}.`);
  };

  const handlePrev = () => {
    if (!selectedLetter) return;
    const currentIndex = ALPHABET_DATA.findIndex(item => item.letter === selectedLetter.letter);
    const prevIndex = (currentIndex - 1 + ALPHABET_DATA.length) % ALPHABET_DATA.length;
    const prevItem = ALPHABET_DATA[prevIndex];
    setSelectedLetter(prevItem);
    speechService.speak(`${prevItem.display}. ${prevItem.word}.`);
  };

  return (
    <div className="space-y-6">
      {/* Section Header Card */}
      <div className="bg-gradient-to-r from-blue-500 via-indigo-500 to-purple-500 rounded-3xl p-6 text-white shadow-lg shadow-blue-200">
        <div className="flex items-center gap-3">
          <span className="text-4xl animate-bounce-slow">🔤</span>
          <div>
            <h2 className="text-2xl md:text-3xl font-black">English Alphabet (A to Z)</h2>
            <p className="text-blue-100 text-sm md:text-base font-medium mt-1">
              Tap any letter card to hear phonics, see the object, and learn spelling!
            </p>
          </div>
        </div>
      </div>

      {/* Grid of A-Z Cards */}
      <div className="grid grid-cols-2 sm:grid-cols-3 md:grid-cols-4 xl:grid-cols-6 gap-3.5">
        {ALPHABET_DATA.map((item) => {
          const isLearned = learnedLetters.has(item.letter);
          return (
            <button
              key={item.letter}
              onClick={() => handleCardClick(item)}
              className={`${item.bgColor} border-2 ${item.borderColor} rounded-3xl p-4 flex flex-col items-center justify-between min-h-[145px] transition-all transform hover:-translate-y-1 hover:shadow-lg active:scale-95 text-center relative group`}
            >
              {/* Star Badge */}
              {isLearned && (
                <div className="absolute top-2.5 right-2.5 text-amber-500">
                  <Sparkles className="w-4 h-4 fill-amber-400" />
                </div>
              )}

              <div className="text-3xl sm:text-4xl font-black tracking-tight mt-1 text-slate-800">
                {item.display}
              </div>

              <div className="text-4xl my-1 transform group-hover:scale-115 transition-transform duration-200">
                {item.emoji}
              </div>

              <div className="font-bold text-sm text-slate-700">
                {item.word}
              </div>

              <div className="mt-1 flex items-center gap-1 text-[11px] font-semibold text-slate-500">
                <Volume2 className="w-3.5 h-3.5" />
                <span>Listen</span>
              </div>
            </button>
          );
        })}
      </div>

      {/* Detail Modal */}
      {selectedLetter && (
        <div className="fixed inset-0 z-50 flex items-center justify-center p-4 bg-slate-900/60 backdrop-blur-sm animate-pop">
          <div className="bg-white rounded-3xl shadow-2xl max-w-md w-full overflow-hidden border-4 border-amber-200 relative">
            {/* Top Bar with Navigation Arrows & Close */}
            <div className="flex items-center justify-between p-4 border-b border-slate-100 bg-amber-50/50">
              <button
                onClick={handlePrev}
                className="w-10 h-10 rounded-full bg-white shadow-sm flex items-center justify-center text-slate-700 hover:bg-slate-100"
                title="Previous Letter"
              >
                <ChevronLeft className="w-6 h-6" />
              </button>

              <span className="font-extrabold text-slate-700 text-lg">
                Letter {selectedLetter.letter}
              </span>

              <div className="flex items-center gap-2">
                <button
                  onClick={handleNext}
                  className="w-10 h-10 rounded-full bg-white shadow-sm flex items-center justify-center text-slate-700 hover:bg-slate-100"
                  title="Next Letter"
                >
                  <ChevronRight className="w-6 h-6" />
                </button>
                <button
                  onClick={() => setSelectedLetter(null)}
                  className="w-10 h-10 rounded-full bg-slate-200/80 hover:bg-slate-300 flex items-center justify-center text-slate-700"
                >
                  <X className="w-5 h-5" />
                </button>
              </div>
            </div>

            {/* Modal Body */}
            <div className="p-6 text-center space-y-4">
              {/* Giant Animated Badge */}
              <div className={`w-36 h-36 mx-auto rounded-3xl ${selectedLetter.bgColor} border-4 ${selectedLetter.borderColor} flex flex-col items-center justify-center shadow-inner`}>
                <span className="text-5xl font-black text-slate-800">
                  {selectedLetter.display}
                </span>
                <span className="text-5xl mt-1 animate-bounce-slow">
                  {selectedLetter.emoji}
                </span>
              </div>

              {/* Word & Spelling */}
              <div>
                <h3 className="text-3xl font-black text-slate-800">
                  {selectedLetter.letter} for {selectedLetter.word}
                </h3>
                <div className="flex justify-center items-center gap-1.5 mt-2">
                  {selectedLetter.word.toUpperCase().split('').map((char, idx) => (
                    <span
                      key={idx}
                      className="px-2.5 py-1 bg-amber-100 border border-amber-300 text-amber-900 rounded-lg font-black text-sm shadow-sm"
                    >
                      {char}
                    </span>
                  ))}
                </div>
              </div>

              {/* Phonics & Sentence Banner */}
              <div className="bg-slate-50 border border-slate-200 rounded-2xl p-4 text-slate-700 space-y-1.5">
                <p className="font-bold text-indigo-600 text-base">
                  {selectedLetter.phonetic}
                </p>
                <p className="text-sm font-medium italic">
                  "{selectedLetter.sentence}"
                </p>
                <p className="text-xs text-slate-500 pt-1 border-t border-slate-200/60">
                  💡 Fun Fact: {selectedLetter.funFact}
                </p>
              </div>

              {/* Big Audio Buttons */}
              <div className="grid grid-cols-2 gap-3 pt-2">
                <button
                  onClick={() => {
                    speechService.speak(`${selectedLetter.phonetic}.`);
                  }}
                  className="w-full py-3.5 px-4 bg-indigo-600 hover:bg-indigo-700 text-white rounded-2xl font-bold flex items-center justify-center gap-2 shadow-md shadow-indigo-200 active:scale-95 transition-all"
                >
                  <Volume2 className="w-5 h-5" />
                  <span>Phonics</span>
                </button>

                <button
                  onClick={() => {
                    speechService.speak(`${selectedLetter.letter} for ${selectedLetter.word}! ${selectedLetter.sentence}`);
                  }}
                  className="w-full py-3.5 px-4 bg-rose-500 hover:bg-rose-600 text-white rounded-2xl font-bold flex items-center justify-center gap-2 shadow-md shadow-rose-200 active:scale-95 transition-all"
                >
                  <Volume2 className="w-5 h-5" />
                  <span>Speak Word</span>
                </button>
              </div>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};
