package algoritmoGenetico.individuos;

import java.util.ArrayList;
import java.util.Random;

import misc.Pair;

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
	
	private int fitness;
	private int fitnessRanking;
	private Pair casilla;
	private ArrayList<Pair> comida;
	private ArrayList<Pair> camino;
	private Pair direccion;
	private String orientacionActual;
	private static final int numPasos = 400;
	
	private final int xSize = 31;
	private final int ySize = 31;
	
	/******************************* CONSTRUCTOR ********************************/
	public Individuo(String metodoIni, int profundidad, ArrayList<Pair> comida) {
		
		this.metodoIni = metodoIni;
		this.profundidad = profundidad;
		this.comida = new ArrayList<Pair>(comida);
		this.camino = new ArrayList<Pair>();
		this.casilla = new Pair(0, 0);
		this.direccion = new Pair(0, 1);
		this.orientacionActual = "ESTE";
		this.fitness = 0;
		
		this.camino.add(new Pair(this.casilla.get_x() + 1, this.casilla.get_y() + 1));
		
		this.inicializaCromosoma();
	}
	
	public Individuo(ArrayList<Operando> fenotipo, int profundidad, ArrayList<Pair> comida) {
		
		this.profundidad = profundidad;
		this.comida = comida;
		this.camino = new ArrayList<Pair>();
		this.casilla = new Pair(0, 0);
		this.direccion = new Pair(0, 1);
		this.orientacionActual = "ESTE";
		
		this.fenotipo = new ArrayList<Operando>(fenotipo);
		this.cromosoma = new Arbol(null, this.fenotipo, this.profundidad);
		
		this.calculateFitness();
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
	
	public int calculateFitness() {
		
		this.fitness = 0;
		int pivote = 0;
		ArrayList<String> terminales = this.getTerminales();
		for(int i = 0; i < Individuo.numPasos; i++) {
			
			for(int j = 0; j < this.comida.size(); j++) {
				if(this.comida.get(j).equals(new Pair(this.casilla.get_x() + 1, this.casilla.get_y() + 1))) {

					fitness++;
					this.comida.remove(j);
					break;
				}
			}
			
			String accion = terminales.get(pivote % terminales.size());
			this.casilla = ejecutaAccion(accion);
			
			this.camino.add(new Pair(this.casilla.get_x() + 1, this.casilla.get_y() + 1));
			/*System.out.println("Casilla X : " + this.camino.get(i).get_x());
			System.out.println("Casilla Y : " + this.camino.get(i).get_y());
			System.out.println("Nueva orientacion: " + this.orientacionActual);
			System.out.println("Accion: " + accion);
			System.out.println("---");*/
			
			pivote++;
		}
		return this.fitness;
	}
	
	
	/********************************* AUXILIARY METHODS *********************************/
	/*
	 * 	Recorrido circular sobre los teminales del individuo
	 */
	private String siguienteAccion(ArrayList<String> terminales, int pivote) {
		
		
		if(pivote  == terminales.size()) {
			pivote = 0;
		}
		return terminales.get(pivote);
		
	}
	
	private Pair ejecutaAccion(String accion) {
		
		if((this.orientacionActual == "OESTE" && accion == "IZQUIERDA") || (this.orientacionActual == "ESTE" && accion == "DERECHA")) {
			this.direccion = new Pair(0, 1);
			this.orientacionActual = "SUR";
			
		} else if((this.orientacionActual == "NORTE" && accion == "IZQUIERDA") || (this.orientacionActual == "SUR" && accion == "DERECHA")) {
			this.direccion = new Pair(-1, 0);
			this.orientacionActual = "OESTE";
			
		} else if((this.orientacionActual == "ESTE" && accion == "IZQUIERDA") || (this.orientacionActual == "OESTE" && accion == "DERECHA")) {
			this.direccion = new Pair(-0, -1);
			this.orientacionActual = "NORTE";
			
		} else if((this.orientacionActual == "SUR" && accion == "IZQUIERDA") || (this.orientacionActual == "NORTE" && accion == "DERECHA")) {
			this.direccion = new Pair(1, 0);
			this.orientacionActual = "ESTE";
		}
		
		Pair casillaActual = new Pair(this.casilla.get_y(),this.casilla.get_x());
		
		if(accion == "AVANZA") {
			casillaActual = new Pair(this.casilla.get_x() + this.direccion.get_x(), this.casilla.get_y() + this.direccion.get_y());
		
			if(casillaActual.get_x() < 0) {						//casilla x
				casillaActual.set_x(this.xSize);
				
			}else if(casillaActual.get_x() > this.xSize) {		//casilla x
				casillaActual.set_x(0);
				
			}else if(casillaActual.get_y() < 0) {				//casilla y
					casillaActual.set_y(this.ySize);
					
			}else if(casillaActual.get_y() > this.ySize) {		//casilla y
				casillaActual.set_y(0);
			}
		}
		
		return casillaActual;
	}
	public ArrayList<Operando> copyFenotipe(){
		
		this.calculateFenotipo();
		return this.fenotipo;
	}
	
	public void copyFenotipe(ArrayList<Operando> fenotipo) {
		
		this.fenotipo = new ArrayList<Operando>(fenotipo);
		this.cromosoma = new Arbol(null, this.fenotipo, this.profundidad);
	}
	
	public ArrayList<String> getTerminales(){
		
		ArrayList<String> terminales = new ArrayList<String>();
		this.cromosoma.getTerminales(this.cromosoma.getHijos(), terminales);
		return terminales;
	}
	
	/********************** AUXILIARY METHODS - Crossover **********************/
	public void mutaTerminalSimple() {
		
		this.cromosoma.mutaTerminalSimple();
	}
	
	public void mutaFuncionSimple(double probMutacion) {
		
		this.cromosoma.mutaFuncionSimple(probMutacion, false);
	}
	
	/**************************** GETTERS & SETTERS ****************************/
	public Arbol getCromosoma() {
		
		return this.cromosoma;
	}
	
	public ArrayList<Operando> getRefFenotipe() {
		
		return this.fenotipo;
	}
	
	public int getMaxProf() {
		return this.profundidad;
	}
	
	public int getFitness() {
		return this.fitness;
	}
	
	public ArrayList<Pair> getCamino(){
		return this.camino;
	}
	
	public String getInicializacion() {
		return this.metodoIni;
	}
	public ArrayList<Pair> getComida() {
		return this.comida;
	}

	public void setFitnessRanking(double probOfIth) {		//TODO
		this.fitnessRanking = (int) Math.floor(probOfIth);
		
	}
	
}
