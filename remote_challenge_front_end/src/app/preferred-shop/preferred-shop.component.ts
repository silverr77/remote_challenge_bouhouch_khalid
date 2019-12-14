import { Component, OnInit } from '@angular/core';
import { AuthentificationService } from '../services/authentification.service';
import { Router } from '@angular/router';
import { SnotifyService } from 'ng-snotify';

@Component({
  selector: 'app-preferred-shop',
  templateUrl: './preferred-shop.component.html',
  styleUrls: ['./preferred-shop.component.css']
})
export class PreferredShopComponent implements OnInit {
  private preferredShops;
  private userLogin=0;
  constructor(private authService:AuthentificationService,private router:Router,private snotifyService:SnotifyService) { }
  ngOnInit() {
    if(localStorage.getItem('token')) this.userLogin=1;
    this.getPreferredShops();
  }

  getPreferredShops(){

    this.authService.getPreferredShops()
    .subscribe(resp=>{
      this.preferredShops=resp;
    },err=>{
      console.log(err);
    })

  }


  showNotification(Msg){
    this.snotifyService.warning(Msg, {
      timeout: 2000,
      showProgressBar: false,
      closeOnClick: false,
      pauseOnHover: true
    });
  }

  onDelete(idShop,nameShop){
    this.authService.deleteShop(idShop)
    .subscribe(resp=>{
      this.showNotification("you deleted shop "+nameShop)
      this.getPreferredShops();
    },err=>{
      console.log(err);
    })
  }
  onLogout(){
    this.authService.logout();
    this.router.navigateByUrl('/login');
  }

}
