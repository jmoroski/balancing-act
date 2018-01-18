import { Component, OnInit, ViewChild, Output, EventEmitter } from '@angular/core';
import { FormGroup, FormControl, Validators, FormBuilder, AbstractControl } from '@angular/forms';
import { UsersService } from 'app/users.service';
import { Modal } from 'clarity-angular';
import { User } from 'app/model/api/user';

@Component({
  selector: 'app-add-edit-user',
  templateUrl: './add-edit-user.component.html',
  styleUrls: ['./add-edit-user.component.scss']
})
export class AddEditUserComponent implements OnInit {
  @ViewChild("modal") modal: Modal;

  private userTypes: String[] = ['Student', 'Administrator'];
  private user: User;

  private opened: boolean = false;

  formGroup: FormGroup;

  constructor(private formBuilder: FormBuilder, private userService: UsersService) { 
    this.user = this.defaultUser();
    this.formGroup = this.formBuilder.group({
      userType: [this.userTypes[0], Validators.required],
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      address1: [''],
      address2: [''],
      city: [''],
      state: [''],
      zip: ['', [Validators.pattern(/^\d{5}$/)]]
    });
  }

  ngOnInit() {
  }

  get isEdit(): boolean {
    return this.user.id != null;
  }

  get firstName() : AbstractControl {
    return this.formGroup.get('firstName');
  }

  get lastName() : AbstractControl {
    return this.formGroup.get('lastName');
  }

  get zip(): AbstractControl {
    return this.formGroup.get('zip');
  }

  get userType() : AbstractControl {
    return this.formGroup.get('userType');
  }

  close() {
    this.opened = false;
    this.formGroup.reset(this.user);
  }

  open(user:User = this.defaultUser()) {
    this.user = user;
    if (this.user.contactInfo == null) {
      this.user.contactInfo = {};
    }
    this.formGroup.reset({
      firstName: this.user.firstName,
      lastName: this.user.lastName,
      userType: this.user.userType,
      address1: this.user.contactInfo.address1,
      address2: this.user.contactInfo.address2,
      city: this.user.contactInfo.city,
      state: this.user.contactInfo.state,
      zip: this.user.contactInfo.zip,
    });
    this.opened = true;
    
  }

  private defaultUser(): User {
    return {
      firstName: '',
      lastName: '',
      userType: 'Student',
      contactInfo: {}
    }
  }

  isFormValid(): boolean {
    return this.formGroup.valid;
  }

  submit() {
    this.user.firstName = this.firstName.value;
    this.user.lastName = this.lastName.value;
    this.user.userType = this.userType.value;
    if (this.user.contactInfo == null) {
      this.user.contactInfo = {};
    }
    this.user.contactInfo.address1 = this.formGroup.get('address1').value;
    this.user.contactInfo.address2 = this.formGroup.get('address2').value;
    this.user.contactInfo.city = this.formGroup.get('city').value;
    this.user.contactInfo.state = this.formGroup.get('state').value;
    this.user.contactInfo.zip = this.formGroup.get('zip').value;

    if (this.user.id == null) {
      this.userService.addUser(this.user).subscribe(
        data => {
          this.close();
          this.userService.modifyUsers();
        }
      );
    } else {
      this.userService.updateUser(this.user).subscribe(
        data => {
          this.close();
          this.userService.modifyUsers();
        }
      );
    }
  }
}
