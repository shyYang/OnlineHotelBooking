import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { WelcomeHelloComponent } from './welcome-hello/welcome-hello.component';
import { WelcomeMainComponent } from './welcome-main/welcome-main.component';
import {WelcomeRoutingModule} from "./welcome-routing.module";
import {NzLayoutModule} from "ng-zorro-antd/layout";
import {NzMenuModule} from "ng-zorro-antd/menu";
import {NzIconModule} from "ng-zorro-antd/icon";



@NgModule({
  declarations: [WelcomeHelloComponent, WelcomeMainComponent],
  imports: [
    CommonModule,
    WelcomeRoutingModule,
    NzLayoutModule,
    NzMenuModule,
    NzIconModule,
  ],
})
export class WelcomeModule { }
