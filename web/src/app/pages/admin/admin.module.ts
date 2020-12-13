import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminMainComponent } from './admin-main/admin-main.component';
import {NzLayoutModule} from "ng-zorro-antd/layout";
import {NzAvatarModule} from "ng-zorro-antd/avatar";
import {NzMenuModule} from "ng-zorro-antd/menu";
import {RouterModule} from "@angular/router";
import {AdminRoutingModule} from "./admin-routing.module";
import { AdminCustomerComponent } from './admin-customer/admin-customer.component';
import {NzBreadCrumbModule} from "ng-zorro-antd/breadcrumb";
import {NzInputModule} from "ng-zorro-antd/input";
import {NzButtonModule} from "ng-zorro-antd/button";
import {NzIconModule} from "ng-zorro-antd/icon";
import {NzEmptyModule} from "ng-zorro-antd/empty";
import {NzPaginationModule} from "ng-zorro-antd/pagination";
import {NzListModule} from "ng-zorro-antd/list";
import { AdminHotelComponent } from './admin-hotel/admin-hotel.component';
import { AdminCustomerDetailComponent } from './admin-customer-detail/admin-customer-detail.component';
import {NzDescriptionsModule} from "ng-zorro-antd/descriptions";
import {NzCollapseModule} from "ng-zorro-antd/collapse";
import {NzCommentModule} from "ng-zorro-antd/comment";
import {NzFormModule} from "ng-zorro-antd/form";
import {FormsModule} from "@angular/forms";
import {NzTypographyModule} from "ng-zorro-antd/typography";
import {NzDividerModule} from "ng-zorro-antd/divider";
import {NzTabsModule} from "ng-zorro-antd/tabs";
import {NzProgressModule} from "ng-zorro-antd/progress";
import {NzRateModule} from "ng-zorro-antd/rate";
import {NzSkeletonModule} from "ng-zorro-antd/skeleton";



@NgModule({
  declarations: [AdminMainComponent, AdminCustomerComponent, AdminHotelComponent, AdminCustomerDetailComponent],
  imports: [
    CommonModule,
    AdminRoutingModule,
    NzLayoutModule,
    NzAvatarModule,
    NzMenuModule,
    RouterModule,
    NzBreadCrumbModule,
    NzInputModule,
    NzButtonModule,
    NzIconModule,
    NzEmptyModule,
    NzPaginationModule,
    NzListModule,
    NzDescriptionsModule,
    NzCollapseModule,
    NzCommentModule,
    NzFormModule,
    FormsModule,
    NzTypographyModule,
    NzDividerModule,
    NzTabsModule,
    NzProgressModule,
    NzRateModule,
  ]
})
export class AdminModule { }
