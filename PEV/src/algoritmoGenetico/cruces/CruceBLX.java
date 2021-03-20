package algoritmoGenetico.cruces;

import java.util.ArrayList;
import java.util.Random;

import algoritmoGenetico.individuos.Individuo;

public class CruceBLX extends Cruce{

	@Override
	public void cruza(ArrayList<Individuo> poblacion, double probCruce) {
		
		this.num_selec_cruce = 0;
		this.probCruce = probCruce;
		this.selec_cruce = new ArrayList<Integer>();
		this.tamPoblacion = poblacion.size();
		
		this.seleccionaIndividuos(poblacion);
		
		for (int i = 0; i < this.num_selec_cruce; i += 2) {
			
			cruzaPadres(poblacion.get(selec_cruce.get(i)), poblacion.get(selec_cruce.get(i + 1)));
		}
	}

	@Override
	protected void cruzaPadres(Individuo padre1, Individuo padre2) {
		// TODO Auto-generated method stub
		
		ArrayList<Double> cromoPadre1 = padre1.getCromosoma();
		ArrayList<Double> cromoPadre2 = padre2.getCromosoma();
		
		double fitnessP1 = padre1.getFitness();
		double fitnessP2 = padre2.getFitness();
		
		ArrayList<Double> cromoHijo1 = new ArrayList<Double>();
		cromoHijo1.addAll(cromoPadre1);

		ArrayList<Double> cromoHijo2 = new ArrayList<Double>();
		cromoHijo2.addAll(cromoPadre2);
		
		int longCromo = padre1.getLongitudCromosoma();
		Random rand = new Random();
		for (int i = 0; i < longCromo; i++) {
			
			double cmax = Math.max(cromoPadre1.get(i), cromoPadre2.get(i));
			double cmin = Math.min(cromoPadre1.get(i), cromoPadre2.get(i));
			
			double varI = cmax - cmin;
			double alpha = rand.nextDouble();
			double rangeMin = cmin - varI*alpha;
			double rangeMax = cmax + varI*alpha;
			
			double newGen1 = rangeMin + (rangeMax - rangeMin) * rand.nextDouble();
			double newGen2 = rangeMin + (rangeMax - rangeMin) * rand.nextDouble();
			
			cromoHijo1.set(i, newGen1);
			cromoHijo2.set(i, newGen2);
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
