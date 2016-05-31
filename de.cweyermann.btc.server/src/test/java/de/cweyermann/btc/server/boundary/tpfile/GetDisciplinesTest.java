package de.cweyermann.btc.server.boundary.tpfile;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class GetDisciplinesTest {

	@Test
	public void minimal_heFound() throws Exception {
		assertDisciplinesAre(Arrays.asList("HE"), "minimum.TP");
	}

	@Test
	public void empty_emptyList() throws Exception {
		assertDisciplinesAre(Collections.emptyList(), "empty.TP");
	}

	@Test
	public void demo_hshd() throws Exception {
		assertDisciplinesAre(Arrays.asList("MS - 3", "MD - 3"), "demo.TP");
	}

	private void assertDisciplinesAre(List<String> list, String fileName) {
		GetDisciplines gd = new GetDisciplines(TestUtils.getTpFilePath(fileName));
		assertEquals(list, gd.getAll().getDiscs());
	}
}
