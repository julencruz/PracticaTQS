package developedCode;

public abstract class Queen {
	private int posX;
	private int posY;
	private Game game;
	
	public Queen(int x, int y, Game g)
	{
		posX = x;
		posY = y;
		game = g;
	}
	
	public int[] getPos() {
	    return new int[]{posX, posY};
	}
	
	public abstract void disableSquares();
	public abstract void enableSquares();
}
