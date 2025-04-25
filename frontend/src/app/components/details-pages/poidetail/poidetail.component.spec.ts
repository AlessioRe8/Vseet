import { ComponentFixture, TestBed } from '@angular/core/testing';

import { POIDetailComponent } from './poidetail.component';

describe('POIDetailComponent', () => {
  let component: POIDetailComponent;
  let fixture: ComponentFixture<POIDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [POIDetailComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(POIDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
