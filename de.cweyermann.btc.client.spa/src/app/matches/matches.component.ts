import { BackendService } from '../backend.service';
import { IMatches } from './imatches';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-matches',
  templateUrl: './matches.component.html',
})
export class MatchesComponent implements OnInit {

  matches: IMatches;

  constructor(private beService: BackendService) { }

  ngOnInit() {
    this.matches = JSON.parse('{ "matches":[] }');

    this.beService.getMatches().subscribe(
      cs => this.matches = cs,
      error => console.log(error));
  }
}
