package de.cweyermann.btc.server.boundary.tpfile;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import de.cweyermann.btc.server.entity.IdName;

public class GetDisciplinesTest {

	@Test
	public void minimal_heFound() throws Exception {
		GetDisciplines gd = new GetDisciplines(TpFileUtils.getConnection("minimum.TP"));
		Map<Integer, String> childs = gd.all().getChilds();
		assertEquals("HE", childs.get(1));
	}

	@Test
	public void empty_emptyList() throws Exception {
		GetDisciplines gd = new GetDisciplines(TpFileUtils.getConnection("empty.TP"));
		assertTrue(gd.all().getChilds().isEmpty());
	}

	@Test
	public void demo_hshd() throws Exception {
		GetDisciplines gd = new GetDisciplines(TpFileUtils.getConnection("demo.tp"));
		Map<Integer, String> childs = gd.all().getChilds();
		assertEquals("MS - 3", childs.get(1));
		assertEquals("MD - 3", childs.get(2));
	}
	

        @Test
        public void demohshd_simpleObjectIsAlsoFilled() throws Exception {
                GetDisciplines gd = new GetDisciplines(TpFileUtils.getConnection("demo.tp"));
                List<IdName> childs = gd.all().getIdNames();
                assertEquals(1, childs.get(0).getId());
                assertEquals("MS - 3", childs.get(0).getName());
                assertEquals(2, childs.get(1).getId());
                assertEquals("MD - 3", childs.get(1).getName());
        }
}
