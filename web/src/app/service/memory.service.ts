import { Injectable } from '@angular/core';
import {AuthData} from "../share/common.model";


@Injectable({
  providedIn: 'root'
})
export class MemoryService {

  user='user';
  hotel='hotel';
  admin = 'admin';

  constructor() { }

  getRememberUser():boolean{
    let val = localStorage.getItem(this.user);
    if(!val){
      return false;
    }
    return true;
  }

  setUser(user:AuthData){
    if(user){
      localStorage.setItem(this.user,JSON.stringify(user));
    }
  }

  unsetUser(){
    if(this.getRememberUser()){
      localStorage.removeItem(this.user);
    }
  }

  getUser(){
    let val = localStorage.getItem(this.user);
    if(!val){
      return {
        "name":"null",
        "password":""
      };
    }
    return JSON.parse(val);
  }

  getRememberHotel():boolean{
    let val = localStorage.getItem(this.hotel);
    if(!val){
      return false;
    }
    return true;
  }

  setHotel(user:AuthData){
    if(user){
      localStorage.setItem(this.hotel,JSON.stringify(user));
    }
  }

  unsetHotel(){
    if(this.getRememberHotel()){
      localStorage.removeItem(this.hotel);
    }
  }

  getHotel(){
    let val = localStorage.getItem(this.hotel);
    if(!val){
      return {
        "name":"null",
        "password":""
      };
    }
    return JSON.parse(val);
  }

  getRememberAdmin():boolean{
    let val = localStorage.getItem(this.admin);
    if(!val){
      return false;
    }
    return true;
  }

  setAdmin(user:AuthData){
    if(user){
      localStorage.setItem(this.admin,JSON.stringify(user));
    }
  }

  unsetAdmin(){
    if(this.getRememberUser()){
      localStorage.removeItem(this.admin);
    }
  }

  getAdmin(){
    let val = localStorage.getItem(this.admin);
    if(!val){
      return {
        "name":"null",
        "password":""
      };
    }
    return JSON.parse(val);
  }
}
