import { Component, OnInit } from '@angular/core';
import {HotelService} from "../../../service/hotel.service";
import {ActivatedRoute, Router} from "@angular/router";
import {NgModel} from "@angular/forms";
import {Hotel} from "../../../share/common.model";

@Component({
  selector: 'app-customer-search',
  templateUrl: './customer-search.component.html',
  styleUrls: ['./customer-search.component.css']
})
export class CustomerSearchComponent implements OnInit {
  pageIndex = 1;
  pageSize = 10;
  total = 0;
  hotelList: Hotel[] = [];
  searchContent: NgModel | undefined;
  hotels: Hotel[] = [];

  constructor(
    private hotelService: HotelService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      if (params){
        this.searchContent = params['content'];
        if (this.searchContent){
          this.hotelService.searchHotels(String(this.searchContent)).subscribe(hotels => {
            this.hotels = hotels.data;
            this.total = this.hotels.length;
            this.hotelList = this.hotels.slice(0, Math.min(this.pageSize, this.total));
          });
        }
      }
    });
  }

  changePage(): void{
    this.hotelList = this.hotels.slice((this.pageIndex - 1) * this.pageSize, Math.min(this.total, this.pageIndex * this.pageSize));
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
