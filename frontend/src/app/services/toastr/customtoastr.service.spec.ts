import { TestBed } from '@angular/core/testing';

import { CustomToastrService } from './customtoastr.service';

describe('ToastrService', () => {
  let service: CustomToastrService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CustomToastrService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
