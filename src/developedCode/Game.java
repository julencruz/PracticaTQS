package developedCode;
import java.util.ArrayList;


public class Game {
	private Board board;
//	private int score;     TODO
//	private int highscore;
	private ArrayList<Queen> queens = new ArrayList<>();
	private Visualizer view;
	
	public Game()
	{
		board = new Board();
		view = new Visualizer();
	}
	
	public Game(Board b, Visualizer v)
	{
		board = b;
		view = v;
	}
	
	
	public void play() {
		
	}
	
	private boolean gameOver() {
		return (queens.size() >= board.getSize());
	}
	
	private void placeOrRemoveQueen(int x, int y){
		Queen queen = isQueenCoords(x,y);
		if (queen == null) {
			if (board.isSquareAvailable(x, y)) {
				board.placeQueenInSection(x, y);
				queen = new QueenDefault(x,y,this);
				queens.add(queen);
				queen.disableSquares(board.getSize());
			}
		} else {
			board.removeQueenInSection(x, y);
			queen.enableSquares(board.getSize());
			queens.remove(queen);
		}
	}
	
	private int[] getCoords() {
		int size = board.getSize();
		int[] coords;
		do {
			coords = view.input();
		} 
		while(coords[0] < 0 || coords[0] >= size || coords[1] < 0 || coords[1] >= size);
		return coords;
	}
	
	private Queen isQueenCoords(int x, int y) {
		for (Queen queen : queens) {
			int[] coord = queen.getPos();
			if (coord[0] == x && coord[1] == y) {
				return queen;
			}
		}
		return null;
	}
	
	public void disableSquare(int x, int y)
	{
		board.disableSquare(x, y);
	}
	
	public void enableSquare(int x, int y)
	{
		board.enableSquare(x, y);
	}

	
	//quitar dsps de test
	public void setVis(Visualizer vis)
	{
		view = vis;
	}
	
	public void setQueens(ArrayList<Queen> qs)
	{
		queens = qs;
	}
	
		public ArrayList<Queen> getQueens()
	{
		return queens;
	}
	
	public void setBoard(Board b)
	{
		board = b;
	}
	
	public int[] callGetCoords()
	{
		return getCoords();
	}
	
	public Queen callIsQueenCoords(int x, int y)
	{
		return isQueenCoords(x,y);
	}
	
	public boolean callGameOver() {
		return gameOver();
	}
	
	public void callPlaceOrRemoveQueen(int x, int y){
		 placeOrRemoveQueen(x, y);
	}
	
}
