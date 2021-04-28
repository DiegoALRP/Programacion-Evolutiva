package algoritmoGenetico.seleccion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import algoritmoGenetico.individuos.Individuo;

/**
 * Universidad Complutense de Madrid.
 * Programación Evolutiva.
 * Grupo A 2021.
 * Profesor:
 * 	-Carlos Cervigon Ruckauer.
 * 
 * Clase Selección Estocástico.
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodríguez Pereira.
 *
 */
public class SeleccionEstocastico extends Seleccion{

	
	/**************************** ATRIBUTTES *******************************/
	private double[] puntAcumulada;
	private double aptitudes;
	private double acum;
	
	
	/**************************** CONSTRUCTOR *******************************/
	
	
	
	/***************************** METHODS ********************************/
	
	/**
	 * [ES]	Esta función es la principal de la clase de Selección.
	 * Esta función selecciona a los individuos de una población para generar
	 * una población nueva/auxiliar con los individuos elegidos.
	 * Ambas poblaciones son del mismo tamaño.
	 * [EN] This is the main function of the Selection's class.
	 * This function selects the individuals of a population to generate a new/auxiliar
	 * population with the individuals chosen.
	 * Both population are of the same size.
	 * 
	 * @param poblacion	[ES] Población de la cual se van a seleccionar los individuos.
	 * 					[EN] Population form which it's going to be selected the individuals.
	 * 
	 * @return	[ES] La nueva población con los individuos seleccionados.
	 * 			[EN] The new population with the selected individuals.
	 */
	@Override
	public ArrayList<Individuo> seleccionar(ArrayList<Individuo> poblacion) {
		inicializaAptitudes(poblacion);
		ArrayList<Individuo> seleccionados = new ArrayList<Individuo>(poblacion.size());
		
		Random r = new Random();
		double marca = (1/poblacion.size()) * r.nextDouble();
		double puntero;
		
		for(int i = 0; i < poblacion.size(); i++) {
			
			 puntero = (marca + i -1) / poblacion.size();
			 Individuo nuevo= poblacion.get(getIndividuo(puntero));
			 addIndividuo(seleccionados, nuevo);
		}
		return seleccionados;
	}
	
	private int getIndividuo(double puntero) {
		int pivote = 0;
		while(puntAcumulada[pivote] < puntero) {
			pivote++;
		}	
		return pivote;
	}
	
	private void inicializaAptitudes(List<Individuo> poblacion) {
		
		aptitudes = 0.0;
		acum = 0.0;
		puntAcumulada = new double[poblacion.size()];
		
		for(Individuo ind : poblacion) {
			aptitudes += ind.getFitness();
		}
		
		for(int i = 0; i < poblacion.size(); i++) {
			acum += poblacion.get(i).getFitness() / aptitudes;
			puntAcumulada[i] = acum;
		}
		
	}

	
	/**************************** GET & SET ********************************/
}
