package developedCode;
import java.util.ArrayList;

public class Board {
	private Square[][] squares;
	private RNG random;
	private ArrayList<Section> sections;
	private GenerationStrategy strategy;
	private int size;
	
	public Board(RNG random, GenerationStrategy strategy, int size) {
		this.random = random;	
		this.strategy = strategy;
		this.size = size;
	}
	
	public void generateBoard() {
		
	}
	
	public void placeOrRemoveQueen(int x, int y) {
		
	}
	
	
	
}
