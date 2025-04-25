import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment.development';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ItineraryService {
 

  private apiUrl = `${environment.apiUrl}`+ "/itineraries";

  constructor(private http: HttpClient) { }
  
  createItinerary(content: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/create`, content);
  }

  getItineraryById(id: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/${id}`);
  }

  getAllItineraries(): Observable<any> {
    return this.http.get(this.apiUrl);
  }

  updateItinerary(id: number, itinerary: any): Observable<any> {
    return this.http.put(`${this.apiUrl}/${id}`, itinerary);
  } 
  
  addPoiToItinerary(itineraryId: number, contentId: number) {
    return this.http.post(`${this.apiUrl}/add`, null, {
      params: {
        idItinerary: itineraryId,
        idContent: contentId
      }
    });
  }
}

