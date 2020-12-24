import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../../service/auth.service";
import {Router} from "@angular/router";
import {NzMessageService} from "ng-zorro-antd/message";

@Component({
  selector: 'app-admin-main',
  templateUrl: './admin-main.component.html',
  styleUrls: ['./admin-main.component.css']
})
export class AdminMainComponent implements OnInit {
  text='admin';
  color='#f56a00';

  constructor(
    private authService: AuthService,
    private router:Router,
    private msg:NzMessageService
  ) { }

  ngOnInit(): void {
  }

  logout():void{
    this.authService.logout().subscribe(res => {
      if (res.code!=200){
        this.msg.error("退出失败，请稍后再试");
      }else this.router.navigate(['/auth/admin-login']);
    });
  }

}
