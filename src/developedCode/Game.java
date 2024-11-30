package developedCode;
import java.util.ArrayList;


public class Game {
	private Board board;
//	private int score;     TODO
//	private int highscore;
	private ArrayList<Queen> queens = new ArrayList<>();
	private Visualizer view;
	
	
	
	
	public void play() {
		
	}
	
	private int[] getCoords() {
		return null;
	}
	
	private Queen isQueenCoords() {
		return null;
	}
	
	
	//quitar dsps de test
	public void setVis(Visualizer vis)
	{
		view = vis;
	}
	
	public void setBoard(Board b)
	{
		board = b;
	}
	
	public int[] callGetCoords()
	{
		return getCoords();
	}
	
}
