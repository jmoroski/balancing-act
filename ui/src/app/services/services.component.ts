import { Component, OnInit, ViewChild } from '@angular/core';
import { Service } from 'app/model/api/service';
import { AddEditServiceComponent } from 'app/services/add-edit-service.component';
import { ServiceCompaniesService } from 'app/service-companies.service';

@Component({
  selector: 'app-services',
  templateUrl: './services.component.html',
  styleUrls: ['./services.component.scss'],
  providers: [ServiceCompaniesService]
})
export class ServicesComponent implements OnInit {
  private services: Service[];
  private loading: boolean = true;

  @ViewChild("addEditServiceComponent") addEditServiceComponent: AddEditServiceComponent;

  constructor(private serviceCompaniesService: ServiceCompaniesService) { }

  ngOnInit() {
    this.loadData();
  }

  loadData() {
    this.loading = true;
    this.serviceCompaniesService.getServices().subscribe(
      data => {
        this.services = data;
        this.loading = false;
      }
    );
  }

  addService() {
    this.addEditServiceComponent.open();
  }

  editService(service: Service) {
    this.addEditServiceComponent.open(service);
  }

  deleteService(service: Service) {
    this.serviceCompaniesService.deleteService(service).subscribe(
      data => {
        this.loadData();
      }
    );
  }

}
