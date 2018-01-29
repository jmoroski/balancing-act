import { Component, OnInit, Input, OnChanges, SimpleChanges } from '@angular/core';
import { ProgramGroup } from 'app/model/api/programGroup';
import { Student } from 'app/model/api/student';
import { ProgramService } from 'app/program.service';

import * as pdfMake from 'pdfmake/build/pdfmake';
import * as pdfFonts from 'pdfmake/build/vfs_fonts';
import { ReportService } from 'app/report.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-program-group-reports',
  templateUrl: './program-group-reports.component.html',
  styleUrls: ['./program-group-reports.component.scss']
})
export class ProgramGroupReportsComponent implements OnInit, OnChanges {
  @Input() programGroup: ProgramGroup;
  private students: Student[];

  private invoiceByGroupFormGroup: FormGroup;
  private payrollByGroupFormGroup: FormGroup;
  private bankingByGroupFormGroup: FormGroup;

  constructor(private programService: ProgramService, private reportService: ReportService, private formBuilder: FormBuilder) {
    pdfMake.vfs = pdfFonts.pdfMake.vfs;

    this.invoiceByGroupFormGroup = this.formBuilder.group({
      startDate: ['', Validators.required],
      endDate: ['', Validators.required]
    });

    this.payrollByGroupFormGroup = this.formBuilder.group({
      startDate: ['', Validators.required],
      endDate: ['', Validators.required]
    });

    this.bankingByGroupFormGroup = this.formBuilder.group({
      startDate: ['', Validators.required],
      endDate: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    
  }

  ngOnChanges(changes: SimpleChanges): void {
    if(this.programGroup) {
      this.programService.getProgramGroupStudents(this.programGroup).subscribe(students => {
        this.students = students;
      });
    }
  }

  private generateInvoicesByGroup(): void {
    const startDate: Date = <Date>this.invoiceByGroupFormGroup.get('startDate').value;
    const endDate: Date = <Date>this.invoiceByGroupFormGroup.get('endDate').value;
    console.log(`generating invoices from ${startDate} to ${endDate}`)
    this.reportService.generateInvoicesByGroup(startDate, endDate, this.programGroup).subscribe(invoices => {
        pdfMake.createPdf(invoices).open();
      }
    );
  }

  private generatePayrollByGroup(): void {
    const startDate: Date = <Date>this.payrollByGroupFormGroup.get('startDate').value;
    const endDate: Date = <Date>this.payrollByGroupFormGroup.get('endDate').value;
    console.log(`generating payroll from ${startDate} to ${endDate}`)
    // this.reportService.generateInvoicesByGroup().subscribe(invoices => {
    //     pdfMake.createPdf(invoices).open();
    //   }
    // );
  }

  private generateBankingByGroup(): void {
    const startDate: Date = <Date>this.bankingByGroupFormGroup.get('startDate').value;
    const endDate: Date = <Date>this.bankingByGroupFormGroup.get('endDate').value;
    console.log(`generating banking from ${startDate} to ${endDate}`)
    // this.reportService.generateInvoicesByGroup().subscribe(invoices => {
    //     pdfMake.createPdf(invoices).open();
    //   }
    // );
  }
}
