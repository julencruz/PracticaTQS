package developedCode;

public class SquareDefault extends Square{

	public SquareDefault()
	{
		super();
	}
	
	public SquareDefault(String initColor)
	{
		super(initColor);
	}
	
	
	@Override
	public void disable() {		
		available++;
	}

	@Override
	public boolean isDisabled() {
		if(available > 0)
		{
			return true;
		}
		
		return false;
	}

	@Override
	public void setColor(String setColor) {	
		color = setColor;
	}

	@Override
	public String getColor() {
		return color;
	}

}
