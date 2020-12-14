import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { catchError, map, tap } from "rxjs/operators";
import {Observable, of} from "rxjs";
import {Hotel, RoomType, Comment, Result} from "../share/common.model";

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
  })
};

@Injectable({
  providedIn: 'root'
})
export class HotelService {
  // 列出所有商家
  private getAllHotelsUrl = '/hotel/list_all_hotel';
  // 列出top5的商家
  private getTop5HotelsUrl = '/hotel/list_top_hotel';
  // 获取特定id的商家
  private getHotelByIdUrl = '/hotel/find_hotel';
  // 获取指定id的房间类型
  private getRoomByIdUrl = '/hotel/find_room_type';
  private getRoomTypeByIdUrl = '/hotel/find_room_type';
  private getCommentsByIdUrl = '/hotel/list_comments_of_hotel';
  // 搜索酒店
  private searchHotelUrl = '/hotel/search_hotel';
  private cancelOrderUrl = '/hotel/cancel_order';
  private finishOrederUrl = '/hotel/finish_order';
  constructor(
    private http: HttpClient,
  ) { }

  getAllHotels(): Observable<Result>{
    // return this.http.get<Hotel[]>(this.getAllHotelsUrl)
    //   .pipe(
    //     catchError(this.handleError<Hotel[]>('getAllHotels', []))
    //   );

    let url = '/assets/data/HotelList.json';
    return this.http.get<Result>(url)
      .pipe(
        catchError(this.handleError)
      );
  }

  getTop5Hotels(): Observable<Result>{
    return this.http.get<Result>(this.getTop5HotelsUrl)
      .pipe(
        catchError(this.handleError)
      );

    // let url = '/assets/data/HotelList.json';
    // return this.http.get<Result>(url)
    //   .pipe(
    //     catchError(this.handleError)
    //   );
  }


  searchHotels(hotelName: string):Observable<Result>{
    let url = this.searchHotelUrl + 'hotelName=' + hotelName;
    // var url='';
    // if (hotelName=='11') url = '/assets/data/HotelList1.json';
    // else url = '/assets/data/HotelList.json';
    return this.http.get<Result>(url)
      .pipe(
        catchError(this.handleError)
      );
  }


  getHotelById(hotelId:number): Observable<Result>{
    let url = this.getHotelByIdUrl + '?hotelId=' + hotelId;
    // let url = '/assets/data/Hotel.json';

    return this.http.get<Result>(url)
      .pipe(
        catchError(this.handleError)
      );
  }

  getHotel(): Observable<Result>{
    let url = this.getHotelByIdUrl ;
    // let url = '/assets/data/Hotel.json';

    return this.http.get<Result>(url)
      .pipe(
        catchError(this.handleError)
      );
  }

  getRoomById(): Observable<Result>{
    let url = this.getRoomTypeByIdUrl;
    // let url = '/assets/data/RoomList.json';

    return this.http.get<Result>(url)
      .pipe(
        catchError(this.handleError)
      );
  }


  getRoomType():Observable<Result>{
    let url = this.getRoomTypeByIdUrl;
    return this.http.get<Result>(url).pipe(
      catchError(this.handleError)
    );
  }

  getRoomTypeById(hotelId: number):Observable<Result>{
    // let url = '/assets/data/RoomTypeList.json';
    let url = this.getRoomTypeByIdUrl + '?hotelId=' + hotelId;
    return this.http.get<Result>(url).pipe(
      catchError(this.handleError)
    );
  }

  getOrdersById(): Observable<Result>{
    let url = '/hotel/find_order_information_status';

    return this.http.get<Result>(url)
      .pipe(
        catchError(this.handleError)
      );
  }
  getCommentsById(hotelId:number): Observable<Result>{
    let url = this.getCommentsByIdUrl + '?hotelId=' + hotelId;
    // let url = '/assets/data/CommentList.json';

    return this.http.get<Result>(url)
      .pipe(
        catchError(this.handleError)
      );
  }

  finishOrder(orderId: number): Observable<Result>{
    let url = this.finishOrederUrl + "?orderId=" + orderId;

    return this.http.get<Result>(url)
      .pipe(
        catchError(this.handleError)
      );
    // return this.http.get<Result>( this.finishOrederUrl, need, httpOptions)
    //   .pipe(
    //     catchError(this.handleError)
    //   );
  }
  cancelOrder(orderId: number): Observable<Result>{
    let url = this.cancelOrderUrl + "?orderId=" + orderId;

    return this.http.get<Result>(url)
      .pipe(
        catchError(this.handleError)
      );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error); // log to console instead

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
}
