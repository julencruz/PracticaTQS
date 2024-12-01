package developedCode;

import java.util.ArrayList;
import java.util.Scanner;

public class Visualizer {
		Scanner scanner;
	public Visualizer() {
		scanner = new Scanner(System.in);
	}
	
	public void init() {
		System.out.println("Bienvenido a Queens!");
		System.out.println("Pulsa 'enter' para continuar");
		scanner.nextLine();
	}
	
	public boolean askForHelp() {
		System.out.println("¿Quieres un marcador que ponga X donde no puedas colocar reinas? (Y/N)");
		while (true) {
	        String input = scanner.nextLine().trim().toUpperCase();
	        if (input.equals("Y")) {
	            return true; 
	        } else if (input.equals("N")) {
	            return false;
	        } else {
	            System.out.println("Error: Introduce Y o N");
	        }
	    }
		
	}
	
	public void showBoard(Board board, ArrayList<Queen> queens, boolean help) {
		clear();
		// Mostrar los números en la parte superior
		System.out.print("   "); // Espacio inicial para alinear con los números de la izquierda
		for (int j = 0; j < board.getSize(); j++) {
		    System.out.print(j + " ");
		}
		System.out.println();

		// Dibujar el tablero con los números a la izquierda
		for (int i = 0; i < board.getSize(); i++) {
		    System.out.print(i + " "); // Número de la fila a la izquierda
		    if (i < 10) {
		        System.out.print(" "); // Alinear los números de una cifra con los de dos cifras
		    }
		    for (int j = 0; j < board.getSize(); j++) {
		        boolean isQueen = false; 
		        if (queens.size() != 0) {
		            for (Queen queen : queens) {
		                int[] pos = queen.getPos();
		                if (i == pos[0] && j == pos[1]) {
		                    System.out.print(board.getMatrix()[i][j].getColor() + "Q \033[0m");
		                    isQueen = true; 
		                    break;
		                }
		            }
		        }
		        if (!isQueen) {
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


	public int[] input() {
        int[] coords = new int[2];
        System.out.println("Escoge dónde poner o quitar una reina:");
        
        try {
            System.out.print("Fila: ");
            coords[0] = Integer.parseInt(scanner.nextLine());
            
            System.out.print("Columna: ");
            coords[1] = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Error: Introduce un número válido.");
            return input();
        }

        return coords;
    }
	
	public boolean playAgain() {
	    System.out.println("¿Quieres volver a jugar? (Y/N)");
	    while (true) {
	        String input = scanner.nextLine().trim().toUpperCase(); // Leer entrada y convertir a mayúsculas
	        if (input.equals("Y")) {
	            return true; // El jugador quiere jugar de nuevo
	        } else if (input.equals("N")) {
	            return false; // El jugador no quiere jugar de nuevo
	        } else {
	            System.out.println("Error: Introduce Y o N");
	        }
	    }
	}
	
	public void win() {
		clear();
		System.out.println("¡Enhorabuena, has ganado!");
		
	}
	
	public void finish() {
		System.out.println("Gracias por jugar.");
	}
	
	public void clear() {
//		System.out.print("\033[H\033[2J");
		System.out.println("\n".repeat(100));
	}

}
