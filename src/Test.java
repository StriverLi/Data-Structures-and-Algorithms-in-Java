import stack.Calculator;

public class Test {

	public static void main(String[] args) {
		
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
