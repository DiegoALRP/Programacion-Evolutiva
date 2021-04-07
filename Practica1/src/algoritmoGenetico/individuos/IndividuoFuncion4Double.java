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
 * Clase de la Función 4: Michalewicz (Reales)
 * 
 * @author 
 * Grupo G06
 * 	-Miguel Robledo
 * 	-Diego Alejandro Rodríguez Pereira
 *
 */
public class IndividuoFuncion4Double extends Individuo<Double>{

	/**
	 * [ES] Constructora de la función 1.
	 * [EN] Function 1's constructor.
	 * 
	 * @param precision [ES] Valor de precisión.
	 * 					[EN] Precision's value.
	 */
	public IndividuoFuncion4Double(int n, double precision) {
		
		this.id = "Funcion Michalewicz (Reales)";
		
		this.min = new double[1];
		this.max = new double[1];
		this.longitud = new int[1];
		this.fenotipo = new double[n];
		this.numGenes = n;
		
		min[0] = 0;
		max[0] = Math.PI;
		
		this.precision = precision;
		
		longitud[0] = 0;
		longitudTotal = n;
		
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
			
			cromosoma.add((Math.PI) * rand.nextDouble());
		}
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
		
		double sumAcum = 0;
		for (int i = 1; i <= this.numGenes; i++) {
			
			double x = fenotipo[i - 1];
			double senx = Math.sin(x);
			double numerador = ((i + 1)*Math.pow(x, 2));
			double senF = Math.pow(Math.sin(numerador/Math.PI), 20);
			
			sumAcum += senx*senF;
		}
		
		aptitud = -sumAcum;
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
	
	/**
	 * Getters and Setters
	 */

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
