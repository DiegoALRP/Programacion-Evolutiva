package algoritmoGenetico.cruces;

import java.util.ArrayList;
import java.util.Random;

import algoritmoGenetico.individuos.Individuo;

public class CruceUniforme extends Cruce {

	@Override
	public <T> void cruza(ArrayList<Individuo<T>> poblacion, double probCruce) {
		// TODO Auto-generated method stub

		this.num_selec_cruce = 0;
		this.selec_cruce.clear();
		
		this.seleccionaIndividuos(poblacion);
		
		System.out.println("Antes:\n");
		for (int i = 0; i < poblacion.size(); i++) {
			
			System.out.println(poblacion.get(i).printCromosoma() + "\n");
		}
		for (int i = 0; i < this.num_selec_cruce; i += 2) {
			
			cruzaPadres(poblacion.get(selec_cruce.get(i)), poblacion.get(selec_cruce.get(i + 1)));
		}
		
		System.out.println("Punto de cruce: " + this.punto_cruce);
		System.out.println("Numero de Seleccionados: " + this.num_selec_cruce);
		
		System.out.println("Despues:\n");
		for (int i = 0; i < poblacion.size(); i++) {
			
			System.out.println(poblacion.get(i).printCromosoma() + "\n");
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
		
		int i = 0;
		Random rand = new Random();
		while (i < longitudCromo) {
			
			if (rand.nextDouble() < this.probCruce) {
				cromoHijo1.set(i, cromoPadre2.get(i));
				cromoHijo2.set(i, cromoPadre1.get(i));
			}
			i++;
		}
		
		padre1.setCromosoma(cromoHijo1);
		padre2.setCromosoma(cromoHijo2);
		
		double fitnessA1 = padre1.getFitness();
		double fitnessA2 = padre2.getFitness();
		
		if (fitnessA1 < fitnessB1) padre1.setCromosoma(cromoPadre1);
		if (fitnessA2 < fitnessB2) padre2.setCromosoma(cromoPadre2);
	}

	@Override
	protected <T> void seleccionaIndividuos(ArrayList<Individuo<T>> poblacion) {
		// TODO Auto-generated method stub

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
