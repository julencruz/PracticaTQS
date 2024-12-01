package testClasses.MockObjects;

import developedCode.Game;
import static org.junit.jupiter.api.Assertions.*;

public class MockGame extends Game {
	int[][] array;
	int i = -1;
	
	public MockGame(int[][] array) {
		this.array = array;
		i = -1;
	}
	
	@Override
	public void disableSquare(int x, int y) {
		i++;
		assertEquals(array[i][0], x);
		assertEquals(array[i][1], y);
	}
	
	@Override
	public void enableSquare(int x, int y) {
		i++;
		assertEquals(array[i][0], x);
		assertEquals(array[i][1], y);
	}
}
