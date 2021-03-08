package algoritmoGenetico.individuos;

import java.util.List;


public abstract class Individuo<E> {

	protected double[] min;
	protected double[] max;
	protected double[] fenotipo;
	protected int[] longitud;
	protected List<E> cromosoma;
	
	
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
}
