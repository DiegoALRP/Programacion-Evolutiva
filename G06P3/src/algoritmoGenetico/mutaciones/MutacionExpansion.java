package algoritmoGenetico.mutaciones;

import java.util.Random;

import algoritmoGenetico.individuos.Arbol;
import algoritmoGenetico.individuos.Individuo;

public class MutacionExpansion extends Mutacion{

	@Override
	public void mutaIndividuo(Individuo individuo) {
		
		Arbol arbol = individuo.getCromosoma();
		Random rand = new Random();
		for(int i = 0; i < arbol.getNumHijos(); i++) {
			if(rand.nextDouble() < this.probMutacion) {
				Arbol mutado = arbol.getHijos().get(i);
				mutado.randomFuncion();
				mutado.inicializaArbol("Completo");
				break;
			}
		}
		
		
	}

}
