package algoritmoGenetico.cruces;

import java.util.ArrayList;
import java.util.Random;

import algoritmoGenetico.individuos.Individuo;

public abstract class Cruce {

	protected ArrayList<Integer> selec_cruce;
	protected double probCruce;
	protected int punto_cruce;
	protected int num_selec_cruce;	//Numero de individuos seleccionados para cruzar
	protected int tamPoblacion;
	
	public abstract void cruza(ArrayList<Individuo> poblacion, double probCruce);
	protected abstract void cruzaPadres(Individuo padre1, Individuo padre2);
	
	protected void seleccionaIndividuos(ArrayList<Individuo> poblacion) {
		
		this.tamPoblacion = poblacion.size();
		Random rand = new Random();
		
		for (int i = 0; i < tamPoblacion; i++) {
			
			if (rand.nextDouble() < this.probCruce) {

				if (this.num_selec_cruce % 2 == 1) {
					
					if (!poblacion.get(i).getCromosoma().equals(poblacion.get(this.selec_cruce.get(this.num_selec_cruce - 1)).getCromosoma())) {
						
						this.selec_cruce.add(i);
						this.num_selec_cruce++;
					}
				}
				else {
					
					this.selec_cruce.add(i);
					this.num_selec_cruce++;
				}
			}
		}
		
		if ((num_selec_cruce % 2) == 1) {
			
			this.num_selec_cruce--;
			this.selec_cruce.remove(num_selec_cruce);
		}
	}
	
	protected void sustituyePadres(Individuo padre1, Individuo padre2, ArrayList cromoHijo1, ArrayList cromoHijo2,
			ArrayList cromoPadre1Aux, ArrayList cromoPadre2Aux) {
		
		double fitnessP1 = padre1.getFitness();
		double fitnessP2 = padre2.getFitness();
		
		padre1.setCromosoma(cromoHijo1);
		padre2.setCromosoma(cromoHijo2);
		
		double fitnessH1 = padre1.calculateFitness();
		double fitnessH2 = padre2.calculateFitness();
		
		if(padre1.getId().equals("Funcion 1")) {
			if (fitnessP1 > fitnessH1) padre1.setCromosoma(cromoPadre1Aux);
			if (fitnessP2 > fitnessH2) padre2.setCromosoma(cromoPadre2Aux);
		}
		else {
			
			if (fitnessP1 < fitnessH1) padre1.setCromosoma(cromoPadre1Aux);
			if (fitnessP2 < fitnessH2) padre2.setCromosoma(cromoPadre2Aux);
		}
	}
	
}