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
	public abstract boolean hasQueen();
	public abstract void enable();
	public abstract boolean isDisabled();
	public abstract void setColor(String color);
	public abstract String getColor();
	public void setAvailable(int n) {available = n;}
	public void clone(Square clone){this.available = clone.available; this.color = clone.color;}
	
	
	public int getAvailable() {
		return available;
		
	
	}
}
