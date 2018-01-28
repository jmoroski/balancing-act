import { Component, OnInit, ViewChild, OnDestroy } from '@angular/core';
import { PayrollCompany } from 'app/model/api/payrollCompany';
import { AddEditPayrollCompanyComponent } from 'app/payroll/add-edit-payroll-company.component';
import { PayrollService } from 'app/payroll.service';
import { Subscription } from 'rxjs/Subscription';

@Component({
  selector: 'app-payroll-companies',
  templateUrl: './payroll-companies.component.html',
  styleUrls: ['./payroll-companies.component.scss'],
  providers: [PayrollService]
})
export class PayrollCompaniesComponent implements OnInit, OnDestroy {
  private payrollCompanies: PayrollCompany[];
  private loading: boolean;
  private subscription: Subscription;

  @ViewChild("addEditPayrollCompanyComponent") addEditPayrollCompanyComponent: AddEditPayrollCompanyComponent;

  constructor(private payrollService: PayrollService) {
    this.subscription = this.payrollService.payrollCompaniesModified().subscribe(() => {
      this.loadData();
    });
  }

  ngOnInit(): void {
    this.loadData();
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  private loadData() {
    this.loading = true;
    this.payrollService.getPayrollCompanies().subscribe(payrollCompanies => {
      this.payrollCompanies = payrollCompanies;
      this.loading = false;
    });
  }

  private addPayrollCompany(): void {
    this.addEditPayrollCompanyComponent.open();
  }

  private editPayrollCompany(payrollCompany: PayrollCompany): void {
    this.addEditPayrollCompanyComponent.open(payrollCompany);
  }

  private deletePayrollCompany(payrollCompany: PayrollCompany): void {
    this.payrollService.deletePayrollCompany(payrollCompany).subscribe(() => {
      this.loadData();
    });
  }
}
