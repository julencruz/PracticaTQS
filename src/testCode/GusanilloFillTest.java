package testCode;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import developedCode.Colors;
import developedCode.GusanilloFill;
import developedCode.Square;
import developedCode.SquareDefault;
import testClasses.MockObjects.MockRNG;

class GusanilloFillTest {
	Square[][] blankMatrix3;
	Square[][] blankMatrix4;
	
	int available = 0;
	int unavailable = 1;
	int queen = 1000;

	@BeforeEach
	void setUp() throws Exception {
		blankMatrix3 =  new Square[3][3];
		blankMatrix4 = new Square[4][4];
		for (int i = 0; i < 4; i++) {
		    for (int j = 0; j < 4; j++) {
		        blankMatrix4[i][j] = new SquareDefault();
		    }
		}
	}

	@Test
	void testGenerateQueens() {
		GusanilloFill tester = new GusanilloFill(4, new MockRNG(new int[][] {{1,0,0,0}, {0,0,0,0,0,0,0,0}, {2,0,0,0}}));
		Square[][] result = tester.callGenerateQueens(blankMatrix4);
		int[][] correct = {{2,queen,3,2}, {3,3,3,queen}, {queen,3,3,3}, {2,3,queen,2}};
		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				
				assertEquals(result[i][j].getAvailable(), correct[i][j]);
			}
		}
		
		result = tester.callGenerateQueens(blankMatrix4);
		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				assertEquals(result[i][j].getAvailable(), correct[i][j]);
			}
		}
		
		result = tester.callGenerateQueens(blankMatrix4);
		int[][] correct2 = {{2,3,queen,2}, {queen,3,3,3}, {3,3,3,queen}, {2,queen,3,2}};
		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				assertEquals(result[i][j].getAvailable(), correct2[i][j]);
			}
		}
	}
	
	@Test
	void testMarkAffected() {
		GusanilloFill tester = new GusanilloFill(4, new MockRNG(new int[][] {{1,0,0,0}, {0,0,0,0,0,0,0,0}, {2,0,0,0}}));
		Square[][] matrix = new Square[4][4];
		for (int i = 0; i < 4; i++) {
		    for (int j = 0; j < 4; j++) {
		        matrix[i][j] = new SquareDefault();
		    }
		}
		Square[][] copy = matrix;
		tester.callMarkAffectedPositions(matrix, -1, -1);
		assertEquals(matrix, copy);
		
		int[][] correct = {{queen,1,1,1}, {1,1,0,0}, {1,0,0,0}, {1,0,0,0}};
		tester.callMarkAffectedPositions(matrix, 0, 0);
		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				assertEquals(correct[i][j], matrix[i][j].getAvailable());
			}
		}
		
		int[][] correct2 = {{queen,1,2,2}, {2,2,1,queen}, {1,0,1,1}, {1,0,0,1}};
		tester.callMarkAffectedPositions(matrix, 1, 3);
		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				assertEquals(correct2[i][j], matrix[i][j].getAvailable());
			}
		}
		
		int[][] correct3 = {{queen,2,2,2}, {3,3,2,queen}, {2,queen,2,2}, {2,1,1,1}};
		tester.callMarkAffectedPositions(matrix, 2, 1);
		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 4; j++)	
			{
				assertEquals(correct3[i][j], matrix[i][j].getAvailable());
			}
		}	
	}
	
	@Test
	void testUnmarkAffected() {
		GusanilloFill tester = new GusanilloFill(4, new MockRNG(new int[][] {{1,0,0,0}, {0,0,0,0,0,0,0,0}, {2,0,0,0}}));
		Square[][] matrix = new Square[4][4];
		for (int i = 0; i < 4; i++) {
		    for (int j = 0; j < 4; j++) {
		        matrix[i][j] = new SquareDefault();
		    }
		}
		tester.callMarkAffectedPositions(matrix, 0, 0);
		tester.callMarkAffectedPositions(matrix, 1, 3);
		tester.callMarkAffectedPositions(matrix, 2, 1);
		
		int[][] correct = {{queen,1,2,2}, {2,2,1,queen}, {1,0,1,1}, {1,0,0,1}};
		tester.callUnmarkAffectedPositions(matrix, 2, 1);
		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 4; j++)	
			{
				assertEquals(correct[i][j], matrix[i][j].getAvailable());
			}
		}
		
		int[][] correct2 = {{queen,1,1,1}, {1,1,0,0}, {1,0,0,0}, {1,0,0,0}};
		tester.callUnmarkAffectedPositions(matrix, 1, 3);
		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				assertEquals(correct2[i][j], matrix[i][j].getAvailable());
			}
		}
		
		tester.callUnmarkAffectedPositions(matrix, 0, 0);
		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				assertEquals(available, matrix[i][j].getAvailable());
			}
		}
	}
	
	
//	@Test
	void testAssignColorToQueens() {
		GusanilloFill tester = new GusanilloFill(4, new MockRNG(new int[][] {{1,0,0,0}, {0,0,0,0}}));
		tester.callGenerateQueens(blankMatrix4);
		Square[][] correct = blankMatrix4;
		correct[0][1].setColor(Colors.P_RED);
		correct[1][0].setColor(Colors.P_GREEN);
		correct[2][3].setColor(Colors.P_BLUE);
		correct[3][1].setColor(Colors.P_YELLOW);
		tester.callAssignColorToQueens(blankMatrix4);
		assertEquals(correct, blankMatrix4);
		
	}
	
//	@Test
	void testCreateSections() {
		fail("Not yet implemented");
	}
	

}
