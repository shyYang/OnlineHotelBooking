import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { catchError, map, tap } from "rxjs/operators";
import {Observable, of} from "rxjs";
import {Hotel, Result, UserOfCustomer} from "../share/common.model";

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
  })
};

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  private getCustomerInfoUrl = '/customer/user_information';
  private changeCustomerInfoUrl = '/customer/change_user_information';
  private changePasswordUrl = '/user/change_password';
  private rechargeUrl = '/customer/recharge';

  constructor(
    private http: HttpClient,
  ) { }

  getCustomerInfo():Observable<Result>{
    // let url = '/assets/data/userOfCustomer.json';
    // return this.http.get<Result>(url)
    //   .pipe(
    //     catchError(this.handleError)
    //   );
    return this.http.get<Result>(this.getCustomerInfoUrl)
      .pipe(
        catchError(this.handleError)
      );
  }

  changeCustomerInfo(data:any):Observable<Result>{
    // let url = '/assets/data/updateCustomerInfo.json';
    // return this.http.get<Result>(url)
    //   .pipe(
    //     catchError(this.handleError)
    //   );
    return this.http.post<Result>(this.changeCustomerInfoUrl,data,httpOptions)
      .pipe(
        catchError(this.handleError)
      );
  }

  changePassword(data:any):Observable<Result>{
    // let url = '/assets/data/success.json';
    // return this.http.get<Result>(url)
    //   .pipe(
    //     catchError(this.handleError)
    //   );
    return this.http.post<Result>(this.changePasswordUrl,data,httpOptions)
      .pipe(
        catchError(this.handleError)
      );
  }

  recharge(data:number):Observable<Result>{
    let url = this.rechargeUrl + '?money=' + data;
    // let url = '/assets/data/recharge.json';
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
