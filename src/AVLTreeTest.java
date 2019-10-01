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
		
		//Two element tree, test removing the root 
		example.insert(1);
		example.insert(2);
		example.remove(1);
	    String testAns = example.printTree().toString();
	    String ans1 = "[2]";
	    assertEquals(ans1,testAns);
    
	    
		//Removing the root when it has two leaves 
		example2.insert(1);
		example2.insert(2);
		example2.insert(3);
		example2.remove(2);
	    String testAns2 = example2.printTree().toString();
	    String ans2 = "[3, 1]";
	    assertEquals(ans2,testAns2);
		
	    //Test removing a middle layer node 
		example3.insert(4);
		example3.insert(1);
		example3.insert(5);
		example3.insert(7);
		example3.remove(5);
	    String testAns3= example3.printTree().toString();
	    String ans3 = "[4, 1, 7]";
	    assertEquals(ans3,testAns3);	
	    
	    //Test removing a root node with branches on both sides
		example4.insert(3);
		example4.insert(2);
		example4.insert(4);
		example4.insert(0);
		example4.remove(3);
	    String testAns4= example4.printTree().toString();
	    String ans4 = "[2, 0, 4]";
	    assertEquals(ans4,testAns4);
	    
	    //Test removing a node that doesn't exist
	    example4.remove(1);
	    String testAns5= example4.printTree().toString();
	    String ans5 = "[2, 0, 4]";
	    assertEquals(ans5,testAns5);	   
		
	}

	@Test
	public void testFindMin() {
	    //Test when min is the leaf 
		example.insert(3);
	    example.insert(2);
	    example.insert(1);
	    assertEquals("1", example.findMin().toString());
	    
	    //Test when min is the root
		example2.insert(3);
		example2.insert(4);
		example2.insert(5);
		assertEquals("3", example2.findMin().toString());
	
		
	    
	}

}
