import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { OrderListComponent } from './order-list/order-list.component';
import { OrderComponent } from './order/order.component';
import { OrdersComponent } from './orders.component';

const routes: Routes = [{
  path: '',
  component: OrdersComponent,
  children: [
    {
      path: 'list',
      component: OrderListComponent,
    },
    {
      path: 'add',
      component: OrderComponent,
    },
    {
      path: 'edit/:orderId',
      component: OrderComponent,
    },
  ]
}]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class OrdersRoutingModule { }
