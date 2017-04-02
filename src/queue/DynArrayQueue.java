package queue;

import java.util.EmptyStackException;

public class DynArrayQueue<T>{
	/**
	 * 基于动态循环数组实现队列
	 * 1、当第一次元素入栈时，队头指向第一个元素，即由-1变为0
	 * 2、队列中front指向队头元素，rear指向队尾元素
	 * 3、当front==rear时，队列中只有一个元素
	 */
	private int front = -1; // 队头
	private int rear = -1; // 队尾
	private int capacity = 10; // 容量
	private Object[] array = new Object[capacity];
	
	public boolean isEmpty(){ // 判断队列是否为空
		return front == -1;
	}
	
	public void enQueue(T data){ // 入队操作
		if((rear+1)%capacity == front){ // 如果队列已满
			Object[] newarr = new Object[capacity+capacity/2];  // 新建1.5倍大小的数组
			System.arraycopy(array, 0, newarr, 0, array.length);
			if(rear < front){
				for(int i = 0; i < front; ++i){
					newarr[i+capacity] = array[i];
					newarr[i] = null;
				}
				rear = rear+capacity; // 新队尾的位置
			}
			array = newarr;
			capacity = capacity + capacity/2;
		}
		rear = (rear+1) % capacity;
		array[rear] = data;
		if(front == -1) // 第一次入队操作后，使队首指向第一个元素
			front = 0;
	}
	
	@SuppressWarnings("unchecked")
	public T deQueue(){
		if(isEmpty())
			throw new EmptyStackException();
		T data = (T) array[front];
		if(front == rear) // 如果队列中只有一个元素
			front = rear = -1;
		else
			front = (front+1)%capacity;
		return data;
	}
	
	@SuppressWarnings("unchecked")
	public T getFront(){ // 获取队头元素
		if(isEmpty())
			throw new EmptyStackException();
		return (T)array[front];
	}
	
	public int size(){ // 队列中元素个数
		if(front == -1)
			return 0;
		int size = (rear-front+1+capacity)%capacity;
		return size == 0 ? capacity : size;
	}
	
	
}
