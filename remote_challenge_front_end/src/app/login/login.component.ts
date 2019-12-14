import { Component, OnInit } from '@angular/core';
import { AuthentificationService } from '../services/authentification.service';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment.prod';
import { SnotifyService } from 'ng-snotify';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  mode=0;

  constructor(private authService:AuthentificationService,private router:Router,private http:HttpClient,
    private snotifyService:SnotifyService) { }

  ngOnInit() {
    if(this.authService.getToken()) this.router.navigateByUrl('/home');
  }

  showNotificationSuccess(Msg){
    this.snotifyService.success(Msg, {
      timeout: 2000,
      showProgressBar: false,
      closeOnClick: false,
      pauseOnHover: true
    });
  }

  showNotificationErreur(Msg){
    this.snotifyService.error(Msg, {
      timeout: 2000,
      showProgressBar: false,
      closeOnClick: false,
      pauseOnHover: true
    });
  }

  onLogin(data){
    this.authService.login(data)
    .subscribe(resp=>{
      this.showNotificationSuccess("login successful");
      let token="Bearer "+resp.body['token'];
      this.authService.saveToken(token);
      this.router.navigateByUrl('/home')
    },
    err=>{
      this.showNotificationErreur("you don't have credentials to login");
      this.mode=1;
    })
  }



}
