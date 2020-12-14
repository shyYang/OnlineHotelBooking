import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';
import {Observable, of} from 'rxjs';
import {Hotel, RoomType, Comment, Result, Room} from '../share/common.model';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
  })
};
@Injectable({
  providedIn: 'root'
})
export class RoomService{
  private addRoomUrl = '/room/add';
  private deleteRoomUrl = '/room/delete';
  private updateRoomInfoUrl = '/room/update';
  private roomInfoUrl = 'room/detail';
  private listRoomUrl = 'room/list';

  constructor(
    private http: HttpClient,
  ) { }
  addRoom(room: any): Observable<Result>{
    // let url = '/assets/data/success.json';
    // return this.http.get<Result>(url)
    //   .pipe(
    //     catchError(this.handleError)
    //   );
    return this.http.post<Result>(this.addRoomUrl,room,httpOptions).pipe(
      catchError(this.handleError)
    );
  }
  deleteRoom(rooId: number): Observable<Result>{
    let url = '/assets/data/success.json';
    return this.http.get<Result>(url)
      .pipe(
        catchError(this.handleError)
      );
    // return this.http.post<Result>(this.deleteRoomUrl,roomId,httpOptions).pipe(
    //   catchError(this.handleError)
    // );
  }
  updateRoomInfo(room: Room): Observable<Result>{
    let url = '/assets/data/success.json';
    return this.http.get<Result>(url)
      .pipe(
        catchError(this.handleError)
      );
    // return this.http.post<Result>(updateRoomInfoUrl,room,httpOptions).pipe(
    //   catchError(this.handleError)
    // );
  }
  roomInfoById(roomId: number): Observable<Result>{
    // let url = this.listRoomUrl + '?roomId=' + roomId;
    let url = '/assets/data/Room.json';

    return this.http.get<Result>(url)
      .pipe(
        catchError(this.handleError)
      );
  }
  listRoomById(hotelId: number): Observable<Result>{
    // let url = this.listRoomUrl + '?hotelId=' + hotelId;
    let url = '/assets/data/RoomList.json';

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
