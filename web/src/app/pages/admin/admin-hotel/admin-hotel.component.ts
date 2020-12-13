import { Component, OnInit } from '@angular/core';
import {User} from "../../../share/common.model";
import {AdminService} from "../../../service/admin.service";
import {NzMessageService} from "ng-zorro-antd/message";
import {Router} from "@angular/router";

@Component({
  selector: 'app-admin-hotel',
  templateUrl: './admin-hotel.component.html',
  styleUrls: ['./admin-hotel.component.css']
})
export class AdminHotelComponent implements OnInit {
  List:User[] = [];
  pageIndex = 1;
  pageSize = 10;
  total = 0;
  hotelList: any;

  constructor(
    private adminService:AdminService,
    private msg:NzMessageService,
    private router:Router
  ) { }

  ngOnInit(): void {
    this.adminService.getUsers().subscribe(res => {
      if (res.code == 200) {
        for (let item of res.data) {
          if (item.role == 1) this.List.push(item);
        }
      }
      this.total = this.List.length;
      this.hotelList = this.List.slice(0, Math.min(this.pageSize, this.total));
    });

  }

  changePage(): void{
    this.hotelList = this.List.slice((this.pageIndex - 1) * this.pageSize, Math.min(this.total, this.pageIndex * this.pageSize));
  }

  deleteUser(userId:number):void{
    this.adminService.deleteUser(userId).subscribe(res => {
      if (res.code==200){
        this.msg.success("删除成功");
        this.router.navigate(['/admin/hotel'])
      }else {
        this.msg.error("删除失败，请稍后再试");
      }
    });
  }
}
