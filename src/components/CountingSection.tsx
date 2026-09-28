import React, { useState } from 'react';
import { COUNTING_DATA, NUMBER_GROUPS, NumberItem } from '../data/countingData';
import { speechService } from '../utils/speech';
import { Volume2, ChevronLeft, ChevronRight, X, Sparkles, Hand } from 'lucide-react';
import confetti from 'canvas-confetti';

interface CountingSectionProps {
  onEarnStar: () => void;
}

export const CountingSection: React.FC<CountingSectionProps> = ({ onEarnStar }) => {
  const [selectedGroup, setSelectedGroup] = useState<string>("1 - 10");
  const [selectedNumber, setSelectedNumber] = useState<NumberItem | null>(null);
  const [interactiveCount, setInteractiveCount] = useState<number>(0);

  const activeGroup = NUMBER_GROUPS.find(g => g.label === selectedGroup) || NUMBER_GROUPS[0];
  const displayedNumbers = COUNTING_DATA.filter(
    n => n.value >= activeGroup.min && n.value <= activeGroup.max
  );

  const handleNumberClick = (item: NumberItem) => {
    speechService.playPop();
    setSelectedNumber(item);
    setInteractiveCount(0);
    speechService.speak(`${item.value}. ${item.word}.`);
  };

  const handleTapCount = () => {
    if (!selectedNumber) return;
    if (interactiveCount < selectedNumber.value) {
      const nextCount = interactiveCount + 1;
      setInteractiveCount(nextCount);
      speechService.playPop();
      speechService.speak(`${nextCount}`);
      if (nextCount === selectedNumber.value) {
        onEarnStar();
        confetti({
          particleCount: 50,
          spread: 70,
          origin: { y: 0.6 }
        });
      }
    }
  };

  const handleNext = () => {
    if (!selectedNumber) return;
    const nextVal = selectedNumber.value === 100 ? 1 : selectedNumber.value + 1;
    const nextItem = COUNTING_DATA.find(n => n.value === nextVal) || COUNTING_DATA[0];
    setSelectedNumber(nextItem);
    setInteractiveCount(0);
    speechService.speak(`${nextItem.value}. ${nextItem.word}.`);
  };

  const handlePrev = () => {
    if (!selectedNumber) return;
    const prevVal = selectedNumber.value === 1 ? 100 : selectedNumber.value - 1;
    const prevItem = COUNTING_DATA.find(n => n.value === prevVal) || COUNTING_DATA[0];
    setSelectedNumber(prevItem);
    setInteractiveCount(0);
    speechService.speak(`${prevItem.value}. ${prevItem.word}.`);
  };

  return (
    <div className="space-y-6">
      {/* Header Banner */}
      <div className="bg-gradient-to-r from-emerald-500 via-teal-500 to-cyan-500 rounded-3xl p-6 text-white shadow-lg shadow-emerald-200">
        <div className="flex items-center gap-3">
          <span className="text-4xl animate-bounce-slow">🔢</span>
          <div>
            <h2 className="text-2xl md:text-3xl font-black">Counting (1 to 100)</h2>
            <p className="text-emerald-100 text-sm md:text-base font-medium mt-1">
              Select groups of 10, tap any number to hear its name in English and Hindi!
            </p>
          </div>
        </div>
      </div>

      {/* Row Grouping Chips */}
      <div className="flex items-center gap-2 overflow-x-auto pb-2 scrollbar-none">
        {NUMBER_GROUPS.map((group) => {
          const isSelected = selectedGroup === group.label;
          return (
            <button
              key={group.label}
              onClick={() => {
                speechService.playPop();
                setSelectedGroup(group.label);
              }}
              className={`px-4 py-2 rounded-2xl font-bold text-sm whitespace-nowrap transition-all shadow-sm ${
                isSelected
                  ? 'bg-emerald-600 text-white shadow-md shadow-emerald-200 scale-105'
                  : 'bg-white text-slate-700 hover:bg-slate-100 border border-slate-200'
              }`}
            >
              {group.label}
            </button>
          );
        })}
      </div>

      {/* Numbers Grid */}
      <div className="grid grid-cols-2 sm:grid-cols-3 md:grid-cols-5 xl:grid-cols-10 gap-3">
        {displayedNumbers.map((item) => (
          <button
            key={item.value}
            onClick={() => handleNumberClick(item)}
            className={`${item.colorClass} border-2 rounded-2xl p-3 flex flex-col items-center justify-between min-h-[105px] transition-all transform hover:-translate-y-1 hover:shadow-md active:scale-95 text-center relative group`}
          >
            <div className="flex items-center justify-between w-full px-1">
              <span className="text-xs font-bold text-slate-400">#{item.value}</span>
              <span className="text-sm">{item.emoji}</span>
            </div>

            <div className="text-3xl font-black tracking-tight my-1 text-slate-800 group-hover:scale-110 transition-transform">
              {item.value}
            </div>

            <div className="font-bold text-xs truncate max-w-full">
              {item.word}
            </div>
          </button>
        ))}
      </div>

      {/* Number Detail Modal with Tap-to-Count */}
      {selectedNumber && (
        <div className="fixed inset-0 z-50 flex items-center justify-center p-4 bg-slate-900/60 backdrop-blur-sm animate-pop">
          <div className="bg-white rounded-3xl shadow-2xl max-w-md w-full overflow-hidden border-4 border-emerald-200 relative">
            {/* Top Navigation */}
            <div className="flex items-center justify-between p-4 border-b border-slate-100 bg-emerald-50/50">
              <button
                onClick={handlePrev}
                className="w-10 h-10 rounded-full bg-white shadow-sm flex items-center justify-center text-slate-700 hover:bg-slate-100"
              >
                <ChevronLeft className="w-6 h-6" />
              </button>

              <span className={`px-3 py-1 rounded-full text-xs font-bold ${selectedNumber.isEven ? 'bg-sky-100 text-sky-800' : 'bg-pink-100 text-pink-800'}`}>
                {selectedNumber.isEven ? 'Even Number' : 'Odd Number'}
              </span>

              <div className="flex items-center gap-2">
                <button
                  onClick={handleNext}
                  className="w-10 h-10 rounded-full bg-white shadow-sm flex items-center justify-center text-slate-700 hover:bg-slate-100"
                >
                  <ChevronRight className="w-6 h-6" />
                </button>
                <button
                  onClick={() => setSelectedNumber(null)}
                  className="w-10 h-10 rounded-full bg-slate-200/80 hover:bg-slate-300 flex items-center justify-center text-slate-700"
                >
                  <X className="w-5 h-5" />
                </button>
              </div>
            </div>

            {/* Modal Content */}
            <div className="p-6 text-center space-y-4">
              {/* Giant Number Badge */}
              <div className="w-28 h-28 mx-auto rounded-full bg-emerald-100 border-4 border-emerald-300 flex flex-col items-center justify-center shadow-inner">
                <span className="text-5xl font-black text-emerald-800">
                  {selectedNumber.value}
                </span>
                <span className="text-xl">{selectedNumber.emoji}</span>
              </div>

              {/* Names */}
              <div>
                <h3 className="text-3xl font-black text-slate-800">
                  {selectedNumber.word}
                </h3>
                <p className="text-lg font-bold text-amber-600 mt-1">
                  हिंदी: {selectedNumber.hindiWord}
                </p>
              </div>

              {/* Tap to Count Interactive Minigame */}
              <div className="bg-slate-50 border border-slate-200 rounded-2xl p-4 space-y-3">
                <div className="flex items-center justify-between text-xs font-bold text-slate-600">
                  <span>Count With Me:</span>
                  <span className="text-emerald-600 font-extrabold text-sm">
                    {interactiveCount} / {selectedNumber.value}
                  </span>
                </div>

                {/* Items to tap/see */}
                <div className="flex flex-wrap justify-center gap-2 p-2 bg-white rounded-xl min-h-[50px] items-center border border-slate-100 max-h-36 overflow-y-auto">
                  {Array.from({ length: Math.min(selectedNumber.value, 15) }).map((_, idx) => {
                    const isCounted = idx < interactiveCount;
                    return (
                      <span
                        key={idx}
                        className={`text-2xl transition-all transform ${isCounted ? 'scale-110 opacity-100' : 'opacity-30 grayscale'}`}
                      >
                        {selectedNumber.emoji}
                      </span>
                    );
                  })}
                  {selectedNumber.value > 15 && (
                    <span className="text-xs text-slate-400 font-bold self-center">
                      +{selectedNumber.value - 15} more
                    </span>
                  )}
                </div>

                <button
                  onClick={handleTapCount}
                  disabled={interactiveCount >= selectedNumber.value}
                  className={`w-full py-2.5 px-4 rounded-xl font-bold flex items-center justify-center gap-2 transition-all shadow-sm ${
                    interactiveCount >= selectedNumber.value
                      ? 'bg-amber-100 text-amber-800 border border-amber-300'
                      : 'bg-emerald-600 hover:bg-emerald-700 text-white active:scale-95'
                  }`}
                >
                  {interactiveCount >= selectedNumber.value ? (
                    <>
                      <Sparkles className="w-4 h-4 fill-amber-400" />
                      <span>Completed! Great Counting! ⭐</span>
                    </>
                  ) : (
                    <>
                      <Hand className="w-4 h-4" />
                      <span>Tap to Count (+1)</span>
                    </>
                  )}
                </button>
              </div>

              {/* Pronounce Audio Button */}
              <button
                onClick={() => {
                  speechService.speak(`${selectedNumber.value}. ${selectedNumber.word}. ${selectedNumber.hindiWord}`);
                }}
                className="w-full py-3.5 px-4 bg-emerald-600 hover:bg-emerald-700 text-white rounded-2xl font-bold flex items-center justify-center gap-2 shadow-md shadow-emerald-200 active:scale-95 transition-all"
              >
                <Volume2 className="w-5 h-5" />
                <span>Pronounce Number</span>
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};
