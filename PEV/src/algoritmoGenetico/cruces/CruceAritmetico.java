package algoritmoGenetico.cruces;

import java.util.ArrayList;
import java.util.Random;

import algoritmoGenetico.individuos.Individuo;

public class CruceAritmetico extends Cruce {

	/*
	 * Nota esta clase solo funciona con funcion 4
	 */
	@Override
	public void cruza(ArrayList<Individuo> poblacion, double probCruce) {
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
	protected void cruzaPadres(Individuo padre1, Individuo padre2) {
		// TODO Auto-generated method stub
		
		ArrayList cromoPadre1 = padre1.getCromosoma();
		ArrayList cromoPadre2 = padre2.getCromosoma();
		
		double fitnessB1 = padre1.getFitness();
		double fitnessB2 = padre2.getFitness();
		
		ArrayList cromoHijo1 = new ArrayList();
		cromoHijo1.addAll(cromoPadre1);

		ArrayList cromoHijo2 = new ArrayList();
		cromoHijo2.addAll(cromoPadre2);
		
		int longitudCromo = padre1.getLongitudCromosoma();
		
		
	}

	@Override
	protected void seleccionaIndividuos(ArrayList<Individuo> poblacion) {
		// TODO Auto-generated method stub

	}

}
