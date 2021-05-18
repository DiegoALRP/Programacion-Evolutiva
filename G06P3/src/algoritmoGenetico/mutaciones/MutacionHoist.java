package algoritmoGenetico.mutaciones;

import java.util.Random;

import algoritmoGenetico.individuos.Arbol;
import algoritmoGenetico.individuos.Individuo;

public class MutacionHoist extends Mutacion{

	@Override
	public void mutaIndividuo(Individuo individuo) {
		
		Random rand = new Random();
		Arbol arbol = individuo.getCromosoma();
		boolean mutado = false;
		
		while(!mutado) {
			for(int i = 0; i < arbol.getNumHijos(); i++) {
				if(rand.nextDouble() < this.probMutacion) {
					individuo.setCromosoma(arbol.getHijos().get(i));
					mutado = true;
				}
			}
		}
	}

}
