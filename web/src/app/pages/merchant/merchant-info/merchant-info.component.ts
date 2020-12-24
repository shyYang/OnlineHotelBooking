import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {HotelService} from '../../../service/hotel.service';
import {NzModalService} from 'ng-zorro-antd/modal';
import {ActivatedRoute, Router} from '@angular/router';
import {NzMessageService} from 'ng-zorro-antd/message';
import {RoomType, Room} from '../../../share/common.model';
import {NzUploadFile} from 'ng-zorro-antd/upload';
import {Observable, Observer} from 'rxjs';
import {RoomService} from '../../../service/room.service';
import {RoomTypeService} from '../../../service/roomType.service';
import {FileUploadService} from "../../../service/fileUpload.service";


@Component({
  selector: 'app-merchant-info',
  templateUrl: './merchant-info.component.html',
  styleUrls: ['./merchant-info.component.css']
})
export class MerchantInfoComponent implements OnInit {
  loading = false;
  photoAddress?: string;
  avatarUrl?: string;
  action?: string;
  validateForm!: FormGroup;
  updateRoomForm!: FormGroup;
  updateRoomTypeForm!: FormGroup;
  addRoomTypeForm!: FormGroup;
  addRoomForm!: FormGroup;
  updateRoomTypeVisible = false;
  updateRoomVisible = false;
  addRoomVisible = false;
  hotelId: number | undefined;
  typeId: number | undefined;
  roomType: RoomType | undefined;
  room: Room | undefined;
  roomTypes: RoomType[] = [];
  roomName: string[] = [];
  rooms: Room[] = [];
  hotelInfo: any;
  constructor(
    private fb: FormBuilder,
    private hotelService: HotelService,
    private modal: NzModalService,
    private roomTypeService: RoomTypeService,
    private roomService: RoomService,
    private router: ActivatedRoute,
    private msg: NzMessageService,
    private fileUploadService: FileUploadService
  ) { }

  ngOnInit(): void {

    this.hotelService.getHotel().subscribe(result => {
      this.hotelInfo = result.data;
    });
    this.hotelService.getRoomType().subscribe(roomTypes => {
        this.roomTypes = roomTypes.data;
    });
    //看不懂
    // this.hotelService.getRoomById().subscribe(rooms => {
    //   this.rooms = rooms.data;
    //   for (const room of this.rooms){
    //     for (const roomsType of this.roomTypes){
    //       if (roomsType.typeId === room.typeId){
    //         this.roomName.push(roomsType.name);
    //         break;
    //       }
    //     }
    //   }
    //   });
    this.updateRoomTypeForm = this.fb.group({
      price: [null, ],
      name: [null, ],
      introduction: [null, ],
    });
    this.updateRoomForm = this.fb.group({
      typeId: [null , ],
      roomNumber: [null, [Validators.pattern('[0-9]{3,5}')]],
    });
    this.addRoomForm = this.fb.group({
      roomNumber: [null, [Validators.pattern('[0-9]{3,5}'), Validators.required]]
    });
    this.addRoomTypeForm = this.fb.group({
      price: [null, [Validators.required]],
      name: [null, [Validators.required]],
      // number: [null , [Validators.required]],
      introduction: [null, [Validators.required]],
    });
  }
  deleteRoomType(typeId: number): void{
    this.roomTypeService.deleteRoomType(typeId).subscribe(result =>{
      if (result.code != 200) {
        this.modal.error({
          nzTitle: '无法删除该种房型',
          nzContent: result.message,
        });
      } else {
        this.modal.success({
          nzTitle: '成功删除该种房型',
          nzContent: result.message,
        });
        this.roomTypes = this.roomTypes.filter(item => item.typeId !== typeId);
      }
    });
  }
  updateRoomType(roomType: RoomType): void{
    this.photoAddress = '';
    this.roomType = roomType;
    this.updateRoomTypeVisible = true;
  }
  submitUpdateRoomType(): void{
    if (!this.roomType){
      this.modal.error({
        nzTitle: '未选中修改的房型',
      });
      return;
    }
    let mark = true;
    if (this.photoAddress){
      mark = false;
    }
    for (const i in this.updateRoomTypeForm.controls) {
      this.updateRoomTypeForm.controls[i].markAsDirty();
      this.updateRoomTypeForm.controls[i].updateValueAndValidity();
      if (this.updateRoomTypeForm.controls[i].value !== null){
        mark = false;
      }
    }
    if (mark){
      this.modal.warning({
        nzTitle: '没有修改数据',
      });
      return;
    }
    this.roomTypeService.updateRoomTypeInfo({
      hotelId: this.roomType.hotelId,
      typeId: this.roomType.typeId,
      name: this.updateRoomTypeForm.value.name === null ? this.roomType.name : this.updateRoomTypeForm.value.name,
      price: this.updateRoomTypeForm.value.price === null ? this.roomType.price : this.updateRoomTypeForm.value.price,
      photo: this.photoAddress === '' ? this.roomType.photo : this.photoAddress,
      introduction: this.updateRoomTypeForm.value.introduction === null ? this.roomType.introduction : this.updateRoomTypeForm.value.introduction,
    }).subscribe(result =>{
      if (result.code != 200) {
        this.modal.error({
          nzTitle: '修改房型失败',
          nzContent: result.message,
        });
        this.photoAddress = undefined
      } else {
        this.modal.success({
          nzTitle: '修改成功',
          nzContent: result.message,
        });
        this.hotelService.getRoomType().subscribe(roomTypes => {
          this.roomTypes = roomTypes.data;
        });
      }
    });
    ;
  }
  deleteRoom(roomId: number): void{
    this.roomService.deleteRoom(roomId).subscribe(result => {
      if (result.code === 500){
        this.modal.error({
          nzTitle: '无法删除该房间',
          nzContent: "",
        });
      }else if (result.code === 200){
          this.modal.success({
            nzTitle: '成功删除该房间',
            nzContent: result.message,
          });
        }
      }
    );
  }
  updateRoom(room: Room): void{
    this.updateRoomVisible = true;
    this.room = room;
  }
  check(typeId: number): void{
  }
  submitUpdateRoom(): void{
    // 可能不存在修改数据， 存在修改数据但不一定每个都修改了，需要检测
    let test = true;
    if (!this.room){
      this.modal.error({
        nzTitle: '未选中修改的房间',
      });
      return;
    }
    for (const i in this.updateRoomForm.controls) {
      this.updateRoomForm .controls[i].markAsDirty();
      this.updateRoomForm .controls[i].updateValueAndValidity();
      if (this.updateRoomForm.controls[i].value !== null){
        test = false;
      }
    }
    if (test){
      this.modal.warning({
        nzTitle: '没有修改数据',
      });
      return;
    }
    this.roomService.updateRoomInfo({
      roomId: this.room.roomId,
      typeId: this.updateRoomForm.value.typeId === null ? this.room.typeId : this.updateRoomForm.value.typeId,
      roomNumber: this.updateRoomForm.value.roomNumber === null ? this.room.roomNumber : this.updateRoomForm.value.roomNumber,
      status: this.room.status,
    }).subscribe(result => {
      if (result.code === 500){
        this.modal.error({
          nzTitle: '信息有误',
          nzContent: result.message
        });
      }else if (result.code === 200){
        this.modal.success({
          nzTitle: '修改成功',
          nzContent: '',
        });
      }
      this.updateRoomVisible = false;
      this.updateRoomForm.reset();
    });
  }

  addRoomType(): void{
    let mark = true;
    if (!this.photoAddress){
      this.modal.error({
        nzTitle: '图片未上传',
        nzContent: '请先上传图片',
      });
      return;
    }
    for (const i in this.addRoomTypeForm.controls) {
      this.addRoomTypeForm.controls[i].markAsDirty();
      this.addRoomTypeForm.controls[i].updateValueAndValidity();
      if (this.addRoomTypeForm.controls[i].value === null){ mark = false; }
    }
    if (!mark) {return;}
    this.roomTypeService.addRoomType({
      hotelId: this.hotelId,
      price: this.addRoomTypeForm.value.price,
      name: this.addRoomTypeForm.value.name,
      photo: this.photoAddress,
      introduction: this.addRoomTypeForm.value.introduction,
      number: 0,
      freeNumber: this.addRoomTypeForm.value.freeNumber,
    }).subscribe(result => {
      if (result.code != 200){
        this.modal.error({
          nzTitle: '信息有误',
          nzContent: result.message
        });
      } else {
        this.modal.success({
          nzTitle: '添加成功',
          nzContent: '',
        });
        this.hotelService.getRoomType().subscribe(roomTypes => {
          this.roomTypes = roomTypes.data;
        });
      }
      this.addRoomTypeForm.reset();
    });
  }

  addRoom(typeID: number): void{
    this.typeId = typeID;
    this.addRoomVisible = true;
  }

  submitAddRoom(): void{
    let mark = false;
    for (const i in this.addRoomForm .controls) {
      this.addRoomForm .controls[i].markAsDirty();
      this.addRoomForm .controls[i].updateValueAndValidity();
      if (this.addRoomForm.controls[i].value === null){
        mark = true;
      }
    }
    if (mark){return; }
    this.roomService.addRoom({
      typeId: this.typeId,
      roomNumber: this.addRoomForm.value.roomNumber,
    }).subscribe(result => {
      if (result.code != 200){
        this.modal.error({
          nzTitle: '信息有误',
          nzContent: result.message
        });
      }else{
        this.modal.success({
          nzTitle: '添加成功',
          nzContent: '',
        });
        this.hotelService.getRoomType().subscribe(roomTypes => {
          this.roomTypes = roomTypes.data;
        });
      }
      this.addRoomForm.reset();
    });
  }
  close(): void{
    this.updateRoomTypeVisible = false;
    this.updateRoomVisible = false;
    this.addRoomVisible = false;
  }

  beforeUpload = this.fileUploadService.beforeUpload;

  handleChange(info: { file: NzUploadFile }): void {
    switch (info.file.status) {
      case 'uploading':
        this.loading = true;
        break;
      case 'done':
        // Get this url from response in real world.
        // this.fileUploadService.getBase64(info.file!.originFileObj!, (img: string) => {
        //   this.photoAddress = info.file.response.data;
        //   this.loading = false;
        //   // this.avatarUrl = img;
        //   // console.log(this.photoAddress);
        // });
        this.loading = false;
        console.log(info.file.response);
        if(info.file.response.code == 200){
          this.photoAddress = info.file.response.data;
        }
        else{
          this.photoAddress = undefined;
          this.msg.error(info.file.response.message);
        }
        break;
      case 'error':
        this.msg.error('Network error');
        this.loading = false;
        break;
    }
  }
}
