package testCode;
import developedCode.Section;
import developedCode.Square;
import developedCode.SquareDefault;



import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import developedCode.Colors;


class SectionTest {

	//basic tests
	@Test
	void testBasicSection() {
		
		Section se1 = new Section();
		
		assertFalse(se1.isDisabled());
		assertTrue(se1.getColor() == "");
		
		se1.disable();
		se1.setColor(Colors.RED);
		
		assertTrue(se1.isDisabled());
		assertTrue(se1.getColor() == Colors.RED);
		
		se1.enable();
		assertFalse(se1.isDisabled());
	}

	
	@Test
	void testIsSquareInSection()
	{
		Section se2 = new Section(Colors.BLUE);
		Square sq1 = new SquareDefault(Colors.BLUE);
		Square sq2 = new SquareDefault(Colors.RED);
		
		assertTrue(se2.isSquareInSection(sq1));
		assertFalse(se2.isSquareInSection(sq2));
	}

}
