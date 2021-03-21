package algoritmoGenetico.individuos;

import java.util.ArrayList;
import java.util.Random;

public class IndividuoFuncion3 extends Individuo<Boolean> {
	
	public IndividuoFuncion3() {
		
		this.id = "Funcion Holder table";
		
		this.min = new double[2];
		this.max = new double[2];
		this.longitud = new int[2];
		this.fenotipo = new double[2];
		this.numGenes = 2;
		
		min[0] = -10;
		max[0] = 10;
		min[1] = -10;
		max[1] = 10;
		
		precision = 0.001;
		
		longitud[0] = tamGen(min[0], max[0]);
		longitud[1] = tamGen(min[1], max[1]);
		longitudTotal = longitud[0] + longitud[1];
		
		cromosoma = new ArrayList<Boolean>(longitudTotal);
	}

	@Override
	public double calculateFitness() {
		
		calculateFenotipo();
		
		double x = fenotipo[0];
		double y = fenotipo[1];
		
		double raiz = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
		double exp = Math.abs(1 - (raiz/Math.PI));
		double sinx = Math.sin(x);
		double cosy = Math.cos(y);
		
		aptitud = - Math.abs(sinx * cosy * Math.exp(exp));
		
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
		for(int i = 0; i < longitudTotal; i++) {
			
			cromosoma.add(rand.nextBoolean());
		}
		//aptitud = calculateFitness();
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
