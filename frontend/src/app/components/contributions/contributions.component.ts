import { Component, OnInit } from '@angular/core';
import { ContentService } from '../../services/content/content.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Observable } from 'rxjs';
import { CustomToastrService } from '../../services/toastr/customtoastr.service';

@Component({
  selector: 'app-contributions',
  imports: [CommonModule, FormsModule],
  templateUrl: './contributions.component.html',
  styleUrl: './contributions.component.css'
})
export class ContributionsComponent implements OnInit{
  contributions: any[] = [];

  constructor(private contentService: ContentService, private router: Router, private toastr: CustomToastrService) {}

  ngOnInit(): void {
    this.contentService.getUserContributions().subscribe({
      next: (data) => this.contributions = data,
      error: (err) => console.error('Errore nel recupero dei contributi', err)
    });
  }


  goToDetail(content: any) {

      if (content.contentType === 'itinerary') {
        this.router.navigate(['/itinerary', content.id]);
      } else {
        this.router.navigate(['/poi', content.id]);
      }
  }

  editContent(event:MouseEvent, content: any) {
    event.stopPropagation();
        const type = content.contentType;
        this.router.navigate(['/edit-content', content.id], { queryParams: { type } });
      }

  confirmDelete(event: MouseEvent, content: any) {
    event.stopPropagation();
    const conferma = confirm('Sei sicuro di voler eliminare il contenuto?');
    if (!conferma) return;

    const id = content.id;
    const type = content.contentType;

  let deleteCall: Observable<void>;

  if (type === 'poi') {
    deleteCall = this.contentService.deletePoi(id);
  } else {
    deleteCall = this.contentService.deleteItinerary(id);
  }

  deleteCall.subscribe({
    next: () => {
    this.contributions = this.contributions.filter(c => c.id !== id);
    this.toastr.success('Contenuto eliminato correttamente.');
  },
    error: (err) => {
    console.error('Errore durante la cancellazione:', err);
    this.toastr.error('Errore durante l\'eliminazione del contenuto.');
  }
  });
      }
}
