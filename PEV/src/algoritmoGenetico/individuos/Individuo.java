package algoritmoGenetico.individuos;

import java.util.ArrayList;

public abstract class Individuo<E> {

	protected double min;
	protected double max;
	protected ArrayList<E> cromosoma;
	protected double fenotipo;
	protected double puntuacion;
	protected double puntAcumulada;
	protected double aptitud;
	protected int longitud;
	protected double precision;
	
	protected abstract double bin2Dec(ArrayList<Boolean> array);
	protected abstract double getFitness();
	protected abstract int tamGen();
}
