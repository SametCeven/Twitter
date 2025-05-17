import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faHeart, faRetweet, faUser } from '@fortawesome/free-solid-svg-icons'
import { useContext } from 'react';
import useAxiosGet from '../hooks/useAxiosGet';
import { GlobalContext } from '../context/GlobalContext';

export default function Tweet(props) {

    const { token } = useContext(GlobalContext);
    const { tweetText, createdDate, likeCount, retweetCount, userId, commentResponseDtos } = props;
    const [user, loadingUser, errorUser] = useAxiosGet(`/user/${userId}`, token);



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
                            <div>
                                <FontAwesomeIcon icon={faHeart} />
                                <span> {likeCount} </span>
                            </div>
                            <div>
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
