package algoritmoGenetico;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import algoritmoGenetico.cruces.Cruce;
import algoritmoGenetico.cruces.CruceMonopunto;
import algoritmoGenetico.individuos.FactoriaIndividuo;
import algoritmoGenetico.individuos.Individuo;
import algoritmoGenetico.individuos.IndividuoFuncion1;
import algoritmoGenetico.mutaciones.Mutacion;
import algoritmoGenetico.mutaciones.MutacionBasica;
import algoritmoGenetico.seleccion.Seleccion;
import algoritmoGenetico.seleccion.SeleccionRuleta;
import algoritmoGenetico.seleccion.SeleccionTorneo;

public class AlgoritmoGenetico {

	//Nota, dejar estas variables como array de doubles, no hacerlo como ArrayList
	private double[] mejorAbsoluto;		//Array con el mejor absoluto (a lo largo de todas las generaciones)
	private double[] mejorGeneracion;	//Array con el mejor fitness de una generacion
	private double[] mediaGeneracion;	//Array con la media de fitness de cada generacion
	
	private int generacionActual;
	
	private int tamPoblacion;			//Tamanho de la poblacion
	
	//TODO: hacer desplazamiento de la aptitud
	/*
	 * crear poblacion inicial
	 * 
	 */
	public <T> AlgoritmoGenetico(int tamPoblacion, int numGeneraciones, double precision, Seleccion metodoSeleccion, Cruce metodoCruce, 
			double porcCruce, Mutacion metodoMutacion, double porcMutacion, double elite, String tipoIndividuo) {
		
		this.inicializaVariables(tamPoblacion, numGeneraciones);
		ArrayList<Individuo> poblacion = new ArrayList<Individuo>(tamPoblacion);
		this.inicializaPoblacion(tamPoblacion, tipoIndividuo, poblacion);
		
		
		while (this.generacionActual < numGeneraciones) {
			//System.out.println("//////////////////////////////////////");
			//System.out.println("Generacion : " + generacionActual);
			this.evaluar(tipoIndividuo, poblacion);
			metodoSeleccion.seleccionar(poblacion);
			metodoCruce.cruza(poblacion, porcCruce);
			
			if (tipoIndividuo.equals("Funcion 1") || tipoIndividuo.equals("Funcion 2") || tipoIndividuo.equals("Funcion 3") || tipoIndividuo.equals("Funcion 4")) {
				metodoMutacion.mutaPoblacionBoolean(poblacion, porcMutacion);
			}
			
			this.generacionActual++;
		}
	}
	
	public void inicializaVariables(int tamPoblacion, int numGeneraciones) {
		
		this.generacionActual = 0;
		this.tamPoblacion = tamPoblacion;
		
		this.mejorAbsoluto = new double[numGeneraciones];
		this.mejorGeneracion = new double[numGeneraciones];
		this.mediaGeneracion = new double[numGeneraciones];
	}
	
	//TODO: hay problema con calcular la elite
	public void evaluar(String tipoIndividuo, ArrayList<Individuo> poblacion) {
		
		ArrayList<Integer> mejoresIndividuos = new ArrayList<Integer>();
		double mejorGeneracion;
		double maxFitness = 0;
		
		if (tipoIndividuo.equals("Funcion 1")) {	//Funcion1
			
			mejorGeneracion = 0;
			int pivote = 0;
			for (Individuo ind : poblacion) {
				pivote++;
				double fitness = ind.getFitness();
				if (fitness > mejorGeneracion) {
					
					mejorGeneracion = fitness;
					mejoresIndividuos.add(poblacion.indexOf(ind));
				}
				
				this.mediaGeneracion[this.generacionActual] += fitness;
			}
			
			this.mediaGeneracion[this.generacionActual] = this.mediaGeneracion[this.generacionActual] / this.tamPoblacion;
			
			this.mejorGeneracion[this.generacionActual] = mejorGeneracion;
			
			if (this.mejorGeneracion[this.generacionActual] > this.mejorAbsoluto[this.generacionActual] || this.generacionActual == 0) {
				
				this.mejorAbsoluto[this.generacionActual] = this.mejorGeneracion[this.generacionActual];
			}
			else {
				
				this.mejorAbsoluto[this.generacionActual] = this.mejorAbsoluto[this.generacionActual - 1];
			}
		}
		else {	//Funciones 2, 3 y 4
			
			mejorGeneracion = Double.MAX_VALUE;
			
			for (Individuo ind : poblacion) {
				
				double fitness = ind.getFitness();
				if (fitness < mejorGeneracion) {
					
					mejorGeneracion = fitness;
					mejoresIndividuos.add(poblacion.indexOf(ind));
				}
				
				if (fitness > maxFitness) {
					
					maxFitness = fitness;
				}
				
				this.mediaGeneracion[this.generacionActual] += fitness;
			}
			
			this.mediaGeneracion[this.generacionActual] = this.mediaGeneracion[this.generacionActual] / this.tamPoblacion;
			
			this.mejorGeneracion[this.generacionActual] = mejorGeneracion;
			
			if (this.mejorGeneracion[this.generacionActual] < this.mejorAbsoluto[this.generacionActual] || this.generacionActual == 0) {
				
				this.mejorAbsoluto[this.generacionActual] = this.mejorGeneracion[this.generacionActual];
			}
			else {
				
				this.mejorAbsoluto[this.generacionActual] = this.mejorAbsoluto[this.generacionActual - 1];
			}
			
			//Desplazamos aptitud
			for (Individuo individuo : poblacion) {
				
				double newFitness = maxFitness - individuo.getFitness();
				individuo.setFitness(newFitness);
			}
		}
	}
	
	public double[] getMejorAbsoluto() {
		return mejorAbsoluto;
	}
	public double[] getMejorGeneracion() {
		return mejorGeneracion;
	}
	public double[] getMediaGeneracion() {
		return mediaGeneracion;
	}
	
	public void inicializaPoblacion(int tamPoblacion, String tipoIndividuo, ArrayList<Individuo> poblacion) {
		
		for (int i = 0; i < tamPoblacion; i++) {
			
			Individuo nuevoInd = FactoriaIndividuo.getIndividuo(tipoIndividuo);
			nuevoInd.inicializaIndividuo();
			poblacion.add(nuevoInd);
		}
	}
}
