package testCode;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

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
	//Test que comprueba que el placeQueens y el generateQueens funcione.
	void testGenerateQueens() {
		GusanilloFill tester = new GusanilloFill(4, new MockRNG(new int[][] {{1,0,0,0}, {0,0,0,0,0,0,0,0}, {2,0,0,0}}));
		Square[][] result = tester.callGenerateQueens(blankMatrix4);
		int[][] correct = {{2,queen,3,2}, {3,3,3,queen}, {queen,3,3,3}, {2,3,queen,2}};
		//Hacemos que la matriz de 4 se solucione con la reina en 0,1. 
		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				
				assertEquals(result[i][j].getAvailable(), correct[i][j]);
			}
		}
		
		//Hacemos que falle en las reinas (la pone en 0,0) y hace backtracking hasta solucion en 0,1
		result = tester.callGenerateQueens(blankMatrix4);
		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				assertEquals(result[i][j].getAvailable(), correct[i][j]);
			}
		}
		
		//Hacemos que la matriz se solucione con la reina en 0,2
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
	//No sirve de nada el MOCKRNG solo marcamos donde están las reinas y disableamos las otras casillas.
	void testMarkAffected() {
		GusanilloFill tester = new GusanilloFill(4, new MockRNG(new int[][] {{1,0,0,0}, {0,0,0,0,0,0,0,0}, {2,0,0,0}}));
		Square[][] matrix = new Square[4][4];
		for (int i = 0; i < 4; i++) {
		    for (int j = 0; j < 4; j++) {
		        matrix[i][j] = new SquareDefault();
		    }
		}
		Square[][] copy = matrix;
		//no se puede disablear en -1
		tester.callMarkAffectedPositions(matrix, -1, -1);
		assertEquals(matrix, copy);
		
		//disableamos desde 0,0 para comprobar que se quite fila columna y diagonales de la reina
		int[][] correct = {{queen,1,1,1}, {1,1,0,0}, {1,0,0,0}, {1,0,0,0}};
		tester.callMarkAffectedPositions(matrix, 0, 0);
		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				assertEquals(correct[i][j], matrix[i][j].getAvailable());
			}
		}
		
		//disableamos desde 1,3 para comprobar que se quite fila columna y diagonales de la reina y se añada su valor a las otras
		int[][] correct2 = {{queen,1,2,2}, {2,2,1,queen}, {1,0,1,1}, {1,0,0,1}};
		tester.callMarkAffectedPositions(matrix, 1, 3);
		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				assertEquals(correct2[i][j], matrix[i][j].getAvailable());
			}
		}
		
		//disableamos desde 2,1
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
	//Test para quitar las reinas y sus casillas disableadas
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
		
		//Quitamos la reina que pusimos antes en 2,1. Para que se convierta en el correct.
		int[][] correct = {{queen,1,2,2}, {2,2,1,queen}, {1,0,1,1}, {1,0,0,1}};
		tester.callUnmarkAffectedPositions(matrix, 2, 1);
		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 4; j++)	
			{
				assertEquals(correct[i][j], matrix[i][j].getAvailable());
			}
		}
		
		//Quitamos la reina que pusimos antes en 1,3. Para que se convierta en el correct2.
		int[][] correct2 = {{queen,1,1,1}, {1,1,0,0}, {1,0,0,0}, {1,0,0,0}};
		tester.callUnmarkAffectedPositions(matrix, 1, 3);
		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				assertEquals(correct2[i][j], matrix[i][j].getAvailable());
			}
		}
		
		//Quitamos la reina que pusimos antes en 0,0. Para que vuelva a estar todo en 0.0
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
	
	@Test
	void testCreateSections() {
		GusanilloFill tester = new GusanilloFill(4, new MockRNG(new int[][] {{3,2,0,1,4,1,1,2,2,4,0,0,0,0,2,2,0}}));
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
		
		tester = new GusanilloFill(4, new MockRNG(new int[][] {{2,2,0,4,1,1,2,2,2,0,0,2,2,0}}));
		blankMatrix4 = new Square[4][4];
		for (int i = 0; i < 4; i++) {
		    for (int j = 0; j < 4; j++) {
		        blankMatrix4[i][j] = new SquareDefault();
		    }
		}
		Square[][] correct2 = blankMatrix4;
		
		// test matriz 4x4, con reinas en 0,1 (red); 1,3(green); 2,0 (yellow) y 3,2 (blue) con un hueco vacio en 3,3
		
		correct2[0][0].setColor(Colors.BACKGROUND_RED);
		correct2[0][1].setColor(Colors.BACKGROUND_RED);
		correct2[0][2].setColor("");
		correct2[0][3].setColor("");
		correct2[1][0].setColor(Colors.BACKGROUND_RED);
		correct2[1][1].setColor("");
		correct2[1][2].setColor(Colors.BACKGROUND_BLUE);
		correct2[1][3].setColor(Colors.BACKGROUND_GREEN);
		correct2[2][0].setColor(Colors.BACKGROUND_YELLOW);
		correct2[2][1].setColor(Colors.BACKGROUND_GREEN);
		correct2[2][2].setColor(Colors.BACKGROUND_BLUE);
		correct2[2][3].setColor(Colors.BACKGROUND_GREEN);
		correct2[3][0].setColor(Colors.BACKGROUND_YELLOW);
		correct2[3][1].setColor(Colors.BACKGROUND_YELLOW);
		correct2[3][2].setColor(Colors.BACKGROUND_BLUE);
		correct2[3][3].setColor("");

		blankMatrix4[0][1].setAvailable(1000);		
		blankMatrix4[0][1].setColor(Colors.BACKGROUND_RED);
		blankMatrix4[1][3].setAvailable(1000);
		blankMatrix4[1][3].setColor(Colors.BACKGROUND_GREEN);
		blankMatrix4[2][0].setAvailable(1000);
		blankMatrix4[2][0].setColor(Colors.BACKGROUND_YELLOW);
		blankMatrix4[3][2].setAvailable(1000);
		blankMatrix4[3][2].setColor(Colors.BACKGROUND_BLUE);
		
		blankMatrix4 = tester.callCreateSections(blankMatrix4);
		assertEquals(correct2, blankMatrix4);
		
	}
	
	@Test
	void testFillBlanksAndReset() {
		GusanilloFill tester = new GusanilloFill(3, new MockRNG(new int[][] {{1,0,2,0}, {0,2,0,0}}));		


		// ya que la funcion debe borrar el available de cada casilla, le ponemos una plantilla de valores para que los pueda borrar		
		Square[][] numberBase = new Square[3][3];
		for (int i = 0; i < 3; i++) {
		    for (int j = 0; j < 3; j++) {
		        numberBase[i][j] = new SquareDefault();
		    }
		}
		
		numberBase[0][0].setAvailable(0);
		numberBase[0][1].setAvailable(0);
		numberBase[0][2].setAvailable(1);
		numberBase[1][0].setAvailable(0);
		numberBase[1][1].setAvailable(1);
		numberBase[1][2].setAvailable(1);
		numberBase[2][0].setAvailable(1);
		numberBase[2][1].setAvailable(1);
		numberBase[2][2].setAvailable(queen);
		
		
		//Caso1: esta todo vacio con una sola reina roja en 2,2
		//resultado: esto fuerza a que la primera casilla no tengo vecinos
		// con color y tenga q escoger un color aleatoria de 
		// las reinas existentes (rojo en este caso). Como el resto de casillas tendran solo vecinos rojos, acabara todo rojo
		
		//setup matriz esperada
		Square[][] correct1 = new Square[3][3];
		for (int i = 0; i < 3; i++) {
		    for (int j = 0; j < 3; j++) {
		        correct1[i][j] = new SquareDefault();
		        correct1[i][j].setColor(Colors.BACKGROUND_RED);
		    }
		}
		
		//setup matriz inicial caso 1
		Square[][] case1 = new Square[3][3];
		for (int i = 0; i < 3; i++) {
		    for (int j = 0; j < 3; j++) {
		        case1[i][j] = new SquareDefault();
		        case1[i][j].setAvailable(numberBase[i][j].getAvailable());
		    }
		}
		
		
		//seup variable reinas del state para que pueda escoger una (no hace falta en el resto de casos pq tienen almenos un vecino con color)
		ArrayList<ArrayList<Integer>> queens = new ArrayList<>();
		queens.add(new ArrayList<>(Arrays.asList(-1, 0)));
		case1[3][3].setColor(Colors.BACKGROUND_RED);
		
		case1 = tester.callFillBlanksAndReset(case1);
		assertEquals(correct1, case1);
		
		
		// Caso2: casillas vacias: 0,0 con 2 vecinos; 0,2 con 2 vecinos; 1,1 con 4 vecinos; 0,2 con 2 vecinos
		// resultado: escoge cada una un color aleatorio (fijo para este test) de los vecionos (probar caso 2 vecinos y 4)	

		//setup matriz esperada
		Square[][] correct2 = new Square[3][3];
		for (int i = 0; i < 3; i++) {
		    for (int j = 0; j < 3; j++) {
		        correct2[i][j] = new SquareDefault();
		    }
		}
		
		correct2[0][0].setColor(Colors.BACKGROUND_GREEN);
		correct2[0][1].setColor(Colors.BACKGROUND_RED);
		correct2[0][2].setColor(Colors.BACKGROUND_BLUE);
		correct2[1][0].setColor(Colors.BACKGROUND_GREEN);
		correct2[1][1].setColor(Colors.BACKGROUND_YELLOW);
		correct2[1][2].setColor(Colors.BACKGROUND_BLUE);
		correct2[2][0].setColor(Colors.BACKGROUND_GREEN);
		correct2[2][1].setColor(Colors.BACKGROUND_YELLOW);
		correct2[2][2].setColor(Colors.BACKGROUND_RED);
		
		
		//setup matriz inicial caso 2
		Square[][] case2 = new Square[3][3];
		for (int i = 0; i < 3; i++) {
		    for (int j = 0; j < 3; j++) {
		        case2[i][j] = new SquareDefault();
		        case2[i][j].setAvailable(numberBase[i][j].getAvailable());
		    }
		}
		
		case2[0][1].setColor(Colors.BACKGROUND_RED);
		case2[1][0].setColor(Colors.BACKGROUND_GREEN);
		case2[1][2].setColor(Colors.BACKGROUND_BLUE);
		case2[2][1].setColor(Colors.BACKGROUND_YELLOW);
		case2[2][2].setColor(Colors.BACKGROUND_RED);
		
		case2 = tester.callFillBlanksAndReset(case2);
		assertEquals(correct2, case2);
		
		
		
		// Caso3: casillas vacias: 0,0 con 2 vecinos; 0,2 con 1 vecino; 1,1 con 3 vecinos; 1,2 con 1 vecino; 0,2 con 2 vecinos
		// resultado: escoge cada una un color aleatorio (fijo para este test) de los vecionos (probar caso 3)	
		
		//setup matriz esperada
		Square[][] correct3 = new Square[3][3];
		for (int i = 0; i < 3; i++) {
		    for (int j = 0; j < 3; j++) {
		        correct3[i][j] = new SquareDefault();
		    }
		}
		
		correct2[0][0].setColor(Colors.BACKGROUND_RED);
		correct2[0][1].setColor(Colors.BACKGROUND_RED);
		correct2[0][2].setColor(Colors.BACKGROUND_RED);
		correct2[1][0].setColor(Colors.BACKGROUND_GREEN);
		correct2[1][1].setColor(Colors.BACKGROUND_GREEN);
		correct2[1][2].setColor(Colors.BACKGROUND_RED);
		correct2[2][0].setColor(Colors.BACKGROUND_GREEN);
		correct2[2][1].setColor(Colors.BACKGROUND_YELLOW);
		correct2[2][2].setColor(Colors.BACKGROUND_RED);
		
		
		//setup matriz inicial caso 3
		Square[][] case3 = new Square[3][3];
		for (int i = 0; i < 3; i++) {
		    for (int j = 0; j < 3; j++) {
		        case3[i][j] = new SquareDefault();
		        case3[i][j].setAvailable(numberBase[i][j].getAvailable());
		    }
		}
		
		case2[0][1].setColor(Colors.BACKGROUND_RED);
		case2[1][0].setColor(Colors.BACKGROUND_GREEN);
		case2[2][1].setColor(Colors.BACKGROUND_YELLOW);
		case2[2][2].setColor(Colors.BACKGROUND_RED);
		
		case3 = tester.callFillBlanksAndReset(case3);
		assertEquals(correct3, case3);
		
		
	}
}
