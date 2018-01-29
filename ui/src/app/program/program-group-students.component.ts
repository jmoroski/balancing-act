import { Component, OnInit, Input, OnChanges, SimpleChanges, ViewChild, OnDestroy } from '@angular/core';
import { ProgramGroup } from 'app/model/api/programGroup';
import { Student } from 'app/model/api/student';
import { ProgramService } from 'app/program.service';
import { AddProgramGroupStudentsComponent } from 'app/program/add-program-group-students.component';
import { Subscription } from 'rxjs/Subscription';

@Component({
  selector: 'app-program-group-students',
  templateUrl: './program-group-students.component.html',
  styleUrls: ['./program-group-students.component.scss']
})
export class ProgramGroupStudentsComponent implements OnInit, OnChanges, OnDestroy {
  @Input() programGroup: ProgramGroup;
  @ViewChild("addComponent") addComponent: AddProgramGroupStudentsComponent;

  private loading: boolean;
  private students: Student[];
  private subscription: Subscription;

  constructor(private programService: ProgramService) {
    this.subscription = this.programService.programGroupStudentsModified().subscribe(() => {
      this.loadStudents();
    });
  }

  ngOnInit(): void {
  }

  ngOnChanges(changes: SimpleChanges): void {
    if(this.programGroup) {
      this.loadStudents();
    }
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  private addStudent(): void {
    this.addComponent.open(this.programGroup);
  }

  private loadStudents(): void {
    this.loading = true;
    this.programService.getProgramGroupStudents(this.programGroup).subscribe(students => {
      this.students = students;
      this.loading = false;
    });
  }
}
