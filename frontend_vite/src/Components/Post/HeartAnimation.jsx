
import "./Post.css";



function HeartAnimation({ isAnimating }) {
    return (
        <svg
            xmlns="http://www.w3.org/2000/svg"
            viewBox="0 0 24 24"
            className={`heart-icon ${isAnimating ? 'animate-like' : ''}`}
        >
            <defs>
                <linearGradient gradientTransform="rotate(35)" id="heart_gradient">
                    <stop offset="0%" stopColor="#FF7A00"></stop>
                    <stop offset="40%" stopColor="#FF0169"></stop>
                    <stop offset="100%" stopColor="#D300C5"></stop>
                </linearGradient>
            </defs>
            <path d="M12 21.35l-1.45-1.32C5.4 15.36 2 12.28 2 8.5 2 5.42 4.42 3 7.5 3c1.74 0 3.41.81 4.5 2.09C13.09 3.81 14.76 3 16.5 3 19.58 3 22 5.42 22 8.5c0 3.78-3.4 6.86-8.55 11.54L12 21.35z" fill="url(#heart_gradient)" />
        </svg>
    )
}


export default HeartAnimation;