/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {
      colors: {
        kids: {
          blue: '#4361EE',
          coral: '#FF7A59',
          yellow: '#FFB703',
          green: '#06D6A0',
          pink: '#FF70A6',
          purple: '#8338EC',
          sky: '#38BDF8',
          bg: '#FFFDF7'
        }
      },
      fontFamily: {
        kids: ['"Fredoka"', '"Comic Neue"', 'sans-serif', 'system-ui']
      },
      keyframes: {
        bounceSlow: {
          '0%, 100%': { transform: 'translateY(0)' },
          '50%': { transform: 'translateY(-6px)' }
        },
        wiggle: {
          '0%, 100%': { transform: 'rotate(-3deg)' },
          '50%': { transform: 'rotate(3deg)' }
        },
        pop: {
          '0%': { transform: 'scale(0.85)', opacity: '0' },
          '70%': { transform: 'scale(1.05)' },
          '100%': { transform: 'scale(1)', opacity: '1' }
        }
      },
      animation: {
        'bounce-slow': 'bounceSlow 2s infinite ease-in-out',
        'wiggle': 'wiggle 0.5s ease-in-out infinite',
        'pop': 'pop 0.3s cubic-bezier(0.175, 0.885, 0.32, 1.275)'
      }
    },
  },
  plugins: [],
}
