import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AVLTreeTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetSize() {
		AVLTree<Integer> example = new AVLTree<Integer>();
		example.insert(3);
		example.insert(4);
		assertEquals(2, example.getSize());
	}

	@Test
	public void testInsertE() {
		//fail("Not yet implemented");
	}

	@Test
	public void testRemoveE() {
		//fail("Not yet implemented");
	}

	@Test
	public void testFindMin() {
		//fail("Not yet implemented");
	}

}
