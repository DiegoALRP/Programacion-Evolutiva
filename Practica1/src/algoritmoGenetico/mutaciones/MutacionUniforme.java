package algoritmoGenetico.mutaciones;

import java.util.ArrayList;
import java.util.Random;

import algoritmoGenetico.individuos.Individuo;

/**
 * Universidad Complutense de Madrid.
 * Programaci�n Evolutiva.
 * Grupo A 2021.
 * Profesor:
 * 	-Carlos Cervigon Ruckauer.
 * 
 * Clase Mutaci�n Uniforme.
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodr�guez Pereira.
 *
 */
public class MutacionUniforme extends Mutacion{

	
	/**
	 * [ES] Funci�n abstracta principal de las clases de mutaci�n.
	 * Esta funci�n selecciona a los individuos a mutar con la probabilidad
	 * de mutaci�n pasada por par�metro a la funci�n.
	 * Est� dise�ada para mutar poblaciones cuyos genes esten formados por n�meros reales.
	 * [EN] Principal abstract function of the mutation classes.
	 * This function selects the individual to mutate with mutation's probability pass as an parameter.
	 * It's design to mutate population with doubles numbers gene.
	 * 
	 * @param poblacion [ES] Poblaci�n de la cual vamos a mutar sus individuos.
	 * 					[EN] Population to mutate.
	 * @param probMutacion	[ES] Probabilidad de mutaci�n.
	 * 						[EN] Mutation probability.
	 */
	public void mutaPoblacionDouble(ArrayList<Individuo> poblacion, double probMutacion) {
		
		this.probMutacion = probMutacion;
		
		Random rand = new Random();
		for (int i = 0; i < poblacion.size(); i++) {
			
			if (rand.nextDouble() < this.probMutacion) {
				
				mutaIndividuoDouble(poblacion.get(i));
			}
		}
	}
	
	
	/**
	 * [ES] Funci�n que muta los genes de un individuo pasado por par�metro.
	 * [EN] Function that mutates the genes of an individual pass as an parameter.
	 * 
	 * @param individuo	[ES] Individuo que queremos mutar.
	 * 					[EN] Individual that we want to mutate.
	 */
	public void mutaIndividuoDouble(Individuo<Double> individuo) {
		
		ArrayList<Double> cromo = individuo.getCromosoma();
		
		Random rand = new Random();
		
		double rangeMin = individuo.getMinValue();
		double rangeMax = individuo.getMaxValue();
		
		cromo.set(rand.nextInt(individuo.getNumGenes()), rangeMin + (rangeMax - rangeMin) * rand.nextDouble());
	}
	
	@Override
	public void mutaPoblacionBoolean(ArrayList<Individuo> poblacion, double probMutacion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void mutaIndividuoBoolean(Individuo<Boolean> individuo) {
		// TODO Auto-generated method stub
		
	}
}
