import React, { useState } from 'react';
import { Header } from './components/Header';
import { Navigation, SectionType, SECTIONS } from './components/Navigation';
import { AlphabetSection } from './components/AlphabetSection';
import { CountingSection } from './components/CountingSection';
import { MathTablesSection } from './components/MathTablesSection';
import { HindiSection } from './components/HindiSection';
import { StoriesSection } from './components/StoriesSection';
import { RhymesSection } from './components/RhymesSection';
import { speechService } from './utils/speech';

export const App: React.FC = () => {
  const [currentSection, setCurrentSection] = useState<SectionType>('alphabet');
  const [stars, setStars] = useState<number>(5);
  const [isMuted, setIsMuted] = useState<boolean>(false);

  const activeSectionObj = SECTIONS.find(s => s.id === currentSection) || SECTIONS[0];

  const handleEarnStar = () => {
    setStars(prev => prev + 1);
    speechService.playChime();
  };

  const handleToggleMute = () => {
    const nextMuted = !isMuted;
    setIsMuted(nextMuted);
    speechService.setMuted(nextMuted);
  };

  const handleSelectSection = (section: SectionType) => {
    speechService.stop();
    setCurrentSection(section);
  };

  return (
    <div className="min-h-screen flex flex-col bg-[#FFFDF7] text-slate-800">
      {/* Sticky Header */}
      <Header
        stars={stars}
        isMuted={isMuted}
        onToggleMute={handleToggleMute}
        activeSectionTitle={activeSectionObj.title}
      />

      <div className="flex-1 flex max-w-7xl w-full mx-auto">
        {/* Navigation (Sidebar on desktop, bottom bar on mobile) */}
        <Navigation
          currentSection={currentSection}
          onSelectSection={handleSelectSection}
        />

        {/* Main Content View */}
        <main className="flex-1 p-4 md:p-6 lg:p-8 pb-24 lg:pb-8 overflow-y-auto">
          {currentSection === 'alphabet' && (
            <AlphabetSection onEarnStar={handleEarnStar} />
          )}
          {currentSection === 'counting' && (
            <CountingSection onEarnStar={handleEarnStar} />
          )}
          {currentSection === 'tables' && (
            <MathTablesSection onEarnStar={handleEarnStar} />
          )}
          {currentSection === 'hindi' && (
            <HindiSection onEarnStar={handleEarnStar} />
          )}
          {currentSection === 'stories' && (
            <StoriesSection onEarnStar={handleEarnStar} />
          )}
          {currentSection === 'rhymes' && (
            <RhymesSection onEarnStar={handleEarnStar} />
          )}
        </main>
      </div>
    </div>
  );
};
