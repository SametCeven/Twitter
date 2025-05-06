import { useState } from "react";

export default function useLocalStorage(key,defaultValue){

    function initialValue(){
        const localStorageValue = JSON.parse(localStorage.getItem(key));
        if(localStorageValue === null){
            localStorage.setItem(key,JSON.stringify(defaultValue));
            return defaultValue;
        }
        else{
            return localStorageValue;
        }
    }

    const [value, setValue] = useState(initialValue());

    function setLocalStorageValue(newValue){
        localStorage.setItem(key, JSON.stringify(newValue));
        setValue(newValue);
    }

    return [value, setLocalStorageValue];

}