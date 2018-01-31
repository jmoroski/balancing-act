import { Component, OnInit, Input, OnChanges, SimpleChanges, ViewChild } from '@angular/core';
import { ProgramGroup } from 'app/model/api/programGroup';
import { BillingRule } from 'app/model/api/billingRule';
import { Subscription } from 'rxjs/Subscription';
import { ProgramService } from 'app/program.service';
import { AddProgramGroupBillingRuleComponent } from 'app/program/add-program-group-billing-rule.component';
import { CalculatedBillingRuleItem } from 'app/model/api/calculatedBillingRuleItem';
import { SimpleBillingRuleItem } from 'app/model/api/simpleBillingRuleItem';
import { BillingRuleItem } from 'app/model/api/billingRuleItem';

@Component({
  selector: 'app-program-group-services',
  templateUrl: './program-group-services.component.html',
  styleUrls: ['./program-group-services.component.scss']
})
export class ProgramGroupServicesComponent implements OnInit, OnChanges {
  @Input() programGroup: ProgramGroup;
  @ViewChild("addComponent") addComponent: AddProgramGroupBillingRuleComponent;

  private loading: boolean;
  private billingRules: BillingRule[];
  private subscription: Subscription;

  constructor(private programService: ProgramService) { }

  ngOnInit(): void {
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (this.programGroup) {
      this.loadBillingRules();
    }
  }

  private addBillingRule(): void {
    this.addComponent.open(this.programGroup);
  }

  private loadBillingRules(): void {
    this.loading = true;
    this.programService.getProgramGroupBillingRules(this.programGroup).subscribe(billingRules => {
      this.billingRules = billingRules;
      this.loading = false;
    });
  }

  totalAmount(billingRule: BillingRule): number {
    var amount: number = 0.0;
    if (!billingRule.items) {
      return amount;
    }
    
    billingRule.items.forEach(item => {
      if (item['quantity']) {
        amount += ((<CalculatedBillingRuleItem>item).quantity * (<CalculatedBillingRuleItem>item).rate);
      } else {
        amount += (<SimpleBillingRuleItem>item).amount;
      }
    });

    return amount;
  }

  amount(item: BillingRuleItem): number {
    return item['amount'] ? (<SimpleBillingRuleItem>item).amount : ((<CalculatedBillingRuleItem>item).quantity * (<CalculatedBillingRuleItem>item).rate);
  }
}
