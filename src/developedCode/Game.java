package developedCode;
import java.util.ArrayList;

public class Game {
    // Objeto que representa el tablero del juego
	private Board board;
    // Lista de reinas en el juego
	private ArrayList<Queen> queens = new ArrayList<>();
    // Visualizador que se encarga de mostrar la interfaz al jugador
	private Visualizer view;
	
    // Constructor por defecto que inicializa el tablero y el visualizador
	public Game()
	{
		board = new Board();  // Se crea un nuevo tablero
		view = new Visualizer();  // Se crea un nuevo visualizador
	}
	
    // Constructor que permite definir el tablero y el visualizador
	public Game(Board b, Visualizer v)
	{
		board = b;  // Se asigna el tablero pasado como parámetro
		view = v;  // Se asigna el visualizador pasado como parámetro
	}
	
    // Método principal para ejecutar el juego
	public void play() {
		queens.clear();  // Se limpia la lista de reinas al comenzar una nueva partida
		board.generateBoard();  // Se genera el tablero
		view.init();  // Se inicializa la interfaz de usuario
		boolean help = view.askForHelp();  // Se pregunta si el jugador necesita ayuda
		while(!gameOver()) {  // Se sigue jugando hasta que el juego termine
			view.showBoard(board, queens, help);  // Se muestra el tablero
			int[] coords = getCoords();  // Se obtienen las coordenadas seleccionadas por el jugador
			placeOrRemoveQueen(coords[0], coords[1]);  // Se coloca o elimina una reina en esas coordenadas
		}
		view.win();  // Se muestra mensaje de victoria
		boolean again = view.playAgain();  // Se pregunta si el jugador quiere jugar de nuevo
		if (again) {
			view.clear();  // Se limpia la pantalla
			play();  // Se inicia una nueva partida
		} else {
			view.finish();  // Se muestra el mensaje de fin de juego
		}
	}
	
    // Método que verifica si el juego ha terminado
	private boolean gameOver() {
		return (queens.size() >= board.getSize());  // El juego termina cuando hay tantas reinas como el tamaño del tablero
	}
	
    // Método que coloca o elimina una reina en las coordenadas especificadas
	private void placeOrRemoveQueen(int x, int y){
		Queen queen = isQueenCoords(x,y);  // Se verifica si ya hay una reina en las coordenadas
		if (queen == null) {  // Si no hay una reina, se coloca una nueva
			if (board.isSquareAvailable(x, y)) {  
				board.placeQueenInSection(x, y);  
				queen = new QueenDefault(x,y,this);  
				queens.add(queen);  // Se añade la reina a la lista de reinas
				queen.disableSquares(board.getSize());  // Se deshabilitan los cuadrados afectados por la reina
			}
		} else {  // Si ya hay una reina, se elimina
			board.removeQueenInSection(x, y);  // Se elimina la reina de la sección
			queen.enableSquares(board.getSize());  // Se habilitan los cuadrados afectados por la reina
			queens.remove(queen);  // Se elimina la reina de la lista
		}
	}
	
    // Método que obtiene las coordenadas seleccionadas por el jugador
	private int[] getCoords() {
		int size = board.getSize();  // Se obtiene el tamaño del tablero
		int[] coords;
		do {
			coords = view.input();  // Se solicita al jugador que ingrese las coordenadas
		} 
		while(coords[0] < 0 || coords[0] >= size || coords[1] < 0 || coords[1] >= size);  // Se asegura de que las coordenadas sean válidas
		return coords;
	}
	
    // Método que verifica si hay una reina en las coordenadas especificadas
	private Queen isQueenCoords(int x, int y) {
		for (Queen queen : queens) {  // Se recorre la lista de reinas
			int[] coord = queen.getPos();  // Se obtiene la posición de la reina
			if (coord[0] == x && coord[1] == y) {  // Si las coordenadas coinciden, se retorna la reina
				return queen;
			}
		}
		return null;  // Si no se encuentra la reina, se retorna null
	}
	
    // Método que deshabilita un cuadrado en las coordenadas especificadas
	public void disableSquare(int x, int y)
	{
		board.disableSquare(x, y);  // Se deshabilita el cuadrado en las coordenadas dadas
	}
	
    // Método que habilita un cuadrado en las coordenadas especificadas
	public void enableSquare(int x, int y)
	{
		board.enableSquare(x, y);  // Se habilita el cuadrado en las coordenadas dadas
	}
	
	
	//Quitar despues de tests
	public void setVis(Visualizer vis)
	{
		view = vis;  // Se asigna el visualizador pasado como parámetro
	}
	
    // Método para establecer la lista de reinas
	public void setQueens(ArrayList<Queen> qs)
	{
		queens = qs;  // Se asigna la lista de reinas pasada como parámetro
	}
	
    // Método que obtiene la lista de reinas
	public ArrayList<Queen> getQueens()
	{
		return queens;  // Se retorna la lista de reinas
	}
	
    // Método para establecer el tablero
	public void setBoard(Board b)
	{
		board = b;  // Se asigna el tablero pasado como parámetro
	}
	
    // Método que llama al método getCoords para obtener las coordenadas del jugador
	public int[] callGetCoords()
	{
		return getCoords();  
	}
	
    // Método que llama al método isQueenCoords para verificar si hay una reina en las coordenadas dadas
	public Queen callIsQueenCoords(int x, int y)
	{
		return isQueenCoords(x,y);  
	}
	
    // Método que llama al método gameOver para verificar si el juego ha terminado
	public boolean callGameOver() {
		return gameOver(); 
	}
	
    // Método que llama al método placeOrRemoveQueen para colocar o eliminar una reina
	public void callPlaceOrRemoveQueen(int x, int y){
		 placeOrRemoveQueen(x, y); 
	}
	
}
