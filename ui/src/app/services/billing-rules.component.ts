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
  @Input() private billingRules: BillingRule[] = [
    {
      name: "Utility Bill Group 2", serviceName: "Howard Water Company", startDate: new Date("01/01/2018"), endDate: new Date("04/01/2018"), frequency: TaskFrequency.Monthly,
      items: [
        <SimpleBillingRuleItem>{description: "Service Charge", amount: 15}, 
        <CalculatedBillingRuleItem>{description: "Gallons", quantity: 2000, rate: 0.007036},
        <CalculatedBillingRuleItem>{description: "Gallons", quantity: 1000, rate: 0.00816}
      ]
    },
    {
      name: "Utility Bill", serviceName: "McGonigal Electric", startDate: new Date("01/01/2018"), endDate: new Date("06/01/2018"), frequency: TaskFrequency.Monthly,
      items: [
        <CalculatedBillingRuleItem>{description: "Usage charge", quantity: 164, rate: 0.153}, 
        <CalculatedBillingRuleItem>{description: "Transmission charge", quantity: 164, rate: 0.0266},
        <CalculatedBillingRuleItem>{description: "Distribution charge", quantity: 164, rate: 0.0233},
        <CalculatedBillingRuleItem>{description: "Transition fee", quantity: 164, rate: 0.0137},
      ]
    },
  ];

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
    var amount = 0;
    if (!billingRule.items) {
      return amount;
    }
    
    billingRule.items.forEach(item => {
      amount += (<SimpleBillingRuleItem>item).amount;
    });

    return amount;
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
