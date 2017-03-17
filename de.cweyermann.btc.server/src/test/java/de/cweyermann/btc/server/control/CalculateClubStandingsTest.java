package de.cweyermann.btc.server.control;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import de.cweyermann.btc.server.entity.ClubStandings;
import de.cweyermann.btc.server.entity.Group;
import de.cweyermann.btc.server.entity.Match;
import de.cweyermann.btc.server.entity.Player;
import de.cweyermann.btc.server.entity.Team;

public class CalculateClubStandingsTest {

    private CalculateClubStandings calc;

    @Before
    public void init() {
        calc = new CalculateClubStandings(10, 6, 4, 2, 1);
    }

    @Test
    public void oneGroupfirstPlace_first() {
        Team team1 = buildTeamFrom("Club1");
        Team team2 = buildTeamFrom("Club2");

        Group group = BuilderUtils.getGroupInRanking(team1, team2);
        ClubStandings standings = calc.execute(Arrays.asList(group));

        assertEquals(10, standings.getStandings().get(0).getPoints(), 0.001);
        assertEquals("Club1", standings.getStandings().get(0).getClubName());
    }

    @Test
    public void oneGroupfirstPlaceButQualification_noPoints() {
        Team team1 = buildTeamFrom("Club1");
        Team team2 = buildTeamFrom("Club2");

        Group group = BuilderUtils.getGroupInRanking(team1, team2);
        group.setQualification(true);
        ClubStandings standings = calc.execute(Arrays.asList(group));

        assertNoPoints(standings, 0);
    }

    @Test
    public void twoGroups_firstPlace() {
        ClubStandings standings = twoGroupsWonByClub1("Club1", "other");

        assertEquals(20, standings.getStandings().get(0).getPoints(), 0.001);
        assertEquals("Club1", standings.getStandings().get(0).getClubName());
    }

    @Test
    public void twoGroups_oneWonOneLost() {
        Team team1 = buildTeamFrom("Club1");
        Team team2 = buildTeamFrom("Club2");

        Group group = BuilderUtils.getGroupInRanking(team1, team2);
        Group group2 = BuilderUtils.getGroupInRanking(team2, team1);
        ClubStandings standings = calc.execute(Arrays.asList(group, group2));

        assertEquals(16, standings.getStandings().get(0).getPoints(), 0.001);
        assertEquals("Club1", standings.getStandings().get(0).getClubName());
    }

    @Test
    public void oneBigGroup_allTeamsCalculated() {
        Group group = BuilderUtils.getGroupInRanking(buildTeamFrom("Club1"), buildTeamFrom("Club2"),
                buildTeamFrom("Club3"), buildTeamFrom("Club4"), buildTeamFrom("Club5"));

        ClubStandings standings = calc.execute(Arrays.asList(group));

        assertEquals(10, standings.getStandings().get(0).getPoints(), 0.001);
        assertEquals("Club1", standings.getStandings().get(0).getClubName());
        assertEquals(6, standings.getStandings().get(1).getPoints(), 0.001);
        assertEquals("Club2", standings.getStandings().get(1).getClubName());
        assertEquals(4, standings.getStandings().get(2).getPoints(), 0.001);
        assertEquals("Club3", standings.getStandings().get(2).getClubName());
        assertEquals(2, standings.getStandings().get(3).getPoints(), 0.001);
        assertEquals("Club4", standings.getStandings().get(3).getClubName());
    }

    @Test
    public void twoTeamsAsked_allTeamsCalculated() {
        ClubStandings standings = twoGroupsWonByClub1("Club_winner", "Club1");

        assertEquals(20, standings.getStandings().get(0).getPoints(), 0.001);
        assertEquals("Club_winner", standings.getStandings().get(0).getClubName());
        assertEquals(12, standings.getStandings().get(1).getPoints(), 0.001);
        assertEquals("Club1", standings.getStandings().get(1).getClubName());

    }

    @Test
    public void koGroup_worksAsWell() {
        Team team1 = buildTeamFrom("Winner");
        Team team2 = buildTeamFrom("Second");
        Team team3 = buildTeamFrom("Third");
        Team team4 = buildTeamFrom("Fourth");

        Group group = buildRealKo(team1, team2, team3, team4);

        ClubStandings standings = calc.execute(Arrays.asList(group));

        assertEquals(10, standings.getStandings().get(0).getPoints(), 0.001);
        assertEquals(6, standings.getStandings().get(1).getPoints(), 0.001);
        assertEquals(4, standings.getStandings().get(2).getPoints(), 0.001);
        assertEquals(2, standings.getStandings().get(3).getPoints(), 0.001);
        assertEquals("Winner", standings.getStandings().get(0).getClubName());
        assertEquals("Second", standings.getStandings().get(1).getClubName());
        assertEquals("Third", standings.getStandings().get(2).getClubName());
        assertEquals("Fourth", standings.getStandings().get(3).getClubName());
    }

    @Test
    public void koGroup_noMatchForThird() {
        Team team1 = buildTeamFrom("Winner");
        Team team2 = buildTeamFrom("Second");
        Team team3 = buildTeamFrom("Third");
        Team team4 = buildTeamFrom("Fourth");

        Group group = new Group();
        group.addMatch(new Match(team1, team3, "21-1", "21-0", null, false, false, 0, 0));
        group.addMatch(new Match(team2, team4, "21-2", "21-0", null, false, false, 1, 0));
        group.addMatch(new Match(team1, team2, "21-3", "21-0", null, false, false, 0, 1));
        group.addTeam(team1);
        group.addTeam(team2);
        group.addTeam(team3);
        group.addTeam(team4);
        makeFinalKo(group);

        ClubStandings standings = calc.execute(Arrays.asList(group));

        assertEquals(10, standings.getStandings().get(0).getPoints(), 0.001);
        assertEquals(6, standings.getStandings().get(1).getPoints(), 0.001);
        assertEquals(4, standings.getStandings().get(2).getPoints(), 0.001);
        assertEquals(4, standings.getStandings().get(3).getPoints(), 0.001);
        assertEquals("Winner", standings.getStandings().get(0).getClubName());
        assertEquals("Second", standings.getStandings().get(1).getClubName());
        assertEquals("Third", standings.getStandings().get(2).getClubName());
        assertEquals("Fourth", standings.getStandings().get(3).getClubName());
    }

    @Test
    public void loosersKoGroup_onlyPointsForFirst() {
        Team team1 = buildTeamFrom("Winner");
        Team team2 = buildTeamFrom("Second");

        Group group = new Group();
        group.addMatch(new Match(team1, team2, "21-3", "21-0", null, false, false, 0, 1));
        group.addTeam(team1);
        group.addTeam(team2);
        group.setKo(true);
        group.setPosition(3);

        ClubStandings standings = calc.execute(Arrays.asList(group));

        assertEquals(1, standings.getStandings().get(0).getPoints(), 0.001);
        assertEquals("Winner", standings.getStandings().get(0).getClubName());
    }

    @Test
    public void finalsNotYetPlayed_NotCalculated() {
        Team team1 = buildTeamFrom("Winner");
        Team team2 = buildTeamFrom("Second");
        Team team3 = buildTeamFrom("Third");
        Team team4 = buildTeamFrom("Fourth");

        Group group = buildRealKo(team1, team2, team3, team4, false);

        ClubStandings standings = calc.execute(Arrays.asList(group));

        assertNoPoints(standings, 0);
    }

    @Test
    public void finalNotPlayedButHfandThird_thirdAndfourthAreRanked1And2() {
        Team team1 = buildTeamFrom("Winner");
        Team team2 = buildTeamFrom("Second");
        Team team3 = buildTeamFrom("Third");
        Team team4 = buildTeamFrom("Fourth");
        Group group1 = new Group();
        group1.addMatch(new Match(team1, team3, "21-0", "21-2", null, false, false, 0, 0));
        group1.addMatch(new Match(team2, team4, "21-0", "21-3", null, false, false, 1, 0));
        group1.addMatch(new Match(team1, team2, null, null, null, false, false, 0, 1));
        group1.addMatch(new Match(team3, team4, "21-5", "21-5", null, false, false, 1, 1));
        group1.addTeam(team1);
        group1.addTeam(team2);
        group1.addTeam(team3);
        group1.addTeam(team4);
        makeFinalKo(group1);

        ClubStandings standings = calc.execute(Arrays.asList(group1));

        assertEquals(4, standings.getStandings().get(0).getPoints(), 0.001);
        assertEquals(2, standings.getStandings().get(1).getPoints(), 0.001);
    }

    @Test
    public void oneHalfFinalPlayed_NoRaning() {
        Team team1 = buildTeamFrom("Winner");
        Team team2 = buildTeamFrom("Second");
        Team team3 = buildTeamFrom("Third");
        Team team4 = buildTeamFrom("Fourth");
        Group group1 = new Group();
        group1.addMatch(new Match(team1, team3, "21-0", "21-2", null, false, false, 0, 0));
        group1.addMatch(new Match(team2, team4, null, null, null, false, false, 1, 0));
        group1.addMatch(new Match(team1, null, null, null, null, false, false, 0, 1));
        group1.addTeam(team1);
        group1.addTeam(team2);
        group1.addTeam(team3);
        group1.addTeam(team4);
        makeFinalKo(group1);

        ClubStandings standings = calc.execute(Arrays.asList(group1));

        assertEquals(4, standings.getStandings().get(0).getPoints(), 0.001);
    }

    @Test
    public void halfTeam_pointsAwardedHalf() {
        Team team1 = buildTeamFrom("Winner", "WinnerB");
        Team team2 = buildTeamFrom("Second");

        Group group1 = new Group();
        group1.addMatch(new Match(team1, team2, "21-0", "21-2", null, false, false, 0, 0));
        group1.addTeam(team1);
        group1.addTeam(team2);
        group1.setKo(true);
        group1.setPosition(5);

        ClubStandings standings = calc.execute(Arrays.asList(group1));

        assertEquals(0.5, standings.getStandings().get(0).getPoints(), 0.001);
        assertEquals(0.5, standings.getStandings().get(1).getPoints(), 0.001);
    }

    @Test
    public void loosersKoFinalNotPlayed_noPoints() {
        Team team1 = buildTeamFrom("Winner", "WinnerB");
        Team team2 = buildTeamFrom("Second");

        Group group1 = new Group();
        group1.addMatch(new Match(team1, team2, null, null, null, false, false, 0, 0));
        group1.addTeam(team1);
        group1.addTeam(team2);
        group1.setKo(true);
        group1.setPosition(5);

        ClubStandings standings = calc.execute(Arrays.asList(group1));

        assertNoPoints(standings, 0);
    }

    private void assertNoPoints(ClubStandings standings, int from) {
        assertEquals(standings.getStandings().size(), from);
    }

    private ClubStandings twoGroupsWonByClub1(String clubname, String otherClub) {
        Team team1 = buildTeamFrom(clubname);
        Team team2 = buildTeamFrom(otherClub);

        Group group = BuilderUtils.getGroupInRanking(team1, team2);
        Group group2 = BuilderUtils.getGroupInRanking(team1, team2);

        ClubStandings standings = calc.execute(Arrays.asList(group, group2));
        return standings;
    }

    private Group buildRealKo(Team team1, Team team2, Team team3, Team team4) {
        return buildRealKo(team1, team2, team3, team4, true);
    }

    private Group buildRealKo(Team team1, Team team2, Team team3, Team team4, boolean finalPlayed) {
        Group group = new Group();
        if (finalPlayed) {
            group.addMatch(new Match(team1, team3, "21-1", "21-0", null, false, false, 0, 0));
            group.addMatch(new Match(team2, team4, "21-2", "21-0", null, false, false, 1, 0));
            group.addMatch(new Match(team1, team2, "21-3", "21-0", null, false, false, 0, 1));
            group.addMatch(new Match(team3, team4, "21-4", "21-0", null, false, false, 1, 1));
        } else {
            group.addMatch(new Match(team1, team3, null, null, null, false, false, 0, 0));
            group.addMatch(new Match(team2, team4, null, null, null, false, false, 1, 0));
            group.addMatch(new Match(team1, team2, null, null, null, false, false, 0, 1));
            group.addMatch(new Match(team3, team4, null, null, null, false, false, 1, 1));
        }
        group.addTeam(team1);
        group.addTeam(team2);
        group.addTeam(team3);
        group.addTeam(team4);
        makeFinalKo(group);
        return group;
    }

    private void makeFinalKo(Group group1) {
        group1.setKo(true);
        group1.setPosition(1);
    }

    private Team buildTeamFrom(String club) {
        Team team1 = new Team();
        Player player1 = new Player();
        team1.setPlayer1(player1);
        player1.setClub(club);
        return team1;
    }

    private Team buildTeamFrom(String club1, String club2) {
        Team team1 = new Team();

        Player player1 = new Player();
        team1.setPlayer1(player1);
        player1.setClub(club1);

        Player player2 = new Player();
        team1.setPlayer2(player2);
        player2.setClub(club2);

        return team1;
    }

}
