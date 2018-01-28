import { Component, OnInit, ViewChild, Output, EventEmitter } from '@angular/core';
import { Modal } from 'clarity-angular';
import { Service } from 'app/model/api/service';
import { FormGroup, FormBuilder, Validators, AbstractControl } from '@angular/forms';
import { ServiceCompaniesService } from 'app/service-companies.service';

@Component({
  selector: 'app-add-edit-service',
  templateUrl: './add-edit-service.component.html',
  styleUrls: ['./add-edit-service.component.scss']
})
export class AddEditServiceComponent implements OnInit {
  @ViewChild("modal") modal: Modal;
  @Output() submitted = new EventEmitter<any>();

  private service: Service;

  private opened: boolean = false;

  formGroup: FormGroup;

  constructor(private formBuilder: FormBuilder, private serviceCompaniesService: ServiceCompaniesService) {
    this.service = this.defaultService();
    this.formGroup = this.formBuilder.group({
      name: ['', Validators.required],
      address1: [''],
      address2: [''],
      city: [''],
      state: [''],
      zip: ['', [Validators.pattern(/^\d{5}$/)]]
    });
  }

  get name(): AbstractControl {
    return this.formGroup.get('name');
  }

  get zip(): AbstractControl {
    return this.formGroup.get('zip');
  }

  close() {
    this.opened = false;
    this.formGroup.reset(this.service);
  }

  open(service: Service = this.defaultService()) {
    this.service = service;
    if (this.service.contactInfo == null) {
      this.service.contactInfo = {};
    }

    this.formGroup.reset({
      name: service.name,
      address1: service.contactInfo.address1,
      address2: service.contactInfo.address2,
      city: service.contactInfo.city,
      state: service.contactInfo.state,
      zip: service.contactInfo.zip,
    });
    this.opened = true;
  }

  submit() {
    this.service.name = this.name.value;
    if (this.service.contactInfo == null) {
      this.service.contactInfo = {};
    }
    this.service.contactInfo.address1 = this.formGroup.get('address1').value;
    this.service.contactInfo.address2 = this.formGroup.get('address2').value;
    this.service.contactInfo.city = this.formGroup.get('city').value;
    this.service.contactInfo.state = this.formGroup.get('state').value;
    this.service.contactInfo.zip = this.formGroup.get('zip').value;

    if (this.service.id == null) {
      this.serviceCompaniesService.addService(this.service).subscribe(
        data => {
          this.close();
          this.submitted.emit();
        }
      );
    } else {
      this.serviceCompaniesService.updateService(this.service).subscribe(
        data => {
          this.close();
          this.submitted.emit();
        }
      );
    }
  }

  private defaultService(): Service {
    return {
      name: '',
      contactInfo: {}
    }
  }

  isFormValid(): boolean {
    return this.formGroup.valid;
  }

  ngOnInit() {
  }
}
