import { BackendService } from './backend.service';
import { IDisciplines } from './idisciplines';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

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

  constructor(private beService: BackendService, private router: Router) { }

  ngOnInit() {
    this.disciplines = JSON.parse('{ "idNames":[] }');

    this.beService.getDisciplines().subscribe(
      cs => this.disciplines = cs,
      error => console.log(error));
  }

  changeRoute(url: string): void {
    this.router.navigateByUrl('/welcome', { skipLocationChange: true });
    const fullUrl = 'groups/' + url;
    setTimeout(() => this.router.navigate([fullUrl]));
  }
}
