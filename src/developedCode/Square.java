package developedCode;

public abstract class Square {
	private int available;
	private String color;
	
	public abstract void disable();
	public abstract boolean isDisabled();
	public abstract void setColor();
	public abstract String getColor();
}
