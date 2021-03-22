package algoritmoGenetico.cruces;

import java.util.ArrayList;
import java.util.Random;

import algoritmoGenetico.individuos.Individuo;

public class CruceMonopunto extends Cruce {
	
	@Override
	public void cruza(ArrayList<Individuo> poblacion, double probCruce) {
		
		this.num_selec_cruce = 0;
		this.probCruce = probCruce;
		this.selec_cruce = new ArrayList<Integer>();
		this.tamPoblacion = poblacion.size();
		
		this.seleccionaIndividuos(poblacion);
		
		Random rand = new Random();
		this.punto_cruce = rand.nextInt(this.tamPoblacion - 2) + 1;
		
		for (int i = 0; i < this.num_selec_cruce; i += 2) {
			
			if (selec_cruce.get(i) == selec_cruce.get(i+1)) {
				
			}
			cruzaPadres(poblacion.get(selec_cruce.get(i)), poblacion.get(selec_cruce.get(i + 1)));
		}
	}
	
	@Override
	protected void cruzaPadres(Individuo padre1, Individuo padre2) {
		
		ArrayList cromoPadre1 = padre1.getCromosoma();
		ArrayList cromoPadre2 = padre2.getCromosoma();
		
		ArrayList cromoPadre1Aux = new ArrayList();
		cromoPadre1Aux.addAll(cromoPadre1);
		
		ArrayList cromoPadre2Aux = new ArrayList();
		cromoPadre2Aux.addAll(cromoPadre2);
		
		double fitnessP1 = padre1.getFitness();
		double fitnessP2 = padre2.getFitness();
		
		ArrayList cromoHijo1 = new ArrayList();
		cromoHijo1.addAll(cromoPadre1Aux);

		ArrayList cromoHijo2 = new ArrayList();
		cromoHijo2.addAll(cromoPadre2Aux);
		
		int longitudCromo = padre1.getLongitudCromosoma();
		
		int longi = this.punto_cruce;
		while (longi < longitudCromo) {
			
			cromoHijo1.set(longi, cromoPadre2Aux.get(longi));
			cromoHijo2.set(longi, cromoPadre1Aux.get(longi));
			longi++;
		}
		
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
		
		//if (fitnessA1 < fitnessB1) padre1.setCromosoma(cromoPadre1);
		//if (fitnessA2 < fitnessB2) padre2.setCromosoma(cromoPadre2);
		
		
	}

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
}
