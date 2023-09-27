import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { OrdersRoutingModule } from '../orders/orders-routing.module';
import { ProductsComponent } from './products.component';
import { ProductComponent } from './product/product.component';
import { ProductItemComponent } from './product-item/product-item.component';
import { ProductListComponent } from './product-list/product-list.component';

@NgModule({
  declarations: [
    ProductsComponent,
    ProductComponent,
    ProductItemComponent,
    ProductListComponent
  ],
  imports: [
    CommonModule,
    OrdersRoutingModule
  ]
})
export class ProductsModule { }
