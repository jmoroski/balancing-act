import { Component, OnInit, ViewChild } from '@angular/core';
import { Modal } from 'clarity-angular';
import { FormGroup, FormBuilder } from '@angular/forms';
import { BillingRule } from 'app/model/api/billingRule';
import { ProgramGroup } from 'app/model/api/programGroup';
import { ProgramService } from 'app/program.service';

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

  private submit() {
    this.programService.setProgramGroupBillingRules(this.programGroup, <BillingRule[]>this.formGroup.get('billingRules').value).subscribe(() => {
      this.close();
    });
  }
}
