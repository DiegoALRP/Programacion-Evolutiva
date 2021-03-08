package algoritmoGenetico.cruces;

import java.util.ArrayList;

import algoritmoGenetico.individuos.Individuo;

public abstract class Cruce {

	protected ArrayList<Integer> selec_cruce;
	protected double probCruce;
	protected int punto_cruce;
	protected int num_selec_cruce;	//Numero de individuos seleccionados para cruzar
	protected int tamPoblacion;
	
	public abstract <T> void cruza(ArrayList<Individuo<T>> poblacion);
	protected abstract <T> void cruzaPadres(Individuo<T> padre1, Individuo<T> padre2);
	protected abstract <T> void seleccionIndividuos(ArrayList<Individuo<T>> poblacion);
}
