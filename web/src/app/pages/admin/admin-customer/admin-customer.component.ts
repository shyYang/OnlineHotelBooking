import { Component, OnInit } from '@angular/core';
import {AdminService} from "../../../service/admin.service";
import {User} from "../../../share/common.model";

@Component({
  selector: 'app-admin-customer',
  templateUrl: './admin-customer.component.html',
  styleUrls: ['./admin-customer.component.css']
})
export class AdminCustomerComponent implements OnInit {
  List:User[] = [];
  pageIndex = 1;
  pageSize = 10;
  total = 0;
  customerList: any;

  constructor(
    private adminService:AdminService
  ) { }

  ngOnInit(): void {
    this.adminService.getUsers().subscribe(res => {
      if (res.code == 200) {
        for (let item of res.data) {
          if (item.role == 2) this.List.push(item);
        }
      }
      this.total = this.List.length;
      this.customerList = this.List.slice(0, Math.min(this.pageSize, this.total));
    });

  }

  changePage(): void{
    this.customerList = this.List.slice((this.pageIndex - 1) * this.pageSize, Math.min(this.total, this.pageIndex * this.pageSize));
  }


}
