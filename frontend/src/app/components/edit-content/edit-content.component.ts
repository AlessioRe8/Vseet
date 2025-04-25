import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ContentService } from '../../services/content/content.service';
import { CommonModule } from '@angular/common';
import { CustomToastrService } from '../../services/toastr/customtoastr.service';

@Component({
  selector: 'app-edit-content',
  imports: [FormsModule, CommonModule, ReactiveFormsModule],
  templateUrl: './edit-content.component.html',
  styleUrl: './edit-content.component.css'
})
export class EditContentComponent implements OnInit {
  position = { latitude: 0, longitude: 0 };
  isPoi = false;
  contentForm!: FormGroup;
  contentId!: number;
  contentType!: 'poi' | 'itinerary';
  categories = ['SPORT', 'CULTURE', 'RELIGION', 'NATURE', 'ENTERTAINMENT', 'SERVICE', 'OTHER'];

  constructor(
    private route: ActivatedRoute,
    private contentService: ContentService,
    private fb: FormBuilder,
    private router: Router,
    private toastr: CustomToastrService
  ) {}

  ngOnInit(): void {
  this.contentId = +this.route.snapshot.paramMap.get('id')!;
  this.contentType = this.route.snapshot.queryParamMap.get('type') as 'poi' | 'itinerary'; //Non vede se è poi o itinerary. aggiungere al itinerarycontroller il metodo post

  this.contentForm = this.fb.group({
    name: ['', Validators.required],
    description: ['', Validators.required],
    contentCategory: ['', Validators.required],
    position: this.fb.group({
      latitude: [null],
      longitude: [null]
    })
  });

  
  this.isPoi = false;

  if (this.contentType === 'poi') {
    this.isPoi = true;
    this.contentService.getPoiById(this.contentId).subscribe(data => {
  
      this.contentForm.patchValue({
        name: data.name,
        description: data.description,
        contentCategory: data.contentCategory,
        position: {
          latitude: data.position?.latitude,
          longitude: data.position?.longitude
        }
      });
    });
  } else if (this.contentType === 'itinerary') {
    this.contentService.getItineraryById(this.contentId).subscribe(data => {
      this.contentForm.patchValue({
        name: data.name,
        description: data.description,
        contentCategory: data.contentCategory,
      });
      
    });
  }
  }

  salvaModifiche() {
    if (this.contentForm.invalid) return;

  let contenutoAggiornato = this.contentForm.value;

  if (this.contentType === 'poi') {
    this.contentService.updatePoi(this.contentId, contenutoAggiornato).subscribe(() => {
      this.router.navigate(['/contributions']);
    });
  } else {
    this.contentService.updateItinerary(this.contentId, contenutoAggiornato).subscribe(() => {
      this.router.navigate(['/contributions']);
    });
  }
  this.toastr.success('Contenuto modificato con successo!');
  }

  annulla(): void {
    this.router.navigate(['/contributions']);
  }
}
