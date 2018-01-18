import { Component, OnInit } from '@angular/core';
import { BankAccountSummary } from 'app/model/api/bankAccountSummary';
import { BankingService } from 'app/banking.service';

@Component({
  selector: 'app-banking',
  templateUrl: './banking.component.html',
  styleUrls: ['./banking.component.scss'],
  providers: [BankingService]
})
export class BankingComponent implements OnInit {
  private loading: boolean = true;

  accountSummaries: BankAccountSummary[];

  constructor(private bankingService: BankingService) { }

  ngOnInit() {
    this.loadData();
  }

  private loadData(): void {
    this.loading = true;
    this.bankingService.getBankAccountSummaries().subscribe(
      accountSummaries => {
        this.accountSummaries = accountSummaries;
        this.loading = false;
      }
    );
  }
}
