import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { ButtonModule } from 'primeng/button';
import { CheckboxModule } from 'primeng/checkbox';
import { InputTextModule } from 'primeng/inputtext';
import { PasswordModule } from 'primeng/password';
import { RippleModule } from 'primeng/ripple';
import { AppFloatingConfigurator } from '../../../main/layout/component/app.floatingconfigurator';
import { AuthGuard } from '@/auth/helpers/auth.guard';
import { confirmPasswordValidator } from './confirmpassword.validator';
import { AuthenticationService } from '@/auth/service/authentication.service';

@Component({
    selector: 'app-signup',
    standalone: true,
    providers: [AuthGuard],
    imports: [ButtonModule, CheckboxModule, InputTextModule, PasswordModule, FormsModule, RouterModule, RippleModule, AppFloatingConfigurator, ReactiveFormsModule],
    templateUrl: './signup.component.html'
})
export class SignupComponent implements OnInit {
  signupForm!: FormGroup;
  
  // fb: FormBuilder = Inject(FormBuilder);

  public constructor(private fb: FormBuilder, private authenticationService : AuthenticationService, private router : Router) {
    
  }

  ngOnInit(): void {
    this.signupForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      name: ['', [Validators.required, Validators.minLength(4)]],
      // Must contain a digit, uppercase letter, and special character
      password: ['', [Validators.required, Validators.minLength(8), Validators.pattern('(?=.*[0-9])(?=.*[A-Z])(?=.*[!@#$%^&*]).*')]],
      retypePassword: ['', [Validators.required, Validators.minLength(8), Validators.pattern('(?=.*[0-9])(?=.*[A-Z])(?=.*[!@#$%^&*]).*')]]
    }, { validators: confirmPasswordValidator('password', 'retypePassword') })
  }

  onSubmit(): void {
    if (this.signupForm.invalid) {
      return;
    }

    this.authenticationService.signup(this.name.value, this.email.value, this.password.value).subscribe(data => {
      this.router.navigate(["/login"]);
    })
  }


  get formControls() {
    return this.signupForm.controls;
  }
  
  get name() : FormControl {
    return this.formControls['name'] as FormControl
  }

  get email(): FormControl {
    return this.formControls['email'] as FormControl
  }

  get password(): FormControl {
    return this.formControls['password'] as FormControl
  }
}
