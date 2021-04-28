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
