import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProgramRoutingModule } from './program-routing.module';
import { BaseModule } from 'app/base.module';
import { ProgramComponent } from 'app/program/program.component';
import { ProgramGroupComponent } from 'app/program/program-group.component';
import { ProgramGroupDetailsComponent } from 'app/program/program-group-details.component';
import { AddEditProgramGroupComponent } from './add-edit-program-group.component';
import { ProgramService } from 'app/program.service';
import { ProgramGroupReportsComponent } from './program-group-reports.component';
import { ProgramGroupStudentsComponent } from './program-group-students.component';
import { AddProgramGroupStudentsComponent } from './add-program-group-students.component';
import { ReportService } from 'app/report.service';

@NgModule({
  imports: [
    BaseModule,
    CommonModule,
    ProgramRoutingModule
  ],
  declarations: [
    ProgramComponent,
    ProgramGroupComponent,
    ProgramGroupDetailsComponent,
    AddEditProgramGroupComponent,
    ProgramGroupReportsComponent,
    ProgramGroupStudentsComponent,
    AddProgramGroupStudentsComponent
  ],
  providers: [
    ProgramService,
    ReportService
  ]
})
export class ProgramModule { }
