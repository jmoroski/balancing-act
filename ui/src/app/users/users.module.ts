import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UsersRoutingModule } from './users-routing.module';
import { UsersComponent } from 'app/users/users.component';
import { AddEditUserComponent } from 'app/users/add-edit-user.component';
import { BaseModule } from 'app/base.module';

@NgModule({
  imports: [
    BaseModule,
    UsersRoutingModule
  ],
  declarations: [
    AddEditUserComponent,
    UsersComponent,
  ],
  entryComponents: [UsersComponent]
})
export class UsersModule {
  constructor () {
    console.log("loading user module");
  }
}
