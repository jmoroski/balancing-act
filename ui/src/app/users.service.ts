import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { User } from 'app/model/api/user';
import { Subject } from 'rxjs/Subject';
import { BaseService } from 'app/base.service';


@Injectable()
export class UsersService extends BaseService {
  private usersModifiedSource: Subject<any> = new Subject<any>();
  private usersModified$: Observable<any>;

  constructor(private http: HttpClient) {
    super();
    this.usersModified$ = this.usersModifiedSource.asObservable();
  }

  modifyUsers() {
    this.usersModifiedSource.next();
  }

  usersModified(): Observable<any> {
    return this.usersModified$;
  }

  getUsers(): Observable<User[]> {
    return this.http.get(`${this.basePath}/users`) as Observable<User[]>;
  }

  getAdministrators(): Observable<User[]> {
    return this.http.get(`${this.basePath}/users?administrators`) as Observable<User[]>;
  }

  deleteUser(user: User): Observable<any> {
    return this.http.delete(`${this.basePath}/users/${user.id}`);
  }

  addUser(user: User): Observable<any> {
    if (user.userType == 'Administrator') {
      return this.http.post(`${this.basePath}/users/administrators`, user);
    } else {
      return this.http.post(`${this.basePath}/users/students`, user);
    }
    
  }

  updateUser(user: User): Observable<User> {
    return this.http.put(`${this.basePath}/users`, user) as Observable<User>;
  }
}
