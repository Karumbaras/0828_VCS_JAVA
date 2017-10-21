package lt.vcs.andrius.geometrija;

import static org.junit.Assert.*;

import org.junit.Test;

public class Taskas2Test {

	@Test
	public void testTaskasIntIntString() {
		Taskas t = new Taskas(5, 9, "");

		assertEquals(5, t.getX());
		assertEquals(9, t.getY());
		assertEquals("", t.getSpalva());
	}

	@Test
	public void testTaskasIntInt() {
		Taskas t = new Taskas(5, 9, "Juoda");
		Taskas t2 = new Taskas(5, 9, "Raudona");

		assertFalse(t.palygink(t2));
		
		Taskas t3 = new Taskas(5, 9);
		
		assertEquals("naudoju standartine spalva","Juoda", t3.getSpalva());
	}

	@Test
	public void testTaskas() {
		fail("Not yet implemented");
	}

	@Test
	public void testSpausdinkKoordinates() {
		fail("Not yet implemented");
	}

	@Test
	public void testSakykSpalva() {
		fail("Not yet implemented");
	}

	@Test
	public void testPalygink() {
		fail("Not yet implemented");
	}

	@Test
	public void testKeiskKordinates() {
		fail("Not yet implemented");
	}

	@Test
	public void testNustatykViska() {
		fail("Not yet implemented");
	}

	@Test
	public void testKeiskSpalva() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetSpalva() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSpalva() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetX() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetX() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetY() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetY() {
		fail("Not yet implemented");
	}

}
