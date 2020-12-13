import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {MemoryService} from '../../../service/memory.service';
import {AuthService} from '../../../service/auth.service';
import {NzModalService} from 'ng-zorro-antd/modal';
import {Router} from '@angular/router';
import {NzMessageService} from "ng-zorro-antd/message";

@Component({
  selector: 'app-admin-login',
  templateUrl: './admin-login.component.html',
  styleUrls: ['./admin-login.component.css']
})
export class AdminLoginComponent implements OnInit {
  checkCodeUrl:string = '/getVerify';
  imgSrc:string = '';
  user: any;
  validateForm!: FormGroup;
  validationMessage: {[propName: string]: any} = {
    'account': {
      'required': '请填写用户名',
    },
    'password': {
      'required': '请填写密码',
    },
    'captcha': {
      'required': '请填写验证码'
    }
  };
  formErrors: {[propName: string]: any} = {
    'account': {
      'message': ''
    },
    'password': {
      'message': ''
    },
    'captcha': {
      'message': ''
    }
  };

  constructor(
    private fb: FormBuilder,
    private memory: MemoryService,
    private authService: AuthService,
    private modal: NzModalService,
    private router: Router,
    private msg:NzMessageService
  ) {}

  ngOnInit(): void {
    this.validateForm = this.fb.group(
      {
        account: [null, [
          Validators.required,
        ]],
        password: [null, [
          Validators.required,
        ]],
        captcha: [null, [
          Validators.required,
        ]],
        remember: [false],
      });
    this.getVerify();
    if (this.memory.getRememberAdmin()){
      this.user = this.memory.getAdmin();
      this.validateForm.setValue({
        account: this.user.name,
        password: this.user.password,
        captcha: null,
        remember: true
      });
    }
    this.validateForm.valueChanges
      .subscribe(data => this.onValueChanged(data));
    this.onValueChanged();
  }
  onValueChanged(data?: any): void{
    // 表单不存在直接返回
    if (!this.validateForm) {return; }
    // 获取当前表单
    const form = this.validateForm;
    // 遍历错误消息
    for (const field in this.formErrors) {
      // 清空当前的错误消息
      this.formErrors[field].message = '';
      const control = form.get(field);
      if (control && control.dirty && !control.valid){
        const messages = this.validationMessage[field];
        for (const key in control.errors) {
          this.formErrors[field].message += messages[key];
        }
      }
    }
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
  submitForm(): void{
    for (const i in this.validateForm.controls){
      this.validateForm.controls[i].markAsDirty();
      this.validateForm.controls[i].updateValueAndValidity();
    }
    if (!this.validateForm.valid){return; }
    if (this.validateForm.value.remember){
      this.memory.setAdmin({
        name: this.validateForm.value.account,
        password: this.validateForm.value.password,
      });
    }else {
      this.memory.unsetAdmin();
    }
    this.authService.submitCaptcha(this.validateForm.value.captcha).subscribe(result => {
        if (result.code != 200){
          this.modal.error({
            nzTitle: '验证码错误',
            nzContent: '请按要求填写正确的验证码'
          });
        }else {
          this.authService.customerLogin({
            userId: this.validateForm.value.account,
            password: this.validateForm.value.password,
            role: 0 // 代表管理员登录
          }).subscribe(result => {
            if (result.code != 200){
              this.modal.error({
                nzTitle: '信息有误',
                nzContent: result.message
              });
            }else {
              this.msg.success("登陆成功")
              this.router.navigate(['admin']);
            }
          });
        }
    });
  }
  getCaptcha(e: MouseEvent): void {
    e.preventDefault();
  }
}
