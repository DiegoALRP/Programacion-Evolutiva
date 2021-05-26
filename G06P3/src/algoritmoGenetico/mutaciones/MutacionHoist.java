package algoritmoGenetico.mutaciones;

import java.util.Random;

import algoritmoGenetico.individuos.Arbol;
import algoritmoGenetico.individuos.Individuo;

/**
 * Universidad Complutense de Madrid.
 * Programaci�n Evolutiva.
 * Grupo A 2021.
 * Profesor:
 * 	-Carlos Cervigon Ruckauer.
 * 
 * Pr�ctica 3
 * 
 * Clase Mutaci�n Hoist.
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodr�guez Pereira.
 *
 */
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
