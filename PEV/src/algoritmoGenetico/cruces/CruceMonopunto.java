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
		
		/*System.out.println("Antes:\n");
		for (int i = 0; i < poblacion.size(); i++) {
			
			System.out.println(poblacion.get(i).printCromosoma() + "\n");
		}*/
		for (int i = 0; i < this.num_selec_cruce; i += 2) {
			
			cruzaPadres(poblacion.get(selec_cruce.get(i)), poblacion.get(selec_cruce.get(i + 1)));
		}
		
		//System.out.println("Punto de cruce: " + this.punto_cruce);
		//sSystem.out.println("Numero de Seleccionados: " + this.num_selec_cruce);
		
		/*System.out.println("Despues:\n");
		for (int i = 0; i < poblacion.size(); i++) {
			
			System.out.println(poblacion.get(i).printCromosoma() + "\n");
		}*/
	}
	
	@Override
	protected void cruzaPadres(Individuo padre1, Individuo padre2) {
		
		ArrayList cromoPadre1 = padre1.getCromosoma();
		ArrayList cromoPadre2 = padre2.getCromosoma();
		
		double fitnessB1 = padre1.getFitness();
		double fitnessB2 = padre2.getFitness();
		
		ArrayList cromoHijo1 = new ArrayList();
		cromoHijo1.addAll(cromoPadre1);

		ArrayList cromoHijo2 = new ArrayList();
		cromoHijo2.addAll(cromoPadre2);
		
		int longitudCromo = padre1.getLongitudCromosoma();
		
		int longi = this.punto_cruce;
		while (longi < longitudCromo) {
			
			cromoHijo1.set(longi, cromoPadre2.get(longi));
			cromoHijo2.set(longi, cromoPadre1.get(longi));
			longi++;
		}
		
		padre1.setCromosoma(cromoHijo1);
		padre2.setCromosoma(cromoHijo2);
		
		double fitnessA1 = padre1.getFitness();
		double fitnessA2 = padre2.getFitness();
		
		if (fitnessA1 < fitnessB1) padre1.setCromosoma(cromoPadre1);
		if (fitnessA2 < fitnessB2) padre2.setCromosoma(cromoPadre2);
		
		
	}

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
