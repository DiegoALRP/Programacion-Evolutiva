package algoritmoGenetico.individuos;

import java.util.ArrayList;
import java.util.Random;

public class IndividuoFuncion2 extends Individuo<Boolean> {

	public IndividuoFuncion2(double precision) {
		
		this.id = "Funcion Schubert";
		
		this.min = new double[2];
		this.max = new double[2];
		this.longitud = new int[2];
		this.fenotipo = new double[2];
		this.numGenes = 2;
		
		min[0] = -10;
		max[0] = 10;
		min[1] = -10;
		max[1] = 10;
		
		this.precision = precision;
		
		longitud[0] = tamGen(min[0], max[0]);
		longitud[1] = tamGen(min[1], max[1]);
		longitudTotal = longitud[0] + longitud[1];
		
		cromosoma = new ArrayList<Boolean>(longitudTotal);
	}
	@Override
	public double calculateFitness() {
		
		calculateFenotipo();
		
		double parte1 = 0;
		double parte2 = 0;
		for (int i = 1; i <= 5; i++) {
			
			parte1 += i*Math.cos((i + 1)*fenotipo[0] + i);
			parte2 += i*Math.cos((i + 1)*fenotipo[1] + i);
		}
		
		aptitud = parte1*parte2;
		return aptitud;
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
	public void inicializaIndividuo() {
		
		Random rand = new Random();
		for (int i = 0; i < longitudTotal; i++) {
			
			cromosoma.add(rand.nextBoolean());
		}
		
		calculateFitness();
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
