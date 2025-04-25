import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { AuthService, SignupRequest } from '../../services/auth/auth.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { CustomToastrService } from '../../services/toastr/customtoastr.service';


@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  imports: [ReactiveFormsModule, FormsModule, RouterLink, CommonModule],
  styleUrl: './signup.component.css'
})
export class SignupComponent {
  signupData: SignupRequest = {
    email: '',
    password: ''
  };

  errorMessage: string = '';

  constructor(private authService: AuthService, private router: Router, private toastr: CustomToastrService) {}

  onSignup(): void {
    this.authService.signup(this.signupData).subscribe({
      next: (response) => {
        console.log('Signup successful:', response);
        this.router.navigateByUrl('/login');
      },
      error: (error) => {
        this.errorMessage = 'Errore durante la registrazione, l\'utente esiste già.';
        this.toastr.error('Errore nel tentativo di registrazione.');
            }
    });
  }
}
