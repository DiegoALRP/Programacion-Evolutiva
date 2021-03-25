package algoritmoGenetico.mutaciones;

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
 * Clase Mutación Básica.
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodríguez Pereira.
 *
 */
public class MutacionBasica extends Mutacion {

	/**
	 * [ES] Función abstracta principal de las clases de mutación.
	 * Esta función selecciona a los individuos a mutar con la probabilidad
	 * de mutación pasada por parámetro a la función.
	 * Está diseñada para mutar poblaciones cuyos genes esten formados por números binarios.
	 * [EN] Principal abstract function of the mutation classes.
	 * This function selects the individual to mutate with mutation's probability pass as an parameter.
	 * It's design to mutate population with binary number gene.
	 * 
	 * @param poblacion [ES] Población de la cual vamos a mutar sus individuos.
	 * 					[EN] Population to mutate.
	 * @param probMutacion	[ES] Probabilidad de mutación.
	 * 						[EN] Mutation probability.
	 */
	@Override
	public void mutaPoblacionBoolean(ArrayList<Individuo> poblacion, double probMutacion) {
		
		this.probMutacion = probMutacion;
		
		Random rand = new Random();
		for (int i = 0; i < poblacion.size(); i++) {
			
			if (rand.nextDouble() < this.probMutacion) {
				
				mutaIndividuoBoolean(poblacion.get(i));
			}
		}
	}
	
	
	/**
	 * [ES] Función que muta los genes de un individuo pasado por parámetro.
	 * [EN] Function that mutates the genes of an individual pass as an parameter.
	 * 
	 * @param individuo	[ES] Individuo que queremos mutar.
	 * 					[EN] Individual that we want to mutate.
	 */
	protected void mutaIndividuoBoolean(Individuo<Boolean> individuo) {
		
		ArrayList<Boolean> cromo = individuo.getCromosoma();
		
		Random rand = new Random();
		for (int i = 0; i < cromo.size(); i++) {
			
			double ran = rand.nextDouble();
			
			if (ran < this.probMutacion) {
				
				cromo.set(i, !cromo.get(i));
			}
		}
	}

	@Override
	public void mutaPoblacionDouble(ArrayList<Individuo> poblacion, double probMutacion) {
		
		this.probMutacion = probMutacion;
		
		Random rand = new Random();
		for (int i = 0; i < poblacion.size(); i++) {
			
			if (rand.nextDouble() < this.probMutacion) {
				
				mutaIndividuoDouble(poblacion.get(i));
			}
		}
	}

	@Override
	protected void mutaIndividuoDouble(Individuo<Double> individuo) {
		
		ArrayList<Double> cromo = individuo.getCromosoma();
		
		Random rand = new Random();
		
		double rangeMin = individuo.getMinValue();
		double rangeMax = individuo.getMaxValue();
		
		cromo.set(rand.nextInt(individuo.getNumGenes()), rangeMin + (rangeMax - rangeMin) * rand.nextDouble());
	}
}
