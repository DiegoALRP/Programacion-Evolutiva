package algoritmoGenetico.individuos;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Universidad Complutense de Madrid.
 * Programación Evolutiva.
 * Grupo A 2021.
 * Profesor:
 * 	-Carlos Cervigon Ruckauer.
 * 
 * Clase Individuo.
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodríguez Pereira.
 *
 */
public abstract class Individuo<E> {

	protected String id;					//Identidifica a que función pertenece el individuo.
	protected double[] min;					//Array que contiene los valores mínimos de cada gen.
	protected double[] max;					//Array que contiene los valores mínimos de cada gen.
	protected double[] fenotipo;			//Array que contiene el fenotipo de cada gen.
	protected int numGenes;					//Número de genes.
	protected int[] longitud;				//Array que contiene la longitud de cada gen.
	protected ArrayList<E> cromosoma;		//Array que contiene el cromosoma del individuo.
	protected double aptitud;				//Aptitud o valor fitness del individuo.
	protected double aptitudDesplazada;		//Aptitud o valor fitness delplazado.
	protected double precision;				//Precision del gen.
	protected int longitudTotal;			//Longitud del cromosoma
	
	
	/**
	 * [ES] Función abstracta que inicializa los valores del individuo.
	 * [EN] Abstract function that initialize the individual's values.
	 */
	public abstract void inicializaIndividuo();
	
	/**
	 * [ES] Esta función abstracta calcula el valor de aptitud/fitness del individuo.
	 * [EN] This abstract function calculates the individual's fitness value.
	 * 
	 * @return	[ES] Valor de aptitud / valor de Fitness del individuo.
	 * 			[EN] Indiviual's Fitness value.
	 */
	public abstract double calculateFitness();
	
	/**
	 * [ES] Esta función abstracta calcula el fenotipo del individuo.
	 * [EN] This abstract function calculates the individual's phenotype.
	 */
	public abstract void calculateFenotipo();
	
	/**
	 * [ES] Esta función crea un String del fenotipo del individuo.
	 * [EN] This abstract function creates a String of the individual's phenotype.
	 * 
	 * @return 	[ES] El fenotipo del individuo en formato String.
	 * 			[EN] The individual's phenotype in String format.
	 */
	public String getFenotipo() {
		
		calculateFenotipo();
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < this.numGenes; i++) {
			
			if (this.numGenes <= 2) {
				sb.append("Variable X" + (i+1) + " = " + this.fenotipo[i] + "\n");
			}
			else {
				if (i % 2 == 0) {
					sb.append("Variable X" + (i+1) + " = " + this.fenotipo[i] + "	");
				}
				else {
					sb.append("Variable X" + (i+1) + " = " + this.fenotipo[i] + "\n");
				}
			}
		}
		
		return sb.toString();
	}
	
	/**
	 * [ES] Esta función calcula el tamaño de un gen.
	 * [EN] This function calculates the size of a gen.
	 * 
	 * @param minGen	[ES] El valor mínimo del gen.
	 * 					[EN] The gen's lowest value.
	 * @param maxGen	[ES] El valor máximo del gen.
	 * 					[EN] The gen's maximum value.
	 * 
	 * @return	[ES] Retorna el tamaño del gen.
	 * 			[EN] Return the gen's size.
	 */
	public int tamGen(double minGen, double maxGen) {
		double longitud = (Math.log10(((maxGen - minGen) / precision) + 1) / Math.log10(2));
		return (int) Math.ceil(longitud) ;
	}
	
	/**
	 * Getters and Setters
	 */
	

	/*
	 * [ES] Getters y Setters de valores de aptitud originales y desplazados.
	 * [EN] Getters and Setters of original fitness value and displaced.
	 */
	public double getFitness() {
		
		calculateFitness();
		return aptitud;
	}
	
	public double getFitnessDesplazado() {
		
		return this.aptitudDesplazada;
	}
	
	public void setFitness(double newFitness) {
		
		this.aptitud = newFitness;
	}
	
	public void setFitnessDesplazada(double newFitness) {
		
		this.aptitudDesplazada = newFitness;
	}

	/*
	 * [ES] Cromosoma.
	 * [EN] Chromosome.
	 * 
	 */
	public abstract ArrayList<E> getCromosoma();
	public void setCromosoma(ArrayList<E> individuo) {
		
		this.cromosoma.clear();
		this.cromosoma.addAll(individuo);
	}
	
	public int getLongitudCromosoma() {
		
		return this.longitudTotal;
	}
	
	public abstract StringBuilder printCromosoma();
	
	public String getId() {
		
		return this.id;
	}
	
	public double getMinValue() {
		
		return this.min[0];
	}
	
	public double getMaxValue() {
		
		return this.max[0];
	}
	
	public int getNumGenes() {
		
		return this.numGenes;
	}
	
	public double getPrecision() {
		
		return this.precision;
	}
}
