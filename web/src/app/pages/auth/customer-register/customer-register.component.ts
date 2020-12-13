import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
// import { measure } from 'ng-zorro-antd/core/util';
import { NzFormTooltipIcon } from 'ng-zorro-antd/form';
import {AuthService} from "../../../service/auth.service";
import {NzModalService} from "ng-zorro-antd/modal";
import {Router} from "@angular/router";
@Component({
  selector: 'app-customer-register',
  templateUrl: './customer-register.component.html',
  styleUrls: ['./customer-register.component.css']
})
export class CustomerRegisterComponent implements OnInit {
  checkCodeUrl:string = '/getVerify';
  imgSrc:string = '';
  validateForm!: FormGroup;
  captchaTooltipIcon: NzFormTooltipIcon = {
    type: 'info-circle',
    theme: 'twotone'
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
    this.authService.submitCaptcha(this.validateForm.value.captcha).subscribe(result => {
      if (result.code!=200){
        this.getVerify();
        this.modal.error({
          nzTitle: '验证码错误',
          nzContent: '请按要求填写正确的验证码'
        });
      }else {
        this.authService.customerSignUp({
          username:this.validateForm.value.username,
          gender:this.validateForm.value.gender,
          phone:this.validateForm.value.phoneNumber,
          password:this.validateForm.value.password,
        }).subscribe(result => {
          if (result.code!=200){
            this.modal.error({
              nzTitle: '信息有误',
              nzContent: result.message
            });
          }else {
            this.router.navigate(['/','customer','home']);
            this.modal.success({
              nzTitle: '注册成功',
              nzContent: '你的id是：'+result.data,
            });
          }
        });
      }
    });
  }

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
  }

  // confirmationValidatorName = (control: FormControl): { [s: string]: boolean } => {
  // }

  getCaptcha(e: MouseEvent): void {
    alert(1);
    e.preventDefault();
  }

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private modal: NzModalService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.validateForm = this.fb.group({
      password: [null, [Validators.pattern('^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[^]{8,16}$'), Validators.required]],
      checkPassword: [null, [Validators.required, this.confirmationValidator]],
      username: [null, [Validators.minLength(1), Validators.maxLength(10), Validators.required]],
      gender: ['男', [Validators.required]],
      phoneNumber: [null, [Validators.minLength(11), Validators.maxLength(11), Validators.pattern('1[0-9]{10}'), Validators.required]],
      captcha: [null, [Validators.required]],
      agree: [false]
    });
    this.getVerify()
  }

}
