package algoritmoGenetico.individuos;

import java.util.ArrayList;
import java.util.Random;

/**
 * Universidad Complutense de Madrid.
 * Programación Evolutiva.
 * Grupo A 2021.
 * Profesor:
 * 	-Carlos Cervigon Ruckauer.
 * 
 * Clase Funcion 1 (Reales).
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodríguez Pereira.
 *
 */
public class IndividuoFuncion1Double extends Individuo<Double> {

	public IndividuoFuncion1Double(double precision) {
		
		this.id = "Funcion 1 (Reales)";
		min = new double[2];
		max = new double[2];
		longitud = new int[2];
		fenotipo = new double[2];
		numGenes = 2;
		
		min[0] = -3.000;		// primer gen
		max[0] = 12.100;
		
		min[1] = 4.100;			// segundo gen
		max[1] = 5.800;
		
		this.precision = precision;
		
		longitud[0] = 0;
		longitudTotal = 2;
		
		cromosoma = new ArrayList<Double>(longitudTotal);	
	}
	
	/**
	 * [ES] Función que inicializa los valores del individuo.
	 * [EN] Function that initialize the individual's values.
	 */
	@Override
	public void inicializaIndividuo() {
		
		Random rand = new Random();
		for (int i = 0; i < longitudTotal; i++) {
			
			double rangeMin = this.min[i];
			double rangeMax = this.max[i];
			cromosoma.add(rangeMin + (rangeMax - rangeMin) * rand.nextDouble());
		}
		
		calculateFitness();
	}

	
	/**
	 * [ES] Esta función calcula el valor de aptitud/fitness del individuo.
	 * [EN] This function calculates the individual's fitness value.
	 * 
	 * @return	[ES] Valor de aptitud / valor de Fitness del individuo.
	 * 			[EN] Indiviual's Fitness value.
	 */
	@Override
	public double calculateFitness() {
		
		calculateFenotipo();
		aptitud = (21.5 + fenotipo[0] * Math.sin(4 * Math.PI * fenotipo[0]) + fenotipo[1] * Math.sin(20 * Math.PI * fenotipo[1]));
		this.aptitudDesplazada = aptitud;
		
		return aptitud;
	}

	
	/**
	 * [ES] Esta función calcula el fenotipo del individuo.
	 * [EN] This function calculates the individual's phenotype.
	 */
	@Override
	public void calculateFenotipo() {
		
		for (int i = 0; i < this.numGenes; i++) {
			
			this.fenotipo[i] = this.cromosoma.get(i);
		}
	}

	@Override
	public double getFitnessDesplazado() {
		
		return this.aptitud;
	}
	
	@Override
	public void setFitnessDesplazada(double newFitness) {
		
		this.aptitud = newFitness;
	}
	@Override
	public ArrayList<Double> getCromosoma() {
		
		return this.cromosoma;
	}

	@Override
	public StringBuilder printCromosoma() {
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < this.longitudTotal; i++) {
			
			sb.append(this.cromosoma.get(i));
		}
		return sb;
	}

}
