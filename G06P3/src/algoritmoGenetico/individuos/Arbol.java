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

	/******************************** ATRIBUTTES ********************************/
	private Arbol padre;
	private Operando raiz;
	private ArrayList<Arbol> hijos;
	private int numHijos;
	private int numNodos;
	private int max_prof;
	private int profundidad;
	private boolean useIF;
	private boolean esHoja;
	private boolean esRaiz;
	private String metodoIni;

	
	/******************************* CONSTRUCTOR ********************************/
	
	/**
	 * Constructora 1
	 * 
	 * @param padre
	 * @param raiz
	 * @param max_prof
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
	 * 
	 * @param treeArray
	 * @param max_prof
	 */
	public Arbol(ArrayList<Operando> treeArray, int max_prof) {
		
		this.padre = null;
		this.raiz = treeArray.get(0);
		treeArray.remove(0);
		this.max_prof = max_prof;
		
		hijos = new ArrayList<Arbol>();
		for (int i = 0; i < treeArray.size(); i++) {
			
			Operando raiz = treeArray.get(0);
			treeArray.remove(0);
			//Arbol a = new Arbol(this, raiz, treeArray, max_prof - 1);
			//hijos.add(a);
		}
	}
	
	/**
	 * Constructora 3
	 * 
	 * @param padre
	 * @param treeArray
	 * @param max_prof
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
				
				//Operando raizH = treeArray.get(0);
				//treeArray.remove(0);
				//Poner aqui uncamente el remove
				Arbol a = new Arbol(this, treeArray, max_prof - 1);
				hijos.add(a);
			}
		}
	}
	/********************************* METHODS *********************************/
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
	
	public Arbol getSubTree(double probCruce) {
	
		Random rand = new Random();
		return this.hijos.get(rand.nextInt(numHijos)).getSubTreeAux(probCruce);
	}
	
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
				
				return this.padre;
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
	
	/********************************* AUXILIARY METHODS *********************************/
	public void toArrayAux(ArrayList<Operando> array) {
		
		array.add(this.getRaiz());
		
		if(this.hijos != null) {
			for(int i = 0; i < this.hijos.size(); i++){
				this.hijos.get(i).toArrayAux(array);
			}
		}
	}
	
	public int actualizaProfundidad() {
		
		this.profundidad = 0;
		for (int i = 0; i < numHijos; i++) {
			
			if (!hijos.get(i).esHoja) {
				
				this.profundidad += actualizaProfundidad();
			}
		}
		
		return profundidad;
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
	}
	
	private void cambiaArbolCompleto() {
		
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
	
	/********************** AUXILIARY METHODS - Crossover **********************/
	public void mutaTerminalSimple() {
		
		if (this.raiz.isTerminal()) {
			
			this.raiz = new Operando(true, this.raiz.toString());
		}
		else {
			
			Random rand = new Random();
			hijos.get(rand.nextInt(numHijos)).mutaTerminalSimple();;
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
	
	public void randomTerminal() {
		this.raiz = new Operando(true);
	}
	
	public void randomFuncion() {
		this.raiz = new Operando(false);
	}
	
	/**************************** GETTERS & SETTERS ****************************/
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