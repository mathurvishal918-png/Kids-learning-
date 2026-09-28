import React from 'react';
import { Volume2, VolumeX, Sparkles } from 'lucide-react';
import { speechService } from '../utils/speech';

interface HeaderProps {
  stars: number;
  isMuted: boolean;
  onToggleMute: () => void;
  activeSectionTitle: string;
}

export const Header: React.FC<HeaderProps> = ({
  stars,
  isMuted,
  onToggleMute,
  activeSectionTitle
}) => {
  return (
    <header className="sticky top-0 z-30 bg-white/90 backdrop-blur-md border-b border-amber-100 shadow-sm px-4 py-3">
      <div className="max-w-7xl mx-auto flex items-center justify-between">
        {/* Logo & Section Title */}
        <div className="flex items-center gap-3">
          <div className="w-11 h-11 rounded-2xl bg-gradient-to-tr from-amber-400 via-rose-400 to-indigo-500 flex items-center justify-center shadow-md shadow-rose-200 transform hover:scale-105 transition-transform">
            <span className="text-2xl animate-bounce-slow">🎈</span>
          </div>
          <div>
            <h1 className="text-xl md:text-2xl font-black bg-gradient-to-r from-blue-600 via-rose-500 to-amber-500 bg-clip-text text-transparent">
              Kids Joy Learning
            </h1>
            <p className="text-xs md:text-sm font-semibold text-slate-500 flex items-center gap-1">
              <span>{activeSectionTitle}</span>
            </p>
          </div>
        </div>

        {/* Action Controls: Stars badge & Sound toggle */}
        <div className="flex items-center gap-3">
          {/* Star Counter */}
          <div className="flex items-center gap-1.5 bg-gradient-to-r from-amber-100 to-yellow-100 border border-amber-300 px-3.5 py-1.5 rounded-full shadow-sm">
            <Sparkles className="w-4 h-4 text-amber-600 fill-amber-400 animate-spin" style={{ animationDuration: '6s' }} />
            <span className="text-sm md:text-base font-extrabold text-amber-900">
              {stars} Stars
            </span>
          </div>

          {/* Sound Mute/Unmute */}
          <button
            onClick={() => {
              speechService.playPop();
              onToggleMute();
            }}
            className={`w-10 h-10 rounded-full flex items-center justify-center transition-all shadow-sm ${
              isMuted
                ? 'bg-rose-100 text-rose-600 hover:bg-rose-200'
                : 'bg-emerald-100 text-emerald-700 hover:bg-emerald-200'
            }`}
            title={isMuted ? "Unmute Voice" : "Mute Voice"}
          >
            {isMuted ? <VolumeX className="w-5 h-5" /> : <Volume2 className="w-5 h-5" />}
          </button>
        </div>
      </div>
    </header>
  );
};
