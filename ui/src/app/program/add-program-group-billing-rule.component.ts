import { Component, OnInit, ViewChild } from '@angular/core';
import { Modal } from 'clarity-angular';
import { FormGroup, FormBuilder } from '@angular/forms';
import { BillingRule } from 'app/model/api/billingRule';
import { ProgramGroup } from 'app/model/api/programGroup';
import { ProgramService } from 'app/program.service';
import { BillingRuleItem } from 'app/model/api/billingRuleItem';
import { SimpleBillingRuleItem } from 'app/model/api/simpleBillingRuleItem';
import { CalculatedBillingRuleItem } from 'app/model/api/calculatedBillingRuleItem';

@Component({
  selector: 'app-add-program-group-billing-rule',
  templateUrl: './add-program-group-billing-rule.component.html',
  styleUrls: ['./add-program-group-billing-rule.component.scss']
})
export class AddProgramGroupBillingRuleComponent implements OnInit {
  @ViewChild("modal") modal: Modal;
  private formGroup: FormGroup;
  private opened: boolean;

  private billingRules: BillingRule[];
  private programGroup: ProgramGroup

  constructor(private formBuilder: FormBuilder, private programService: ProgramService) {
    this.formGroup = this.formBuilder.group({
      billingRules: []
    });
  }

  ngOnInit(): void {
  }

  private close(): void {
    this.opened = false;
  }

  open(programGroup: ProgramGroup): void {
    this.formGroup.reset();
    this.programGroup = programGroup;
    this.programService.getEligibleProgramGroupBillingRules(programGroup).subscribe(billingRules => {
      this.billingRules = billingRules;
    });

    this.opened = true;
  }

  private convertItems(items: BillingRuleItem[]): BillingRuleItem[] {
    const convertedItems: BillingRuleItem[] = [];

    for (let item of items) {
      if (item['amount']) {
        item.itemType = 'SimpleBillingRuleItem';
        convertedItems.push(<SimpleBillingRuleItem>item);
      } else {
        item.itemType = 'CalculatedBillingRuleItem';
        convertedItems.push(<CalculatedBillingRuleItem>item);
      }
    }

    return convertedItems;
  }

  private submit() {
    const billingRules: BillingRule[] = <BillingRule[]>this.formGroup.get('billingRules').value;
    for (let billingRule of billingRules) {
      billingRule.items = this.convertItems(billingRule.items);
    }

    this.programService.setProgramGroupBillingRules(this.programGroup, billingRules).subscribe(() => {
      this.close();
    });
  }
}
