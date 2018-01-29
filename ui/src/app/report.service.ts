import { Injectable } from '@angular/core';
import { BaseService } from 'app/base.service';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { ProgramGroup } from 'app/model/api/programGroup';

@Injectable()
export class ReportService extends BaseService {

  constructor(private http: HttpClient) { 
    super();
  }

  generateInvoicesByGroup(startDate: Date, endDate: Date, programGroup: ProgramGroup): Observable<any> {
    return this.http.get(`${this.basePath}/reports/invoices?startDate=${startDate}&endDate=${endDate}&programGroup=${programGroup.id}`);
  }
}
