import { Component, OnInit } from '@angular/core';
import { AuthentificationService } from '../services/authentification.service';
import { Router } from '@angular/router';
import { SnotifyService } from 'ng-snotify';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private authService:AuthentificationService,private router:Router,
    private snotifyService:SnotifyService) { }
  private userLogin=0;
  private Nearredshops;

  ngOnInit() {
    if(localStorage.getItem('token')) this.userLogin=1;
    this.getNearredShops()
  }

  getNearredShops(){
    this.authService.getNearbyShops()
    .subscribe(resp=>{
      console.log(resp);
      this.Nearredshops=resp;
    },err=>{
      console.log(err);
      this.authService.logout();
      this.router.navigateByUrl('/login');
    })
  }

  showNotification(Msg){
    this.snotifyService.success(Msg, {
      timeout: 2000,
      showProgressBar: false,
      closeOnClick: false,
      pauseOnHover: true
    });
  }

  likeShop(idShop,nameShop){
    this.authService.setlikeShop(idShop)
    .subscribe(resp=>{
      this.showNotification('you liked the shop : '+nameShop);
      this.getNearredShops();
    },err=>{
      console.log("err",err);
    });
  }

  dislikeShop(idShop,nameShop){
    this.authService.setDislikeShop(idShop)
    .subscribe(resp=>{
      this.showNotification('you diliked the shop : '+nameShop);
      this.getNearredShops();
    },err=>{
      console.log("err",err);
    });
  }

  onLogout(){
    this.authService.logout();
    this.router.navigateByUrl('/login');
  }

}
