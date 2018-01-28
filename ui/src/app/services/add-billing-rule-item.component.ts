import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Modal } from 'clarity-angular';
import { BillingRuleItem } from 'app/model/api/billingRuleItem';
import { ObjectId } from 'app/model/api/objectId';
import { SimpleBillingRuleItem } from 'app/model/api/simpleBillingRuleItem';
import { CalculatedBillingRuleItem } from 'app/model/api/calculatedBillingRuleItem';

@Component({
  selector: 'app-add-billing-rule-item',
  templateUrl: './add-billing-rule-item.component.html',
  styleUrls: ['./add-billing-rule-item.component.scss']
})
export class AddBillingRuleItemComponent implements OnInit {
  @ViewChild("modal") modal: Modal;

  private itemTypes: String[] = ['Simple', 'Calulated']
  private billingRuleItem: BillingRuleItem;

  private formGroup: FormGroup;
  private opened: boolean = false;

  constructor(private formBuilder: FormBuilder) {
    this.formGroup = this.formBuilder.group({
      description: ['', Validators.required],
      type: [this.itemTypes[0], Validators.required],
      rate: [''],
      quantity: [''],
      amount: ['']
    }, { validator: this.validateInputs() });
  }

  ngOnInit() {
  }

  private invalid(name: string, groupError: string = null): boolean {
    const control = this.formGroup.get(name);

    return (control.invalid || this.formGroup.hasError(groupError)) && (control.dirty || control.touched);
  }

  private defaultBillingRuleItem(): BillingRuleItem {
    return <BillingRuleItem>{
    }
  }

  private close(): void {
    this.opened = false;
  }

  open(billingRuleId: ObjectId, billingRuleItem: BillingRuleItem = this.defaultBillingRuleItem()) {
    this.billingRuleItem = billingRuleItem;
    this.billingRuleItem.billingRuleId = billingRuleId;

    this.formGroup.reset({
      type: this.itemTypes[0]
    });

    this.opened = true;
  }

  private submit(): void {
    var billingRuleItem;
    if (this.formGroup.get('type').value == this.itemTypes[0]) {
      billingRuleItem = <SimpleBillingRuleItem>{
        amount: this.formGroup.get('amount').value
      };
    } else {
      billingRuleItem = <CalculatedBillingRuleItem>{
        quantity: this.formGroup.get('quantity').value,
        rate: this.formGroup.get('rate').value
      };
    }
    
    billingRuleItem.description = this.formGroup.get('description').value;
    billingRuleItem.billingRuleId = this.billingRuleItem.id;
    console.log(JSON.stringify(billingRuleItem));

    // this.serviceCompaniesService.addBillingRule(this.billingRule).subscribe(
    //   data => {
    //     this.serviceCompaniesService.modifyBillingRules(this.billingRule.serviceId);
    //     this.close();
    //   }
    // );
  }

  private validateInputs() {
    return (group: FormGroup): { [key: string]: any } => {
      const type = group.controls['type'].value;
      if (type == this.itemTypes[0]) {
        const amount = group.controls['amount'].value;
        if (!amount) {
          return { missingAmount: true };
        }
      } else {
        const rate = group.controls['rate'].value;
        const quantity = group.controls['quantity'].value;
        var returnValue = {};
        if (!rate) {
          returnValue['missingRate'] = true;
        }

        if (!quantity) {
          returnValue['missingQuantity'] = true;
        }

        return returnValue;
      }
    }
  }
}
