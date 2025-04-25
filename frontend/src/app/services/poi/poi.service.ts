import { HttpClient, HttpClientModule, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class POIService {

  private apiUrl = `${environment.apiUrl}`+ "/pois";

  constructor(private http: HttpClient) { }

  getAllPOIs(): Observable<any> {
    return this.http.get(this.apiUrl);
  }

  getPoiById(id: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/${id}`);
  }

  createPOI(content: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/create`, content);
  }

  updatePoi(id: number, poi: any): Observable<any> {
    return this.http.put(`${this.apiUrl}/${id}`, poi);
  }

}
