package algoritmoGenetico.mutaciones;

import java.util.ArrayList;
import java.util.Random;

import algoritmoGenetico.individuos.Individuo;

/**
 * Universidad Complutense de Madrid.
 * Programaci�n Evolutiva.
 * Grupo A 2021.
 * Profesor:
 * 	-Carlos Cervigon Ruckauer.
 * 
 * Pr�ctica 2
 * 
 * Clase Mutaci�n por Inversi�n.
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodr�guez Pereira.
 *
 */
public class MutacionInversion extends Mutacion {

	
	/**************************** ATRIBUTTES *******************************/

	
	/**************************** CONSTRUCTOR ******************************/
	
	
	/***************************** METHODS ********************************/
	
	/**
	 * [ES] Esta funci�n muta un individuo siguiendo el patron de mutaci�n por inversi�n.
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
