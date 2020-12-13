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
export class AuthService {
  private logoutUrl = '/user/logout';
  private customerLoginUrl = '/user/login';
  private customerSignUpUrl = '/customer/sign_up';
  private hotelSignUpUrl = '/hotel/sign_up';
  private submitCaptchaUrl = '/checkCode';

  constructor(
    private http: HttpClient,
  ) { }

  customerLogin(data:any):Observable<Result>{
    console.log(data);
    return this.http.post<Result>(this.customerLoginUrl,data,httpOptions).pipe(
      catchError(this.handleError)
    );
  }

  customerSignUp(data:any):Observable<Result>{
    console.log(data);
    // let url = '/assets/data/success.json';
    //     // return this.http.get<Result>(url).pipe(
    //     //   catchError(this.handleError)
    //     // );

    return this.http.post<Result>(this.customerSignUpUrl,data,httpOptions).pipe(
      catchError(this.handleError)
    );
  }
  hotelSignUp(data:any):Observable<Result>{
    console.log(data);
    // let url = '/assets/data/success.json';
    //     // return this.http.get<Result>(url).pipe(
    //     //   catchError(this.handleError)
    //     // );

    return this.http.post<Result>(this.hotelSignUpUrl,data,httpOptions).pipe(
      catchError(this.handleError)
    );
  }

  submitCaptcha(data:any):Observable<Result>{
    console.log(data);
    // let url = '/verify';
    //     // return this.http.get<Result>(url).pipe(
    //     //   catchError(this.handleError)
    //     // );

    let url = this.submitCaptchaUrl + '?checkCode=' + data;
    return this.http.get<Result>(url).pipe(
      catchError(this.handleError)
    );
  }

  logout():void{
    this.http.get(this.logoutUrl).pipe(
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
