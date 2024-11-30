package developedCode;
import java.util.ArrayList;

public class Board {
	private Square[][] squares;
	private ArrayList<Section> sections = new ArrayList<>();
	private GenerationStrategy strategy;
	private int size;
	
	public Board (GenerationStrategy strategy, int size) {
		this.strategy = strategy;
		this.size = size;
		squares = new Square[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				squares[i][j] = new SquareDefault();
			}
		}
	}
	
	public void generateBoard() {
		squares = strategy.generate();
		createSections(strategy.getQueensPosition());
	}
	
	public void removeQueenInSection(int x, int y) {

	}
	
	public void placeQueenInSection(int x, int y) {
		getSquaresSection(squares[x][y]).disable();
	}
	
	public void disableSquare(int x, int y) {
		if (x < size && x >= 0 && y < size && y >=0) 
		{
			squares[x][y].disable();
		}
		
	}
	
	
	private void createSections(ArrayList<ArrayList<Integer>> queensPosition) {
		sections.clear();
		String color = null;
		for (ArrayList<Integer> coords : queensPosition) {
			color = squares[coords.get(0)][coords.get(1)].getColor();
			sections.add(new Section(color));
		}
	}
	
	public void enableSquare(int x, int y) {
		if(x >= 0 && x < size && y >= 0 && y < size)
		{
			squares[x][y].enable();
		}
	}
	
	public boolean isSquareAvailable(int x, int y) {
		boolean available = false;
		if (x >= 0 && x < size && y >= 0 && y < size) {
			Square square = squares[x][y];
			if(!square.isDisabled() && getSquaresSection(square) != null && !getSquaresSection(square).isDisabled()){
				available = true;
			}
		}
		return available;
	}
	
	private Section getSquaresSection(Square sq) {
		for (Section section : sections) {
			if (section.isSquareInSection(sq)) {
				return section;
			}
		}
		return null;
	}
	
//	Remove when finished testing
	public Square[][] getMatrix() {
		return squares;
	}
	
	public void setMatrix(Square[][] matrix) {
		for (int i = 0; i < matrix[0].length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				squares[i][j].clone(matrix[i][j]);
			}
		}
	}
	
	public ArrayList<Section> getSections() {
		return sections;
	}
	
	public void setSections(ArrayList<Section> sections) {
		this.sections = sections;
	}
	
	public Section callGetSquaresSection(Square sq) {
		return getSquaresSection(sq);
	}
	
	public void callCreateSections(ArrayList<ArrayList<Integer>> queensPosition){
		createSections(queensPosition);
	}
}
