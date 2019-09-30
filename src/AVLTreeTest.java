import static org.junit.Assert.*;
import java.util.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AVLTreeTest {

	protected AVLTree<Integer> example;
	protected AVLTree<Integer> example2;
	protected AVLTree<Integer> example3;
	protected AVLTree<Integer> example4;
	
	@Before
	public void setUp() throws Exception {
		 example = new AVLTree<Integer>();
		 example2 = new AVLTree<Integer>();
		 example3 = new AVLTree<Integer>();
		 example4 = new AVLTree<Integer>();
	}

	@After
	public void tearDown() throws Exception {
		example = null;
		example2 = null;
	}

	@Test
	public void testGetSize() {
		example.insert(3);
		example.insert(4);
		assertEquals(2, example.getSize());
	}

	@Test
	public void testInsertE() {
		//Test single left rotation
		example.insert(3);
		example.insert(2);
		example.insert(1);
		int ex1 = example.root.element;
		System.out.println();
		//example.printTree();
		assertEquals(2,ex1);
		

		//Test single right rotation
		example2.insert(3);
		example2.insert(4);
		example2.insert(5);
		int ex2 = example2.root.element;
		//example2.printTree();
		assertEquals(4, ex2);
		
		//Test double right (inner) rotation
		example3.insert(5);
		example3.insert(7);
    	example3.insert(6);
		//example3.printTree();
		int ex3 = example3.root.element;
		assertEquals(6, ex3);
		
		
		//Test double left rotation
		example4.insert(5);
		example4.insert(3);
		example4.insert(4);
		ArrayList<Integer> ex4sample = example4.printTree();
		int ex4 = example4.root.element;
		List<Integer> x = new ArrayList<>(Arrays.asList(4,3,5));
		assertEquals(x, ex4sample);
	}

	@Test
	public void testRemoveE() {
		
//		//2 element tree, removing the root 
//		example.insert(1);
//		example.insert(2);
//		example.remove(1);
//		int removeAns1 = example.root.element;
//		assertEquals(2, removeAns1);
//		
//		//three element case, removing the middle node
//		example2.insert(1);
//		example2.insert(2);
//		example2.insert(3);
//		example2.remove(2);
//		int removeAns2 = example.root.right.element;
//		assertEquals(3, removeAns2);
		
		
	}

	@Test
	public void testFindMin() {
		//fail("Not yet implemented");
	}

}
