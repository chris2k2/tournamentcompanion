import { BackendService } from '../backend.service';
import { IDiscipline } from '../idisciplines';
import { IGroup } from '../igroup';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-groups',
  templateUrl: './groups.component.html',
  styleUrls: ['./groups.component.css']
})
export class GroupsComponent implements OnInit {

  disc: IDiscipline;
  id: number;

  constructor(private beService: BackendService, private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.disc = JSON.parse('{ "groups":[] }');

    this.beService.getDiscipline(this.route.snapshot.params['id']).subscribe(
      cs => this.disc = cs,
      error => console.log(error));
  }

  getIt(id: number) {
    return '#' + id;
  }

}
