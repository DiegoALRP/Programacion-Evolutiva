package algoritmoGenetico.cruces;

import java.util.ArrayList;

import algoritmoGenetico.individuos.Individuo;

public abstract class Cruce<E> {

	protected boolean probCruce;
	protected int punto_cruce;
	protected int num_selec_cruce;	//Numero de individuos seleccionados para cruzar
	
	protected abstract ArrayList<Individuo<E>> seleccionadosCruce(ArrayList<Individuo<E>> poblacion);
	protected abstract ArrayList<Individuo<E>> cruza(ArrayList<Individuo<E>> poblacion);
	

}
