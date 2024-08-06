import { useState } from "react";
import "./Post.css";
import ReasonOfReport from "./ReasonOfReport";

function Options({ postId }) {
    const [showReportModal, setShowReportModal] = useState(false);

    const handleReportClick = () => {
        setShowReportModal(true);
    };

    const handleCloseModal = () => {
        setShowReportModal(false);
    };

    return (
        <div className="options">
            <button className="reportButton" onClick={handleReportClick}>Report post</button>
            {showReportModal && (
                <ReasonOfReport postId={postId} onClose={handleCloseModal} />
            )}
        </div>
    );
}

export default Options;