import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../services/auth/auth.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { CustomToastrService } from '../../services/toastr/customtoastr.service';

@Component({
  standalone: true,
  selector: 'app-login',
  templateUrl: './login.component.html',
  imports: [ReactiveFormsModule, FormsModule, RouterLink, CommonModule],
  styleUrl: './login.component.css'
})
export class LoginComponent {
  email = '';
  password = '';

  errorMessage: string = '';

  constructor(private authService: AuthService, private router: Router, private toastr: CustomToastrService) {}

  onSubmit(): void {
    this.authService.login(this.email, this.password).subscribe({
      next: (response) => {
        console.log('Login successful:', response);
        this.router.navigate(['/home']).then(() => {
          window.location.reload(); 
        });
      
      },
      error: (error) => {
        this.errorMessage = 'Email o password non corretti.';
        this.toastr.error('Errore nel tentativo di autenticazione.');
      }
    });
  }

}
