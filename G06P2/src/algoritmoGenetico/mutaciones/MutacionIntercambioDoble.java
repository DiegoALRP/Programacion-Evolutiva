package algoritmoGenetico.mutaciones;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import algoritmoGenetico.individuos.Individuo;

/**
 * Universidad Complutense de Madrid.
 * Programación Evolutiva.
 * Grupo A - 2021.
 * Profesor:
 * 	-Carlos Cervigon Ruckauer.
 * 
 * Práctica 2.
 * 
 * Clase Mutación por Intercambio Doble.
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodríguez Pereira.
 *
 */
public class MutacionIntercambioDoble extends Mutacion {

	
	/**************************** ATRIBUTTES *******************************/

	
	/**************************** CONSTRUCTOR ******************************/
	
	
	/***************************** METHODS ********************************/
	
	/**
	 * [ES] Esta función muta un individuo siguiendo el patron de mutación por intercambio doble la cual fue
	 * creada por nosotros.
	 * Dado un indiviuo, se seleccionan cuatro puntos de corte en el cromosoma de este y se intercambian
	 * las posiciones en dichos puntos por parejas.
	 * 
	 * 
	 * [EN] This function mutates an individual following the pattern of mutation by exchange which was
	 * developed by us.
	 * Given an individual, four breakpoints are selected on the individual's chromosome and the 
	 * positions at these breakpoints are swapped by pairs.
	 * 
	 * 
	 * @param Individuo	[ES] El individuo.
	 * 					[EN] The individual.
	 * 
	 */
	@Override
	protected void mutaIndividuo(Individuo individuo) {
		
		ArrayList<Integer> cromosoma = individuo.getCromosoma();
		int longCromo = cromosoma.size();
		
		Random rand = new Random();
		HashSet<Integer> puntos = new HashSet<Integer>();
		int a = rand.nextInt(longCromo);
		puntos.add(a);
		int b = rand.nextInt(longCromo);
		while (puntos.contains(b)) {
			b = rand.nextInt(longCromo);
		}
		puntos.add(b);
		int c = rand.nextInt(longCromo);
		while (puntos.contains(c)) {
			c = rand.nextInt(longCromo);
		}
		puntos.add(c);
		int d = rand.nextInt(longCromo);
		while (puntos.contains(d)) {
			d = rand.nextInt(longCromo);
		}
		puntos.add(d);
		
		int aux1 = cromosoma.get(a);
		int aux2 = cromosoma.get(c);
		cromosoma.set(a, cromosoma.get(b));
		cromosoma.set(b, aux1);
		cromosoma.set(c, cromosoma.get(d));
		cromosoma.set(d, aux2);
		
		individuo.avisoCromoModificado();
	}

	
	/**************************** GETTERS & SETTERS ********************************/
}
