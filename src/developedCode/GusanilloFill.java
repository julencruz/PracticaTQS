package developedCode;

import java.util.ArrayList;
import java.util.Arrays;

public class GusanilloFill extends GenerationStrategy {

	// Constructor por defecto
	public GusanilloFill()
	{
		super();
	}

	// Constructor con tamaño y generador RNG
	public GusanilloFill(int size, RNG rng) {
		super(size, rng);
	}

	// Método que crea las secciones en la matriz de cuadros coloreados
	@Override
	protected Square[][] createSections(Square[][] coloredMatrix) {
		// Itera sobre todas las posiciones de las reinas
		for(ArrayList<Integer> coords : queensPosition)
		{
			// Obtiene las coordenadas de la reina actual
			int row = coords.get(0);
			int col = coords.get(1);
			
			// Genera un número aleatorio de pasos que puede tomar la reina
			int stepsNumber = rng.random(0, size);
			
			// Obtiene el color de la casilla donde está la reina
			String color = coloredMatrix[row][col].getColor();
			
			// Realiza una serie de pasos desde la posición de la reina
			for (int i = 0; i < stepsNumber; i++)
			{
				// Crea una lista para almacenar las direcciones posibles
				ArrayList<ArrayList<Integer>> steps = new ArrayList<>();
				
				// Verifica si puede moverse hacia arriba y si la casilla no tiene reina
				if (row-1 >= 0 && !coloredMatrix[row-1][col].hasQueen()) {
					steps.add(new ArrayList<>(Arrays.asList(-1, 0)));  // Mueve hacia arriba
				}
				
				// Verifica si puede moverse hacia la derecha y si la casilla no tiene reina
				if (col+1 < size && !coloredMatrix[row][col+1].hasQueen()) {
					steps.add(new ArrayList<>(Arrays.asList(0, 1)));  // Mueve hacia la derecha
				}
				
				// Verifica si puede moverse hacia abajo y si la casilla no tiene reina
				if (row+1 < size && !coloredMatrix[row+1][col].hasQueen()) {
					steps.add(new ArrayList<>(Arrays.asList(1, 0)));  // Mueve hacia abajo
				}
				
				// Verifica si puede moverse hacia la izquierda y si la casilla no tiene reina
				if (col-1 >= 0 && !coloredMatrix[row][col-1].hasQueen()) {
					steps.add(new ArrayList<>(Arrays.asList(0, -1))); // Mueve hacia la izquierda
				}
				
				// Si no hay direcciones disponibles, termina el bucle
				if (steps.isEmpty()) {
					break;
				}
				
				// Selecciona una dirección aleatoria para moverse
				int direction = rng.random(0, steps.size()-1);
				
				// Actualiza la posición de la reina según la dirección elegida
				row = row + steps.get(direction).get(0);
				col = col + steps.get(direction).get(1);
				
				// Cambia el color de la casilla a la del color de la reina
				coloredMatrix[row][col].setColor(color);
			}
		}
		// Devuelve la matriz coloreada después de los cambios
		return coloredMatrix;
	}
	
}
