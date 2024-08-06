import { useNavigate } from "react-router-dom";
import { toast } from 'react-toastify';
import { useGetToken } from "../CustomHook/CustomHook";
import 'react-toastify/dist/ReactToastify.css';
import "./Post.css";

const reportPost = async (postId, reason, token) => {
    const res = await fetch("/api/post/report", {
        method: "PATCH",
        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${token}`
        },
        body: JSON.stringify({ postPublicId: postId, reason: reason }),
    });
    if (res.ok) {
        return res.status;
    } else {
        throw new Error('Report is unsuccessful!');
    }
};

function ReasonOfReport({ postId, onClose }) {
    const navigate = useNavigate();
    const token = useGetToken();

    const handleClick = (e) => {
        const fetchReport = async () => {
            try {
                const data = await reportPost(postId, e.target.innerText, token);
                if (data === 208) {
                    toast.error('You already reported this post!', {
                        position: "bottom-right",
                        autoClose: 5000,
                        hideProgressBar: false,
                        closeOnClick: true,
                        pauseOnHover: true,
                        draggable: true,
                        progress: undefined,
                        theme: "light"
                    });
                } else {
                    toast.success('Your report was successful!', {
                        position: "bottom-right",
                        autoClose: 5000,
                        hideProgressBar: false,
                        closeOnClick: true,
                        pauseOnHover: true,
                        draggable: true,
                        progress: undefined,
                        theme: "light"
                    });
                }
            } catch (error) {
                toast.error('Error reporting the post!', {
                    position: "bottom-right",
                    autoClose: 5000,
                    hideProgressBar: false,
                    closeOnClick: true,
                    pauseOnHover: true,
                    draggable: true,
                    progress: undefined,
                    theme: "light"
                });
            }
        };
        fetchReport();
        onClose();
        navigate("/");
    };

    return (
        <div className="overlay">
            <div className="modal">
                <h4 className="reportQuestion">Why are you reporting this post?</h4>
                {["It's spam", "Nudity or sexual activity", "Hate speech or symbols", "Violence or dangerous organizations", "Sale of illegal or regulated goods", "Bullying or harassment", "Suicide or self-injury", "Eating disorders", "Scam or fraud", "Drugs", "False information", "I just don't like it"].map(reason => (
                    <button key={reason} onClick={handleClick} className="reportReasonsBtn">{reason}</button>
                ))}
                <button onClick={onClose} className="reportReasonCancelBtn">Cancel</button>
            </div>
        </div>
    );
}

export default ReasonOfReport;