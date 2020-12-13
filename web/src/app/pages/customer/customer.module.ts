import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CustomerMainComponent } from './customer-main/customer-main.component';
import {CustomerRoutingModule} from "./customer-routing.module";
import {NzLayoutModule} from "ng-zorro-antd/layout";
import {NzBreadCrumbModule} from "ng-zorro-antd/breadcrumb";
import {NzMenuModule} from "ng-zorro-antd/menu";
import { CustomerHomeComponent } from './customer-home/customer-home.component';
import {NzCarouselModule} from "ng-zorro-antd/carousel";
import {NzAvatarModule} from "ng-zorro-antd/avatar";
import {NzCardModule} from "ng-zorro-antd/card";
import {NzInputModule} from "ng-zorro-antd/input";
import {NzIconModule} from "ng-zorro-antd/icon";
import {NzButtonModule} from "ng-zorro-antd/button";
import {NzTypographyModule} from "ng-zorro-antd/typography";
import { CustomerSettingComponent } from './customer-setting/customer-setting.component';
import {NzDescriptionsModule} from "ng-zorro-antd/descriptions";
import {NzBadgeModule} from "ng-zorro-antd/badge";
import {NzFormModule} from "ng-zorro-antd/form";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {NzCheckboxModule} from "ng-zorro-antd/checkbox";
import { CustomerSearchComponent } from './customer-search/customer-search.component';
import {NzEmptyModule} from "ng-zorro-antd/empty";
import {NzPaginationModule} from "ng-zorro-antd/pagination";
import { CustomerHotelDetailComponent } from './customer-hotel-detail/customer-hotel-detail.component';
import {NzRateModule} from "ng-zorro-antd/rate";
import {NzTabsModule} from "ng-zorro-antd/tabs";
import {NzListModule} from "ng-zorro-antd/list";
import {NzProgressModule} from "ng-zorro-antd/progress";
import { CustomerOrderComponent } from './customer-order/customer-order.component';
import {NzDatePickerModule} from "ng-zorro-antd/date-picker";
import { CustomerHistoricalOrderComponent } from './customer-historical-order/customer-historical-order.component';
import {NzCollapseModule} from "ng-zorro-antd/collapse";
import {NzCommentModule} from "ng-zorro-antd/comment";
import {NzSelectModule} from "ng-zorro-antd/select";
import {NzModalModule} from "ng-zorro-antd/modal";
import {NzInputNumberModule} from "ng-zorro-antd/input-number";



@NgModule({
  declarations: [CustomerMainComponent, CustomerHomeComponent, CustomerSettingComponent, CustomerSearchComponent, CustomerHotelDetailComponent, CustomerOrderComponent, CustomerHistoricalOrderComponent],
  imports: [
    CommonModule,
    CustomerRoutingModule,
    NzLayoutModule,
    NzBreadCrumbModule,
    NzMenuModule,
    NzCarouselModule,
    NzAvatarModule,
    NzCardModule,
    NzInputModule,
    NzIconModule,
    NzButtonModule,
    NzDescriptionsModule,
    NzTypographyModule,
    NzBadgeModule,
    NzFormModule,
    ReactiveFormsModule,
    NzCheckboxModule,
    NzEmptyModule,
    NzPaginationModule,
    NzRateModule,
    FormsModule,
    NzTabsModule,
    NzListModule,
    NzProgressModule,
    NzDatePickerModule,
    NzCollapseModule,
    NzCommentModule,
    NzSelectModule,
    NzModalModule,
    NzInputNumberModule
  ]
})
export class CustomerModule { }
