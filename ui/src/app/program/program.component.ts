import { Component, OnInit, ViewChild, OnDestroy } from '@angular/core';
import { AddEditProgramGroupComponent } from 'app/program/add-edit-program-group.component';
import { ProgramService } from 'app/program.service';
import { ProgramGroup } from 'app/model/api/programGroup';
import { Subscription } from 'rxjs/Subscription';

@Component({
  selector: 'app-program',
  templateUrl: './program.component.html',
  styleUrls: ['./program.component.scss'],
  providers: [ProgramService]
})
export class ProgramComponent implements OnInit, OnDestroy {
  @ViewChild("addEditComponent") addEditComponent: AddEditProgramGroupComponent;

  private programGroups: ProgramGroup[];

  private loading: boolean = false;
  private subscription: Subscription;
  
  constructor(private programService: ProgramService) {
    this.subscription = this.programService.programGroupsModified().subscribe(() => {
      this.loadData();
    });
   }

  ngOnInit(): void {
    this.loadData();
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  private addProgramGroup() {
    this.addEditComponent.open();
  }

  private loadData(): void {
    this.loading = true;
    this.programService.getProgramGroups().subscribe(programGroups => {
      this.programGroups = programGroups;
      this.loading = false;
    });
  }
}
