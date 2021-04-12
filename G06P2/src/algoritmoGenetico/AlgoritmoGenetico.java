package algoritmoGenetico;

import algoritmoGenetico.cruces.Cruce;
import algoritmoGenetico.mutaciones.Mutacion;
import algoritmoGenetico.seleccion.Seleccion;

public class AlgoritmoGenetico {
	
	/**************************** ATRIBUTTES *******************************/
	private int tamPoblacion;
	private int numGeneraciones;
	private Seleccion metodoSeleccion;
	private Cruce metodoCruce;
	private double porcCruce;
	private Mutacion metodoMutacion;
	private double porcElite;
	
	/**************************** CONSTRUCTOR *******************************/
	public AlgoritmoGenetico() {
		
		
	}
	
	public AlgoritmoGenetico(int tamPoblacion, int numGeneraciones, Seleccion metodoSeleccion, 
			Cruce metodoCruce, double porcCruce, Mutacion metodoMutacion, double porcElite) {
		
		this.tamPoblacion = tamPoblacion;
		this.numGeneraciones = numGeneraciones;
		this.metodoSeleccion = metodoSeleccion;
		this.metodoCruce = metodoCruce;
		this.porcCruce = porcCruce;
		this.metodoMutacion = metodoMutacion;
		this.porcElite = porcElite;
	}
	
	/***************************** METHODS ********************************/
	/**************************** GET & SET ********************************/

}
