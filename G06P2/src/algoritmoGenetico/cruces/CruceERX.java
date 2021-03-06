package algoritmoGenetico.cruces;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;

import algoritmoGenetico.individuos.Individuo;

/**
 * Universidad Complutense de Madrid.
 * Programaci?n Evolutiva.
 * Grupo A 2021.
 * Profesor:
 * 	-Carlos Cervigon Ruckauer.
 * 
 * Pr?ctica 2
 * 
 * Clase Cruce ERX (Cruce por Recombinaci?n de Rutas).
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodr?guez Pereira.
 *
 */
public class CruceERX extends Cruce{

	/**************************** ATRIBUTTES *******************************/
	private int longCromo;
	private boolean encontrado;
	
	/**************************** CONSTRUCTOR ******************************/
	
	/***************************** METHODS ********************************/
	/**
	 * [ES] Esta funci?n selecciona los individuos a cruzar y los cruza dos a dos aplicando recombinacion de rutas.
	 * 
	 * [EN] This function selects the individuals to cross and crosses them by pairs applying rute recalculation.
	 * 
	 * @param poblacion	[ES] La poblaci?n original.
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
	 * [ES] Para cada dos progenitores se calcula la matriz de adyacencia(matriz con los vecinos).
	 * Una vez construida, para cada posicion que se quiera intercambiar se seleccionar?.
	 * La siguiente posici?n  ser? aquella con conexiones minimas en la tabla de adyacencia.
	 * 
	 * [EN] For both of the two parents, the adjacent matrix (matrix with neighbours) is calculated.
	 * Once built, for each position to be exchanged, it will cross.
	 * The next position will be the one with minimal connections in the adjacency table.
	 * 
	 * @param poblacion	[ES] La poblaci?n original.
	 * 					[EN] The original population.
	 * 
	 * @param probCruce	[ES] La probabilidad de cruce.
	 * 					[EN] Cross probability.
	 */
	@Override
	protected void cruzaPadres(Individuo padre1, Individuo padre2) {
		
		ArrayList<Integer> cromoPadre1 = padre1.getCromosoma();
		ArrayList<Integer> cromoPadre2 = padre2.getCromosoma();
		
		longCromo = cromoPadre1.size();
		
		ArrayList<Integer> cromoPadre1Aux = new ArrayList<Integer>(longCromo);
		ArrayList<Integer> cromoPadre2Aux = new ArrayList<Integer>(longCromo);
		
		HashMap<Integer, HashSet<Integer>> tablaConectividades = new HashMap<Integer, HashSet<Integer>>(longCromo);
		
		int a;
		int b;
		for (int i = 0; i < longCromo; i++) {
			
			a = cromoPadre1.get(i);
			b = cromoPadre2.get(i);
			
			cromoPadre1Aux.add(a);
			cromoPadre2Aux.add(b);
			
			if (tablaConectividades.containsKey(a)) {
				
				tablaConectividades.get(a).add(cromoPadre1.get(Math.floorMod(i + 1, longCromo)));
				tablaConectividades.get(a).add(cromoPadre1.get(Math.floorMod(i - 1, longCromo)));
			}
			else {
				
				HashSet<Integer> set = new HashSet<Integer>(4);
				set.add(cromoPadre1.get(Math.floorMod(i + 1, longCromo)));
				set.add(cromoPadre1.get(Math.floorMod(i - 1, longCromo)));
				tablaConectividades.put(a, set);
			}
			
			if (tablaConectividades.containsKey(b)) {
				
				tablaConectividades.get(b).add(cromoPadre2.get(Math.floorMod(i + 1, longCromo)));
				tablaConectividades.get(b).add(cromoPadre2.get(Math.floorMod(i - 1, longCromo)));
			}
			else {
				
				HashSet<Integer> set = new HashSet<Integer>(4);
				set.add(cromoPadre2.get(Math.floorMod(i + 1, longCromo)));
				set.add(cromoPadre2.get(Math.floorMod(i - 1, longCromo)));
				tablaConectividades.put(b, set);
			}
		}
		
		ArrayList<Integer> cromoHijo1 = new ArrayList<Integer>(longCromo);
		ArrayList<Integer> cromoHijo1Aux = new ArrayList<Integer>(longCromo);
		HashSet<Integer> setCromoHijo1 = new HashSet<Integer>(longCromo);
		cromoHijo1Aux.add(cromoPadre1.get(0));
		setCromoHijo1.add(cromoPadre1.get(0));
		
		encontrado = false;
		this.funcionRecursiva(cromoHijo1Aux, setCromoHijo1, 1, tablaConectividades, cromoHijo1);
		
		ArrayList<Integer> cromoHijo2 = new ArrayList<Integer>(longCromo);
		ArrayList<Integer> cromoHijo2Aux = new ArrayList<Integer>(longCromo);
		HashSet<Integer> setCromoHijo2 = new HashSet<Integer>(longCromo);
		cromoHijo2Aux.add(cromoPadre2.get(0));
		setCromoHijo2.add(cromoPadre2.get(0));
		
		encontrado = false;
		this.funcionRecursiva(cromoHijo2Aux, setCromoHijo2, 1, tablaConectividades, cromoHijo2);
		
		sustituyePadres(padre1, padre2, cromoHijo1, cromoHijo2, cromoPadre1Aux, cromoPadre2Aux);
	}

	private void funcionRecursiva(ArrayList<Integer> cromosoma, HashSet<Integer> setCromosoma, int numAdded,
			HashMap<Integer, HashSet<Integer>> tablaConectividades, ArrayList<Integer> cromosomaSol) {
		
		if (!this.encontrado) {
			ArrayList<Integer> array = new ArrayList<Integer>(tablaConectividades.get(cromosoma.get(numAdded - 1)));
			Collections.sort(array, new Comparator<Integer>() {
	
				@Override
				public int compare(Integer o1, Integer o2) {
					
					return tablaConectividades.get(o1).size() - tablaConectividades.get(o2).size();
				}
			});
			
			for (int toAdd : array) {
				
				if (!setCromosoma.contains(toAdd) && !this.encontrado) {
					
					cromosoma.add(toAdd);
					setCromosoma.add(toAdd);
					
					if (numAdded == longCromo - 1) {
						
						cromosomaSol.addAll(cromosoma);
						encontrado = true;
					}
					else {
						
						funcionRecursiva(cromosoma, setCromosoma, numAdded + 1, tablaConectividades, cromosomaSol);
					}
					
					cromosoma.remove(numAdded);
					setCromosoma.remove(toAdd);
				}
			}
		}
	}
	
	/**************************** GETTERS & SETTERS ********************************/
}
