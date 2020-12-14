import { Component, OnInit } from '@angular/core';
import {HotelService} from '../../../service/hotel.service';
import {NzMessageService} from 'ng-zorro-antd/message';
import {NzModalService} from 'ng-zorro-antd/modal';

@Component({
  selector: 'app-merchant-order',
  templateUrl: './merchant-order.component.html',
  styleUrls: ['./merchant-order.component.css']
})
export class MerchantOrderComponent implements OnInit {
  hotelId = 0;
  rating = 0;
  list: any[] = [];

  constructor(
    private hotelService: HotelService,
    private msg: NzMessageService,
    private modal: NzModalService,
  ) { }

  ngOnInit(): void {
    this.hotelService.getOrdersById().subscribe(res => {
      for (const i of res.data){
        if (i.status !== 1){
          this.list.push(i);
        }
      }
      console.log(this.list);
    });
  }

  changeOrderStatus(orderid: number, changeStatus: number): void{
    // 传来前景的状态
    if (changeStatus === 0) {
      {
        this.hotelService.cancelOrder(
          orderid,
        //   {
        //   orderId: orderid,
        //   // status: changeStatus
        // }
        ).subscribe(result =>{
          if (result.code === 200){
            this.modal.success({
              nzTitle: "成功取消订单",
              nzContent: result.message,
            });
          }else {
            this.modal.error({
              nzTitle: "取消订单失败",
              nzContent: result.message,
            });
          }
        });
      }
    } else if (changeStatus === 1) {
      {
        this.hotelService.finishOrder(
          orderid,
      //     {
      //   orderId: orderid,
      //   // status: changeStatus
      // }
      ).subscribe(result =>
      {
        if (result.code === 200){
          this.modal.success({
            nzTitle: "订单结束",
            nzContent: result.message,
          });
        }else {
          this.modal.error({
            nzTitle: "订单结束失败",
            nzContent: result.message,
          });
        }
      });
      }
    } else if (changeStatus === 2) {
    } else if (changeStatus === 3) {
      {
      }
    }
  }
}
