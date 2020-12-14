import { Component, OnInit } from '@angular/core';
import {HotelService} from '../../../service/hotel.service';
import {NzMessageModule, NzMessageService} from 'ng-zorro-antd/message';

@Component({
  selector: 'app-merchant-history',
  templateUrl: './merchant-history.component.html',
  styleUrls: ['./merchant-history.component.css']
})
export class MerchantHistoryComponent implements OnInit {
  hotelId = 0;
  rating = 0;
  orderList: any[] = [];

  constructor(
    private hotelService: HotelService,
    private msg: NzMessageService,
  ) { }

  ngOnInit(): void {
    this.hotelService.getOrdersById().subscribe(res => {
      for (const i of res.data){
        if (i.status === 1) {
          i.statusName = i.orderId+ "(已完成)";
          this.orderList.push(i);
        } else if (i.status === 2){
          i.statusName = i.orderId+"(已取消)";
          this.orderList.push(i);
        }
      }
      console.log(this.orderList);
    });
  }
}
