import { Injectable } from '@angular/core';
import { BaseService } from 'app/base.service';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { PayrollCompany } from 'app/model/api/payrollCompany';
import { ObjectId } from 'app/model/api/objectId';
import { Subject } from 'rxjs/Subject';
import { PayrollRule } from 'app/model/api/payrollRule';

@Injectable()
export class PayrollService extends BaseService {
  private payrollCompaniesModifiedSource: Subject<any> = new Subject();
  private payrollCompaniesModified$: Observable<any>;

  constructor(private http: HttpClient) { 
    super();
    this.payrollCompaniesModified$ = this.payrollCompaniesModifiedSource.asObservable();
  }

  getPayrollCompanies(): Observable<PayrollCompany[]> {
    return this.http.get(`${this.basePath}/payroll`) as Observable<PayrollCompany[]>;
  }

  getPayrollCompany(id: ObjectId): Observable<PayrollCompany> {
    return this.http.get(`${this.basePath}/payroll/${id}`) as Observable<PayrollCompany>;
  }

  addPayrollCompany(payrollCompany: PayrollCompany): Observable<PayrollCompany> {
    return this.http.post(`${this.basePath}/payroll`, payrollCompany) as Observable<PayrollCompany>;
  }

  updatePayrollCompany(payrollCompany: PayrollCompany): Observable<PayrollCompany> {
    return this.http.put(`${this.basePath}/payroll/${payrollCompany.id}`, payrollCompany) as Observable<PayrollCompany>;
  }

  deletePayrollCompany(payrollCompany: PayrollCompany): Observable<any> {
    return this.http.delete(`${this.basePath}/payroll/${payrollCompany.id}`) as Observable<any>;
  }

  getPayrollCompanyRules(payrollCompany: PayrollCompany): Observable<PayrollRule[]> {
    return this.http.get(`${this.basePath}/payroll/${payrollCompany.id}/payrollRules`) as Observable<PayrollRule[]>;
  }

  modifyPayrollCompanies(): void {
    this.payrollCompaniesModifiedSource.next();
  }

  payrollCompaniesModified(): Observable<any> {
    return this.payrollCompaniesModified$;
  }
}
