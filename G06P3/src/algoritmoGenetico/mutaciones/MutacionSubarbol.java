package algoritmoGenetico.mutaciones;

import algoritmoGenetico.individuos.Arbol;
import algoritmoGenetico.individuos.Individuo;

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
