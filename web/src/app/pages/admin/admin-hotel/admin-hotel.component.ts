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
  List:any[] = [];
  pageIndex = 1;
  pageSize = 3;
  total = 0;
  hotelList: any;

  constructor(
    private adminService:AdminService,
    private msg:NzMessageService,
    private router:Router
  ) { }

  ngOnInit(): void {
    this.adminService.getSellers().subscribe(res => {
      if (res.code == 200) {
        this.List = res.data;
      }
      this.total = this.List.length;
      this.hotelList = this.List.slice(0, Math.min(this.pageSize, this.total));
    });

  }

  changePage(): void{
    this.hotelList = this.List.slice((this.pageIndex - 1) * this.pageSize, Math.min(this.total, this.pageIndex * this.pageSize));
  }

  deleteSeller(hotelId:number,index:number):void{
    this.adminService.deleteSeller(hotelId).subscribe(res => {
      if (res.code==200){
        this.msg.success("删除成功");
        if (index>-1){
          this.List.splice(index,1);
          this.total--;
          this.pageIndex=1;
          this.changePage();
        }
      }else {
        this.msg.error("删除失败，请稍后再试");
      }
    });
  }
}
