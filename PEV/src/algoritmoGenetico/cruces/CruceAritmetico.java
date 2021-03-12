package algoritmoGenetico.cruces;

import java.util.ArrayList;
import java.util.Random;

import algoritmoGenetico.individuos.Individuo;

public class CruceAritmetico extends Cruce {

	/*
	 * Nota esta clase solo funciona con funcion 4
	 */
	@Override
	public <T> void cruza(ArrayList<Individuo<T>> poblacion, double probCruce) {
		// TODO Auto-generated method stub

		this.num_selec_cruce = 0;
		this.selec_cruce.clear();
		
		this.seleccionaIndividuos(poblacion);
		
		Random rand = new Random();
		
		for (int i = 0; i < poblacion.size(); i += 2) {
			
			cruzaPadres(poblacion.get(selec_cruce.get(i)), poblacion.get(selec_cruce.get(i + 1)));
		}
	}

	@Override
	protected <T> void cruzaPadres(Individuo<T> padre1, Individuo<T> padre2) {
		// TODO Auto-generated method stub
		
		ArrayList<T> cromoPadre1 = padre1.getCromosoma();
		ArrayList<T> cromoPadre2 = padre2.getCromosoma();
		
		double fitnessB1 = padre1.getFitness();
		double fitnessB2 = padre2.getFitness();
		
		ArrayList<T> cromoHijo1 = new ArrayList<T>();
		cromoHijo1.addAll(cromoPadre1);

		ArrayList<T> cromoHijo2 = new ArrayList<T>();
		cromoHijo2.addAll(cromoPadre2);
		
		int longitudCromo = padre1.getLongitudCromosoma();
		
		
	}

	@Override
	protected <T> void seleccionaIndividuos(ArrayList<Individuo<T>> poblacion) {
		// TODO Auto-generated method stub

	}

}
