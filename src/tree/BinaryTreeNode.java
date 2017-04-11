package tree;

import queue.DynArrayQueue;
import queue.LLQueue;
import stack.DynArrayStack;
import stack.LLStack;

public class BinaryTreeNode<T> {
	/**
	 * 本类中使用的栈为自定义栈，也可以使用java中封装的栈
	 */
	private T data;
	private BinaryTreeNode<T> left;  // 左子树
	private BinaryTreeNode<T> right; // 右子树
	private static int preIndex = 0; // 在通过前序序列和中序序列构造二叉树是应用
	
	public BinaryTreeNode(){}; // 空参构造
	public BinaryTreeNode(T data) { // 有参构造
		super();
		this.data = data;
	}
	
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public BinaryTreeNode<T> getLeft() {
		return left;
	}
	public void setLeft(BinaryTreeNode<T> left) {
		this.left = left;
	}
	public BinaryTreeNode<T> getRight() {
		return right;
	}
	public void setRight(BinaryTreeNode<T> right) {
		this.right = right;
	}
	
	/***********************基本操作**************************/
	
	public static<T> void preOrder(BinaryTreeNode<T> root){ // 前序遍历递归算法
		if(root != null){
			System.out.println(root.getData());
			preOrder(root.getLeft());
			preOrder(root.getRight());
		}
	}
	
	public static<T> void inOrder(BinaryTreeNode<T> root){ // 中序遍历递归算法
		if(root != null){
			inOrder(root.getLeft());
			System.out.println(root.getData());
			inOrder(root.getRight());
		}
	}
	
	public static<T> void postOrder(BinaryTreeNode<T> root){ // 中后序遍历递归算法
		if(root != null){
			postOrder(root.getLeft());
			postOrder(root.getRight());
			System.out.println(root.getData());
		}
	}
	
	public static<T> void preOrderNonRecursion(BinaryTreeNode<T> root){ // 非递归前序遍历
		LLStack<BinaryTreeNode<T>> stack = new LLStack<>();
		while(true){
			while(root != null){
				System.out.println(root.getData());
				stack.push(root);
				root = root.getLeft();
			}
			if(stack.isEmpty())
				break;
			root = stack.pop().getRight();
		}
	}
	
	public static<T> void inOrderNonRecursion(BinaryTreeNode<T> root){ // 非递归中序遍历
		LLStack<BinaryTreeNode<T>> stack = new LLStack<>();
		while(true){
			while(root != null){
				stack.push(root);
				root = root.getLeft();
			}
			if(stack.isEmpty())
				break;
			root = stack.pop();
			System.out.println(root.getData());
			root = root.getRight();
		}
	}
	
	public static<T> void postOrderNonRecursion(BinaryTreeNode<T> root){ // 后序遍历非递归算法
		LLStack<BinaryTreeNode<T>> stack = new LLStack<>();
		while(true){
			if(root != null){
				stack.push(root);
				root = root.getLeft();
			} else {
				while(!stack.isEmpty() && root == stack.getTop().getRight()){ // 判断右子树是否遍历完成
					root = stack.pop();
					System.out.println(root.getData());
				}
				// 判断上述while循环退出的原因
				if(stack.isEmpty()) // 如果是因为栈为空，退出外层循环
					break;
				else // 如果是因为右子树未遍历完，将右子树作为根结点循环
					root = stack.getTop().getRight();
			}
		}
	}
	
	public static<T> void levelOrder(BinaryTreeNode<T> root){ // 层次遍历
		LLQueue<BinaryTreeNode<T>> queue = new LLQueue<>();
		BinaryTreeNode<T> temp;
		if(root == null)
			return;
		queue.enQueue(root);
		while(!queue.isEmpty()){
			temp = queue.deQueue();
			System.out.println(temp.getData());
			if(temp.getLeft() != null)
				queue.enQueue(temp.getLeft());
			if(temp.getRight() != null)
				queue.enQueue(temp.getRight());
		}
	}
	
	/**
	 * 通过中序序列和前序序列建立二叉树算法步骤
	 
	 * 1、从前序序列中取一个元素，然后将前序序列索引加一
	 * 2、根据所选元素的值，创建一个新的树节点(root)
	 * 3、查找所选结点在中序序列中的索引，用变量tempIndex标记
	 * 4、递归调用BuildBinaryTree为tempIndex之前的所有结点构建一棵子树，并将其作为root的左子树
	 * 5、递归调用BuildBinaryTree为tempIndex之后的所有结点构建一课子树，并将其作为root的右子树
	 * 6、返回root
	 * 参数解释：
	 * 		inOrder:中序序列数组
	 * 		preOrder:前序序列数组
	 * 		inStart:在中序遍历中查找所选结点的起始位置
	 * 		inEnd:在中序遍历序列中查找所选结点的结束位置
	 */
	@SuppressWarnings("unchecked")
	public static<T> BinaryTreeNode<T> buildBinaryTree(Object[] inOrder, Object[] preOrder, int inStart, int inEnd){
		if(inStart > inEnd){
			return null;
		}
		T temp = (T)preOrder[preIndex];
		preIndex++; // 前序序列索引加一
		BinaryTreeNode<T> root = new BinaryTreeNode<T>(temp);
		if(inStart == inEnd){
			return root;
		}
		int tempindex = 0;
		for(int i = inStart; i <= inEnd; ++i){ // 查找temp在中序遍历序列中的位置
			if((T)inOrder[i] == temp){
				tempindex = i;
				break;
			}
		}
		root.setLeft(buildBinaryTree(inOrder, preOrder, inStart, tempindex-1));
		root.setRight(buildBinaryTree(inOrder, preOrder, tempindex+1, inEnd));
		return root;
		
	}
	
	public static<T> BinaryTreeNode<T> mirrorOfBinaryTree(BinaryTreeNode<T> root){ // 反转二叉树
		BinaryTreeNode<T> temp;
		if(root != null){
			mirrorOfBinaryTree(root.getLeft());
			mirrorOfBinaryTree(root.getRight());
			temp = root.getLeft();
			root.setLeft(root.getRight());
			root.setRight(temp);
		}
		return root;
	}
	
	
	public static<T> boolean areMirrors(BinaryTreeNode<T> root1, BinaryTreeNode<T> root2){ // 判断两棵二叉树是否互为反转二叉树
		if(root1 == null && root2 == null)
			return true;
		if(root1 == null || root2 == null)
			return false;
		if(root1.getData() != root2.getData())
			return false;
		else return areMirrors(root1.getLeft(), root2.getLeft()) && areMirrors(root1.getRight(), root2.getRight());
	}
	
	public static<T> int heightOfBinaryTreeInRecursion(BinaryTreeNode<T> root){ // 求二叉树的深度递归算法
		int leftHeight,rightHeight;
		if(root == null)
			return 0;
		leftHeight = heightOfBinaryTreeInRecursion(root.getLeft());
		rightHeight = heightOfBinaryTreeInRecursion(root.getRight());
		if(leftHeight > rightHeight)
			return (leftHeight+1);
		else 
			return (rightHeight+1);
	}
	
	public static<T> int heightOfBinaryTree(BinaryTreeNode<T> root){ // 求二叉树深度的非递归算法
		if(root == null)
			return 0;
		DynArrayQueue<BinaryTreeNode<T>> queue = new DynArrayQueue<>();
		queue.enQueue(root);
		queue.enQueue(null); // 作为分层的标记
		int count = 0; // 第一层
		while(!queue.isEmpty()){
			root = queue.deQueue();
			if(root == null){ // 如果一层结束
				if(!queue.isEmpty()) // 为下一层添加标记
					queue.enQueue(null);
				count++;
			} else {
				if(root.getLeft() != null)
					queue.enQueue(root.getLeft());
				if(root.getRight() != null)
					queue.enQueue(root.getRight());
			}
		}
		return count;
	}
	
	public static<T> void levelOrderTraversalInReverse(BinaryTreeNode<T> root){ // 逆向逐层输出元素
		if(root == null)
			return;
		DynArrayQueue<BinaryTreeNode<T>> queue = new DynArrayQueue<>();
		DynArrayStack<BinaryTreeNode<T>> stack = new DynArrayStack<>();
		queue.enQueue(root);
		while(!queue.isEmpty()){
			root = queue.deQueue();
			if(root.getRight() != null)
				queue.enQueue(root.getRight());
			if(root.getLeft() != null)
				queue.enQueue(root.getLeft());
			stack.push(root);
		}
		while(!stack.isEmpty())
			System.out.print(stack.pop().getData() + " ");
	}
	
	public static<T> int numOfLeaves(BinaryTreeNode<T> root){ // 获取二叉树叶子节点的个数的非递归算法
		if(root == null)
			return 0;
		DynArrayQueue<BinaryTreeNode<T>> queue = new DynArrayQueue<>();
		queue.enQueue(root);
		int count = 0;
		while(!queue.isEmpty()){
			root = queue.deQueue();
			if(root.getLeft() == null && root.getRight() == null){
				count++;
			} else {
				if(root.getLeft() != null)
					queue.enQueue(root.getLeft());
				if(root.getRight() != null)
					queue.enQueue(root.getRight());
			}
		}
		return count;
	}
	
	public static<T> int numOfLeavesInRecursion(BinaryTreeNode<T> root){ // 获取二叉树叶子节点的个数的递归算法
		if(root == null)
			return 0;
		if(root.getLeft() == null && root.getRight() == null)
			return 1;
		return numOfLeavesInRecursion(root.getLeft()) + numOfLeavesInRecursion(root.getRight());
	}
	
}
