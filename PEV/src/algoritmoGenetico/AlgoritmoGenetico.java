package algoritmoGenetico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

	private double[] mejorAbsoluto;		//Array con el mejor absoluto (a lo largo de todas las generaciones)
	private double[] mejorGeneracion;	//Array con el mejor fitness de una generacion
	private double[] mediaGeneracion;	//Array con la media de fitness de cada generacion
	private String mejorSolucion;
	
	private int generacionActual;
	
	private int tamPoblacion;			//Tamanho de la poblacion
	
	private List<Individuo> elite;
	
	public <T> AlgoritmoGenetico(int tamPoblacion, int numGeneraciones, double precision, Seleccion metodoSeleccion, Cruce metodoCruce, 
			double porcCruce, Mutacion metodoMutacion, double porcMutacion, double porcElite, String tipoIndividuo, int numN) {
		
		this.inicializaVariables(tamPoblacion, numGeneraciones);
		ArrayList<Individuo> poblacion = new ArrayList<Individuo>(tamPoblacion);
		this.inicializaPoblacion(tamPoblacion, tipoIndividuo, poblacion, precision, numN);
		
		while (this.generacionActual < numGeneraciones) {
			
			this.evaluar(tipoIndividuo, poblacion, porcElite);
			metodoSeleccion.seleccionar(poblacion);
			metodoCruce.cruza(poblacion, porcCruce);
			
			if (tipoIndividuo.equals("Funcion Michalewicz (Reales)") || tipoIndividuo.equals("Funcion Schubert (Reales)") ||
					tipoIndividuo.equals("Funcion Holder table (Reales)") || tipoIndividuo.equals("Funcion 1 (Reales)")) {
				metodoMutacion.mutaPoblacionDouble(poblacion, porcMutacion);
			}
			else {
				metodoMutacion.mutaPoblacionBoolean(poblacion, porcMutacion);
			}
			
			poblacion.addAll(elite);
			this.generacionActual++;
		}
		
		//System.out.println("Solucion: " + this.mejorSolucion);
	}
	
	public void inicializaVariables(int tamPoblacion, int numGeneraciones) {
		
		this.generacionActual = 0;
		this.tamPoblacion = tamPoblacion;
		
		this.mejorAbsoluto = new double[numGeneraciones];
		this.mejorGeneracion = new double[numGeneraciones];
		this.mediaGeneracion = new double[numGeneraciones];
	}
	
	public void evaluar(String tipoIndividuo, ArrayList<Individuo> poblacion, double porcElite) {
		
		double mejorGeneracion = 0;
		double maxFitness = 0;
		
		if (tipoIndividuo.equals("Funcion 1") || (tipoIndividuo.equals("Funcion 1 (Reales)"))) {
			
			mejorGeneracion = 0;
			String mejorFenotipo = "";
			for (Individuo ind : poblacion) {
				double fitness = ind.calculateFitness();
				if (fitness > mejorGeneracion) {
					
					mejorGeneracion = fitness;
					mejorFenotipo = ind.getFenotipo() + "\nFitness: " + fitness;
				}
				
				this.mediaGeneracion[this.generacionActual] += fitness;
			}
			
			this.mediaGeneracion[this.generacionActual] = this.mediaGeneracion[this.generacionActual] / this.tamPoblacion;
			
			this.mejorGeneracion[this.generacionActual] = mejorGeneracion;
			
			if (this.generacionActual == 0 || mejorGeneracion > this.mejorAbsoluto[this.generacionActual - 1]) {
				
				this.mejorSolucion = mejorFenotipo;
			}
			
			if (this.generacionActual == 0 || this.mejorGeneracion[this.generacionActual] > this.mejorAbsoluto[this.generacionActual - 1]) {
				
				this.mejorAbsoluto[this.generacionActual] = this.mejorGeneracion[this.generacionActual];
			}
			else {
				
				this.mejorAbsoluto[this.generacionActual] = this.mejorAbsoluto[this.generacionActual - 1];
			}
			
			generaElite(poblacion, porcElite, tipoIndividuo);
		}
		else {	//Funciones 2, 3 y 4
			
			maxFitness = -Double.MAX_VALUE;
			mejorGeneracion = Double.MAX_VALUE;
			String mejorFenotipo = "";
			
			for (Individuo ind : poblacion) {
				
				double fitness = ind.calculateFitness();
				if (fitness < mejorGeneracion) {
					
					mejorGeneracion = fitness;
					
					if (ind.getId().equals("Funcion Michalewicz (Reales)") || ind.getId().equals("Funcion Michalewicz (Booleanos)")) {
						mejorFenotipo = ind.getFenotipo() + "Fitness: " + fitness;
					}
					else {
						mejorFenotipo = ind.getFenotipo() + "\nFitness: " + fitness;
					}
				}
				
				if (fitness > maxFitness) {
					
					maxFitness = fitness;
				}
				
				this.mediaGeneracion[this.generacionActual] += fitness;
			}
			
			this.mediaGeneracion[this.generacionActual] = this.mediaGeneracion[this.generacionActual] / this.tamPoblacion;
			this.mejorGeneracion[this.generacionActual] = mejorGeneracion;
			
			if (this.generacionActual == 0 || mejorGeneracion < this.mejorAbsoluto[this.generacionActual - 1]) {
				
				this.mejorSolucion = mejorFenotipo;
			}
			if (this.generacionActual == 0 || this.mejorGeneracion[this.generacionActual] < this.mejorAbsoluto[this.generacionActual - 1]) {
				
				this.mejorAbsoluto[this.generacionActual] = this.mejorGeneracion[this.generacionActual];
			}
			else {
				
				this.mejorAbsoluto[this.generacionActual] = this.mejorAbsoluto[this.generacionActual - 1];
			}
			
			generaElite(poblacion, porcElite, tipoIndividuo);
			
			//Desplazamos aptitud
			for (Individuo individuo : poblacion) {
				
				double newFitness = maxFitness - individuo.getFitness();
				individuo.setFitnessDesplazada(newFitness);
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
	public String getSolucion() {
		return this.mejorSolucion;
	}
	
	public void inicializaPoblacion(int tamPoblacion, String tipoIndividuo, ArrayList<Individuo> poblacion, double precision, int numN) {
		
		for (int i = 0; i < tamPoblacion; i++) {
			Individuo nuevoInd = FactoriaIndividuo.getIndividuo(tipoIndividuo, precision, numN);
			nuevoInd.inicializaIndividuo();
			poblacion.add(nuevoInd);
		}
	}
	
	public void generaElite(List<Individuo> poblacion, double porcElite, String tipoIndividuo) {
		
		elite = new ArrayList<Individuo>();
		List<Individuo> poblacionAuxiliar = new ArrayList<>();
		poblacionAuxiliar.addAll(poblacion);
		
		if(tipoIndividuo == "Funcion 1") {
			Collections.sort(poblacionAuxiliar, new Comparator<Individuo>() { 		// ordena de mayor a menor
				@Override
				public int compare(Individuo o1, Individuo o2) {
					return Double.compare(o2.getFitness(), o1.getFitness());
				}
				
			});
		} else {
			Collections.sort(poblacionAuxiliar, new Comparator<Individuo>() { 		// ordena de menor a mayor
				@Override
				public int compare(Individuo o1, Individuo o2) {
					return Double.compare(o1.getFitness(), o2.getFitness());
				}
				
			});
		}
		for(int i = 0; i < porcElite*poblacion.size(); i++) {
			elite.add(poblacionAuxiliar.get(i));
		}
		
		for(int i = 0; i < elite.size(); i++) {
			poblacion.remove(elite.get(i));
		}
	}
}
