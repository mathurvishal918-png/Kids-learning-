import React from 'react';
import { speechService } from '../utils/speech';

export type SectionType = 'alphabet' | 'counting' | 'tables' | 'hindi' | 'stories' | 'rhymes';

interface NavigationProps {
  currentSection: SectionType;
  onSelectSection: (section: SectionType) => void;
}

export const SECTIONS: { id: SectionType; title: string; shortTitle: string; icon: string; color: string }[] = [
  { id: 'alphabet', title: 'English Alphabet (A-Z)', shortTitle: 'ABC', icon: '🔤', color: 'from-blue-500 to-indigo-600' },
  { id: 'counting', title: 'Counting (1 to 100)', shortTitle: '1-100', icon: '🔢', color: 'from-emerald-500 to-teal-600' },
  { id: 'tables', title: 'Math Tables (1 to 10)', shortTitle: 'Tables', icon: '✖️', color: 'from-amber-500 to-orange-600' },
  { id: 'hindi', title: 'Hindi Alphabet (अ से ज्ञ)', shortTitle: 'हिन्दी', icon: 'अ', color: 'from-rose-500 to-pink-600' },
  { id: 'stories', title: 'Kids Stories (कहानियाँ)', shortTitle: 'Stories', icon: '📖', color: 'from-purple-500 to-indigo-600' },
  { id: 'rhymes', title: 'Poems & Rhymes (कविताएँ)', shortTitle: 'Rhymes', icon: '🎵', color: 'from-pink-500 to-rose-600' },
];

export const Navigation: React.FC<NavigationProps> = ({
  currentSection,
  onSelectSection
}) => {
  return (
    <>
      {/* Desktop & Tablet Sidebar */}
      <aside className="hidden lg:flex flex-col w-64 shrink-0 bg-white border-r border-amber-100 p-4 min-h-[calc(100vh-68px)]">
        <div className="space-y-2 sticky top-24">
          <p className="text-xs font-bold uppercase tracking-wider text-slate-400 px-3 py-1">
            Learning Fun
          </p>
          {SECTIONS.map((sec) => {
            const isActive = currentSection === sec.id;
            return (
              <button
                key={sec.id}
                onClick={() => {
                  speechService.playPop();
                  onSelectSection(sec.id);
                }}
                className={`w-full flex items-center gap-3.5 px-4 py-3 rounded-2xl font-bold text-left transition-all ${
                  isActive
                    ? `bg-gradient-to-r ${sec.color} text-white shadow-md shadow-blue-100 scale-102`
                    : 'text-slate-600 hover:bg-slate-100 hover:text-slate-900'
                }`}
              >
                <span className="text-2xl">{sec.icon}</span>
                <span className="text-base tracking-wide">{sec.title}</span>
              </button>
            );
          })}
        </div>
      </aside>

      {/* Mobile & Small Tablet Bottom Navigation */}
      <nav className="lg:hidden fixed bottom-0 left-0 right-0 z-40 bg-white/95 backdrop-blur-md border-t border-slate-200 shadow-lg px-2 py-1.5 safe-area-bottom">
        <div className="flex items-center justify-around">
          {SECTIONS.map((sec) => {
            const isActive = currentSection === sec.id;
            return (
              <button
                key={sec.id}
                onClick={() => {
                  speechService.playPop();
                  onSelectSection(sec.id);
                }}
                className={`flex flex-col items-center justify-center py-1 px-2.5 rounded-xl transition-all min-w-[50px] ${
                  isActive
                    ? 'text-blue-600 font-extrabold bg-blue-50 transform scale-105'
                    : 'text-slate-500 font-medium hover:text-slate-800'
                }`}
              >
                <span className="text-xl">{sec.icon}</span>
                <span className="text-[11px] leading-tight mt-0.5">{sec.shortTitle}</span>
              </button>
            );
          })}
        </div>
      </nav>
    </>
  );
};
