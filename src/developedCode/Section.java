package developedCode;

public class Section {
	private String color;
	private boolean disabled;
	
	public Section() {
		color = "";
		disabled = false;
	}
	public Section(String initColor) {
		color = initColor;
		disabled = true;
	}
	
	public void setColor(String setColor) {
		color = setColor;
	}
	
	
	public String getColor() {
		return color;
	}
	public boolean isDisabled() {
		return disabled;
	}
	
	
	public void disable() {
		disabled = true;
	}
	public void enable() {
		disabled = false;
	}
	
	public boolean isSquareInSection(Square sq) {
		if(sq.getColor() == color)
		{
			return true;
		}
		
		return false;
	}

	
}
