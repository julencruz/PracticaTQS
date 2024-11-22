package developedCode;

import java.util.ArrayList;
import java.util.Arrays;

public class GusanilloFill extends GenerationStrategy {

	public GusanilloFill(int size, RNG rng) {
		super(size, rng);
	}

	@Override
	protected Square[][] createSections(Square[][] coloredMatrix) {
		for(int i = 0; i < size; i++)
		{
			for (int j = 0; j < size; j++)
			{
				if (coloredMatrix[i][j].hasQueen())
				{
					int row = i;
					int col = j;
					int stepsNumber = rng.random(1, size);
					String color = coloredMatrix[i][j].getColor();
					for (int k = 0; k < stepsNumber; k++)
					{
						ArrayList<ArrayList<Integer>> steps = new ArrayList<>();
						
						if (i-1 >= 0 && !coloredMatrix[i-1][j].hasQueen()) {
							steps.add(new ArrayList<>(Arrays.asList(-1, 0)));
						}
						if (i+1 < size && !coloredMatrix[i+1][j].hasQueen()) {
							steps.add(new ArrayList<>(Arrays.asList(1, 0)));
						}
						if (j-1 >= 0 && !coloredMatrix[i][j-1].hasQueen()) {
							steps.add(new ArrayList<>(Arrays.asList(0, -1)));
						}
						if (j+1 < size && !coloredMatrix[i][j+1].hasQueen()) {
							steps.add(new ArrayList<>(Arrays.asList(0, 1)));
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
			}
		}
		return coloredMatrix;
	}
	
}
