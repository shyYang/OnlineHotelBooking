import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {CustomerMainComponent} from "./customer-main/customer-main.component";
import {CustomerHomeComponent} from "./customer-home/customer-home.component";
import {CustomerSettingComponent} from "./customer-setting/customer-setting.component";
import {CustomerSearchComponent} from "./customer-search/customer-search.component";
import {CustomerHotelDetailComponent} from "./customer-hotel-detail/customer-hotel-detail.component";
import {CustomerOrderComponent} from "./customer-order/customer-order.component";
import {CustomerHistoricalOrderComponent} from "./customer-historical-order/customer-historical-order.component";

const routes: Routes = [
  {path:'', component:CustomerMainComponent, children:[
      {path:'home', component:CustomerHomeComponent},
      {path:'setting', component:CustomerSettingComponent},
      {path:'search', component:CustomerSearchComponent},
      {path:'detail/:hotelId', component:CustomerHotelDetailComponent},
      {path:'order', component:CustomerOrderComponent},
      {path:'history', component:CustomerHistoricalOrderComponent},
  ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CustomerRoutingModule { }
