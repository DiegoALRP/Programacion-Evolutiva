package algoritmoGenetico.mutaciones;

import java.util.ArrayList;
import java.util.Random;

import algoritmoGenetico.individuos.Individuo;

public class MutacionDelOrdenMD extends Mutacion {

	@Override
	protected void mutaIndividuo(Individuo individuo) {
		
		ArrayList<Integer> cromosoma = individuo.getCromosoma();
		ArrayList<Integer> cromosomaAux = new ArrayList<Integer>(cromosoma);
		
		int longCromo = cromosoma.size();
		Random rand = new Random();
		int puntoIntercambio = rand.nextInt(5) + 10;
		
		//for (int i = 0; i < )
	}

}
