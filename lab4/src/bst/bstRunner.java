package bst;

public class bstRunner {
	public static void main(String[] args){
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		bst.add(10);
		bst.add(1);
		bst.add(3);
		bst.add(7);
		bst.add(6);
		bst.add(2);
		bst.add(9);
		bst.add(8);
		bst.add(9);
		bst.add(11);
		bst.add(12);
	    bst.printTree();
		BSTVisualizer w = new BSTVisualizer("Ett träd", 200, 200);
		//w.drawTree(bst);
		bst.rebuild();
		w.drawTree(bst);
	}	
}
