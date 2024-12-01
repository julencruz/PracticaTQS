package developedCode;

public class QueenDefault extends Queen{
	
	// Constructor que inicializa la posición de la reina y el juego
	public QueenDefault(int x, int y, Game g) {
		super(x, y, g);
	}

	// Método que deshabilita las casillas en las que puede moverse la reina
	@Override
	public void disableSquares(int size) {
		// Deshabilita todas las casillas en la misma fila y columna que la reina
		for(int i = 0; i < size; i++)
		{
			game.disableSquare(posX, i);  // Deshabilita la fila de la reina
			game.disableSquare(i, posY);  // Deshabilita la columna de la reina
		}
		
		// Deshabilita las casillas en las diagonales de la reina
		if (posX-1 >= 0 && posY+1 < size) {
			game.disableSquare(posX-1, posY+1); // Diagonal superior derecha
		}
		if (posX+1 < size && posY+1 < size) {
			game.disableSquare(posX+1, posY+1); // Diagonal inferior derecha
		}
		if (posX+1 < size && posY-1 >= 0) {
			game.disableSquare(posX+1, posY-1); // Diagonal inferior izquierda
		}
		if (posX-1 >= 0 && posY-1 >= 0) {
			game.disableSquare(posX-1, posY-1); // Diagonal superior izquierda
		}
	}

	// Método que habilita las casillas en las que la reina puede moverse
	@Override
	public void enableSquares(int size) {
		// Habilita todas las casillas en la misma fila y columna que la reina
		for (int i = 0; i < size; i++) {
			game.enableSquare(posX, i);  // Habilita la fila de la reina
			game.enableSquare(i, posY);  // Habilita la columna de la reina
		}
		
		// Habilita las casillas en las diagonales de la reina
		if (posX-1 >= 0 && posY+1 < size) {
			game.enableSquare(posX-1, posY+1); // Diagonal superior derecha
		}
		if (posX+1 < size && posY+1 < size) {
			game.enableSquare(posX+1, posY+1); // Diagonal inferior derecha
		}
		if (posX+1 < size && posY-1 >= 0) {
			game.enableSquare(posX+1, posY-1); // Diagonal inferior izquierda
		}
		if (posX-1 >= 0 && posY-1 >= 0) {
			game.enableSquare(posX-1, posY-1); // Diagonal superior izquierda
		}
	}
}
