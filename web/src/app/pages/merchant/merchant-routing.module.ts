import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MerchantMainComponent } from './merchant-main/merchant-main.component';
import { MerchantInfoComponent } from './merchant-info/merchant-info.component';
import { MerchantOrderComponent } from './merchant-order/merchant-order.component';
import { MerchantRoomComponent } from './merchant-room/merchant-room.component';
import { MerchantHistoryComponent } from './merchant-history/merchant-history.component';

const routes: Routes = [
  {path: '', component: MerchantMainComponent, children: [
      {path: 'info', component: MerchantInfoComponent},
      {path: 'order', component: MerchantOrderComponent},
      {path: 'room', component: MerchantRoomComponent},
      {path: 'orderHistory', component: MerchantHistoryComponent},
    ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MerchantRoutingModule { }
