package algoritmoGenetico.cruces;

import java.util.ArrayList;
import java.util.Random;

import algoritmoGenetico.individuos.Individuo;

/**
 * Universidad Complutense de Madrid.
 * Programación Evolutiva.
 * Grupo A 2021.
 * Profesor:
 * 	-Carlos Cervigon Ruckauer.
 * 
 * Clase Cruce Aritmético.
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodríguez Pereira.
 *
 */
public class CruceAritmetico extends Cruce {

	/**
	 * [ES] Esta función cruza dos individuos (2 padres) y genera e inserta dos nuevos individuos (2 nuevos hijos).
	 * [EN] This function crossover two individuals (2 parenst) and generates and insert two new individual (2 new children).
	 * 
	 * @param padre1	[ES] El primer individuo a cruzar.
	 * 					[EN] The first individual to cross.
	 * @param padre2	[ES] El segundo indivuduo a cruzar.
	 * 					[EN] The second individual to cross.
	 */
	@Override
	protected void cruzaPadres(Individuo padre1, Individuo padre2) {
		
		ArrayList<Double> cromoPadre1 = padre1.getCromosoma();
		ArrayList<Double> cromoPadre2 = padre2.getCromosoma();
		
		ArrayList<Double> cromoPadre1Aux = new ArrayList<Double>();
		cromoPadre1Aux.addAll(cromoPadre1);
		
		ArrayList<Double> cromoPadre2Aux = new ArrayList<Double>();
		cromoPadre2Aux.addAll(cromoPadre2);
		
		ArrayList<Double> cromoHijo1 = new ArrayList<Double>();
		cromoHijo1.addAll(cromoPadre1);

		ArrayList<Double> cromoHijo2 = new ArrayList<Double>();
		cromoHijo2.addAll(cromoPadre2);
		
		int i = 0;
		int longitudCromo = padre1.getLongitudCromosoma();
		Random rand = new Random();
		double alpha = rand.nextDouble();
		while (i < longitudCromo) {
			
			//double alpha = rand.nextDouble();
			cromoHijo1.set(i, alpha*cromoPadre1Aux.get(i) + (1 - alpha)*cromoPadre2Aux.get(i));
			cromoHijo2.set(i, alpha*cromoPadre2Aux.get(i) + (1 - alpha)*cromoPadre1Aux.get(i));
			i++;
		}
		
		sustituyePadres(padre1, padre2, cromoHijo1, cromoHijo2, cromoPadre1Aux, cromoPadre2Aux);
	}
}
