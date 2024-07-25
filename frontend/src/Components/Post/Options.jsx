import { useGetToken } from "../CustomHook/CustomHook"
import "./Post.css"
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

const reportPost = async (postId, token) => {
    const res = await fetch("/api/post/report", {
        method: "PATCH",
        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${token}`
        },
        body: JSON.stringify({ postPublicId: postId }),
    });
    if (res.ok) {
        return res;
    } else {
        throw new Error('Report is unsuccesful!');
    }
}


function Options({ postId }) {
    const token = useGetToken();

    const handleClick = () => {
        reportPost(postId, token);
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

    return (
        <div className="options">
            <button className="reportButton" onClick={handleClick}>Report post</button>

        </div>
    )
}

export default Options;