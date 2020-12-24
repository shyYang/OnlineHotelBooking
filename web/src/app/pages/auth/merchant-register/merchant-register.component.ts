import {Component, OnInit, SystemJsNgModuleLoader} from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { NzFormTooltipIcon } from 'ng-zorro-antd/form';
import { NzCascaderOption } from 'ng-zorro-antd/cascader';
import { NzMessageService } from 'ng-zorro-antd/message';
import { NzUploadFile } from 'ng-zorro-antd/upload';
import {Observable, Observer} from 'rxjs';
import {AuthService} from "../../../service/auth.service";
import {NzModalService} from "ng-zorro-antd/modal";
import {Router} from "@angular/router";
import {FileUploadService} from "../../../service/fileUpload.service";

const options = [
  {value: '北京', label: '北京', children: [
    {value: '朝阳路', label: '朝阳路', isLeaf: true},
  ]},
  {value: '浙江', label: '浙江', children: [
      {value: '杭州', label: '杭州', children: [
          {value: '西湖', label: '西湖', isLeaf: true}
      ]},
      {value: '宁波', label: '宁波', isLeaf: true}
  ]},
  {value: '江苏', label: '江苏', children: [
      {value: '南京', label: '南京', children: [
          {value: '中华门', label: '中华门', isLeaf: true}
       ]},
  ]}
];



@Component({
  selector: 'app-merchant-register',
  templateUrl: './merchant-register.component.html',
  styleUrls: ['./merchant-register.component.css']
})

export class MerchantRegisterComponent implements OnInit {
  checkCodeUrl:string = '/getVerify';
  imgSrc:string = '';
  nzOptions: NzCascaderOption[] = options;
  loading = false;
  avatarUrl?: string;
  // 图片上传服务器
  action = 'root';
  photoAddress?: string;
  validateForm!: FormGroup;
  captchaTooltipIcon: NzFormTooltipIcon = {
    type: 'info-circle',
    theme: 'twotone'
  };
  // 提交表单
  submitForm(): void {
    console.log(this.photoAddress);
    if (!this.photoAddress){
      this.modal.error({
        nzTitle: '图片未上传',
        nzContent: '请先上传图片',
      });
      return;
    }
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[i].markAsDirty();
      this.validateForm.controls[i].updateValueAndValidity();
    }
    console.log(this.validateForm.value);
    let t = '';
    for (const i in this.validateForm.value.address1){
      t += this.validateForm.value.address1[i];
    }
    this.authService.submitCaptcha(this.validateForm.value.captcha).subscribe(result => {
      if (result.code != 200){
        this.modal.error({
          nzTitle: '验证码错误',
          nzContent: '请按要求填写正确的验证码'
        });
      }else {
        this.authService.hotelSignUp({
          hotelName: this.validateForm.value.hotelName,
          password: this.validateForm.value.password,
          address: t + this.validateForm.value.detailAddress,
          phone: this.validateForm.value.phoneNumber,
          photo: this.photoAddress,
          introduction: this.validateForm.value.introduction
        }).subscribe(result => {
          if (result.code != 200){
            this.modal.error({
              nzTitle: '信息有误',
              nzContent: result.message
            });
          }else {
            this.router.navigate(['/','merchant','info']);
            this.msg.success("注册成功，您的id是"+result.data);
          }
        });
      }
    });
  }

  getVerify():void{
    //为url时间戳
    var getTimestamp = new Date().getTime();
    if (this.checkCodeUrl.indexOf("?") > -1) {
      this.checkCodeUrl = this.checkCodeUrl + "&timestamp=" + getTimestamp
    } else {
      this.checkCodeUrl = this.checkCodeUrl + "?timestamp=" + getTimestamp
    }
    this.imgSrc = this.checkCodeUrl;
  }

  // 检验密码正确
  updateConfirmValidator(): void {
    /** wait for refresh value */
    Promise.resolve().then(() => this.validateForm.controls.checkPassword.updateValueAndValidity());
  }
  confirmationValidator = (control: FormControl): { [s: string]: boolean } => {
    if (!control.value) {
      return { required: true };
    } else if (control.value !== this.validateForm.controls.password.value) {
      return { confirm: true, error: true };
    }
    return {};
  };
  onChanges(values: string[]): void {
    console.log(values);
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

  getCaptcha(e: MouseEvent): void {
    e.preventDefault();
  }
  // constructor(private fb: FormBuilder){}
  constructor(
    private fb: FormBuilder,
    private msg: NzMessageService,
    private authService: AuthService,
    private modal: NzModalService,
    private router: Router,
    private fileUploadService: FileUploadService
    ) {}

  ngOnInit(): void {
    this.validateForm = this.fb.group({
      password: [null, [Validators.pattern('^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[^]{8,16}$'), Validators.required]],
      checkPassword: [null, [Validators.required, this.confirmationValidator]],
      hotelName: [null, [Validators.required]],
      address1: [null, [Validators.required]],
      detailAddress: [null, [Validators.required]],
      introduction: [null, [Validators.min(1), Validators.required]],
      phoneNumber: [null, [Validators.minLength(11), Validators.maxLength(11), Validators.pattern('1[0-9]{10}'), Validators.required]],
      captcha: [null, [Validators.required]],
      agree: [false]
    });

    this.getVerify();

  }

}
