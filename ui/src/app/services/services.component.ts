import { Component, OnInit, ViewChild } from '@angular/core';
import { Service } from 'app/model/api/service';
import { AddEditServiceComponent } from 'app/services/add-edit-service.component';
import { ServicesService } from 'app/services.service';

@Component({
  selector: 'app-services',
  templateUrl: './services.component.html',
  styleUrls: ['./services.component.scss'],
  providers: [ServicesService]
})
export class ServicesComponent implements OnInit {
  private services: Service[];
  private loading: boolean = true;

  @ViewChild("addEditServiceComponent") addEditServiceComponent: AddEditServiceComponent;

  constructor(private servicesService: ServicesService) { }

  ngOnInit() {
    this.loadData();
  }

  addressString(service: Service): string {
    if (service.contactInfo == null) {
      return '';
    }

    var address: string = '';
    if (service.contactInfo.address1 != null) {
      address += `${service.contactInfo.address1}`
      address += '<br />';
    }
    if (service.contactInfo.address2 != null) {
      address += `${service.contactInfo.address2}`;
      address += '<br />';
    }

    if (service.contactInfo.city != null) {
      address += `${service.contactInfo.city}, ${service.contactInfo.state} ${service.contactInfo.zip}`;
    }

    return address;
  }

  loadData() {
    this.loading = true;
    this.servicesService.getServices().subscribe(
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
    this.servicesService.deleteService(service).subscribe(
      data => {
        this.loadData();
      }
    );
  }

}
