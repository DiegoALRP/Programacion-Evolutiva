package algoritmoGenetico.individuos;

import java.util.ArrayList;

import com.sun.org.apache.bcel.internal.generic.D2I;

import algoritmoGenetico.misc.Pair;

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
	
	/****************************************************************************/
	/******************************** ATRIBUTTES ********************************/
	/****************************************************************************/
	
	protected Arbol cromosoma;
	protected ArrayList<Operando> fenotipo;
	protected String stringFenotipo;
	
	private int profMaxima;
	private String metodoIni;
	
	private int fitness;
	private int fitnessRanking;
	
	private Pair<Integer, Integer> pos;
	private Direccion direccion;
	
	private int numMaximoPasos;
	private int numPasos;
	
	private final int tamTablero = 32;
	
	private RastroSantaFe santaFe;
	private int[][] tablero;
	
	
	/****************************************************************************/
	/******************************* CONSTRUCTOR ********************************/
	/****************************************************************************/
	
	public Individuo(String metodoIni, int profMaxima, int numPasos, RastroSantaFe santaFe) {
		
		this.metodoIni = metodoIni;
		
		this.profMaxima = profMaxima;
		
		this.numMaximoPasos = numPasos;
		
		this.santaFe = santaFe;
		
		this.tablero = santaFe.getTablero();
		this.fitness = 0;
		
		this.inicializaCromosoma();
		this.calculateFenotipo();
		this.calculateFitness();
	}
	
	public Individuo(ArrayList<Operando> fenotipo, String metodoIni, int profMaxima, int numPasos, RastroSantaFe santaFe) {
		
		this.metodoIni = metodoIni;
		
		this.profMaxima = profMaxima;
		
		this.numMaximoPasos = numPasos;
		
		this.santaFe = santaFe;
		
		this.tablero = santaFe.getTablero();
		this.fitness = 0;
		
		this.fenotipo = new ArrayList<Operando>(fenotipo);
		this.cromosoma = new Arbol(null, this.fenotipo, this.profMaxima);
		
		this.calculateFitness();
	}
	
	
	
	/***************************************************************************/
	/********************************* METHODS *********************************/
	/***************************************************************************/
	
	private void inicializaCromosoma() {
		
		if (profMaxima == 0) {
			
			cromosoma = new Arbol(null, new Operando(true), profMaxima);
		}
		else {
			
			cromosoma = new Arbol(null, new Operando(false), profMaxima);
		}
		
		cromosoma.inicializaArbol(metodoIni);
	}
	
	private void calculateFenotipo() {
		
		fenotipo = new ArrayList<Operando>();
		this.cromosoma.toArrayAux(fenotipo);
	}
	
	public String printFenotipo() {
		
		return cromosoma.arbolToString().toString();
	}
	
	public int calculateFitness() {
		
		this.inicializaFitness();
		
		while (numPasos < numMaximoPasos) {
			
			recorreArbol(cromosoma);
		}
		
		return this.fitness;
	}
	
	private void recorreArbol(Arbol a) {
		
		if (numPasos < numMaximoPasos) {
			
			Operando raiz = a.getRaiz();
			if (raiz.isFunction()) {
				
				if (raiz.equalsSiComida()) {
					
					if (calculaSiComida()) {
						
						recorreArbol(a.getHijos().get(0));
					}
					else {
						
						recorreArbol(a.getHijos().get(1));
					}
				}
				else {
					
					int numHijos = a.getNumHijos();
					for (int i = 0; i < numHijos; i++) {
						
						recorreArbol(a.getHijos().get(i));
					}
				}
			}
			else {
				
				ejecutaAccion(raiz);
			}
		}
	}
	/********************************* AUXILIARY METHODS *********************************/
	private void inicializaFitness() {
		
		this.numPasos = 0;
		this.fitness = 1;
		this.pos = new Pair<Integer, Integer>(0, 0);
		this.direccion = Direccion.ESTE;
	}
	
	private boolean hayComida() {
		
		if (this.tablero[pos.getFirst()][pos.getSecond()] ==  1) {
			
			return true;
		}
		
		return false;
	}
	
	private boolean calculaSiComida() {
		
		Pair<Integer, Integer> pos;
		
		if (direccion.equals(Direccion.ESTE)) {
			
			pos = new Pair<Integer, Integer>(this.pos.getFirst() + 1, this.pos.getSecond());
		}
		else if (direccion.equals(Direccion.OESTE)) {
			
			pos = new Pair<Integer, Integer>(Math.floorMod(this.pos.getFirst() - 1, tamTablero), this.pos.getSecond());
		}
		else if (direccion.equals(Direccion.NORTE)) {
			
			pos = new Pair<Integer, Integer>(this.pos.getFirst(), Math.floorMod(this.pos.getSecond() - 1, tamTablero));
		}
		else {
			
			pos = new Pair<Integer, Integer>(this.pos.getFirst(), this.pos.getSecond() + 1);
		}
		
		if (this.tablero[pos.getFirst()][pos.getSecond()] == 1) {
			
			return true;
		}
		return false;
	}
	
	private void ejecutaAccion(Operando accion) {
		
		if (accion.equalsAvanza()) {
			
			this.ejecutaAvanza();
			
			if (hayComida()) fitness++;
		}
		else {
			
			this.ejecutaCambioDireccion(accion);
		}
		
		this.numPasos++;
	}
	
	private void ejecutaAvanza() {
		
		if (direccion.equals(Direccion.ESTE)) {
			
			pos = new Pair<Integer, Integer>((this.pos.getFirst() + 1)%tamTablero, this.pos.getSecond());
		}
		else if (direccion.equals(Direccion.OESTE)) {
			
			pos = new Pair<Integer, Integer>(Math.floorMod(this.pos.getFirst() - 1, tamTablero), this.pos.getSecond());
		}
		else if (direccion.equals(Direccion.NORTE)) {
			
			pos = new Pair<Integer, Integer>(this.pos.getFirst(), Math.floorMod(this.pos.getSecond() - 1, tamTablero));
		}
		else {
			
			pos = new Pair<Integer, Integer>(this.pos.getFirst(), this.pos.getSecond() + 1);
		}
	}
	
	private void ejecutaCambioDireccion(Operando accion) {
		
		if ((direccion.equals(Direccion.ESTE) && accion.equalsIzquierda()) || (direccion.equals(Direccion.OESTE) && accion.equalsDerecha())) {
			
			this.direccion = Direccion.NORTE;
		}
		else if ((direccion.equals(Direccion.OESTE) && accion.equalsIzquierda()) || (direccion.equals(Direccion.ESTE) && accion.equalsDerecha())) {
			
			this.direccion = Direccion.SUR;
		}
		else if ((direccion.equals(Direccion.SUR) && accion.equalsIzquierda()) || (direccion.equals(Direccion.NORTE) && accion.equalsDerecha())) {
			
			this.direccion = Direccion.ESTE;
		}
		else {
			
			this.direccion = Direccion.OESTE;
		}
	}
	
	/*private Pair ejecutaAccion(String accion) {
		
		if((this.orientacionActual == "OESTE" && accion == "IZQUIERDA") || (this.orientacionActual == "ESTE" && accion == "DERECHA")) {
			this.direccion = new Pair(1, 0);
			this.orientacionActual = "SUR";
			
		} else if((this.orientacionActual == "NORTE" && accion == "IZQUIERDA") || (this.orientacionActual == "SUR" && accion == "DERECHA")) {
			this.direccion = new Pair(0, -1);
			this.orientacionActual = "OESTE";
			
		} else if((this.orientacionActual == "ESTE" && accion == "IZQUIERDA") || (this.orientacionActual == "OESTE" && accion == "DERECHA")) {
			this.direccion = new Pair(-1, 0);
			this.orientacionActual = "NORTE";
			
		} else if((this.orientacionActual == "SUR" && accion == "IZQUIERDA") || (this.orientacionActual == "NORTE" && accion == "DERECHA")) {
			this.direccion = new Pair(0, 1);
			this.orientacionActual = "ESTE";
		}
		
		Pair casillaActual = new Pair(this.casilla.get_x(),this.casilla.get_y());
		
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
	}*/
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
		
		/*
		terminales.add("AVANZA");
		terminales.add("DERECHA");
		terminales.add("IZQUIERDA");
		terminales.add("DERECHA");
		terminales.add("IZQUIERDA");
		terminales.add("DERECHA");
		terminales.add("DERECHA");
		terminales.add("DERECHA");
		terminales.add("DERECHA");
		terminales.add("AVANZA");
		*/
		
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