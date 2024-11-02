package developedCode;

public abstract class Square {
	protected int available;
	protected String color;
	
	public Square()
	{
		available = 0;
		color = "";
	}
	
	public Square(String initColor)
	{
		available = 0;
		color = initColor;
	}
	
	public abstract void disable();
	public abstract boolean isDisabled();
	public abstract void setColor(String color);
	public abstract String getColor();
}
