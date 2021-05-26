package algoritmoGenetico.mutaciones;

import java.util.ArrayList;
import java.util.Random;

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
 * Clase Abstracta Mutaci�n.
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodr�guez Pereira.
 *
 */
public abstract class Mutacion {

	
	/******************************** ATRIBUTTES ********************************/
	protected double probMutacion;
	protected int numMutaciones;

	
	/******************************* CONSTRUCTOR ********************************/
	
	
	/********************************* METHODS *********************************/
	public void muta(ArrayList<Individuo> poblacion, double probMutacion) {
		
		this.probMutacion = probMutacion;
		
		Random rand = new Random();
		for (int i = 0; i < poblacion.size(); i++) {
			
			if (rand.nextDouble() < this.probMutacion) {
				
				this.numMutaciones++;
				mutaIndividuo(poblacion.get(i));
			}
		}
		
	}
	
	public abstract void mutaIndividuo(Individuo individuo);


	/**************************** AUXILIARY METHODS ****************************/
	
	/**************************** GETTERS & SETTERS ****************************/
}
