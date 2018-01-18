import { Component, OnInit, ViewChild } from '@angular/core';
import { Modal } from 'clarity-angular';
import { BankAccountTransaction } from 'app/model/api/bankAccountTransaction';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { BankingService } from 'app/banking.service';
import { ObjectId } from 'app/model/api/objectId';

@Component({
  selector: 'app-add-edit-transaction',
  templateUrl: './add-edit-transaction.component.html',
  styleUrls: ['./add-edit-transaction.component.scss']
})
export class AddEditTransactionComponent implements OnInit {
  @ViewChild("modal") modal: Modal;

  private transactionTypes: string[] = ['Credit', "Debit"];
  private transaction: BankAccountTransaction;

  private formGroup: FormGroup;
  private opened: boolean = false;
  private isEdit: boolean = false;

  constructor(private formBuilder: FormBuilder, private bankingService: BankingService) { 
    this.transaction = this.defaultTransaction();
    this.formGroup = this.formBuilder.group({
      transactionTime: [, Validators.required],
      name: ['', Validators.required],
      transactionType: [this.transactionTypes[1], Validators.required],
      amount: ['', Validators.required]
    });
  }

  ngOnInit() {
  }

  private defaultTransaction(): BankAccountTransaction {
    return <BankAccountTransaction>{
      amount: 0,
      transactionTime: new Date(),
      name: '',
      accountId: ''
    }
  }

  private invalid(name: string): boolean {
    const control = this.formGroup.get(name);

    return control.invalid && (control.dirty || control.touched);
  }

  private submit(): void {
    this.transaction.name = this.formGroup.get('name').value;
    this.transaction.transactionTime = new Date(this.formGroup.get('transactionTime').value);

    const multiplier: number = (this.transactionTypes[0] == this.formGroup.get('transactionType').value) ? 1 : -1;
    this.transaction.amount = this.formGroup.get('amount').value * multiplier;

    if (this.transaction.id == null) {
      this.bankingService.addBankAccountTransaction(this.transaction).subscribe(
        data => {
          this.bankingService.modifyTransactions(this.transaction.accountId);
          this.close();
        }
      );
    } else {
      this.bankingService.updateBankAccountTransaction(this.transaction).subscribe(
        data => {
          this.bankingService.modifyTransactions(this.transaction.accountId);
          this.close();
        }
      );
    }
  }

  private close(): void {
    this.opened = false;
  }

  open(accountId: ObjectId, transaction: BankAccountTransaction = this.defaultTransaction()) {
    this.transaction = transaction;
    this.transaction.accountId = accountId;
    this.isEdit = (this.transaction.id != null);

    this.formGroup.reset({
      transactionTime: this.transaction.transactionTime,
      name: this.transaction.name,
      amount: Math.abs(this.transaction.amount),
      transactionType: this.transaction.amount > 0 ? this.transactionTypes[0] : this.transactionTypes[1]
    });

    this.opened = true;
  }
}
