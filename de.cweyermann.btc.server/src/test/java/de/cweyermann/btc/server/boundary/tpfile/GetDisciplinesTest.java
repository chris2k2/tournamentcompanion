package de.cweyermann.btc.server.boundary.tpfile;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Test;

public class GetDisciplinesTest {

	@Test
	public void minimal_heFound() throws Exception {
		GetDisciplines gd = new GetDisciplines(TestUtils.getConnection("minimum.TP"));
		Map<Integer, String> childs = gd.all().getChilds();
		assertEquals("HE", childs.get(1));
	}

	@Test
	public void empty_emptyList() throws Exception {
		GetDisciplines gd = new GetDisciplines(TestUtils.getConnection("empty.TP"));
		assertTrue(gd.all().getChilds().isEmpty());
	}

	@Test
	public void demo_hshd() throws Exception {
		GetDisciplines gd = new GetDisciplines(TestUtils.getConnection("demo.tp"));
		Map<Integer, String> childs = gd.all().getChilds();
		assertEquals("MS - 3", childs.get(1));
		assertEquals("MD - 3", childs.get(2));
	}
}
