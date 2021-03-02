package differentiation;
//https://www.codewars.com/kata/584daf7215ac503d5a0001ae
public class Main {
	public static void main(String args[])
	{
		PolishParser p = new PolishParser();
	//	p.parse("(+ x (+ x x))");
		p.parse("(cos (* 2 x))");
		p.parse("(- (+ x x) x)");
	}
}
