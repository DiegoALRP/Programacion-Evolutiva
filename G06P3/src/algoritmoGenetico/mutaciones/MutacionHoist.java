package algoritmoGenetico.mutaciones;

import java.util.Random;

import algoritmoGenetico.individuos.Arbol;
import algoritmoGenetico.individuos.Individuo;

public class MutacionHoist extends Mutacion{

	@Override
	public void mutaIndividuo(Individuo individuo) {
		
		Random rand = new Random();
		Arbol cromosoma = individuo.getCromosoma();
		
		Arbol hijo = cromosoma.getHijos().get(rand.nextInt(cromosoma.getNumHijos()));
		
		while (rand.nextDouble() > this.probMutacion || hijo.getNumHijos() < 1) {
			
			if (hijo.getNumHijos() < 1) {
				
				hijo = hijo.getPadre();
			}
			else {
				
				hijo = hijo.getHijos().get(rand.nextInt(hijo.getNumHijos()));
			}
		}
		
		individuo.setCromosoma(hijo);
	}
}
