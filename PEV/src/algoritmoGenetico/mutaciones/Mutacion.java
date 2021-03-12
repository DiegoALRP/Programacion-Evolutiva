package algoritmoGenetico.mutaciones;

import java.util.ArrayList;

import algoritmoGenetico.individuos.Individuo;

public abstract class Mutacion {
	
	protected double probMutacion;
	
	public abstract void mutaPoblacionBoolean(ArrayList<Individuo<Boolean>> poblacion, double probMutacion);
	protected abstract void mutaIndividuoBoolean(Individuo<Boolean> individuo);
}
