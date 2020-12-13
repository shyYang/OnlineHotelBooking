import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {AdminService} from "../../../service/admin.service";
import {Order,Comment} from "../../../share/common.model";
import {NzMessageService} from "ng-zorro-antd/message";

@Component({
  selector: 'app-admin-customer-detail',
  templateUrl: './admin-customer-detail.component.html',
  styleUrls: ['./admin-customer-detail.component.css']
})
export class AdminCustomerDetailComponent implements OnInit {
  userId:number=0;
  header = '';
  orders:Order[] = [];
  comments:Comment[]=[];

  constructor(
    private route:ActivatedRoute,
    private adminService: AdminService,
    private msg:NzMessageService,
    private router:Router
  ) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.userId = Number(params.get('userId'));
      this.header = '用户ID:'+this.userId;
      this.adminService.getOrderByUser(this.userId).subscribe(res => {
        if (res.code==200){
          this.orders = res.data;
        }
      });
      this.adminService.getCommentsByUser(this.userId).subscribe(result => {
        if (result.code==200){
          this.comments = result.data;
        }
      });
    });
  }

  deleteUser():void{
    this.adminService.deleteUser(this.userId).subscribe(res => {
      if (res.code==200){
        this.msg.success("删除成功");
        this.router.navigate(['/admin/customer'])
      }else {
        this.msg.error("删除失败，请稍后再试");
      }
    });
  }
}
