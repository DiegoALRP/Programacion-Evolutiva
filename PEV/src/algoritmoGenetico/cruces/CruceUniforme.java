package algoritmoGenetico.cruces;

import java.util.ArrayList;
import java.util.Random;

import algoritmoGenetico.individuos.Individuo;

public class CruceUniforme extends Cruce {

	@Override
	public void cruza(ArrayList<Individuo> poblacion, double probCruce) {
		// TODO Auto-generated method stub

		this.num_selec_cruce = 0;
		this.selec_cruce = new ArrayList<Integer>();
		this.probCruce = probCruce;
		
		this.seleccionaIndividuos(poblacion);
		
		for (int i = 0; i < this.num_selec_cruce; i += 2) {
			
			cruzaPadres(poblacion.get(selec_cruce.get(i)), poblacion.get(selec_cruce.get(i + 1)));
		}
	}

	@Override
	protected void cruzaPadres(Individuo padre1, Individuo padre2) {
		// TODO Auto-generated method stub

		ArrayList cromoPadre1 = padre1.getCromosoma();
		ArrayList cromoPadre2 = padre2.getCromosoma();
		
		ArrayList cromoPadre1Aux = new ArrayList();
		cromoPadre1Aux.addAll(cromoPadre1);
		
		ArrayList cromoPadre2Aux = new ArrayList();
		cromoPadre2Aux.addAll(cromoPadre2);
		
		/*double fitnessB1 = padre1.getFitness();
		double fitnessB2 = padre2.getFitness();*/
		
		ArrayList cromoHijo1 = new ArrayList();
		cromoHijo1.addAll(cromoPadre1Aux);

		ArrayList cromoHijo2 = new ArrayList();
		cromoHijo2.addAll(cromoPadre2Aux);
		
		int longitudCromo = padre1.getLongitudCromosoma();
		int i = 0;
		Random rand = new Random();
		while (i < longitudCromo) {
			
			if (rand.nextDouble() < this.probCruce) {
				
				cromoHijo1.set(i, cromoPadre2Aux.get(i));
				cromoHijo2.set(i, cromoPadre1Aux.get(i));
			}
			i++;
		}
		
		sustituyePadres(padre1, padre2, cromoHijo1, cromoHijo2, cromoPadre1Aux, cromoPadre2Aux);
		
		/*padre1.setCromosoma(cromoHijo1);
		padre2.setCromosoma(cromoHijo2);*/
		
		/*double fitnessA1 = padre1.getFitness();
		double fitnessA2 = padre2.getFitness();
		
		if (fitnessA1 < fitnessB1) padre1.setCromosoma(cromoPadre1);
		if (fitnessA2 < fitnessB2) padre2.setCromosoma(cromoPadre2);*/
	}

	/*@Override
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
	}*/

}
