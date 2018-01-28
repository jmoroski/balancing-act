import { Component, OnInit, ViewChild } from '@angular/core';
import { Modal } from 'clarity-angular';
import { PayrollCompany } from 'app/model/api/payrollCompany';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { PayrollService } from 'app/payroll.service';

@Component({
  selector: 'app-add-edit-payroll-company',
  templateUrl: './add-edit-payroll-company.component.html',
  styleUrls: ['./add-edit-payroll-company.component.scss']
})
export class AddEditPayrollCompanyComponent implements OnInit {
  @ViewChild("modal") modal: Modal;
  private opened: boolean;
  private formGroup: FormGroup;
  
  private payrollCompany: PayrollCompany;

  constructor(private formBuilder: FormBuilder, private payrollService: PayrollService) {
    this.formGroup = this.formBuilder.group({
      name: ['', Validators.required],
      address1: [''],
      address2: [''],
      city: [''],
      state: [''],
      zip: ['', [Validators.pattern(/^\d{5}$/)]]
    });
  }

  ngOnInit() {
  }

  private defaultPayrollCompany(): PayrollCompany {
    return <PayrollCompany> {
      name: '',
      contactInfo: {}
    };
  }

  private invalid(name: string): boolean {
    const control = this.formGroup.get(name);

    return control.invalid && (control.dirty || control.touched);
  }

  private close(): void {
    this.opened = false;
  }

  open(payrollCompany: PayrollCompany = this.defaultPayrollCompany()) : void {
    this.payrollCompany = payrollCompany;

    this.formGroup.reset({
      name: payrollCompany.name,
      address1: payrollCompany.contactInfo.address1,
      address2: payrollCompany.contactInfo.address2,
      city: payrollCompany.contactInfo.city,
      state: payrollCompany.contactInfo.state,
      zip: payrollCompany.contactInfo.zip,
    });

    this.opened = true;
  }

  private submit(): void {
    this.payrollCompany.name = this.formGroup.get('name').value;
    this.payrollCompany.contactInfo.address1 = this.formGroup.get('address1').value;
    this.payrollCompany.contactInfo.address2 = this.formGroup.get('address2').value;
    this.payrollCompany.contactInfo.city = this.formGroup.get('city').value;
    this.payrollCompany.contactInfo.state = this.formGroup.get('state').value;
    this.payrollCompany.contactInfo.zip = this.formGroup.get('zip').value;

    if (this.payrollCompany.id == null) {
      this.payrollService.addPayrollCompany(this.payrollCompany).subscribe(payrollCompany => {
          this.payrollService.modifyPayrollCompanies();
          this.close();
        }
      );
    } else {
      this.payrollService.updatePayrollCompany(this.payrollCompany).subscribe(payrollCompany => {
          this.payrollService.modifyPayrollCompanies();
          this.close();
        }
      );
    }
  }
}
