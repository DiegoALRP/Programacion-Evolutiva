package algoritmoGenetico.cruces;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

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
	
	/**************************** ATRIBUTTES *******************************/

	/**************************** CONSTRUCTOR ******************************/
	
	/***************************** METHODS ********************************/
	/**
	 * [ES] Esta función selecciona los individuos a cruzar y los cruza dos a dos aplicando cruce por ciclos.
	 * 
	 * [EN] This function selects the individuals to cross and crosses them by pairs applying cicle crossover.
	 * 
	 * @param poblacion	[ES] La población original.
	 * 					[EN] The original population.
	 * 
	 * @param probCruce	[ES] La probabilidad de cruce.
	 * 					[EN] Cross probability.
	 */
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
	
	/**
	 * [ES] Un padre comienza heredando la primera posicion del otro. Para la posicion original que ha sido 
	 * sustituida, se consulta su ubicacion en el otro padre y "se baja" al padre actual siguiente un ciclo hasta
	 * que termina.
	 * 
	 * 
	 * [EN] One parent begins by inheriting the first position from the other. For the original position 
	 * that has been substituted, its location in the other parent is queried and "down-cycled" to the
	 * until it ends.
	 * 
	 * @param padre1	[ES] Primer padre.
	 * 					[EN] First parent.
	 * 
	 * @param padre2	[ES] Segundo padre.
	 * 					[EN] Second parent.
	 */	
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
