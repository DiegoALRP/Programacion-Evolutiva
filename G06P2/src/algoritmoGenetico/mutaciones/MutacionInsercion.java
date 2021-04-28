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
 * Clase Mutación por Inserción.
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodríguez Pereira.
 *
 */
public class MutacionInsercion extends Mutacion {

	/**************************** ATRIBUTTES *******************************/
	
	
	/**************************** CONSTRUCTOR ******************************/
	
	
	/***************************** METHODS ********************************/
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
