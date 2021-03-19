package algoritmoGenetico.mutaciones;

import java.util.ArrayList;
import java.util.Random;

import algoritmoGenetico.individuos.Individuo;

public class MutacionUniforme extends Mutacion{

	public void mutaPoblacionDouble(ArrayList<Individuo> poblacion, double probMutacion) {
		
		this.probMutacion = probMutacion;
		
		Random rand = new Random();
		for (int i = 0; i < poblacion.size(); i++) {
			
			if (rand.nextDouble() < this.probMutacion) {
				
				mutaIndividuoDouble(poblacion.get(i));
			}
		}
	}
	
	public void mutaIndividuoDouble(Individuo<Double> individuo) {
		
		ArrayList<Double> cromo = individuo.getCromosoma();
		
		Random rand = new Random();
		
		double rangeMin = individuo.getMinValue();
		double rangeMax = individuo.getMaxValue();
		
		cromo.set(rand.nextInt(individuo.getNumGenes()), rangeMin + (rangeMax - rangeMin) * rand.nextDouble());
	}
	
	@Override
	public void mutaPoblacionBoolean(ArrayList<Individuo> poblacion, double probMutacion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void mutaIndividuoBoolean(Individuo<Boolean> individuo) {
		// TODO Auto-generated method stub
		
	}

}
