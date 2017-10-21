package lt.vcs.andrius.geometrija;

import static org.junit.Assert.*;

import org.junit.Test;

public class TaskasTest {

	@Test
	public void test() {
		int sk = 4;
		
		assertEquals(5,++sk);
		
		assertEquals(5,sk++);
		
		
		assertNotEquals(5, 6);
		
	}
	
	@Test
	public void test2() {
		//fail("Not yet implemented");
		
	}

}
