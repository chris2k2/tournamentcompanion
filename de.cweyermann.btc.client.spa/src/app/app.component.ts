import { BackendService } from './backend.service';
import { IDisciplines } from './idisciplines';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})
export class AppComponent implements OnInit {
  isCollapsed = true;
  disciplines: IDisciplines;

  toggleCollapse(): void {
    this.isCollapsed = !this.isCollapsed;
  }

  constructor(private beService: BackendService) { }

  ngOnInit() {
    this.disciplines = JSON.parse('{ "idNames":[] }');

    this.beService.getDisciplines().subscribe(
      cs => this.disciplines = cs,
      error => console.log(error));
  }
}
