package algoritmoGenetico.cruces;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import algoritmoGenetico.individuos.Individuo;

public class CrucePorOrden extends Cruce{
	
	private int punto1;
	private int punto2;

	@Override
	public void cruza(ArrayList<Individuo> poblacion, double probCruce) {
		
		this.num_selec_cruce = 0;
		this.probCruce = probCruce;
		this.selec_cruce = new ArrayList<Integer>();
		this.tamPoblacion = poblacion.size();
		
		int longitudCromosoma = 26;
		
		this.seleccionaIndividuos(poblacion);
		
		Random rand = new Random();
		punto1 = rand.nextInt(25);
		punto2 = rand.nextInt(26 - punto1) + punto1;
		
		for (int i = 0; i < this.num_selec_cruce; i += 2) {
			
			cruzaPadres(poblacion.get(selec_cruce.get(i)), poblacion.get(selec_cruce.get(i + 1)));
		}
	}
	
	protected void cruzaPadres(Individuo padre1, Individuo padre2) {
		
		ArrayList<Integer> cromoPadre1 = padre1.getCromosoma();
		ArrayList<Integer> cromoPadre2 = padre2.getCromosoma();
		
		ArrayList<Integer> cromoPadre1Aux = new ArrayList<Integer>();
		cromoPadre1Aux.addAll(cromoPadre1);
		
		ArrayList<Integer> cromoPadre2Aux = new ArrayList<Integer>();
		cromoPadre2Aux.addAll(cromoPadre2);
		
		ArrayList<Integer> cromoHijo1 = new ArrayList<Integer>();
		cromoHijo1.addAll(cromoPadre1Aux);
		HashSet<Integer> setHijo1 = new HashSet<Integer>(26);

		ArrayList<Integer> cromoHijo2 = new ArrayList<Integer>();
		cromoHijo2.addAll(cromoPadre2Aux);
		HashSet<Integer> setHijo2 = new HashSet<Integer>(26);
		
		int longitudCromo = 26;
		int longi = this.punto1;
		int added1 = 0, added2 = 0;
		while (longi < this.punto2) {
			
			cromoHijo1.set(longi, cromoPadre2Aux.get(longi));
			setHijo1.add(cromoPadre2Aux.get(longi));
			cromoHijo2.set(longi, cromoPadre1Aux.get(longi));
			setHijo2.add(cromoPadre1Aux.get(longi));
			longi++;
			added1++;
			added2++;
		}
		
		int i1 = (this.punto2 + 1)%26;
		int j1 = (this.punto2 + 1)%26;
		while (added1 <= 26) {
			
			int toAdd = cromoPadre2Aux.get(j1);
			if (!setHijo1.contains(toAdd)) {
				
				cromoHijo1.set(i1, toAdd);
				i1 = (i1 + 1)%26;
				added1++;
			}
			j1 = (j1 + 1)%26;
		}
		
		int i2 = (this.punto2 + 1)%26;
		int j2 = (this.punto2 + 1)%26;
		while (added2 <= 26) {
			
			int toAdd = cromoPadre2Aux.get(j2);
			if (!setHijo1.contains(toAdd)) {
				
				cromoHijo1.set(i2, toAdd);
				i2 = (i2 + 1)%26;
				added2++;
			}
			j2 = (j2 + 1)%26;
		}
		
		sustituyePadres(padre1, padre2, cromoHijo1, cromoHijo2, cromoPadre1Aux, cromoPadre2Aux);
	}
}
