package algoritmoGenetico.misc;

/**
 * Universidad Complutense de Madrid.
 * Programaci�n Evolutiva.
 * Grupo A 2021.
 * Profesor:
 * 	-Carlos Cervigon Ruckauer.
 * 
 * Pr�ctica 3
 * 
 * Clase Pair.
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodr�guez Pereira.
 *
 */
public class Pair<T1, T2> {
	
	/****************************************************************************/
	/******************************** ATRIBUTTES ********************************/
	private T1 _first;
	private T2 _second;

	
	
	/****************************************************************************/
	/******************************* CONSTRUCTOR ********************************/
	public Pair(T1 first, T2 second) {
		_first = first;
		_second = second;
	}

	
	
	/***************************************************************************/
	/********************************* METHODS *********************************/
	public T1 getFirst() {
		return _first;
	}

	public T2 getSecond() {
		return _second;
	}

}