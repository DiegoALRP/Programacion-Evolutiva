package algoritmoGenetico.mutaciones;

import algoritmoGenetico.individuos.Individuo;

public abstract class Mutacion {

	protected double probMutacion;

	protected abstract void mutaIndividuo(Individuo individuo);

}
