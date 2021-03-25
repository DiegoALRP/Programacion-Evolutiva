package algoritmoGenetico.individuos;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Universidad Complutense de Madrid
 * Programación Evolutiva
 * Grupo A 2021
 * Profesor:
 * 	-Carlos Cervigon Ruckauer
 * 
 * Clase de la Función 1
 * 
 * @author 
 * Grupo G06
 * 	-Miguel Robledo
 * 	-Diego Alejandro Rodríguez Pereira
 *
 */
public class IndividuoFuncion1 extends Individuo<Boolean> implements Cloneable{

	/**
	 * [ES] Constructora de la función 1.
	 * [EN] Function 1's constructor.
	 * 
	 * @param precision [ES] Valor de precisión.
	 * 					[EN] Precision's value.
	 */
	public IndividuoFuncion1(double precision){
		
		this.id = "Funcion 1";
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
		
		Random ran = new Random();
		for(int i = 0; i < longitudTotal; i++) {
			
			cromosoma.add(ran.nextBoolean());
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
	public double getFitnessDesplazado() {
		return this.aptitud;
	}
	
	@Override
	public void setFitnessDesplazada(double newFitness) {
		
		this.aptitud = newFitness;
	}
	
	@Override
	public ArrayList<Boolean> getCromosoma(){
		
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
