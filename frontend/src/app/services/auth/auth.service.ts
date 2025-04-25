import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map, Observable } from 'rxjs';
import { environment } from '../../../environments/environment.development';
import { Router } from '@angular/router';

export interface AuthResponse {
  token: string;
}

export interface SignupRequest {
  email: string;
  password: string;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private baseUrl = `${environment.apiUrl}`;


  constructor(private http: HttpClient, private router: Router) { }

login(email: string, password: string): Observable<any> {
     return this.http.post(`${this.baseUrl}/auth/login`, {email, password}).pipe(
      map((response: any) => {
        if (response && response.token) {
          this.setToken(response.token);
        }
        return response;
      }) 
    );
  }

signup(signupData: SignupRequest): Observable<any> {
    return this.http.post<AuthResponse>(`${this.baseUrl}/auth/signup`, signupData);
  }

setToken(token: string): void {
    localStorage.setItem('jwtToken', token);
  }

getToken(): string | null {
    return localStorage.getItem('jwtToken');
  }

  isAuthenticated(): boolean {
    return this.getToken() !== null;
  }

  logout(): void {
    localStorage.removeItem('jwtToken');
  }
}
