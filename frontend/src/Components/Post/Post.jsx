import { useEffect, useState } from "react"

const fetchPosts = () => {
    return fetch('/api/post/').then(res => res.json());
}


function Post() {
    const [username, setUserName] = useState('')
    const [picture, setPicture] = useState('')
    const [date, setDate] = useState('')
    const [description, setDescription] = useState('')

    useEffect(() => {
        fetchPosts()
            .then(post => {
                setPicture(post.picture)
                setDate(post.creation_date)
                setDescription(post.description)
            })
    }, []);

    return (
        <div className="post">
            <p><b>{username}</b></p>
            <div>{picture}</div>
            <div>{date}</div>
            <p>{description}</p>
            <form>
                <input placeholder="Comment here" name="comment" />
            </form>
            <button>View all comments</button>
        </div>
    )
}

export default Post