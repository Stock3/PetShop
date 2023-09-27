import { Component, OnInit } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { URL_ROUTES } from '../../../models/url-routes';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  title = environment.shopTitle

  routes = URL_ROUTES

  constructor() { }

  ngOnInit(): void {

  }

}
