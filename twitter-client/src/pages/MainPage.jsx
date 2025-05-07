import AllUsers from "../components/AllUsers";
import SideNavBar from "../layout/SideNavBar";

export default function(){
    return(
        <div className="flex">
            <SideNavBar></SideNavBar>
            <div>
            <AllUsers></AllUsers>
            </div>
            
        </div>
    )
}