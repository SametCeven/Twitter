import { useContext } from "react";
import { GlobalContext } from "../context/GlobalContext";
import Tweet from "./Tweet";

export default function AllTweets(){

    const {allTweets, loadingTweets, errorTweets} = useContext(GlobalContext);

    
    return(
        <div className="mt-10 ml-10 flex flex-col gap-5">
            {loadingTweets ? <p> Loading ... </p> : 
            
                allTweets.map((tweet)=>{
                    return <Tweet 
                        key = {tweet.id}
                        tweetId = {tweet.id}
                        tweetText = {tweet.tweetText}
                        createdDate = {tweet.createdDate}
                        likeCount = {tweet.likeCount}
                        retweetCount = {tweet.retweetCount}
                        userId = {tweet.userId}
                        comments = {tweet.commentResponseDtos}
                        ></Tweet>
                })
            
            
            
            }
        </div>
    )
}