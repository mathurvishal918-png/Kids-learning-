import React, { useState } from 'react';
import { STORIES_DATA, StoryItem } from '../data/storiesData';
import { speechService } from '../utils/speech';
import { BookOpen, Volume2, ChevronLeft, ChevronRight, X, Globe, Award, Sparkles } from 'lucide-react';
import confetti from 'canvas-confetti';

interface StoriesSectionProps {
  onEarnStar: () => void;
}

export const StoriesSection: React.FC<StoriesSectionProps> = ({ onEarnStar }) => {
  const [selectedStory, setSelectedStory] = useState<StoryItem | null>(null);
  const [currentPage, setCurrentPage] = useState<number>(0);
  const [isHindi, setIsHindi] = useState<boolean>(false);

  const handleOpenStory = (story: StoryItem) => {
    speechService.playPop();
    setSelectedStory(story);
    setCurrentPage(0);
    const firstPage = story.pages[0];
    const text = isHindi ? firstPage.textHi : firstPage.textEn;
    speechService.speak(text, isHindi);
  };

  const handleCloseStory = () => {
    speechService.stop();
    setSelectedStory(null);
  };

  const handleToggleLanguage = () => {
    if (!selectedStory) return;
    const newLang = !isHindi;
    setIsHindi(newLang);
    const page = selectedStory.pages[currentPage];
    const text = newLang ? page.textHi : page.textEn;
    speechService.speak(text, newLang);
  };

  const handleNextPage = () => {
    if (!selectedStory) return;
    if (currentPage < selectedStory.pages.length - 1) {
      const nextPage = currentPage + 1;
      setCurrentPage(nextPage);
      const text = isHindi ? selectedStory.pages[nextPage].textHi : selectedStory.pages[nextPage].textEn;
      speechService.speak(text, isHindi);

      if (nextPage === selectedStory.pages.length - 1) {
        onEarnStar();
        confetti({
          particleCount: 50,
          spread: 70,
          origin: { y: 0.6 }
        });
      }
    }
  };

  const handlePrevPage = () => {
    if (!selectedStory) return;
    if (currentPage > 0) {
      const prevPage = currentPage - 1;
      setCurrentPage(prevPage);
      const text = isHindi ? selectedStory.pages[prevPage].textHi : selectedStory.pages[prevPage].textEn;
      speechService.speak(text, isHindi);
    }
  };

  const handleReadAloud = () => {
    if (!selectedStory) return;
    const page = selectedStory.pages[currentPage];
    const text = isHindi ? page.textHi : page.textEn;
    speechService.speak(text, isHindi);
  };

  return (
    <div className="space-y-6">
      {/* Header Banner */}
      <div className="bg-gradient-to-r from-purple-500 via-indigo-500 to-pink-500 rounded-3xl p-6 text-white shadow-lg shadow-purple-200">
        <div className="flex items-center gap-3">
          <span className="text-4xl animate-bounce-slow">📖</span>
          <div>
            <h2 className="text-2xl md:text-3xl font-black">Kids Moral Stories (कहानियाँ)</h2>
            <p className="text-purple-100 text-sm md:text-base font-medium mt-1">
              Read classic illustrated moral stories in English & Hindi with voice narration!
            </p>
          </div>
        </div>
      </div>

      {/* Stories Grid */}
      <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
        {STORIES_DATA.map((story) => (
          <div
            key={story.id}
            className="bg-white rounded-3xl p-5 border-2 border-slate-200 hover:border-purple-300 hover:shadow-lg transition-all flex flex-col justify-between group"
          >
            <div className="flex items-start gap-4">
              <div className="w-20 h-20 rounded-2xl bg-gradient-to-br from-purple-50 to-pink-50 border border-purple-200 flex items-center justify-center text-4xl shrink-0 group-hover:scale-105 transition-transform shadow-inner">
                {story.coverEmoji}
              </div>
              <div className="flex-1 min-w-0">
                <h3 className="text-xl font-black text-slate-800 truncate">
                  {story.titleEn}
                </h3>
                <h4 className="text-base font-extrabold text-purple-600 font-['Baloo_2',sans-serif]">
                  {story.titleHi}
                </h4>
                <p className="text-xs text-slate-500 mt-1 line-clamp-2">
                  {story.summaryEn}
                </p>
              </div>
            </div>

            <div className="mt-4 pt-3 border-t border-slate-100 flex items-center justify-between">
              <span className="text-xs font-bold text-amber-700 bg-amber-50 px-2.5 py-1 rounded-lg border border-amber-200 truncate max-w-[200px]">
                ⭐ {story.moralEn}
              </span>

              <button
                onClick={() => handleOpenStory(story)}
                className="px-4 py-2 bg-purple-600 hover:bg-purple-700 text-white rounded-xl font-bold text-sm flex items-center gap-1.5 shadow-sm shadow-purple-200 active:scale-95 transition-all"
              >
                <BookOpen className="w-4 h-4" />
                <span>Read Story</span>
              </button>
            </div>
          </div>
        ))}
      </div>

      {/* Interactive Story Reader Modal */}
      {selectedStory && (
        <div className="fixed inset-0 z-50 flex items-center justify-center p-4 bg-slate-900/60 backdrop-blur-sm animate-pop">
          <div className="bg-white rounded-3xl shadow-2xl max-w-xl w-full overflow-hidden border-4 border-purple-200 relative flex flex-col max-h-[90vh]">
            {/* Modal Header */}
            <div className="flex items-center justify-between p-4 border-b border-slate-100 bg-purple-50/50">
              <div>
                <h4 className="font-extrabold text-slate-800 text-lg">
                  {isHindi ? selectedStory.titleHi : selectedStory.titleEn}
                </h4>
                <p className="text-xs text-slate-500 font-medium">
                  Page {currentPage + 1} of {selectedStory.pages.length}
                </p>
              </div>

              <div className="flex items-center gap-2">
                <button
                  onClick={handleToggleLanguage}
                  className="px-3 py-1.5 rounded-xl bg-amber-100 border border-amber-300 text-amber-900 text-xs font-extrabold flex items-center gap-1 hover:bg-amber-200"
                >
                  <Globe className="w-3.5 h-3.5" />
                  <span>{isHindi ? "English 🇬🇧" : "हिन्दी 🇮🇳"}</span>
                </button>

                <button
                  onClick={handleCloseStory}
                  className="w-9 h-9 rounded-full bg-slate-200/80 hover:bg-slate-300 flex items-center justify-center text-slate-700"
                >
                  <X className="w-5 h-5" />
                </button>
              </div>
            </div>

            {/* Modal Body */}
            <div className="p-6 overflow-y-auto space-y-4 text-center">
              {/* Illustration Placeholder */}
              <div className="w-full h-40 rounded-3xl bg-gradient-to-r from-purple-100 via-pink-100 to-amber-100 border-2 border-purple-200 flex items-center justify-center shadow-inner relative overflow-hidden">
                <span className="text-7xl animate-bounce-slow">
                  {selectedStory.pages[currentPage].emoji}
                </span>
              </div>

              {/* Story Page Text */}
              <div className="bg-slate-50 border border-slate-200 rounded-2xl p-5">
                <p className={`text-slate-800 font-medium text-lg leading-relaxed ${isHindi ? "font-['Baloo_2',sans-serif] text-xl" : ""}`}>
                  {isHindi
                    ? selectedStory.pages[currentPage].textHi
                    : selectedStory.pages[currentPage].textEn}
                </p>
              </div>

              {/* Moral Banner on Last Page */}
              {currentPage === selectedStory.pages.length - 1 && (
                <div className="bg-emerald-50 border-2 border-emerald-300 rounded-2xl p-4 text-emerald-900 space-y-1 animate-pop">
                  <div className="flex items-center justify-center gap-1.5 font-black text-sm text-emerald-700">
                    <Award className="w-5 h-5 text-emerald-600" />
                    <span>Moral of the Story:</span>
                  </div>
                  <p className="font-extrabold text-base">
                    {isHindi ? selectedStory.moralHi : selectedStory.moralEn}
                  </p>
                </div>
              )}
            </div>

            {/* Modal Footer Controls */}
            <div className="p-4 border-t border-slate-100 bg-white flex items-center justify-between">
              <button
                onClick={handlePrevPage}
                disabled={currentPage === 0}
                className="w-11 h-11 rounded-2xl bg-purple-50 hover:bg-purple-100 text-purple-700 flex items-center justify-center disabled:opacity-30 disabled:pointer-events-none"
              >
                <ChevronLeft className="w-6 h-6" />
              </button>

              <button
                onClick={handleReadAloud}
                className="py-2.5 px-5 bg-purple-600 hover:bg-purple-700 text-white rounded-2xl font-bold flex items-center gap-2 shadow-md shadow-purple-200 active:scale-95"
              >
                <Volume2 className="w-4 h-4" />
                <span>Read Page Aloud</span>
              </button>

              <button
                onClick={handleNextPage}
                disabled={currentPage === selectedStory.pages.length - 1}
                className="w-11 h-11 rounded-2xl bg-purple-50 hover:bg-purple-100 text-purple-700 flex items-center justify-center disabled:opacity-30 disabled:pointer-events-none"
              >
                <ChevronRight className="w-6 h-6" />
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};
