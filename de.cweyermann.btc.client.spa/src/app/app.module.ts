import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { ClubstandingsComponent } from './clubstandings/clubstandings.component';
import { GroupsComponent } from './groups/groups.component';
import { MatchesComponent } from './matches/matches.component';
import { RouterModule } from '@angular/router';

@NgModule({
  declarations: [
    AppComponent,
    WelcomeComponent,
    GroupsComponent,
    ClubstandingsComponent,
    MatchesComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule.forRoot([
      {path: 'clubs', component: ClubstandingsComponent},
      {path: 'matches', component: MatchesComponent},
      {path: 'groups/:id', component: GroupsComponent},
      {path: 'welcome', component: WelcomeComponent},
      {path: '', redirectTo: 'welcome', pathMatch: 'full'}
    ])],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
