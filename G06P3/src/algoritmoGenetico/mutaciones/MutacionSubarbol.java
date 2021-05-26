package algoritmoGenetico.mutaciones;

import algoritmoGenetico.individuos.Arbol;
import algoritmoGenetico.individuos.Individuo;

/**
 * Universidad Complutense de Madrid.
 * Programaci�n Evolutiva.
 * Grupo A 2021.
 * Profesor:
 * 	-Carlos Cervigon Ruckauer.
 * 
 * Pr�ctica 3
 * 
 * Clase Mutaci�n Sub�rbol.
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodr�guez Pereira.
 *
 */
public class MutacionSubarbol extends Mutacion{

	
	/******************************** ATRIBUTTES ********************************/
	
	
	/******************************* CONSTRUCTOR ********************************/
	
	
	/********************************* METHODS *********************************/
	@Override
	public void mutaIndividuo(Individuo individuo) {
		
		Arbol cromosoma = individuo.getCromosoma();
		Arbol arbolAGenerar = cromosoma.getSubTree(probMutacion);
		arbolAGenerar.expandeNodo();
	}
	
	/**************************** AUXILIARY METHODS ****************************/
	
	/**************************** GETTERS & SETTERS ****************************/

}
