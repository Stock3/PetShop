import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserRoutingModule } from './user-routing.module';
import { ProfileComponent } from './components/profile/profile.component';
import { MyOrdersComponent } from './components/my-orders/my-orders.component';



@NgModule({
  declarations: [
    ProfileComponent,
    MyOrdersComponent
  ],
  imports: [
    CommonModule,
    UserRoutingModule
  ]
})
export class UserModule { }
