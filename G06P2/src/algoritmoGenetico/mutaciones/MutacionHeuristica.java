package algoritmoGenetico.mutaciones;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import algoritmoGenetico.individuos.Individuo;
import algoritmoGenetico.individuos.NGramas;
import algoritmoGenetico.individuos.Texto;

/**
 * Universidad Complutense de Madrid.
 * Programación Evolutiva.
 * Grupo A 2021.
 * Profesor:
 * 	-Carlos Cervigon Ruckauer.
 * 
 * Práctica 2
 * 
 * Clase Mutación Heurística.
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodríguez Pereira.
 *
 */
public class MutacionHeuristica extends Mutacion {

	
	/**************************** ATRIBUTTES *******************************/
	private double mejorFitness;
	private Individuo mejorIndividuo;
	private int n;
	private ArrayList<Integer> cromosoma;
	private Texto claseTexto;
	private NGramas ngramas;
	
	
	/**************************** CONSTRUCTOR ******************************/
	
	
	/***************************** METHODS ********************************/
	
	/**
	 * [ES] Esta función muta un individuo siguiendo el patron de mutación heuristica.
	 * Dado un indiviuo, se seleccionan 3 posiciones aleatorias.
	 * Se calculará el fitness de las permutacones y se seleccionará aquella de 
	 * mayor fitness
	 * 
	 * 
	 * [EN] This function mutates an individual following the heuristic mutation pattern.
	 * Given an individual, 3 random positions are selected.
	 * The fitness of the permutations will be calculated and the one with the 
	 * higher fitness will be selected.
	 * 
	 * 
	 * 
	 * @param Individuo	[ES] El individuo.
	 * 					[EN] The  individual.
	 * 
	 */
	@Override
	protected void mutaIndividuo(Individuo individuo) {
		
		cromosoma = individuo.getCromosoma();
		ArrayList<Integer> cromosomaAux = new ArrayList<Integer>();
		cromosomaAux.addAll(cromosoma);
		this.claseTexto = individuo.getTexto();
		this.ngramas = individuo.getNGrama();
		
		int lonCromo = cromosoma.size();
		
		Random rand = new Random();
		n = rand.nextInt(2) + 2;
		
		HashSet<Integer> posiciones = new HashSet<Integer>(n);
		
		for (int i = 0; i < n; i++) {
			
			int pos = rand.nextInt(lonCromo);
			while (posiciones.contains(pos)) {
				
				pos = rand.nextInt(lonCromo);
			}
			posiciones.add(pos);
		}
		
		ArrayList<Integer> orden = new ArrayList<Integer>(n);
		
		permutacion(posiciones, posiciones, orden, 0);
		
		individuo.setCromosoma(mejorIndividuo.getCromosoma());
	}
	/**
	 * [ES] Función recursiva que calcula la permutación y  el fitness
	 * cuando está completamente construida
	 * 
	 * 
	 * [EN] Recursive function that calculates the permutation and fitness
	 * when it is fully built 
	 * 
	 */
	private void permutacion(HashSet<Integer> posiciones, HashSet<Integer> set, ArrayList<Integer> orden, int i) {
		
		for (int pos : set) {
			
			HashSet<Integer> copySet = new HashSet<Integer>(set);
			copySet.remove(pos);
			ArrayList<Integer> copyOrden = new ArrayList<Integer>(orden);
			copyOrden.add(pos);
			
			if (i == n - 1) {
				
				calculaFitness(posiciones, copyOrden);
			}
			permutacion(posiciones, copySet, copyOrden, i + 1);
		}
	}
	
	/**
	 * [ES] Esta función introduce los valores del parametro posiciones en el cromosoma original
	 * y calcula el fitness 
	 * 
	 * 
	 * [EN] This function enters the values of the parameter positions in the original chromosome
	 * and calculates the fitness 
	 * 
	 * 
	 * 
	 */
	private void calculaFitness(HashSet<Integer> posiciones, ArrayList<Integer> orden) {
		
		ArrayList<Integer> cromosomaAux = new ArrayList<Integer>();
		cromosomaAux.addAll(cromosoma);
		Object[] array = posiciones.toArray();
		
		for (int i = 0; i < n/2; i++) {
			
			cromosomaAux.set((int) array[i], this.cromosoma.get(orden.get(i)));
			cromosomaAux.set((int) array[n - i - 1], this.cromosoma.get(orden.get(n - i - 1)));
		}
		if (n == 3) {
			
			cromosomaAux.set((int) array[1], this.cromosoma.get(orden.get(1)));
		}
		
		Individuo ind = new Individuo(claseTexto, ngramas, cromosomaAux);
		double fitness = ind.calculateFitness();
		
		if (fitness > mejorFitness) {
			mejorFitness = fitness;
			mejorIndividuo = ind;
		}
		
		/**************************** GETTERS & SETTERS ********************************/
	}
}
