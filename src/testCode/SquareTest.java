package testCode;
import developedCode.Colors;
import developedCode.Square;
import developedCode.SquareDefault;



import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SquareTest {

	
	//Basic tests, are to test the constructors, getters and setters
	@Test
	void testBasicSquare() {
		
		//default square testing
		Square s = new SquareDefault();
		
		assertFalse(s.isDisabled());
		assertTrue(s.getColor() == "");
		
		s.disable();
		s.setColor(Colors.BACKGROUND_BLUE);
		
		assertTrue(s.isDisabled());
		assertTrue(s.getColor() == Colors.BACKGROUND_BLUE);
		
		Square s2 = new SquareDefault(Colors.BACKGROUND_RED);
		
		assertFalse(s2.isDisabled());
		assertTrue(s2.getColor() == Colors.BACKGROUND_RED);
		
	}

}
