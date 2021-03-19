package algoritmoGenetico.cruces;

import java.util.ArrayList;

import algoritmoGenetico.individuos.Individuo;

public abstract class Cruce {

	protected ArrayList<Integer> selec_cruce;
	protected double probCruce;
	protected int punto_cruce;
	protected int num_selec_cruce;	//Numero de individuos seleccionados para cruzar
	protected int tamPoblacion;
	
	public abstract void cruza(ArrayList<Individuo> poblacion, double probCruce);
	protected abstract void cruzaPadres(Individuo padre1, Individuo padre2);
	protected abstract void seleccionaIndividuos(ArrayList<Individuo> poblacion);
	
}