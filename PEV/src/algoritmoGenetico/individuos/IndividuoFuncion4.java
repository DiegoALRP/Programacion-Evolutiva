package algoritmoGenetico.individuos;

import java.util.ArrayList;
import java.util.Random;

public class IndividuoFuncion4 extends Individuo<Boolean> {

	public IndividuoFuncion4(int n, double precision) {
		
		this.id = "Funcion Michalewicz (Booleanos)";
		
		min = new double[1];
		max = new double[1];
		longitud = new int[1];
		fenotipo = new double[n];
		this.numGenes = n;
		
		min[0] = 0;
		max[0] = Math.PI;
		
		this.precision = precision;
		
		longitud[0] = tamGen(min[0], max[0]);
		longitudTotal = longitud[0]*n;
		
		cromosoma = new ArrayList<Boolean>(longitudTotal);
	}
	
	@Override
	public double calculateFitness() {
		
		calculateFenotipo();
		
		double sumAcum = 0;
		for (int i = 1; i <= this.numGenes; i++) {
			
			double x = fenotipo[i - 1];
			double senx = Math.sin(x);
			double numerador = (i + 1)*Math.pow(x, 2);
			double senF = Math.pow(Math.sin(numerador/Math.PI), 20);
			
			sumAcum += senx*senF;
		}
		
		aptitud = -sumAcum;
		this.aptitudDesplazada = aptitud;
		
		return aptitud;
	}

	@Override
	public void calculateFenotipo() {
		
		int index = 0;
		for (int i = 0; i < numGenes; i++) {
			
			StringBuilder gen = new StringBuilder();
			for (int k = 0; k < longitud[0]; k++) {
				
				if (cromosoma.get(index)) {
					gen.append('1');
				}
				else {
					gen.append('0');
				}
				index++;
			}
			
			double real = Integer.parseInt(gen.toString(),2);
			fenotipo[i] = min[0] + real * (max[0] - min[0])/(Math.pow(2,longitud[0])-1);
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
