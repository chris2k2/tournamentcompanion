import { BackendService } from '../backend.service';
import { IClubStanding, IClubStandings } from './iclubstandings';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-clubstandings',
  templateUrl: './clubstandings.component.html',
})
export class ClubstandingsComponent implements OnInit {

  standings: IClubStandings;

  constructor(private beService: BackendService) { }

  ngOnInit() {
    this.standings = JSON.parse('{ "standings":[] }');

    this.beService.getClubStandings().subscribe(
      cs => this.standings = cs,
      error => console.log(error));
  }
}
