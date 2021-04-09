package algoritmoGenetico.individuos;

import java.util.ArrayList;
import java.util.Random;

/**
 * Universidad Complutense de Madrid
 * Programación Evolutiva
 * Grupo A 2021
 * Profesor:
 * 	-Carlos Cervigon Ruckauer
 * 
 * Clase de la Función 2: Schubert (Reales)
 * 
 * @author 
 * Grupo G06
 * 	-Miguel Robledo
 * 	-Diego Alejandro Rodríguez Pereira
 *
 */
public class IndividuoFuncion2Double extends Individuo<Double>{

	/**
	 * [ES] Constructora de la función 2.
	 * [EN] Function 2's constructor.
	 * 
	 * @param precision [ES] Valor de precisión.
	 * 					[EN] Precision's value.
	 */
	public IndividuoFuncion2Double(double precision) {

		this.id = "Funcion Schubert (Reales)";
		
		this.min = new double[2];
		this.max = new double[2];
		this.longitud = new int[2];
		this.fenotipo = new double[2];
		this.numGenes = 2;
		
		min[0] = -10;
		max[0] = 10;
		min[1] = -10;
		max[1] = 10;
		
		this.precision = precision;
		
		longitud[0] = 0;
		longitudTotal = 2;
		
		cromosoma = new ArrayList<Double>(longitudTotal);
	}

	/**
	 * [ES] Función que inicializa los valores del individuo.
	 * [EN] Function that initialize the individual's values.
	 * 
	 */
	@Override
	public void inicializaIndividuo() {
		
		Random rand = new Random();
		for (int i = 0; i < longitudTotal; i++) {
			
			double rangeMin = this.min[0];
			double rangeMax = this.max[0];
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
		
		double parte1 = 0;
		double parte2 = 0;
		for (int i = 1; i <= 5; i++) {
			
			parte1 += i*Math.cos((i + 1)*fenotipo[0] + i);
			parte2 += i*Math.cos((i + 1)*fenotipo[1] + i);
		}
		
		aptitud = parte1*parte2;
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
