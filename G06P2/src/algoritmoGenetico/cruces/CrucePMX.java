package algoritmoGenetico.cruces;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

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
 * Clase Cruce PMX.
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodr�guez Pereira.
 *
 */
public class CrucePMX extends Cruce {

	/**************************** ATRIBUTTES *******************************/
	private int punto1;
	private int punto2;
	private final int longCromo = 26;
	
	/**************************** CONSTRUCTOR ******************************/
	
	
	/***************************** METHODS ********************************/
	/**
	 * [ES] Esta funci�n selecciona los individuos a cruzar y los cruza dos a dos aplicando cruce PLX.
	 * 
	 * [EN] This function selects the individuals to cross and crosses them by pairs applying PMX.
	 * 
	 * @param poblacion	[ES] La poblaci�n original.
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
	 * [ES] Seleccionar dos puntos de corte e intercambiar las posiciones de los cromosomas entre dichos puntos.
	 * Para el resto de posiciones, si la posicion que se quiere intercambiar no est� en la subcadena, se copia.
	 * Si la posici�n si esta en la subcadena, se copia el valor del otro padre. 
	 * 
	 * 
	 * [EN] Select two cut points and swap the chromosome positions between them.
	 * For the rest of the positions, if the position to be exchanged is not in the substring, it is copied.
	 * If the position is in the substring, the value of the other parent is copied. 
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
		
		ArrayList<Integer> cromoHijo1Aux = new ArrayList<Integer>(longCromo);
		ArrayList<Integer> cromoHijo2Aux = new ArrayList<Integer>(longCromo);
		
		int a;
		int b;
		for (int i = 0; i < longCromo; i++) {
			
			a = cromoPadre1.get(i);
			b = cromoPadre2.get(i);
			
			cromoPadre1Aux.add(a);
			cromoPadre2Aux.add(b);
			cromoHijo1.add(a);
			cromoHijo2.add(b);
		}
		
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
		
		int i1 = (this.punto2 + 1)%longCromo;
		while (added1 < longCromo) {
			
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
			i1 = (i1 + 1)%longCromo;
		}
		
		int i2 = (this.punto2 + 1)%longCromo;
		while (added2 < longCromo) {
			
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
			i2 = (i2 + 1)%longCromo;
		}
		
		sustituyePadres(padre1, padre2, cromoHijo1, cromoHijo2, cromoPadre1Aux, cromoPadre2Aux);
	}

	
	/**************************** GETTERS & SETTERS ********************************/
}
