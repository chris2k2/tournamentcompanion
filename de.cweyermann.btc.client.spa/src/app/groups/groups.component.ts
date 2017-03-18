import { BackendService } from '../backend.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-groups',
  templateUrl: './groups.component.html',
  styleUrls: ['./groups.component.css']
})
export class GroupsComponent implements OnInit {

  disc: Object;

  constructor(private beService: BackendService) { }

  ngOnInit() {
    this.disc = JSON.parse('{ "groups":[] }');

    this.beService.getDiscipline(1).subscribe(
      cs => this.disc = cs,
      error => console.log(error));
  }
 
  getIt(id: number) {
    return '#' + id;
  }

}
