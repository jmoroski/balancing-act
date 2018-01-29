import { Component, OnInit, ViewChild } from '@angular/core';
import { Modal } from 'clarity-angular';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { UsersService } from 'app/users.service';
import { Administrator } from 'app/model/api/administrator';
import { ProgramGroup } from 'app/model/api/programGroup';
import { ProgramService } from 'app/program.service';

@Component({
  selector: 'app-add-edit-program-group',
  templateUrl: './add-edit-program-group.component.html',
  styleUrls: ['./add-edit-program-group.component.scss'],
  providers: [UsersService]
})
export class AddEditProgramGroupComponent implements OnInit {
  @ViewChild("modal") modal: Modal;

  private programGroup: ProgramGroup;
  private administrators: Administrator[] = [];

  private formGroup: FormGroup;
  private opened: boolean = false;
  private isEdit: boolean = false;

  constructor(private formBuilder: FormBuilder, private userService: UsersService, private programService: ProgramService) {
    this.formGroup = this.formBuilder.group({
      name: ['', Validators.required],
      administrator: [<Administrator>{}, Validators.required]
    });
  }

  ngOnInit(): void {
    
  }

  private defaultProgramGroup(): ProgramGroup {
    return <ProgramGroup>{
    };
  }

  private invalid(name: string): boolean {
    const control = this.formGroup.get(name);

    return control.invalid  && (control.dirty || control.touched);
  }

  private close(): void {
    this.opened = false;
  }

  open(programGroup: ProgramGroup = this.defaultProgramGroup()): void {
    this.programGroup = programGroup;
    this.formGroup.reset();
    this.opened = true;
    this.userService.getAdministrators().subscribe(administrators => {
      this.administrators = administrators;
    });
  }

  private submit(): void {
    this.programGroup.name = this.formGroup.get('name').value;
    this.programGroup.administrator = <Administrator>this.formGroup.get('administrator').value;

    if (this.programGroup.id == null) {
      this.programService.addProgramGroup(this.programGroup).subscribe(programGroup => {
        this.programService.onModifyProgramGroups();
        this.close();
      });
    }
  }
}
