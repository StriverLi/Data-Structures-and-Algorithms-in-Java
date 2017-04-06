package tree;

import stack.LLStack;

public class BinaryTreeNode<T> {
	/**
	 * 本类中使用的栈为自定义栈，也可以使用java中封装的栈
	 */
	private T data;
	private BinaryTreeNode<T> left;  // 左子树
	private BinaryTreeNode<T> right; // 右子树
	
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
	
	public void preOrder(BinaryTreeNode<T> root){ // 前序遍历递归算法
		if(root != null){
			System.out.println(root.getData());
			preOrder(root.getLeft());
			preOrder(root.getRight());
		}
	}
	
	public void inOrder(BinaryTreeNode<T> root){ // 中序遍历递归算法
		if(root != null){
			inOrder(root.getLeft());
			System.out.println(root.getData());
			inOrder(root.getRight());
		}
	}
	
	public void postOrder(BinaryTreeNode<T> root){ // 中后序遍历递归算法
		if(root != null){
			postOrder(root.getLeft());
			postOrder(root.getRight());
			System.out.println(root.getData());
		}
	}
	
	public void preOrderNonRecursion(BinaryTreeNode<T> root){ // 非递归前序遍历
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
	
	public void inOrderNonRecursion(BinaryTreeNode<T> root){ // 非递归中序遍历
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
	
	public void postOrderNonRecursion(BinaryTreeNode<T> root){ // 后序遍历非递归算法
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
	
}
