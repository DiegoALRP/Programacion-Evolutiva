package algoritmoGenetico.mutaciones;

import java.util.ArrayList;
import java.util.Random;

import algoritmoGenetico.individuos.Individuo;

public abstract class Mutacion {

	protected double probMutacion;
	protected int numMutaciones;

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

}
