import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProgramRoutingModule } from './program-routing.module';
import { BaseModule } from 'app/base.module';
import { ProgramComponent } from 'app/program/program.component';
import { ProgramGroupComponent } from 'app/program/program-group.component';
import { ProgramGroupDetailsComponent } from 'app/program/program-group-details.component';
import { AddEditProgramGroupComponent } from './add-edit-program-group.component';
import { ProgramService } from 'app/program.service';

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
    AddEditProgramGroupComponent
  ],
  providers: [
    ProgramService
  ]
})
export class ProgramModule { }
