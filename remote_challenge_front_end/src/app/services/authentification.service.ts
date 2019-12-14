import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment.prod';

@Injectable({
  providedIn: 'root'
})
export class AuthentificationService {

  private jwtToken=null;
  constructor(private http:HttpClient) { }

  login(user){
    return this.http.post(environment.api+"authenticate",user,{observe:'response'});
  }

  saveToken(jwt:string){
    localStorage.setItem('token',jwt);
  }

  loadToken(){
    this.jwtToken=localStorage.getItem('token');
  }

  getToken(){
    return localStorage.getItem('token');
  }


  logout(){
    this.jwtToken=null;
    localStorage.removeItem('token');
  }

  getPreferredShops(){
    if(this.jwtToken==null) this.loadToken();
    return this.http.get(environment.api+"shops/getPreferredShops",{headers:new HttpHeaders({'Authorization':this.jwtToken})});
  }

  getNearbyShops(){
    if(this.jwtToken==null) this.loadToken();
    return this.http.get(environment.api+"shops/getNearbyShops",{headers:new HttpHeaders({'Authorization':this.jwtToken})});
  }

  setDislikeShop(idShop){
    if(this.jwtToken==null) this.loadToken();
    return this.http.get(environment.api+"users/dislike/"+idShop,{headers:new HttpHeaders({'Authorization':this.jwtToken})});
  }

  setlikeShop(idShop){
    if(this.jwtToken==null) this.loadToken();
    return this.http.get(environment.api+"users/like/"+idShop,{headers:new HttpHeaders({'Authorization':this.jwtToken})});
  }
  deleteShop(idShop){
    if(this.jwtToken==null) this.loadToken();
    return this.http.delete(environment.api+"users/delete/"+idShop,{headers:new HttpHeaders({'Authorization':this.jwtToken})})
  }

}
