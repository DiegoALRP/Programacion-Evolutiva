package algoritmoGenetico.individuos;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public abstract class Individuo<E> {

	protected String id;
	protected double[] min;
	protected double[] max;
	protected double[] fenotipo;
	protected int numGenes;
	protected int[] longitud;
	protected ArrayList<E> cromosoma;
	
	
	protected double aptitud;
	protected double aptitudDesplazada;
	protected double precision;
	protected double puntuacion;
	protected double puntAcumulada;
	protected int longitudTotal;
	
	
	//TODO: hacer funcion void (fitness)
	public abstract double calculateFitness();
	//TODO: poner funcion distinta de fenotipo
	public abstract void inicializaIndividuo();
	
	public abstract void calculateFenotipo();
	public String getFenotipo() {
		
		calculateFenotipo();
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < this.numGenes; i++) {
			
			if (this.numGenes <= 2) {
				sb.append("Variable X" + (i+1) + " = " + this.fenotipo[i] + "\n");
			}
			else {
				if (i % 2 == 0) {
					sb.append("Variable X" + (i+1) + " = " + this.fenotipo[i] + "	");
				}
				else {
					sb.append("Variable X" + (i+1) + " = " + this.fenotipo[i] + "\n");
				}
			}
		}
		
		return sb.toString();
	}
	
	public double getFitness() {
		return aptitud;
	}
	public double getFitnessDesplazado() {
		return this.aptitudDesplazada;
	}
	public void setFitness(double newFitness) {
		
		this.aptitud = newFitness;
	}
	public void setFitnessDesplazada(double newFitness) {
		
		this.aptitudDesplazada = newFitness;
	}
	
	public int tamGen(double minGen, double maxGen) {
		double longitud = (Math.log10(((maxGen - minGen) / precision) + 1) / Math.log10(2));
		return (int) Math.ceil(longitud) ;
	}

	public abstract ArrayList<E> getCromosoma();
	public void setCromosoma(ArrayList<E> individuo) {
		
		this.cromosoma.clear();
		this.cromosoma.addAll(individuo);
	}
	
	//Obtiene la longitud del cromosoma del individuo
	public int getLongitudCromosoma() {
		
		return this.longitudTotal;
	}
	
	public abstract StringBuilder printCromosoma();
	
	public String getId() {
		
		return this.id;
	}
	
	public double getMinValue() {
		
		return this.min[0];
	}
	
	public double getMaxValue() {
		
		return this.max[0];
	}
	
	public int getNumGenes() {
		
		return this.numGenes;
	}
	
	public double getPrecision() {
		
		return this.precision;
	}
}
