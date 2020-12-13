import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';
import {Observable, of} from 'rxjs';
import {Hotel, RoomType, Comment, Result} from '../share/common.model';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
  })
};
@Injectable({
  providedIn: 'root'
})
export class RoomTypeService {
  private addUrl = '/room/type/add';
  private deleteUrl = '/room/type/delete';
  private updateUrl = '/room/type/update';
  private infoUrl = '/room/type/detail';
  private roomInfoUrl = '/room/type/roomDetail';
  private listUrl = '/room/type/list';
  constructor(
    private http: HttpClient,
  ) { }
  addRoomType(roomType: any): Observable<Result>{
    let url = '/assets/data/success.json';
    return this.http.get<Result>(url)
      .pipe(
        catchError(this.handleError)
      );
    // return this.http.post<Result>(this.addRoomUrl,roomType,httpOptions).pipe(
    //   catchError(this.handleError)
    // );
  }
  deleteRoomType(typeId: number): Observable<Result>{
    let url = '/assets/data/success.json';
    return this.http.get<Result>(url)
      .pipe(
        catchError(this.handleError)
      );
    // return this.http.post<Result>(this.deleteRoomUrl,typeId,httpOptions).pipe(
    //   catchError(this.handleError)
    // );
  }
  updateRoomTypeInfo(roomType: any): Observable<Result>{
    let url = '/assets/data/success.json';
    return this.http.get<Result>(url)
      .pipe(
        catchError(this.handleError)
      );
    // return this.http.post<Result>(updateRoomInfoUrl,roomType,httpOptions).pipe(
    //   catchError(this.handleError)
    // );
  }
  roomTypeInfoById(roomId: number): Observable<Result>{
    // let url = this.listRoomUrl + '?roomId=' + roomId;
    let url = '/assets/data/RoomType.json';

    return this.http.get<Result>(url)
      .pipe(
        catchError(this.handleError)
      );
  }
  listRoomTypeById(hotelId: number): Observable<Result>{
    // let url = this.listRoomUrl + '?hotelId=' + hotelId;
    let url = '/assets/data/RoomTypeList.json';

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
