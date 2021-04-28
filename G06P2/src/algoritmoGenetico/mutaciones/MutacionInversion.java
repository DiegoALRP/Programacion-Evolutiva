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
