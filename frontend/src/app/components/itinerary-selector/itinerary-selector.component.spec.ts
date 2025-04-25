import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ItinerarySelectorComponent } from './itinerary-selector.component';

describe('ItinerarySelectorComponent', () => {
  let component: ItinerarySelectorComponent;
  let fixture: ComponentFixture<ItinerarySelectorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ItinerarySelectorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ItinerarySelectorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
