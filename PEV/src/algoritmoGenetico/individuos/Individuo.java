package algoritmoGenetico.individuos;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public abstract class Individuo<E> implements Cloneable{

	protected double[] min;
	protected double[] max;
	protected double[] fenotipo;
	protected int[] longitud;
	protected ArrayList<E> cromosoma;
	
	
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
	

	
	//Diego
	public abstract ArrayList<E> getCromosoma();	//Obtiene el cromosoma del individuo
	public abstract void setCromosoma(ArrayList<E> individuo);
	public abstract int getLongitudCromosoma();		//Obtiene la longitud del cromosoma del individuo
	public abstract StringBuilder printCromosoma();
}
