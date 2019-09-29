/**
 * Jillian Baggett and Arjun Manoj
 * Class AVLTree is a basic implementaion of Adelson-Velskii and
 * Landis' Balanced Binary Search Tree.
 */

public class AVLTree <E extends Comparable<? super E>> {
    private Node root;
    private int size;

    public static void main(String[] args)
    {
        AVLTree example = new AVLTree();
        example.insert(7);
        example.insert(3);
        example.insert(6);
        example.insert(5);
    }

    public int getSize()
    { return this.size;
    }
    /**
     * Construct an empty AVLTree.
     */
    public AVLTree() {
        // not necessary, but explicit stating root starts at null
        this.root = null;
        this.size = 0;
    }

    /**
     * Insert the element into this AVLTree.
     * @param element the element to insert into the tree. Duplicates are
     * allowed
     */
    public void insert(E element) {
        this.root = insert(this.root, element);
        this.size++;
    }

    /**
     * Remove the element from this AVLTree.
     * @param element the element to remove
     */
    public void remove(E element) {
        this.root = remove(this.root, element);
        this.size--;
    }

    /**
     * Check if this tree contains the element.
     * @return true if this tree contains the element, false otherwise
     */
    public boolean contains(E element) {
        return contains(this.root, element);
    }

    /**
     * Return the minimum elemnent in this tree.
     * @return the mininum element in this tree
     */
    public E findMin() {
        return findMin(this.root);
    }

    /*
     * A private helper method for insertion.
     * By taking a Node as a parameter, we can write this method
     * recursively, continuing to call insert on subtrees until the element
     * can be inserted.
     * @param node the root of some subtree of this AVLTree
     * @param element the element to insert into this subtree
     */
    private Node insert(Node node, E element) {
        if(node == null) {
            return new Node(element);
        }
        // if element is less than the value contained by node...
        if(element.compareTo(node.element) < 0) {
            // insert element into the left subtree
            node.left = insert(node.left, element);
        } else {
            // insert element into the right subtree
            node.right = insert(node.right, element);
        }
        if(Math.abs(height(node.left) - height(node.right)) > 1) {
            node = balance(node);
        }
        // update this node's height using the private helper method
        // height().
        node.height = this.height(node);
        return node;
    }

    /*
     * A private helper method for removal.
     * By taking a Node as a parameter, we can write this method
     * recursively, continuing to call remove on subtrees until the element
     * is removed.
     * @param node the root of some subtree of this AVLTree
     * @param element the element to remove from this subtree
     */
    private Node remove(Node node, E element) {

        if (node == null){
            return node;
        }

        if (element.compareTo(node.element) < 0){
            node.left = remove(node.left, element);
        }

        else if (element.compareTo(node.element) > 0){
            node.right = remove(node.right, element);
        }

        else
        {
            Node temp;

            //One child or no child case
            if (node.left == null || node.right == null) {

                //set temporary node to left child
                temp = node.left;

                //if left child doesnt exist, check if the right one does
                if (temp == null) {
                    temp = node.right;
                }

                //no children exist, remove it
                if (temp == null) {
                    node = null;
                }
                else {
                    node = temp;//copy that one child to node
                }
            }

            else //nodes with two children
            {

                /*
                LOGIC:When two children exist, take the next greatest element to the left subtree and
                set that at the root of the node so the order and parent constraints are still maintained.
                */

                temp = node.right;

                //find the smallest element on the right subtree
                temp.element = findMin(node.right);

                // copy element into current node
                node.element = temp.element;

                // Delete the smallest element on the right subtree
                node.right = remove(node.right, temp.element);
            }
        }

        //check if tree is balanced
        if(Math.abs(height(node.left) - height(node.right)) > 1) {
            node = balance(node);
        }

        // update height
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        return node;
    }

    /*
     * As for insert and remove, a private helper is used for a recursive
     * implementation.
     * @param element the element to search for
     * @param node the root of the subtree to search in
     * @return true if this subtree contains the element, false otherwise
     */
    private boolean contains(Node node, E element) {
        if(node == null) {
            return false;
        }
        if(element.compareTo(node.element) == 0) {
            return true;
        }
        if(element.compareTo(node.element) < 0) {
            return contains(node.left, element);
        } else {
            return contains(node.right, element);
        }
    }

    /*
     * Return the minimum element in the subtree rooted at node
     * @param node the root of the subtree
     * @return the minimum element in this subtree
     */
    private E findMin(Node node) {

        //recursive loop till value is found
        if (node.left == null){
            return node.element;
        }
        else{
            return findMin(node.left);
        }
    }

    /*
     * Balance the subtree rooted at this node.
     * @param node the root of the subtree to balance
     * @return the new root of the balanced subtree
     */
    private Node balance(Node node) {
        // Determine which type of rotation should be used and call on it.
        // NOTE: Don't forget to update the heights of the nodes after you
        // manipulate the subtree. Implementor's perogative on best way to
        // maintain correct heights.
        int rightHeight = -1;
        int leftHeight = -1;
        if (node.right != null)
        {  rightHeight = height(node.right);}
        if (node.left != null)
        { leftHeight = height(node.left);
        }

        //tree is imbalanced on right side
        if ((rightHeight - leftHeight) > 1)
        { //found the lowest imbalance, must perform rotation
            if ((rightHeight == 1) && (leftHeight == -1))
            { node = singleRotateWithRightChild(node);

            }
            else //not to the lowest imbalance, move down the tree
            { node.right = balance(node.right);
                rightHeight = height(node.right);
                leftHeight = height(node.left);
            }
        }
        if ((leftHeight - rightHeight) > 1)
        {
            if ((leftHeight == 1) && (rightHeight == -1))
            { node = singleRotateWithLeftChild(node);
            }
            else //not to the lowest imbalance, move down the tree
            { node.left = balance(node.left);
                rightHeight = height(node.right);
                leftHeight = height(node.left);
            }
        }
        return node;
    }

    /*
     * Perform a single rotation for left outside case.
     * @param node the root of the subtree to rotate
     * @return the new root of this subtree
     */
    private Node singleRotateWithLeftChild(Node node) {
        Node y = node.left;
        Node b = y.right;

        //Rotate
        y.right = node;
        node.left = b;

        //Update Heights
        if (node.right != null)
        {
            Node c = node.right;
            c.height--;
        }
        else
        { Node c = null;
        }
        node.height--;
        y.height++;

        if (y.left != null)
        {
            Node a = y.left;
            a.height++;
        }
        else
        { Node a = null;
        }

        //how to reduce/increase the height of all the nodes in each of the subtrees?
        return y;
    }

    /*
     * Perform a single rotation for right outside case.
     * @param node the root of the subtree to rotate
     * @return the new root of this subtree
     */
    private Node singleRotateWithRightChild(Node node) {
        Node y = node.right;
        Node a = y.right;
        Node b = y.left;

        //Rotate
        y.left = node;
        node.right = b;

        //Update Heights
        if (node.left != null)
        {
            Node c = node.left;
            c.height--;
        }
        else
        { Node c = null;
        }

        y.height++;
        a.height++;
        node.height--;


        return y;
    }

    /*
     * Perform a double rotation for left inside case.
     * @param node the root of the subtree to rotate
     * @return the new root of this subtree
     */
    private Node doubleRotateWithLeftChild(Node node) {
        //STUBBED
        node.left = singleRotateWithRightChild(node.left);
        Node newRoot = singleRotateWithLeftChild(node);
        return newRoot;
    }

    /*
     * Perform a double rotation for right inside case.
     * @param node the root of the subtree to rotate
     * @return the new root of this subtree
     */
    private Node doubleRotateWithRightChild(Node node) {
        node.right = singleRotateWithLeftChild(node.right);
        Node newRoot = singleRotateWithRightChild(node);
        return newRoot;
    }

    /*
     * Private helper method to calculate the height of a node. A node's
     * height is the larger of its left and right subtree's heights plus
     * one. To make this calculation consistent and easy, we define
     * height of an empty subtree is -1.
     * @param node the node to calculate the height of
     * @return its height as determined by the heights of its subtrees
     */
    private int height(Node node) {
        // if the left child is null, its height is -1, otherwise, retrieve
        // its height
        if (node == null)
        { return -1;
        }
        int leftHeight = (node.left == null ? -1 : node.left.height);
        // same
        int rightHeight = (node.right == null ? -1 : node.right.height);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    private class Node {
        // since this is a private inner class, and the outer AVLTree class
        // will need to freely modify the connections and update the height
        // of its nodes, the following three variables are not private.
        Node left;
        Node right;
        int height;
        E element;

        /**
         * Construct an AVLTreeNode. At instantiation, each node has no
         * children and therefore a height of 0.
         * @param element the element that this node contains
         */
        public Node(E element) {
            this.left = null;
            this.right = null;
            this.height = 0;
            this.element = element;
        }
    }
}