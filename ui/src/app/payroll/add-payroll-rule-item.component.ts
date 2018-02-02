import { Component, OnInit, ViewChild } from '@angular/core';
import { Modal } from 'clarity-angular';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { PayrollRuleItem } from 'app/model/api/payrollRuleItem';
import { PayrollService } from 'app/payroll.service';
import { ObjectId } from 'app/model/api/objectId';
import { SimplePayrollRuleItem } from 'app/model/api/simplePayrollRuleItem';
import { CalculatedPayrollRuleItem } from 'app/model/api/calculatedPayrollRuleItem';

@Component({
  selector: 'app-add-payroll-rule-item',
  templateUrl: './add-payroll-rule-item.component.html',
  styleUrls: ['./add-payroll-rule-item.component.scss']
})
export class AddPayrollRuleItemComponent implements OnInit {
  @ViewChild("modal") modal: Modal;
  private formGroup: FormGroup;
  private opened: boolean;

  private payrollRuleItem: PayrollRuleItem;
  private itemTypes: String[] = ['Simple', 'Calulated'];

  constructor(private formBuilder: FormBuilder, private payrollService: PayrollService) {
    this.formGroup = this.formBuilder.group({
      description: ['', Validators.required],
      type: [this.itemTypes[0], Validators.required],
      rate: [''],
      quantity: [''],
      amount: ['']
    }, { validator: this.validateInputs() });
  }

  ngOnInit(): void {
  }

  private close(): void {
    this.opened = false;
  }

  open(payrollRuleId: ObjectId, payrollRuleItem: PayrollRuleItem = <PayrollRuleItem>{
  }) {
    this.payrollRuleItem = payrollRuleItem;
    this.payrollRuleItem.payrollRuleId = payrollRuleId;

    this.formGroup.reset({
      type: this.itemTypes[0]
    });

    this.opened = true;
  }

  private submit(): void {
    let payrollRuleItem: PayrollRuleItem;
    if (this.formGroup.get('type').value == this.itemTypes[0]) {
      payrollRuleItem = <SimplePayrollRuleItem>{
        amount: this.formGroup.get('amount').value,
        itemType: 'SimplePayrollRuleItem'
      };
    } else {
      payrollRuleItem = <CalculatedPayrollRuleItem>{
        quantity: this.formGroup.get('quantity').value,
        rate: this.formGroup.get('rate').value,
        itemType: 'CalculatedPayrollRuleItem'
      };
    }
    
    payrollRuleItem.description = this.formGroup.get('description').value;
    payrollRuleItem.payrollRuleId = this.payrollRuleItem.payrollRuleId;
    console.log(JSON.stringify(payrollRuleItem));

    this.payrollService.addPayrollRuleItem(payrollRuleItem).subscribe(
      data => {
        // this.serviceCompaniesService.modifyBillingRules(billingRuleItem.billingRuleId);
        this.close();
      }
    );
  }

  private invalid(name: string, groupError: string = null): boolean {
    const control = this.formGroup.get(name);

    return (control.invalid || this.formGroup.hasError(groupError)) && (control.dirty || control.touched);
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
