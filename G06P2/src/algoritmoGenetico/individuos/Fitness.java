package algoritmoGenetico.individuos;

/**
 * Universidad Complutense de Madrid.
 * Programaci�n Evolutiva.
 * Grupo A 2021.
 * Profesor:
 * 	-Carlos Cervigon Ruckauer.
 * 
 * Pr�ctica 2
 * 
 * Clase Almacen Fitness.
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodr�guez Pereira.
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
	public void addFitness(double fitness) {
		
		this.fitness += fitness;
	}
	
	
	/**************************** GETTERS & SETTERS ********************************/
	public double getFitness() {
		return this.fitness;
	}
}
