package algoritmoGenetico.cruces;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
 * Clase Cruce CX (Por Ciclos).
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodríguez Pereira.
 *
 */
public class CruceCX extends Cruce {

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
		
		ArrayList<Integer> cromoPadre1 = padre1.getCromosoma();
		ArrayList<Integer> cromoPadre2 = padre2.getCromosoma();
		/*ArrayList<Integer> cromoPadre1 = new ArrayList<Integer>();
		ArrayList<Integer> cromoPadre2 = new ArrayList<Integer>();
		
		cromoPadre1.add(1);
		cromoPadre1.add(2);
		cromoPadre1.add(3);
		cromoPadre1.add(4);
		cromoPadre1.add(5);
		cromoPadre1.add(6);
		cromoPadre1.add(7);
		cromoPadre1.add(8);
		cromoPadre1.add(9);
		
		cromoPadre2.add(4);
		cromoPadre2.add(1);
		cromoPadre2.add(2);
		cromoPadre2.add(8);
		cromoPadre2.add(7);
		cromoPadre2.add(6);
		cromoPadre2.add(9);
		cromoPadre2.add(3);
		cromoPadre2.add(5);*/
		
		int longCromo = cromoPadre1.size();
		
		HashMap<Integer, Integer> mapaIndices1 = new HashMap<Integer, Integer>(longCromo);
		HashMap<Integer, Integer> mapaIndices2 = new HashMap<Integer, Integer>(longCromo);
		ArrayList<Integer> cromoHijo1 = new ArrayList<Integer>(longCromo);
		ArrayList<Integer> cromoHijo2 = new ArrayList<Integer>(longCromo);
		ArrayList<Integer> cromoPadre1Aux = new ArrayList<Integer>(longCromo);
		ArrayList<Integer> cromoPadre2Aux = new ArrayList<Integer>(longCromo);
		
		for (int i = 0; i < longCromo; i++) {
			
			cromoHijo1.add(cromoPadre1.get(i));
			cromoHijo2.add(cromoPadre2.get(i));
			cromoPadre1Aux.add(cromoPadre1.get(i));
			cromoPadre2Aux.add(cromoPadre2.get(i));
			mapaIndices1.put(cromoHijo1.get(i), i);
			mapaIndices2.put(cromoHijo2.get(i), i);
		}
		
		HashSet<Integer> set1 = new HashSet<Integer>(longCromo);
		HashSet<Integer> set2 = new HashSet<Integer>(longCromo);
		
		int k = 0;
		int toAdd = cromoHijo1.get(k);
		while (!set1.contains(toAdd)) {
			
			set1.add(toAdd);
			toAdd = cromoHijo2.get(k);
			k = mapaIndices1.get(toAdd);
			mapaIndices1.remove(toAdd);
		}
		
		k = 0;
		toAdd = cromoHijo2.get(k);
		while (!set2.contains(toAdd)) {
			
			set2.add(toAdd);
			toAdd = cromoHijo1.get(k);
			k = mapaIndices2.get(toAdd);
			mapaIndices2.remove(toAdd);
		}
		
		//Probar iterando por el hashmap
		/*for (Integer num : mapaIndices1.) {
			
		}*/
		
		int a = 0;
		int b = 0;
		for (int i = 0; i < longCromo; i++) {
			
			a = cromoHijo1.get(i);
			b = cromoHijo2.get(i);
			if (!set1.contains(a)) {
				cromoHijo1.set(i, cromoPadre2.get(i));
			}
			if (!set2.contains(b)) {
				cromoHijo2.set(i, cromoPadre1.get(i));
			}
		}
		
		sustituyePadres(padre1, padre2, cromoHijo1, cromoHijo2, cromoPadre1Aux, cromoPadre2Aux);
	}

}
