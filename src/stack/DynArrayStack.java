package stack;

public class DynArrayStack<T> {
	/**
	 * 通过动态数组来实现栈
	 * 1、capacity为数组容量
	 * 2、定义数组初始容量为10，当栈满后数组容量变为原数组的1.5倍
	 */
	private int top = -1;  // 栈顶所在位置
	private int capacity = 10;
	private Object[] arr = new Object[capacity];
	
	public boolean isEmpty(){ //判断栈是否为空
		return top == -1;
	}
	
	public void push(T data){ //将元素压入栈
		if(top == capacity - 1){ // 当栈满时，数组扩容
			capacity += capacity/2;  // 容量变为原来的1.5倍
			Object[] newarr = new Object[capacity];
			System.arraycopy(arr, 0, newarr, 0, arr.length); // 将arr中的元素复制到newarr
			arr = newarr;
		}
		arr[++top] = data;
	}
	
	@SuppressWarnings("unchecked")
	public T pop(){ //栈顶元素出栈
		if(isEmpty())
			throw new ArrayIndexOutOfBoundsException();  // 如果栈为空，抛出异常
		return (T)arr[top--];
	}
	
	@SuppressWarnings("unchecked")
	public T getTop(){ // 获取栈顶元素，不用出栈
		if(isEmpty())
			throw new ArrayIndexOutOfBoundsException();
		return (T)arr[top];
	}
	
	public void clear(){ // 清空栈
		top = -1;
	}
	 
	public int size(){ // 返回栈中元素个数
		return top+1;
	}
	
}
