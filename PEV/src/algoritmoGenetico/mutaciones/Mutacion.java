package algoritmoGenetico.mutaciones;

import java.util.ArrayList;

import algoritmoGenetico.individuos.Individuo;

public abstract class Mutacion<E> {
	
	protected double probMutacion;
	
	protected abstract ArrayList<Individuo<E>> muta(ArrayList<Individuo<E>> individuos);
}
