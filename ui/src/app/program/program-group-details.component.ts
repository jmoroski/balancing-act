import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProgramGroup } from 'app/model/api/programGroup';
import { ProgramService } from 'app/program.service';

@Component({
  selector: 'app-program-group-details',
  templateUrl: './program-group-details.component.html',
  styleUrls: ['./program-group-details.component.scss']
})
export class ProgramGroupDetailsComponent implements OnInit {
  private programGroup: ProgramGroup;

  constructor(private router: Router, private route: ActivatedRoute, private programService: ProgramService) { }

  ngOnInit() {
    this.loadData();
  }

  private loadData(): void {
    const id: string = this.route.snapshot.paramMap.get('id');
    this.programService.getProgramGroup(id).subscribe(programGroup => {
      this.programGroup = programGroup;
    });

    if (!this.route.snapshot.fragment) {
      this.router.navigate([], {fragment: 'reports', replaceUrl: true});
    }
  }

  private hasFragment(pathName: string): boolean {
    return this.route.snapshot.fragment == pathName;
  }
}
