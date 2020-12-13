import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthRoutingModule } from './auth-routing.module';
import { CustomerLoginComponent } from './customer-login/customer-login.component';
import { NzFormModule } from 'ng-zorro-antd/form';
import { NzInputModule } from 'ng-zorro-antd/input';
import { NzButtonModule } from 'ng-zorro-antd/button';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NzCheckboxModule } from 'ng-zorro-antd/checkbox';
import { MerchantLoginComponent } from './merchant-login/merchant-login.component';
import { CustomerRegisterComponent } from './customer-register/customer-register.component';
import { MerchantRegisterComponent } from './merchant-register/merchant-register.component';
import { NzSelectModule} from 'ng-zorro-antd/select';
import { AuthMainComponent } from './auth-main/auth-main.component';
import { NzLayoutModule} from 'ng-zorro-antd/layout';
import {NzAvatarModule} from 'ng-zorro-antd/avatar';
import {NzMenuModule} from 'ng-zorro-antd/menu';
import { NzBreadCrumbModule } from 'ng-zorro-antd/breadcrumb';
import { NzCarouselModule } from 'ng-zorro-antd/carousel';
import { NzCardModule } from 'ng-zorro-antd/card';
import { NzIconModule } from 'ng-zorro-antd/icon';
import { NzDescriptionsModule } from 'ng-zorro-antd/descriptions';
import { NzTypographyModule } from 'ng-zorro-antd/typography';
import { NzBadgeModule } from 'ng-zorro-antd/badge';
import { NzEmptyModule } from 'ng-zorro-antd/empty';
import { NzPaginationModule } from 'ng-zorro-antd/pagination';
import { NzRateModule } from 'ng-zorro-antd/rate';
import { NzTabsModule } from 'ng-zorro-antd/tabs';
import { NzListModule } from 'ng-zorro-antd/list';
import { NzProgressModule } from 'ng-zorro-antd/progress';
import { NzDatePickerModule } from 'ng-zorro-antd/date-picker';
import { NzCollapseModule } from 'ng-zorro-antd/collapse';
import { NzCommentModule } from 'ng-zorro-antd/comment';
import { NzCascaderModule } from 'ng-zorro-antd/cascader';
import { NzUploadModule} from 'ng-zorro-antd/upload';
import { NzMessageModule} from 'ng-zorro-antd/message';
import { AdminLoginComponent } from './admin-login/admin-login.component';

@NgModule({
  declarations: [CustomerLoginComponent, MerchantLoginComponent, CustomerRegisterComponent, MerchantRegisterComponent, AuthMainComponent, AdminLoginComponent],
  imports: [
    CommonModule,
    AuthRoutingModule,
    NzLayoutModule,
    NzBreadCrumbModule,
    NzMenuModule,
    NzCarouselModule,
    NzAvatarModule,
    NzCardModule,
    NzInputModule,
    NzIconModule,
    NzButtonModule,
    NzDescriptionsModule,
    NzTypographyModule,
    NzBadgeModule,
    NzFormModule,
    ReactiveFormsModule,
    NzCheckboxModule,
    NzEmptyModule,
    NzPaginationModule,
    NzRateModule,
    FormsModule,
    NzTabsModule,
    NzListModule,
    NzProgressModule,
    NzDatePickerModule,
    NzCollapseModule,
    NzCommentModule,
    NzSelectModule,
    NzCascaderModule,
    NzUploadModule,
    NzMessageModule
  ]
})
export class AuthModule { }
