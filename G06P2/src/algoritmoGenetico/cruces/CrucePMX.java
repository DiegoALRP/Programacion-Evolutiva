package algoritmoGenetico.cruces;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import algoritmoGenetico.individuos.Individuo;

/**
 * Universidad Complutense de Madrid.
 * Programación Evolutiva.
 * Grupo A 2021.
 * Profesor:
 * 	-Carlos Cervigon Ruckauer.
 * 
 * Práctica 2
 * 
 * Clase Cruce PMX.
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodríguez Pereira.
 *
 */
public class CrucePMX extends Cruce {

	private int punto1;
	private int punto2;
	private final int longCromosoma = 26;
	
	@Override
	public void cruza(ArrayList<Individuo> poblacion, double probCruce) {
		
		this.num_selec_cruce = 0;
		this.probCruce = probCruce;
		this.selec_cruce = new ArrayList<Integer>();
		this.tamPoblacion = poblacion.size();
		
		this.seleccionaIndividuos(poblacion);
		
		Random rand = new Random();
		punto1 = rand.nextInt(20);
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
		
		ArrayList<Integer> cromoHijo1Aux = new ArrayList<>();
		ArrayList<Integer> cromoHijo2Aux = new ArrayList<Integer>();
		
		int longi = this.punto1;
		int added1 = 0, added2 = 0;
		while (longi <= this.punto2) {
			
			cromoHijo1.set(longi, cromoPadre2Aux.get(longi));
			cromoHijo1Aux.add(cromoPadre2Aux.get(longi));
			setHijo1.add(cromoPadre2Aux.get(longi));
			cromoHijo2.set(longi, cromoPadre1Aux.get(longi));
			cromoHijo2Aux.add(cromoPadre1Aux.get(longi));
			setHijo2.add(cromoPadre1Aux.get(longi));
			added1++;
			added2++;
			longi++;
		}
		
		int i1 = (this.punto2 + 1)%longCromosoma;
		while (added1 < longCromosoma) {
			
			int toAdd = cromoPadre1Aux.get(i1);
			if (!setHijo1.contains(toAdd)) {
				
				cromoHijo1.set(i1, toAdd);
				setHijo1.add(toAdd);
				added1++;
			}
			else {
				
				int index = cromoHijo1Aux.indexOf(toAdd);
				toAdd = cromoHijo2Aux.get(index);
				while (setHijo1.contains(toAdd)) {
					
					index = cromoHijo1Aux.indexOf(toAdd);
					toAdd = cromoHijo2Aux.get(index);
				}
				
				cromoHijo1.set(i1, toAdd);
				setHijo1.add(toAdd);
				added1++;
			}
			i1 = (i1 + 1)%longCromosoma;
		}
		
		int i2 = (this.punto2 + 1)%longCromosoma;
		while (added2 < longCromosoma) {
			
			int toAdd = cromoPadre2Aux.get(i2);
			if (!setHijo2.contains(toAdd)) {
				
				cromoHijo2.set(i2, toAdd);
				setHijo2.add(toAdd);
				added2++;
			}
			else {
				
				int index = cromoHijo2Aux.indexOf(toAdd);
				toAdd = cromoHijo1Aux.get(index);
				while (setHijo2.contains(toAdd)) {
					
					index = cromoHijo2Aux.indexOf(toAdd);
					toAdd = cromoHijo1Aux.get(index);
				}
				
				cromoHijo2.set(i2, toAdd);
				setHijo2.add(toAdd);
				added2++;
			}
			i2 = (i2 + 1)%longCromosoma;
		}
		
		sustituyePadres(padre1, padre2, cromoHijo1, cromoHijo2, cromoPadre1Aux, cromoPadre2Aux);
	}

}
