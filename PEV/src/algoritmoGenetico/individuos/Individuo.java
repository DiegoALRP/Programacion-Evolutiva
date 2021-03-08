package algoritmoGenetico.individuos;

import java.util.ArrayList;
import java.util.List;


public abstract class Individuo<T> {

	protected double[] min;
	protected double[] max;
	protected double[] fenotipo;
	protected int[] longitud;
	protected ArrayList<T> cromosoma;
	
	
	protected double aptitud;
	protected double precision;
	protected double puntuacion;
	protected double puntAcumulada;
	protected int longitudTotal;
		
	
	public abstract double calculateFitness();
	public abstract double getFitness();
	public abstract int tamGen(double min, double max);
	public abstract void inicializaIndividuo();
	public abstract double getFenotipo(int longitudGen, double min, double max);
	
	//Diego
	public abstract ArrayList<T> getCromosoma();	//Obtiene el cromosoma del individuo
	public abstract void setCromosoma(ArrayList<T> individuo);
	public abstract int getLongitudCromosoma();		//Obtiene la longitud del cromosoma del individuo
	public abstract StringBuilder printCromosoma();
}
