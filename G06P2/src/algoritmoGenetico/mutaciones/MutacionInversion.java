package algoritmoGenetico.mutaciones;

import java.util.ArrayList;
import java.util.Random;

import algoritmoGenetico.individuos.Individuo;

/**
 * Universidad Complutense de Madrid.
 * Programación Evolutiva.
 * Grupo A 2021.
 * Profesor:
 * 	-Carlos Cervigon Ruckauer.
 * 
 * Práctica 2
 * 
 * Clase Mutación por Inversión.
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodríguez Pereira.
 *
 */
public class MutacionInversion extends Mutacion {

	
	/**************************** ATRIBUTTES *******************************/

	
	/**************************** CONSTRUCTOR ******************************/
	
	
	/***************************** METHODS ********************************/
	
	/**
	 * [ES] Esta función muta un individuo siguiendo el patron de mutación por inversión.
	 * Dado un indiviuo, se seleccionan dos puntos de corte en el cromosoma de este y se invierten
	 * las posiciones entre dichos puntos.
	 * 
	 * 
	 * [EN] This function mutates an individual following the pattern of mutation by inversion.
	 * Given an individual, two breakpoints are selected on the individual's chromosome and the 
	 * positions between these breakpoints are inverted.
	 * 
	 * 
	 * @param Individuo	[ES] El individuo.
	 * 					[EN] The  individual.
	 * 
	 */
	@Override
	protected void mutaIndividuo(Individuo individuo) {
		ArrayList<Integer> cromosoma = individuo.getCromosoma();
		ArrayList<Integer> cromosomaAux = new ArrayList<Integer>(cromosoma);
		
		Random rand = new Random();
		int i = rand.nextInt(20);
		int j = rand.nextInt(26 - i) + i;
		for (int k = i; k <= j; k++) {
			
			cromosoma.set(k, cromosomaAux.get(j - k + i));
		}
		
		individuo.avisoCromoModificado();
	}

	
	/**************************** GETTERS & SETTERS ********************************/
}
