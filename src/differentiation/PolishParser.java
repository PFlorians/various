package differentiation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class PolishParser {
	private int decision_matrix[][];
	
	public void parse(String expression)
	{
		//String[] partial_expressions = expression.split("(?<![^\\s\\(\\)])([^\\s\\(\\)]+)");
		String partial_expressions[] = expression.split("[\\s\\(\\)]");
		List<String> tmp_buffer = Arrays.asList(partial_expressions);
		List<String> l = tmp_buffer.stream().filter(Pattern.compile("[^\\s\\(\\)]+").asPredicate()).collect(Collectors.toList());
		
		long literals = Pattern.compile("(?<!\\S)([a-zA-z]{1})").matcher(expression).results().count();
		long functions = Pattern.compile("(?<=\\S)([a-zA-z]{1})").matcher(expression).results().count();
		long signs = Pattern.compile("[+-/*]{1}").matcher(expression).results().count();
		long constants = Pattern.compile("\\d{1}").matcher(expression).results().count();
		
		calculateDecisionMatrix(partial_expressions);
	}
	private String convertExpression(String expressions[])
	{
		int i, j;
		for( i = 0; i < expressions.length ; i++ )
		{
			
		}
		return "";
	}
	private void calculateDecisionMatrix(String expressions[])
	{
		
	}
}
