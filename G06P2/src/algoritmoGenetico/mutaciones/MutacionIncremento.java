package algoritmoGenetico.mutaciones;

import java.util.ArrayList;

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
 * Clase Mutaci�n por Incremento.
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodr�guez Pereira.
 *
 */
public class MutacionIncremento extends Mutacion {
	
	/**************************** ATRIBUTTES *******************************/
	private final int incremento = 1;

	
	/**************************** CONSTRUCTOR ******************************/
	
	
	/***************************** METHODS ********************************/
	@Override
	protected void mutaIndividuo(Individuo individuo) {
		
		ArrayList<Integer> cromosoma = individuo.getCromosoma();
		
		int aux;
		for (int i = 0; i < cromosoma.size(); i++) {
			
			aux = cromosoma.get(i);
			cromosoma.set(i, Math.floorMod((aux + incremento), 26));
		}
		
		individuo.avisoCromoModificado();
	}

	
	/**************************** GETTERS & SETTERS ********************************/
}
