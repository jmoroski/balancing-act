import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BillingRuleItemsComponent } from './billing-rule-items.component';

describe('BillingRuleItemsComponent', () => {
  let component: BillingRuleItemsComponent;
  let fixture: ComponentFixture<BillingRuleItemsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BillingRuleItemsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BillingRuleItemsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
