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

//	@Test
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
		
	}
	
//	@Test
	void testPlaceQueenInSection() {
		fail("Not yet implemented");
	}
	
//	@Test
	void testRemoveQueenInSection() {
		fail("Not yet implemented");
	}

}
