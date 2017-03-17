import { BackendService } from '../backend.service';
import { IClubStanding } from './iclubstanding';
import { IClubStandings } from './iclubstandings';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-clubstandings',
  templateUrl: './clubstandings.component.html',
  styleUrls: ['./clubstandings.component.css']
})
export class ClubstandingsComponent implements OnInit {

  errorMessage: string;
  standings: IClubStandings;

  constructor(private beService: BackendService) { }

  ngOnInit() {
    this.standings = JSON.parse("{ \"standings\":[] }");
    
    this.beService.getClubStandings().subscribe(
      cs => this.standings = cs,
      error => this.errorMessage = <any>error);
  }
}
