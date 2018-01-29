import { Component, OnInit, Input, ViewChild, OnDestroy } from '@angular/core';
import { BillingRule } from 'app/model/api/billingRule';
import { ActivatedRoute } from '@angular/router';
import { ServiceCompaniesService } from 'app/service-companies.service';
import { Service } from 'app/model/api/service';
import { AddEditBillingRuleComponent } from 'app/services/add-edit-billing-rule.component';
import { Subscription } from 'rxjs/Subscription';
import { TaskFrequency } from 'app/model/api/taskFrequency';

import 'rxjs/add/operator/switchMap';
import { SimpleBillingRuleItem } from 'app/model/api/simpleBillingRuleItem';
import { CalculatedBillingRuleItem } from 'app/model/api/calculatedBillingRuleItem';
import { BillingRuleItem } from 'app/model/api/billingRuleItem';

@Component({
  selector: 'app-billing-rules',
  templateUrl: './billing-rules.component.html',
  styleUrls: ['./billing-rules.component.scss'],
  providers: [ServiceCompaniesService]
})
export class BillingRulesComponent implements OnInit, OnDestroy {
  private loading: boolean;
  private subscription: Subscription;

  @Input() private service: Service;
  @Input() private billingRules: BillingRule[];

  @ViewChild("addEditBillingRuleComponent") addEditBillingRuleComponent: AddEditBillingRuleComponent;

  constructor(private route: ActivatedRoute, private serviceCompaniesService: ServiceCompaniesService) {
    this.subscription = this.serviceCompaniesService.billingRulesModified().subscribe(
      serviceId => {
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

  loadData(): void {
    this.loading = true;
    const id: string = this.route.snapshot.paramMap.get('id');
    this.serviceCompaniesService.getService(id).switchMap(service => {
        this.service = service;
        return this.serviceCompaniesService.getBillingRules(this.service);
      }
    ).subscribe(billingRules => {
        this.billingRules = billingRules;
        this.loading = false;
      }
    );
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

  private addBillingRule(): void {
    this.addEditBillingRuleComponent.open(this.service.id);
  }

  private editBillingRule(billingRule: BillingRule): void {
    this.addEditBillingRuleComponent.open(this.service.id, billingRule);
  }

  private deleteBillingRule(billingRule: BillingRule): void {
    this.serviceCompaniesService.deleteBillingRule(billingRule).subscribe(
      () => {
        this.serviceCompaniesService.modifyBillingRules(billingRule.serviceId);
      }
    );
  }
}
