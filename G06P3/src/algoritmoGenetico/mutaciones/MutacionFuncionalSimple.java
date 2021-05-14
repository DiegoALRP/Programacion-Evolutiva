package algoritmoGenetico.mutaciones;

import algoritmoGenetico.individuos.Individuo;

/**
 * Universidad Complutense de Madrid.
 * Programación Evolutiva.
 * Grupo A 2021.
 * Profesor:
 * 	-Carlos Cervigon Ruckauer.
 * 
 * Práctica 3
 * 
 * Clase Mutación Funcional Simple.
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodríguez Pereira.
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
