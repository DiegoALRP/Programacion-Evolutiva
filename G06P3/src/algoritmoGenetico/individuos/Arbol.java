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

	
	/******************************* CONSTRUCTOR ********************************/
	public Arbol(Arbol padre, Operando raiz, int max_prof) {
		
		this.padre = padre;
		this.raiz = raiz;
		this.max_prof = max_prof;
		hijos = new ArrayList<Arbol>(this.numHijos);
		
		//if (raiz.equals(Funciones.PROGN2) || raiz.equals(Funciones.SIComida)) {
		if (this.raiz.equalsProgN2() || this.raiz.equalsSiComida()) {
			
			this.numHijos = 2;
			this.profundidad = 1;
			this.esHoja = false;
		}
		//else if (raiz.equals(Funciones.PROGN3)) {
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
	}
	
	/********************************* METHODS *********************************/
	public void insert(Operando raiz) {
		
		boolean encontrado = false;
		
		int menorHijo = 0;
		int minHihjos = Integer.MAX_VALUE;
		for (int i = 0; i < this.numHijos && !encontrado; i++) {
			
			if (this.hijos.get(i).raiz.isTerminal()) {
				
				Arbol a = new Arbol(this, raiz, this.max_prof - 1);
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
	
	
	/********************************* AUXILIARY METHODS *********************************/
	
	private void toArrayAux(ArrayList<String> array, Arbol a) {
		
		array.add(a.getRaiz());
		
		for(int i = 0; i < a.hijos.size(); i++){
			toArrayAux(array, a.hijos.get(i));
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
	
	
	/**************************** GETTERS & SETTERS ****************************/
	public Arbol getPadre() {
		
		return this.padre;
	}
	
	public void setPadre(Arbol padre) {
		
		this.padre = padre;
	}
	
	public String getRaiz() {
		return raiz.getOperando();
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

	public int getProfundidad() {
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
