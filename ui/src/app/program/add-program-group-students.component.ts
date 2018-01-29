import { Component, OnInit, ViewChild } from '@angular/core';
import { Modal } from 'clarity-angular';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Student } from 'app/model/api/student';
import { ProgramService } from 'app/program.service';
import { ProgramGroup } from 'app/model/api/programGroup';

@Component({
  selector: 'app-add-program-group-students',
  templateUrl: './add-program-group-students.component.html',
  styleUrls: ['./add-program-group-students.component.scss']
})
export class AddProgramGroupStudentsComponent implements OnInit {
  @ViewChild("modal") modal: Modal;
  private formGroup: FormGroup;
  private opened: boolean = false;

  private students: Student[];
  private programGroup: ProgramGroup;

  constructor(private formBuilder: FormBuilder, private programService: ProgramService) { 
    this.formGroup = this.formBuilder.group({
      students: []
    });
  }

  ngOnInit() {
  }

  private close(): void {
    this.opened = false;
  }

  open(programGroup: ProgramGroup): void {
    this.formGroup.reset();
    this.programGroup = programGroup;
    this.programService.getEligibleProgramGroupStudents(programGroup).subscribe(students => {
      this.students = students;
    });
    this.opened = true;
  }

  private submit() {
    this.programService.setProgramGroupStudents(this.programGroup, <Student[]>this.formGroup.get('students').value).subscribe(() => {
      this.close();
      this.programService.modifyProgramGroupStudents();
    });
  }
}
