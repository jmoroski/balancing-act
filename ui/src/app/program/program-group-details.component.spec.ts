import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProgramGroupDetailsComponent } from './program-group-details.component';

describe('ProgramGroupDetailsComponent', () => {
  let component: ProgramGroupDetailsComponent;
  let fixture: ComponentFixture<ProgramGroupDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProgramGroupDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProgramGroupDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
