import { Component, importProvidersFrom } from '@angular/core';
import { ContentService } from '../../../services/content/content.service';
import { environment } from '../../../../environments/environment.development';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { CustomToastrService } from '../../../services/toastr/customtoastr.service';

@Component({
  selector: 'app-create-content',
  standalone: true,                 
  imports: [
    CommonModule,
    FormsModule,
  ],
  templateUrl: './create-content.component.html',
  styleUrl: './create-content.component.css'
})
export class CreateContentComponent {


  content = { 
    name: '',
    description: '',
    type: '',
    category: '',
    position: {
      latitude: 0,
      longitude: 0
    }
};
  categories = ['SPORT', 'CULTURE', 'RELIGION', 'NATURE', 'ENTERTAINMENT', 'SERVICE', 'OTHER']; 
  poiType: string = 'Concrete';
  constructor(private contentService: ContentService, private http: HttpClient, private router: Router,
    private toastr: CustomToastrService
  ) {}

  onContentTypeChange(event: Event): void {
    const select = event.target as HTMLSelectElement;
    this.poiType = select.value;
  }

  onSubmit(): void {
    let url = '';
    let formPayload = new FormData();

    if (!this.isFormValid()) { 
      return; 
    }
  
    if (this.content.type === 'POI') {
      url = `${environment.apiUrl}/pois/create`;
      formPayload.append('poi', new Blob([JSON.stringify(this.content)], { type: 'application/json' }));
      formPayload.append('type', this.poiType);
    } else if (this.content.type === 'Itinerary') {
      url = `${environment.apiUrl}/itineraries/create`;
      formPayload.append('itinerary', new Blob([JSON.stringify(this.content)], { type: 'application/json' }));
    }

    this.http.post(url, formPayload).subscribe({
      next: () => {
        this.toastr.success('Contenuto creato correttamente!', 'Successo');
        this.router.navigate(['/']);
      },
      error: (err) => {
        console.error('Errore durante la creazione:', err);
        this.toastr.error('C\'è stato un errore nella creazione', 'Errore');
      }
    });
  }

  isFormValid(): boolean {
    const baseValid = !!this.content.name && !!this.content.description && !!this.content.type && !!this.content.category;
    const poiValid = this.content.type !== 'POI' || 
                     ( this.content.position.latitude !== null &&
                      this.content.position.latitude !== undefined &&
                      this.content.position.longitude !== null &&
                      this.content.position.longitude !== undefined);
    return baseValid && poiValid;
  }

  ngOnInit(): void {}

}
