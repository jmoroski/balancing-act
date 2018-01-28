import { Component, OnInit, ViewChild } from '@angular/core';
import { Modal } from 'clarity-angular';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { BillingRule } from 'app/model/api/billingRule';
import { ServiceCompaniesService } from 'app/service-companies.service';
import { ObjectId } from 'app/model/api/objectId';
import { TaskFrequency } from 'app/model/api/taskFrequency';

@Component({
  selector: 'app-add-edit-billing-rule',
  templateUrl: './add-edit-billing-rule.component.html',
  styleUrls: ['./add-edit-billing-rule.component.scss']
})
export class AddEditBillingRuleComponent implements OnInit {
  @ViewChild("modal") modal: Modal;

  private billingRule: BillingRule;
  private frequencies: TaskFrequency[] = TaskFrequency.values();

  private formGroup: FormGroup;
  private opened: boolean = false;
  private isEdit: boolean = false;

  constructor(private formBuilder: FormBuilder, private serviceCompaniesService: ServiceCompaniesService) {
    this.formGroup = this.formBuilder.group({
      frequency: [TaskFrequency.Monthly, Validators.required],
      startDate: ['', Validators.required],
      endDate: ['', Validators.required],
      name: ['', Validators.required]
    });
  }

  ngOnInit() {
  }

  private defaultBillingRule(): BillingRule {
    return <BillingRule>{
      frequency: TaskFrequency.Monthly
    }
  }

  private invalid(name: string): boolean {
    const control = this.formGroup.get(name);

    return control.invalid && (control.dirty || control.touched);
  }

  private close(): void {
    this.opened = false;
  }

  open(serviceId: ObjectId, billingRule: BillingRule = this.defaultBillingRule()) {
    this.billingRule = billingRule;
    this.billingRule.serviceId = serviceId;
    this.isEdit = (this.billingRule.id != null);

    this.formGroup.reset({
      frequency: this.billingRule.frequency,
      name: this.billingRule.name,
      startDate: this.billingRule.startDate,
      endDate: this.billingRule.endDate
    });

    this.opened = true;
  }

  private submit(): void {
    this.billingRule.name = this.formGroup.get('name').value;
    this.billingRule.startDate = new Date(this.formGroup.get('startDate').value);
    this.billingRule.endDate = new Date(this.formGroup.get('endDate').value);
    this.billingRule.frequency = this.formGroup.get('frequency').value;

    if (this.billingRule.id == null) {
      this.serviceCompaniesService.addBillingRule(this.billingRule).subscribe(
        data => {
          this.serviceCompaniesService.modifyBillingRules(this.billingRule.serviceId);
          this.close();
        }
      );
    } else {
      this.serviceCompaniesService.updateBillingRule(this.billingRule).subscribe(
        data => {
          this.serviceCompaniesService.modifyBillingRules(this.billingRule.serviceId);
          this.close();
        }
      );
    }
  }
}