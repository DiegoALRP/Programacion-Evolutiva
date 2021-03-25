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
 * Clase Cruce BLX.
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodríguez Pereira.
 *
 */
public class CruceBLX extends Cruce{

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
		cromoHijo1.addAll(cromoPadre1Aux);

		ArrayList<Double> cromoHijo2 = new ArrayList<Double>();
		cromoHijo2.addAll(cromoPadre2Aux);
		
		int longCromo = padre1.getLongitudCromosoma();
		Random rand = new Random();
		double alpha = rand.nextDouble();
		for (int i = 0; i < longCromo; i++) {
			
			double cmax = Math.max(cromoPadre1Aux.get(i), cromoPadre2Aux.get(i));
			double cmin = Math.min(cromoPadre1Aux.get(i), cromoPadre2Aux.get(i));
			
			double varI = cmax - cmin;
			double rangeMin = cmin - varI*alpha;
			double rangeMax = cmax + varI*alpha;
			
			double newGen1 = rangeMin + (rangeMax - rangeMin) * rand.nextDouble();
			double newGen2 = rangeMin + (rangeMax - rangeMin) * rand.nextDouble();
			
			cromoHijo1.set(i, newGen1);
			cromoHijo2.set(i, newGen2);
		}
		
		sustituyePadres(padre1, padre2, cromoHijo1, cromoHijo2, cromoPadre1Aux, cromoPadre2Aux);
	}
}
