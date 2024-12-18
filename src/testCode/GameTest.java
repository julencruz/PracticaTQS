package testCode;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import developedCode.Board;
import developedCode.Game;
import developedCode.Queen;
import developedCode.QueenDefault;
import developedCode.Visualizer;



class GameTest {
	
	
	Visualizer mockVis;
	Board mockBoard;
	Game tester;
	

	@BeforeEach
	void setUp() throws Exception {
		mockVis = mock(Visualizer.class);
		mockBoard = mock(Board.class);
	}

	@Test
	void testPlay() {
		tester = new Game(mockBoard, mockVis);
		int[][] inputs = {{0,0},{1,1},{-1,5},{1,2},{1,2},{0,0},{2,0},{1,3},{0,1},{3,2}};
		when(mockVis.input()).thenReturn(inputs[0]).thenReturn(inputs[1]).thenReturn(inputs[2]).thenReturn(inputs[3]).thenReturn(inputs[4]).thenReturn(inputs[5]).thenReturn(inputs[6]).thenReturn(inputs[7]).thenReturn(inputs[8]).thenReturn(inputs[9]);
		when(mockVis.playAgain()).thenReturn(false);
		when(mockBoard.getSize()).thenReturn(4);
		Boolean[] isAvailableOut = {true, false, true, true, true, true, true};
		when(mockBoard.isSquareAvailable(anyInt(), anyInt())).thenReturn(isAvailableOut[0]).thenReturn(isAvailableOut[1]).thenReturn(isAvailableOut[2]).thenReturn(isAvailableOut[3]).thenReturn(isAvailableOut[4]).thenReturn(isAvailableOut[5]).thenReturn(isAvailableOut[6]);

		tester.play();
		
		verify(mockBoard).placeQueenInSection(0,0);
		verify(mockBoard, times(61)).disableSquare(anyInt(),anyInt());
		
		verify(mockBoard).placeQueenInSection(1,2);
		verify(mockBoard, times(61)).disableSquare(anyInt(),anyInt());
		
		verify(mockBoard).removeQueenInSection(1,2);
		verify(mockBoard, times(21)).enableSquare(anyInt(),anyInt());
		
		verify(mockBoard).removeQueenInSection(0,0);
		verify(mockBoard, times(21)).enableSquare(anyInt(),anyInt());
		
		verify(mockBoard).placeQueenInSection(2,0);
		verify(mockBoard, times(61)).disableSquare(anyInt(),anyInt());
		
		verify(mockBoard).placeQueenInSection(1,3);
		verify(mockBoard, times(61)).disableSquare(anyInt(),anyInt());
		
		verify(mockBoard).placeQueenInSection(0,1);
		verify(mockBoard, times(61)).disableSquare(anyInt(),anyInt());
		
		verify(mockBoard).placeQueenInSection(3,2);
		verify(mockBoard, times(61)).disableSquare(anyInt(),anyInt());
		
		verify(mockVis).win();
		verify(mockVis).playAgain();
	}
	
	@Test
	void testPlaceOrRemoveQueen() {
		//Coordenadas correctas porque se encarga input de evitar coordenadas incorrectas.
		tester = new Game();
		when(mockBoard.isSquareAvailable(1, 1)).thenReturn(false).thenReturn(true);
		when(mockBoard.isSquareAvailable(0, 0)).thenReturn(true);
		when(mockBoard.isSquareAvailable(3, 3)).thenReturn(true);
		when(mockBoard.getSize()).thenReturn(4);
		
		tester.setBoard(mockBoard);
		ArrayList<Queen> queens = new ArrayList<>();
		
		tester.callPlaceOrRemoveQueen(0, 0);
		verify(mockBoard).placeQueenInSection(0, 0);
		verify(mockBoard, times(9)).disableSquare(anyInt(), anyInt());
		assertTrue(tester.getQueens().size() == 1);
		
		tester.callPlaceOrRemoveQueen(1, 1);
		assertTrue(tester.getQueens().size() == 1);
		
		tester.callPlaceOrRemoveQueen(3,3);
		verify(mockBoard).placeQueenInSection(3, 3);
		verify(mockBoard, times(18)).disableSquare(anyInt(), anyInt());
		assertTrue(tester.getQueens().size() == 2);
		
		tester.callPlaceOrRemoveQueen(0, 0);
		verify(mockBoard).removeQueenInSection(0, 0);
		verify(mockBoard, times(9)).enableSquare(anyInt(), anyInt());
		assertTrue(tester.getQueens().size() == 1);
		
		tester.callPlaceOrRemoveQueen(1, 1);
		verify(mockBoard).placeQueenInSection(1, 1);
		verify(mockBoard, times(30)).disableSquare(anyInt(), anyInt());
		assertTrue(tester.getQueens().size() == 2);
		
		tester.callPlaceOrRemoveQueen(1, 1);
		verify(mockBoard).removeQueenInSection(1, 1);
		verify(mockBoard, times(21)).enableSquare(anyInt(), anyInt());
		assertTrue(tester.getQueens().size() == 1);
		
		tester.callPlaceOrRemoveQueen(3, 3);
		verify(mockBoard).removeQueenInSection(3, 3);
		verify(mockBoard, times(30)).enableSquare(anyInt(), anyInt());
		assertTrue(tester.getQueens().size() == 0);
		
	}
	
//	@Test
	void testEnableOrDisableSquares() {
		tester = new Game();
		tester.setBoard(mockBoard);
		
		tester.disableSquare(0, 0);
		verify(mockBoard).disableSquare(0, 0);
		
		tester.enableSquare(0, 0);
		verify(mockBoard).enableSquare(0, 0);
	}
	
	@Test
	void testGameOver() {
		when(mockBoard.getSize()).thenReturn(4);
		tester = new Game();
		tester.setBoard(mockBoard);
		ArrayList<Queen> queens = new ArrayList<>();
		tester.setQueens(queens);
		
		assertFalse(tester.callGameOver());
		
		queens.add(new QueenDefault(0,0,tester));
		
		assertFalse(tester.callGameOver());
		
		queens.add(new QueenDefault(0,1,tester));
		
		assertFalse(tester.callGameOver());
		
		queens.add(new QueenDefault(0,2,tester));
		
		assertFalse(tester.callGameOver());
		
		queens.add(new QueenDefault(0,3,tester));
		
		assertTrue(tester.callGameOver());
		
		queens.add(new QueenDefault(1,0,tester));
		
		assertTrue(tester.callGameOver());
	}

	@Test
	void testGetCoords() {
		int[][] inputs = { {-1,-1}, {0,0}, {1,1}, {6,6}, {7,7}, {8,8},{-100,-100}, {-100, 5}, {-100, 100}, {5,-100}, {5, 5}, {5, 100}, {100, -100}, {100, 5}, {100, 100}, {3,6}};
		when(mockVis.input()).thenReturn(inputs[0]).thenReturn(inputs[1]).thenReturn(inputs[2]).thenReturn(inputs[3])
		.thenReturn(inputs[4]).thenReturn(inputs[5]).thenReturn(inputs[6]).thenReturn(inputs[7])
		.thenReturn(inputs[8]).thenReturn(inputs[9]).thenReturn(inputs[10]).thenReturn(inputs[11])
		.thenReturn(inputs[12]).thenReturn(inputs[13]).thenReturn(inputs[14]).thenReturn(inputs[15]);
		when(mockBoard.getSize()).thenReturn(8);
		
		tester = new Game();
		tester.setBoard(mockBoard);
		tester.setVis(mockVis);
		
		int [] result = tester.callGetCoords();
		
		assertEquals(result[0], 0);
		assertEquals(result[1], 0);
		
		result = tester.callGetCoords();
		
		assertEquals(result[0], 1);
		assertEquals(result[1], 1);
		
		result = tester.callGetCoords();
		
		assertEquals(result[0], 6);
		assertEquals(result[1], 6);
		
		result = tester.callGetCoords();
		
		assertEquals(result[0], 7);
		assertEquals(result[1], 7);
		
		result = tester.callGetCoords();
		
		assertEquals(result[0], 5);
		assertEquals(result[1], 5);
		
		result = tester.callGetCoords();
		
		assertEquals(result[0], 3);
		assertEquals(result[1], 6);
		
	}
	
	@Test
	void testIsQueenCoords() {
		tester = new Game();
		ArrayList<Queen> queens = new ArrayList<>();
		queens.add(new QueenDefault(2,3,tester));
		queens.add(new QueenDefault(0,0,tester));
		
		
		
		tester.setQueens(queens);
		
		assertTrue(tester.callIsQueenCoords(0,0) == queens.get(1));
		assertTrue(tester.callIsQueenCoords(-1,0) == null);
		assertTrue(tester.callIsQueenCoords(0,-1) == null);
		assertTrue(tester.callIsQueenCoords(-1,-1) == null);
		assertTrue(tester.callIsQueenCoords(2,3) == queens.get(0));

		
	}
}