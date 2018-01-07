import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { User } from 'app/model/api/user';


@Injectable()
export class UsersService {

  constructor(private http: HttpClient) { }

  getUsers(): Observable<User[]> {
    return this.http.get('http://localhost:8080/users') as Observable<User[]>;
  }

  getAdministrators(): Observable<User[]> {
    return this.http.get('http://localhost:8080/users?administrators') as Observable<User[]>;
  }

  deleteUser(user: User): Observable<any> {
    return this.http.delete(`http://localhost:8080/users/${user.id}`);
  }

  addUser(user: User): Observable<any> {
    if (user.userType == 'Administrator') {
      return this.http.post(`http://localhost:8080/users/administrators`, user);
    } else {
      return this.http.post(`http://localhost:8080/users/students`, user);
    }
    
  }

  updateUser(user: User): Observable<User> {
    return this.http.put(`http://localhost:8080/users`, user) as Observable<User>;
  }
}
