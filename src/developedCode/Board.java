package developedCode;

import java.util.ArrayList;

public class Board {
    // Matriz de casillas en el tablero
	private Square[][] squares;
    // Lista de secciones que almacenan las posiciones de las reinas
	private ArrayList<Section> sections = new ArrayList<>();
    // Estrategia para generar el tablero
	private GenerationStrategy strategy;
    // Tamaño del tablero
	private int size;
	
    // Constructor por defecto
	public Board()
	{
		strategy = new GusanilloFill();  // Se asigna la estrategia de generación
		size = strategy.getSize();  // Se obtiene el tamaño del tablero desde la estrategia puesto que es aleatorio
		squares = new Square[size][size];  // Se inicializa la matriz de cuadrados
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				squares[i][j] = new SquareDefault();  // Se llena el tablero con cuadrados por defecto
			}
		}
	}
	
    // Constructor que permite definir la estrategia de generación y el tamaño para tests
	public Board (GenerationStrategy strategy, int size) {
		this.strategy = strategy;  // Se asigna la estrategia pasada como parámetro
		this.size = size;  // Se asigna el tamaño pasado como parámetro
		squares = new Square[size][size];  // Se inicializa la matriz de cuadrados
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				squares[i][j] = new SquareDefault();  // Se llena el tablero con cuadrados por defecto
			}
		}
	}
	
    // Método que genera el tablero usando la estrategia de generación
	public void generateBoard() {
		squares = strategy.generate();  // Genera el tablero según la estrategia
		createSectionsList(strategy.getQueensPosition());  // Crea la lista de secciones según las posiciones de las reinas
	}
	
    // Método que elimina una reina en la sección especificada
	public void removeQueenInSection(int x, int y) {
		getSquaresSection(squares[x][y]).enable();  // Habilita la sección que contiene la reina
	}
	
    // Método que coloca una reina en la sección especificada
	public void placeQueenInSection(int x, int y) {
		getSquaresSection(squares[x][y]).disable();  // Deshabilita la sección para colocar la reina
	}
	
    // Método para deshabilitar un cuadrado en las coordenadas especificadas
	public void disableSquare(int x, int y) {
		if (x < size && x >= 0 && y < size && y >= 0) 
		{
			squares[x][y].disable();  // Deshabilita el cuadrado en las coordenadas dadas
		}
	}
	
    // Método privado que crea la lista de secciones en base a las posiciones de las reinas
	private void createSectionsList(ArrayList<ArrayList<Integer>> queensPosition) {
		sections.clear();  // Se limpia la lista de secciones
		String color = null;  // Se inicializa una variable para almacenar el color
		for (ArrayList<Integer> coords : queensPosition) {
			color = squares[coords.get(0)][coords.get(1)].getColor();  // Se obtiene el color de la sección de la reina
			sections.add(new Section(color));  // Se añade la sección a la lista
		}
	}
	
    // Método que habilita un cuadrado en las coordenadas especificadas
	public void enableSquare(int x, int y) {
		if(x >= 0 && x < size && y >= 0 && y < size)
		{
			squares[x][y].enable();  // Habilita el cuadrado en las coordenadas dadas
		}
	}
	
    // Método que verifica si un cuadrado está disponible para colocar una reina
	public boolean isSquareAvailable(int x, int y) {
		boolean available = false;  // Se inicializa la variable que indica si el cuadrado está disponible
		if (x >= 0 && x < size && y >= 0 && y < size) {
			Square square = squares[x][y];  // Se obtiene el cuadrado en las coordenadas dadas
			if(!square.isDisabled() && getSquaresSection(square) != null && !getSquaresSection(square).isDisabled()){
				available = true;  // El cuadrado está disponible si no está deshabilitado y pertenece a una sección habilitada
			}
		}
		return available;  // Se retorna el resultado
	}
	
    // Método privado que obtiene la sección a la que pertenece un cuadrado
	private Section getSquaresSection(Square sq) {
		for (Section section : sections) {
			if (section.isSquareInSection(sq)) {  // Si el cuadrado está en la sección, se retorna esa sección
				return section;
			}
		}
		return null;  // Si no se encuentra la sección, se retorna null
	}
	
    // Método que obtiene la matriz de cuadrados del tablero
	public Square[][] getMatrix() {
		return squares;
	}
	
    // Método de prueba para establecer la matriz de cuadrados del tablero
	
	//	Eliminar después de acabar con los tests
	public void setMatrix(Square[][] matrix) {
		for (int i = 0; i < matrix[0].length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				squares[i][j].clone(matrix[i][j]);  // Se clona cada cuadrado en la nueva matriz
			}
		}
	}
	
    // Método que obtiene la lista de secciones
	public ArrayList<Section> getSections() {
		return sections;
	}
	
    // Método que establece la lista de secciones
	public void setSections(ArrayList<Section> sections) {
		this.sections = sections;
	}
	
    // Método que llama al método getSquaresSection para obtener la sección de un cuadrado
	public Section callGetSquaresSection(Square sq) {
		return getSquaresSection(sq);
	}
	
    // Método que llama al método createSectionsList para crear la lista de secciones
	public void callCreateSections(ArrayList<ArrayList<Integer>> queensPosition){
		createSectionsList(queensPosition);
	}

    // Método que obtiene el tamaño del tablero
	public int getSize() {
		return size;
	}
}
