package algoritmoGenetico.individuos;

import java.util.ArrayList;
import java.util.Random;

public class IndividuoFuncionBukin extends Individuo<Boolean>{

	
	public IndividuoFuncionBukin() {
		
		this.id = "Funcion Bukin";
		
		this.min = new double[2];
		this.max = new double[2];
		this.longitud = new int[2];
		this.fenotipo = new double[2];
		this.numGenes = 2;
		
		min[0] = -15;
		max[0] = -5;
		min[1] = -3;
		max[1] = 3;
		
		precision = 0.001;
		
		longitud[0] = tamGen(min[0], max[0]);
		longitud[1] = tamGen(min[1], max[1]);
		longitudTotal = longitud[0] + longitud[1];

		cromosoma = new ArrayList<Boolean>(longitudTotal);
	}
	@Override
	public double calculateFitness() {
		calculateFenotipo();
		
		double abs = Math.abs(fenotipo[1] - 0.01 * fenotipo[0] * fenotipo[0]);
		aptitud = 100 * Math.sqrt(abs) + 0.01 * Math.abs(fenotipo[0] + 10);
		return aptitud;
	}

	@Override
	public void inicializaIndividuo() {
		Random rand = new Random();
		for(int i = 0; i < longitudTotal; i++) {
			
			cromosoma.add(rand.nextBoolean());
		}
		//aptitud = calculateFitness();
		calculateFitness();
		
	}

	@Override
	public void calculateFenotipo() {
		int index = 0;
		for (int i = 0; i < numGenes; i++) {
			
			StringBuilder gen = new StringBuilder();
			
			for (int k = 0; k < longitud[i]; k++) {
				
				if (cromosoma.get(index)) {
					
					gen.append('1');
				}
				else {
					
					gen.append('0');
				}
				
				index++;
			}
			
			double real = Integer.parseInt(gen.toString(),2);
			fenotipo[i] = min[i] + real * (max[i] - min[i])/(Math.pow(2,longitud[i])-1);
			//System.out.println("x" + i + " fenotipo: " + fenotipo[i]);
		}
		
	}

	@Override
	public ArrayList<Boolean> getCromosoma() {
		return this.cromosoma;
	}

	@Override
	public StringBuilder printCromosoma() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < this.longitudTotal; i++) {
			
			if (this.cromosoma.get(i)) {
				sb.append(1);
			}
			else {
				sb.append(0);
			}
		}
		
		return sb;
	}

}
