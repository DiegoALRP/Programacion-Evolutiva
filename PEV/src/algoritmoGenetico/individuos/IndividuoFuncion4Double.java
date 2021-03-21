package algoritmoGenetico.individuos;

import java.util.ArrayList;
import java.util.Random;

public class IndividuoFuncion4Double extends Individuo<Double>{

	public IndividuoFuncion4Double(int n, double precision) {
		
		this.id = "Funcion Michalewicz (Reales)";
		
		this.min = new double[1];
		this.max = new double[1];
		this.longitud = new int[1];
		this.fenotipo = new double[n];
		this.numGenes = n;
		
		min[0] = 0;
		max[0] = Math.PI;
		
		this.precision = precision;
		
		longitud[0] = 0;
		longitudTotal = n;
		
		cromosoma = new ArrayList<Double>(longitudTotal);
	}

	@Override
	public double calculateFitness() {
		
		calculateFenotipo();
		
		double sumAcum = 0;
		for (int i = 1; i <= this.numGenes; i++) {
			
			double x = fenotipo[i - 1];
			double senx = Math.sin(x);
			double numerador = ((i + 1)*Math.pow(x, 2));
			double senF = Math.pow(Math.sin(numerador/Math.PI), 20);
			
			sumAcum += senx*senF;
		}
		
		aptitud = -sumAcum;
		return aptitud;
	}

	@Override
	public void inicializaIndividuo() {
		
		Random rand = new Random();
		for (int i = 0; i < longitudTotal; i++) {
			
			cromosoma.add((Math.PI) * rand.nextDouble());
		}
	}

	@Override
	public void calculateFenotipo() {
		
		for (int i = 0; i < this.numGenes; i++) {
			
			this.fenotipo[i] = this.cromosoma.get(i);
		}
	}

	@Override
	public ArrayList<Double> getCromosoma() {
		// TODO Auto-generated method stub
		return this.cromosoma;
	}

	@Override
	public StringBuilder printCromosoma() {
		// TODO Auto-generated method stub
		return null;
	}
}
