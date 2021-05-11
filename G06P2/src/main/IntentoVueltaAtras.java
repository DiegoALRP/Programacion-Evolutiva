package main;

import java.util.ArrayList;
import java.util.HashSet;

import algoritmoGenetico.individuos.Individuo;
import algoritmoGenetico.individuos.NGramas;
import algoritmoGenetico.individuos.Texto;

public class IntentoVueltaAtras {

	private NGramas ng;
	private Texto claseTexto;
	private double mejorFitness;
	
	public IntentoVueltaAtras() {
		
		ng = new NGramas();
		ng.loadNGrams();
		
		StringBuilder st = new StringBuilder("Eqa ycwe aqqt aqcit v aqqtwecwb wecwb zn v aqqtwecwb wqcit wecwb aqqt?  Zr aqcit wecwb vii rep aqqt revr v aqqtwecwb wqcit, zn v aqqtwecwb wqcit wecwb aqqt.");
		StringBuilder st2 = new StringBuilder();
		st2.append(st);
		st2.append(" ");
		st2.append(st);
		
		claseTexto = new Texto(st, st2);
	}
	
	public void start() {
		
		ArrayList<Integer> cromosoma = new ArrayList<Integer>(26);
		Individuo ind = new Individuo(claseTexto, ng);
		HashSet<Integer> set = new HashSet<Integer>(26);
		mejorFitness = 0;
		
		funcionRecursiva(cromosoma, set, ind, 0);
	}
	
	public void funcionRecursiva(ArrayList<Integer> cromosoma, HashSet<Integer> set, Individuo ind, int numAdded) {
		
		for (int i = 0; i < 26; i++) {
			
			if (!set.contains(i)) {
				
				cromosoma.add(i);
				set.add(i);
				
				if (numAdded == 25) {
					
					ind.setCromosoma(cromosoma);
					double fitness = ind.calculateFitness();
					if (fitness > mejorFitness) {
						mejorFitness = fitness;
						
						System.out.println("Solucion");
						System.out.println("Fitness: " + fitness);
						System.out.println("Fenotipo: " + ind.getFenotipe());
					}
				}
				else {
					
					funcionRecursiva(cromosoma, set, ind, numAdded + 1);
				}
				
				cromosoma.remove(numAdded);
				set.remove(i);
			}
		}
	}
}
