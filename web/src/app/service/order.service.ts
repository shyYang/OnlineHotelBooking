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
export class OrderService {
  private addOrderUrl = '/customer/order_room';
  private getOrderByCustomerIdUrl = '/customer/list_all_order';
  private commentUrl = '/customer/comment';

  constructor(
    private http: HttpClient,
  ) { }

  addOrder(typeId:number):Observable<Result>{
    let url = this.addOrderUrl + '?typeId=' + typeId;
    // let url = '/assets/data/success.json';

    return this.http.get<Result>(url)
      .pipe(
        catchError(this.handleError)
      );
  }

  getOrderByCustomerId():Observable<Result>{
    // let url = '/assets/data/OrderList.json';

    // return this.http.get<Result>(url)
    //   .pipe(
    //     catchError(this.handleError)
    //   );
    return this.http.get<Result>(this.getOrderByCustomerIdUrl)
      .pipe(
        catchError(this.handleError)
      );
  }

  comment(data:any):Observable<Result>{
    // let url = '/assets/data/success.json';
    // return this.http.get<Result>(url)
    //   .pipe(catchError(this.handleError));

    return this.http.post<Result>(this.commentUrl,data,httpOptions)
      .pipe(catchError(this.handleError));
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error); // log to console instead

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
}
