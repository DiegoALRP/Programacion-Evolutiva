package algoritmoGenetico.individuos;

import java.util.List;
import java.util.Random;


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
	public abstract double getFenotipo(int longitudGen, double min, double max);
	public abstract void inicializaIndividuo();
	
	public double getFitness() {
		return aptitud;
	}
	
	public int tamGen(double minGen, double maxGen) {
		double longitud = (Math.log10(((maxGen - minGen) / precision) + 1) / Math.log10(2));
		return (int) Math.ceil(longitud) ;
	}
	

}
