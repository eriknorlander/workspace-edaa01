package bst;

public class BinarySearchTree<E extends Comparable<? super E>> {
	BinaryNode<E> root;
    int size;
    
	/**
	 * Constructs an empty binary search tree. 
	 * The elements are assumed to implement 
	 * Comparable<? super E>. */
    public BinarySearchTree() {	
    }
	/**
	 * Inserts the specified element in the tree if no duplicate exists.
	 * @param x element to be inserted
	 * @return true if the the element was inserted
	 */
	public boolean add(E x) {
		if(root == null){
			root = new BinaryNode<E>(x);
			size++;
			return true;
		}
		return add(root,x);
	}
	
	private boolean add(BinaryNode<E> n, E x) {
		if(n.element.equals(x)) {
			return false;
		} 
		if (x.compareTo(n.element) < 0){
			if (n.left == null){
				n.left = new BinaryNode<E>(x);
				size++;
				return true;
			}
			add(n.left,x);
		} else if (x.compareTo(n.element) > 0) {
			if (n.right == null) {
				n.right = new BinaryNode<E>(x);
				size++;
				return true;
			}
			add(n.right,x);
		}
		return false;
	}
	
	/**
	 * Computes the height of tree.
	 * @return the height of the tree
	 */
	public int height() {
		return height(root);
	}
	
	private int height(BinaryNode<E> n){
		if(n == null){
			return -1;
		} else {
			return 1 + Math.max(height(n.left), height(n.right));
		}
	}
	
	/**
	 * Returns the number of elements in this tree.
	 * @return the number of elements in this tree
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Print tree contents in inorder.
	 */
	public void printTree() {
		printInorder(root);
	}
	
	//||
	private void printInorder(BinaryNode<E> n){
		if(n.left!=null){
			printInorder(n.left);
		}
		System.out.println(n.element);
		if(n.right!=null){
			printInorder(n.right);
		} 
	}

	/** 
	 * Builds a complete tree from the elements in the tree.
	 */
	public E[] rebuild() {
		E[] a = (E[]) new Comparable[size];
		int index = toArray(root,a,0);
		root = buildTree(a, 0, index-1);
		return a;
	}
	
	/*
	 * Adds all elements from the tree rooted at n in inorder to the array a
	 * starting at a[index].
	 * Returns the index of the last inserted element + 1 (the first empty
	 * position in a).
	 */
	private int toArray(BinaryNode<E> n, E[] a, int index) {
		if(n.left!=null){
			index = toArray(n.left, a, index);
		}
		a[index] = n.element;
		index++;
		
		if(n.right!=null){
			index = toArray(n.right, a, index);
		} 
		return index;
	}
	
	/*
	 * Builds a complete tree from the elements a[first]..a[last].
	 * Elements in the array a are assumed to be in ascending order.
	 * Returns the root of tree.
	 */
	private BinaryNode<E> buildTree(E[] a, int first, int last) {
		int mid = (last+first)/2;	
		BinaryNode<E> temp = new BinaryNode<E>(a[mid]);
		if(first<=last){
			temp.left = buildTree(a,first,mid-1);
			temp.right = buildTree(a,mid+1,last);
			return temp;
		}
		return null;
	}
	


	static class BinaryNode<E> {
		E element;
		BinaryNode<E> left;
		BinaryNode<E> right;

		private BinaryNode(E element) {
			this.element = element;
		}
	}
}