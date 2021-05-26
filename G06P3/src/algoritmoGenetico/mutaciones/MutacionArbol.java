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
 * Clase Mutación Árbol.
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodríguez Pereira.
 *
 */
public class MutacionArbol extends Mutacion{

	
	/******************************** ATRIBUTTES ********************************/
	
	/******************************* CONSTRUCTOR ********************************/
	
	/********************************* METHODS *********************************/
	@Override
	public void mutaIndividuo(Individuo individuo) {
		
		Arbol cromosoma = individuo.getCromosoma();
		
		Random rand = new Random();
		
		Arbol hijo = cromosoma.getHijos().get(rand.nextInt(cromosoma.getNumHijos()));
		
		while (rand.nextDouble() > this.probMutacion) {
			
			if (hijo.getNumHijos() < 1) {
				
				hijo = hijo.getPadre();
			}
			else {
				
				hijo = hijo.getHijos().get(rand.nextInt(hijo.getNumHijos()));
			}
		}
		
		hijo.cambiaArbol("Completo");
	}

	/**************************** AUXILIARY METHODS ****************************/
	
	/**************************** GETTERS & SETTERS ****************************/
}
