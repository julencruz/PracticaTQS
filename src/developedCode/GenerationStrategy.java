package developedCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class GenerationStrategy {
	protected int size;
	protected RNG rng;
	protected ArrayList<ArrayList<Integer>> queensPosition = new ArrayList<>();
	
	public GenerationStrategy(int size, RNG rng) {
		assert (size <= Colors.colorArray.size()) : "Not enough colors for that many queens!";
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
	   	    
	    
	    while (!availableCols.isEmpty()) {
	    	
		    int randomIndex = rng.random(0, availableCols.size() - 1); 
		    int selectedCol = availableCols.get(randomIndex);
		    
	        markAffectedPositions(matrix, row, selectedCol);
	        queensPosition.add(new ArrayList<>(Arrays.asList(row, selectedCol)));

	        if (placeQueens(matrix, row + 1)) {
	            return true;
	        }

	        matrix[row][selectedCol].available = 0; 
	        unmarkAffectedPositions(matrix, row, selectedCol); 
	        queensPosition.remove(queensPosition.size() - 1);
	        availableCols.remove(randomIndex);
	    }

	    return false; 
	}
	
	private void unmarkAffectedPositions(Square[][] matrix, int row, int col) {
		if (row >= 0 && row < size && col >= 0 && col < size)
		{
			for (int i = 0; i < size; i++) {
				if (!matrix[row][i].hasQueen()) {
		            matrix[row][i].enable();
		        }
		    }

		    for (int i = 0; i < size; i++) {
		        if (!matrix[i][col].hasQueen()) {
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
				if (!matrix[row][i].hasQueen()) {
		            matrix[row][i].disable();
		        }
		    }

		    for (int i = 0; i < size; i++) {
		        if (!matrix[i][col].hasQueen()) {
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
		queensPosition.clear();
		blankMatrix = new Square[size][size];
		for (int i = 0; i < size; i++) {
		    for (int j = 0; j < size; j++) {
		        blankMatrix[i][j] = new SquareDefault();
		    }
		}
		if (placeQueens(blankMatrix, 0)) {
	        return blankMatrix;
	    } else {
	        return null;
	    }
		
	}
	
	protected Square[][] assignColorToQueens(Square[][] queenMatrix){
		ArrayList<String> colors = new ArrayList<>(Colors.colorArray);
		for(ArrayList<Integer> coord : queensPosition) {
			int index = rng.random(0, colors.size()-1);
			queenMatrix[coord.get(0)][coord.get(1)].setColor(colors.get(index));
			colors.remove(index);
			
		}
		return queenMatrix;
	}
	
	protected abstract Square[][] createSections(Square[][] coloredMatrix);
	
	protected Square[][] fillBlanksAndReset(Square[][] sectionedMatrix){
		for (int i = 0; i < size; i++)
		{
			for (int j = 0; j < size; j++)
			{
				sectionedMatrix[i][j].setAvailable(0);
				if (sectionedMatrix[i][j].getColor() == "")
				{
				ArrayList<String> possibleColors = new ArrayList<>();
						
					if (i-1 >= 0 && sectionedMatrix[i-1][j].getColor() != "") {
						possibleColors.add(sectionedMatrix[i-1][j].getColor());
					}
						
					if (j+1 < size && sectionedMatrix[i][j+1].getColor() != "") {
						possibleColors.add(sectionedMatrix[i][j+1].getColor());
					}
					if (i+1 < size && sectionedMatrix[i+1][j].getColor() != "") {
						possibleColors.add(sectionedMatrix[i+1][j].getColor());
					}
					if (j-1 >= 0 && sectionedMatrix[i][j-1].getColor() != "") {
						possibleColors.add(sectionedMatrix[i][j-1].getColor());
					}
					
					String color = "";
					if (possibleColors.isEmpty()) {
						int index = rng.random(0, queensPosition.size()-1);
						color = sectionedMatrix[queensPosition.get(index).get(0)][queensPosition.get(index).get(1)].getColor();
					}
					else
					{
						if(possibleColors.size() != 1)
						{
							int index = rng.random(0, possibleColors.size()-1);
							color = possibleColors.get(index);	
						} 
						else 
						{
							color = possibleColors.get(0);
						}

					}
					
					
					sectionedMatrix[i][j].setColor(color);
				}
			}
		}
		
		return sectionedMatrix;
	}
	
	public Square[][] generate(int size, RNG random){
		return null;
	}
	
	
	
//	Remove when finished testing
	
	public void setQueensPosition(ArrayList<ArrayList<Integer>> queens) {
		queensPosition = queens;
	}
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
	
	public Square[][] callFillBlanksAndReset(Square[][] sectionedMatrix){
		return fillBlanksAndReset(sectionedMatrix);
	}
}
