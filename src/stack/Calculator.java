package stack;

public class Calculator {
	/**
	 * 1、包含计算前缀、后缀、中缀表达式的方法
	 * 2、包含中缀转前缀、中缀转后缀的方法
	 * 3、包含一些辅助操作，如计算运算符的优先级、获取操作数等
	 * 4、注意，如果直接输入前缀表达式或后缀表达式，每一项之间用空格分开，
	 * 	  否则无法计算含有两位及以上的操作数的表达式，因为不知道哪几个数字组成一个数
	 * 5、本例中使用的栈为自定义栈，也可以用java提供的位于java.util包下的Stack
	 * 6、为减少代码并方便测试，将三个成员变量设置为public类型。
	 */
	public String prefix = ""; // 前缀表达式
	public String nifix = "";  // 中缀表达式
	public String postfix = ""; //后缀表达式
	
	public void nifix2Prefix(){ // 中缀表达式转前缀表达式
		StringBuilder str = new StringBuilder();
		DynArrayStack<Character> stack = new DynArrayStack<>();
		DynArrayStack<String> result = new DynArrayStack<>();
		for(int j = nifix.length()-1; j >= 0; j--){
			char c = nifix.charAt(j);
			if(!isOperator(c+"")){ // 如果是操作数
				int i = readDoubleReverse(j);
				String temp = nifix.substring(i+1, j+1);
				result.push(temp);
				j = i+1;
			} else {
				if(stack.isEmpty() || c == ')' || stack.getTop() == ')'){
					stack.push(c);
				} else if(c == '('){
					char op = stack.pop();
					while(op != ')'){
						result.push(op + "");
						op = stack.pop();
					}
				} else {
					if(getPriority(c) >= getPriority(stack.getTop())){
						stack.push(c);
					} else {
						result.push(stack.pop() + "");
						stack.push(c);
					}
				}
			}
		}
		while(!stack.isEmpty()){
			result.push(stack.pop() + "");
		}
		while(!result.isEmpty())
			prefix += result.pop() + " ";
	}
	
	public int readDoubleReverse(int i){ // 反向读取操作数，中缀转前缀时使用
		for(; i >= 0; i--){
			char c = nifix.charAt(i);
			int count = 0;
			if(count == 2){
				throw new NumberFormatException("输入的数据有误");
			}
			if(c == '.'){ // 小数点
				count++;
			} else if(isOperator(c+"")){ // 如果是运算符
				break;
			}
		}
		return i;
	}
	
	public void nifix2Postfix(){ // 中缀表达式转后缀表达式
		DynArrayStack<Character> stack = new DynArrayStack<>();
		for(int j = 0; j < nifix.length(); ++j){
			char c = nifix.charAt(j);
			if(!isOperator(c+"")){ //如果是操作数
				int i = readDouble(j);
				String temp = nifix.substring(j, i);
				postfix += temp + " ";
				j = i-1;
			} else { // 如果是运算符
				if(c == '(' || stack.isEmpty()){
					stack.push(c);
				} else if(c == ')') {
					char op = stack.pop();
					while(op != '('){
						postfix += op + " ";
						op = stack.pop();
					}
				} else {
					if(getPriority(c) > getPriority(stack.getTop())){
						stack.push(c);
					} else {
						postfix += stack.pop() + " ";
						stack.push(c);
					}
				}
			}
		}
		while(!stack.isEmpty()){
			postfix += stack.pop() + " ";
		}
	}
	
	public double calculateNifix(){ // 计算中缀表达式
		DynArrayStack<Character> operatorStack = new DynArrayStack<>(); // 存放运算符
		DynArrayStack<Double> operandStack = new DynArrayStack<>(); // 存放操作数
		for(int j = 0; j < nifix.length(); ++j){
			char c = nifix.charAt(j);
			if(!isOperator(c+"")){ // 如果是操作数
				int i = readDouble(j);
				String temp = nifix.substring(j, i);
				operandStack.push(Double.parseDouble(temp)); 
				j = i-1;
			} else { // 如果是运算符
				if(c == '(' || operatorStack.isEmpty() || operatorStack.getTop() == '('){
					operatorStack.push(c);
				} else if(c == ')') {
					char operator = operatorStack.pop();
					while(operator != '('){
						double a = operandStack.pop(); // 放在运算符的右边
						double b = operandStack.pop(); // 放在左边
						String s = operator+"";
						operandStack.push(calculate(b, a, s)); // 计算结果进栈
						operator = operatorStack.pop();
					}
				} else {
					if(getPriority(c) > getPriority(operatorStack.getTop())){
						operatorStack.push(c);
					} else {
						double a = operandStack.pop(); // 放在运算符的右边
						double b = operandStack.pop(); // 放在左边
						String s = operatorStack.pop()+"";
						operandStack.push(calculate(b, a, s)); // 计算结果进栈
						operatorStack.push(c);
					}
				}
			}
		}
		while(!operatorStack.isEmpty()){ // 当存放运算符的栈不为空时
			char c = operatorStack.pop();
			double a = operandStack.pop(); // 放在运算符的右边
			double b = operandStack.pop(); // 放在左边
			String s = c+"";
			operandStack.push(calculate(b, a, s)); // 计算结果进栈
		}
		
		return operandStack.pop();
	}
	
	public int readDouble(int i){ // 正向读取操作数
		for(; i < nifix.length(); ++i){
			char c = nifix.charAt(i);
			int count = 0;
			if(count == 2){
				throw new NumberFormatException("输入的数据有误");
			}
			if(c == '.'){ // 小数点
				count++;
			} else if(isOperator(c+"")){ // 如果是运算符
				break;
			}
		}
		return i;
	}
	
	public int getPriority(char c){ // 获取运算符的优先级
		switch (c) {
		case '*':
		case '/':
			return 2;
		case '+':
		case '-':
			return 1;
		default: 
			return -1;
		}
	}
	
	public double calculatePostfix(){ // 计算后缀表达式
		DynArrayStack<Double> stack = new DynArrayStack<>();
		String[] arr = postfix.split(" ");
		for(int i = 0; i < arr.length; i++){
			if(isOperator(arr[i])){ // 如果是运算符
				double a = stack.pop();
				double b = stack.pop();
				double temp = calculate(b, a, arr[i]);
				stack.push(temp);
			} else {
				stack.push(Double.parseDouble(arr[i]));
			}
		}
		return stack.pop();
	}
	
	public double calculatePrefix(){ // 计算前缀表达式
		DynArrayStack<Double> stack = new DynArrayStack<>();
		String[] arr = prefix.split(" ");
		for(int i = arr.length-1; i >= 0; i--){
			if(isOperator(arr[i])){ // 如果是运算符
				double a = stack.pop();
				double b = stack.pop();
				double temp = calculate(a, b, arr[i]);
				stack.push(temp);
			} else {
				stack.push(Double.parseDouble(arr[i]));
			}
		}
		return stack.pop();
	}
	
	
	
	public boolean isOperator(String s){ // 判断是否是运算符
		return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/") || s.equals("(") || s.equals(")");
	}
	
	public double calculate(double a, double b, String s){ // 计算
		switch (s) {
		case "*":
			return a*b;
		case "/":
			return a/b;
		case "+":
			return a+b;
		case "-":
			return a-b;
		default: 
			return -1;
		}
	}
	
}

