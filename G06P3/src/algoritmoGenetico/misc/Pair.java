package algoritmoGenetico.misc;

import java.util.Comparator;
import java.util.Objects;

/**
 * Universidad Complutense de Madrid.
 * Programación Evolutiva.
 * Grupo A 2021.
 * Profesor:
 * 	-Carlos Cervigon Ruckauer.
 * 
 * Práctica 3
 * 
 * Clase Pair.
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodríguez Pereira.
 *
 */
public class Pair {
	
	/****************************************************************************/
	/******************************** ATRIBUTTES ********************************/
	private int _first;
	private int _second;

	
	
	/****************************************************************************/
	/******************************* CONSTRUCTOR ********************************/
	public Pair(int first, int second) {
		_first = first;
		_second = second;
	}

	
	
	/***************************************************************************/
	/********************************* METHODS *********************************/
	public int getFirst() {
		return _first;
	}

	public int getSecond() {
		return _second;
	}

	public int hashCode() {
        return Objects.hash(this._first, this._second);
    }
	
	public boolean equals(Object obj) {

        Pair that = (Pair) obj;
        return this._first == that._first && this._second == that._second;
    }
}