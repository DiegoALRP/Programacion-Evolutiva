package algoritmoGenetico.mutaciones;

import java.util.Random;

import algoritmoGenetico.individuos.Individuo;
import algoritmoGenetico.individuos.Operando;

/**
 * Universidad Complutense de Madrid.
 * Programaci�n Evolutiva.
 * Grupo A 2021.
 * Profesor:
 * 	-Carlos Cervigon Ruckauer.
 * 
 * Pr�ctica 3
 * 
 * Clase Mutaci�n Terminal Simple.
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodr�guez Pereira.
 *
 */
public class MutacionTerminalSimple extends Mutacion{

	/******************************** ATRIBUTTES ********************************/
	
	/******************************* CONSTRUCTOR ********************************/
	
	/********************************* METHODS *********************************/
	@Override
	public void mutaIndividuo(Individuo individuo) {
		
		individuo.mutaTerminalSimple();
		
		//Nota:
		/*
		 * Para este m�todo hemos decidido encargar la mutaci�n a la clase �rbol.
		 * Para que de esta manera esa clase tenga control total de los operadores.
		 * Y b�sicamente lo que hace es buscar un nodo terminal (es decir que sea hoja)
		 * y lo cambia por otro operador distinto
		 * 
		 * Dejamos a continuaci�n la parte del c�digo que realiza la clase �rbol
		 * 
		 */
		
		/*public void mutaTerminalSimple() {
			
			if (this.raiz.isTerminal()) {
				
				this.raiz = new Operando(true, this.raiz.toString());
			}
			else {
				
				Random rand = new Random();
				hijos.get(rand.nextInt(numHijos)).mutaTerminalSimple();
			}
		}*/
	}

	/***************************** AUXILIARY METHODS *****************************/
	
	/**************************** GETTERS & SETTERS ****************************/
}