package algoritmoGenetico.individuos;

import java.util.ArrayList;
import java.util.Random;

/**
 * Universidad Complutense de Madrid.
 * Programaci�n Evolutiva.
 * Grupo A 2021.
 * Profesor:
 * 	-Carlos Cervigon Ruckauer.
 * 
 * Pr�ctica 3
 * 
 * Clase Arbol.
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodr�guez Pereira.
 *
 */
public class Arbol {

	
	/****************************************************************************/
	/******************************** ATRIBUTTES ********************************/
	/****************************************************************************/
	private Arbol padre;				//Puntero al nodo padre.
	private Operando raiz;				//Ra�z del sub�rbol
	private ArrayList<Arbol> hijos;		//Array que contiene los hijos del sub�rbol
	private int numHijos;				//N�mero de hijos (directos) que contiene
	@SuppressWarnings("unused")
	private int numNodos;				//N�mero de nodos que contiene el �rbol	
	private int max_prof;				//Profundidad m�xima
	private int profundidad;			//Profundidad total
	private boolean useIF;				//useIF
	private boolean esHoja;				//Booleano que nos indica si el nodo es hoja o no
	private boolean esRaiz;				//Booleano que nos indica si el nodo es ra�z del �rbol o no
	private String metodoIni;			//M�todo de Inicializaci�n

	
	/****************************************************************************/
	/******************************* CONSTRUCTOR ********************************/
	/****************************************************************************/
	
	/**
	 * Constructora 1
	 * 
	 * @param padre	[ES] Puntero al nodo padre (si es ra�z del �rbol no tiene puntero).
	 * 				[EN] Father's node pointer (doesn't have pointer if it is tree's root).
	 * 
	 * @param raiz	[ES] Nodo ra�z del sub�rbol.
	 * 				[ES] Subtree's root.
	 * 
	 * @param max_prof	[ES] M�xima profundidad (restante) del sub�rbol.
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
	 * @param padre	[ES] Puntero al nodo padre (si es ra�z del �rbol no tiene puntero).
	 * 				[EN] Father's node pointer (doesn't have pointer if it is tree's root).
	 * 
	 * @param treeArray [ES] Array que contiene la estructura del �rbol que vamos a construir.
	 * 					[EN] Array that contains the tree's structure that we are going to build.
	 * 
	 * @param max_prof	[ES] M�xima profundidad (restante) del sub�rbol.
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
	 * [ES] Inserta un operando en el �rbol.
	 * [EN] Inserts an operand in the tree.
	 * 
	 * @param raiz	[ES] Operando que queremos a�adir.
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
	 * [ES] M�todo que nos devuelve un sub�rbol del �rbol principal.
	 * (Se utiliza principalmente para la funci�n de cruce).
	 * 
	 * [EN] Method that returns a subtree of the main tree.
	 * (Mainly used in the crossover function).
	 * 
	 * @param probCruce	[ES] Probabilidad de coger un sub�rbol.
	 * 					[EN] Probability of get a subtree.
	 * 
	 * @return	[ES] Un sub�rbol del �rbol principal.
	 * 			[EN] A subtree of the main tree.
	 */
	public Arbol getSubTree(double probCruce) {
	
		Random rand = new Random();
		Arbol hijo = this.hijos.get(rand.nextInt(numHijos)).getSubTreeAux(probCruce);
		return hijo;
	}
	
	/**
	 * [ES] M�todo auxiliar que nos devuelve un sub�rbol.
	 * 
	 * [EN] Method that returns a subtree.
	 * 
	 * @param probCruce	[ES] Probabilidad de coger un sub�rbol.
	 * 					[EN] Probability of get a subtree.
	 * 
	 * @return	[ES] Un sub�rbol.
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
	
	/**
	 * [ES] Funci�n que calcula el �ndice en el que se encuentra el operador
	 * solicitado.
	 * [EN] Function that calculates the index where is the operator
	 * requested.
	 * 
	 * @param op 	[ES] Operandor que se busca.
	 * 				[EN] Operator searching for.
	 * 
	 * @return	[ES] El �ndice del operador buscado.
	 * 			[EN] Index of the operator.
	 */
	public int getIndex(Operando op) {
		
		for (int i = 0; i < this.numHijos; i++) {
			
			if (hijos.get(i).getRaiz().equals(op)) {
				
				return i;
			}
		}
		return 0;
	}
	
	/**
	 * [ES] Inserta un nuevo �rbol en el �ndice indicado.
	 * [EN] Inserts a new tree in the index indicated.
	 * 
	 * @param a	[ES] �rbol que queremos insertar.
	 * 			[EN] Tree that we want to insert.
	 * 
	 * @param index	[ES] �dice en el cual se inserta el �rbol.
	 * 				[EN] Index in which the tree will we inserted.
	 */
	public void insertNewTree(Arbol a, int index) {
		
		hijos.set(index, a);
	}
	
	
	
	/***************************************************************************/
	/**************************** AUXILIARY METHODS ****************************/
	/***************************************************************************/
	
	/**
	 * [ES] Funci�n auxiliar que guarda en un array (pasado por par�metro)
	 * la estructura del �rbol.
	 * 
	 * [EN] Auxiliary function that store in an array (passed as a parameter)
	 * the structure of the tree.
	 * 
	 * @param array	[ES] Array donde se va a guardar la estructura del �rbol.
	 * 				[EN] Array where is going to be stored the tree's structure.
	 */
	public void toArrayAux(ArrayList<Operando> array) {
		
		array.add(this.getRaiz());
		
		if(this.hijos != null) {
			
			for(int i = 0; i < this.hijos.size(); i++){
				
				this.hijos.get(i).toArrayAux(array);
			}
		}
	}
	
	/**
	 * [ES] Funci�n que inicializa el �rbol, construyendo 
	 * de esta manera la estructura interna del �rbol.
	 * 
	 * [EN] Function that initializes the tree, building
	 * the inner structure of the tree.
	 * 
	 * @param metodo	[ES] M�todo de inicializaci�n.
	 * 					[EN] Initialization method.
	 */
	public void inicializaArbol(String metodo) {
		
		if (metodo.equals("Completo")) {
			
			this.inicializaCompleto();
		}
		else if (metodo.equalsIgnoreCase("Creciente")) {
			
			this.inicializaCreciente();
		}
	}
	
	/**
	 * [ES] Crea un nuevo �rbol/sub�rbol con una nueva estructura
	 * interna.
	 * 
	 * [EN] Creates a new tree/subtree with a new inner
	 * structure.
	 * 
	 * @param metodo 	[ES] M�todo de construcci�n del �rbol.
	 * 					[EN] Tree's construction method.
	 */
	public void cambiaArbol(String metodo) {
		
		this.metodoIni = metodo;
		if (metodo.equalsIgnoreCase("Completo")) {
			
			this.cambiaArbolCompleto();
		}
		else if (metodo.equalsIgnoreCase("Creciente")) {
			
			this.cambiaArbolCompleto();
		}
	}
	
	/**
	 * [ES] M�todo que cambia la entructura del �rbol
	 * [EN] Method that changes the structure of the tree.
	 * 
	 */
	private void cambiaArbolCompleto() {
		
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
	
	/**
	 * [ES] Funci�n que inicializa con el m�todo "Completo" el �rbol.
	 * [EN] Function that initializes the tree with the "Complete" method.
	 * 
	 */
	private void inicializaCompleto() {
		
		if (this.max_prof > 1) {
			
			for (int i = 0; i < numHijos; i++) {
				
				Operando op = new Operando(false);
				Arbol a = new Arbol(this, op, max_prof - 1, this.metodoIni);
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
	
	/**
	 * [ES] Funci�n que inicializa el �rbol con el m�todo "Creciente".
	 * [EN] Function that initializes the tree with the "Crescent" method.
	 * 
	 */
	private void inicializaCreciente() {
		
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
	
	/**
	 * [ES] Esta funci�n crea un string con la estructura interna del �rbol.
	 * Devolviendo as� el string para luego poder observar o imprimir el �rbol.
	 * 
	 * [EN] This function creates a string with the inner structure of the tree.
	 * Returning the string to see it or to print it.
	 * 
	 * @return	[ES] Devuelve el string con la estructura del �rbol.
	 * 			[EN] Return the string with the tree's structure.
	 */
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
	
	/**
	 * [ES] Esta funci�n realiza el trabajo "sucio" para el m�todo:
	 * 		- "Mutaci�n Terminal Simple"
	 * Est� funci�n lo que realiza es que busca dentro de la estructura del �rbol un nodo
	 * terminal aleatorio. Una vez encuentra un nodo terminal sustituye su operando
	 * por un operando nuevo.
	 * 
	 * [EN] This function realizes the "dirty" work for the method:
	 * 		- "Mutaci�n Terminal Simple"
	 * This function search inside of the tree's structure for a random terminal node.
	 * Once it finds a terminal node, it substitute it's operand for a new operand.
	 * 
	 */
	public void mutaTerminalSimple() {
		
		if (this.raiz.isTerminal()) {
			
			this.raiz = new Operando(true, this.raiz.toString());
		}
		else {
			
			Random rand = new Random();
			hijos.get(rand.nextInt(numHijos)).mutaTerminalSimple();
		}
	}
	
	/**
	 * [ES] Esta funci�n realiza el trabajo "sucio" para el m�todo:
	 * 		- "Mutaci�n Funci�n Simple"
	 * Est� funci�n lo que realiza es que busca dentro de la estructura del �rbol un nodo
	 * Funci�n aleatorio. Una vez encuentra un nodo Funci�n sustituye su operando
	 * por un operando nuevo.
	 * 
	 * [EN] This function realizes the "dirty" work for the method:
	 * 		- "Mutaci�n Funci�n Simple"
	 * This function search inside of the tree's structure for a random function node.
	 * Once it finds a function node, it substitute it's operand for a new operand.
	 * 
	 */
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
	
	/**
	 * [ES] Funci�n auxiliar que ayuda a hacer el trabajo de:
	 * 		- Mutaci�n Expansi�n
	 * Cambia un operador terminal y luego utiliza parte de la idea
	 * de la inicializaci�n del �rbol para expandir el nodo y crear la nueva estructura
	 * que depende de ese operando nuevo.
	 * 
	 * [EN] Auxiliary function that helps the work of:
	 * 		- Mutaci�n Expansi�n
	 * It changes a terminal operator and then uses part of the idea in
	 * the initialization of the tree to expand the node and creates a new
	 * structure that depends on the new operator.
	 * 
	 */
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
	
	/**
	 * [ES] Cambia el operador del nodo actual a un operador terminal.
	 * [EN] Changes the operator of the actual node to a terminal operator.
	 * 
	 */
	public void randomTerminal() {
		this.raiz = new Operando(true);
	}
	
	/**
	 * [ES] Cambia el operador del nodo actual a un operador funcional.
	 * [EN] Changes the operator of the actual node to a functional operator.
	 */
	public void randomFuncion() {
		this.raiz = new Operando(false);
	}
	
	
	/***************************************************************************/
	/********************** AUXILIARY METHODS - Bloating ***********************/
	/***************************************************************************/
	
	/**
	 * [ES] Funci�n que calcula el n�mero de nodos que hay en el �rbol y sus
	 * sub�rboles y retorna el valor.
	 * 
	 * [EN] Function that calculates the number of nodes that are in the tree
	 * and all of the subtress and then it returns it's value.
	 * 
	 * @return	[ES] Retorna el n�mero de nodos que hay en el �rbol.
	 * 			[EN] Returns the number of nodes that are in the tree.
	 */
	protected int getNumNodos() {
		
		int numNodos = 0;
		
		numNodos += getNumNodosAux();
		this.numNodos = numNodos;
		return numNodos;
	}
	
	/**
	 * [ES] Funci�n auxiliar que calcula el n�mero de nodos de los hijos.
	 * [EN] Auxiliary function that calculates the number of nodes of the children.
	 * 
	 * @return	[ES] N�mero de nodos de los hijos.
	 * 			[EN] Number of nodes of the children.
	 */
	private int getNumNodosAux() {
		
		int numNodos = 1;
		
		for (int i = 0; i < numHijos; i++) {
			
			numNodos += hijos.get(i).getNumNodos();
		}
		
		return numNodos;
	}
	
	/**
	 * [ES] Esta funci�n calcula la profundidad del �rbol.
	 * [EN] This function calculates the depth of the tree.
	 * 
	 * @param arbol	[ES] �rbol del cual se parte.
	 * 				[EN] Entry tree.
	 * 
	 * @param profundidad	[ES] Profundidad por la que va el �rbol.
	 * 						[EN] Tree's depth.
	 */
	public void calculaProfundidad(Arbol arbol, int profundidad) {
		
		profundidad++;
		if (arbol.getRaiz().isTerminal()) {
			
			if (this.profundidad < profundidad) {
				this.profundidad = profundidad;
			}
		}
		else {
			
			for (int i = 0; i < arbol.getNumHijos(); i++) {
				calculaProfundidad(arbol.getHijos().get(i), profundidad);
			}
		}
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