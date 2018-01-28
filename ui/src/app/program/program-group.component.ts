import { Component, OnInit, Input } from '@angular/core';
import { ProgramGroup } from 'app/model/api/programGroup';
import { ProgramService } from 'app/program.service';

@Component({
  selector: '[app-program-group]',
  templateUrl: './program-group.component.html',
  styleUrls: ['./program-group.component.scss']
})
export class ProgramGroupComponent implements OnInit {
  @Input("programGroup") programGroup: ProgramGroup;

  constructor(private programService: ProgramService) { }

  ngOnInit() {
  }

  editGroup() {
    console.log('editing ' + this.programGroup.name);
  }

  manageGroupStudents() {

  }

  deleteProgramGroup() {
    this.programService.deleteProgramGroup(this.programGroup).subscribe(() => {
      this.programService.onModifyProgramGroups();
    })
  }
}
