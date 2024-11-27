package testClasses.MockObjects;

import developedCode.RNG;

public class MockRNG extends RNG {
	int[][] array;
	int i = 0;
	int j = -1;
	
	public MockRNG(int[][] array) {
		this.array = array;
	}
	
	@Override
	public int random(int minValue, int maxValue) {
		if(array[0].length == 1 && array.length == 1)
		{
			return array[0][0];
		}
		
		j++;
		if (j >= array[i].length) {
			j = 0;
			i++;
		}
		return array[i][j];
	}

}
