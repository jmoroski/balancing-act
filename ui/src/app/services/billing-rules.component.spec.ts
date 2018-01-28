import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BillingRulesComponent } from './billing-rules.component';

describe('BillingRulesComponent', () => {
  let component: BillingRulesComponent;
  let fixture: ComponentFixture<BillingRulesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BillingRulesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BillingRulesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
