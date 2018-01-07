import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Service } from 'app/model/api/service';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class ServicesService {
  protected basePath: string = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  getServices(): Observable<Service[]> {
    return this.http.get(`${this.basePath}/services`) as Observable<Service[]>;
  }

  addService(service: Service): Observable<Service> {
    return this.http.post(`${this.basePath}/services`, service) as Observable<Service>;
  }

  updateService(service: Service): Observable<Service> {
    return this.http.put(`${this.basePath}/services`, service) as Observable<Service>;
  }

  deleteService(service: Service): Observable<any> {
    return this.http.delete(`${this.basePath}/services/${service.id}`) as Observable<any>;
  }
}
