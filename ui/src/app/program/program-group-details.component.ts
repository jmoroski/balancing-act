import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-program-group-details',
  templateUrl: './program-group-details.component.html',
  styleUrls: ['./program-group-details.component.scss']
})
export class ProgramGroupDetailsComponent implements OnInit {
  constructor(private route: ActivatedRoute) { }

  ngOnInit() {
  }

  private loadData(): void {
    const id: string = this.route.snapshot.paramMap.get('id');
  }
}
