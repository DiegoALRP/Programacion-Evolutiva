package algoritmoGenetico.mutaciones;

import java.util.ArrayList;
import java.util.Random;

import algoritmoGenetico.individuos.Individuo;

public class MutacionBasica extends Mutacion {

	@Override
	public void mutaPoblacionBoolean(ArrayList<Individuo<Boolean>> poblacion, double probMutacion) {
		
		this.probMutacion = probMutacion;
		
		Random rand = new Random();
		for (int i = 0; i < poblacion.size(); i++) {
			
			if (rand.nextDouble() < this.probMutacion) {
				
				mutaIndividuoBoolean(poblacion.get(i));
				System.out.println("Individuo a mutar: " + i);
			}
		}
	}
	
	protected void mutaIndividuoBoolean(Individuo<Boolean> individuo) {
		
		ArrayList<Boolean> cromo = individuo.getCromosoma();
		
		Random rand = new Random();
		for (int i = 0; i < cromo.size(); i++) {
			
			double ran = rand.nextDouble();
			System.out.println("random: " + ran);
			if (ran < this.probMutacion) {
				
				cromo.set(i, !cromo.get(i));
				System.out.println("Alelo mutado: " + i);
			}
		}
	}
}
