package stack;

import java.util.Stack;

public class ReverseStack {
	/*
	 * 给定一个栈，将其中的内容逆置
	 * 要求：
	 * 		1.只能使用栈操作push和pop
	 * 		2.不能使用其他的类似于数组、字符串等做过渡
	 */

	public static void main(String[] args) {
		Stack<Integer> st = new Stack<>();
		st.push(1);
		st.push(2);
		st.push(3);
		st.push(4);
		System.out.println("反转前： " +st.toString());
		reverseStack(st);
		System.out.println("反转后：" + st.toString());
		
	}
	
	public static<T> void reverseStack(Stack<T> stack){
		if(stack.isEmpty())
			return;
		T temp = stack.pop();
		reverseStack(stack); // 将原栈中的元素递归出栈
		insertAtButtom(stack,temp); // 将出栈的元素插入栈底
	}
	public static<T> void insertAtButtom(Stack<T> stack, T data){
		if(stack.isEmpty()){
			stack.push(data); // 栈为空后将该元素插入栈底
			return;
		}
		T temp = stack.pop();
		insertAtButtom(stack,data); // 将栈中的元素递归出来直至栈为空
		stack.push(temp); // 将开始递归出的元素依次入栈
	}

}
