import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { catchError, map, tap } from "rxjs/operators";
import {Observable, of} from "rxjs";
import {Result} from "../share/common.model";

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
  })
};

@Injectable({
  providedIn: 'root'
})
export class AdminService {
  //列出所有用户的请求url
  private getUsersUrl = '/admin/users';
  //列出所有商家用户的请求url
  private getSellersUrl = '/admin/sellers';
  //列出指定订单记录的请求url
  private getOrderByIdUrl = '/admin/orders';
  //列出指定用户的所有订单的请求url
  private getOrderByUserUrl = '/admin/orders_all';
  //列出指定用户的所有评论信息的请求url
  private getCommentByUserUrl = '/admin/comments_all';
  //列出所有用户的所有评论信息的请求url
  private getAllCommentUrl = '/admin/comments_all';
  //删除指定的消费者用户的请求url
  private deleteCustomerUrl = '/admin/delete_consumer';
  private deleteOrderUrl = '/admin/delete_order';
  private deleteCommentUrl = '/admin/delete_comment';
  //删除指定的商家用户的请求url
  private deleteSellerUrl = '/admin/delete_seller';
  //删除指定的酒店的请求url
  private deleteHotelUrl = '/admin/delete';

  getUsers():Observable<Result>{
    // let url = '/assets/data/UserList.json';
    // return this.http.get<Result>(url)
    //   .pipe(catchError(this.handleError));
    return this.http.get<Result>(this.getUsersUrl)
      .pipe(catchError(this.handleError));
  }

  getOrderByUser(data:number):Observable<Result>{
    let url = this.getOrderByUserUrl + '?userID=' + data;
    // let url = '/assets/data/adOrders.json';
    return this.http.get<Result>(url)
      .pipe(catchError(this.handleError));
  }

  getCommentsByUser(data:number):Observable<Result>{
    let url = this.getCommentByUserUrl + '?userID=' + data;
    // let url = '/assets/data/adComments.json'
    return this.http.get<Result>(url)
      .pipe(catchError(this.handleError));
  }

  deleteUser(customerID:number):Observable<Result>{
    let url = this.deleteCustomerUrl + '?customerID=' + customerID;
    // let url = 'assets/data/success.json';
    return this.http.get<Result>(url)
      .pipe(catchError(this.handleError));
  }

  deleteOrder(orderID:number):Observable<Result>{
    let url = this.deleteOrderUrl + '?orderID=' + orderID;
    // let url = 'assets/data/success.json';
    return this.http.get<Result>(url)
      .pipe(catchError(this.handleError));
  }

  deleteComment(commentID:number):Observable<Result>{
    let url = this.deleteCommentUrl + '?commentID=' + commentID;
    // let url = 'assets/data/success.json';
    return this.http.get<Result>(url)
      .pipe(catchError(this.handleError));
  }

  constructor(
    private http: HttpClient,
  ) { }

  /**
   * Handle Http operation that failed.
   * Let the app continue.
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error); // log to console instead

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

}
