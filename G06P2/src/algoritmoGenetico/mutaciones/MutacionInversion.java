package algoritmoGenetico.mutaciones;

import java.util.ArrayList;
import java.util.Random;

import algoritmoGenetico.individuos.Individuo;

public class MutacionInversion extends Mutacion {

	@Override
	protected void mutaIndividuo(Individuo individuo) {
		ArrayList<Integer> cromosoma = individuo.getCromosoma();
		/*ArrayList<Integer> cromosoma = new ArrayList<Integer>();
		cromosoma.add(1);
		cromosoma.add(2);
		cromosoma.add(6);
		cromosoma.add(9);
		cromosoma.add(4);
		cromosoma.add(7);
		cromosoma.add(3);
		cromosoma.add(8);
		cromosoma.add(5);*/
		ArrayList<Integer> cromosomaAux = new ArrayList<Integer>();
		cromosomaAux.addAll(cromosoma);
		
		int longCromosoma = cromosoma.size();
		Random rand = new Random();
		int i = rand.nextInt(20);
		int j = rand.nextInt(26 - i) + i;
		for (int k = i; k <= j; k++) {
			
			cromosoma.set(k, cromosomaAux.get(j - k + i));
		}
	}

}
