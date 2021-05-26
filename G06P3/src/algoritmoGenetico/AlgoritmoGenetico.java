package algoritmoGenetico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Random;

import algoritmoGenetico.cruces.Cruce;
import algoritmoGenetico.individuos.Individuo;
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

	
	/** Individuo **/
	//Mejor Individuo Absoluto
	@SuppressWarnings("unused")
	private Individuo mejorIndividuoAbsoluto;	//Mejor Individuo Absoluto.
	private double mejorFitnessAbsoluto;		//Fitness del mejor individuo absoluto.
	private double[] arrayMejorFitnessAbsoluto;	//Array que contiene el fitness del mejor individuo absoluto por generacion.
	
	//Mejor Individuo Generacion
	private double[] mejorFitnessGeneracion;	//Array que contiene el mejor individuo de esa generacion.
	
	//Media Individuos
	@SuppressWarnings("unused")
	private double mediaFitnessTotal;			//Media de todos los individuos de todas las generaciones.
	private double[] mediaFitnessGeneracion;	//Array con la media de la poblacion en cada generacion.
	
	//Presion Selectiva
	private double[] presionSelectiva;			//Array con la presion selectiva de cada generación.
	
	//Bloating
	private double[] mediaAlturaGeneracion;
	private int[] fitnessIndividuos;
	private int[] alturaIndividuos;
	//private final double k = 0.1;
	
	@SuppressWarnings("unused")
	private boolean plebeBool;
	private ArrayList<Pair> mejorCaminoHormiga;
	
	
	/****************************************************************************/
	/******************************* CONSTRUCTOR ********************************/
	/****************************************************************************/
	
	/**
	 * Constructora del Algoritmo Genetico.
	 * 
	 * 
	 * @param tamPoblacion 		Tamaño de la población.
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
			double porcElite, String metodoInicializacion, String metodoBloating, int profundidadMaxima, 
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
		
		this.metodoBloating = metodoBloating;
		
		this.plebeBool = true;
		this.inicializaVariables();
	}
	
	
	
	/***************************************************************************/
	/********************************* METHODS *********************************/
	/***************************************************************************/
	
	/**
	 * [ES] Método principal del algoritmo genético.
	 * Aquí se ejecutan:
	 * 	-Selección
	 * 	-Cruce
	 * 	-Mutación
	 * 	-Elitismo (si procede)
	 *  
	 * [EN] This is the main method of genetic algorithm
	 * Here we execute:
	 * 	-Selection
	 * 	-Crossover
	 * 	-Mutation
	 * 	-Elitism (if applicable)
	 * 
	 */
	public void startAlgorithm() {
		
		inicializaPoblacion();
		
		while (this.generacionActual < this.numGeneraciones) {
			
			this.generaElite();
			this.poblacion = this.metodoSeleccion.seleccionar(poblacion);
			this.metodoCruce.cruza(poblacion, porcCruce);
			this.metodoMutacion.muta(poblacion, porcMutacion);
			
			this.evaluaFitnessPoblacion();
			this.reintroduceElite();
			this.evaluaFitnessPoblacion();
			this.bloating();
			generacionActual++;
		}
	}
	
	
	
	/**
	 * [ES] Este metodo evalua el valor de aptitud/fitness de la población.
	 * Para ello primero se actualiza el valor de fitness de cada individuo.
	 * Una vez actualizado, buscamos el mejor individuo de toda la población.
	 * Y por último guardamos el valor del mejor individuo en los respectivos 
	 * atributos de clase. (MejorIndividuoGeneracion, MejorIndividuoAbsoluto, ...)
	 * 
	 * [EN] This method evaluates population's fitness value.
	 * It updates the fitness value of each individual.
	 * Once updated, we search for the best individual in all the population.
	 * Y last, we store the value of the best individual in the respective class's
	 * attributes. (MejorIndividuoGeneracion, MejorIndividuoAbsoluto, ...)
	 * 
	 */
	private void evaluaFitnessPoblacion() {
		
		int mejorGeneracion = -Integer.MAX_VALUE;
		Individuo mejorIndividuo = null;
		int mediaGeneracion = 0;
		
		//Bloating
		int mediaAltura = 0;
		int altura = 0;
		int maxAltura = 0;
		@SuppressWarnings("unused")
		String alto = "";
		fitnessIndividuos = new int[tamPoblacion];
		alturaIndividuos = new int[tamPoblacion];
		
		int fitness;
		int i = 0;
		for (Individuo ind : poblacion) {
			
			fitness = ind.calculateFitness();
			
			if (fitness > mejorGeneracion) {
				
				mejorGeneracion = fitness;
				mejorIndividuo = ind;
			}
			
			mediaGeneracion += fitness;
			altura = ind.getTreeSize();
			mediaAltura += altura;
			if (altura > maxAltura) {
				maxAltura = altura;
				alto = ind.printFenotipo();
			}
			
			fitnessIndividuos[i] = fitness;
			alturaIndividuos[i] = altura;
			i++;
		}
		
		this.mejorFitnessGeneracion[generacionActual] = mejorGeneracion;
		
		this.mediaFitnessGeneracion[generacionActual] = mediaGeneracion/tamPoblacion;
		this.mediaFitnessTotal += this.mediaFitnessGeneracion[generacionActual];
		
		this.mediaAlturaGeneracion[generacionActual] = mediaAltura/tamPoblacion;
		double div = mediaGeneracion/tamPoblacion;
		this.presionSelectiva[generacionActual] = mejorGeneracion/div;
		
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
	
	
	/**
	 * [ES] Función que genera la élite de la población.
	 * Recorre toda la población y se queda con los mejores individuos.
	 * Aquí hemos agregado nuestra propia creación que hemos llamados "La Plebe".
	 * 
	 */
	private void generaElite() {
			
			int numElite = (int) Math.ceil(this.tamPoblacion*this.porcElitismo);
			int numPlebe = numElite*3;
			
			this.elite = new ArrayList<Individuo>(numElite);
			this.plebe = new ArrayList<Individuo>(numPlebe);
			
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
			for (int i = 0; i < numPlebe; i++) {
				
				plebe.add(poblacionAuxiliar.get(tamPoblacion - i - 1));
			}
		}
	
	/**
	 * [ES] Función que reintroduce la élite.
	 * [EN] Function that reintroduces the elite.
	 * 
	 */
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
	
	
	/**
	 * [ES] Inicializa la población, creando tantos nuevos individuos como el
	 * tamaño de la población.
	 * 
	 * [EN] Initializes the population, creating as many new individual as
	 * the size of the population.
	 * 
	 */
	private void inicializaPoblacion() {
		
		this.poblacion = new ArrayList<Individuo>(tamPoblacion);
		
		for (int i = 0; i < tamPoblacion; i++) {
			
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
	/*********************** AUXILIARY METHODS - Bloating **********************/
	/***************************************************************************/
	
	private void bloating() {
		
		if (metodoBloating.equalsIgnoreCase("Tarpeian")) {
			bloatingTarpeian();
		}
		else if (metodoBloating.equalsIgnoreCase("Penalizacion")) {
			bloatingPenalizacion();
		}
		else {
			bloatingCasero();
		}
		
	}
	
	private void bloatingTarpeian() {
		
		Random rand = new Random();
		for (Individuo ind : poblacion) {
			
			int altura = ind.getTreeSizeConst();
			if (altura > (profundidadMaxima+1)*2 + 1) {
				ind.setFitness(0);
			}
			else if (altura > this.mediaAlturaGeneracion[generacionActual] && rand.nextBoolean()) {
				
				ind.setFitness((int) (ind.getFitness() - (altura - mediaAlturaGeneracion[generacionActual])));
			}
		}
	}
	
	private void bloatingPenalizacion() {
		
		int fitness = 0;
		for (Individuo ind : poblacion) {
			
			int altura = ind.getTreeSizeConst();
			if (altura > (profundidadMaxima+1)*2 + 1) {
				ind.setFitness(0);
			}
			else {
				fitness = ind.getFitness();
				double k = getCovarianza()/getVarianza();
				ind.setFitness((int) (fitness - (k * ind.getNumNodosArbol())));
			}
		}
	}
	
	private double getCovarianza() {
		
		double covarianza = 0;
		
		double x = 0;
		double y = 0;
		for (int i = 0; i < tamPoblacion; i++) {
			
			x += (alturaIndividuos[i] - mediaAlturaGeneracion[generacionActual]);
			y += (fitnessIndividuos[i] - mediaFitnessGeneracion[generacionActual]);
		}
		
		covarianza = (x*y)/tamPoblacion;
		
		return covarianza;
	}
	
	private double getVarianza() {
		
		double varianza = 0;
		
		double x = 0;
		for (int i = 0; i < tamPoblacion; i++) {
			
			x += Math.pow((alturaIndividuos[i] - mediaAlturaGeneracion[generacionActual]), 2);
		}
		
		varianza = x/tamPoblacion;
		
		return varianza;
	}
	
	private void bloatingCasero() {

		for (Individuo ind : poblacion) {
			
			int altura = ind.getTreeSizeConst();
			if (altura > (profundidadMaxima+1)*2 + 1) {
				ind.setFitness(0);
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
	
	public Individuo getMejorIndividuo() {
		return this.mejorIndividuoAbsoluto;
	}
}
