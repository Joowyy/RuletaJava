package RuletaSuerteNew;

import java.util.ArrayList;
import java.util.Scanner;

public class Jugador {

//	ATRIBUTOS
	private int id;
	private double dinero;
	private ArrayList<Jugador> arrayJugadores = new ArrayList<Jugador>();
	
//	CONSTRUCTORES
	Jugador () {
		
	}
	Jugador (int id, double dinero) {
		this.id = id;
		this.dinero = dinero;
	}
	Jugador (int id, double dinero, ArrayList<Jugador> arrayJugadores) {
		this.id = id;
		this.dinero = dinero;
		this.arrayJugadores = arrayJugadores;
	}
	
//	GETTERS & SETTERS
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public double getDinero() {
		return dinero;
	}
	public void setDinero(double dinero) {
		this.dinero = dinero;
	}
	
	public ArrayList<Jugador> getArrayJugadores() {
		return arrayJugadores;
	}
	public void setArrayJugadores(ArrayList<Jugador> arrayJugadores) {
		this.arrayJugadores = arrayJugadores;
	}
	
//	METODOS
//	public void mostrarJugador () {
//		for (Jugador j : arrayJugadores) {
//        	System.out.println("Comienza jugador " + j.getId());
//        }
//	}
}
