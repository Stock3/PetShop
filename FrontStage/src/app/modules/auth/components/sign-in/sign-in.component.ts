import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { URL_ROUTES } from 'src/app/models/url-routes';
// import { Router } from '@angular/router';
import { PanelStyle } from '../../../../enum/message.style';
import { MessageService } from '../../../../services/message.service';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.scss']
})
export class SignInComponent implements OnInit {
	buttonAuthDisabled: boolean = false;
  loginForm: FormGroup;
  routes = URL_ROUTES;

  constructor(
    private formBuilder: FormBuilder,
		private messageService: MessageService
  ) { 
    this.loginForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.maxLength(50)]]
    });

  }

  ngOnInit(): void {
  }

  get email() {
		return this.loginForm.get('email');
	}

	get password() {
		return this.loginForm.get('password');
	}

	logIn() {
		this.buttonAuthDisabled = true;
		const { email, password } = this.loginForm.value;

    let error = {
      message: 'Can\'t be logged in' 
    }
		console.log(this.loginForm)
    this.messageService.openSnackBar(error.message, '×', PanelStyle.error, 2);
    // this.messageService.openSnackBar(error.message, '×', PanelStyle.error, 2);
				
	}

  getEmailErrorMessage() {
    if (this.loginForm.controls['email'].hasError('required')) {
      return 'You must enter a value';
    }
    return this.loginForm.controls['email'].hasError('email') ? 'Not a valid email' : '';
  }

}
