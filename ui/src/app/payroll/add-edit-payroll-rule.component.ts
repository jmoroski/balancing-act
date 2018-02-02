import { Component, OnInit, ViewChild } from '@angular/core';
import { Modal } from 'clarity-angular';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { PayrollRule } from 'app/model/api/payrollRule';
import { PayrollService } from 'app/payroll.service';
import { ObjectId } from 'app/model/api/objectId';
import { RuleFrequency } from 'app/model/api/ruleFrequency';

@Component({
  selector: 'app-add-edit-payroll-rule',
  templateUrl: './add-edit-payroll-rule.component.html',
  styleUrls: ['./add-edit-payroll-rule.component.scss']
})
export class AddEditPayrollRuleComponent implements OnInit {
  @ViewChild("modal") modal: Modal;
  private opened: boolean;
  private isEdit: boolean;
  private formGroup: FormGroup;

  private payrollRule: PayrollRule;
  private frequencies: RuleFrequency[] = RuleFrequency.values();

  constructor(private formBuilder: FormBuilder, private payrollService: PayrollService) {
    this.formGroup = this.formBuilder.group({
      frequency: [RuleFrequency.Monthly, Validators.required],
      startDate: ['', Validators.required],
      endDate: ['', Validators.required],
      name: ['', Validators.required]
    });
  }

  ngOnInit(): void {
  }

  private defaultPayrollRule(): PayrollRule {
    return <PayrollRule>{
      frequency: RuleFrequency.Monthly
    }
  }

  private invalid(name: string): boolean {
    const control = this.formGroup.get(name);

    return control.invalid && (control.dirty || control.touched);
  }

  private close(): void {
    this.opened = false;
  }

  open(payrollId: ObjectId, payrollRule: PayrollRule = this.defaultPayrollRule()) {
    this.payrollRule = payrollRule;
    this.payrollRule.payrollId = payrollId;
    this.isEdit = (this.payrollRule.id != null);

    this.formGroup.reset({
      frequency: this.payrollRule.frequency,
      name: this.payrollRule.name,
      startDate: this.payrollRule.startDate,
      endDate: this.payrollRule.endDate
    });

    this.opened = true;
  }

  private submit(): void {
    this.payrollRule.name = this.formGroup.get('name').value;
    this.payrollRule.startDate = new Date(this.formGroup.get('startDate').value);
    this.payrollRule.endDate = new Date(this.formGroup.get('endDate').value);
    this.payrollRule.frequency = this.formGroup.get('frequency').value;

    if (this.payrollRule.id == null) {
      this.payrollService.addPayrollRule(this.payrollRule).subscribe(
        data => {
          // this.serviceCompaniesService.modifyBillingRules(this.payrollRule.serviceId);
          this.close();
        }
      );
    } else {
      this.payrollService.updatePayrollRule(this.payrollRule).subscribe(
        data => {
          // this.serviceCompaniesService.modifyBillingRules(this.billingRule.serviceId);
          this.close();
        }
      );
    }
  }
}
