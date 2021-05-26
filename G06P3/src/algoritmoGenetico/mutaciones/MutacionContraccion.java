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
 * Clase Mutaci�n Contraccion.
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodr�guez Pereira.
 *
 */
public class MutacionContraccion extends Mutacion{

	@Override
	public void mutaIndividuo(Individuo individuo) {
		
		Random rand = new Random();
		Arbol arbol = individuo.getCromosoma();
		@SuppressWarnings("unused")
		Arbol arbolMuta = null;
		boolean mutado = false;
		
		while(!mutado) {
			for(int i = 0; i < arbol.getNumHijos(); i++) {
				if(rand.nextDouble() < this.probMutacion) {
					arbolMuta = arbol.getHijos().get(i);
					mutado = true;
				}
			}
			arbol = arbol.getHijos().get(rand.nextInt(arbol.getNumHijos()));
		}
		
		arbol.randomTerminal();
	}

}
