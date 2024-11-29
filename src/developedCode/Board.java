package developedCode;
import java.util.ArrayList;

public class Board {
	private Square[][] squares;
	private ArrayList<Section> sections;
	private GenerationStrategy strategy;
	private int size;
	
	public Board (GenerationStrategy strategy, int size) {
		this.strategy = strategy;
		this.size = size;
	}
	
	public void generateBoard() {
		
	}
	
	public void removeQueenInSection(int x, int y) {
		
	}
	
	public void placeQueenInSection(int x, int y) {
		
	}
	
	public void disableSquare(int x, int y) {
	}
	
	
	private void createSections(ArrayList<ArrayList<Integer>> queensPosition) {
		
	}
	
	public void enableSquare(int x, int y) {

	}
	
	public boolean isSquareAvailable(int x, int y) {
		return true;
	}
	
//	Remove when finished testing
	public Square[][] getMatrix() {
		return squares;
	}
	
	public void setMatrix(Square[][] matrix) {
		squares = matrix;
	}
	
	public ArrayList<Section> getSections() {
		return sections;
	}
	
	
	public void callCreateSections(ArrayList<ArrayList<Integer>> queensPosition){
		createSections(queensPosition);
	}
}
