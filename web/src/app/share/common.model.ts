export interface AuthData{
  name:string;
  password:string;
}

export interface Result{
  code:number;
  message:string;
  data:any;
}

export interface User{
  userId: number;
  password: string;
  role: number;
}

export interface UserOfCustomer{
  userId: number;
  username: string;
  gender: string;
  phone: string;
  account: number;
  password: string;
  role: number;//0-管理员 1-商家 2-顾客
}

export interface Comment{
  comment_id: number;
  content: string;
  rating: number;
  user_id: number;
  order_id: number;
  time: Date;
}

export interface Customer{
  userId: number;
  username: string;
  gender: string;
  phone: string;
  account: number;
}

export interface Hotel{
  hotelId: number;
  userId: number;
  hotelName: string;
  address: string;
  phone: string;
  photo: string;
  rating: number;
  guestNumber: number;
  introduction: string;
}

export interface HotelType{
  password: string;
  hotelName: string;
  address: string;
  phone: string;
  photo: string;
  introduction: string;
}

export interface Order{
  order_id: number;
  room_id: number;
  user_id: number;
  status: number;
  time: Date;
  payment: number;
}

export interface Room{
  roomId: number;
  typeId: number;
  roomNumber: number;
  status: number;
}

export interface RoomType{
  hotelId: number;
  price: number;
  photo: string;
  number: number;
  name: string;
  introduction: string;
  typeId: number;
  freeNumber: number;
}
