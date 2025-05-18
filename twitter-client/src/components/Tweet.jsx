import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faHeart, faRetweet, faUser } from '@fortawesome/free-solid-svg-icons'
import { useContext, useEffect, useState } from 'react';
import useAxiosGet from '../hooks/useAxiosGet';
import { GlobalContext } from '../context/GlobalContext';
import useAxiosPost from '../hooks/useAxiosPost';
import useAxiosDelete from "../hooks/useAxiosDelete";

export default function Tweet(props) {

    const { token, loggedInUser } = useContext(GlobalContext);
    const { tweetId, tweetText, createdDate, likeCount: initialLikeCount, retweetCount: initialRetweetCount, userId, commentResponseDtos } = props;
    const [user, loadingUser, errorUser] = useAxiosGet(`/user/${userId}`, token);

    const [likeData, loadingLike, errorLike, postLike] = useAxiosPost();
    const [likeCount, setLikeCount] = useState(initialLikeCount);
    const [dislikeData, loadingDislike, errorDislike, postDislike] = useAxiosPost();
    const [initialIsLiked, loadingIsLiked, errorIsLiked] = useAxiosGet("/isLiked/tweet", token, { tweetId: tweetId });
    const [isLiked, setIsLiked] = useState(initialIsLiked);

    const [retweetData, loadingRetweet, errorRetweet, postRetweet] = useAxiosPost();
    const [retweetCount, setRetweetCount] = useState(initialRetweetCount);
    const [disretweetLoading, disretweetError, deleteRetweet] = useAxiosDelete();
    const [initialIsRetweeted, loadingIsRetweeted, errorIsRetweeted] = useAxiosGet("/retweet/tweet/isRetweeted", token, {tweetId : tweetId});
    const [isRetweeted, setIsRetweeted] = useState(initialIsRetweeted);
    

    useEffect(()=>{
        if(initialIsLiked !== null){
            setIsLiked(initialIsLiked)
        }
    },[initialIsLiked])

    async function handleLike(e) {
        if (!isLiked) {
            try {
                await postLike("like/tweet", tweetId, token);
                setLikeCount((prev) => prev + 1);
                setIsLiked(true);
            }
            catch {

            }
        } else {
            try {
                await postDislike("dislike/tweet", tweetId, token);
                setLikeCount((prev) => prev - 1);
                setIsLiked(false);
            }
            catch {

            }
        }
    }

    useEffect(()=>{
        if(initialIsRetweeted !== null){
            setIsRetweeted(initialIsRetweeted)
        }
    },[initialIsRetweeted])

    async function handleRetweet(e) {
        console.log(isRetweeted)
        if (!isRetweeted) {
            try {
                await postRetweet("retweet/tweet", tweetId, token);
                setRetweetCount((prev) => prev + 1);
                setIsRetweeted(true);
            }
            catch {

            }
        } else {
            try {
                await deleteRetweet(`retweet/tweet/${tweetId}`, token);
                setRetweetCount((prev) => prev - 1);
                setIsRetweeted(false);
            }
            catch {

            }
        }
    }


    return (
        <div className="">
            {loadingUser ? <p> Loading ... </p> :

                <div className='border rounded-xl w-200 px-5 py-5'>
                    <p className='border-b mb-5 py-5 break-words'> {tweetText} </p>
                    <div className='flex justify-between'>
                        <div>
                            <FontAwesomeIcon icon={faUser} />
                            <span> {user.username} </span>
                        </div>
                        <div className='flex gap-10'>
                            <div className='hover:cursor-pointer' onClick={handleLike}>
                                <FontAwesomeIcon icon={faHeart} />
                                <span> {likeCount} </span>
                            </div>
                            <div className='hover:cursor-pointer' onClick={handleRetweet}>
                                <FontAwesomeIcon icon={faRetweet} />
                                <span> {retweetCount} </span>
                            </div>
                        </div>

                        <span> {createdDate} </span>
                    </div>
                </div>

            }
        </div>
    )
}
