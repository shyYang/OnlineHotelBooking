import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NZ_I18N } from 'ng-zorro-antd/i18n';
import { zh_CN } from 'ng-zorro-antd/i18n';
import { registerLocaleData } from '@angular/common';
import zh from '@angular/common/locales/zh';
import {NzButtonModule} from "ng-zorro-antd/button";
import { PageErrorComponent } from './components/page-error/page-error.component';
import {NzPageHeaderModule} from "ng-zorro-antd/page-header";
import {NzLayoutModule} from "ng-zorro-antd/layout";
import {NzModalModule, NzModalService} from "ng-zorro-antd/modal";
import {AuthService} from "./service/auth.service";
import {CustomerService} from "./service/customer.service";
import {AdminService} from "./service/admin.service";
import {NzMessageService} from "ng-zorro-antd/message";
import {HotelService} from "./service/hotel.service";
import {OrderService} from "./service/order.service";
import {MemoryService} from "./service/memory.service";

registerLocaleData(zh);

@NgModule({
  declarations: [
    AppComponent,
    PageErrorComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    NzButtonModule,
    NzPageHeaderModule,
    NzLayoutModule,
    NzModalModule
  ],
  exports: [
  ],
  providers: [
    { provide: NZ_I18N, useValue: zh_CN },
    NzModalService,
    AuthService,
    CustomerService,
    AdminService,
    NzMessageService,
    HotelService,
    OrderService,
    MemoryService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
