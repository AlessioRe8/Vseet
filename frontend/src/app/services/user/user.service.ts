import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment.development';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CommonModule } from '@angular/common';

export interface User {
  email: string;
  role: string;
}

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiUrl = `${environment.apiUrl}/users`;

  constructor(private http: HttpClient) {}

  getUserDetails(): Observable<any> {
    return this.http.get(`${this.apiUrl}/me`);
  }

  getAllUsers(): Observable<any> {
    return this.http.get(this.apiUrl);
  }

  editUserRole(userId: number, role: string) {
    return this.http.put(`${this.apiUrl}/${userId}/role`, { role });
  }
}

