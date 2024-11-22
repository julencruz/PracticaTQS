package developedCode;

import java.util.ArrayList;

public class Colors {
	public static final String BACKGROUND_RED = "\u001b[37;41m";
	public static final String BACKGROUND_WHITE = "\u001b[30;47m";
	public static final String BACKGROUND_BLACK = "\u001b[37;40m";
	public static final String BACKGROUND_GREEN = "\u001b[37;42m";
	public static final String BACKGROUND_BLUE = "\u001b[37;44m";
	public static final String BACKGROUND_YELLOW = "\u001b[30;43m";
	public static final String BACKGROUND_CYAN = "\u001b[30;46m"; 
	public static final String BACKGROUND_MAGENTA = "\u001b[30;45m";

	public static final ArrayList<String> colorArray = new ArrayList<String>() {{
	    add(Colors.BACKGROUND_RED);
	    add(Colors.BACKGROUND_GREEN);
	    add(Colors.BACKGROUND_BLUE);
	    add(Colors.BACKGROUND_YELLOW);
	    add(Colors.BACKGROUND_CYAN);
	    add(Colors.BACKGROUND_MAGENTA);
	    add(Colors.BACKGROUND_WHITE);
	    add(Colors.BACKGROUND_BLACK);
	}};
}



