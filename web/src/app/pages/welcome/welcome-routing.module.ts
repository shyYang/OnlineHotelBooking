import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {WelcomeHelloComponent} from "./welcome-hello/welcome-hello.component";
import {WelcomeMainComponent} from "./welcome-main/welcome-main.component";

const routes: Routes = [
  { path: '', component: WelcomeMainComponent },
  { path: 'hello', component: WelcomeHelloComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class WelcomeRoutingModule { }
