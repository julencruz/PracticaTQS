package developedCode;

import java.util.ArrayList;
import java.util.Scanner;

public class Visualizer {
	Scanner scanner;

	// Constructor que inicializa el scanner para la entrada del usuario
	public Visualizer() {
		scanner = new Scanner(System.in);
	}
	
	// Método que muestra el mensaje de bienvenida y espera una entrada del usuario
	public void init() {
		System.out.println("Bienvenido a Queens!");
		System.out.println("Pulsa 'enter' para continuar");
		scanner.nextLine();
	}
	
	// Método que pregunta al usuario si desea un marcador de ayuda y devuelve la respuesta
	public boolean askForHelp() {
		System.out.println("¿Quieres un marcador que ponga X donde no puedas colocar reinas? (Y/N)");
		while (true) {
	        String input = scanner.nextLine().trim().toUpperCase();
	        if (input.equals("Y")) {
	            return true; // Si responde 'Y', devuelve true
	        } else if (input.equals("N")) {
	            return false; // Si responde 'N', devuelve false
	        } else {
	            System.out.println("Error: Introduce Y o N"); // En caso de respuesta incorrecta, pide nuevamente la respuesta
	        }
	    }
	}
	
	// Método que muestra el tablero con las reinas y, si es necesario, la ayuda de marcadores
	public void showBoard(Board board, ArrayList<Queen> queens, boolean help) {
		clear(); // Limpia la consola antes de mostrar el tablero
		
		// Mostrar los números en la parte superior para las columnas
		System.out.print("   "); // Espacio inicial para alinear con los números de la izquierda
		for (int j = 0; j < board.getSize(); j++) {
		    System.out.print(j + " "); // Imprime los números de las columnas
		}
		System.out.println();

		// Dibujar el tablero con los números a la izquierda para las filas
		for (int i = 0; i < board.getSize(); i++) {
		    System.out.print(i + " "); // Imprime el número de la fila a la izquierda
		    if (i < 10) {
		        System.out.print(" "); // Alinear los números de una cifra con los de dos cifras
		    }
		    for (int j = 0; j < board.getSize(); j++) {
		        boolean isQueen = false; 
		        // Verificar si hay una reina en la casilla (i, j)
		        if (queens.size() != 0) {
		            for (Queen queen : queens) {
		                int[] pos = queen.getPos();
		                if (i == pos[0] && j == pos[1]) {
		                    // Si hay una reina, mostrarla con su color
		                    System.out.print(board.getMatrix()[i][j].getColor() + "Q \033[0m");
		                    isQueen = true; 
		                    break;
		                }
		            }
		        }
		        // Si no hay una reina, mostrar la casilla
		        if (!isQueen) {
		        	// Si la casilla está deshabilitada y se pide ayuda, mostrar marcador 'X'
		        	if (board.getMatrix()[i][j].isDisabled()  && help) {
		        		System.out.print(board.getMatrix()[i][j].getColor() + "· \033[0m");
		        	} else {
		        		System.out.print(board.getMatrix()[i][j].getColor() + "  \033[0m");
		        	}
		        }
		    }
		    System.out.println();
		}
		System.out.println();
	}

	// Método que pide al usuario las coordenadas para colocar o quitar una reina
	public int[] input() {
        int[] coords = new int[2];
        System.out.println("Escoge dónde poner o quitar una reina:");
        
        try {
            System.out.print("Fila: ");
            coords[0] = Integer.parseInt(scanner.nextLine()); // Leer fila
            
            System.out.print("Columna: ");
            coords[1] = Integer.parseInt(scanner.nextLine()); // Leer columna
        } catch (NumberFormatException e) {
            System.out.println("Error: Introduce un número válido."); // Si la entrada no es válida, pide de nuevo las coordenadas
            return input();
        }

        return coords;
    }
	
	// Método que pregunta si el jugador quiere jugar otra vez
	public boolean playAgain() {
	    System.out.println("¿Quieres volver a jugar? (Y/N)");
	    while (true) {
	        String input = scanner.nextLine().trim().toUpperCase(); // Leer entrada y convertir a mayúsculas
	        if (input.equals("Y")) {
	            return true; // El jugador quiere jugar de nuevo
	        } else if (input.equals("N")) {
	            return false; // El jugador no quiere jugar de nuevo
	        } else {
	            System.out.println("Error: Introduce Y o N"); // En caso de respuesta incorrecta, pide nuevamente la respuesta
	        }
	    }
	}
	
	// Método que muestra el mensaje de victoria cuando el jugador gana
	public void win() {
		clear();
		System.out.println("¡Enhorabuena, has ganado!"); // Mensaje de victoria
	}
	
	// Método que muestra un mensaje de despedida cuando el jugador termina
	public void finish() {
		System.out.println("Gracias por jugar."); // Mensaje de agradecimiento
	}
	
	// Método que limpia la pantalla imprimiendo varias líneas en blanco
	public void clear() {
		// System.out.print("\033[H\033[2J"); // Este es un código ANSI para limpiar la terminal, pero está comentado.
		System.out.println("\n".repeat(100)); // Imprime 100 saltos de línea para simular la limpieza de la pantalla
	}
}
