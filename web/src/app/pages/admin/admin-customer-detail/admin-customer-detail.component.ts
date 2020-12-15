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
  orders:any[] = [];
  ordersTime:any[] = [];
  comments:any[]=[];
  commentsTime:any[] = [];

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
          for (let item of this.orders){
            this.ordersTime.push(String(new Intl.DateTimeFormat('zh-CN', { timeZone: "Asia/Shanghai" }).format(new Date(item.time))));
          }
        }
      });
      this.adminService.getCommentsByUser(this.userId).subscribe(result => {
        if (result.code==200){
          this.comments = result.data;
          for (let item of this.comments){
            this.commentsTime.push(String(new Intl.DateTimeFormat('zh-CN', { timeZone: "Asia/Shanghai" }).format(new Date(item.time))));
          }
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

  deleteOrder(orderId:number,index:number):void {
    this.adminService.deleteOrder(orderId).subscribe(res => {
      if (res.code==200){
        this.msg.success("删除成功");
        if(index > -1) {
          this.orders.splice(index,1);
          this.ordersTime.splice(index,1);
        }
      }else {
        this.msg.error("删除失败，请稍后再试");
      }
    });
  }

  deleteComment(commentId:number,index:number):void {
    this.adminService.deleteComment(commentId).subscribe(res => {
      if (res.code==200){
        this.msg.success("删除成功");
        if(index > -1) {
          this.comments.splice(index,1);
          this.commentsTime.splice(index,1);
        }
      }else {
        this.msg.error("删除失败，请稍后再试");
      }
    });
  }
}
