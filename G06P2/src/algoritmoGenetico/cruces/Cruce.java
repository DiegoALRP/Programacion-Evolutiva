package algoritmoGenetico.cruces;

import java.util.ArrayList;
import java.util.Random;

import algoritmoGenetico.individuos.Individuo;

/**
 * Universidad Complutense de Madrid.
 * Programación Evolutiva.
 * Grupo A 2021.
 * Profesor:
 * 	-Carlos Cervigon Ruckauer.
 * 
 * Clase Abstracta Cruce.
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodríguez Pereira.
 *
 */
public abstract class Cruce {

	
	/**************************** ATRIBUTTES *******************************/
	protected ArrayList<Integer> selec_cruce;	//Array de enteros que contiene el índice de los individuos de la población
	protected double probCruce;					//Probabilidad de Cruce
	protected int punto_cruce;					//Punto de Cruce
	protected int num_selec_cruce;				//Numero de individuos seleccionados para cruzar
	protected int tamPoblacion;					//Tamaño de la población
	protected int numCruce;
	
	/**************************** CONSTRUCTOR *******************************/
	
	
	/***************************** METHODS ********************************/
	
	public abstract void cruza(ArrayList<Individuo> poblacion, double probCruce);
	
	protected void seleccionaIndividuos(ArrayList<Individuo> poblacion) {
		
		this.tamPoblacion = poblacion.size();
		Random rand = new Random();
		
		for (int i = 0; i < tamPoblacion; i++) {
			
			if (rand.nextDouble() < this.probCruce) {

				if (this.num_selec_cruce % 2 == 1) {
					
					if (!poblacion.get(i).getCromosoma().equals(poblacion.get(this.selec_cruce.get(this.num_selec_cruce - 1)).getCromosoma())) {
						
						this.selec_cruce.add(i);
						this.num_selec_cruce++;
					}
				}
				else {
					
					this.selec_cruce.add(i);
					this.num_selec_cruce++;
				}
			}
		}
		
		if ((num_selec_cruce % 2) == 1) {
			
			this.num_selec_cruce--;
			this.selec_cruce.remove(num_selec_cruce);
		}
	}
	
	protected void sustituyePadres(Individuo padre1, Individuo padre2, ArrayList<Integer> cromoHijo1, ArrayList<Integer>  cromoHijo2,
			ArrayList<Integer>  cromoPadre1Aux, ArrayList<Integer>  cromoPadre2Aux) {
		
		double fitnessP1 = padre1.getFitness();
		double fitnessP2 = padre2.getFitness();
		
		padre1.setCromosoma(cromoHijo1);
		padre2.setCromosoma(cromoHijo2);
		
		double fitnessH1 = padre1.calculateFitness();
		double fitnessH2 = padre2.calculateFitness();
		
		//TODO: probar que se cambien con una cierta probabilidad
		if (fitnessP1 > fitnessH1) padre1.setCromosoma(cromoPadre1Aux);
		if (fitnessP2 > fitnessH2) padre2.setCromosoma(cromoPadre2Aux);
	}
	
	protected abstract void cruzaPadres(Individuo padre1, Individuo padre2);
	
	
	/**************************** GETTERS & SETTERS ********************************/
	
	public int getNumCruces() {
		return this.numCruce;
	}
}
