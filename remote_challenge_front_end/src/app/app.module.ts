import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {HttpClientModule} from '@angular/common/http';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { Routes, RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { AuthentificationService } from './services/authentification.service';
import { PreferredShopComponent } from './preferred-shop/preferred-shop.component';
import { RegistrationService } from './services/registration.service';
import { HomeComponent } from './home/home.component';
import { IndexComponent } from './index/index.component';
import { SnotifyModule, SnotifyService, ToastDefaults } from 'ng-snotify';
import { FooterComponent } from './footer/footer.component';


const appRoutes:Routes=[
  {path:"login",component:LoginComponent},
  {path:"registration",component:RegistrationComponent},
  {path:"prefered",component:PreferredShopComponent},
  {path:"home",component:HomeComponent},
  {path:"index",component:IndexComponent},
  {path:"",redirectTo:"index",pathMatch:"full"},
];

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegistrationComponent,
    PreferredShopComponent,
    HomeComponent,
    IndexComponent,
    FooterComponent,

  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes),
    FormsModule,
    SnotifyModule
  ],
  providers: [AuthentificationService,RegistrationService,
    { provide: 'SnotifyToastConfig', useValue: ToastDefaults},
    SnotifyService],
  bootstrap: [AppComponent]
})
export class AppModule { }
