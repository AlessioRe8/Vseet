import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ItineraryService } from '../../../services/itinerary/itinerary.service';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../../services/auth/auth.service';

@Component({
  selector: 'app-itinerary-detail',
  imports: [CommonModule],
  templateUrl: './itinerary-detail.component.html',
  styleUrl: './itinerary-detail.component.css'
})
export class ItineraryDetailComponent {

  itinerary: any;

  constructor(
    private route: ActivatedRoute,
    private itineraryService: ItineraryService,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    const idParam = this.route.snapshot.paramMap.get('id');
    const id = Number(idParam);

  if (!isNaN(id)) {
    this.itineraryService.getItineraryById(id).subscribe((data) => {
      this.itinerary = data;
    });
  } else {
    console.error("ID non valido:", idParam);
    
  }
}

goToDetail(content: any) {
  if (!this.authService.isAuthenticated()) {
    this.router.navigate(['/login']);
    return;
  }
this.router.navigate(['/poi', content.id]);
}
}
