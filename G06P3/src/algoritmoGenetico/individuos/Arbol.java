package algoritmoGenetico.individuos;

import java.util.ArrayList;
import java.util.Random;

/**
 * Universidad Complutense de Madrid.
 * Programación Evolutiva.
 * Grupo A 2021.
 * Profesor:
 * 	-Carlos Cervigon Ruckauer.
 * 
 * Práctica 3
 * 
 * Clase Arbol.
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodríguez Pereira.
 *
 */
public class Arbol {

	
	/****************************************************************************/
	/******************************** ATRIBUTTES ********************************/
	/****************************************************************************/
	private Arbol padre;				//Puntero al nodo padre.
	private Operando raiz;				//Raíz del subárbol
	private ArrayList<Arbol> hijos;		//Array que contiene los hijos del subárbol
	private int numHijos;				//Número de hijos (directos) que contiene
	private int numNodos;				//Número de nodos que contiene el árbol	
	private int max_prof;				//Profundidad máxima
	private int profundidad;			//Profundidad total
	private boolean useIF;				//useIF
	private boolean esHoja;				//Booleano que nos indica si el nodo es hoja o no
	private boolean esRaiz;				//Booleano que nos indica si el nodo es raíz del árbol o no
	private String metodoIni;			//Método de Inicialización

	
	/****************************************************************************/
	/******************************* CONSTRUCTOR ********************************/
	/****************************************************************************/
	
	/**
	 * Constructora 1
	 * 
	 * @param padre	[ES] Puntero al nodo padre (si es raíz del árbol no tiene puntero).
	 * 				[EN] Father's node pointer (doesn't have pointer if it is tree's root).
	 * 
	 * @param raiz	[ES] Nodo raíz del subárbol.
	 * 				[ES] Subtree's root.
	 * 
	 * @param max_prof	[ES] Máxima profundidad (restante) del subárbol.
	 * 					[EN] Subtree's maximum depth (remaining).
	 */
	public Arbol(Arbol padre, Operando raiz, int max_prof, String metodoIni) {
		
		if (padre == null) {
			this.esRaiz = true;
		}
		this.padre = padre;
		this.raiz = raiz;
		this.max_prof = max_prof;
		this.metodoIni = metodoIni;
		hijos = new ArrayList<Arbol>(this.numHijos);
		
		if (this.raiz.equalsProgN2() || this.raiz.equalsSiComida()) {
			
			this.numHijos = 2;
			this.profundidad = 1;
			this.esHoja = false;
		}
		else if (this.raiz.equalsProgN3()) {
			
			this.numHijos = 3;
			this.profundidad = 1;
			this.esHoja = false;
		}
		else {
			
			this.numHijos = 0;
			this.profundidad = 0;
			this.esHoja = true;
		}
		
		if (raiz.isFunction()) {
			this.inicializaArbol(metodoIni);
		}
	}
	
	/**
	 * Constructora 2
	 * (Recibe como parametro de entrada el arbol a construir)
	 * 
	 * @param padre	[ES] Puntero al nodo padre (si es raíz del árbol no tiene puntero).
	 * 				[EN] Father's node pointer (doesn't have pointer if it is tree's root).
	 * 
	 * @param treeArray [ES] Array que contiene la estructura del árbol que vamos a construir.
	 * 					[EN] Array that contains the tree's structure that we are going to build.
	 * 
	 * @param max_prof	[ES] Máxima profundidad (restante) del subárbol.
	 * 					[EN] Subtree's maximum depth (remaining).
	 */
	public Arbol(Arbol padre, ArrayList<Operando> treeArray, int max_prof) {
		
		this.padre = padre;
		this.raiz = treeArray.get(0);
		treeArray.remove(0);
		this.max_prof = max_prof;
		
		if (this.raiz.equalsProgN2() || this.raiz.equalsSiComida()) {
			
			this.numHijos = 2;
			this.profundidad = 1;
			this.esHoja = false;
		}
		else if (this.raiz.equalsProgN3()) {
			
			this.numHijos = 3;
			this.profundidad = 1;
			this.esHoja = false;
		}
		else {
			
			this.numHijos = 0;
			this.profundidad = 0;
			this.esHoja = true;
		}
		
		if (raiz.isFunction()) {
		
			hijos = new ArrayList<Arbol>();
			for (int i = 0; i < this.numHijos; i++) {
				
				Arbol a = new Arbol(this, treeArray, max_prof - 1);
				hijos.add(a);
			}
		}
	}
	
	
	
	/***************************************************************************/
	/********************************* METHODS *********************************/
	/***************************************************************************/
	
	/**
	 * [ES] Inserta un operando en el árbol.
	 * [EN] Inserts an operand in the tree.
	 * 
	 * @param raiz	[ES] Operando que queremos añadir.
	 * 				[EN] Operand that we want to add.
	 */
	public void insert(Operando raiz) {
		
		boolean encontrado = false;
		
		int menorHijo = 0;
		int minHihjos = Integer.MAX_VALUE;
		for (int i = 0; i < this.numHijos && !encontrado; i++) {
			
			if (this.hijos.get(i).raiz.isTerminal()) {
				
				Arbol a = new Arbol(this, raiz, this.max_prof - 1, this.metodoIni);
				this.hijos.set(i, a);
				encontrado = true;
			}
			else {
				
				if (this.hijos.get(i).numHijos < minHihjos) {
					
					menorHijo = i;
				}
			}
		}
		
		if (!encontrado) {
			
			this.numHijos += 2;
			this.profundidad += 1;
			
			if (raiz.equalsProgN3()) {
				
				this.numHijos++;
			}
			this.hijos.get(menorHijo).insert(raiz);
		}
	}
	
	/**
	 * [ES] Método que nos devuelve un subárbol del árbol principal.
	 * (Se utiliza principalmente para la función de cruce).
	 * 
	 * [EN] Method that returns a subtree of the main tree.
	 * (Mainly used in the crossover function).
	 * 
	 * @param probCruce	[ES] Probabilidad de coger un subárbol.
	 * 					[EN] Probability of get a subtree.
	 * 
	 * @return	[ES] Un subárbol del árbol principal.
	 * 			[EN] A subtree of the main tree.
	 */
	public Arbol getSubTree(double probCruce) {
	
		Random rand = new Random();
		Arbol hijo = this.hijos.get(rand.nextInt(numHijos)).getSubTreeAux(probCruce);
		//System.out.println("Hijo: " + hijo.arbolToString());
		//System.out.println("Padre: " + this.arbolToString());
		return hijo;
	}
	
	/**
	 * [ES] Método auxiliar que nos devuelve un subárbol.
	 * 
	 * [EN] Method that returns a subtree.
	 * 
	 * @param probCruce	[ES] Probabilidad de coger un subárbol.
	 * 					[EN] Probability of get a subtree.
	 * 
	 * @return	[ES] Un subárbol.
	 * 			[EN] A subtree.
	 */
	public Arbol getSubTreeAux(double probCruce) {
		
		Random rand = new Random();
		double random = rand.nextDouble();
		
		if(this.raiz.isTerminal() || this.esRaiz) {
			probCruce = 1 - probCruce;
		}
		
		if (random < probCruce*this.profundidad) {
			
			return this;
		}
		else {
			
			if (this.numHijos < 1) {
				
				return this;
				//return this.padre;
			}
			else {
				
				return this.hijos.get(rand.nextInt(this.numHijos)).getSubTreeAux(probCruce);
			}
		}
	}
	
	public int getIndex(Operando op) {
		
		for (int i = 0; i < this.numHijos; i++) {
			
			if (hijos.get(i).getRaiz().equals(op)) {
				
				return i;
			}
		}
		return 0;
	}
	
	public void insertNewTree(Arbol a, int index) {
		
		hijos.set(index, a);
	}
	
	
	
	/***************************************************************************/
	/**************************** AUXILIARY METHODS ****************************/
	/***************************************************************************/
	public void toArrayAux(ArrayList<Operando> array) {
		
		array.add(this.getRaiz());
		
		if(this.hijos != null) {
			for(int i = 0; i < this.hijos.size(); i++){
				this.hijos.get(i).toArrayAux(array);
			}
		}
	}
	
	public void inicializaArbol(String metodo) {
		
		if (metodo.equals("Completo")) {
			
			this.inicializaCompleto();
		}
		else if (metodo.equalsIgnoreCase("Creciente")) {
			
			this.inicializaCreciente();
		}
	}
	
	public void cambiaArbol(String metodo) {
		
		this.metodoIni = metodo;
		if (metodo.equalsIgnoreCase("Completo")) {
			
			this.cambiaArbolCompleto();
		}
		else if (metodo.equalsIgnoreCase("Creciente")) {
			
			this.cambiaArbolCompleto();
		}
	}
	
	private void cambiaArbolCompleto() {
		
		System.out.println("Cambia Arbol");
		if (this.max_prof > 1) {
			
			this.hijos = new ArrayList<Arbol>(numHijos);
			for (int i = 0; i < numHijos; i++) {
				
				Operando op = new Operando(false);
				Arbol a = new Arbol(this, op, max_prof - 1, this.metodoIni);
				//a.inicializaCompleto();
				hijos.add(a);
			}
		}
		else {
			
			if (this.numHijos > 1) hijos.clear();
			for (int i = 0; i < numHijos; i++) {
				
				Operando op = new Operando(true);
				Arbol a = new Arbol(this, op, max_prof - 1, this.metodoIni);
				hijos.add(a);
			}
			
		}
	}
	
	protected void inicializaCompleto() {
		
		if (this.max_prof > 1) {
			
			//if (this.hijos != null) hijos.clear();
			for (int i = 0; i < numHijos; i++) {
				
				Operando op = new Operando(false);
				Arbol a = new Arbol(this, op, max_prof - 1, this.metodoIni);
				//a.inicializaCompleto();
				hijos.add(a);
			}
		}
		else {
			
			//if (this.hijos != null) hijos.clear();
			for (int i = 0; i < numHijos; i++) {
				
				Operando op = new Operando(true);
				Arbol a = new Arbol(this, op, max_prof - 1, metodoIni);
				hijos.add(a);
			}
			
		}
	}
	
	protected void inicializaCreciente() {
		
		if (this.max_prof > 1) {
			
			for (int i = 0; i < numHijos; i++) {
				
				Operando op = new Operando(); //Operando Aleatorio
				Arbol a = new Arbol(this, op, max_prof - 1, "Creciente");
				hijos.add(a);
			}
		}
		else {
			
			for (int i = 0; i < numHijos; i++) {
				
				Operando op = new Operando(true);
				Arbol a = new Arbol(this, op, max_prof - 1, "Creciente");
				hijos.add(a);
			}
		}
	}
	
	public StringBuilder arbolToString() {
		
		StringBuilder sb = new StringBuilder();
		sb.append(raiz.getOperando());
		
		if (raiz.isFunction()) {
			
			sb.append("( ");
			for (int i = 0; i < numHijos; i++) {
				
				sb.append(hijos.get(i).arbolToString());
				sb.append(", ");
			}
			int in = sb.length();
			sb.deleteCharAt(in - 1);
			sb.deleteCharAt(in - 2);
			sb.append(")");
		}
		
		return sb;
	}
	
	
	
	
	/***************************************************************************/
	/********************** AUXILIARY METHODS - Crossover **********************/
	/***************************************************************************/
	public void mutaTerminalSimple() {
		
		if (this.raiz.isTerminal()) {
			
			this.raiz = new Operando(true, this.raiz.toString());
		}
		else {
			
			Random rand = new Random();
			hijos.get(rand.nextInt(numHijos)).mutaTerminalSimple();
		}
	}
	
	public void mutaFuncionSimple(double probMutacion, boolean up) {
		
		if (this.raiz.isTerminal()) {
			
			this.padre.mutaFuncionSimple(probMutacion, true);
		}
		else {
			
			if (up) {
				
				boolean encontrado = false;
				for (int i = 0; i < numHijos && !encontrado; i++) {
					
					if (this.raiz.equalsProgN2()) {
						
						this.raiz = new Operando("SIComida");
						encontrado = true;
					}
					else if (this.raiz.equalsSiComida()) {
						
						this.raiz = new Operando("PROGN2");
						encontrado = true;
					}
				}
				
				if (!encontrado && this.padre != null) {
					
					this.padre.mutaFuncionSimple(probMutacion, true);
				}
			}
			else {
				
				Random rand = new Random();
				if (this.raiz.equalsProgN3() || (rand.nextDouble() > probMutacion)) {
					
					this.hijos.get(rand.nextInt(numHijos)).mutaFuncionSimple(probMutacion, up);
				}
				else {
					
					if (this.raiz.equalsProgN2()) {
						
						this.raiz = new Operando("SIComida");
					}
					else if (this.raiz.equalsSiComida()) {
						
						this.raiz = new Operando("PROGN2");
					}
				}
			}
		}
	}
	
	public void expandeNodo() {
		
		this.raiz = new Operando(false);
		this.max_prof += 2;
		this.metodoIni = "Completo";
		hijos = new ArrayList<Arbol>(this.numHijos);
		
		if (this.raiz.equalsProgN2() || this.raiz.equalsSiComida()) {
			
			this.numHijos = 2;
			this.profundidad = 1;
			this.esHoja = false;
		}
		else if (this.raiz.equalsProgN3()) {
			
			this.numHijos = 3;
			this.profundidad = 1;
			this.esHoja = false;
		}
		else {
			
			this.numHijos = 0;
			this.profundidad = 0;
			this.esHoja = true;
		}
		
		if (raiz.isFunction()) {
			this.inicializaArbol(metodoIni);
		}
	}
	
	public void randomTerminal() {
		this.raiz = new Operando(true);
	}
	
	public void randomFuncion() {
		this.raiz = new Operando(false);
	}
	
	
	
	/***************************************************************************/
	/**************************** GETTERS & SETTERS ****************************/
	/***************************************************************************/
	public Arbol getPadre() {
		
		return this.padre;
	}
	
	public void setPadre(Arbol padre) {
		
		this.padre = padre;
	}
	
	public Operando getRaiz() {
		return raiz;
	}

	public ArrayList<Arbol> getHijos() {
		return hijos;
	}

	public void setHijos(ArrayList<Arbol> hijos) {
		this.hijos = hijos;
	}

	public int getNumHijos() {
		return numHijos;
	}

	public void setNumHijos(int numHijos) {
		this.numHijos = numHijos;
	}

	public int getNumNodos() {
		return numNodos;
	}

	public void setNumNodos(int numNodos) {
		this.numNodos = numNodos;
	}

	public int getMax_prof() {
		return max_prof;
	}

	public void setMax_prof(int max_prof) {
		this.max_prof = max_prof;
	}

	public int getProfundidadConst() {
		return this.profundidad;
	}
	public int getProfundidad() {
		
		calculaProfundidad(this, 0);
		return profundidad;
	}
	
	public void calculaProfundidad(Arbol a, int profundidad) {
		
		profundidad++;
		if (a.getRaiz().isTerminal()) {
			
			if (this.profundidad < profundidad) {
				this.profundidad = profundidad;
			}
		}
		else {
			
			for (int i = 0; i < a.getNumHijos(); i++) {
				calculaProfundidad(a.getHijos().get(i), profundidad);
			}
		}
	}

	public void setProfundidad(int profundidad) {
		this.profundidad = profundidad;
	}

	public boolean isUseIF() {
		return useIF;
	}

	public void setUseIF(boolean useIF) {
		this.useIF = useIF;
	}

	public boolean isEsHoja() {
		return esHoja;
	}

	public void setEsHoja(boolean esHoja) {
		this.esHoja = esHoja;
	}

	public boolean isEsRaiz() {
		return esRaiz;
	}

	public void setEsRaiz(boolean esRaiz) {
		this.esRaiz = esRaiz;
	}
}