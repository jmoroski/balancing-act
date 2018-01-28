import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BankingRoutingModule } from './banking-routing.module';
import { BaseModule } from 'app/base.module';
import { AccountDetailsComponent } from 'app/banking/account-details.component';
import { AddEditTransactionComponent } from 'app/banking/add-edit-transaction.component';
import { BankingComponent } from 'app/banking/banking.component';

@NgModule({
  imports: [
    BaseModule,
    BankingRoutingModule
  ],
  declarations: [
    AccountDetailsComponent,
    AddEditTransactionComponent,
    BankingComponent
  ]
})
export class BankingModule {
  constructor() {
    console.log("loading banking module");
  }
}
