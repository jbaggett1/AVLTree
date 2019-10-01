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
	    String testAns = example.printTree().toString();
	    String ans1 = "[2, 1, 3]";
	    assertEquals(ans1,testAns);
		

		//Test single right rotation
		example2.insert(3);
		example2.insert(4);
		example2.insert(5);
		String testAns2 = example2.printTree().toString();
		String ans2 = "[4, 3, 5]";
		assertEquals(ans2,testAns2);
		
		//Test double right (inner) rotation
		example3.insert(5);
		example3.insert(7);
    	example3.insert(6);
    	String testAns3 = example3.printTree().toString();
    	String ans3 = "[6, 5, 7]";
		assertEquals(ans3, testAns3);
		
		
		//Test double left rotation
		example4.insert(5);
		example4.insert(3);
		example4.insert(4);
    	String testAns4 = example4.printTree().toString();
    	String ans4 = "[4, 3, 5]";
		assertEquals(ans4, testAns4);
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
