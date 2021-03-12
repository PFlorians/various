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
		
		
		convert_and_simplify( l );
		
		/*long literals = Pattern.compile("(?<!\\S)([a-zA-z]{1})").matcher(expression).results().count();
		long functions = Pattern.compile("(?<=\\S)([a-zA-z]{1})").matcher(expression).results().count();
		long signs = Pattern.compile("[+-/*]{1}").matcher(expression).results().count();
		long constants = Pattern.compile("\\d{1}").matcher(expression).results().count();
	*/	
	}
	
	
	private String convert_and_simplify(List<String> expressions)
	{
		int i;
		String simplified = "";
		String op1, op2, res;
		
		for( i = expressions.size() - 1; i >= 0; i-- )
		{
			//push to stack if variable or digit
			if( Pattern.matches( "[^*\\/+\\-\\^\\s]", expressions.get(i) ) 
				&& Pattern.matches( "(?=(cos|sin|tan|cot|exp))", expressions.get(i) ) == false )
			{
				stack.push( expressions.get(i) );
			}
			//operator
			else if( Pattern.matches( "[*\\/+\\-\\^]", expressions.get(i) ) && 
					 Pattern.matches( "(?=(cos|sin|tan|cot|exp))", expressions.get(i) ) == false )
			{
				op1 = stack.pop();
				op2 = stack.pop();
				res = arithmethic_simplification_basic( op1, op2, expressions.get(i) );
				stack.push(res);
			}
		}
		while( !stack.isEmpty() )
		{
			simplified += stack.pop();
		}
		return simplified;
	}
	
	
	private String arithmethic_simplification_basic(String operand1, String operand2, String operator)
	{
		//this does basic arithmetic
		Double op1, op2;
		
		if( operator.length() != 1  )
		{
			return null;
		}
		
		//check if the input has digits(some real number), no scientific notation
		if( Pattern.matches( "^(\\d*\\.{0,1}\\d*)$", operand1 ) &&
			Pattern.matches( "^(\\d*\\.{0,1}\\d*)$", operand2 ) )
		{
			op1 = Double.parseDouble( operand1 );
			op2 = Double.parseDouble( operand2 );
			return Double.toString( opcode_decision_simplification( op1.doubleValue(), op2.doubleValue(), operator.charAt( 0 ) ) );
		}
		//one or both operands start with digits
		else if( Pattern.matches( "(?<=[0-9])([a-zA-Z\\^]+)([a-zA-Z0-9\\^]*)", operand1 ) ||
				 Pattern.matches( "(?<=[0-9])([a-zA-Z\\^]+)([a-zA-Z0-9\\^]*)", operand2 )  )
		{
			return opcode_decision_simplification_complex( operand1, operand2, operator.charAt( 0 ) );
		}
		else
		{
			return opcode_decision_simplification( operand1, operand2, operator.charAt( 0 ) );
		}
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
	
	
	private String opcode_decision_simplification(String op1, String op2, char oper)
	{
		
		String res = null;
		
		switch(oper)
		{
			case '+':
				res = ( op1.equals(op2) )?( "2"+op1 ):( op1 + "+" + op2 );
				return res;
			case '-':
				res = ( op1.equals(op2) )?( "0" ):( op1 + "-" + op2 );
				return res;
			case '*':
				res = ( op1.equals(op2) )?( op1 + "^" + "2" ):( op1 + op2 );
				return res;
			case '/':
				res = ( op1.equals(op2) )?( "1" ):( op1 + "/" + op2 );
				return res;
			case '^':
				res = ( op1 + "^" + op2 );
				return res;
			default:
				return res;
		}
		
	}
	
	private String opcode_decision_simplification_complex(String op1, String op2, char oper)
	{
		Matcher m1 = Pattern.compile("^[0-9]+").matcher( op1 );
		Matcher m2 = Pattern.compile("^[0-9]+").matcher( op2 );
		String operand1 = op1, operand2 = op2;
		byte decisions = 0b0;
		
		double digits_op1 = 0, digits_op2 = 0;
		
		operand1 = op1;
		operand2 = op2;
		
		if( m1.find() )
		{
			digits_op1 = Double.parseDouble( m1.group( 1 ) );
			operand1 = operand1.replaceFirst("^[0-9]+", "");
			decisions |= (byte) 0b100;					
		}
		if( m2.find() )
		{
			digits_op2 = Double.parseDouble( m2.group( 1 ) );
			operand2 = operand2.replaceFirst("^[0-9]+", "");
			decisions |= (byte) 0b10;
		}
		
		decisions = (byte) (decisions | ( ( operand1.equals(operand2))?0b1:0b0 ) );
		
		
		switch(oper)
		{
			case '+':
				return opcode_complex_plus(decisions, digits_op1, digits_op2, operand1, operand2);
			case '-':
				return opcode_complex_minus(decisions, digits_op1, digits_op2, operand1, operand2);
			case '*':
				
			default:
				return null;
		}
		
	}
	
	private String opcode_complex_plus(byte flags, double digits_op1, double digits_op2, String op1, String op2)
	{
		switch(flags)
		{
			case 0b111:
				return Double.toString( digits_op1 + digits_op2 ) +  op1;
			case 0b101:
				return Double.toString( ( digits_op1 + 1.0 ) ) + op1;
			case 0b011:
				return Double.toString( ( digits_op2 + 1.0 ) ) + op1;
			case 0b001:
				return "2" + op1;
			default:
				return op1 + "+" + op2;
		}
	}
	
	private String opcode_complex_minus(byte flags, double digits_op1, double digits_op2, String op1, String op2)
	{
		
		switch(flags)
		{
			case 0b111:
				return Double.toString( digits_op1 - digits_op2 ) +  op1;
			case 0b101:
				return Double.toString( ( digits_op1 - 1.0 ) ) + op1;
			case 0b011:
				return Double.toString( ( digits_op2 - 1.0 ) ) + op1;
			case 0b001:
				return "0";
			default:
				return op1 + "-" + op2;
		}
		
	}
	
	private String opcode_complex_multiply(byte flags, double digits_op1, double digits_op2, String op1, String op2)
	{
		//need to take exponents into consideration
		switch(flags)
		{
			case 0b111:
				return Double.toString( digits_op1 * digits_op2 ) + op1;
			case 0b101:
				return Double.toString( ( digits_op1 * 1.0 ) ) + op1;
			case 0b011:
				return Double.toString( ( digits_op2 * 1.0 ) ) + op1;
			case 0b001:
				return "0";
			default:
				return null;
		}
	}
}
