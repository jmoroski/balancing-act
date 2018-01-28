import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PayrollRoutingModule } from './payroll-routing.module';
import { PayrollCompaniesComponent } from './payroll-companies.component';
import { PayrollRulesComponent } from './payroll-rules.component';
import { PayrollRuleItemsComponent } from './payroll-rule-items.component';
import { AddEditPayrollCompanyComponent } from './add-edit-payroll-company.component';
import { AddEditPayrollRuleComponent } from './add-edit-payroll-rule.component';
import { AddPayrollRuleItemComponent } from './add-payroll-rule-item.component';
import { BaseModule } from 'app/base.module';

@NgModule({
  imports: [
    BaseModule,
    CommonModule,
    PayrollRoutingModule
  ],
  declarations: [PayrollCompaniesComponent, PayrollRulesComponent, PayrollRuleItemsComponent, AddEditPayrollCompanyComponent, AddEditPayrollRuleComponent, AddPayrollRuleItemComponent]
})
export class PayrollModule { }
