import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ServicesRoutingModule } from './services-routing.module';
import { BaseModule } from 'app/base.module';
import { AddBillingRuleItemComponent } from 'app/services/add-billing-rule-item.component';
import { AddEditBillingRuleComponent } from 'app/services/add-edit-billing-rule.component';
import { AddEditServiceComponent } from 'app/services/add-edit-service.component';
import { BillingRuleItemsComponent } from 'app/services/billing-rule-items.component';
import { BillingRulesComponent } from 'app/services/billing-rules.component';
import { ServicesComponent } from 'app/services/services.component';

@NgModule({
  imports: [
    BaseModule,
    ServicesRoutingModule
  ],
  declarations: [
    AddBillingRuleItemComponent,
    AddEditBillingRuleComponent,
    AddEditServiceComponent,
    BillingRuleItemsComponent,
    BillingRulesComponent,
    ServicesComponent
  ]
})
export class ServicesModule { }
