package developedCode;

public class QueenDefault extends Queen{
	
	public QueenDefault(int x, int y, Game g) {
		super(x, y, g);
	}

	@Override
	public void disableSquares(int size) {
		for(int i = 0; i < size; i++)
		{
			game.disableSquare(i, posY);
			game.disableSquare(posX, i);
		}
		
		if (posX-1 >= 0 && posY-1 >= 0) {
			game.disableSquare(posX-1, posY-1);
		}
		if (posX-1 >= 0 && posY+1 < size) {
			game.disableSquare(posX-1, posY+1);
		}
		if (posX+1 < size && posY-1 >= 0) {
			game.disableSquare(posX+1, posY-1);
		}
		if (posX+1 < size && posY+1 < size) {
			game.disableSquare(posX+1, posY+1);
		}
	}

	@Override
	public void enableSquares(int size) {
		for (int i = 0; i < size; i++) {
			game.enableSquare(i, posY);
			game.enableSquare(posX, i);
		}
		
		if (posX-1 >= 0 && posY-1 >= 0) {
			game.enableSquare(posX-1, posY-1);
		}
		if (posX-1 >= 0 && posY+1 < size) {
			game.enableSquare(posX-1, posY+1);
		}
		if (posX+1 < size && posY-1 >= 0) {
			game.enableSquare(posX+1, posY-1);
		}
		if (posX+1 < size && posY+1 < size) {
			game.enableSquare(posX+1, posY+1);
		}
	}
}
