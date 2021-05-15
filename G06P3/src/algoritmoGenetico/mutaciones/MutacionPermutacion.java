package algoritmoGenetico.mutaciones;

import java.util.ArrayList;
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
 * Clase Mutación Permutación.
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodríguez Pereira.
 *
 */
public class MutacionPermutacion extends Mutacion{

	/******************************** ATRIBUTTES ********************************/
	
	/******************************* CONSTRUCTOR ********************************/
	
	/********************************* METHODS *********************************/
	@Override
	public void mutaIndividuo(Individuo individuo) {
		
		Random rand = new Random();
		
		Arbol a = individuo.getCromosoma();
		
		while (rand.nextDouble() > 0.5) {
			
			if (a.getNumHijos() < 1) {
				
				a = a.getPadre();
			}
			else {
				
				a = a.getHijos().get(rand.nextInt(a.getNumHijos()));
			}
		}
		
		ArrayList<Arbol> hijos = a.getHijos();
		
		ArrayList<Arbol> nuevoHijos = new ArrayList<Arbol>();
		
		for (int i = hijos.size() - 1; i >= 0; i--) {
			
			nuevoHijos.add(hijos.get(i));
		}
		
		a.setHijos(nuevoHijos);
	}

	/***************************** AUXILIARY METHODS *****************************/
	
	/**************************** GETTERS & SETTERS ****************************/
}
