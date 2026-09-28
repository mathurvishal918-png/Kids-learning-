import React, { useState } from 'react';
import { RHYMES_DATA, RhymeItem } from '../data/rhymesData';
import { speechService } from '../utils/speech';
import { Music, Volume2, Play, Square, X, Sparkles } from 'lucide-react';
import confetti from 'canvas-confetti';

interface RhymesSectionProps {
  onEarnStar: () => void;
}

export const RhymesSection: React.FC<RhymesSectionProps> = ({ onEarnStar }) => {
  const [filter, setFilter] = useState<'all' | 'english' | 'hindi'>('all');
  const [selectedRhyme, setSelectedRhyme] = useState<RhymeItem | null>(null);
  const [isSinging, setIsSinging] = useState<boolean>(false);
  const [activeLineIndex, setActiveLineIndex] = useState<number | null>(null);

  const filteredRhymes = RHYMES_DATA.filter((r) => {
    if (filter === 'english') return !r.isHindi;
    if (filter === 'hindi') return r.isHindi;
    return true;
  });

  const handleOpenRhyme = (rhyme: RhymeItem) => {
    speechService.playPop();
    setSelectedRhyme(rhyme);
    setIsSinging(false);
    setActiveLineIndex(null);
    speechService.speak(rhyme.title, rhyme.isHindi);
  };

  const handleCloseRhyme = () => {
    speechService.stop();
    setIsSinging(false);
    setActiveLineIndex(null);
    setSelectedRhyme(null);
  };

  const handleSingFullRhyme = () => {
    if (!selectedRhyme) return;

    if (isSinging) {
      speechService.stop();
      setIsSinging(false);
      setActiveLineIndex(null);
      return;
    }

    setIsSinging(true);
    let curr = 0;

    const singNextLine = () => {
      if (curr >= selectedRhyme.lines.length) {
        setIsSinging(false);
        setActiveLineIndex(null);
        onEarnStar();
        confetti({
          particleCount: 50,
          spread: 70,
          origin: { y: 0.6 }
        });
        return;
      }

      setActiveLineIndex(curr);
      speechService.speak(selectedRhyme.lines[curr], selectedRhyme.isHindi, () => {
        curr++;
        setTimeout(singNextLine, 400);
      });
    };

    singNextLine();
  };

  const handleSpeakLine = (line: string, index: number) => {
    if (!selectedRhyme) return;
    speechService.playPop();
    setActiveLineIndex(index);
    speechService.speak(line, selectedRhyme.isHindi, () => {
      setActiveLineIndex(null);
    });
  };

  return (
    <div className="space-y-6">
      {/* Header Banner */}
      <div className="bg-gradient-to-r from-pink-500 via-rose-500 to-amber-500 rounded-3xl p-6 text-white shadow-lg shadow-pink-200">
        <div className="flex items-center gap-3">
          <span className="text-4xl animate-bounce-slow">🎵</span>
          <div>
            <h2 className="text-2xl md:text-3xl font-black">Poems & Rhymes (कविताएँ)</h2>
            <p className="text-pink-100 text-sm md:text-base font-medium mt-1">
              Sing along with favorite English and Hindi nursery rhymes and balgeet!
            </p>
          </div>
        </div>
      </div>

      {/* Language Filter Buttons */}
      <div className="flex items-center gap-2">
        <button
          onClick={() => {
            speechService.playPop();
            setFilter('all');
          }}
          className={`px-4 py-2 rounded-2xl font-bold text-sm transition-all shadow-sm ${
            filter === 'all'
              ? 'bg-pink-600 text-white shadow-md shadow-pink-200 scale-105'
              : 'bg-white text-slate-700 hover:bg-slate-100 border border-slate-200'
          }`}
        >
          All Rhymes ({RHYMES_DATA.length})
        </button>

        <button
          onClick={() => {
            speechService.playPop();
            setFilter('english');
          }}
          className={`px-4 py-2 rounded-2xl font-bold text-sm transition-all shadow-sm ${
            filter === 'english'
              ? 'bg-pink-600 text-white shadow-md shadow-pink-200 scale-105'
              : 'bg-white text-slate-700 hover:bg-slate-100 border border-slate-200'
          }`}
        >
          🇬🇧 English Rhymes
        </button>

        <button
          onClick={() => {
            speechService.playPop();
            setFilter('hindi');
          }}
          className={`px-4 py-2 rounded-2xl font-bold text-sm transition-all shadow-sm font-['Baloo_2',sans-serif] ${
            filter === 'hindi'
              ? 'bg-pink-600 text-white shadow-md shadow-pink-200 scale-105'
              : 'bg-white text-slate-700 hover:bg-slate-100 border border-slate-200'
          }`}
        >
          🇮🇳 हिन्दी कविताएँ
        </button>
      </div>

      {/* Rhymes Grid */}
      <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 xl:grid-cols-4 gap-4">
        {filteredRhymes.map((rhyme) => (
          <div
            key={rhyme.id}
            className={`${rhyme.colorClass} border-2 rounded-3xl p-5 flex flex-col justify-between hover:shadow-lg transition-all group`}
          >
            <div>
              <div className="flex items-center justify-between">
                <span className="text-4xl group-hover:scale-110 transition-transform">
                  {rhyme.emoji}
                </span>
                <span className={`text-xs font-bold px-2.5 py-1 rounded-full ${rhyme.badgeClass}`}>
                  {rhyme.isHindi ? "हिन्दी" : "English"}
                </span>
              </div>

              <h3 className={`text-xl font-black mt-3 text-slate-800 ${rhyme.isHindi ? "font-['Baloo_2',sans-serif]" : ""}`}>
                {rhyme.title}
              </h3>
              <p className="text-xs font-semibold text-slate-500 mt-0.5">
                {rhyme.subtitle}
              </p>

              <div className="mt-3 p-3 bg-white/70 rounded-xl border border-slate-200/50 space-y-1">
                <p className="text-xs italic text-slate-600 truncate">
                  "{rhyme.lines[0]}"
                </p>
                <p className="text-xs italic text-slate-600 truncate">
                  "{rhyme.lines[1]}"
                </p>
              </div>
            </div>

            <button
              onClick={() => handleOpenRhyme(rhyme)}
              className="mt-4 w-full py-2.5 px-4 bg-white hover:bg-pink-600 hover:text-white text-slate-800 border-2 border-pink-300 rounded-2xl font-bold text-sm flex items-center justify-center gap-2 shadow-sm transition-all active:scale-95"
            >
              <Music className="w-4 h-4 text-pink-500" />
              <span>Sing & Learn</span>
            </button>
          </div>
        ))}
      </div>

      {/* Rhyme Modal Reader */}
      {selectedRhyme && (
        <div className="fixed inset-0 z-50 flex items-center justify-center p-4 bg-slate-900/60 backdrop-blur-sm animate-pop">
          <div className="bg-white rounded-3xl shadow-2xl max-w-lg w-full overflow-hidden border-4 border-pink-300 relative flex flex-col max-h-[90vh]">
            {/* Header */}
            <div className="flex items-center justify-between p-4 border-b border-slate-100 bg-pink-50/50">
              <div className="flex items-center gap-3">
                <span className="text-3xl">{selectedRhyme.emoji}</span>
                <div>
                  <h4 className={`font-extrabold text-slate-800 text-lg ${selectedRhyme.isHindi ? "font-['Baloo_2',sans-serif]" : ""}`}>
                    {selectedRhyme.title}
                  </h4>
                  <p className="text-xs text-slate-500 font-medium">
                    {selectedRhyme.subtitle}
                  </p>
                </div>
              </div>

              <button
                onClick={handleCloseRhyme}
                className="w-9 h-9 rounded-full bg-slate-200/80 hover:bg-slate-300 flex items-center justify-center text-slate-700"
              >
                <X className="w-5 h-5" />
              </button>
            </div>

            {/* Action Bar: Sing full rhyme */}
            <div className="p-4 bg-amber-50/70 border-b border-amber-200/60 flex items-center justify-between">
              <div>
                <p className="text-xs font-bold text-amber-900">
                  {isSinging ? "Singing along with rhyme..." : "Tap any line below to listen:"}
                </p>
              </div>

              <button
                onClick={handleSingFullRhyme}
                className={`py-2 px-4 rounded-xl font-bold text-sm flex items-center gap-1.5 shadow-sm transition-all ${
                  isSinging
                    ? 'bg-rose-500 text-white animate-pulse'
                    : 'bg-pink-600 hover:bg-pink-700 text-white shadow-pink-200'
                }`}
              >
                {isSinging ? <Square className="w-4 h-4 fill-white" /> : <Play className="w-4 h-4 fill-white" />}
                <span>{isSinging ? "Stop" : "Sing Along"}</span>
              </button>
            </div>

            {/* Structured Lyrics Body */}
            <div className="p-5 overflow-y-auto space-y-2.5">
              {selectedRhyme.lines.map((line, idx) => {
                const isActive = activeLineIndex === idx;
                return (
                  <button
                    key={idx}
                    onClick={() => handleSpeakLine(line, idx)}
                    className={`w-full p-3.5 rounded-2xl border-2 text-left flex items-center justify-between transition-all transform hover:-translate-y-0.5 active:scale-98 ${
                      isActive
                        ? 'bg-pink-100 border-pink-400 shadow-md scale-102'
                        : 'bg-slate-50 border-slate-200 hover:border-pink-300 hover:bg-pink-50/50'
                    }`}
                  >
                    <span className={`text-base md:text-lg font-bold text-slate-800 ${selectedRhyme.isHindi ? "font-['Baloo_2',sans-serif] text-xl" : ""}`}>
                      {line}
                    </span>
                    <Volume2 className="w-4 h-4 text-pink-500 shrink-0 ml-2" />
                  </button>
                );
              })}
            </div>
          </div>
        </div>
      )}
    </div>
  );
};
