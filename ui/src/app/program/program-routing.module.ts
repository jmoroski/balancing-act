import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ProgramComponent } from 'app/program/program.component';
import { ProgramGroupDetailsComponent } from 'app/program/program-group-details.component';

const routes: Routes = [
  {path: '', component: ProgramComponent},
  {path: ':id',  component: ProgramGroupDetailsComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProgramRoutingModule { }
