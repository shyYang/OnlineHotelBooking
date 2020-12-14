import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {HotelService} from "../../../service/hotel.service";
import {Hotel, RoomType, Comment} from "../../../share/common.model";

@Component({
  selector: 'app-customer-hotel-detail',
  templateUrl: './customer-hotel-detail.component.html',
  styleUrls: ['./customer-hotel-detail.component.css']
})
export class CustomerHotelDetailComponent implements OnInit {
  hotelId: number | undefined;
  hotel: Hotel | undefined;
  rooms: RoomType[] = [];
  comments: any[] = [];
  formatOne = (percent: number) => `${percent} %空余`;
  times:string[] = [];

  constructor(
    private route: ActivatedRoute,
    private hotelService: HotelService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.hotelId = Number(params.get('hotelId'));
      this.hotelService.getHotelById(this.hotelId).subscribe(res => {
        this.hotel = res.data;
      });
      this.hotelService.getRoomTypeById(this.hotelId).subscribe(rooms => {
        this.rooms = rooms.data;
        console.log(this.rooms);
        for (let item of this.rooms){
          item.freeNumber = Number((item.freeNumber / item.number * 100).toFixed(2));
        }
      });
      this.hotelService.getCommentsById(this.hotelId).subscribe(comments => {
        this.comments = comments.data;
        console.log(this.comments);
        for (let item of this.comments){
          this.times.push(String(new Intl.DateTimeFormat('zh-CN', { timeZone: "Asia/Shanghai" }).format(new Date(item.time))));
        }
      });
    });
  }

  goToOrder(index:number):void{
    this.router.navigate(['/customer/order'],
      {
        queryParams: {
          'hotelId':this.hotelId,
          'name':this.hotel?.hotelName,
          'address':this.hotel?.address,
          'phone':this.hotel?.phone,
          'roomName':this.rooms[index].name,
          'roomIntroduction':this.rooms[index].introduction,
          'roomPrice':this.rooms[index].price,
          'typeId': this.rooms[index].typeId
      }}
      );
  }
}
