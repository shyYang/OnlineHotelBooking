import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {CustomerService} from "../../../service/customer.service";
import {UserOfCustomer} from "../../../share/common.model";
import {NzModalService} from "ng-zorro-antd/modal";
import {AuthService} from "../../../service/auth.service";
import {Router} from "@angular/router";
import {NzMessageService} from "ng-zorro-antd/message";

@Component({
  selector: 'app-customer-setting',
  templateUrl: './customer-setting.component.html',
  styleUrls: ['./customer-setting.component.css']
})
export class CustomerSettingComponent implements OnInit {
  validateForm!: FormGroup;
  updateForm!: FormGroup;
  userInfo: any;
  isVisible = false;
  money = 1;
  updateMessage: {[propName: string]: any} = {
    'username': {
      'required': '请填写用户名',
      'minlength': '用户名长度最少为8个字符',
      'maxlength': '用户名长度最多为16个字符'
    },
    'phone': {
      'required': '请填写手机号码',
      'pattern': '手机号码只能为8位或者11位数字'
    }
  };
  validMessage:{[propName: string]: any} = {
    'oldPassword': {
      'required': '请填写密码',
      'minlength': '密码长度最少为8个字符',
      'maxlength': '密码长度最多为16个字符',
      'pattern': '密码字符至少包含一个数字，一个大小写字母'
    },
    'newPassword': {
      'required': '请填写密码',
      'minlength': '密码长度最少为8个字符',
      'maxlength': '密码长度最多为16个字符',
      'pattern': '密码字符至少包含一个数字，一个大小写字母'
    },
    'confirmedPassword': {
      'required': '请填写密码',
      'minlength': '密码长度最少为8个字符',
      'maxlength': '密码长度最多为16个字符',
      'pattern': '密码字符至少包含一个数字，一个大小写字母'
    }
  };
  updateErrors: {[propName: string]: any} = {
    'username': {
      'message': ''
    },
    'phone': {
      'message': ''
    }
  };
  validErrors:{[propName: string]: any} = {
    'oldPassword': {
      'message': ''
    },
    'newPassword': {
      'message': ''
    },
    'confirmedPassword':{
      'message': ''
    }
  };

  submitUpdateForm():void{
    for (const i in this.updateForm.controls) {
      this.updateForm.controls[i].markAsDirty();
      this.updateForm.controls[i].updateValueAndValidity();
    }
    if (!this.updateForm.valid)return;
    this.customerService.changeCustomerInfo({
      username:this.updateForm.value.username,
      gender:this.updateForm.value.gender,
      phone:this.updateForm.value.phone
    }).subscribe(result => {
      if (result.code!=200){
        this.modal.error({
          nzTitle: '修改失败',
          nzContent: result.message
        });
      }else {
        this.userInfo = result.data
        this.modal.success({
          nzTitle: '修改成功',
          nzContent: '请查看修改后的信息'
        });
      }
    });
    console.log(this.updateForm.value);
  }

  submitForm(): void {
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[i].markAsDirty();
      this.validateForm.controls[i].updateValueAndValidity();
    }
    if (this.validateForm.valid){
      if (this.validateForm.value.newPassword !== this.validateForm.value.confirmedPassword){
        this.modal.error({
          nzTitle: '新密码有误',
          nzContent: '确认密码必须与新密码相同'
        });
        return;
      }
      console.log(this.validateForm.value);
      this.customerService.changePassword({
        oldPassword:this.validateForm.value.oldPassword,
        newPassword:this.validateForm.value.newPassword
      }).subscribe(result => {
        if (result.code!=200){
          this.modal.error({
            nzTitle: '修改失败',
            nzContent: result.message
          });
        }else {
          this.modal.success({
            nzTitle: '修改成功',
            nzContent: '请牢记新密码'
          });
        }
      });
    }
  }

  constructor(
    private fb: FormBuilder,
    private customerService: CustomerService,
    private modal: NzModalService,
    private authService: AuthService,
    private router:Router,
    private msg:NzMessageService
  ) {}

  ngOnInit(): void {
    this.validateForm = this.fb.group({
      oldPassword: [null, [
        Validators.required,
        Validators.minLength(8),
        Validators.maxLength(16),
      ]],
      newPassword: [null, [
        Validators.required,
        Validators.minLength(8),
        Validators.maxLength(16),
        Validators.pattern('^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[^]{8,16}$')
      ]],
      confirmedPassword: [null, [
        Validators.required,
        Validators.minLength(8),
        Validators.maxLength(16),
        Validators.pattern('^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[^]{8,16}$')
      ]]
    });
    this.updateForm = this.fb.group({
      username:[null, [
        Validators.required,
        Validators.minLength(8),
        Validators.maxLength(16),
      ]],
      gender:['男', [Validators.required,]],
      phone:[null, [Validators.required,Validators.pattern('^[0-9]{8}$|^[0-9]{11}$')]]
    });
    this.customerService.getCustomerInfo().subscribe(result => {
      this.userInfo = result.data;
    });
    this.updateForm.valueChanges
      .subscribe(data => this.onUpdateValueChanged(data));
    this.onUpdateValueChanged();
    this.validateForm.valueChanges
      .subscribe(data => this.onValueChanged(data));
    this.onValueChanged();
  }

  onUpdateValueChanged(data?: any) {
    // 如果表单不存在则返回
    if (!this.updateForm) return;
    // 获取当前的表单
    const form = this.updateForm;
    // 遍历错误消息对象
    for (const field in this.updateErrors) {
      // 清空当前的错误消息
      this.updateErrors[field].message = '';
      // 获取当前表单的控件
      const control = form.get(field);
      // 当前表单存在此空间控件 && 此控件没有被修改 && 此控件验证不通过
      if (control && control.dirty && !control.valid) {
        // 获取验证不通过的控件名，为了获取更详细的不通过信息
        const messages = this.updateMessage[field];
        // 遍历当前控件的错误对象，获取到验证不通过的属性
        for (const key in control.errors) {
          // 把所有验证不通过项的说明文字拼接成错误消息
          this.updateErrors[field].message += messages[key];
        }
      }
    }
  }

  onValueChanged(data?: any) {
    // 如果表单不存在则返回
    if (!this.validateForm) return;
    // 获取当前的表单
    const form = this.validateForm;
    // 遍历错误消息对象
    for (const field in this.validErrors) {
      // 清空当前的错误消息
      this.validErrors[field].message = '';
      // 获取当前表单的控件
      const control = form.get(field);
      // 当前表单存在此空间控件 && 此控件没有被修改 && 此控件验证不通过
      if (control && control.dirty && !control.valid) {
        // 获取验证不通过的控件名，为了获取更详细的不通过信息
        const messages = this.validMessage[field];
        // 遍历当前控件的错误对象，获取到验证不通过的属性
        for (const key in control.errors) {
          // 把所有验证不通过项的说明文字拼接成错误消息
          this.validErrors[field].message += messages[key];
        }
      }
    }
  }

  logout():void{
    this.authService.logout().subscribe(res => {
      if (res.code!=200){
        this.msg.error("退出失败，请稍后再试");
      }else this.router.navigate(['/auth/customer-login']);
    });
  }

  recharge():void{
    this.isVisible = true;
  }

  handleOk(): void {
    this.isVisible = false;
    if (this.money<=0){
      this.msg.error('金额输入无效，请输入大于0的值');
    }else {
      this.customerService.recharge(this.money).subscribe(res => {
        if (res.code==200){
          this.userInfo.account = Number(res.data);
          this.msg.success('充值成功');
        }else this.msg.error('充值失败，请稍后再试');
      });
    }
  }

  handleCancel(): void {
    this.isVisible = false;
  }
}
