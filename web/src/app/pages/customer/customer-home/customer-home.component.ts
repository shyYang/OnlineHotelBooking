import { Component, OnInit } from '@angular/core';
import {HotelService} from "../../../service/hotel.service";
import {Hotel} from "../../../share/common.model";
import {NgModel} from "@angular/forms";
import {Router} from "@angular/router";

@Component({
  selector: 'app-customer-home',
  templateUrl: './customer-home.component.html',
  styleUrls: ['./customer-home.component.css']
})
export class CustomerHomeComponent implements OnInit {
  array = ["/assets/homeTop_6.jpg", "/assets/homeTop_3.jpg", "/assets/homeTop_4.jpg", "/assets/homeTop_5.jpg"];
  top3Hotels: Hotel[] = [];
  searchContent: NgModel | undefined;

  constructor(
    private hotelService: HotelService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.hotelService.getTop5Hotels().subscribe(res => {
      this.top3Hotels = res.data.slice(0,3);
      for (let item of this.top3Hotels){
        if (item.introduction.length>20){
          item.introduction = item.introduction.slice(0,17)+'...';
        }
      }
    });
  }

  search():void{
    this.router.navigate(['/customer/search'],
      {
        queryParams: {
          'content':this.searchContent
        }}
    );
  }
}
