import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';

import {NewPasswordCompleteComponent} from './components/new-password-complete/new-password-complete.component';
import {NewPasswordComponent} from './components/new-password/new-password.component';
import {ResetPasswordComponent} from './components/reset-password/reset-password.component';
import {SignInComponent} from './components/sign-in/sign-in.component';
import {SignOutComponent} from './components/sign-out/sign-out.component';
import {SignUpCompleteComponent} from './components/sign-up-complete/sign-up-complete.component';
import {SignUpConfirmComponent} from './components/sign-up-confirm/sign-up-confirm.component';
import {SignUpComponent} from './components/sign-up/sign-up.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: '/catalog',
    pathMatch: 'full',
  },
  {path: 'signin', component: SignInComponent},
  {path: 'signup', component: SignUpComponent},
  {path: 'confirm', component: SignUpConfirmComponent},
  {path: 'complete', component: SignUpCompleteComponent},
  {path: 'signout', component: SignOutComponent},
  {path: 'reset-passsword', component: ResetPasswordComponent},
  {path: 'new-password', component: NewPasswordComponent},
  {path: 'new-password-complete', component: NewPasswordCompleteComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AuthRoutingModule {
}
