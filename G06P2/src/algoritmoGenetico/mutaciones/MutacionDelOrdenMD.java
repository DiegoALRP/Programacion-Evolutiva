package algoritmoGenetico.mutaciones;

import java.util.ArrayList;
import java.util.Random;

import algoritmoGenetico.individuos.Individuo;
import algoritmoGenetico.individuos.NGramas;
import algoritmoGenetico.individuos.Texto;

/**
 * Universidad Complutense de Madrid.
 * Programación Evolutiva.
 * Grupo A 2021.
 * Profesor:
 * 	-Carlos Cervigon Ruckauer.
 * 
 * Práctica 2
 * 
 * Clase Mutación del Orden - Miguel Diego.
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodríguez Pereira.
 *
 */
public class MutacionDelOrdenMD extends Mutacion {
	
	/**************************** ATRIBUTTES *******************************/
	
	
	/**************************** CONSTRUCTOR ******************************/
	
	
	/***************************** METHODS ********************************/
	@Override
	protected void mutaIndividuo(Individuo individuo) {
		
		ArrayList<Integer> cromosoma = individuo.getCromosoma();
		ArrayList<Integer> cromosomaAux = new ArrayList<Integer>(cromosoma);
		
		int longCromo = cromosoma.size();
		Random rand = new Random();
		int puntoIntercambio = rand.nextInt(10) + (26/2 - 5);
		
		for (int i = 0; i < longCromo; i++) {
			
			cromosoma.set(i, cromosomaAux.get(Math.floorMod(i + puntoIntercambio, 26) ));
		}
		
		individuo.avisoCromoModificado();
	}

	
	/**************************** GETTERS & SETTERS ********************************/
}
