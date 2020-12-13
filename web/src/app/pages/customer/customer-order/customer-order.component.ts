import { Component, OnInit } from '@angular/core';
import differenceInCalendarDays from 'date-fns/differenceInCalendarDays';
import {NzModalService} from "ng-zorro-antd/modal";
import {ActivatedRoute, Router} from "@angular/router";
import {OrderService} from "../../../service/order.service";
import {NzMessageService} from "ng-zorro-antd/message";

@Component({
  selector: 'app-customer-order',
  templateUrl: './customer-order.component.html',
  styleUrls: ['./customer-order.component.css']
})
export class CustomerOrderComponent implements OnInit {
  hotelName: string | null = "";
  address: string | null = "";
  phone: string | null = "";
  roomName: string | null = "";
  roomIntroduction: string | null = "";
  roomPrice: string | null = "";
  hotelId = 0;
  typeId = 0;

  constructor(
    private modal: NzModalService,
    private route: ActivatedRoute,
    private orderService: OrderService,
    private router: Router,
    private msg:NzMessageService
  ) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.hotelId = Number(params['hotelId']);
      this.hotelName = String(params['name']);
      this.address = String(params['address']);
      this.phone = String(params['phone']);
      this.roomName = String(params['roomName']);
      this.roomIntroduction = String(params['roomIntroduction']);
      this.roomPrice = String(params['roomPrice']);
      this.typeId = Number(params['typeId']);
    });
  }

  showConfirm(): void {
    this.modal.confirm({
      nzTitle: '你确定要下单了吗？',
      nzContent: '<b>请确认信息准确无误，下单后不可修改</b>',
      nzOkText: '确认',
      nzOkType: 'primary',
      nzOnOk: () => {
        this.orderService.addOrder(this.typeId).subscribe(result => {
          if (result.code==200){
            this.router.navigate(['/customer/history']);
          }else {
            this.msg.error(result.message);
          }
        });
      },
      nzCancelText: '取消',
      nzOnCancel: () => console.log('Cancel')
    });
  }
}
