import { TestBed, inject } from '@angular/core/testing';

import { JackboxService } from './jackbox.service';

describe('JackboxService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [JackboxService]
    });
  });

  it('should be created', inject([JackboxService], (service: JackboxService) => {
    expect(service).toBeTruthy();
  }));
});
