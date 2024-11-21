package testCode;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

//	@Test
	void testGenerateQueens() {
		GusanilloFill tester = new GusanilloFill(4, new MockRNG(new int[][] {{1,0,0,0}, {0,0,0,0,0,0,0,0}, {2,0,0,0}}));
		Square[][] result = tester.callGenerateQueens(blankMatrix4);
		int[][] correct = {{unavailable,queen,unavailable,unavailable}, {unavailable,unavailable,unavailable,queen}, 
				{queen,unavailable,unavailable,unavailable}, {unavailable,unavailable,queen,unavailable}};
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
		int[][] correct2 = {{unavailable,unavailable,queen,unavailable}, {queen,unavailable,unavailable,unavailable}, 
				{unavailable,unavailable,unavailable,queen}, {unavailable,queen,unavailable,unavailable}};
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
		
		int[][] correct = {{queen,unavailable,unavailable,unavailable}, {unavailable,unavailable,available,available}, 
				{unavailable,available,available,available}, {unavailable,available,available,available}};
		tester.callMarkAffectedPositions(matrix, 0, 0);
		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				assertEquals(matrix[i][j].getAvailable(), correct[i][j]);
			}
		}
		
		int[][] correct2 = {{queen,1,2,2}, {2,2,1,queen}, {1,0,1,1}, {1,0,0,1}};
		tester.callMarkAffectedPositions(matrix, 1, 3);
		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				assertEquals(matrix[i][j].getAvailable(), correct2[i][j]);
			}
		}
		
		int[][] correct3 = {{queen,2,2,2}, {3,3,2,queen}, {2,queen,2,2}, {2,1,1,1}};
		tester.callMarkAffectedPositions(matrix, 2, 1);
		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 4; j++)	
			{
				assertEquals(matrix[i][j].getAvailable(), correct3[i][j]);
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
				assertEquals(matrix[i][j].getAvailable(), correct[i][j]);
			}
		}
		
		int[][] correct2 = {{queen,unavailable,unavailable,unavailable}, {unavailable,unavailable,available,available}, 
				{unavailable,available,available,available}, {unavailable,available,available,available}};
		tester.callUnmarkAffectedPositions(matrix, 1, 3);
		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				assertEquals(matrix[i][j].getAvailable(), correct2[i][j]);
			}
		}
		
		tester.callUnmarkAffectedPositions(matrix, 0, 0);
		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				assertEquals(matrix[i][j].getAvailable(), available);
			}
		}
	}
	
	
//	@Test
	void testAssignColorToQueens() {
		fail("Not yet implemented");
	}
	
//	@Test
	void testCreateSections() {
		fail("Not yet implemented");
	}
	

}
