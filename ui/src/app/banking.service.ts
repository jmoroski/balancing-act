import { Injectable } from '@angular/core';
import { BaseService } from 'app/base.service';
import { HttpClient } from '@angular/common/http';
import { BankAccountSummary } from 'app/model/api/bankAccountSummary';
import { Observable } from 'rxjs/Observable';
import { BankAccount } from 'app/model/api/bankAccount';
import { BankAccountTransaction } from 'app/model/api/bankAccountTransaction';
import { ObjectId } from 'app/model/api/objectId';
import { Subject } from 'rxjs/Subject';

@Injectable()
export class BankingService extends BaseService {
  private transactionsModifiedSource: Subject<any> = new Subject<any>();
  private transactionsModified$: Observable<any>;

  constructor(private http: HttpClient) {
    super();
    this.transactionsModified$ = this.transactionsModifiedSource.asObservable();
  }

  getBankAccountSummaries(): Observable<BankAccountSummary[]> {
    return this.http.get(`${this.basePath}/banking/accounts`) as Observable<BankAccountSummary[]>;
  }

  getBankAccount(id: ObjectId): Observable<BankAccountSummary> {
    return this.http.get(`${this.basePath}/banking/accounts/${id}`) as Observable<BankAccountSummary>;
  }

  getBankAccountTransactions(id: ObjectId): Observable<BankAccountTransaction[]> {
    return this.http.get(`${this.basePath}/banking/accounts/${id}/transactions`) as Observable<BankAccountTransaction[]>;
  }

  addBankAccountTransaction(transaction: BankAccountTransaction): Observable<BankAccountTransaction> {
    return this.http.post(`${this.basePath}/banking/accounts/${transaction.accountId}/transactions`, transaction) as Observable<BankAccountTransaction>;
  }

  updateBankAccountTransaction(transaction: BankAccountTransaction): Observable<BankAccountTransaction> {
    return this.http.put(`${this.basePath}/banking/transactions/${transaction.id}`, transaction) as Observable<BankAccountTransaction>;
  }

  deleteBankAccountTransaction(transaction: BankAccountTransaction): Observable<any> {
    return this.http.delete(`${this.basePath}/banking/transactions/${transaction.id}`) as Observable<any>;
  }

  modifyTransactions(accountId: ObjectId): void {
    this.transactionsModifiedSource.next(accountId);
  }

  transactionsModified(): Observable<any> {
    return this.transactionsModified$;
  }
}
