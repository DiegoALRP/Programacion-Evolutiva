package algoritmoGenetico.mutaciones;

import java.util.ArrayList;
import java.util.Random;

import algoritmoGenetico.individuos.Individuo;

public class MutacionInsersion extends Mutacion {

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
		int i = rand.nextInt(longCromosoma);
		int j = rand.nextInt(longCromosoma);
		while (j == i) {
			
			j = rand.nextInt(longCromosoma);
		}
		
		int k = (j + 1)%longCromosoma;
		while (k != (i + 1)%longCromosoma) {
			
			if (k == 0) {
				cromosoma.set(k, cromosomaAux.get(longCromosoma - 1));
			}
			else {
				cromosoma.set(k, cromosomaAux.get((k - 1)%longCromosoma));
			}
			k = (k + 1)%longCromosoma;
		}
		
		cromosoma.set(j, cromosomaAux.get(i));
	}

}
