package algoritmoGenetico.seleccion;

import java.util.ArrayList;

import algoritmoGenetico.individuos.Individuo;

/**
 * Universidad Complutense de Madrid.
 * Programación Evolutiva.
 * Grupo A 2021.
 * Profesor:
 * 	-Carlos Cervigon Ruckauer.
 * 
 * Clase Abstracta Selección.
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodríguez Pereira.
 *
 */

public abstract class Seleccion {

	/**************************** ATRIBUTTES *******************************/
	
	
	
	/**************************** CONSTRUCTOR *******************************/
	
	
	
	/***************************** METHODS ********************************/
	
	/**
	 * [ES]	Esta función es la principal de la clase de Selección.
	 * Esta función selecciona a los individuos de una población para generar
	 * una población nueva/auxiliar con los individuos elegidos.
	 * Ambas poblaciones son del mismo tamaño.
	 * La manera en la que se seleccionan depende del tipo de algoritmo y de como
	 * este esté implementado.
	 * [EN] This is the main function of the Selection's class.
	 * This function selects the individuals of a population to generate a new/auxiliar
	 * population with the individuals chosen.
	 * Both population are of the same size.
	 * The way that the individuals are selected depends of the algorith and how it's
	 * implemented.
	 * 
	 * @param poblacion	[ES] Población de la cual se van a seleccionar los individuos.
	 * 					[EN] Population form which it's going to be selected the individuals.
	 * 
	 * @return	[ES] La nueva población con los individuos seleccionados.
	 * 			[EN] The new population with the selected individuals.
	 */
	public abstract ArrayList<Individuo> seleccionar(ArrayList<Individuo> poblacion);
	
	
	/**
	 * [ES] Copia el individuo seleccionado (por el índice) de la población inicial
	 * a la nueva población.
	 * La copia es por valor (no por referencia).
	 * [EN] Copies the selected individual (by index) from the initial population
	 * to the new population.
	 * The copy is by value (not by reference).
	 * 
	 * @param nuevaPoblacion 	[ES] Nueva población.
	 * 							[EN] New population.
	 * @param index	[ES] Individuo seleccionado.
	 * 				[EN] Individual selected.
	 */
	public void addIndividuo(ArrayList<Individuo> nuevaPoblacion, Individuo individuo) {
		
		Individuo nuevoIndividuo = new Individuo(individuo.getTexto(), individuo.getNGrama(), individuo.getCromosoma());
		nuevaPoblacion.add(nuevoIndividuo);
	}
	
	
	
	/**************************** GETTERS & SETTERS ********************************/
}
