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
 * Clase de la Función 3: Holder Table (Reales)
 * 
 * @author 
 * Grupo G06
 * 	-Miguel Robledo
 * 	-Diego Alejandro Rodríguez Pereira
 *
 */
public class IndividuoFuncion3Double extends Individuo<Double> {

	public IndividuoFuncion3Double(double precision) {
		
		this.id = "Funcion Holder table (Reales)";
		
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
		
		double x = fenotipo[0];
		double y = fenotipo[1];
		
		double raiz = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
		double exp = Math.abs(1 - (raiz/Math.PI));
		double sinx = Math.sin(x);
		double cosy = Math.cos(y);
		
		aptitud = - Math.abs(sinx * cosy * Math.exp(exp));
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
