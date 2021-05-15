package algoritmoGenetico.individuos;

import java.util.ArrayList;
import java.util.HashSet;

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
	private HashSet<Pair<Integer, Integer>> setPos;
	
	private int numMaximoPasos;
	private int numPasos;
	
	private final int tamTablero = 32;
	
	private RastroSantaFe santaFe;
	private int[][] tablero;
	
	private boolean printCamino;
	private ArrayList<Pair<Integer, Integer>> camino;
	
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
	
	public ArrayList<Pair<Integer, Integer>> getCamino() {
		
		camino = new ArrayList<Pair<Integer,Integer>>(numMaximoPasos);
		
		this.inicializaFitness();
		camino.add(pos);
		
		this.printCamino = true;
		
		this.calculateFitness();
		
		return camino;
	}
	/********************************* AUXILIARY METHODS *********************************/
	private void inicializaFitness() {
		
		this.numPasos = 0;
		this.fitness = 1;
		this.pos = new Pair<Integer, Integer>(0, 0);
		this.direccion = Direccion.ESTE;
		this.setPos = new HashSet<Pair<Integer,Integer>>();
	}
	
	private boolean hayComida() {
		
		if (this.tablero[pos.getFirst()][pos.getSecond()] ==  1 && !setPos.contains(pos)) {
			
			this.setPos.add(pos);
			return true;
		}
		
		return false;
	}
	
	private boolean calculaSiComida() {
		
		Pair<Integer, Integer> pos;
		
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
			
			pos = new Pair<Integer, Integer>(this.pos.getFirst(), (this.pos.getSecond() + 1)%tamTablero);
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
			
			pos = new Pair<Integer, Integer>(this.pos.getFirst(), (this.pos.getSecond() + 1)%tamTablero);
		}
		
		if (printCamino) {
			
			camino.add(pos);
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
	
	public ArrayList<Operando> copyFenotipe(){
		
		this.calculateFenotipo();
		return this.fenotipo;
	}
	
	public void copyFenotipe(ArrayList<Operando> fenotipo) {
		
		this.fenotipo = new ArrayList<Operando>(fenotipo);
		this.cromosoma = new Arbol(null, this.fenotipo, this.profMaxima);
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
		return this.profMaxima;
	}
	
	public int getFitness() {
		return this.fitness;
	}
	
	public String getInicializacion() {
		return this.metodoIni;
	}

	public void setFitnessRanking(double probOfIth) {		//TODO
		this.fitnessRanking = (int) Math.floor(probOfIth);
		
	}
}