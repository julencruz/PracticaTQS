package developedCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class GenerationStrategy {
    // Atributos de la clase: tamaño del tablero, generador de números aleatorios y posiciones de las reinas
    protected int size;
    protected RNG rng;
    protected ArrayList<ArrayList<Integer>> queensPosition = new ArrayList<>();
    
    // Constructor por defecto que inicializa el tamaño y el generador de números aleatorios
    public GenerationStrategy()
    {
        rng = new RNG();  // Se crea una nueva instancia del generador de números aleatorios
        size = rng.random(4,8);  // El tamaño se elige aleatoriamente entre 4 y 8
    }
    
    // Constructor que permite especificar el tamaño y el generador de números aleatorios
    public GenerationStrategy(int size, RNG rng) {
        // Asegura que haya suficientes colores (espacios para reinas)
        assert (size <= Colors.colorArray.size()) : "Not enough colors for that many queens!";
        this.size = size;
        this.rng = rng;
    }
    
    // Método para colocar las reinas en el tablero
    private boolean placeQueens(Square[][] matrix, int row) {
        if (row == size) {
            return true;  // Si todas las reinas han sido colocadas, se devuelve true
        }
        
        // Lista para almacenar las columnas disponibles para colocar una reina en la fila actual
        List<Integer> availableCols = new ArrayList<>();
        for (int col = 0; col < size; col++) {
            if (matrix[row][col].getAvailable() == 0) {
                availableCols.add(col); // Añade las columnas disponibles a la lista
            }
        }

        // Si no hay columnas disponibles, se retorna false
        if (availableCols.isEmpty()) {
            return false;
        }
        
        // Se intenta colocar una reina en cada columna disponible
        while (!availableCols.isEmpty()) {
            int randomIndex = rng.random(0, availableCols.size() - 1);  // Selección aleatoria de una columna disponible
            int selectedCol = availableCols.get(randomIndex);
            
            // Marca las posiciones afectadas por la reina en la columna seleccionada
            markAffectedPositions(matrix, row, selectedCol);
            queensPosition.add(new ArrayList<>(Arrays.asList(row, selectedCol)));  // Guarda la posición de la reina

            // Llama recursivamente para colocar la siguiente reina
            if (placeQueens(matrix, row + 1)) {
                return true;  // Si se coloca con éxito la siguiente reina, devuelve true
            }

            // Si no se puede colocar la siguiente reina, deshace los cambios y prueba otra columna
            matrix[row][selectedCol].available = 0;  // Restablece la columna como no disponible
            unmarkAffectedPositions(matrix, row, selectedCol);  // Desmarca las posiciones afectadas
            queensPosition.remove(queensPosition.size() - 1);  // Elimina la última posición registrada
            availableCols.remove(randomIndex);  // Elimina la columna probada de las disponibles
        }

        return false;  // Si no se ha podido colocar la reina, devuelve false
    }
    
    // Método para desmarcar las posiciones afectadas por la reina
    private void unmarkAffectedPositions(Square[][] matrix, int row, int col) {
        if (row >= 0 && row < size && col >= 0 && col < size)
        {
            // Desmarca las casillas de la fila
            for (int i = 0; i < size; i++) {
                if (!matrix[row][i].hasQueen()) {
                    matrix[row][i].enable();  // Habilita las casillas en la fila
                }
            }

            // Desmarca las casillas de la columna
            for (int i = 0; i < size; i++) {
                if (!matrix[i][col].hasQueen()) {
                    matrix[i][col].enable();  // Habilita las casillas en la columna
                }
            }
            
            // Desmarca las casillas en las diagonales
            if (row - 1 >= 0 && col - 1 >= 0) {
                matrix[row - 1][col - 1].enable();  // Habilita la casilla diagonal superior izquierda
            }
            if (row - 1 >= 0 && col + 1 < size) {
                matrix[row - 1][col + 1].enable();  // Habilita la casilla diagonal superior derecha
            }
            if (row + 1 < size && col - 1 >= 0) {
                matrix[row + 1][col - 1].enable();  // Habilita la casilla diagonal inferior izquierda
            }
            if (row + 1 < size && col + 1 < size) {
                matrix[row + 1][col + 1].enable();  // Habilita la casilla diagonal inferior derecha
            }
            
            matrix[row][col].setAvailable(0);  // Marca la casilla actual como no disponible
        } 
    }

    // Método para marcar las posiciones afectadas por la reina
    private void markAffectedPositions(Square[][] matrix, int row, int col) {
        if (row >= 0 && row < size && col >= 0 && col < size)
        {
            matrix[row][col].setAvailable(1000);  // Marca la casilla donde se coloca la reina
            // Deshabilita las casillas en la fila de la reina
            for (int i = 0; i < size; i++) {
                if (!matrix[row][i].hasQueen()) {
                    matrix[row][i].disable();  // Deshabilita las casillas en la fila
                }
            }

            // Deshabilita las casillas en la columna de la reina
            for (int i = 0; i < size; i++) {
                if (!matrix[i][col].hasQueen()) {
                    matrix[i][col].disable();  // Deshabilita las casillas en la columna
                }
            }
            
            // Deshabilita las casillas en las diagonales
            if (row - 1 >= 0 && col - 1 >= 0) {
                matrix[row - 1][col - 1].disable();  // Deshabilita la casilla diagonal superior izquierda
            }
            if (row - 1 >= 0 && col + 1 < size) {
                matrix[row - 1][col + 1].disable();  // Deshabilita la casilla diagonal superior derecha
            }
            if (row + 1 < size && col - 1 >= 0) {
                matrix[row + 1][col - 1].disable();  // Deshabilita la casilla diagonal inferior izquierda
            }
            if (row + 1 < size && col + 1 < size) {
                matrix[row + 1][col + 1].disable();  // Deshabilita la casilla diagonal inferior derecha
            }
        } 
    }
	

    protected Square[][] generateQueens(Square[][] blankMatrix){
        queensPosition.clear();  // Limpia la lista de posiciones de las reinas
        blankMatrix = new Square[size][size];  // Crea un nuevo tablero vacío de tamaño 'size'
        
        // Inicializa cada casilla del tablero como un objeto SquareDefault
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                blankMatrix[i][j] = new SquareDefault();
            }
        }
        
        // Intenta colocar las reinas en el tablero comenzando desde la fila 0
        if (placeQueens(blankMatrix, 0)) {
            return blankMatrix;  // Si las reinas se colocan con éxito, devuelve el tablero
        } else {
            return null;  // Si no se pueden colocar todas las reinas, devuelve null
        }
    }

    protected Square[][] assignColorToQueens(Square[][] queenMatrix){
        ArrayList<String> colors = new ArrayList<>(Colors.colorArray);  // Crea una lista de colores disponibles
        for(ArrayList<Integer> coord : queensPosition) {  // Recorre las posiciones de las reinas
            int index = rng.random(0, colors.size()-1);  // Selecciona aleatoriamente un color
            queenMatrix[coord.get(0)][coord.get(1)].setColor(colors.get(index));  // Asigna el color a la reina en la posición dada
            colors.remove(index);  // Elimina el color usado para evitar repetirlo
        }
        return queenMatrix;  // Devuelve el tablero con los colores asignados a las reinas
    }

    protected abstract Square[][] createSections(Square[][] coloredMatrix);  // Método abstracto para crear secciones en el tablero

    protected Square[][] fillBlanksAndReset(Square[][] sectionedMatrix){
        // Recorre todas las casillas del tablero
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                sectionedMatrix[i][j].setAvailable(0);  // Establece la casilla como no disponible

                // Si la casilla no tiene color, intenta asignar un color basado en sus casillas vecinas
                if (sectionedMatrix[i][j].getColor() == "")
                {
                    ArrayList<String> possibleColors = new ArrayList<>();
                    
                    // Añade colores de las casillas vecinas a la lista de posibles colores
                    if (i-1 >= 0 && sectionedMatrix[i-1][j].getColor() != "") {
                        possibleColors.add(sectionedMatrix[i-1][j].getColor());
                    }
                    
                    if (j+1 < size && sectionedMatrix[i][j+1].getColor() != "") {
                        possibleColors.add(sectionedMatrix[i][j+1].getColor());
                    }
                    if (i+1 < size && sectionedMatrix[i+1][j].getColor() != "") {
                        possibleColors.add(sectionedMatrix[i+1][j].getColor());
                    }
                    if (j-1 >= 0 && sectionedMatrix[i][j-1].getColor() != "") {
                        possibleColors.add(sectionedMatrix[i][j-1].getColor());
                    }
                    
                    String color = "";
                    // Si no hay colores vecinos, selecciona un color aleatorio de las reinas
                    if (possibleColors.isEmpty()) {
                        int index = rng.random(0, queensPosition.size()-1);
                        color = sectionedMatrix[queensPosition.get(index).get(0)][queensPosition.get(index).get(1)].getColor();
                    }
                    else
                    {
                        // Si hay varios colores posibles, selecciona uno aleatoriamente
                        if(possibleColors.size() != 1)
                        {
                            int index = rng.random(0, possibleColors.size()-1);
                            color = possibleColors.get(index);    
                        } 
                        else 
                        {
                            color = possibleColors.get(0);  // Si solo hay un color, lo selecciona
                        }
                    }
                    
                    sectionedMatrix[i][j].setColor(color);  // Asigna el color seleccionado a la casilla
                }
            }
        }
        
        return sectionedMatrix;  // Devuelve el tablero con los colores asignados a las casillas vacías
    }

    public Square[][] generate(){
        Square[][] matrix = new Square[size][size];  // Crea un nuevo tablero vacío de tamaño 'size'
        
        // Inicializa cada casilla del tablero como un objeto SquareDefault
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++)
            {
                matrix[i][j] = new SquareDefault();
            }
        }
        
        // Llama a los métodos para generar el tablero con las reinas y colores asignados
        matrix = generateQueens(matrix);
        matrix = assignColorToQueens(matrix);
        matrix = createSections(matrix);  // Este método es abstracto y debe ser implementado por una subclase
        matrix = fillBlanksAndReset(matrix);
        
        return matrix;  // Devuelve el tablero generado
    }

    public ArrayList<ArrayList<Integer>> getQueensPosition() {
        return queensPosition;  // Devuelve la lista de posiciones de las reinas
    }

    public int getSize()
    {
        return size;  // Devuelve el tamaño del tablero
    }

    // Métodos de prueba, que deben eliminarse cuando se termine de probar
    public void setQueensPosition(ArrayList<ArrayList<Integer>> queens) {
        queensPosition = queens;  // Establece la lista de posiciones de las reinas
    }

    public Square[][] callGenerateQueens(Square[][] blankMatrix){
        return generateQueens(blankMatrix);  // Llama al método generateQueens
    }

    public Square[][] callAssignColorToQueens(Square[][] queenMatrix){
        return assignColorToQueens(queenMatrix);  // Llama al método assignColorToQueens
    }

    public Square[][] callCreateSections(Square[][] coloredMatrix){
        return createSections(coloredMatrix);  // Llama al método abstracto createSections
    }

    public void callMarkAffectedPositions(Square[][] matrix, int row, int col){
        markAffectedPositions(matrix, row, col);  // Llama al método markAffectedPositions
    }

    public void callUnmarkAffectedPositions(Square[][] matrix, int row, int col){
        unmarkAffectedPositions(matrix, row, col);  // Llama al método unmarkAffectedPositions
    }

    public Square[][] callFillBlanksAndReset(Square[][] sectionedMatrix){
        return fillBlanksAndReset(sectionedMatrix);  // Llama al método fillBlanksAndReset
    }
}
