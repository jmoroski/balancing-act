import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Service } from 'app/model/api/service';
import { Observable } from 'rxjs/Observable';
import { BaseService } from 'app/base.service';
import { ObjectId } from 'app/model/api/objectId';
import { BillingRule } from 'app/model/api/billingRule';
import { Subject } from 'rxjs/Subject';

@Injectable()
export class ServiceCompaniesService extends BaseService {
  private billingRulesModifiedSource: Subject<any> = new Subject<any>();
  private billingRulesModified$: Observable<any>;

  constructor(private http: HttpClient) {
    super();
    this.billingRulesModified$ = this.billingRulesModifiedSource.asObservable();
  }

  getServices(): Observable<Service[]> {
    return this.http.get(`${this.basePath}/services`) as Observable<Service[]>;
  }

  getService(id: ObjectId): Observable<Service> {
    return this.http.get(`${this.basePath}/services/${id}`) as Observable<Service>;
  }

  getBillingRules(service: Service): Observable<BillingRule[]> {
    return this.http.get(`${this.basePath}/services/${service.id}/billingRules`) as Observable<BillingRule[]>;
  }

  getBillingRule(id: ObjectId): Observable<BillingRule> {
    return this.http.get(`${this.basePath}/services/billingRules/${id}`) as Observable<BillingRule>;
  }

  addBillingRule(billingRule: BillingRule): Observable<BillingRule> {
    return this.http.post(`${this.basePath}/services/${billingRule.serviceId}/billingRules`, billingRule) as Observable<BillingRule>;
  }

  updateBillingRule(billingRule: BillingRule): Observable<BillingRule> {
    return this.http.put(`${this.basePath}/services/${billingRule.serviceId}/billingRules/${billingRule.id}`, billingRule) as Observable<BillingRule>;
  }

  deleteBillingRule(billingRule: BillingRule): Observable<any> {
    return this.http.delete(`${this.basePath}/services/${billingRule.serviceId}/billingRules/${billingRule.id}`) as Observable<any>;
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

  modifyBillingRules(serviceId: ObjectId): void {
    this.billingRulesModifiedSource.next(serviceId);
  }

  billingRulesModified(): Observable<any> {
    return this.billingRulesModified$;
  }
}
