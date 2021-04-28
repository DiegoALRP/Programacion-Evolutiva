package algoritmoGenetico.seleccion;

import java.util.ArrayList;

import algoritmoGenetico.individuos.Individuo;

/**
 * Universidad Complutense de Madrid.
 * Programaci�n Evolutiva.
 * Grupo A 2021.
 * Profesor:
 * 	-Carlos Cervigon Ruckauer.
 * 
 * Clase Abstracta Selecci�n.
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodr�guez Pereira.
 *
 */

public abstract class Seleccion {

	/**************************** ATRIBUTTES *******************************/
	
	
	/**************************** CONSTRUCTOR *******************************/
	
	
	/***************************** METHODS ********************************/
	
	public abstract ArrayList<Individuo> seleccionar(ArrayList<Individuo> poblacion);
	
	public void addIndividuo(ArrayList<Individuo> nuevaPoblacion, Individuo individuo) {
		
		Individuo nuevoIndividuo = new Individuo(individuo.getTexto(), individuo.getNGrama(), individuo.getCromosoma());
		nuevaPoblacion.add(nuevoIndividuo);
	}
	
	
	
	/**************************** GETTERS & SETTERS ********************************/
}
