package algoritmoGenetico.mutaciones;

import java.util.Random;

import algoritmoGenetico.individuos.Arbol;
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
 * Clase Mutación Expansión.
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodríguez Pereira.
 *
 */
public class MutacionExpansion extends Mutacion{

	
	/******************************** ATRIBUTTES ********************************/
	private Random rand;
	private Arbol arbolExpandir;
	
	
	/******************************* CONSTRUCTOR ********************************/
	
	
	/********************************* METHODS *********************************/
	@Override
	public void mutaIndividuo(Individuo individuo) {
		
		Arbol arbol = individuo.getCromosoma();
		rand = new Random();
		
		buscaTerminal(arbol);
		
		arbolExpandir.expandeNodo();
	}
	
	
	/**************************** AUXILIARY METHODS ****************************/
	
	private void buscaTerminal(Arbol a) {
		
		if (a.getRaiz().isTerminal()) {
			arbolExpandir = a;
		}
		else {
			
			buscaTerminal(a.getHijos().get(rand.nextInt(a.getNumHijos())));
		}
	}
	
	/**************************** GETTERS & SETTERS ****************************/
}
