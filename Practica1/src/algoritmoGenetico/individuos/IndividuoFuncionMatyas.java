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
 * Clase de la Función 6: Matyas
 * 
 * @author 
 * Grupo G06
 * 	-Miguel Robledo
 * 	-Diego Alejandro Rodríguez Pereira
 *
 */
public class IndividuoFuncionMatyas extends Individuo<Boolean>{

	/**
	 * [ES] Constructora de la función 1.
	 * [EN] Function 1's constructor.
	 * 
	 * @param precision [ES] Valor de precisión.
	 * 					[EN] Precision's value.
	 */
	public IndividuoFuncionMatyas() {
		this.id = "Funcion Matyas";
		
		this.min = new double[2];
		this.max = new double[2];
		this.longitud = new int[2];
		this.fenotipo = new double[2];
		this.numGenes = 2;
		
		min[0] = -10;
		max[0] = 10;
		min[1] = -10;
		max[1] = 10;
		
		precision = 0.001;
		
		longitud[0] = tamGen(min[0], max[0]);
		longitud[1] = tamGen(min[1], max[1]);
		longitudTotal = longitud[0] + longitud[1];
		
		cromosoma = new ArrayList<Boolean>(longitudTotal);
	}
	
	
	/**
	 * [ES] Función que inicializa los valores del individuo.
	 * [EN] Function that initialize the individual's values.
	 */
	@Override
	public void inicializaIndividuo() {
		
		Random rand = new Random();
		for(int i = 0; i < longitudTotal; i++) {
			
			cromosoma.add(rand.nextBoolean());
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
		
		double x1 = fenotipo[0] * fenotipo[0];
		double x2 = fenotipo[1] * fenotipo[1];
		aptitud = 0.26*(x1 + x2) - 0.48 * fenotipo[0] * fenotipo[1];
		this.aptitudDesplazada = aptitud;
		
		return aptitud;
	}

	
	/**
	 * [ES] Esta función calcula el fenotipo del individuo.
	 * [EN] This function calculates the individual's phenotype.
	 */
	@Override
	public void calculateFenotipo() {
		int index = 0;
		for (int i = 0; i < numGenes; i++) {
			
			StringBuilder gen = new StringBuilder();
			for (int k = 0; k < longitud[i]; k++) {
				
				if (cromosoma.get(index)) {
					
					gen.append('1');
				}
				else {
			
					gen.append('0');
				}
				
				index++;
			}
			
			double real = Integer.parseInt(gen.toString(),2);
			fenotipo[i] = min[i] + real * (max[i] - min[i])/(Math.pow(2,longitud[i])-1);
		}
	}
	
	/**
	 * Getters and Setters
	 */

	@Override
	public ArrayList<Boolean> getCromosoma() {
		return this.cromosoma;
	}

	@Override
	public StringBuilder printCromosoma() {
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < this.longitudTotal; i++) {
			
			if (this.cromosoma.get(i)) {
				sb.append(1);
			}
			else {
				sb.append(0);
			}
		}
		return sb;
	}

}
