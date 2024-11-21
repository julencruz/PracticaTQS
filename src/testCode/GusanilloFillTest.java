package testCode;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import developedCode.GusanilloFill;
import developedCode.Square;
import testClasses.MockObjects.MockRNG;

class GusanilloFillTest {
	Square[][] blankMatrix3;
	Square[][] blankMatrix4;

	@BeforeEach
	void setUp() throws Exception {
		blankMatrix3 =  new Square[3][3];
		blankMatrix4 = new Square[4][4];
	}

	@Test
	void testGenerateQueens() {
		GusanilloFill tester = new GusanilloFill(4, new MockRNG(new int[][] {{1,0,0,0}, {0,0,0,0,0,0,0,0}, {2,0,0,0}}));
		Square[][] result = tester.callGenerateQueens(blankMatrix4);
		boolean[][] correct = {{false,true,false,false}, {false,false,false,true}, {true,false,false,false}, {false,false,true,false}};
		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				assertEquals(result[i][j].isDisabled(), correct[i][j]);
			}
		}
		
		result = tester.callGenerateQueens(blankMatrix4);
		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				assertEquals(result[i][j].isDisabled(), correct[i][j]);
			}
		}
		
		result = tester.callGenerateQueens(blankMatrix4);
		boolean[][] correct2 = {{false,false,true,false}, {true,false,false,false}, {false,false,false,true}, {false,true,false,false}};
		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				assertEquals(result[i][j].isDisabled(), correct2[i][j]);
			}
		}
	}
	
	@Test
	void testAssignColorToQueens() {
		fail("Not yet implemented");
	}
	
	@Test
	void testCreateSections() {
		fail("Not yet implemented");
	}
	

}
