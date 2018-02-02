import { Component, OnInit, OnDestroy, Input, ViewChild } from '@angular/core';
import { PayrollService } from 'app/payroll.service';
import { Subscription } from 'rxjs/Subscription';
import { PayrollCompany } from 'app/model/api/payrollCompany';
import { PayrollRule } from 'app/model/api/payrollRule';
import { AddEditPayrollRuleComponent } from 'app/payroll/add-edit-payroll-rule.component';
import { ActivatedRoute } from '@angular/router';

import 'rxjs/add/operator/mergeMap';
import { CalculatedPayrollRuleItem } from 'app/model/api/calculatedPayrollRuleItem';
import { SimplePayrollRuleItem } from 'app/model/api/simplePayrollRuleItem';
import { PayrollRuleItem } from 'app/model/api/payrollRuleItem';

@Component({
  selector: 'app-payroll-rules',
  templateUrl: './payroll-rules.component.html',
  styleUrls: ['./payroll-rules.component.scss'],
  providers: [PayrollService]
})
export class PayrollRulesComponent implements OnInit, OnDestroy {
  private loading: boolean;
  private subscription: Subscription;

  @Input() private payrollCompany: PayrollCompany;
  @Input() private payrollRules: PayrollRule[];

  @ViewChild("addEditComponent") addEditComponent: AddEditPayrollRuleComponent;

  constructor(private route: ActivatedRoute, private payrollService: PayrollService) { }

  ngOnInit(): void {
    this.loadData();
  }

  ngOnDestroy(): void {
    // this.subscription.unsubscribe();
  }

  private loadData(): void {
    this.loading = true;
    const id: string = this.route.snapshot.paramMap.get('id');
    this.payrollService.getPayrollCompany(id).mergeMap(payrollCompany=> {
      this.payrollCompany = payrollCompany;
      return this.payrollService.getPayrollCompanyRules(payrollCompany);
    }).subscribe(payrollRules => {
      this.payrollRules = payrollRules;
      this.loading = false;
    });
  }

  private addPayrollRule(): void {
    this.addEditComponent.open(this.payrollCompany.id);
  }

  private editPayrollRule(payrollRule: PayrollRule): void {
    this.addEditComponent.open(this.payrollCompany.id, payrollRule);
  }

  private deletePayrollRule(payrollRule: PayrollRule): void {

  }

  totalAmount(payrollRule: PayrollRule): number {
    var amount: number = 0.0;
    if (!payrollRule.items) {
      return amount;
    }
    
    payrollRule.items.forEach(item => {
      if (item['quantity']) {
        amount += ((<CalculatedPayrollRuleItem>item).quantity * (<CalculatedPayrollRuleItem>item).rate);
      } else {
        amount += (<SimplePayrollRuleItem>item).amount;
      }
    });

    return amount;
  }

  amount(item: PayrollRuleItem): number {
    return item['amount'] ? (<SimplePayrollRuleItem>item).amount : ((<CalculatedPayrollRuleItem>item).quantity * (<CalculatedPayrollRuleItem>item).rate);
  }
}
