import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PayrollCompaniesComponent } from 'app/payroll/payroll-companies.component';
import { PayrollRulesComponent } from 'app/payroll/payroll-rules.component';
import { PayrollRuleItemsComponent } from 'app/payroll/payroll-rule-items.component';

const routes: Routes = [
  {path: 'employers', component: PayrollCompaniesComponent},
  {path: 'employers/:id/payrollRules', component: PayrollRulesComponent},
  {path: 'employers/:id/payrollRules/:payrollRuleId', component: PayrollRuleItemsComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PayrollRoutingModule { }
