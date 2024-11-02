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
	}

	@Override
	public boolean isDisabled() {
		return false;
	}

	@Override
	public void setColor(String color) {	
	}

	@Override
	public String getColor() {
		return null;
	}

}
