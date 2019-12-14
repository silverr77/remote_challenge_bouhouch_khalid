import { Component, OnInit } from '@angular/core';
import { RegistrationService } from '../services/registration.service';
import { Router } from '@angular/router';
import { SnotifyService } from 'ng-snotify';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  constructor(private regService:RegistrationService,private router:Router,private snotifyService:SnotifyService) { }

  ngOnInit() {
    if(localStorage.getItem('token')) this.router.navigateByUrl('/home');
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
  onRegister(data){
    this.regService.register(data)
    .subscribe(resp=>{
        this.showNotificationSuccess("Account has ben Created Successfuly");
        this.router.navigateByUrl('/login');
    },err=>{
        this.showNotificationErreur("The email was aleardy used by another user");
    })
  }

}
