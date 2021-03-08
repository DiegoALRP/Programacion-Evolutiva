package algoritmoGenetico.individuos;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IndividuoFuncion1<T> extends Individuo<Boolean> {

	
	public IndividuoFuncion1(){
		
		min = new double[2];	// Inicializar colecciones
		max = new double[2];
		longitud = new int[2];
		fenotipo = new double[2];
		
		min[0] = -3.000;		// primer gen
		max[0] = 12.100;
		
		min[1] = 4.100;			// segundo gen
		max[1] = 5.800;
		
		precision = 0.0001;
		
		longitud[0] = tamGen(min[0], max[0]);
		longitud[1] = tamGen(min[1], max[1]);
		longitudTotal = longitud[0] + longitud[1];
		
		cromosoma = new ArrayList<Boolean>(longitudTotal);
		
		
	}
	
	@Override
	public double calculateFitness() {
		fenotipo[0] = getFenotipo(longitud[0], min[0], max[0]);
		fenotipo[1] = getFenotipo(longitud[1], min[1], max[1]);
		//System.out.println(fenotipo[0]);
		//System.out.println(fenotipo[1]);
		aptitud = (21.5 + fenotipo[0] * Math.sin(4 * Math.PI * fenotipo[0]) + fenotipo[1] * Math.sin(20 * Math.PI * fenotipo[1])); 
		return aptitud;
	}

	@Override
	public int tamGen(double minGen, double maxGen) {
		double longitud = (Math.log10(((maxGen - minGen) / precision) + 1) / Math.log10(2));
		return (int) Math.ceil(longitud) ;
	}

	@Override
	public void inicializaIndividuo() {
		for(int i = 0; i < longitudTotal; i++) {
			Random ran = new Random();
			cromosoma.add(ran.nextBoolean());
		}
		aptitud = calculateFitness();
	}

	@Override
	public double getFenotipo(int longitudGen, double genMin, double genMax) {
		String gen = "";
		for(int i = 0; i < longitudGen;i++) {
			if(cromosoma.get(i)) {		
				gen += "1";
			} else {
				gen += "0";
			}
		}
		double real = Integer.parseInt(gen,2);
		return genMin + real * ((genMax - genMin)/(Math.pow(2,longitudTotal)-1));
	}
	
	/*public double getFenotipoDiego() {
		String gen = "";
		for(int i = 0; i < this.longitud[0];i++) {
			if(cromosoma.get(i)) {		
				gen += "1";
			} else {
				gen += "0";
			}
		}
		double real = Integer.parseInt(gen,2);
		return genMin + real * ((genMax - genMin)/(Math.pow(2,longitudTotal)-1));
	}*/

	@Override
	public double getFitness() {
		return aptitud;
	}
	
	public ArrayList<Boolean> getCromosoma(){
		
		return this.cromosoma;
	}

	@Override
	public int getLongitudCromosoma() {
		
		return this.longitudTotal;
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

	@Override
	public void setCromosoma(ArrayList<Boolean> individuo) {
		
		this.cromosoma.clear();
		this.cromosoma.addAll(individuo);
	}

}
