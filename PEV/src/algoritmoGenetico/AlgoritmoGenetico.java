package algoritmoGenetico;

import java.util.ArrayList;
import java.util.List;

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
		
		generacionActual = 0;
		ArrayList<Individuo> poblacion = new ArrayList<Individuo>(tamPoblacion);
		inicializaPoblacion(tamPoblacion, tipoIndividuo, poblacion);
		
		
		while (this.generacionActual < numGeneraciones) {
			
			//Evalua Poblacion
			metodoSeleccion.seleccionar(poblacion);
			//metodoCruce.cruza(poblacion, porcCruce);
			//metodoMutacion.mutaPoblacionBoolean(poblacion, porcMutacion);
			
			this.generacionActual++;
		}
		//metodoSeleccion.seleccionar(poblacion)
	}
	
	public void evaluar(String tipoIndividuo) {
		
		
		if (tipoIndividuo == "Funcion1") {	//Funcion1
			
			//max

			
			/*for(poblacion){
			 * 
			 * //calculamos el mayor fitness
			     *         calcular fitness
			     *     calcualr mejorAbs...*/
		}
		else {	//Funciones 2, 3 y 4
			
			//min
			/*for(poblacion){
			 * 
			 * //calculamos el menor fitness
			     *         calcular fitness
			     *     calcualr menorAbs...*/
			
			//Elitismo, peores
		}
		
		//Desplazamos aptitud
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
