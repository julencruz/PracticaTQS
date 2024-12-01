package developedCode;

import java.util.ArrayList;
import java.util.Arrays;

public class GusanilloFill extends GenerationStrategy {

	public GusanilloFill()
	{
		super();
	}

	public GusanilloFill(int size, RNG rng) {
		super(size, rng);
	}

	@Override
	protected Square[][] createSections(Square[][] coloredMatrix) {
		for(ArrayList<Integer> coords : queensPosition)
		{
			int row = coords.get(0);
			int col = coords.get(1);
			int stepsNumber = rng.random(0, size);
			String color = coloredMatrix[row][col].getColor();
			for (int i = 0; i < stepsNumber; i++)
			{
						
				ArrayList<ArrayList<Integer>> steps = new ArrayList<>();
						
				if (row-1 >= 0 && !coloredMatrix[row-1][col].hasQueen()) {
					steps.add(new ArrayList<>(Arrays.asList(-1, 0)));
				}
						
				if (col+1 < size && !coloredMatrix[row][col+1].hasQueen()) {
					steps.add(new ArrayList<>(Arrays.asList(0, 1)));
				}
				if (row+1 < size && !coloredMatrix[row+1][col].hasQueen()) {
					steps.add(new ArrayList<>(Arrays.asList(1, 0)));
				}
				if (col-1 >= 0 && !coloredMatrix[row][col-1].hasQueen()) {
					steps.add(new ArrayList<>(Arrays.asList(0, -1)));
				}
						
				if (steps.isEmpty()) {
					break;
				}
						
				int direction = rng.random(0, steps.size()-1);
						
				row = row + steps.get(direction).get(0);
				col = col + steps.get(direction).get(1);
				coloredMatrix[row][col].setColor(color);
			}
		}
		return coloredMatrix;
	}
	
}
