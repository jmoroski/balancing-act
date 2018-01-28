import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BankingComponent } from 'app/banking/banking.component';
import { AccountDetailsComponent } from 'app/banking/account-details.component';

const routes: Routes = [
  {path: 'accounts', component: BankingComponent},
  {path: 'accounts/:id', component: AccountDetailsComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BankingRoutingModule { }
