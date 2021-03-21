package algoritmoGenetico.individuos;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IndividuoFuncion1 extends Individuo<Boolean> implements Cloneable{

	
	public IndividuoFuncion1(double precision){
		
		this.id = "Funcion 1";
		min = new double[2];	// Inicializar colecciones
		max = new double[2];
		longitud = new int[2];
		fenotipo = new double[2];
		numGenes = 2;
		
		min[0] = -3.000;		// primer gen
		max[0] = 12.100;
		
		min[1] = 4.100;			// segundo gen
		max[1] = 5.800;
		
		this.precision = precision;
		
		longitud[0] = tamGen(min[0], max[0]);
		longitud[1] = tamGen(min[1], max[1]);
		longitudTotal = longitud[0] + longitud[1];
		
		cromosoma = new ArrayList<Boolean>(longitudTotal);	
	}
	
	@Override
	public double calculateFitness() {
		
		calculateFenotipo();
		aptitud = (21.5 + fenotipo[0] * Math.sin(4 * Math.PI * fenotipo[0]) + fenotipo[1] * Math.sin(20 * Math.PI * fenotipo[1]));
		//System.out.println("Fitness: " + this.aptitud + "\n");
		return aptitud;
	}
	
	@Override
	public void calculateFenotipo() {
		int index = 0;
		//System.out.println("Cromosoma: " + printCromosoma());
		//System.out.println(numGenes);
		for (int i = 0; i < numGenes; i++) {
			StringBuilder gen = new StringBuilder();
			//System.out.println("Variable i :" + i);
			for (int k = 0; k < longitud[i]; k++) {
				//System.out.println("Variable index :" + index);
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
		Random ran = new Random();
		for(int i = 0; i < longitudTotal; i++) {
			cromosoma.add(ran.nextBoolean());
		}
		//aptitud = calculateFitness();
		calculateFitness();
	}

	@Override
	public double getFitness() {
		
		calculateFitness();
		return aptitud;
	}
	
	@Override
	public ArrayList<Boolean> getCromosoma(){
		
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
		//System.out.println(sb);
		return sb;
	}
}
