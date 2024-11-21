package developedCode;

import java.util.ArrayList;
import java.util.List;

public abstract class GenerationStrategy {
	protected int size;
	protected RNG rng;
	
	public GenerationStrategy(int size, RNG rng) {
		this.size = size;
		this.rng = rng;
	}
	
	private boolean placeQueens(Square[][] matrix, int row) {
	    if (row == size) {
	        return true;
	    }
	    
	    List<Integer> availableCols = new ArrayList<>();
	    for (int col = 0; col < size; col++) {
	        if (matrix[row][col].getAvailable() == 0) {
	            availableCols.add(col); //
	        }
	    }

	    if (availableCols.isEmpty()) {
	        return false;
	    }

	    for (int column : availableCols) {
	        markAffectedPositions(matrix, row, column);

	        if (placeQueens(matrix, row + 1)) {
	            return true;
	        }

	        matrix[row][column].available = 0; 
	        unmarkAffectedPositions(matrix, row, column); 
	    }

	    return false; 
	}
	
	private void unmarkAffectedPositions(Square[][] matrix, int row, int col) {
		if (row >= 0 && row < size && col >= 0 && col < size)
		{
			for (int i = 0; i < size; i++) {
				if (matrix[row][i].getAvailable() != 1000) {
		            matrix[row][i].enable();
		        }
		    }

		    for (int i = 0; i < size; i++) {
		        if (matrix[i][col].getAvailable() != 1000) {
		            matrix[i][col].enable();
		        }
		    }
		    if (row - 1 >= 0 && col - 1 >= 0) {
		        matrix[row - 1][col - 1].enable(); 
		    }
		    if (row - 1 >= 0 && col + 1 < size) {
		        matrix[row - 1][col + 1].enable(); 
		    }
		    if (row + 1 < size && col - 1 >= 0) {
		        matrix[row + 1][col - 1].enable(); 
		    }
		    if (row + 1 < size && col + 1 < size) {
		        matrix[row + 1][col + 1].enable(); 
		    }
		    
		    matrix[row][col].setAvailable(0);
		} 
	}

	private void markAffectedPositions(Square[][] matrix, int row, int col) {
		if (row >= 0 && row < size && col >= 0 && col < size)
		{
			matrix[row][col].setAvailable(1000);
			for (int i = 0; i < size; i++) {
				if (matrix[row][i].getAvailable() != 1000) {
		            matrix[row][i].disable();
		        }
		    }

		    for (int i = 0; i < size; i++) {
		        if (matrix[i][col].getAvailable() != 1000) {
		            matrix[i][col].disable();
		        }
		    }
		    if (row - 1 >= 0 && col - 1 >= 0) {
		        matrix[row - 1][col - 1].disable(); 
		    }
		    if (row - 1 >= 0 && col + 1 < size) {
		        matrix[row - 1][col + 1].disable(); 
		    }
		    if (row + 1 < size && col - 1 >= 0) {
		        matrix[row + 1][col - 1].disable(); 
		    }
		    if (row + 1 < size && col + 1 < size) {
		        matrix[row + 1][col + 1].disable(); 
		    }
		} 
		
	}	
	

	protected Square[][] generateQueens(Square[][] blankMatrix){
		
	}
	protected Square[][] assignColorToQueens(Square[][] queenMatrix){
		
	}
	
	protected abstract Square[][] createSections(Square[][] coloredMatrix);
	
	protected Square[][] fillBlanks(Square[][] sectionedMatrix){
		return null;
	}
	
	public Square[][] generate(int size, RNG random){
		return null;
	}
	
	
	
//	Remove when finished testing
	public Square[][] callGenerateQueens(Square[][] blankMatrix){
		return generateQueens(blankMatrix);
	}
	
	public Square[][] callAssignColorToQueens(Square[][] queenMatrix){
		return assignColorToQueens(queenMatrix);
	}
	
	public Square[][] callCreateSections(Square[][] coloredMatrix){
		return createSections(coloredMatrix);
	}
	
	public void callMarkAffectedPositions(Square[][] matrix, int row, int col){
		markAffectedPositions(matrix, row, col);
	}
	
	public void callUnmarkAffectedPositions(Square[][] matrix, int row, int col){
		unmarkAffectedPositions(matrix, row, col);
	}
}
