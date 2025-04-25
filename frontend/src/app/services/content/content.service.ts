import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { forkJoin, map, Observable } from 'rxjs';
import { environment } from '../../../environments/environment.development';
import { POIService } from '../poi/poi.service';
import { ItineraryService } from '../itinerary/itinerary.service';

@Injectable({
  providedIn: 'root'
})
export class ContentService {
  
  

  private apiUrl = `${environment.apiUrl}`+ "/contents";

  constructor(private http: HttpClient, private poiService: POIService, private itineraryService: ItineraryService) {}

  getAllContents(): Observable<any> {
    const pois$ = this.http.get<any[]>(`${environment.apiUrl}/pois`);
    const itineraries$ = this.http.get<any[]>(`${environment.apiUrl}/itineraries`);

    return forkJoin([pois$, itineraries$]).pipe( 
      map(([pois, itineraries]) => [...pois, ...itineraries])
    );
  }

  verifyContent(contentId: number): Observable<any> {
    return this.http.post(`${this.apiUrl}/verify`, null, { params: { id: contentId } });
  }

  createPOI(content: any): Observable<any> {
    return this.poiService.createPOI(content);
  }

  createItinerary(content: any): Observable<any> {
    return this.itineraryService.createItinerary(content);
  }

  getUserContributions(): Observable<any[]> {
    const pois$ = this.http.get<any[]>(`${environment.apiUrl}/pois/contributions`);
    const itineraries$ = this.http.get<any[]>(`${environment.apiUrl}/itineraries/contributions`);
  
    return forkJoin([pois$, itineraries$]).pipe(
      map(([pois, itineraries]) => [...pois, ...itineraries])
    );
  }

  updatePoi(id: number, poi: any): Observable<any> {
    return this.poiService.updatePoi(id, poi);
    }
  
  updateItinerary(id: number, itinerary: any): Observable<any> {
    return this.itineraryService.updateItinerary(id, itinerary);
  }

  getPoiById(id: number): Observable<any> {
    return this.poiService.getPoiById(id);
  }
  
  getItineraryById(id: number): Observable<any> {
    return this.itineraryService.getItineraryById(id);
  }

  deleteContent(id: number) {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
  
  deletePoi(id: number): Observable<void> {
    return this.http.delete<void>(`${environment.apiUrl}/pois?id=${id}`);
  }
  
  deleteItinerary(id: number): Observable<void> {
    return this.http.delete<void>(`${environment.apiUrl}/itineraries?id=${id}`);
  }
}
