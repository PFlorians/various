package differentiation.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import differentiation.PolishParser;

class PolishParserTests {
	private PolishParser cut;
	@BeforeEach
	void setUp() throws Exception 
	{
		cut = new PolishParser();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testParse() 
	{
		//given
		//when
		cut.parse(null);
		//then
	}

}
