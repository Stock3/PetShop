import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthRoutingModule } from './auth-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { NewPasswordComponent } from './components/new-password/new-password.component';
import { NewPasswordCompleteComponent } from './components/new-password-complete/new-password-complete.component';
import { ResetPasswordComponent } from './components/reset-password/reset-password.component';
import { SignInComponent } from './components/sign-in/sign-in.component';
import { SignOutComponent } from './components/sign-out/sign-out.component';
import { SignUpComponent } from './components/sign-up/sign-up.component';
import { SignUpCompleteComponent } from './components/sign-up-complete/sign-up-complete.component';
import { SignUpConfirmComponent } from './components/sign-up-confirm/sign-up-confirm.component';
import { ButtonComponent } from '../../components/button/button.component';
import { IconComponent } from '../../components/button/icon/icon.component';
import { CommonDirectivesModule } from '../../directives/common-directives/common-directives.module';

import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';



@NgModule({
  declarations: [
    NewPasswordComponent,
    NewPasswordCompleteComponent,
    ResetPasswordComponent,
    SignInComponent,
    SignOutComponent,
    SignUpComponent,
    SignUpCompleteComponent,
    SignUpConfirmComponent,
    ButtonComponent,
    IconComponent
  ],
  imports: [
    CommonModule,
    AuthRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    CommonDirectivesModule,
    MatFormFieldModule,
    MatIconModule,
    MatInputModule,
  ]
})
export class AuthModule { }
