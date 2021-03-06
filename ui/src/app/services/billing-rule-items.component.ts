import { Component, OnInit, Input, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ServiceCompaniesService } from 'app/service-companies.service';
import { BillingRule } from 'app/model/api/billingRule';
import { AddBillingRuleItemComponent } from 'app/services/add-billing-rule-item.component';
import { BillingRuleItem } from 'app/model/api/billingRuleItem';

import 'rxjs/add/operator/mergeMap';
import { SimpleBillingRuleItem } from 'app/model/api/simpleBillingRuleItem';
import { CalculatedBillingRuleItem } from 'app/model/api/calculatedBillingRuleItem';

@Component({
  selector: 'app-billing-rule-items',
  templateUrl: './billing-rule-items.component.html',
  styleUrls: ['./billing-rule-items.component.scss'],
  providers: [ServiceCompaniesService]
})
export class BillingRuleItemsComponent implements OnInit {
  private loading: boolean = false;

  @Input() billingRule: BillingRule;

  @ViewChild("addBillingRuleItemComponent") addBillingRuleItemComponent: AddBillingRuleItemComponent;

  constructor(private route: ActivatedRoute, private serviceCompaniesService: ServiceCompaniesService) { }

  ngOnInit() {
    this.loadData();
  }

  private loadData(): void {
    this.loading = true;
    const id: string = this.route.snapshot.paramMap.get('billingRuleId');
    this.serviceCompaniesService.getBillingRule(id).subscribe(billingRule => {
      this.billingRule = billingRule;
      this.loading = false;
    });
  }

  private addBillingRuleItem() {
    this.addBillingRuleItemComponent.open(this.billingRule.id);
  }

  amount(item: BillingRuleItem): number {
    return item['amount'] ? (<SimpleBillingRuleItem>item).amount : ((<CalculatedBillingRuleItem>item).quantity * (<CalculatedBillingRuleItem>item).rate);
  }
}
