package differentiation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class PolishParser {
	
	public void parse(String expression)
	{
		//String[] partial_expressions = expression.split("(?<![^\\s\\(\\)])([^\\s\\(\\)]+)");
		String partial_expressions[] = expression.split("[\\s\\(\\)]");

		
		List<String> tmp_buffer = Arrays.asList(partial_expressions);
		List<String> l = tmp_buffer.stream().filter(Pattern.compile("[^\\s\\(\\)]+").asPredicate()).collect(Collectors.toList());

		arithmethicSimplifier(l);
		
		/*long literals = Pattern.compile("(?<!\\S)([a-zA-z]{1})").matcher(expression).results().count();
		long functions = Pattern.compile("(?<=\\S)([a-zA-z]{1})").matcher(expression).results().count();
		long signs = Pattern.compile("[+-/*]{1}").matcher(expression).results().count();
		long constants = Pattern.compile("\\d{1}").matcher(expression).results().count();
	*/	
	}
	private void arithmethicSimplifier(List<String> expressions)
	{
		int i, state = 0;
		for( i = 0; i < expressions.size(); i++ )
		{
			state = switchingLogic(expressions.get(i), state);
		}
	}
	private int switchingLogic(String expression, int state)
	{
		int new_state = -1;
		switch (state)
		{
			case 0: //initial
				new_state = initialStateLogic(expression);
				break;
			case 1: //operator
				break;
			case 2: //function
				break;
		}	
		
		return new_state;
	}
	private int initialStateLogic(String expression)
	{
		//operator
		if( Pattern.compile("[+-/*^]").matcher(expression).matches() )
		{
			return 1;
		}//function
		else if ( Pattern.compile("cos|sin|tan|cot|exp").matcher(expression).matches() )
		{
			return 2;
		}
		return -1;
	}
	private int operatorLogic(String expression)
	{
		//variable
		if( Pattern.compile("[a-zA-Z]").matcher(expression).matches() )
		{
			return 3;
		}//constant
		else if( Pattern.compile("[0-9]").matcher(expression).matches() )
		{
			return 4;
		}//another operator, could be likely a bracket as a left operand
		else if( Pattern.compile("[+-/*^]").matcher(expression).matches() )
		{
			return 5;
		}
		return -1;
	}
}
