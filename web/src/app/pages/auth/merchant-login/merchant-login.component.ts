import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {MemoryService} from '../../../service/memory.service';
import {AuthService} from '../../../service/auth.service';
import {NzModalService} from 'ng-zorro-antd/modal';
import {Router} from '@angular/router';
@Component({
  selector: 'app-merchant-login',
  templateUrl: './merchant-login.component.html',
  styleUrls: ['./merchant-login.component.css']
})
export class MerchantLoginComponent implements OnInit {
  checkCodeUrl:string = '/getVerify';
  imgSrc:string = '';
  hotel: any;
  validateForm!: FormGroup;
  validationMessage: {[propName: string]: any} = {
    'username': {
      'required': '请填写用户名',
      'minlength': '用户名长度最少为8个字符',
      'maxlength': '用户名长度最多为16个字符'
    },
    'password': {
      'required': '请填写密码',
      'minlength': '密码长度最少为8个字符',
      'maxlength': '密码长度最多为16个字符',
      'pattern': '密码字符至少包含一个数字，一个大小写字母'
    },
    'captcha': {
      'required': '请填写验证码'
    }
  };

  formErrors: {[propName: string]: any} = {
    'username': {
      'message': ''
    },
    'password': {
      'message': ''
    },
    'captcha': {
      'message': ''
    }
  };

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

  submitForm(): void {
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[i].markAsDirty();
      this.validateForm.controls[i].updateValueAndValidity();
    }
    if (!this.validateForm.valid)return;
    if (this.validateForm.value.remember){
      this.memory.setUser({
        name:this.validateForm.value.account,
        password:this.validateForm.value.password
      });
    }else {
      this.memory.unsetHotel();
    }
    this.authService.submitCaptcha(this.validateForm.value.captcha).subscribe(result => {
      if (result.code!=200){
        this.modal.error({
          nzTitle: '验证码错误',
          nzContent: '请按要求填写正确的验证码'
        });
      }else {
        this.authService.customerLogin({
          userId:this.validateForm.value.username,
          password:this.validateForm.value.password,
          role:1   //代表商家登录
        }).subscribe(result => {
          if (result.code!=200){
            this.modal.error({
              nzTitle: '信息有误',
              nzContent: result.message
            });
          }else {
            this.router.navigate(['/','merchant','info']);
            this.modal.success({
              nzTitle: '登录成功',
              nzContent: '',
            });
          }
        });
      }
    });

  }

  constructor(private fb: FormBuilder,
              private memory: MemoryService,
              private authService:AuthService,
              private modal:NzModalService,
              private router:Router) {}

  ngOnInit(): void {
    this.validateForm = this.fb.group({
      username: [null, [
        Validators.required,
        // Validators.minLength(8),
        // Validators.maxLength(16),
      ]],
      password: [null, [
        Validators.required,
        // Validators.minLength(8),
        //         // Validators.maxLength(16),
        //         // Validators.pattern('^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[^]{8,16}$')
      ]],
      captcha: [null, [Validators.required]],
      remember: [false]
    });
    this.getVerify();
    if(this.memory.getRememberHotel()){
      this.hotel = this.memory.getHotel();
      this.validateForm.setValue({
        account: this.hotel.name,
        password: this.hotel.password,
        captcha: null,
        remember:true
      });
    }
    this.validateForm.valueChanges
      .subscribe(data => this.onValueChanged(data));
    this.onValueChanged();
  }

  onValueChanged(data?: any) {
    // 如果表单不存在则返回
    if (!this.validateForm) return;
    // 获取当前的表单
    const form = this.validateForm;
    // 遍历错误消息对象
    for (const field in this.formErrors) {
      // 清空当前的错误消息
      this.formErrors[field].message = '';
      // 获取当前表单的控件
      const control = form.get(field);
      // 当前表单存在此空间控件 && 此控件没有被修改 && 此控件验证不通过
      if (control && control.dirty && !control.valid) {
        // 获取验证不通过的控件名，为了获取更详细的不通过信息
        const messages = this.validationMessage[field];
        // 遍历当前控件的错误对象，获取到验证不通过的属性
        for (const key in control.errors) {
          // 把所有验证不通过项的说明文字拼接成错误消息
          this.formErrors[field].message += messages[key];
        }
      }
    }
  }
}
