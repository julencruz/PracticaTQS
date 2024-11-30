package testCode;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import developedCode.Board;
import developedCode.Colors;
import developedCode.GenerationStrategy;
import developedCode.GusanilloFill;
import developedCode.Section;
import developedCode.Square;
import developedCode.SquareDefault;

class BoardTest {
	GenerationStrategy mockGenStat;
	ArrayList<Section> sections = new ArrayList<>();
	Square[][] mockMatrix;
	Board board;
	@BeforeEach
	void setUp() throws Exception {
		mockGenStat = mock(GusanilloFill.class);
		mockMatrix = new Square[4][4];
		for (int i = 0; i < 4 ; i++) {
			for (int j = 0; j < 4; j++) {
				mockMatrix[i][j] = new SquareDefault();
			}
		}
	}

	@Test
	void testGenerateBoard() {
		
		mockMatrix[0][0].setColor(Colors.BACKGROUND_YELLOW);
		mockMatrix[0][1].setColor(Colors.BACKGROUND_YELLOW);
		mockMatrix[0][2].setColor(Colors.BACKGROUND_BLUE);
		mockMatrix[0][3].setColor(Colors.BACKGROUND_BLUE);
		mockMatrix[1][0].setColor(Colors.BACKGROUND_MAGENTA);
		mockMatrix[1][1].setColor(Colors.BACKGROUND_BLUE);
		mockMatrix[1][2].setColor(Colors.BACKGROUND_BLUE);
		mockMatrix[1][3].setColor(Colors.BACKGROUND_BLUE);
		mockMatrix[2][0].setColor(Colors.BACKGROUND_MAGENTA);
		mockMatrix[2][1].setColor(Colors.BACKGROUND_YELLOW);
		mockMatrix[2][2].setColor(Colors.BACKGROUND_RED);
		mockMatrix[2][3].setColor(Colors.BACKGROUND_RED);
		mockMatrix[3][0].setColor(Colors.BACKGROUND_YELLOW);
		mockMatrix[3][1].setColor(Colors.BACKGROUND_YELLOW);
		mockMatrix[3][2].setColor(Colors.BACKGROUND_RED);
		mockMatrix[3][3].setColor(Colors.BACKGROUND_RED);
		
		
		when(mockGenStat.generate()).thenReturn(mockMatrix);
		
		ArrayList<ArrayList<Integer>> queens = new ArrayList<>();
		queens.add(new ArrayList<>(Arrays.asList(0, 1)));
		queens.add(new ArrayList<>(Arrays.asList(1, 3)));
		queens.add(new ArrayList<>(Arrays.asList(2, 0)));
		queens.add(new ArrayList<>(Arrays.asList(3, 2)));

		when(mockGenStat.getQueensPosition()).thenReturn(queens);
		
		ArrayList<Section> correctSections = new ArrayList<Section>();
		correctSections.add(new Section(Colors.BACKGROUND_YELLOW));
		correctSections.add(new Section(Colors.BACKGROUND_BLUE));
		correctSections.add(new Section(Colors.BACKGROUND_MAGENTA));
		correctSections.add(new Section(Colors.BACKGROUND_RED));		
		
		board = new Board(mockGenStat, 4);
		board.generateBoard();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				assertEquals(mockMatrix[i][j], board.getMatrix()[i][j]);
			}
		}
		
		for (int i = 0; i < 4; i++)
		{
			assertEquals(correctSections.get(i).getColor(), board.getSections().get(i).getColor());
			assertEquals(correctSections.get(i).isDisabled(), board.getSections().get(i).isDisabled());
		}
	}
	
	
	@Test
	void testCreateSections() {
		mockMatrix[0][0].setColor(Colors.BACKGROUND_YELLOW);
		mockMatrix[0][1].setColor(Colors.BACKGROUND_YELLOW);
		mockMatrix[0][2].setColor(Colors.BACKGROUND_BLUE);
		mockMatrix[0][3].setColor(Colors.BACKGROUND_BLUE);
		mockMatrix[1][0].setColor(Colors.BACKGROUND_MAGENTA);
		mockMatrix[1][1].setColor(Colors.BACKGROUND_BLUE);
		mockMatrix[1][2].setColor(Colors.BACKGROUND_BLUE);
		mockMatrix[1][3].setColor(Colors.BACKGROUND_BLUE);
		mockMatrix[2][0].setColor(Colors.BACKGROUND_MAGENTA);
		mockMatrix[2][1].setColor(Colors.BACKGROUND_YELLOW);
		mockMatrix[2][2].setColor(Colors.BACKGROUND_RED);
		mockMatrix[2][3].setColor(Colors.BACKGROUND_RED);
		mockMatrix[3][0].setColor(Colors.BACKGROUND_YELLOW);
		mockMatrix[3][1].setColor(Colors.BACKGROUND_YELLOW);
		mockMatrix[3][2].setColor(Colors.BACKGROUND_RED);
		mockMatrix[3][3].setColor(Colors.BACKGROUND_RED);
		
		ArrayList<Section> correctSections = new ArrayList<Section>();
		correctSections.add(new Section(Colors.BACKGROUND_YELLOW));
		correctSections.add(new Section(Colors.BACKGROUND_BLUE));
		correctSections.add(new Section(Colors.BACKGROUND_MAGENTA));
		correctSections.add(new Section(Colors.BACKGROUND_RED));
		
		ArrayList<ArrayList<Integer>> queens = new ArrayList<>();
		queens.add(new ArrayList<>(Arrays.asList(0, 1)));
		queens.add(new ArrayList<>(Arrays.asList(1, 3)));
		queens.add(new ArrayList<>(Arrays.asList(2, 0)));
		queens.add(new ArrayList<>(Arrays.asList(3, 2)));
		
		board = new Board(mockGenStat,4);
		board.setMatrix(mockMatrix);
		
		board.callCreateSections(queens);
		
		for (int i = 0; i < 4; i++)
		{
			assertEquals(correctSections.get(i).getColor(), board.getSections().get(i).getColor());
			assertEquals(correctSections.get(i).isDisabled(), board.getSections().get(i).isDisabled());
		}
		
//		------------------------ Simple loop testing ----------------------------
		mockMatrix[0][0].setColor(Colors.BACKGROUND_RED);
		mockMatrix[0][1].setColor(Colors.BACKGROUND_GREEN);
		mockMatrix[0][2].setColor(Colors.BACKGROUND_BLUE);
		mockMatrix[0][3].setColor(Colors.BACKGROUND_YELLOW);
		mockMatrix[1][0].setColor(Colors.BACKGROUND_CYAN);
		mockMatrix[1][1].setColor(Colors.BACKGROUND_MAGENTA);
		mockMatrix[1][2].setColor(Colors.BACKGROUND_WHITE);
		mockMatrix[1][3].setColor(Colors.BACKGROUND_BLACK);
		board.setMatrix(mockMatrix);

		ArrayList<ArrayList<Integer>> noQueens = new ArrayList<>();
		ArrayList<ArrayList<Integer>> oneQueen= new ArrayList<>();
		oneQueen.add(new ArrayList<>(Arrays.asList(0, 0)));
		ArrayList<ArrayList<Integer>> twoQueens= new ArrayList<>();
		twoQueens.add(new ArrayList<>(Arrays.asList(0,0)));
		twoQueens.add(new ArrayList<>(Arrays.asList(0,1)));
		ArrayList<ArrayList<Integer>> fiveQueens= new ArrayList<>();
		fiveQueens.add(new ArrayList<>(Arrays.asList(0, 0)));
		fiveQueens.add(new ArrayList<>(Arrays.asList(0, 1)));
		fiveQueens.add(new ArrayList<>(Arrays.asList(0, 2)));
		fiveQueens.add(new ArrayList<>(Arrays.asList(0, 3)));
		fiveQueens.add(new ArrayList<>(Arrays.asList(1, 0)));
		ArrayList<ArrayList<Integer>> sevenQueens= new ArrayList<>();
		sevenQueens.add(new ArrayList<>(Arrays.asList(0,0)));
		sevenQueens.add(new ArrayList<>(Arrays.asList(0,1)));
		sevenQueens.add(new ArrayList<>(Arrays.asList(0,2)));
		sevenQueens.add(new ArrayList<>(Arrays.asList(0,3)));
		sevenQueens.add(new ArrayList<>(Arrays.asList(1,0)));
		sevenQueens.add(new ArrayList<>(Arrays.asList(1,1)));
		sevenQueens.add(new ArrayList<>(Arrays.asList(1,2)));
		ArrayList<ArrayList<Integer>> eightQueens= new ArrayList<>();
		eightQueens.add(new ArrayList<>(Arrays.asList(0, 0)));
		eightQueens.add(new ArrayList<>(Arrays.asList(0, 1)));
		eightQueens.add(new ArrayList<>(Arrays.asList(0, 2)));
		eightQueens.add(new ArrayList<>(Arrays.asList(0, 3)));
		eightQueens.add(new ArrayList<>(Arrays.asList(1, 0)));
		eightQueens.add(new ArrayList<>(Arrays.asList(1, 1)));
		eightQueens.add(new ArrayList<>(Arrays.asList(1, 2)));
		eightQueens.add(new ArrayList<>(Arrays.asList(1, 3)));

		ArrayList<Section> eightQueensCorrect= new ArrayList<>();
		eightQueensCorrect.add(new Section(Colors.BACKGROUND_RED));
		eightQueensCorrect.add(new Section(Colors.BACKGROUND_GREEN));
		eightQueensCorrect.add(new Section(Colors.BACKGROUND_BLUE));
		eightQueensCorrect.add(new Section(Colors.BACKGROUND_YELLOW));
		eightQueensCorrect.add(new Section(Colors.BACKGROUND_CYAN));
		eightQueensCorrect.add(new Section(Colors.BACKGROUND_MAGENTA));
		eightQueensCorrect.add(new Section(Colors.BACKGROUND_WHITE));
		eightQueensCorrect.add(new Section(Colors.BACKGROUND_BLACK));
		
		ArrayList<ArrayList<ArrayList<Integer>>> queensArray = new ArrayList<>();
		queensArray.add(noQueens);
		queensArray.add(oneQueen);
		queensArray.add(twoQueens);
		queensArray.add(fiveQueens);
		queensArray.add(sevenQueens);
		queensArray.add(eightQueens);
		
		for (int i = 0; i < 6; i++) {
			board.callCreateSections(queensArray.get(i));
			assertEquals(queensArray.get(i).size(), board.getSections().size());
			for (int j = 0; j < queensArray.get(i).size(); j++) {
				assertEquals(eightQueensCorrect.get(j).getColor(), board.getSections().get(j).getColor());
				assertEquals(eightQueensCorrect.get(j).isDisabled(), board.getSections().get(j).isDisabled());
			}
		}
	}
	
//	@Test
	void testPlaceAndRemoveQueenInSection() {
		Square[][] twoMatrix = new Square[2][2];
		
		for(int i = 0; i< 2; i++)
		{
			for(int j = 0; j< 2; j++)
			{
				twoMatrix[i][j] = new SquareDefault();
			}

		}
		
		mockMatrix[0][0].setColor(Colors.BACKGROUND_RED);
		mockMatrix[0][1].setColor(Colors.BACKGROUND_YELLOW);
		mockMatrix[1][0].setColor(Colors.BACKGROUND_BLUE);
		mockMatrix[1][1].setColor(Colors.BACKGROUND_GREEN);
		
		sections.add(new Section(Colors.BACKGROUND_RED));
		sections.add(new Section(Colors.BACKGROUND_YELLOW));
		sections.add(new Section(Colors.BACKGROUND_BLUE));
		sections.add(new Section(Colors.BACKGROUND_GREEN));
		
		board.setMatrix(twoMatrix);
		board.setSections(sections);
		
		//debido a que esta funcion solo se ejecutara despues de 
		//comprobar que la casilla es valida solo hay que mirar que 
		//deshabilite la seccion correctamente
		
		board.placeQueenInSection(0, 0);
		
		assertTrue(board.getSections().get(0).isDisabled());
		assertFalse(board.getSections().get(1).isDisabled());
		assertFalse(board.getSections().get(2).isDisabled());
		assertFalse(board.getSections().get(3).isDisabled());

		board.placeQueenInSection(0, 1);
		
		assertTrue(board.getSections().get(1).isDisabled());
		assertTrue(board.getSections().get(0).isDisabled());
		assertFalse(board.getSections().get(2).isDisabled());
		assertFalse(board.getSections().get(3).isDisabled());
		
		board.placeQueenInSection(1, 0);
		
		assertTrue(board.getSections().get(2).isDisabled());
		assertTrue(board.getSections().get(0).isDisabled());
		assertTrue(board.getSections().get(1).isDisabled());
		assertFalse(board.getSections().get(3).isDisabled());
		
		
		board.placeQueenInSection(1, 1);
		
		assertTrue(board.getSections().get(3).isDisabled());
		assertTrue(board.getSections().get(0).isDisabled());
		assertTrue(board.getSections().get(1).isDisabled());
		assertTrue(board.getSections().get(2).isDisabled());
		
	}
	
	@Test
	void testIsSquareAvailable() {
		board = new Board(mockGenStat, 4);
		
		mockMatrix[0][0].setColor(Colors.BACKGROUND_RED);
		mockMatrix[0][1].setColor(Colors.BACKGROUND_YELLOW);
		mockMatrix[0][2].setColor(Colors.BACKGROUND_BLUE);
		mockMatrix[0][3].setColor(Colors.BACKGROUND_BLUE);
		mockMatrix[1][0].setColor(Colors.BACKGROUND_YELLOW);
		mockMatrix[1][1].setColor(Colors.BACKGROUND_YELLOW);
		mockMatrix[1][2].setColor(Colors.BACKGROUND_YELLOW);
		mockMatrix[1][3].setColor(Colors.BACKGROUND_MAGENTA);
		mockMatrix[2][0].setColor(Colors.BACKGROUND_RED);
		mockMatrix[2][1].setColor(Colors.BACKGROUND_MAGENTA);
		mockMatrix[2][2].setColor(Colors.BACKGROUND_BLUE);
		mockMatrix[2][3].setColor(Colors.BACKGROUND_MAGENTA);
		mockMatrix[3][0].setColor(Colors.BACKGROUND_RED);
		mockMatrix[3][1].setColor(Colors.BACKGROUND_RED);
		mockMatrix[3][2].setColor(Colors.BACKGROUND_BLUE);
		mockMatrix[3][3].setColor(Colors.BACKGROUND_MAGENTA);
		
		mockMatrix[3][3].disable();
		mockMatrix[0][3].disable();
		mockMatrix[1][3].disable();
		mockMatrix[2][3].disable();
		mockMatrix[2][2].disable();
		mockMatrix[3][0].disable();
		mockMatrix[3][1].disable();
		mockMatrix[3][2].disable();

		sections.add(new Section(Colors.BACKGROUND_RED));
		sections.add(new Section(Colors.BACKGROUND_YELLOW));
		sections.add(new Section(Colors.BACKGROUND_BLUE));
		sections.add(new Section(Colors.BACKGROUND_MAGENTA));

		sections.get(3).disable();
		
		board.setMatrix(mockMatrix);
		board.setSections(sections);
		
		//Path coverage 
		//poner dentro del tablero pero que no se puede
		//Path 1
		assertFalse(board.isSquareAvailable(2,3));
		
		assertFalse(board.isSquareAvailable(3,2));
		assertFalse(board.isSquareAvailable(2,1));
		
		//poner dentro del tablero y si se puede
		//Path 2
		assertTrue(board.isSquareAvailable(0,0));
		
		//poner fuera del tablero
		//Path 3
		assertFalse(board.isSquareAvailable(-1,-1));
		
		assertFalse(board.isSquareAvailable(-10,-10));
		assertFalse(board.isSquareAvailable(100,100));
		assertFalse(board.isSquareAvailable(4,4));
		
	}

	@Test
	void testGetSquaresSection()
	{
		board = new Board(mockGenStat, 4);
		sections.add(new Section(Colors.BACKGROUND_RED));
		sections.add(new Section(Colors.BACKGROUND_YELLOW));
		sections.add(new Section(Colors.BACKGROUND_BLUE));
		sections.add(new Section(Colors.BACKGROUND_MAGENTA));
		
		board.setSections(sections);
		
		Square sq1 = new SquareDefault(Colors.BACKGROUND_RED);
		Square sq2 = new SquareDefault(Colors.BACKGROUND_YELLOW);
		Square sq3 = new SquareDefault(Colors.BACKGROUND_BLUE);
		Square sq4 = new SquareDefault(Colors.BACKGROUND_MAGENTA);
		Square sq5 = new SquareDefault(Colors.BACKGROUND_BLACK);
		
		assertEquals(sections.get(0),board.callGetSquaresSection(sq1));
		assertEquals(sections.get(1),board.callGetSquaresSection(sq2));
		assertEquals(sections.get(2),board.callGetSquaresSection(sq3));
		assertEquals(sections.get(3),board.callGetSquaresSection(sq4));
		assertEquals(null, board.callGetSquaresSection(sq5));
	}

	@Test
	void testDisableAndEnableSquare()
	{
		board = new Board(mockGenStat, 4);
		board.setMatrix(mockMatrix);
		
		
		//valores que dan error
		board.disableSquare(-10, -10);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				assertEquals(mockMatrix[i][j].isDisabled(), board.getMatrix()[i][j].isDisabled());
			}
		}
		
		board.disableSquare(-1, -1);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				assertEquals(mockMatrix[i][j].isDisabled(), board.getMatrix()[i][j].isDisabled());
			}
		}
		
		board.disableSquare(4, 4);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				assertEquals(mockMatrix[i][j].isDisabled(), board.getMatrix()[i][j].isDisabled());
			}
		}
		
		board.disableSquare(100, 100);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				assertEquals(mockMatrix[i][j].isDisabled(), board.getMatrix()[i][j].isDisabled());
			}
		}
		
		board.disableSquare(-10, 1);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				assertEquals(mockMatrix[i][j].isDisabled(), board.getMatrix()[i][j].isDisabled());
			}
		}
		
		board.disableSquare(-10, 100);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				assertEquals(mockMatrix[i][j].isDisabled(), board.getMatrix()[i][j].isDisabled());
			}
		}
		
		board.disableSquare(1, 100);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				assertEquals(mockMatrix[i][j].isDisabled(), board.getMatrix()[i][j].isDisabled());
			}
		}
		
		board.disableSquare(1, -10);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				assertEquals(mockMatrix[i][j].isDisabled(), board.getMatrix()[i][j].isDisabled());
			}
		}
		
		board.disableSquare(100, -10);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				assertEquals(mockMatrix[i][j].isDisabled(), board.getMatrix()[i][j].isDisabled());
			}
		}
		
		board.disableSquare(100, 1);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				assertEquals(mockMatrix[i][j].isDisabled(), board.getMatrix()[i][j].isDisabled());
			}
		}
		
		//valores que cambian
		
		mockMatrix[0][0].setAvailable(1);
		board.disableSquare(0, 0);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				assertEquals(mockMatrix[i][j].isDisabled(), board.getMatrix()[i][j].isDisabled());
			}
		}
		
		mockMatrix[1][1].setAvailable(1);
		board.disableSquare(1, 1);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				assertEquals(mockMatrix[i][j].isDisabled(), board.getMatrix()[i][j].isDisabled());
			}
		}
		
		mockMatrix[2][2].setAvailable(1);
		board.disableSquare(2, 2);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				assertEquals(mockMatrix[i][j].isDisabled(), board.getMatrix()[i][j].isDisabled());
			}
		}
		
		mockMatrix[3][3].setAvailable(1);
		board.disableSquare(3, 3);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				assertEquals(mockMatrix[i][j].isDisabled(), board.getMatrix()[i][j].isDisabled());
			}
		}
		
		mockMatrix[1][1].setAvailable(2);
		board.disableSquare(1, 1);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				assertEquals(mockMatrix[i][j].isDisabled(), board.getMatrix()[i][j].isDisabled());
			}
		}
		
		//------------------------------ENABLE-----------------------------------
		
			//valores que dan error
		board.enableSquare(-10, -10);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				assertEquals(mockMatrix[i][j].isDisabled(), board.getMatrix()[i][j].isDisabled());
			}
		}
		
		board.enableSquare(-1, -1);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				assertEquals(mockMatrix[i][j].isDisabled(), board.getMatrix()[i][j].isDisabled());
			}
		}
		
		board.enableSquare(4, 4);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				assertEquals(mockMatrix[i][j].isDisabled(), board.getMatrix()[i][j].isDisabled());
			}
		}
		
		board.enableSquare(100, 100);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				assertEquals(mockMatrix[i][j].isDisabled(), board.getMatrix()[i][j].isDisabled());
			}
		}
		
		board.enableSquare(-10, 1);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				assertEquals(mockMatrix[i][j].isDisabled(), board.getMatrix()[i][j].isDisabled());
			}
		}
		
		board.enableSquare(-10, 100);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				assertEquals(mockMatrix[i][j].isDisabled(), board.getMatrix()[i][j].isDisabled());
			}
		}
		
		board.enableSquare(1, 100);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				assertEquals(mockMatrix[i][j].isDisabled(), board.getMatrix()[i][j].isDisabled());
			}
		}
		
		board.enableSquare(1, -10);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				assertEquals(mockMatrix[i][j].isDisabled(), board.getMatrix()[i][j].isDisabled());
			}
		}
		
		board.enableSquare(100, -10);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				assertEquals(mockMatrix[i][j].isDisabled(), board.getMatrix()[i][j].isDisabled());
			}
		}
		
		board.enableSquare(100, 1);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				assertEquals(mockMatrix[i][j].isDisabled(), board.getMatrix()[i][j].isDisabled());
			}
		}
		
		//valores que cambian
		mockMatrix[1][1].setAvailable(1);
		board.enableSquare(1, 1);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				assertEquals(mockMatrix[i][j].isDisabled(), board.getMatrix()[i][j].isDisabled());
			}
		}
		
		mockMatrix[3][3].setAvailable(0);
		board.enableSquare(3, 3);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				assertEquals(mockMatrix[i][j].isDisabled(), board.getMatrix()[i][j].isDisabled());
			}
		}
		
		mockMatrix[2][2].setAvailable(0);
		board.enableSquare(2, 2);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				assertEquals(mockMatrix[i][j].isDisabled(), board.getMatrix()[i][j].isDisabled());
			}
		}
		
		mockMatrix[1][1].setAvailable(0);
		board.enableSquare(1, 1);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				assertEquals(mockMatrix[i][j].isDisabled(), board.getMatrix()[i][j].isDisabled());
			}
		}
		
		mockMatrix[0][0].setAvailable(0);
		board.enableSquare(0, 0);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				assertEquals(mockMatrix[i][j].isDisabled(), board.getMatrix()[i][j].isDisabled());
			}
		}
		

	}
}
