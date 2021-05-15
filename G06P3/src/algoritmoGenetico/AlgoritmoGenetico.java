package algoritmoGenetico;

import java.util.ArrayList;

import algoritmoGenetico.cruces.Cruce;
import algoritmoGenetico.individuos.Individuo;
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

	/******************************** ATRIBUTTES ********************************/
	private int tamPoblacion;			//Tamanho de la poblacion.
	private int numGeneraciones;		//Numero de generaciones que se van a ejecutar.
	private Seleccion metodoSeleccion;	//Metodo de Seleccion.
	private Cruce metodoCruce;			//Metodo de Cruce.
	private double porcCruce;			//Porcentaje de Cruce.
	private Mutacion metodoMutacion;	//Metodo de Mutacion.
	private double porcMutacion;		//Porcentaje de Mutacion.
	private double porcElitismo;		//Porcentaje de Elitismo.
	
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
	
	//Presion Selectiva
	private double[] presionSelectiva;			//Array con la presion selectiva de cada generacion.
	
	
	
	/******************************* CONSTRUCTOR ********************************/
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
			double porcElite) {
		
		this.tamPoblacion = tamPoblacion;
		this.numGeneraciones = numGeneraciones;
		this.metodoSeleccion = metodoSeleccion;
		this.metodoCruce = metodoCruce;
		this.porcCruce = porcCruce;
		this.metodoMutacion = metodoMutacion;
		this.porcMutacion = porcMutacion;
		this.porcElitismo = porcElite;
	}
	
	/********************************* METHODS *********************************/
	
	/**************************** AUXILIARY METHODS ****************************/
	private void inicializaVariables() {
		
		this.arrayMejorFitnessAbsoluto = new double[numGeneraciones];
		
		this.mejorFitnessGeneracion = new double[numGeneraciones];
		
		this.mediaFitnessGeneracion = new double[numGeneraciones];
		
		this.presionSelectiva = new double[numGeneraciones];
	}
	
	/**************************** GETTERS & SETTERS ****************************/
}
