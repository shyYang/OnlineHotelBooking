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
    this.hotelService.getOrdersById(this.hotelId).subscribe(res => {
      for (const i of res.data){
        if ( i.status === 1){
          this.orderList.push(i);
        }
      }
      console.log(this.orderList);
    });
  }
}
