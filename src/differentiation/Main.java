package differentiation;

public class Main {
	public static void main(String args[])
	{
		PolishParser p = new PolishParser();
	//	p.parse("(+ x (+ x x))");
		p.parse("(cos (* 2 x))");
		p.parse("(- (+ x x) x)");
	}
}
