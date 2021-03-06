package algoritmoGenetico.individuos;

public abstract class Individuo {

	protected double min;
	protected double max;
	protected boolean[] cromosoma;
	protected double fenotipo;
	protected double puntuacion;
	protected double puntAcumulada;
	protected double aptitud;
	protected int longitud;
	protected double precision;
	
	protected abstract int[] bin2Dec(boolean[] array);
	protected abstract double getFitness();
	protected abstract int tamGen();
}
