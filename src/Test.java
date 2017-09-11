import stack.Calculator;
import tree.BinaryTreeNode;

public class Test {

	public static void main(String[] args) {
		//stacktest();
		BinaryTreeNode<Integer> root = buildBinaryTree();
//		BinaryTreeNode<Integer> root2 = BinaryTreeNode.mirrorOfBinaryTree(root);
//		BinaryTreeNode.mirrorOfBinaryTree(root);
//		BinaryTreeNode.levelOrder(root);
		
//		System.out.println(BinaryTreeNode.heightOfBinaryTree(root));
//		BinaryTreeNode.levelOrderTraversalInReverse(root);
		
		
//		System.out.println(BinaryTreeNode.numOfLeaves(root));
//		System.out.println(BinaryTreeNode.numOfLeavesInRecursion(root));
//		System.out.println(BinaryTreeNode.heightOfBinaryTreeInRecursion(root));
//		System.out.println(BinaryTreeNode.heightOfBinaryTree(root));
		
		
//		buildeBinaryTreeTest(root);
//		recursion(root); // 递归遍历
		
//		root.inOrderNonRecursion(root);
//		System.out.println("----------------------");
//		root.preOrderNonRecursion(root);
//		System.out.println("----------------------");
//		root.postOrderNonRecursion(root);
		
//		BinaryTreeNode<Integer> a = new BinaryTreeNode<Integer>(4);
//		BinaryTreeNode<Integer> b = new BinaryTreeNode<Integer>(8);
//		BinaryTreeNode<Integer> parent = BinaryTreeNode.LCA(root, a, b);
//		System.out.println(parent.getData());
		BinaryTreeNode<Integer> node = new BinaryTreeNode<>(10);
		BinaryTreeNode.printAllAncestors(root, node);
	}



	public static void buildeBinaryTreeTest(BinaryTreeNode<Integer> root) {
		Integer[] inOrder = {12,11,1,7,6,2,4,5,13,3,9,8,10};
		Integer[] preOrder = {1,11,12,2,6,7,3,4,5,13,8,9,10};
		Integer[] postOrder = {12,11,7,6,13,5,4,9,10,8,3,2,1};
		BinaryTreeNode<Integer> newroot = BinaryTreeNode.buildBinaryTree(inOrder, preOrder, 0, 12);
		BinaryTreeNode.inOrder(newroot);
		System.out.println("------------");
		BinaryTreeNode.preOrder(root);
	}
	
	

	public static BinaryTreeNode<Integer> buildBinaryTree() {
		BinaryTreeNode<Integer> root = new BinaryTreeNode<>();
		root.setData(1);
		BinaryTreeNode<Integer> left = new BinaryTreeNode<>();
		left.setData(2);
		BinaryTreeNode<Integer> right = new BinaryTreeNode<>();
		right.setData(3);
		
		root.setRight(left);
		left.setRight(right);
		right.setLeft(new BinaryTreeNode<Integer>(4));
		right.getLeft().setRight(new BinaryTreeNode<Integer>(5));
		right.getLeft().getRight().setRight(new BinaryTreeNode<Integer>(13));
		right.setRight(new BinaryTreeNode<>(8));
		right.getRight().setLeft(new BinaryTreeNode<Integer>(9));
		right.getRight().setRight(new BinaryTreeNode<Integer>(10));
		left.setLeft(new BinaryTreeNode<Integer>(6));
		left.getLeft().setLeft(new BinaryTreeNode<Integer>(7));
		
		root.setLeft(new BinaryTreeNode<Integer>(11));
		root.getLeft().setLeft(new BinaryTreeNode<Integer>(12));
		return root;
	}

	public static void recursion(BinaryTreeNode<Integer> root) {
		BinaryTreeNode.inOrder(root);
		System.out.println("------------------");
		BinaryTreeNode.preOrder(root);
		System.out.println("------------------");
		BinaryTreeNode.postOrder(root);
	}

	public static void stacktest() {
		Calculator c = new Calculator();
		c.nifix = "5*((12/4+3*2)-(4+2)/3)+1.5*2";
		c.nifix2Prefix();
		c.nifix2Postfix();
		System.out.println("中缀表达式计算结果: " + c.calculateNifix());
		System.out.println("前缀表达式为: " + c.prefix);
		System.out.println("前缀表达式计算结果: " + c.calculatePrefix());
		System.out.println("后缀缀表达式为: " + c.postfix);
		System.out.println("后缀表达式计算结果: " + c.calculatePostfix());
	}
}
