package algoritmoGenetico;

import java.util.ArrayList;
import java.util.List;

import algoritmoGenetico.cruces.Cruce;
import algoritmoGenetico.cruces.CruceMonopunto;
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
	
	private int tamPoblacion;			//Tamanho de la poblacion
	
	/*
	 * crear poblacion inicial
	 * 
	 */
	public AlgoritmoGenetico(int tamPoblacion, int numGeneraciones, double precision, Seleccion metodoSeleccion, Cruce metodoCruce, 
			double porcCruce, Mutacion metodoMutacion, double porcMutacion, double elite) {
		
		// IndividuoFuncion1 ind1 = new IndividuoFuncion1();
		// ind1.inicializaIndividuo();
		// System.out.println(ind1.getFitness());
		
		/*List<Individuo> poblacion = new ArrayList<Individuo>();
		for(int i=0;i<20;i++) {
			IndividuoFuncion1 ind1 = new IndividuoFuncion1();
			ind1.inicializaIndividuo();
			poblacion.add(ind1);
		}
	
		Seleccion ruleta = new SeleccionRuleta(poblacion);
		ruleta.seleccionar(poblacion);*/
		
		List<Individuo> poblacion = new ArrayList<Individuo>();
		for (int i = 0; i < 2; i++) {
			
			Individuo ind1 = new IndividuoFuncion1();
			ind1.inicializaIndividuo();
			poblacion.add(ind1);
		}
		
		Seleccion torneo = new SeleccionTorneo(tamPoblacion);
		torneo.seleccionar(poblacion);
	}
	
	public <T> void start() {
		
		/*ArrayList<Individuo<Boolean>> poblacion = new ArrayList<Individuo<Boolean>>();
		for(int i=0;i<5;i++) {
			Individuo ind1 = new IndividuoFuncion1();
			ind1.inicializaIndividuo();
			poblacion.add(ind1);
		}
		
		CruceMonopunto cruce = new CruceMonopunto(0.6);
		cruce.cruza(poblacion);
		
		MutacionBasica mutacion = new MutacionBasica(0.2);
		mutacion.mutaPoblacionBoolean(poblacion);*/
		
		/*List<Individuo> poblacion = new ArrayList<Individuo>();
		for (int i = 0; i < 2; i++) {
			
			Individuo ind1 = new IndividuoFuncion1();
			ind1.inicializaIndividuo();
			poblacion.add(ind1);
		}
		
		Seleccion torneo = new SeleccionTorneo();
		torneo.seleccionar(poblacion);*/
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
}
