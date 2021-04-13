package algoritmoGenetico.mutaciones;

import java.util.ArrayList;
import java.util.Random;

import algoritmoGenetico.individuos.Individuo;

/**
 * Universidad Complutense de Madrid.
 * Programación Evolutiva.
 * Grupo A 2021.
 * Profesor:
 * 	-Carlos Cervigon Ruckauer.
 * 
 * Clase Mutacion.
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodríguez Pereira.
 *
 */

public abstract class Mutacion {

	/**************************** ATRIBUTTES *******************************/
	protected double probMutacion;
	
	/**************************** CONSTRUCTOR *******************************/
	/***************************** METHODS ********************************/
	public void muta(ArrayList<Individuo> poblacion, double probMutacion) {
		
		//TODO: probar seleccionando a toda la poblacion
		this.probMutacion = probMutacion;
		
		Random rand = new Random();
		for (int i = 0; i < poblacion.size(); i++) {
			
			if (rand.nextDouble() < this.probMutacion) {
				
				mutaIndividuo(poblacion.get(i));
			}
		}
		
	}
	
	protected abstract void mutaIndividuo(Individuo individuo);
	
	
	/**************************** GET & SET ********************************/
}
