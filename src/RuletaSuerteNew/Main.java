package RuletaSuerteNew;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
	private static ArrayList<Jugador> jugadores = new ArrayList<>();
	private static int jugadorActual = 0;
	private static final double QUIEBRA = -1;
	
	public static void main(String[] args) {
//		CINCO PANELES EN TOTAL
		for (int i = 0; i < 5; i ++) {
			Scanner sc = new Scanner (System.in);
			
			crearJugadores();
			
			System.out.println("¿Frase clave de la ruleta?");
			String fraseRuleta = sc.nextLine();
			System.out.println("¿Me dices una pista para ayudar a los jugadores?");
			String pistaRuleta = sc.nextLine();
			
			tiradaOletra(fraseRuleta, pistaRuleta);
		}
	}

	private static void crearJugadores() {
	        Scanner sc = new Scanner(System.in);
	        int numJugadores;
	        
	        do {
	            System.out.println("¿Cuántos jugadores van a participar? (1-3)");
	            numJugadores = sc.nextInt();
	        } while (numJugadores < 1 || numJugadores > 3);

//	        AÑADE EL ID A CADA JUGADOR Y LO AÑADE A LA ARRAYLIST
	        for (int i = 0; i < numJugadores; i++) {
	            jugadores.add(new Jugador(i + 1, 0.0));
	        }
	}
	private static void siguienteJugador() {
        jugadorActual = (jugadorActual + 1) % jugadores.size();
        
//      RECOGE EL ID DEL NUMERO DE JUGADORES ASIGNADOS ANTERIORMENTE
        System.out.println("\nTurno del jugador: " + jugadores.get(jugadorActual).getId());
    }
	
	public static double numRandom () {
		Random rnd = new Random ();
		double valorRuleta = 0;
		int numRandom = 0;
		
		
//		HEMOS METIDO UN VALOR EXTRA PARA QUE QUEDE BONITO
		switch (numRandom = rnd.nextInt(1, 9)) {
		case 1:
			valorRuleta = 0;
			break;
		case 2:
			valorRuleta = 25;
			break;
		case 3:
			valorRuleta = 50;
			break;
		case 4:
			valorRuleta = 75;
			break;
		case 5:
			valorRuleta = 100;
			break;
		case 6:
			valorRuleta = 150;
			break;
		case 7:
			valorRuleta = 200;
			break;
		case 8:
//			PIERDE TURNO
			valorRuleta = QUIEBRA;
			break;
		}
		
//		CADA VEZ QUE CAIGA EN QUIEBRA, CAMBIO DE TURNO Y DINERO 0
		if (valorRuleta == QUIEBRA) {
            System.out.println("¡Quiebra! Pierdes todo el dinero y el turno");
            jugadores.get(jugadorActual).setDinero(0);
            siguienteJugador();
            System.out.println("Dinero actual: $" + jugadores.get(jugadorActual).getDinero());
        }
		System.out.println("Resultado de la ruleta: " + valorRuleta);
		return valorRuleta;
	}
	
	public static void imprimirRuleta () {
		
//		HEMOS METIDO UN VALOR EXTRA PARA QUE QUEDE BONITO
		System.out.print("         _____________________");
		System.out.println("\n         |RULETA DE LA SUERTE|    ");
		System.out.println("         | jowy  <->  franky |     ");
        System.out.println("         *********************");
        System.out.println("        **                   **");
        System.out.println("       **       quiebra       **");
        System.out.println("      **                       **");
        System.out.println("     **   200               0   **");
        System.out.println("    **                           **");
        System.out.println("   **                             **");
        System.out.println("  **   150         ❌         25   **");
        System.out.println("  **                               **");
        System.out.println("   **                             **");
        System.out.println("    **                           **");
        System.out.println("     **   100              50   **");
        System.out.println("      **                       **");
        System.out.println("       **         75          **");
        System.out.println("        **                   **");
        System.out.println("         *********************");
        System.out.println();

	}

	public static void tiradaOletra (String fraseRuleta, String pistaRuleta) {
		Scanner sc = new Scanner (System.in);
		char opc = 0;

//		CREAMOS STRING BUILDER PARA UTILIZAR MAS TARDE EL '.setChatAt' SERVIRA PARA REEMPLAZAR LAS LETRAS
		String fraseAster = fraseRuleta.replaceAll("[^ ]", "*");
        StringBuilder fraseRuletaAster = new StringBuilder(fraseAster);
		
		boolean resuelto = false;
		
		do {
			System.out.println("\nJugador " + jugadores.get(jugadorActual).getId() + " elige opción: ");
			System.out.println("-- ACCIONES --\n1. Elegir letra\n2. Resolver panel");
			switch (opc = sc.nextLine().charAt(0)) {
			case '1':
				imprimirRuleta();
				double valorRuleta = numRandom();
				
//				IMPRIMIMOS PANELES
				System.out.println("\nPRIMER PANEL: " + "\t\t\t\tPISTA PANEL:");
                System.out.println(fraseRuletaAster + "\t\t\t" + pistaRuleta);
                if (valorRuleta != QUIEBRA) {
                	adivinarLetra(fraseRuleta, fraseRuletaAster, valorRuleta);
                }
				break;
			case '2':
				System.out.println("\nPRIMER PANEL: " + "\t\t\t\tPISTA PANEL:");
                System.out.println(fraseRuletaAster + "\t\t\t" + pistaRuleta);
                System.out.println("Introduce la frase completa:");
                String respuesta1 = sc.nextLine();

//              COMPARAMOS LA RESPUESTA CON LA FRASE REAL
                if (respuesta1.equalsIgnoreCase(fraseRuleta)) {
                    System.out.println("¡Correcto! Has ganado.");
                    resuelto = true;
                } else {
                
//                	CAMBIO DE JUGADOR AL FALLAR PANEL
                    System.out.println("Respuesta incorrecta. Cambiamos de jugador");
                    siguienteJugador();
                    System.out.println("Dinero actual: $" + jugadores.get(jugadorActual).getDinero());
                }
                break;
            default:
            	System.out.println("Carácter inválido");
			}
		} while (resuelto != true);
	}
	public static void adivinarLetra(String fraseRuleta, StringBuilder fraseRuletaAster, double valorRuleta) {
		Scanner sc = new Scanner (System.in);
		
		System.out.println("\nIntroduce una letra:");
		char letra = sc.nextLine().toLowerCase().charAt(0);

//		COMPRUEBA SI LA LETRA ESTÁ EN LA FRASE
		if (fraseRuleta.toLowerCase().contains(String.valueOf(letra))) {
			System.out.println("¡La letra está en la frase!");
			jugadores.get(jugadorActual).setDinero(jugadores.get(jugadorActual).getDinero() + valorRuleta);
			for (int i = 0; i < fraseRuleta.length(); i++) {
				if (Character.toLowerCase(fraseRuleta.charAt(i)) == letra) {
					
//					FUNCION QUE SOLO SE PUEDE CON UN STRING BUILDER
					fraseRuletaAster.setCharAt(i, fraseRuleta.charAt(i));
				}
			}
		} else {
			System.out.println("La letra no está en la frase.");
			siguienteJugador();
		}
		
//		FRASE ACTUAL SIN ASTERISCOS EN LAS LETRAS DESTAPADAS
	    System.out.println("Frase actual: " + fraseRuletaAster);
	    System.out.println("Dinero actual: $" + jugadores.get(jugadorActual).getDinero());

	}
}
