package developedCode;

public abstract class Queen {
	private int posX;
	private int posY;
	private Game game;
	
	public Queen(int x, int y)
	{
		posX = x;
		posY = y;
	}
	
	public int[] getPos() {
	    return new int[]{posX, posY};
	}
}
