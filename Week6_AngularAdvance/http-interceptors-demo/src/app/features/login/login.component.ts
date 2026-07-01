// src/app/features/login/login.component.ts
import { signal } from '@angular/core';
import { Component, inject } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../core/services/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule],
  template: `
<div class="login-page">

  <div class="login-card">

    <div class="logo">🔐</div>

    <h1>Welcome Back</h1>

    <p class="subtitle">
      Login to access your dashboard
    </p>

    <form [formGroup]="form" (ngSubmit)="onSubmit()">

      <input
        type="text"
        formControlName="username"
        placeholder="Username"
      />

      <input
        type="password"
        formControlName="password"
        placeholder="Password"
      />

      <button
        type="submit"
        [disabled]="form.invalid">

        Login

      </button>

    </form>

    @if(error()){

      <p class="error">

        {{error()}}

      </p>

    }

  </div>

</div>
`,

styles: [`

.login-page{

height:100vh;

display:flex;

justify-content:center;

align-items:center;

background:linear-gradient(135deg,#4f46e5,#06b6d4);

padding:20px;

}

.login-card{

background:white;

padding:40px;

width:380px;

border-radius:18px;

box-shadow:0 15px 35px rgba(0,0,0,.2);

text-align:center;

animation:fadeIn .5s ease;

}

.logo{

font-size:45px;

margin-bottom:10px;

}

h1{

margin-bottom:8px;

color:#333;

}

.subtitle{

color:#777;

margin-bottom:25px;

}

input{

width:100%;

padding:14px;

margin-bottom:15px;

border:1px solid #ddd;

border-radius:8px;

font-size:15px;

box-sizing:border-box;

transition:.3s;

}

input:focus{

outline:none;

border-color:#4f46e5;

box-shadow:0 0 8px rgba(61, 176, 187, 0.76);

}

button{

width:100%;

padding:14px;

background:#4f46e5;

color:white;

border:none;

border-radius:8px;

font-size:16px;

cursor:pointer;

transition:.3s;

}

button:hover{

background:#4338ca;

transform:translateY(-2px);

}

button:disabled{

background:#999;

cursor:not-allowed;

}

.error{

margin-top:15px;

color:red;

font-size:14px;

}

@keyframes fadeIn{

from{

opacity:0;

transform:translateY(25px);

}

to{

opacity:1;

transform:translateY(0);

}

}

`]
})
export class LoginComponent {
  private fb = inject(FormBuilder);
  private authService = inject(AuthService);
  private router = inject(Router);

  error = signal('');

  form = this.fb.group({
    username: ['', Validators.required],
    password: ['', Validators.required]
  });

  onSubmit() {
    if (this.form.invalid) return;
    const { username, password } = this.form.value;
    const success = this.authService.login(username!, password!);
    if (success) {
      this.router.navigate(['/dashboard']);
    } else {
      this.error.set('Invalid credentials');
    }
  }
}