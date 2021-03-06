package de.cweyermann.btc.server.boundary.tpfile;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Test;

import de.cweyermann.btc.server.entity.Groups;

public class GetGroupsTest {


	@Test
	public void demo_givesElimination() throws SQLException
	{
		GetGroups getGroups = new GetGroups(TpFileUtils.getConnection("demo.tp"));
		
		Groups groups = getGroups.withParent(1);
		
		assertEquals(1, groups.getChilds().size());
		assertEquals("Elimination 1", groups.getChilds().get(2));
	}
	

	@Test
	public void all_EliminationAndPoule() throws SQLException
	{
		GetGroups getGroups = new GetGroups(TpFileUtils.getConnection("demo.tp"));
		
		Groups groups = getGroups.all();
		
		assertEquals(2, groups.getChilds().size());
		assertEquals("Poule 1", groups.getChilds().get(1));
		assertEquals("Elimination 1", groups.getChilds().get(2));
	}
}
