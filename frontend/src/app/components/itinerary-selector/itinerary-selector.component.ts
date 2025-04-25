import { Component, Inject, OnInit } from '@angular/core';
import { ItineraryService } from '../../services/itinerary/itinerary.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { CustomToastrService } from '../../services/toastr/customtoastr.service';

@Component({
  selector: 'app-itinerary-selector',
  imports: [CommonModule],
  templateUrl: './itinerary-selector.component.html',
  styleUrl: './itinerary-selector.component.css'
})
export class ItinerarySelectorComponent implements OnInit {
  itineraries: any[] = [];

  constructor(
    private itineraryService: ItineraryService,
    private dialogRef: MatDialogRef<ItinerarySelectorComponent>,
    @Inject(MAT_DIALOG_DATA) public contentId: number,
    private router: Router,
    private toastr: CustomToastrService
  ) {}

  ngOnInit(): void {
    this.itineraryService.getAllItineraries().subscribe(data => {
      this.itineraries = data;
    });
  }

  select(itineraryId: number) {
    this.itineraryService.addPoiToItinerary(itineraryId, this.contentId).subscribe(() => {
      this.toastr.success('Contenuto aggiunto correttamente all\'itinerario');
      this.dialogRef.close(true);
      this.router.navigate(['/itinerary', itineraryId])
    }, () => {
      alert("Errore durante l'aggiunta del POI all'itinerario.");
    });
  }
}
