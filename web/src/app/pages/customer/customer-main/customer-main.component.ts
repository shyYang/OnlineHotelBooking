import { Component, OnInit } from '@angular/core';
import {CustomerService} from "../../../service/customer.service";

@Component({
  selector: 'app-customer-main',
  templateUrl: './customer-main.component.html',
  styleUrls: ['./customer-main.component.css']
})
export class CustomerMainComponent implements OnInit {
  user: any;
  colorList = ['#f56a00', '#7265e6', '#ffbf00', '#00a2ae'];
  text='';
  color='';
  isLogin = false;

  constructor(
    private customerService: CustomerService,
  ) { }

  ngOnInit(): void {
    this.customerService.getCustomerInfo().subscribe(res =>{
      if (res.code==200){
        this.isLogin=true;
        let i=Math.round(this.colorList.length-1);
        this.color=this.colorList[i];
        this.user = res.data;
        this.text = this.user.username;
      }
    })
  }

}
