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
 * Clase Cruce Por Orden.
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodríguez Pereira.
 *
 */
public class CrucePorOrden extends Cruce{
	
	/**************************** ATRIBUTTES *******************************/
	private int punto1;
	private int punto2;
	private final int longCromo = 26;

	
	/**************************** CONSTRUCTOR ******************************/
	
	
	/***************************** METHODS ********************************/
	/**
	 * [ES] Esta función selecciona los individuos a cruzar y los cruza dos a dos aplicando cruce por orden.
	 * 
	 * [EN] This function selects the individuals to cross and crosses them by pairs applying order crossover.
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
		
		Random rand = new Random();
		punto1 = rand.nextInt(20);
		punto2 = rand.nextInt(longCromo - punto1) + punto1;
		
		for (int i = 0; i < this.num_selec_cruce; i += 2) {
			this.numCruce++;
			cruzaPadres(poblacion.get(selec_cruce.get(i)), poblacion.get(selec_cruce.get(i + 1)));
		}
	}
	
	/**
	 * [ES] Similar al cruce PMX. Se intercambian las posiciones entre dos puntos de corte.
	 * Para el resto de posiciones se copian los valores del padre respetando el orden 
	 * a partir de la zona copiada
	 * 
	 * 
	 * [EN] Similar to PMX crossover. Positions are exchanged between two cut-off points.
	 * For the remaining positions, the values are copied from the parent in the order of the two cut-off points
	 * from the copied area
	 * 
	 * @param padre1	[ES] Primer padre.
	 * 					[EN] First parent.
	 * 
	 * @param padre2	[ES] Segundo padre.
	 * 					[EN] Second parent.
	 */	 
	protected void cruzaPadres(Individuo padre1, Individuo padre2) {
		
		ArrayList<Integer> cromoPadre1 = padre1.getCromosoma();
		ArrayList<Integer> cromoPadre2 = padre2.getCromosoma();
		
		ArrayList<Integer> cromoPadre1Aux = new ArrayList<Integer>(longCromo);
		ArrayList<Integer> cromoPadre2Aux = new ArrayList<Integer>(longCromo);
		
		ArrayList<Integer> cromoHijo1 = new ArrayList<Integer>(longCromo);
		HashSet<Integer> setHijo1 = new HashSet<Integer>(longCromo);

		ArrayList<Integer> cromoHijo2 = new ArrayList<Integer>(longCromo);
		HashSet<Integer> setHijo2 = new HashSet<Integer>(longCromo);
		
		for (int i = 0; i < longCromo; i++) {
			
			cromoPadre1Aux.add(cromoPadre1.get(i));
			cromoPadre2Aux.add(cromoPadre2.get(i));
			
			cromoHijo1.add(cromoPadre1.get(i));
			cromoHijo2.add(cromoPadre2.get(i));
		}
		
		int longi = this.punto1;
		int added1 = 0, added2 = 0;
		while (longi <= this.punto2) {
			
			cromoHijo1.set(longi, cromoPadre1Aux.get(longi));
			setHijo1.add(cromoPadre1Aux.get(longi));
			cromoHijo2.set(longi, cromoPadre2Aux.get(longi));
			setHijo2.add(cromoPadre2Aux.get(longi));
			added1++;
			added2++;
			longi++;
		}
		
		int i1 = (this.punto2 + 1)%26;
		int j1 = (this.punto2 + 1)%26;
		while (added1 < 26) {
			
			int toAdd = cromoPadre2Aux.get(j1);
			if (!setHijo1.contains(toAdd)) {
				
				cromoHijo1.set(i1, toAdd);
				i1 = (i1 + 1)%26;
				setHijo1.add(toAdd);
				added1++;
			}
			j1 = (j1 + 1)%26;
		}
		
		int i2 = (this.punto2 + 1)%26;
		int j2 = (this.punto2 + 1)%26;
		while (added2 < 26) {
			
			int toAdd = cromoPadre1Aux.get(j2);
			if (!setHijo2.contains(toAdd)) {
				
				cromoHijo2.set(i2, toAdd);
				i2 = (i2 + 1)%26;
				setHijo2.add(toAdd);
				added2++;
			}
			j2 = (j2 + 1)%26;
		}
		
		sustituyePadres(padre1, padre2, cromoHijo1, cromoHijo2, cromoPadre1Aux, cromoPadre2Aux);
	}
	
	
	/**************************** GETTERS & SETTERS ********************************/
}
