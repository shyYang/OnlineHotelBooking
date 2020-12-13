import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CustomerLoginComponent } from './customer-login/customer-login.component';
import { MerchantLoginComponent} from './merchant-login/merchant-login.component';
import {CustomerRegisterComponent} from './customer-register/customer-register.component';
import {MerchantRegisterComponent} from './merchant-register/merchant-register.component';
import {AuthMainComponent} from './auth-main/auth-main.component';
import {AdminLoginComponent} from './admin-login/admin-login.component';


const routes: Routes = [
    {path: '', component: AuthMainComponent, children: [
        { path: '', pathMatch: 'full', redirectTo: 'customer-login' },
        { path: 'customer-login' , component: CustomerLoginComponent},
        { path: 'merchant-login', component: MerchantLoginComponent},
        { path: 'customer-register', component: CustomerRegisterComponent},
        { path: 'merchant-register', component: MerchantRegisterComponent},
        { path: 'admin-login', component: AdminLoginComponent},
    ]}
    // example { path: 'example', component: AuthComponent},
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})

export class AuthRoutingModule { }
