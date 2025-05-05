import { applyMiddleware, legacy_createStore as createStore } from "redux";
import { reducer } from "./reducers/reducers";
import logger from "redux-logger";
import {thunk} from "redux-thunk";

export const myStore = createStore(reducer,applyMiddleware(thunk,logger))
