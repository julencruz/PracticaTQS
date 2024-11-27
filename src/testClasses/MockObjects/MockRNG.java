package testClasses.MockObjects;

import developedCode.RNG;

public class MockRNG extends RNG {
	int[][] array;
	int i = 0;
	int j = -1;
	
	public MockRNG(int[][] array) {
		this.array = array;
		i = 0;
		j = -1;
	}
	
	@Override
	public int random(int minValue, int maxValue) {
		j++;
		if (j >= array[i].length) {
			j = 0;
			i++;
		}
		return array[i][j];
	}

}
