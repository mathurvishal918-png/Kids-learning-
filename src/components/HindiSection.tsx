import React, { useState } from 'react';
import { SWAR_DATA, VYANJAN_DATA, HindiItem } from '../data/hindiData';
import { speechService } from '../utils/speech';
import { Volume2, ChevronLeft, ChevronRight, X, Sparkles } from 'lucide-react';
import confetti from 'canvas-confetti';

interface HindiSectionProps {
  onEarnStar: () => void;
}

export const HindiSection: React.FC<HindiSectionProps> = ({ onEarnStar }) => {
  const [activeTab, setActiveTab] = useState<'swar' | 'vyanjan'>('swar');
  const [selectedLetter, setSelectedLetter] = useState<HindiItem | null>(null);
  const [learnedLetters, setLearnedLetters] = useState<Set<string>>(new Set(['अ', 'आ']));

  const displayedList = activeTab === 'swar' ? SWAR_DATA : VYANJAN_DATA;

  const handleCardClick = (item: HindiItem) => {
    speechService.playPop();
    setSelectedLetter(item);
    speechService.speak(item.spokenText, true);

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
    const currentIndex = displayedList.findIndex(item => item.letter === selectedLetter.letter);
    const nextIndex = (currentIndex + 1) % displayedList.length;
    const nextItem = displayedList[nextIndex];
    setSelectedLetter(nextItem);
    speechService.speak(nextItem.spokenText, true);
  };

  const handlePrev = () => {
    if (!selectedLetter) return;
    const currentIndex = displayedList.findIndex(item => item.letter === selectedLetter.letter);
    const prevIndex = (currentIndex - 1 + displayedList.length) % displayedList.length;
    const prevItem = displayedList[prevIndex];
    setSelectedLetter(prevItem);
    speechService.speak(prevItem.spokenText, true);
  };

  return (
    <div className="space-y-6">
      {/* Header Banner */}
      <div className="bg-gradient-to-r from-rose-500 via-pink-500 to-amber-500 rounded-3xl p-6 text-white shadow-lg shadow-rose-200">
        <div className="flex items-center gap-3">
          <span className="text-4xl animate-bounce-slow">🇮🇳</span>
          <div>
            <h2 className="text-2xl md:text-3xl font-black font-['Baloo_2',sans-serif]">
              हिन्दी वर्णमाला (Hindi Alphabet)
            </h2>
            <p className="text-rose-100 text-sm md:text-base font-medium mt-1">
              अ से ज्ञ तक सीखें: स्वर (अ से अः) और व्यंजन (क से ज्ञ) बोलकर!
            </p>
          </div>
        </div>
      </div>

      {/* Sub-tabs: Swar and Vyanjan */}
      <div className="flex items-center gap-3 bg-white p-2 rounded-2xl border border-slate-200 shadow-sm max-w-md">
        <button
          onClick={() => {
            speechService.playPop();
            setActiveTab('swar');
            speechService.speak("स्वर, अ से अः", true);
          }}
          className={`flex-1 py-3 px-4 rounded-xl font-black text-sm md:text-base transition-all font-['Baloo_2',sans-serif] ${
            activeTab === 'swar'
              ? 'bg-rose-500 text-white shadow-md shadow-rose-200 scale-102'
              : 'text-slate-600 hover:bg-slate-100'
          }`}
        >
          स्वर (Swar - 13)
        </button>

        <button
          onClick={() => {
            speechService.playPop();
            setActiveTab('vyanjan');
            speechService.speak("व्यंजन, क से ज्ञ", true);
          }}
          className={`flex-1 py-3 px-4 rounded-xl font-black text-sm md:text-base transition-all font-['Baloo_2',sans-serif] ${
            activeTab === 'vyanjan'
              ? 'bg-rose-500 text-white shadow-md shadow-rose-200 scale-102'
              : 'text-slate-600 hover:bg-slate-100'
          }`}
        >
          व्यंजन (Vyanjan - 36)
        </button>
      </div>

      {/* Grid of Hindi Cards */}
      <div className="grid grid-cols-2 sm:grid-cols-3 md:grid-cols-4 xl:grid-cols-6 gap-3.5">
        {displayedList.map((item) => {
          const isLearned = learnedLetters.has(item.letter);
          return (
            <button
              key={item.letter}
              onClick={() => handleCardClick(item)}
              className={`${item.colorClass} border-2 rounded-3xl p-4 flex flex-col items-center justify-between min-h-[145px] transition-all transform hover:-translate-y-1 hover:shadow-lg active:scale-95 text-center relative group`}
            >
              {isLearned && (
                <div className="absolute top-2.5 right-2.5 text-amber-500">
                  <Sparkles className="w-4 h-4 fill-amber-400" />
                </div>
              )}

              <div className="text-4xl sm:text-5xl font-black font-['Baloo_2',sans-serif] mt-1 text-slate-800">
                {item.letter}
              </div>

              <div className="text-4xl my-1 transform group-hover:scale-115 transition-transform duration-200">
                {item.emoji}
              </div>

              <div className="font-extrabold text-sm text-slate-700 font-['Baloo_2',sans-serif]">
                {item.wordHindi}
              </div>

              <div className="text-[11px] font-semibold text-slate-500">
                {item.wordEnglish}
              </div>
            </button>
          );
        })}
      </div>

      {/* Hindi Detail Modal */}
      {selectedLetter && (
        <div className="fixed inset-0 z-50 flex items-center justify-center p-4 bg-slate-900/60 backdrop-blur-sm animate-pop">
          <div className="bg-white rounded-3xl shadow-2xl max-w-md w-full overflow-hidden border-4 border-rose-200 relative">
            {/* Top Bar with Navigation Arrows & Close */}
            <div className="flex items-center justify-between p-4 border-b border-slate-100 bg-rose-50/50">
              <button
                onClick={handlePrev}
                className="w-10 h-10 rounded-full bg-white shadow-sm flex items-center justify-center text-slate-700 hover:bg-slate-100"
                title="Previous Letter"
              >
                <ChevronLeft className="w-6 h-6" />
              </button>

              <span className="font-extrabold text-slate-700 text-lg font-['Baloo_2',sans-serif]">
                अक्षर: {selectedLetter.letter}
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
              <div className={`w-36 h-36 mx-auto rounded-3xl ${selectedLetter.colorClass} border-4 flex flex-col items-center justify-center shadow-inner`}>
                <span className="text-6xl font-black font-['Baloo_2',sans-serif] text-slate-800">
                  {selectedLetter.letter}
                </span>
                <span className="text-4xl mt-1 animate-bounce-slow">
                  {selectedLetter.emoji}
                </span>
              </div>

              {/* Hindi Phrase */}
              <div>
                <h3 className="text-3xl font-black text-slate-800 font-['Baloo_2',sans-serif]">
                  {selectedLetter.spokenText}
                </h3>
                <p className="text-base font-bold text-rose-600 mt-1">
                  {selectedLetter.wordEnglish} ({selectedLetter.meaning})
                </p>
              </div>

              {/* Pronunciation guide */}
              <div className="bg-slate-50 border border-slate-200 rounded-2xl p-4 text-slate-700 space-y-1">
                <p className="text-sm font-semibold text-slate-500">
                  Romanization: <span className="font-bold text-slate-800">{selectedLetter.roman}</span>
                </p>
                <p className="text-sm font-medium">
                  {selectedLetter.letter} stands for {selectedLetter.meaning} in Hindi
                </p>
              </div>

              {/* Listen Voice Button */}
              <button
                onClick={() => {
                  speechService.speak(selectedLetter.spokenText, true);
                }}
                className="w-full py-3.5 px-4 bg-rose-500 hover:bg-rose-600 text-white rounded-2xl font-bold flex items-center justify-center gap-2 shadow-md shadow-rose-200 active:scale-95 transition-all text-base"
              >
                <Volume2 className="w-5 h-5" />
                <span className="font-['Baloo_2',sans-serif] text-lg font-bold">उच्चारण सुनें (Listen Voice)</span>
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};
