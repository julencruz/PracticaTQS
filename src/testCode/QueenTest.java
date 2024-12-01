package testCode;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import developedCode.Queen;
import developedCode.QueenDefault;
import testClasses.MockObjects.MockGame;
import developedCode.Game;



class QueenTest {
	
	Queen queen1;
	Queen queen2;
	Game mockGame;	
	
	@Test
	void testDisableAndEnableSquares() {
		
		// En primera fila son las coordenadas de las casilla que se deberian deshabilitar si ponemos una reina en 0,0. 
		// La segunda fila es para una reina puesta en 2,2
		// segunda copia de los valores es para test de enable
		int[][] valuesToTest = {{0,0},{0,0},{0,1},{1,0},{0,2},{2,0},{0,3},{3,0},{1,1},
								{2,0},{0,2},{2,1},{1,2},{2,2},{2,2},{2,3},{3,2},{1,3},{3,3},{3,1},{1,1},
								{0,0},{0,0},{0,1},{1,0},{0,2},{2,0},{0,3},{3,0},{1,1},
								{2,0},{0,2},{2,1},{1,2},{2,2},{2,2},{2,3},{3,2},{1,3},{3,3},{3,1},{1,1}};
		
		
		mockGame = new MockGame(valuesToTest);
		queen1 = new QueenDefault(0,0,mockGame);

		// los asserts se hacen en el mock
		queen1.disableSquares(4);
		
		queen2 = new QueenDefault(2,2,mockGame);
		queen2.disableSquares(4);
		
		queen1.enableSquares(4);
		queen2.enableSquares(4);
	}
}