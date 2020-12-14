import { Component, OnInit } from '@angular/core';
import {AdminService} from "../../../service/admin.service";
import {Customer, User} from "../../../share/common.model";
import {NzMessageService} from "ng-zorro-antd/message";
import {Router} from "@angular/router";

@Component({
  selector: 'app-admin-customer',
  templateUrl: './admin-customer.component.html',
  styleUrls: ['./admin-customer.component.css']
})
export class AdminCustomerComponent implements OnInit {
  customers:Customer[] = [];
  users:User[] = [];
  pageIndex = 1;
  pageSize = 4;
  total = 0;
  customerList: any;

  constructor(
    private adminService:AdminService,
  ) { }

  ngOnInit(): void {
    this.adminService.getUsers().subscribe(res => {
      if (res.code == 200) {
        this.customers = res.data.customers;
        this.users = res.data.users;
      }
      this.total = this.customers.length;
      this.customerList = this.customers.slice(0, Math.min(this.pageSize, this.total));
    });

  }

  changePage(): void{
    this.customerList = this.customers.slice((this.pageIndex - 1) * this.pageSize, Math.min(this.total, this.pageIndex * this.pageSize));
  }

}
