package algoritmoGenetico.mutaciones;

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
 * Clase Mutaci�n Funcional Simple.
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodr�guez Pereira.
 *
 */
public class MutacionFuncionalSimple extends Mutacion{

	
	/******************************** ATRIBUTTES ********************************/
	
	/******************************* CONSTRUCTOR ********************************/
	
	/********************************* METHODS *********************************/
	@Override
	public void mutaIndividuo(Individuo individuo) {
		
		individuo.mutaFuncionSimple(0.3);
	}

	/***************************** AUXILIARY METHODS *****************************/
	
	/**************************** GETTERS & SETTERS ****************************/
}
