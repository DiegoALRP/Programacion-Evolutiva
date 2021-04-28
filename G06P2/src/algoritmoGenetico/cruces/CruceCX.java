package algoritmoGenetico.cruces;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import algoritmoGenetico.individuos.Individuo;

/**
 * Universidad Complutense de Madrid.
 * Programaci�n Evolutiva.
 * Grupo A 2021.
 * Profesor:
 * 	-Carlos Cervigon Ruckauer.
 * 
 * Pr�ctica 2
 * 
 * Clase Cruce CX (Por Ciclos).
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodr�guez Pereira.
 *
 */
public class CruceCX extends Cruce {
	
	/**************************** ATRIBUTTES *******************************/

	/**************************** CONSTRUCTOR ******************************/
	
	/***************************** METHODS ********************************/
	@Override
	public void cruza(ArrayList<Individuo> poblacion, double probCruce) {
		
		this.num_selec_cruce = 0;
		this.probCruce = probCruce;
		this.selec_cruce = new ArrayList<Integer>();
		this.tamPoblacion = poblacion.size();
		
		this.seleccionaIndividuos(poblacion);
		
		for (int i = 0; i < this.num_selec_cruce; i += 2) {
			this.numCruce++;
			cruzaPadres(poblacion.get(selec_cruce.get(i)), poblacion.get(selec_cruce.get(i + 1)));
		}
	}

	@Override
	protected void cruzaPadres(Individuo padre1, Individuo padre2) {
		
		ArrayList<Integer> cromoPadre1 = padre1.getCromosoma();
		ArrayList<Integer> cromoPadre2 = padre2.getCromosoma();
		
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

	
	/**************************** GETTERS & SETTERS ********************************/
}
