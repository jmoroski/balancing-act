import { Component, OnInit, ViewChild, OnDestroy } from '@angular/core';
import { UsersService } from 'app/users.service';
import { User } from 'app/model/api/user';
import { AddEditUserComponent } from 'app/users/add-edit-user.component';
import { Subscription } from 'rxjs/Subscription';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss'],
  providers: [UsersService]
})
export class UsersComponent implements OnInit, OnDestroy {
  private users: User[];
  private loading: boolean = true;
  private subscription: Subscription;

  @ViewChild("addEditUserComponent") addEditUserComponent: AddEditUserComponent;
  
  constructor(private userService: UsersService) {
    this.subscription = userService.usersModified().subscribe(
      data => {
        this.reloadData();
      }
    );
  }

  addressString(user: User): string {
    if (user.contactInfo == null) {
      return '';
    }

    var address: string = '';
    if (user.contactInfo.address1 != null) {
      address += `${user.contactInfo.address1}`
      address += '<br />';
    }
    if (user.contactInfo.address2 != null) {
      address += `${user.contactInfo.address2}`;
      address += '<br />';
    }

    if (user.contactInfo.city != null) {
      address += `${user.contactInfo.city}, ${user.contactInfo.state} ${user.contactInfo.zip}`;
    }

    return address;
  }

  ngOnInit() {
    this.reloadData();
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  addUser() {
    this.addEditUserComponent.open();
  }

  editUser(user) {
    this.addEditUserComponent.open(user);
  }

  deleteUser(user) {
    this.userService.deleteUser(user).subscribe(
      data => {
        this.userService.modifyUsers();
      }
    );
  }

  reloadData() {
    this.loading = true;
    this.userService.getUsers().subscribe(
      data => {
        this.users = data;
        this.loading = false;
      }
    );
  }
}
