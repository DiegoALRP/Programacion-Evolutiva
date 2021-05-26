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
	
	protected Arbol cromosoma;				//Cromosoma del individuo.
	protected ArrayList<Operando> fenotipo;	//Fenotipo del individuo.
	protected String stringFenotipo;		//Fenotipo del individuo en string.
	
	private int profMaxima;		//Profundidad máxima.
	private String metodoIni;	//Método de inicialización.
	
	private int fitness;		//Fitness del individuo.
	@SuppressWarnings("unused")
	private int fitnessRanking;	//Fitness ranking del individuo.
	
	private Pair pos;				//Posición actual de la hormiga.
	private Direccion direccion;	//Dirección actual de la hormiga.
	private HashSet<Pair> setPos;	//Conjunto de casillas por donde ha pasado la hormiga.
	
	private int numMaximoPasos;		//Número máximo de pasos.
	private int numPasos;			//Número de pasos que lleva actualmente.
	
	private final int tamTablero = 32;	//Tamaño del tablero.
	
	private RastroSantaFe santaFe;		//Rastro de santa fé.
	private int[][] tablero;			//Tablero.
	
	private boolean printCamino;		//Booleano que indica si se imprime o no el camino de la hormiga.
	private ArrayList<Pair> camino;		//Camino de la hormiga
	
	
	
	/****************************************************************************/
	/******************************* CONSTRUCTOR ********************************/
	/****************************************************************************/
	
	/**
	 * Constructora 1.
	 * 
	 * [ES] Esta constructora crea un nuevo individuo desde cero,
	 * es decir, construye un cromosoma aleatorio.
	 * 
	 * [EN] This constructor creates a new individual from cero,
	 * creates a random chromosome.
	 * 
	 * @param metodoIni	[ES] Método de inicialización.
	 * 					[EN] Initialization method.
	 * 
	 * @param profMaxima	[ES] Profundidad máxima.
	 * 						[EN] Maximum depth.
	 * 
	 * @param numPasos		[ES] Número máximo de pasos.
	 * 						[EN] Maximum number of steps.
	 * 
	 * @param santaFe		[ES] Rastro Santa Fe.
	 * 						[EN] Rastro Santa Fe.
	 */
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
	
	
	/**
	 * Constructora 2.
	 * 
	 * [ES] Esta constructora crea un nuevo individuo, con la diferencia
	 * que construye un cromosoma con una estructura específica que es pasada
	 * como parámetro en la construtora.
	 * 
	 * [EN] This constructor creates a new individual, with the diference
	 * that builds a chromosome with a specific structure that is passed as
	 * parameter in the constructor.
	 * 
	 * @param cromosoma	[ES] Array con la estructura del árbol a crear.
	 * 					[EN] Array with the tree's estructure.
	 * 
	 * @param metodoIni	[ES] Método de inicialización.
	 * 					[EN] Initialization method.
	 * 
	 * @param profMaxima	[ES] Profundidad máxima.
	 * 						[EN] Maximum depth.
	 * 
	 * @param numPasos		[ES] Número máximo de pasos.
	 * 						[EN] Maximum number of steps.
	 * 
	 * @param santaFe		[ES] Rastro Santa Fe.
	 * 						[EN] Rastro Santa Fe.
	 */
	public Individuo(ArrayList<Operando> cromosoma, String metodoIni, int profMaxima, int numPasos, RastroSantaFe santaFe) {
		
		this.metodoIni = metodoIni;
		
		this.profMaxima = profMaxima;
		
		this.numMaximoPasos = numPasos;
		
		this.santaFe = santaFe;
		
		this.tablero = santaFe.getTablero();
		this.fitness = 0;
		
		this.fenotipo = new ArrayList<Operando>(cromosoma);
		this.cromosoma = new Arbol(null, new ArrayList<Operando>(cromosoma), this.profMaxima);
		
		this.calculateFitness();
	}
	
	
	
	/***************************************************************************/
	/********************************* METHODS *********************************/
	/***************************************************************************/
	
	/**
	 * [ES] Función que crea un nuevo árbol con una estructura aleatoria.
	 * [EN] Function that creates a new tree with a random structure.
	 */
	private void inicializaCromosoma() {
		
		if (profMaxima == 0) {
			
			cromosoma = new Arbol(null, new Operando(true), profMaxima, metodoIni);
		}
		else {
			
			cromosoma = new Arbol(null, new Operando(false), profMaxima, metodoIni);
		}
	}
	
	/**
	 * [ES] Función que calcula el fenotipo a partir del árbol de cromosoma
	 * [EN] Function that calculates the fenotipe from the tree.
	 * 
	 */
	private void calculateFenotipo() {
		
		fenotipo = new ArrayList<Operando>();
		this.cromosoma.toArrayAux(fenotipo);
	}
	
	/**
	 * [ES] Función que imprime el fenotipo.
	 * [EN] Function that prints the fenotipe.
	 * 
	 * @return	[ES] Devuelve el string del árbol.
	 * 			[EN] Returns the tree's string.
	 */
	public String printFenotipo() {
		
		return cromosoma.arbolToString().toString();
	}
	
	/**
	 * [ES] Función que calcula y devuelve el valor de fitness del individuo.
	 * [EN] Function that caculates and return the fitness value of the individual.
	 * 
	 * @return	[ES] El valor de fitness del individuo.
	 * 			[EN] The fitness value of the individual.
	 */
	public int calculateFitness() {
		
		this.inicializaFitness();
		
		while (numPasos < numMaximoPasos) {
			
			recorreArbol(cromosoma);
		}
		
		return this.fitness;
	}
	
	/**
	 * [ES] Función principal del cálculo del fitness en el que recorre el árbol (cromosoma)
	 * del individuo y va ejecutando sus movimientos según los nodos del árbol.
	 * 
	 * [EN] Fitness's calculation function that iterates the individual's tree (cromosome)
	 * and it executes the movements from the tree's nodes.
	 * 
	 * @param a	[ES] Subárbol que estamos recorriendo.
	 * 			[EN] Subtree the we are iterating.
	 */
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
	
	/**
	 * [ES] Función que devuelve las posiciones del tablero por el cual la hormiga
	 * ha pasado.
	 * 
	 * [EN] Function that return the positions of the board that the ant has traveled.
	 * 
	 * @return	[ES] Devuelve un array con las posiciones.
	 * 			[EN] Return an array with the positions.
	 */
	public ArrayList<Pair> getCamino() {
		
		camino = new ArrayList<Pair>(numMaximoPasos);
		
		this.inicializaFitness();
		camino.add(pos);
		
		this.printCamino = true;
		
		this.calculateFitness();
		
		return camino;
	}
	
	
	/*************************************************************************************/
	/********************************* AUXILIARY METHODS *********************************/
	/*************************************************************************************/
	
	/**
	 * [ES] Función auxiliar que inicializa los parámetros del cáculo del fitness.
	 * 
	 * [EN] Auxiliary function that initializes the parameter of the fitness's calculation.
	 * 
	 */
	private void inicializaFitness() {
		
		this.numPasos = 0;
		this.fitness = 0;
		this.pos = new Pair(0, 0);
		this.direccion = Direccion.ESTE;
		this.setPos = new HashSet<Pair>(100);
		this.setPos.add(pos);
	}
	
	/**
	 * [ES] Función auxiliar que indica si en la casilla en que la está
	 * la hormiga actualmente hay o no comida. Y a su vez verifica de si ha comido
	 * o no la comida que está o estaba en esa posición.
	 * 
	 * [EN] Auxiliary function that indicates if the box in which the ant is actually
	 * has o hasn't food. And in the same time verifies if has eating the food that was
	 * in that box.
	 * 
	 * @return	[ES] True, si hay comida en esa casilla, False en otro caso.
	 * 			[EN] True, if there is food in that box, False otherwise.
	 */
	private boolean hayComida() {
		
		if (this.tablero[pos.getFirst()][pos.getSecond()] ==  1 && !setPos.contains(pos)) {
			
			this.setPos.add(pos);
			return true;
		}
		
		return false;
	}
	
	/**
	 * [ES] Cálculo para el operando "SIComida".
	 * [EN] Calculation for the "SIComida".
	 * 
	 * @return 	[ES] True, si encuentra comida, False en otro caso.
	 * 			[EN] True, if it finds food, False otherwise.
	 */
	private boolean calculaSiComida() {
		
		Pair pos;
		
		if (direccion.equals(Direccion.ESTE)) {
			
			pos = new Pair((this.pos.getFirst() + 1)%tamTablero, this.pos.getSecond());
		}
		else if (direccion.equals(Direccion.OESTE)) {
			
			pos = new Pair(Math.floorMod(this.pos.getFirst() - 1, tamTablero), this.pos.getSecond());
		}
		else if (direccion.equals(Direccion.NORTE)) {
			
			pos = new Pair(this.pos.getFirst(), Math.floorMod(this.pos.getSecond() - 1, tamTablero));
		}
		else {
			
			pos = new Pair(this.pos.getFirst(), (this.pos.getSecond() + 1)%tamTablero);
		}
		
		if (this.tablero[pos.getFirst()][pos.getSecond()] == 1) {
			
			return true;
		}
		return false;
	}
	
	/**
	 * [ES] Función auxiliar que ejecuta el operando.
	 * [EN] Auxiliary function that executes the operator.
	 * 
	 * @param accion	[ES] Acción a ejecutar.
	 * 					[EN] Acction to execute.
	 */
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
	
	/**
	 * [ES] Función Auxilicar que ejecuta el operador "Avanza".
	 * [EN] Auxiliary function that executes the operator "Avanza".
	 * 
	 */
	private void ejecutaAvanza() {
		
		if (direccion.equals(Direccion.ESTE)) {
			
			pos = new Pair((this.pos.getFirst() + 1)%tamTablero, this.pos.getSecond());
		}
		else if (direccion.equals(Direccion.OESTE)) {
			
			pos = new Pair(Math.floorMod(this.pos.getFirst() - 1, tamTablero), this.pos.getSecond());
		}
		else if (direccion.equals(Direccion.NORTE)) {
			
			pos = new Pair(this.pos.getFirst(), Math.floorMod(this.pos.getSecond() - 1, tamTablero));
		}
		else {
			
			pos = new Pair(this.pos.getFirst(), (this.pos.getSecond() + 1)%tamTablero);
		}
		
		if (printCamino) {
			
			camino.add(pos);
		}
	}
	
	/**
	 * [ES] Función auxiliar que ejecuta una acción de cambio de dirección.
	 * [EN] Auxiliary function that executes a direction change.
	 * 
	 * @param accion	[ES] La acción a ejecutar.
	 * 					[EN] The action to execute.
	 */
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
	
	/**
	 * [ES] Función que copia el fenotipo de un individuo.
	 * [EN] Function that copies the fenotipe of the indivual.
	 * 
	 * @return	[ES] El fenotipo del individuo.
	 * 			[EN] The individual fenotipe
	 */
	public ArrayList<Operando> copyFenotipe(){
		
		this.calculateFenotipo();
		return this.fenotipo;
	}
	
	/**
	 * [ES] Función que copia el fenotipo de un individuo.
	 * [EN] Function that copies the fenotipe of the indivual.
	 * 
	 * @param fenotipo	[ES] Fenotipo a copiar.
	 * 					[EN] Fenotipe to copy.
	 */
	public void copyFenotipe(ArrayList<Operando> fenotipo) {
		
		this.fenotipo = new ArrayList<Operando>(fenotipo);
		this.cromosoma = new Arbol(null, this.fenotipo, this.profMaxima);
	}
	
	
	
	/***************************************************************************/
	/********************** AUXILIARY METHODS - Crossover **********************/
	/***************************************************************************/
	public void mutaTerminalSimple() {
		
		this.cromosoma.mutaTerminalSimple();
	}
	
	public void mutaFuncionSimple(double probMutacion) {
		
		this.cromosoma.mutaFuncionSimple(probMutacion, false);
	}
	
	
	
	/***************************************************************************/
	/********************** AUXILIARY METHODS - Bloating ***********************/
	/***************************************************************************/
	public int getNumNodosArbol() {
		
		return this.cromosoma.getNumNodos();
	}
	
	
	/***************************************************************************/
	/**************************** GETTERS & SETTERS ****************************/
	/***************************************************************************/
	public Arbol getCromosoma() {
		
		return this.cromosoma;
	}
	
	public ArrayList<Operando> getRefFenotipe() {
		
		return this.fenotipo;
	}
	
	public int getMaxProf() {
		return this.profMaxima;
	}
	
	public int getMaxPasos() {
		return this.numMaximoPasos;
	}
	
	public int getFitness() {
		return this.fitness;
	}
	
	public void setFitness(int fitness) {
		this.fitness = fitness;
	}
	
	public String getMetodoIni() {
		return this.metodoIni;
	}

	public void setFitnessRanking(double probOfIth) {
		this.fitnessRanking = (int) Math.floor(probOfIth);	
	}
	
	public void setCromosoma(Arbol a) {
		this.cromosoma = a;
	}
	
	public RastroSantaFe getSantaFe() {
		
		return this.santaFe;
	}
	
	public int getTreeSize() {
		return this.cromosoma.getProfundidad();
	}
	
	public int getTreeSizeConst() {
		return this.cromosoma.getProfundidadConst();
	}
}