
export default function LoginPage(){
    
    function handleChange(){
        
    }

    function handleSubmit(){

    }


    return(
        <form 
        className="flex flex-col justify-center w-80 gap-10 mt-20 mx-20" 
        onSubmit={handleSubmit}>
            <div className="input-label">
                <label htmlFor="username"> Username </label>
                <input className="input" type="text" name="username" id="username" onChange={handleChange} />
            </div>
            
            <div className="input-label">
                <label htmlFor="email"> Email </label>
                <input className="input" type="text" name="email" id="email" onChange={handleChange}/>
            </div>
            
            <div className="input-label">
                <label htmlFor="password"> Password </label>
                <input className="input" type="password" name="password" id="password" onChange={handleChange}/>
            </div>
            

        </form>
    )
}