import { Component, OnInit, Input, ViewChild } from '@angular/core';
import { PayrollRule } from 'app/model/api/payrollRule';
import { ActivatedRoute } from '@angular/router';
import { PayrollService } from 'app/payroll.service';
import { SimplePayrollRuleItem } from 'app/model/api/simplePayrollRuleItem';
import { CalculatedPayrollRuleItem } from 'app/model/api/calculatedPayrollRuleItem';
import { PayrollRuleItem } from 'app/model/api/payrollRuleItem';
import { AddPayrollRuleItemComponent } from 'app/payroll/add-payroll-rule-item.component';

@Component({
  selector: 'app-payroll-rule-items',
  templateUrl: './payroll-rule-items.component.html',
  styleUrls: ['./payroll-rule-items.component.scss']
})
export class PayrollRuleItemsComponent implements OnInit {
  @Input() payrollRule: PayrollRule;
  @ViewChild("addComponent") addComponent: AddPayrollRuleItemComponent;
  private loading: boolean;

  constructor(private route: ActivatedRoute, private payrollService: PayrollService) { }

  ngOnInit(): void {
    this.loadData();
  }

  private loadData(): void {
    this.loading = true;
    const id: string = this.route.snapshot.paramMap.get('payrollRuleId');
    this.payrollService.getPayrollRule(id).subscribe(payrollRule => {
      this.payrollRule = payrollRule;
      this.loading = false;
    });
  }

  amount(item: PayrollRuleItem): number {
    return item['amount'] ? (<SimplePayrollRuleItem>item).amount : ((<CalculatedPayrollRuleItem>item).quantity * (<CalculatedPayrollRuleItem>item).rate);
  }

  private addPayrollRuleItem(): void {
    this.addComponent.open(this.payrollRule.id);
  }
}
