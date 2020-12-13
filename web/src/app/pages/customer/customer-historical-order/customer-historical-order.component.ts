import { Component, OnInit } from '@angular/core';
import {OrderService} from "../../../service/order.service";
import {NzMessageService} from "ng-zorro-antd/message";

@Component({
  selector: 'app-customer-historical-order',
  templateUrl: './customer-historical-order.component.html',
  styleUrls: ['./customer-historical-order.component.css']
})
export class CustomerHistoricalOrderComponent implements OnInit {
  inputValue = '';
  rating = 0;
  list: any;

  handleSubmit(index:number): void {
    const content = this.inputValue;
    this.orderService.comment({
      content: content,
      rating:this.rating,
      order_id:this.list[index].orderId
    }).subscribe(res => {
      if (res.code==200){
        this.list[index].content=content;
        this.list[index].commentTime = new Date();
        this.list[index].rating = this.rating;
        this.inputValue='';
        this.rating=0;
        this.msg.success("评论成功");
      }else {
        this.msg.error(res.message);
      }
    });
  }

  constructor(
    private orderService: OrderService,
    private msg: NzMessageService
  ) { }

  ngOnInit(): void {
    this.orderService.getOrderByCustomerId().subscribe(res => {
      this.list = res.data;
      for (let item of this.list){
        item.commentTime = new Date(item.commentTime);
      }
    });
  }

}
