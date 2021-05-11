package algoritmoGenetico.individuos;

import java.util.ArrayList;

/**
 * Universidad Complutense de Madrid.
 * Programación Evolutiva.
 * Grupo A 2021.
 * Profesor:
 * 	-Carlos Cervigon Ruckauer.
 * 
 * Práctica 3
 * 
 * Clase Individuo.
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodríguez Pereira.
 *
 */
public class Individuo {
	
	/******************************** ATRIBUTTES ********************************/
	protected Arbol cromosoma;
	protected ArrayList<Operando> fenotipo;
	protected String stringFenotipo;
	
	private int profundidad;
	private String metodoIni;
	
	/******************************* CONSTRUCTOR ********************************/
	public Individuo(String metodoIni, int profundidad) {
		
		this.metodoIni = metodoIni;
		this.profundidad = profundidad;
		
		this.inicializaCromosoma();
	}
	
	/********************************* METHODS *********************************/
	private void inicializaCromosoma() {
		
		if (profundidad == 0) {
			
			cromosoma = new Arbol(null, new Operando(true), profundidad);
		}
		else {
			
			cromosoma = new Arbol(null, new Operando(false), profundidad);
		}
		
		if (metodoIni.equals("Completo")) {
			
			this.cromosoma.inicializaCompleto();
		}
	}
	
	private void calculateFenotipo() {
		
		fenotipo = new ArrayList<Operando>();
		this.cromosoma.toArrayAux(fenotipo);
	}
	
	public String printFenotipo() {
		
		return cromosoma.arbolToString().toString();
	}
	
	/********************************* AUXILIARY METHODS *********************************/
	public ArrayList<Operando> copyFenotipe(){
		
		this.calculateFenotipo();
		return this.fenotipo;
	}
	
	public void copyFenotipe(ArrayList<Operando> fenotipo) {
		
		this.fenotipo = new ArrayList<Operando>(fenotipo);
		this.cromosoma = new Arbol(null, this.fenotipo, this.profundidad);
	}
	
	/**************************** GETTERS & SETTERS ****************************/
	public int getMaxProf() {
		
		return this.profundidad;
	}
	
}
