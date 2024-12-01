package developedCode;

public abstract class Queen {
	protected int posX;
	protected int posY;
	protected Game game;
	
	public Queen(int x, int y, Game g)
	{
		posX = x;
		posY = y;
		game = g;
	}
	
	public int[] getPos() {
	    return new int[]{posX, posY};
	}
	
	public abstract void disableSquares(int size);
	public abstract void enableSquares(int size);
}
