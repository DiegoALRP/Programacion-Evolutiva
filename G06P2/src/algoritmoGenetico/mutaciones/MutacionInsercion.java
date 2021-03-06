package algoritmoGenetico.mutaciones;

import java.util.ArrayList;
import java.util.Random;

import algoritmoGenetico.individuos.Individuo;

/**
 * Universidad Complutense de Madrid.
 * Programaci?n Evolutiva.
 * Grupo A 2021.
 * Profesor:
 * 	-Carlos Cervigon Ruckauer.
 * 
 * Pr?ctica 2
 * 
 * Clase Mutaci?n por Inserci?n.
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodr?guez Pereira.
 *
 */
public class MutacionInsercion extends Mutacion {

	/**************************** ATRIBUTTES *******************************/
	
	
	/**************************** CONSTRUCTOR ******************************/
	
	
	/***************************** METHODS ********************************/
	
	/**
	 * [ES] Esta funci?n muta un individuo siguiendo el patron de mutaci?n por inserci?n
	 * Dado un indiviuo, se seleccionan dos puntos de corte en el cromosoma de este y se introducen
	 * en distintas posiciones desplazando el resto del cromosoma para dejar espacio.
	 * 
	 * 
	 * [EN]This function mutates an individual following the insertion mutation pattern.
	 * Given an individual, two breakpoints are selected on the individual's chromosome 
	 * and inserted at different positions, displacing the rest of the chromosome to make room.
	 * 
	 * 
	 * @param Individuo	[ES] El individuo.
	 * 					[EN] The individual.
	 * 
	 */
	
	@Override
	protected void mutaIndividuo(Individuo individuo) {
		
		ArrayList<Integer> cromosoma = individuo.getCromosoma();
		ArrayList<Integer> cromosomaAux = new ArrayList<Integer>();
		cromosomaAux.addAll(cromosoma);
		
		int longCromosoma = cromosoma.size();
		
		Random rand = new Random();
		int i = rand.nextInt(longCromosoma);
		int j = rand.nextInt(longCromosoma);
		while (j == i) {
			
			j = rand.nextInt(longCromosoma);
		}
		
		int k = (j + 1)%longCromosoma;
		while (k != (i + 1)%longCromosoma) {
			
			if (k == 0) {
				cromosoma.set(k, cromosomaAux.get(longCromosoma - 1));
			}
			else {
				cromosoma.set(k, cromosomaAux.get((k - 1)%longCromosoma));
			}
			k = (k + 1)%longCromosoma;
		}
		
		cromosoma.set(j, cromosomaAux.get(i));
		individuo.avisoCromoModificado();
	}

	
	/**************************** GETTERS & SETTERS ********************************/
}
