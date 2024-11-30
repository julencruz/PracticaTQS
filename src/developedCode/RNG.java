package developedCode;
public class RNG {
	public int random(int minValue, int maxValue) {
		int range = maxValue - minValue + 1;
		return (int) (Math.random() * range) + minValue;
	}
}
