import { MerchantRoutingModule } from './merchant-routing.module';
import { MerchantMainComponent } from './merchant-main/merchant-main.component';
import { MerchantInfoComponent } from './merchant-info/merchant-info.component';
// 当前订单
import { MerchantOrderComponent } from './merchant-order/merchant-order.component';
// 房间管理
import { MerchantRoomComponent } from './merchant-room/merchant-room.component';
// 历史订单
import { MerchantHistoryComponent } from './merchant-history/merchant-history.component';
/*---------------------分界线-------------------*/
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {NzLayoutModule} from 'ng-zorro-antd/layout';
import {NzBreadCrumbModule} from 'ng-zorro-antd/breadcrumb';
import {NzMenuModule} from 'ng-zorro-antd/menu';
import {NzDescriptionsModule} from 'ng-zorro-antd/descriptions';
import {NzFormModule} from 'ng-zorro-antd/form';
import {NzTabsModule} from 'ng-zorro-antd/tabs';
import {NzListModule} from 'ng-zorro-antd/list';
import {NzProgressModule} from 'ng-zorro-antd/progress';
import {NzButtonModule} from 'ng-zorro-antd/button';
import {NzIconModule} from 'ng-zorro-antd/icon';
import {NzDrawerModule} from 'ng-zorro-antd/drawer';
import {NzSelectModule} from 'ng-zorro-antd/select';
import {NzInputModule} from 'ng-zorro-antd/input';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {NzUploadModule} from 'ng-zorro-antd/upload';
import {NzCommentModule} from 'ng-zorro-antd/comment';
import {NzCollapseModule} from 'ng-zorro-antd/collapse';
import {NzRateModule} from 'ng-zorro-antd/rate';

@NgModule({
  declarations: [MerchantMainComponent, MerchantInfoComponent, MerchantOrderComponent, MerchantRoomComponent, MerchantHistoryComponent],
  imports: [
    CommonModule,
    MerchantRoutingModule,
    NzLayoutModule,
    NzBreadCrumbModule,
    NzMenuModule,
    NzDescriptionsModule,
    NzFormModule,
    NzTabsModule,
    NzListModule,
    NzProgressModule,
    NzButtonModule,
    NzIconModule,
    NzDrawerModule,
    NzSelectModule,
    NzInputModule,
    FormsModule,
    ReactiveFormsModule,
    NzUploadModule,
    NzCommentModule,
    NzCollapseModule,
    NzRateModule,
  ]
})
export class MerchantModule { }
