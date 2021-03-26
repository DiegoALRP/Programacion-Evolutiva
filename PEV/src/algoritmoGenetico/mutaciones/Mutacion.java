package algoritmoGenetico.mutaciones;

import java.util.ArrayList;

import algoritmoGenetico.individuos.Individuo;

/**
 * Universidad Complutense de Madrid.
 * Programación Evolutiva.
 * Grupo A 2021.
 * Profesor:
 * 	-Carlos Cervigon Ruckauer.
 * 
 * Clase Abstracta Mutación.
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodríguez Pereira.
 *
 */
public abstract class Mutacion {
	
	protected double probMutacion;	//Probabilidad de mutación.
	
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
	public abstract void mutaPoblacionBoolean(ArrayList<Individuo> poblacion, double probMutacion);
	
	
	/**
	 * [ES] Función que muta los genes de un individuo pasado por parámetro.
	 * [EN] Function that mutates the genes of an individual pass as an parameter.
	 * 
	 * @param individuo	[ES] Individuo que queremos mutar.
	 * 					[EN] Individual that we want to mutate.
	 */
	protected abstract void mutaIndividuoBoolean(Individuo<Boolean> individuo);
	
	
	/**
	 * [ES] Función abstracta principal de las clases de mutación.
	 * Esta función selecciona a los individuos a mutar con la probabilidad
	 * de mutación pasada por parámetro a la función.
	 * Está diseñada para mutar poblaciones cuyos genes esten formados por números reales.
	 * [EN] Principal abstract function of the mutation classes.
	 * This function selects the individual to mutate with mutation's probability pass as an parameter.
	 * It's design to mutate population with doubles numbers gene.
	 * 
	 * @param poblacion [ES] Población de la cual vamos a mutar sus individuos.
	 * 					[EN] Population to mutate.
	 * @param probMutacion	[ES] Probabilidad de mutación.
	 * 						[EN] Mutation probability.
	 */
	public abstract void mutaPoblacionDouble(ArrayList<Individuo> poblacion, double probMutacion);
	
	
	/**
	 * [ES] Función que muta los genes de un individuo pasado por parámetro.
	 * [EN] Function that mutates the genes of an individual pass as an parameter.
	 * 
	 * @param individuo	[ES] Individuo que queremos mutar.
	 * 					[EN] Individual that we want to mutate.
	 */
	protected abstract void mutaIndividuoDouble(Individuo<Double> individuo);
}
