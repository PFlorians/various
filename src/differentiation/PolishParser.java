package differentiation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class PolishParser 
{

	private Stack<String> stack;
	
	
	{
		stack = new Stack<String>();
	}
	
	
	public void parse(String expression)
	{
		//String[] partial_expressions = expression.split("(?<![^\\s\\(\\)])([^\\s\\(\\)]+)");
		String partial_expressions[] = expression.split( "[\\s\\(\\)]" );

		
		List<String> tmp_buffer = Arrays.asList( partial_expressions );
		List<String> l = tmp_buffer.stream().filter(Pattern.compile("[^\\s\\(\\)]+").asPredicate()).collect(Collectors.toList());

		convert_and_simplify( partial_expressions );
		
		/*long literals = Pattern.compile("(?<!\\S)([a-zA-z]{1})").matcher(expression).results().count();
		long functions = Pattern.compile("(?<=\\S)([a-zA-z]{1})").matcher(expression).results().count();
		long signs = Pattern.compile("[+-/*]{1}").matcher(expression).results().count();
		long constants = Pattern.compile("\\d{1}").matcher(expression).results().count();
	*/	
	}
	
	
	private ArrayList<String> convert_and_simplify(String[] expressions)
	{
		int i;
		ArrayList<String> simplified = new ArrayList<String>();
		String op1, op2;
		
		for( i = expressions.length - 1; i >= 0; i-- )
		{
			//push to stack if variable or digit
			if( Pattern.matches( "[^*\\/+\\-\\^\\s]", expressions[i] ) 
				&& Pattern.matches( "(?=(cos|sin|tan|cot|exp))", expressions[i] ) == false )
			{
				stack.push(expressions[i]);
			}
			//operator
			else if( Pattern.matches( "[*\\/+\\-\\^]", expressions[i] ) && 
					 Pattern.matches( "(?=(cos|sin|tan|cot|exp))", expressions[i] ) == false )
			{
				op1 = stack.pop();
				op2 = stack.pop();
				simplified.add( arithmethic_simplification_basic( op1, op2, expressions[i] ) );
			}
		}
		return simplified;
	}
	
	
	private String arithmethic_simplification_basic(String operand1, String operand2, String operator)
	{
		//this does basic arithmetic
		Double op1, op2;
		
		//check if the input has digits(some real number), no scientific notation
		if( Pattern.matches( "^(\\d*\\.{0,1}\\d*)$", operand1 ) &&
			Pattern.matches( "^(\\d*\\.{0,1}\\d*)$", operand2 ) && 
				operator.length() == 1 )
		{
			op1 = Double.parseDouble( operand1 );
			op2 = Double.parseDouble( operand2 );
			return Double.toString( opcode_decision_simplification( op1.doubleValue(), op2.doubleValue(), operator.charAt( 0 ) ) );
		}
		//both operands are a variable
		else if( Pattern.matches( "(?<=\\s)([a-zA-Z]+)(?=\\s|\\))", operand1 ) &&
				 Pattern.matches( "(?<=\\s)([a-zA-Z]+)(?=\\s|\\))", operand2 ) && 
				 operator.length() == 1 )
		{
			
		}
		//one of the operands is a number
		else if( ( Pattern.matches( "^(\\d*\\.{0,1}\\d*)$", operand1 ) &&
				   Pattern.matches( "(?<=\\s)([a-zA-Z]+)(?=\\s|\\))", operand2 ) ) || 
				 ( Pattern.matches( "^(\\d*\\.{0,1}\\d*)$", operand2 ) &&
				   Pattern.matches( "(?<=\\s)([a-zA-Z]+)(?=\\s|\\))", operand1 ) ) && 
				 operator.length() == 1 )
		{
			
		}
		return null;
	}
	
	
	private double opcode_decision_simplification(double op1, double op2, char oper)
	{
		switch(oper)
		{
			case '+':
				return op1 + op2;
			case '-':
				return op1 - op2;
			case '*':
				return op1 * op2;
			case '/':
				return op1 / op2;
			case '^':
				return Math.pow(op1,  op2);
			default:
				return 0xffffffffffffffffL;
		}
	}
	
	
	private String opcode_decision_simplification(String op1, String op2, String oper)
	{
		String res;
		
		return null;
	}
	
	
}
