import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';

import { IClubStandings } from './clubstandings/iclubstandings';
import { IDisciplines } from './idisciplines';
import { IMatches } from './matches/imatches';

@Injectable()
export class BackendService {

  private url = 'http://localhost:8082/btc/';  // URL to web API

  constructor(private http: Http) { }

  getClubStandings(): Observable<IClubStandings> {
    return this.http.get(this.url + 'clubs/')
      .map((response: Response) => <IClubStandings>response.json())
      .do(response => console.log(JSON.stringify(response)))
      .catch(this.handleError);
  }

  getDisciplines(): Observable<IDisciplines> {
    return this.http.get(this.url + 'disciplines/')
      .map((response: Response) => <IDisciplines>response.json())
      .do(response => console.log(JSON.stringify(response)))
      .catch(this.handleError);
  }

  getMatches(): Observable<IMatches> {
    return this.http.get(this.url + 'matches/')
      .map((response: Response) => <IMatches>response.json())
      .do(response => console.log(JSON.stringify(response)))
      .catch(this.handleError);
  }

  private handleError(error: Response | any) {
    // In a real world app, you might use a remote logging infrastructure
    let errMsg: string;
    if (error instanceof Response) {
      const body = error.json() || '';
      const err = body.error || JSON.stringify(body);
      errMsg = `${error.status} - ${error.statusText || ''} ${err}`;
    } else {
      errMsg = error.message ? error.message : error.toString();
    }
    console.error(errMsg);
    return Observable.throw(errMsg);
  }
}
