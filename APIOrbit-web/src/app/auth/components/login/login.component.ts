import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, FormsModule, Validators, ReactiveFormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { ButtonModule } from 'primeng/button';
import { CheckboxModule } from 'primeng/checkbox';
import { InputTextModule } from 'primeng/inputtext';
import { PasswordModule } from 'primeng/password';
import { RippleModule } from 'primeng/ripple';
import { AppFloatingConfigurator } from '../../../main/layout/component/app.floatingconfigurator';
import { AuthGuard } from '@/auth/helpers/auth.guard';
import { AuthenticationService } from '@/auth/service/authentication.service';

@Component({
    selector: 'app-login',
    standalone: true,
    providers: [AuthGuard],
    imports: [ButtonModule, CheckboxModule, InputTextModule, PasswordModule, FormsModule, ReactiveFormsModule, RouterModule, RippleModule, AppFloatingConfigurator, ReactiveFormsModule],
    templateUrl: './login.component.html' 
})
export class LoginComponent implements OnInit {
   
    checked: boolean = false;

    loginForm!: FormGroup;

    public constructor(private fb: FormBuilder, private authenticationService: AuthenticationService, private router : Router) { }
    
    
    ngOnInit(): void {
        this.loginForm = this.fb.group({
            email: ['', [Validators.required, Validators.email]],
            password: ['', [Validators.required]] //TODO: add other validatores from signup 
        })
    }
    

    onSubmit(): void {
        if (this.loginForm.invalid) {
            return;
        }

        this.authenticationService.login(this.email.value, this.password.value).subscribe((data) => {
            this.router.navigate([''])
        })
    }

    get formControls() {
        return this.loginForm.controls;
    }

    get email(): FormControl {
        return this.formControls['email'] as FormControl;
    }

    get password(): FormControl {
        return this.formControls['password'] as FormControl;
    }
}
