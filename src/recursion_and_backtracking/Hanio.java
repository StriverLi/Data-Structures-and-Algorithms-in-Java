package recursion_and_backtracking;

public class Hanio {

	public static void main(String[] args) {
		TowersOfHanoi(4,'a','b','c');
	}
	public static void TowersOfHanoi(int n, char frompeg, char topeg, char auxpeg){
		if(n == 1){
			System.out.println("Move disk 1 from pge " + frompeg + " to peg " + topeg);
			return;
		}
		TowersOfHanoi(n-1,frompeg,auxpeg,topeg);
		System.out.println("Move disk " + n + " from peg " + frompeg + " to peg " + topeg);
		TowersOfHanoi(n-1,auxpeg,topeg,frompeg);
	}

}
