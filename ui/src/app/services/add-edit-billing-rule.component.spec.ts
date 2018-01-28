import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddEditBillingRuleComponent } from './add-edit-billing-rule.component';

describe('AddEditBillingRuleComponent', () => {
  let component: AddEditBillingRuleComponent;
  let fixture: ComponentFixture<AddEditBillingRuleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddEditBillingRuleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddEditBillingRuleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
