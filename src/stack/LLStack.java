package stack;

import java.util.EmptyStackException;

import linkedlist.ListNode;

public class LLStack<T> {
	/**
	 * 通过链表实现栈
	 * 1、通过在链表表头不断插入的方式实现push操作
	 * 2、删除表头结点实现pop操作
	 */
	private ListNode<T> headNode = new ListNode<T>(null);
	private int size = 0; // 栈中元素个数
	
	public void push(T data){ // 入栈
		if(headNode == null){//头结点不存在，即栈为空时
			headNode = new ListNode<T>(data);
		} else if(headNode.getData() == null){ //头结点数据为null，即第一次向栈中加元素
			headNode.setData(data); 
		} else {
			ListNode<T> node = new ListNode<T>(data);
			node.setNext(headNode);
			headNode = node;
		}
		size++;
	}
	
	public T getTop(){ // 获取栈顶元素
		if(headNode == null)
			throw new EmptyStackException();
		else
			return headNode.getData();
	}
	
	public T pop(){ // 出栈
		if(headNode == null){
			throw new EmptyStackException();
		} else {
			T data = headNode.getData();
			headNode = headNode.getNext();
			size--;
			return data;
		}
	}
	
	public boolean isEmpty(){ // 判断栈是否为空
		if(headNode == null) // 表头结点不存在
			return true;
		else if(headNode.getData() == null) //表头结点数据域为null
			return true;
		else
			return false;
	}
	
	public int size(){ // 返回栈中元素个数
		return size;
	}
	
}
