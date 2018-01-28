import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddBillingRuleItemComponent } from './add-billing-rule-item.component';

describe('AddBillingRuleItemComponent', () => {
  let component: AddBillingRuleItemComponent;
  let fixture: ComponentFixture<AddBillingRuleItemComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddBillingRuleItemComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddBillingRuleItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
