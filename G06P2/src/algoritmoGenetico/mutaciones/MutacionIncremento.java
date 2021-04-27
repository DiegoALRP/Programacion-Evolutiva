package algoritmoGenetico.mutaciones;

import java.util.ArrayList;

import algoritmoGenetico.individuos.Individuo;

public class MutacionIncremento extends Mutacion {
	
	private final int incremento = 1;

	@Override
	protected void mutaIndividuo(Individuo individuo) {
		
		ArrayList<Integer> cromosoma = individuo.getCromosoma();
		
		int aux;
		for (int i = 0; i < cromosoma.size(); i++) {
			
			aux = cromosoma.get(i);
			cromosoma.set(i, Math.floorMod((aux + incremento), 26));
		}
		
		individuo.avisoCromoModificado();
	}

}
