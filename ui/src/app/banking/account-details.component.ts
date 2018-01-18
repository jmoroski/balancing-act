import { Component, OnInit, Input, ViewChild, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BankingService } from 'app/banking.service';
import { BankAccountTransaction } from 'app/model/api/bankAccountTransaction';

import {mergeMap} from 'rxjs/operators';
import { BankAccountSummary } from 'app/model/api/bankAccountSummary';
import { AddEditTransactionComponent } from 'app/banking/add-edit-transaction.component';
import { Subscription } from 'rxjs/Subscription';

@Component({
  selector: 'app-account-details',
  templateUrl: './account-details.component.html',
  styleUrls: ['./account-details.component.scss'],
  providers: [BankingService]
})
export class AccountDetailsComponent implements OnInit, OnDestroy {
  @Input() bankAccount: BankAccountSummary;
  @Input() transactions: BankAccountTransaction[];

  private subscription: Subscription;
  private transactionsLoading: boolean = true;

  @ViewChild("addEditTransactionComponent") addEditTransactionComponent: AddEditTransactionComponent;

  constructor(private route: ActivatedRoute, private bankingService: BankingService) {
    this.subscription = this.bankingService.transactionsModified().subscribe(
      accountId => {
        this.loadData();
      }
    );
  }

  ngOnInit(): void {
    this.loadData();
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  private loadData(): void {
    this.transactionsLoading = true;
    const id: string = this.route.snapshot.paramMap.get('id');
    this.bankingService.getBankAccount(id).subscribe(
      bankAccount => {
        this.bankAccount = bankAccount;
        this.bankingService.getBankAccountTransactions(this.bankAccount.accountId).subscribe(
          transactions => {
            this.transactions = transactions;
            this.transactionsLoading = false;
          }
        )
      }
    );
  }

  addTransaction() {
    this.addEditTransactionComponent.open(this.bankAccount.accountId);
  }

  editTransaction(transaction: BankAccountTransaction) {
    this.addEditTransactionComponent.open(this.bankAccount.accountId, transaction);
  }

  deleteTransaction(transaction: BankAccountTransaction) {
    this.bankingService.deleteBankAccountTransaction(transaction).subscribe(
      () => {
        this.loadData();
      }
    );
  }
}
