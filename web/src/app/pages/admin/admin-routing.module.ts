import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AdminMainComponent} from "./admin-main/admin-main.component";
import {AdminCustomerComponent} from "./admin-customer/admin-customer.component";
import {AdminHotelComponent} from "./admin-hotel/admin-hotel.component";
import {AdminCustomerDetailComponent} from "./admin-customer-detail/admin-customer-detail.component";

const routes: Routes = [
  {path:'', component:AdminMainComponent, children:[
      {path:'customer', component:AdminCustomerComponent},
      {path:'hotel', component:AdminHotelComponent},
      {path:'customer/detail/:userId', component:AdminCustomerDetailComponent},
    ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
