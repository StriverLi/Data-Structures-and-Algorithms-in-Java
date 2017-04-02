package queue;

import java.util.EmptyStackException;

import linkedlist.ListNode;

public class LLQueue<T> {
	/**
	 * 基于链表实现队列
	 */
	private ListNode<T> frontNode = null; // 队头结点
	private ListNode<T> rearNode = null; // 队尾结点
	private int size = 0; // 队列中元素个数
	
	public boolean isEmpty(){ // 判断队列是否为空
		if(frontNode == null || frontNode.getData() == null)
			return true;
		else
			return false;
	}
	
	public void enQueue(T data){ // 入队操作
		if(rearNode == null){ // 第一次元素入队
			rearNode = new ListNode<T>(data);
			frontNode = rearNode;
		} else {
			ListNode<T> node = new ListNode<T>(data);
			rearNode.setNext(node);
			rearNode = rearNode.getNext();
		}
		size++;
	}
	
	public T deQueue(){ // 出队操作
		if(isEmpty())
			throw new EmptyStackException();
		T data = frontNode.getData();
		if(frontNode == rearNode)
			frontNode = rearNode = null;
		else
			frontNode = frontNode.getNext();
		size--;
		return data;
	}
	
	public T getFront(){ // 获取队头元素
		if(isEmpty())
			throw new EmptyStackException();
		return frontNode.getData();
	}
	
	public int size(){
		return size;
	}
}
