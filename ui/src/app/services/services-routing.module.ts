import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ServicesComponent } from 'app/services/services.component';
import { BillingRulesComponent } from 'app/services/billing-rules.component';
import { BillingRuleItemsComponent } from 'app/services/billing-rule-items.component';

const routes: Routes = [
  {path: 'companies', component: ServicesComponent},
  {path: 'companies/:id/billingRules', component: BillingRulesComponent},
  {path: 'companies/:id/billingRules/:billingRuleId', component: BillingRuleItemsComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ServicesRoutingModule { }
