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
		this.selec_cruce = new ArrayList<Integer>();
		this.probCruce = probCruce;
		this.tamPoblacion = poblacion.size();
		
		this.seleccionaIndividuos(poblacion);
		
		for (int i = 0; i < poblacion.size(); i += 2) {
			
			cruzaPadres(poblacion.get(selec_cruce.get(i)), poblacion.get(selec_cruce.get(i + 1)));
		}
	}

	@Override
	protected void cruzaPadres(Individuo padre1, Individuo padre2) {
		// TODO Auto-generated method stub
		
		ArrayList<Double> cromoPadre1 = padre1.getCromosoma();
		ArrayList<Double> cromoPadre2 = padre2.getCromosoma();
		
		double fitnessB1 = padre1.getFitness();
		double fitnessB2 = padre2.getFitness();
		
		ArrayList<Double> cromoHijo1 = new ArrayList<Double>();
		cromoHijo1.addAll(cromoPadre1);

		ArrayList<Double> cromoHijo2 = new ArrayList<Double>();
		cromoHijo2.addAll(cromoPadre2);
		
		int i = 0;
		int longitudCromo = padre1.getLongitudCromosoma();
		Random rand = new Random();
		while (i < longitudCromo) {
			
			double alpha = rand.nextDouble();
			cromoHijo1.set(i, alpha*cromoPadre1.get(i) + (1 - alpha)*cromoPadre2.get(i));
			cromoHijo2.set(i, alpha*cromoPadre2.get(i) + (1 - alpha)*cromoPadre1.get(i));
			
			i++;
		}
		
		
	}

	@Override
	protected void seleccionaIndividuos(ArrayList<Individuo> poblacion) {
		
		this.tamPoblacion = poblacion.size();
		Random rand = new Random();
		
		for (int i = 0; i < tamPoblacion; i++) {
			
			if (rand.nextDouble() < this.probCruce) {
				
				this.selec_cruce.add(i);
				this.num_selec_cruce++;
			}
		}
		
		if ((num_selec_cruce % 2) == 1) {
			
			this.num_selec_cruce--;
			this.selec_cruce.remove(num_selec_cruce);
		}
	}

}
