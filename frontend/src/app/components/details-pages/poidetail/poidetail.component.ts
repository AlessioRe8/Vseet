import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { POIService } from '../../../services/poi/poi.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-poidetail',
  imports: [CommonModule],
  templateUrl: './poidetail.component.html',
  styleUrl: './poidetail.component.css'
})
export class POIDetailComponent {

  poi: any;

  constructor(
    private route: ActivatedRoute,
    private poiService: POIService
  ) {}

  ngOnInit(): void {
    const idParam = this.route.snapshot.paramMap.get('id');
    const id = Number(idParam);

  if (!isNaN(id)) {
    this.poiService.getPoiById(id).subscribe((data) => {
      this.poi = data;
    });
  } else {
    console.error("ID non valido:", idParam);
  }
    /* const id = this.route.snapshot.paramMap.get('id');
    this.poiService.getPoiById(id!).subscribe((data) => {
      this.poi = data;
    });
  } */
}
}
