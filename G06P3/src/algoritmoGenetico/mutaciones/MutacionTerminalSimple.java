package algoritmoGenetico.mutaciones;

import java.util.Random;

import algoritmoGenetico.individuos.Individuo;
import algoritmoGenetico.individuos.Operando;

/**
 * Universidad Complutense de Madrid.
 * Programación Evolutiva.
 * Grupo A 2021.
 * Profesor:
 * 	-Carlos Cervigon Ruckauer.
 * 
 * Práctica 3
 * 
 * Clase Mutación Terminal Simple.
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodríguez Pereira.
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
		 * Para este método hemos decidido encargar la mutación a la clase árbol.
		 * Para que de esta manera esa clase tenga control total de los operadores.
		 * Y básicamente lo que hace es buscar un nodo terminal (es decir que sea hoja)
		 * y lo cambia por otro operador distinto
		 * 
		 * Dejamos a continuación la parte del código que realiza la clase árbol
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