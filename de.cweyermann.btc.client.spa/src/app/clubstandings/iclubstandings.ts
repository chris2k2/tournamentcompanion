export interface IClubStanding {
  'clubName': string;
  'points': number;
  'position': number;
};

export interface IClubStandings {
  'standings': IClubStanding[];
};
