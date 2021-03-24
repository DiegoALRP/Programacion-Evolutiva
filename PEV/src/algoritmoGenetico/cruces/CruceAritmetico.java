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
		
		for (int i = 0; i < this.num_selec_cruce; i += 2) {
			
			cruzaPadres(poblacion.get(selec_cruce.get(i)), poblacion.get(selec_cruce.get(i + 1)));
		}
	}

	@Override
	protected void cruzaPadres(Individuo padre1, Individuo padre2) {
		// TODO Auto-generated method stub
		
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
			
			cromoHijo1.set(i, alpha*cromoPadre1Aux.get(i) + (1 - alpha)*cromoPadre2Aux.get(i));
			cromoHijo2.set(i, alpha*cromoPadre2Aux.get(i) + (1 - alpha)*cromoPadre1Aux.get(i));
			i++;
		}
		
		sustituyePadres(padre1, padre2, cromoHijo1, cromoHijo2, cromoPadre1Aux, cromoPadre2Aux);
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
