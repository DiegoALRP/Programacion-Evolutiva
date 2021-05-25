package algoritmoGenetico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Random;

import algoritmoGenetico.cruces.Cruce;
import algoritmoGenetico.individuos.Individuo;
import algoritmoGenetico.individuos.Operando;
import algoritmoGenetico.individuos.RastroSantaFe;
import algoritmoGenetico.misc.Pair;
import algoritmoGenetico.mutaciones.Mutacion;
import algoritmoGenetico.selecciones.Seleccion;

/**
 * Universidad Complutense de Madrid.
 * Programación Evolutiva.
 * Grupo A 2021.
 * Profesor:
 * 	-Carlos Cervigon Ruckauer.
 * 
 * Práctica 3
 * 
 * Clase Algoritmo Genético.
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodríguez Pereira.
 *
 */
public class AlgoritmoGenetico {

	/****************************************************************************/
	/******************************** ATRIBUTTES ********************************/
	/****************************************************************************/
	
	/** Algoritmo Genetico **/
	private int tamPoblacion;			//Tamanho de la poblacion.
	private int numGeneraciones;		//Numero de generaciones que se van a ejecutar.
	private Seleccion metodoSeleccion;	//Metodo de Seleccion.
	private Cruce metodoCruce;			//Metodo de Cruce.
	private double porcCruce;			//Porcentaje de Cruce.
	private Mutacion metodoMutacion;	//Metodo de Mutacion.
	private double porcMutacion;		//Porcentaje de Mutacion.
	private double porcElitismo;		//Porcentaje de Elitismo.
	private String metodoInicializacion;//Metodo de Inicializacion.
	private int profundidadMaxima;		//Profundidad Maxima del Arbol.
	private int numeroPasos;			//Numero de movimientos a realizar;
	private RastroSantaFe santaFe;		//Tablero
	private String metodoBloating;		//Metodo de control de Bloating
	
	
	/** Poblacion **/
	private ArrayList<Individuo> poblacion;	//Poblacion.
	private ArrayList<Individuo> elite;		//Poblacion de Elite.
	private ArrayList<Individuo> plebe;		//Poblacion de Plebenhos.
	private int generacionActual;			//Generacion en la que estamos actualmente.
	private int generacionSolucion;			//Generacion en la que se obtuvo la solucion.

	
	/** Individuo **/
	//Mejor Individuo Absoluto
	private Individuo mejorIndividuoAbsoluto;	//Mejor Individuo Absoluto.
	private double mejorFitnessAbsoluto;		//Fitness del mejor individuo absoluto.
	private double[] arrayMejorFitnessAbsoluto;	//Array que contiene el fitness del mejor individuo absoluto por generacion.
	
	//Mejor Individuo Generacion
	private double[] mejorFitnessGeneracion;	//Array que contiene el mejor individuo de esa generacion.
	
	//Media Individuos
	private double mediaFitnessTotal;			//Media de todos los individuos de todas las generaciones.
	private double[] mediaFitnessGeneracion;	//Array con la media de la poblacion en cada generacion.
	private double[] mediaAlturaGeneracion;
	
	//Presion Selectiva
	private double[] presionSelectiva;			//Array con la presion selectiva de cada generacion.
	
	private boolean plebeBool;
	private ArrayList<Pair> mejorCaminoHormiga;
	
	/****************************************************************************/
	/******************************* CONSTRUCTOR ********************************/
	/****************************************************************************/
	
	/**
	 * Constructora del Algoritmo Genetico.
	 * 
	 * 
	 * @param tamPoblacion 		Tamanho de la poblacion.
	 * @param numGeneraciones	Numero de generaciones.
	 * @param metodoSeleccion	Metodo de Deleccion.
	 * @param metodoCruce		Metodo de Cruce.
	 * @param porcCruce			Porcentaje de Cruce.
	 * @param metodoMutacion	Metodo de Mutacion.
	 * @param porcMutacion		Porcentaje de Mutacion.
	 * @param porcElite			Porcentaje de Elitismo.
	 */
	public AlgoritmoGenetico(int tamPoblacion, int numGeneraciones, Seleccion metodoSeleccion,
			Cruce metodoCruce, double porcCruce, Mutacion metodoMutacion, double porcMutacion,
			double porcElite, String metodoInicializacion, int profundidadMaxima, 
			int numeroPasos, RastroSantaFe santaFe) {
		
		this.tamPoblacion = tamPoblacion;
		this.numGeneraciones = numGeneraciones;
		this.metodoSeleccion = metodoSeleccion;
		this.metodoCruce = metodoCruce;
		this.porcCruce = porcCruce/100;
		this.metodoMutacion = metodoMutacion;
		this.porcMutacion = porcMutacion/100;
		this.porcElitismo = porcElite/100;
		
		this.metodoInicializacion = metodoInicializacion;
		this.profundidadMaxima = profundidadMaxima;
		this.numeroPasos = numeroPasos;
		this.santaFe = santaFe;
		
		this.metodoBloating = "Tarpeian";
		
		this.plebeBool = true;
		this.inicializaVariables();
	}
	
	
	
	/***************************************************************************/
	/********************************* METHODS *********************************/
	/***************************************************************************/
	
	public void startAlgorithm() {
		
		inicializaPoblacion();
		
		while (this.generacionActual < this.numGeneraciones) {
			
			this.generaElite();
			this.poblacion = this.metodoSeleccion.seleccionar(poblacion);
			//System.out.println("A");
			this.metodoCruce.cruza(poblacion, porcCruce);
			//System.out.println("B");
			this.metodoMutacion.muta(poblacion, porcMutacion);
			//System.out.println("C");
			
			this.evaluaFitnessPoblacion();
			this.reintroduceElite();
			//System.out.println("D");
			this.evaluaFitnessPoblacion();
			//System.out.println("E");
			//System.out.println("MediaAltura: " + this.mediaAlturaGeneracion[generacionActual]);
			this.bloating();
			//System.out.println(generacionActual);
			generacionActual++;
			//System.out.println(this.generacionActual);
		}
	}
	
	private void evaluaFitnessPoblacion() {
		
		int mejorGeneracion = -Integer.MAX_VALUE;
		Individuo mejorIndividuo = null;
		int mediaGeneracion = 0;
		int mediaAltura = 0;
		
		int fitness;
		for (Individuo ind : poblacion) {
			
			fitness = ind.calculateFitness();
			
			if (fitness > mejorGeneracion) {
				
				mejorGeneracion = fitness;
				mejorIndividuo = ind;
				System.out.println("Mejor: " + ind.printFenotipo());
			}
			
			mediaGeneracion += fitness;
			mediaAltura += ind.getTreeSize();
		}
		
		this.mejorFitnessGeneracion[generacionActual] = mejorGeneracion;
		
		this.mediaFitnessGeneracion[generacionActual] = mediaGeneracion/tamPoblacion;
		this.mediaFitnessTotal += this.mediaFitnessGeneracion[generacionActual];
		
		this.mediaAlturaGeneracion[generacionActual] = mediaAltura/tamPoblacion;
		
		this.presionSelectiva[generacionActual] = mejorGeneracion/(mediaGeneracion/tamPoblacion);
		
		if (this.generacionActual == 0 ||
				mejorGeneracion > this.arrayMejorFitnessAbsoluto[generacionActual - 1]) {
			
			this.arrayMejorFitnessAbsoluto[generacionActual] = mejorGeneracion;
			
			this.mejorFitnessAbsoluto = mejorGeneracion;
			
			this.mejorIndividuoAbsoluto = new Individuo(mejorIndividuo.copyFenotipe(), metodoInicializacion, 
					profundidadMaxima, numeroPasos, santaFe);
			this.mejorCaminoHormiga = mejorIndividuo.getCamino();
		}
		else {
			
			this.arrayMejorFitnessAbsoluto[generacionActual] = this.arrayMejorFitnessAbsoluto[generacionActual - 1];
		}
	}
	
	private void bloating() {
		
		if (metodoBloating.equalsIgnoreCase("tarpeian")) {
			bloatingTarpeian();
		}
	}
	
	private void bloatingTarpeian() {
		
		Random rand = new Random();
		for (Individuo ind : poblacion) {
			
			int altura = ind.getTreeSizeConst();
			if (altura > this.mediaAlturaGeneracion[generacionActual] && rand.nextBoolean()) {
				
				//ind.setFitness((int) (ind.getFitness() - (altura - mediaAlturaGeneracion[generacionActual])));
			}
		}
	}
	
	private void generaElite() {
			
			int numElite = (int) Math.ceil(this.tamPoblacion*this.porcElitismo);
			int numPlebe = numElite*3;
			
			this.elite = new ArrayList<Individuo>(numElite);
			//this.plebe = new ArrayList<Individuo>(numPlebe);
			
			ArrayList<Individuo> poblacionAuxiliar = new ArrayList<Individuo>(this.poblacion);
			Collections.sort(poblacionAuxiliar, new Comparator<Individuo>() {
	
				@Override
				public int compare(Individuo o1, Individuo o2) {
					
					return Double.compare(o2.getFitness(), o1.getFitness());
				}
			});
			
			for (int i = 0; i < numElite; i++) {
				
				elite.add(poblacionAuxiliar.get(i));
			}
			/*for (int i = 0; i < numPlebe; i++) {
				
				plebe.add(poblacionAuxiliar.get(tamPoblacion - i - 1));
			}*/
		}
	
		public void reintroduceElite() {
		
			int numElite = (int) Math.ceil(this.tamPoblacion*this.porcElitismo);
			HashSet<Integer> indexAdded = new HashSet<Integer>(numElite);
			int numAdded = 0;
			int index;
			Random rand = new Random();
			while (numAdded < numElite && indexAdded.size() < this.tamPoblacion) {
				
				index = rand.nextInt(this.tamPoblacion);
				while (indexAdded.contains(index)) {
					
					index = rand.nextInt(this.tamPoblacion);
				}
				
				indexAdded.add(index);
				
				Individuo eli = this.elite.get(numAdded);
				double fitnessEli = eli.calculateFitness();
				Individuo ple = this.poblacion.get(index);
				double fitnessPle = ple.getFitness();
				if (fitnessEli > fitnessPle) {
					
					ple.setCromosoma(eli.getCromosoma());
					ple.calculateFitness();
					
					numAdded++;
				}
			}
			
			/*for (Individuo indPlebe : this.plebe) {
				
				index = rand.nextInt(this.tamPoblacion);
				while (indexAdded.contains(index)) {
					
					index = rand.nextInt(this.tamPoblacion);
				}
				
				indexAdded.add(index);
				
				double fitnessPlebe = indPlebe.getFitness();
				Individuo indComparado = this.poblacion.get(index);
				double fitnessComparado = indComparado.getFitness();
				
				if (fitnessPlebe > fitnessComparado && this.plebeBool) {
					
					indComparado.setCromosoma(indPlebe.getCromosoma());
					indComparado.calculateFitness();
				}
			}*/
		}
	
	
	/***************************************************************************/
	/**************************** AUXILIARY METHODS ****************************/
	/***************************************************************************/
	
	/**
	 * 
	 * [ES] Esta función auxiliar inicializa los atributos de la clase.
	 * [EN] This auxiliary function initialize the class's attributes.
	 * 
	 */
	private void inicializaVariables() {
		
		this.arrayMejorFitnessAbsoluto = new double[numGeneraciones];
		
		this.mejorFitnessGeneracion = new double[numGeneraciones];
		
		this.mediaFitnessGeneracion = new double[numGeneraciones];
		
		this.mediaAlturaGeneracion = new double[numGeneraciones];
		
		this.presionSelectiva = new double[numGeneraciones];
		
	}
	
	
	private void inicializaPoblacion() {
		
		this.poblacion = new ArrayList<Individuo>(tamPoblacion);
		
		for (int i = 0; i < tamPoblacion; i++) {
			
			//if (i%2 == 0);
			/*ArrayList<Operando> fenotipo = new ArrayList<Operando>();
			fenotipo.add(new Operando("SIComida"));
			fenotipo.add(new Operando("AVANZA"));
			fenotipo.add(new Operando("PROGN3"));
			fenotipo.add(new Operando("DERECHA"));
			fenotipo.add(new Operando("SIComida"));
			fenotipo.add(new Operando("IZQUIERDA"));
			fenotipo.add(new Operando("DERECHA"));
			fenotipo.add(new Operando("PROGN3"));
			fenotipo.add(new Operando("DERECHA"));
			fenotipo.add(new Operando("SIComida"));
			fenotipo.add(new Operando("AVANZA"));
			fenotipo.add(new Operando("DERECHA"));
			fenotipo.add(new Operando("AVANZA"));
			Individuo ind = new Individuo(fenotipo, metodoInicializacion, profundidadMaxima, numeroPasos, santaFe);*/
			
			if (metodoInicializacion.equals("Ramped and Half")) {
				
				if (i%2==0) {
					
					Individuo ind = new Individuo("Completo", profundidadMaxima, numeroPasos, santaFe);
					poblacion.add(ind);
				}
				else {
					
					Individuo ind = new Individuo("Creciente", profundidadMaxima, numeroPasos, santaFe);
					poblacion.add(ind);
				}
			}
			else {
				
				Individuo ind = new Individuo(metodoInicializacion, profundidadMaxima, numeroPasos, santaFe);
				poblacion.add(ind);
			}
		}
	}
	

	/***************************************************************************/
	/**************************** GETTERS & SETTERS ****************************/
	/***************************************************************************/
	
	public ArrayList<Pair> getCaminoHormiga(){
		return this.mejorCaminoHormiga;
	}
	
	public double getMejorFitness() {
		return this.mejorFitnessAbsoluto;
	}
	
	public double[] getPresionSelectiva() {
		return this.presionSelectiva;
	}
	
	public double[] getArrayMejorAbsoluto() {
		return this.arrayMejorFitnessAbsoluto;
	}
	
	public double[] getMejorFitnessGeneracion() {
		return this.mejorFitnessGeneracion;
	}
	
	public double[] getMediaFitnessGeneracion() {
		return this.mediaFitnessGeneracion;
	}
}
