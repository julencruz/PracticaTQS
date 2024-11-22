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
	Square[][] blankMatrix9;
	Square[][] blankMatrix8;
	
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
		
		blankMatrix9 = new Square[9][9];
		for (int i = 0; i < 9; i++) {
		    for (int j = 0; j < 9; j++) {
		        blankMatrix9[i][j] = new SquareDefault();
		    }
		}
		
		blankMatrix8 = new Square[8][8];
		for (int i = 0; i < 8; i++) {
		    for (int j = 0; j < 8; j++) {
		        blankMatrix8[i][j] = new SquareDefault();
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
	
	
	@Test
	void testAssignColorToQueens() {
		
		
		// probar con 4 colores, PE
		GusanilloFill tester = new GusanilloFill(4, new MockRNG(new int[][] {{0,0,0,0}}));
		blankMatrix4[0][1].setAvailable(1000);
		blankMatrix4[1][0].setAvailable(1000);
		blankMatrix4[2][3].setAvailable(1000);
		blankMatrix4[3][1].setAvailable(1000);
		
		Square[][] correct = blankMatrix4;
		correct[0][1].setColor(Colors.BACKGROUND_RED);
		correct[1][0].setColor(Colors.BACKGROUND_GREEN);
		correct[2][3].setColor(Colors.BACKGROUND_BLUE);
		correct[3][1].setColor(Colors.BACKGROUND_YELLOW);
		
		blankMatrix4 = tester.callAssignColorToQueens(blankMatrix4);
		assertEquals(correct, blankMatrix4);
		
		// probar 8 colores, maximo posible
		tester = new GusanilloFill(8, new MockRNG(new int[][] {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}}));

		blankMatrix8[0][1].setAvailable(1000);
		blankMatrix8[1][0].setAvailable(1000);
		blankMatrix8[2][3].setAvailable(1000);
		blankMatrix8[3][1].setAvailable(1000);
		blankMatrix8[1][1].setAvailable(1000);
		blankMatrix8[1][2].setAvailable(1000);
		blankMatrix8[2][4].setAvailable(1000);
		blankMatrix8[3][2].setAvailable(1000);
		
		
		Square[][] correct3 = blankMatrix8;

		correct3[0][1].setColor(Colors.BACKGROUND_RED);
		correct3[1][0].setColor(Colors.BACKGROUND_GREEN);
		correct3[2][3].setColor(Colors.BACKGROUND_BLUE);
		correct3[3][1].setColor(Colors.BACKGROUND_YELLOW);
		correct3[1][1].setColor(Colors.BACKGROUND_CYAN);
		correct3[1][2].setColor(Colors.BACKGROUND_MAGENTA);
		correct3[2][4].setColor(Colors.BACKGROUND_WHITE);
		correct3[3][2].setColor(Colors.BACKGROUND_BLACK);
		
		blankMatrix8 = tester.callAssignColorToQueens(blankMatrix8);
		assertEquals(correct3, blankMatrix8);
		
	}
	
//	@Test
	void testCreateSections() {
		GusanilloFill tester = new GusanilloFill(4, new MockRNG(new int[][] {{3,2,1,1,4,1,2,3,2,4,0,0,0,1,2,2,1,4}}));
		Square[][] correct = blankMatrix4;
		
		// test matriz 4x4, con reinas en 0,1 (red); 1,3(green); 2,0 (yellow) y 3,2 (blue) con un hueco vacio en 3,3
		
		correct[0][0].setColor(Colors.BACKGROUND_RED);
		correct[0][1].setColor(Colors.BACKGROUND_RED);
		correct[0][2].setColor(Colors.BACKGROUND_BLUE);
		correct[0][3].setColor(Colors.BACKGROUND_BLUE);
		correct[1][0].setColor(Colors.BACKGROUND_RED);
		correct[1][1].setColor(Colors.BACKGROUND_RED);
		correct[1][2].setColor(Colors.BACKGROUND_BLUE);
		correct[1][3].setColor(Colors.BACKGROUND_GREEN);
		correct[2][0].setColor(Colors.BACKGROUND_YELLOW);
		correct[2][1].setColor(Colors.BACKGROUND_GREEN);
		correct[2][2].setColor(Colors.BACKGROUND_BLUE);
		correct[2][3].setColor(Colors.BACKGROUND_GREEN);
		correct[3][0].setColor(Colors.BACKGROUND_YELLOW);
		correct[3][1].setColor(Colors.BACKGROUND_YELLOW);
		correct[3][2].setColor(Colors.BACKGROUND_BLUE);
		correct[3][3].setColor("");

		blankMatrix4[0][1].setAvailable(1000);		
		blankMatrix4[0][1].setColor(Colors.BACKGROUND_RED);
		blankMatrix4[1][3].setAvailable(1000);
		blankMatrix4[1][3].setColor(Colors.BACKGROUND_GREEN);
		blankMatrix4[2][0].setAvailable(1000);
		blankMatrix4[2][0].setColor(Colors.BACKGROUND_YELLOW);
		blankMatrix4[3][2].setAvailable(1000);
		blankMatrix4[3][2].setColor(Colors.BACKGROUND_BLUE);

		blankMatrix4 = tester.callCreateSections(blankMatrix4);
		assertEquals(correct, blankMatrix4);

}
