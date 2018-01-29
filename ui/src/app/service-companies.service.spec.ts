import { TestBed, inject } from '@angular/core/testing';
import { ServiceCompaniesService } from 'app/service-companies.service';

describe('ServiceCompaniesService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ServiceCompaniesService]
    });
  });

  it('should be created', inject([ServiceCompaniesService], (service: ServiceCompaniesService) => {
    expect(service).toBeTruthy();
  }));
});
