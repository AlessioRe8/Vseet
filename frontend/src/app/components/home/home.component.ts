import { Component } from '@angular/core';
import { Router, RouterLink, RouterModule } from '@angular/router';
import { AuthService } from '../../services/auth/auth.service';
import { POIService } from '../../services/poi/poi.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { UserService } from '../../services/user/user.service';
import { ContentService } from '../../services/content/content.service';
import { ItinerarySelectorComponent } from '../itinerary-selector/itinerary-selector.component';
import { MatDialog } from '@angular/material/dialog';
import { CustomToastrService } from '../../services/toastr/customtoastr.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  imports: [CommonModule, FormsModule]
})
export class HomeComponent {
  constructor(private authService: AuthService, private router: Router, private poiService: POIService,
     private userService: UserService, private contentService: ContentService, private dialog: MatDialog,
    private toastr: CustomToastrService) { }
  contents: any[] = [];
  userRole: string | null = null;
  filteredContents: any[] = [];
  searchTerm: string = '';



  ngOnInit(): void {
    this.contentService.getAllContents().subscribe({
      next: data => {
        this.contents = data,
        this.filteredContents = [...this.contents];
    },
      error: err => console.error('Errore nel caricamento dei contenuti', err)
    });
    if (this.authService.isAuthenticated()) { 
      this.userService.getUserDetails().subscribe({
        next: (user) => this.userRole = user.role,
        error: (err) => console.error('Errore nel recuperare il ruolo utente', err)
      });
    }
  }

  goToDetail(content: any) {
    if (!this.authService.isAuthenticated()) {
      this.toastr.warning('Prima effettua il login', 'Necessaria autenticazione');
      this.router.navigate(['/login']);
      return;
    }

  if (content.contentType === 'itinerary') {
    this.router.navigate(['/itinerary', content.id]);
  } else {
    this.router.navigate(['/poi', content.id]);
  }
  }

   filterContents(): void {
    const term = this.searchTerm.toLowerCase();
    this.filteredContents = this.contents.filter(content =>
      content.name.toLowerCase().includes(term)
    );
  }

  onCreateContent(): void {
    if (this.authService.isAuthenticated()) {
      this.router.navigate(['/create-content']);
    } else {
      this.toastr.warning('Prima effettua il login', 'Necessaria autenticazione')
      this.router.navigate(['/login']);
    }
  }

  canAddToItinerary(): boolean {
    if (!this.authService.isAuthenticated()) {
      return false;
    }
    return  (this.userRole !== 'TOURIST' && this.userRole !== 'CONTRIBUTOR');
  }

  canVerifyContent(): boolean {
  if (!this.authService.isAuthenticated()) {
    return false;
  }
  return this.userRole === 'PLATFORM_MANAGER' || this.userRole === 'CURATOR' || this.userRole === 'ANIMATOR';
  }

  openItinerarySelector(event: MouseEvent, content: any): void {
    event.stopPropagation();
    this.dialog.open(ItinerarySelectorComponent, {
      data: content.id
    });
  }

  verifyContent(event: MouseEvent, contentId: number): void {
    event.stopPropagation();
    this.contentService.verifyContent(contentId).subscribe({
      next: () => {
        this.toastr.success('Contenuto verificato con successo!');
        const content = this.contents.find(c => c.id === contentId);
        if (content) {
        content.verified = true; 
        }
        if (content.contentType === 'itinerary') {
          this.router.navigate(['/itinerary', content.id]);
        } else {
          this.router.navigate(['/poi', content.id]);
        }
      },
      error: (err) => console.error('Errore nella verifica del contenuto', err)
    }); 
  } 
}
