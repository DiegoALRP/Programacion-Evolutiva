package algoritmoGenetico.cruces;

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
 * Clase Abstracta Cruce.
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodr�guez Pereira.
 *
 */
public abstract class Cruce {

	
	/**************************** ATRIBUTTES *******************************/
	protected ArrayList<Integer> selec_cruce;	//Array de enteros que contiene el �ndice de los individuos de la poblaci�n
	protected double probCruce;					//Probabilidad de Cruce
	protected int punto_cruce;					//Punto de Cruce
	protected int num_selec_cruce;				//Numero de individuos seleccionados para cruzar
	protected int tamPoblacion;					//Tama�o de la poblaci�n
	protected int numCruce;
	
	/**************************** CONSTRUCTOR *******************************/
	
	
	/***************************** METHODS ********************************/
	
	public abstract void cruza(ArrayList<Individuo> poblacion, double probCruce);
	
	/**
	 * [ES] Esta funci�n se extiende a todas las subclases de la clase cruce. Su funci�n es seleccionar a los
	 * individuos a cruzar. Cada individuo se selecciona con una probabilidad (la probabilidad de cruce que pasamos
	 * como par�metro en la funci�n principal). Y almacenamos el �ndice del individuo en un array.
	 * Este m�todo incluye una variaci�n, y es que no cruza individuos con mismo cromosoma.
	 * 
	 * [EN] This function extends to all subclasses of the cruce's class. It's function it's to select the
	 * individuals to cross. Each individual is selected with a probability (the crossover probability that we pass
	 * as parameter of the principal function. And we store the index of the individual in an array.
	 * This method includes a variation, it doens't crossover individuals with the same chromosome.
	 * 
	 * @param poblacion	[ES] La poblaci�n de la cual queremos seleccionar a los individuos a cruzar.
	 * 					[EN] The population from which we select the individuals to cross.
	 */
	protected void seleccionaIndividuos(ArrayList<Individuo> poblacion) {
		
		this.tamPoblacion = poblacion.size();
		Random rand = new Random();
		
		for (int i = 0; i < tamPoblacion; i++) {
			
			if (rand.nextDouble() < this.probCruce) {

				if (this.num_selec_cruce % 2 == 1) {
					
					if (!poblacion.get(i).getCromosoma().equals(poblacion.get(this.selec_cruce.get(this.num_selec_cruce - 1)).getCromosoma())) {
						
						this.selec_cruce.add(i);
						this.num_selec_cruce++;
					}
				}
				else {
					
					this.selec_cruce.add(i);
					this.num_selec_cruce++;
				}
			}
		}
		
		if ((num_selec_cruce % 2) == 1) {
			
			this.num_selec_cruce--;
			this.selec_cruce.remove(num_selec_cruce);
		}
	}
	
	/**
	 * [ES] Esta funci�n utiliza la pol�tica de reemplazo "Reemplazar si es mejor".
	 * Es decir, esta funci�n evalua la aptitud de ambos padres y ambos hijos.
	 * Y s�lo se sustituye a los padres, en caso de que los hijos sean mejores que los padres.
	 * En caso contrario, se mantiene a los padres y se descartan a los hijos.
	 * 
	 * [EN] This function uses the replacement policy "Replace if better".
	 * In other words, this function evaluates the fitness value of both parents and both children.
	 * And it only replaces the parents if the children are better than the parents.
	 * Otherwise, the parents are keep and the children are discarded.
	 * 
	 * 
	 * @param padre1	[ES] El primer individuo.
	 * 					[EN] The first individual.
	 * @param padre2	[ES] El segundo individuo.
	 * 					[EN] The second individual.
	 * @param cromoHijo1	[ES] Cromosoma del primer hijo.
	 * 						[EN] Chromosome of the first child.
	 * @param cromoHijo2	[ES] Cromsoma del segundo hijo.
	 * 						[EN] Chormosome of the second child.
	 * @param cromoPadre1Aux	[ES] Cromosoma del primer individuo.
	 * @param cromoPadre2Aux	[EN] Chromosome of the first individual.
	 */
	protected void sustituyePadres(Individuo padre1, Individuo padre2, ArrayList<Integer> cromoHijo1, ArrayList<Integer>  cromoHijo2,
			ArrayList<Integer>  cromoPadre1Aux, ArrayList<Integer>  cromoPadre2Aux) {
		
		double fitnessP1 = padre1.getFitness();
		double fitnessP2 = padre2.getFitness();
		
		padre1.setCromosoma(cromoHijo1);
		padre2.setCromosoma(cromoHijo2);
		
		double fitnessH1 = padre1.calculateFitness();
		double fitnessH2 = padre2.calculateFitness();
		
		if (fitnessP1 > fitnessH1) padre1.setCromosoma(cromoPadre1Aux);
		if (fitnessP2 > fitnessH2) padre2.setCromosoma(cromoPadre2Aux);
	}
	
	protected abstract void cruzaPadres(Individuo padre1, Individuo padre2);
	
	
	/**************************** GETTERS & SETTERS ********************************/
	
	public int getNumCruces() {
		return this.numCruce;
	}
}
