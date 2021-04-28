package algoritmoGenetico.mutaciones;

import java.util.ArrayList;
import java.util.Random;

import algoritmoGenetico.individuos.Individuo;

/**
 * Universidad Complutense de Madrid.
 * Programaci�n Evolutiva.
 * Grupo A - 2021.
 * Profesor:
 * 	-Carlos Cervigon Ruckauer.
 * 
 * Pr�ctica 2.
 * 
 * Clase Mutaci�n por Intercambio.
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodr�guez Pereira.
 *
 */
public class MutacionIntercambio extends Mutacion {

	/**************************** ATRIBUTTES *******************************/

	
	/**************************** CONSTRUCTOR ******************************/
	
	
	/***************************** METHODS ********************************/
	
	/**
	 * [ES] Esta funci�n muta un individuo siguiendo el patron de mutaci�n por intercambio.
	 * Dado un indiviuo, se seleccionan dos puntos de corte en el cromosoma de este y se intercambian
	 * las posiciones en dichos puntos.
	 * 
	 * 
	 * [EN] This function mutates an individual following the pattern of mutation by exchange.
	 * Given an individual, two breakpoints are selected on the individual's chromosome and the 
	 * positions at these breakpoints are swapped.
	 * 
	 * 
	 * @param Individuo	[ES] El individuo.
	 * 					[EN] The individual.
	 * 
	 */
	@Override
	protected void mutaIndividuo(Individuo individuo) {
		
		ArrayList<Integer> cromosoma = individuo.getCromosoma();
		
		Random rand = new Random();
		int i = rand.nextInt(cromosoma.size());
		int j = rand.nextInt(cromosoma.size());
		while (j == i) {
			
			j = rand.nextInt(cromosoma.size());
		}
		
		int aux = cromosoma.get(i);
		cromosoma.set(i, cromosoma.get(j));
		cromosoma.set(j, aux);
		individuo.avisoCromoModificado();
	}
	
	
	/**************************** GETTERS & SETTERS ********************************/
}
