package algoritmoGenetico.individuos;

/**
 * Universidad Complutense de Madrid.
 * Programación Evolutiva.
 * Grupo A 2021.
 * Profesor:
 * 	-Carlos Cervigon Ruckauer.
 * 
 * Práctica 2
 * 
 * Clase Almacen Fitness.
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodríguez Pereira.
 *
 */
public class Fitness {

	/**************************** ATRIBUTTES *******************************/
	private volatile double fitness;
	
	
	/**************************** CONSTRUCTOR ******************************/
	public Fitness() {
		
		fitness = 0;
	}
	
	
	/***************************** METHODS ********************************/
	
	/**
	 * [ES] Suma al fitness actual, el valor que se pasa por parámetro.
	 * [EN] Add the value passed as parameter to the actual fitness.
	 * 
	 * @param fitness 	[ES] Valor de fitness que queremos añadir
	 * 					[EN] Fitness value that we want to add.
	 */
	public void addFitness(double fitness) {
		
		this.fitness += fitness;
	}
	
	
	/**************************** GETTERS & SETTERS ********************************/
	public double getFitness() {
		return this.fitness;
	}
}
