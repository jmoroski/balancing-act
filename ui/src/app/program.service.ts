import { Injectable } from '@angular/core';
import { BaseService } from 'app/base.service';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Subject } from 'rxjs/Subject';
import { ProgramGroup } from 'app/model/api/programGroup';
import { ObjectId } from 'app/model/api/objectId';
import { Student } from 'app/model/api/student';
import { BillingRule } from 'app/model/api/billingRule';

@Injectable()
export class ProgramService extends BaseService {
  private programGroupsModifiedSource: Subject<any> = new Subject<any>();
  private programGroupsModified$: Observable<any>;

  private programGroupStudentssModifiedSource: Subject<any> = new Subject<any>();
  private programGroupStudentsModified$: Observable<any>;

  constructor(private http: HttpClient) {
    super();
    this.programGroupsModified$ = this.programGroupsModifiedSource.asObservable();
  }

  getProgramGroups(): Observable<ProgramGroup[]> {
    return this.http.get<ProgramGroup[]>(`${this.basePath}/programGroups`);
  }

  getProgramGroup(id: ObjectId): Observable<ProgramGroup> {
    return this.http.get<ProgramGroup>(`${this.basePath}/programGroups/${id}`);
  }

  addProgramGroup(programGroup: ProgramGroup): Observable<ProgramGroup> {
    return this.http.post<ProgramGroup>(`${this.basePath}/programGroups`, programGroup);
  }

  editProgramGroup(programGroup: ProgramGroup): Observable<ProgramGroup> {
    return this.http.put<ProgramGroup>(`${this.basePath}/programGroups`, programGroup);
  }

  deleteProgramGroup(programGroup: ProgramGroup): Observable<ProgramGroup> {
    return this.http.delete<ProgramGroup>(`${this.basePath}/programGroups/${programGroup.id}`);
  }

  getProgramGroupStudents(programGroup: ProgramGroup): Observable<Student[]> {
    return this.http.get<Student[]>(`${this.basePath}/programGroups/${programGroup.id}/students`);
  }

  setProgramGroupStudents(programGroup: ProgramGroup, students: Student[]): Observable<Student[]> {
    return this.http.put<Student[]>(`${this.basePath}/programGroups/${programGroup.id}/students`, students);
  }

  getEligibleProgramGroupStudents(programGroup: ProgramGroup): Observable<Student[]> {
    return this.http.get<Student[]>(`${this.basePath}/programGroups/${programGroup.id}/eligibleStudents`);
  }

  getProgramGroupBillingRules(programGroup: ProgramGroup): Observable<BillingRule[]> {
    return this.http.get<BillingRule[]>(`${this.basePath}/programGroups/${programGroup.id}/billingRules`);
  }

  setProgramGroupBillingRules(programGroup: ProgramGroup, billingRules: BillingRule[]): Observable<BillingRule[]> {
    return this.http.put<BillingRule[]>(`${this.basePath}/programGroups/${programGroup.id}/billingRules`, billingRules);
  }

  getEligibleProgramGroupBillingRules(programGroup: ProgramGroup): Observable<BillingRule[]> {
    return this.http.get<BillingRule[]>(`${this.basePath}/programGroups/${programGroup.id}/eligibleBillingRules`);
  }
  
  onModifyProgramGroups() {
    this.programGroupsModifiedSource.next();
  }

  programGroupsModified(): Observable<any> {
    return this.programGroupsModified$;
  }

  modifyProgramGroupStudents() {
    this.programGroupsModifiedSource.next();
  }

  programGroupStudentsModified(): Observable<any> {
    return this.programGroupsModified$;
  }
}
