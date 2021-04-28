package algoritmoGenetico.seleccion;

import java.util.ArrayList;
import java.util.List;

import algoritmoGenetico.individuos.Individuo;

/**
 * Universidad Complutense de Madrid.
 * Programación Evolutiva.
 * Grupo A 2021.
 * Profesor:
 * 	-Carlos Cervigon Ruckauer.
 * 
 * Clase Selección por Ruleta.
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodríguez Pereira.
 *
 */
public class SeleccionRuleta extends Seleccion {

	
	/**************************** ATRIBUTTES *******************************/
	private double[] puntAcumulada;		
	private double prob;
	private int pos_super;
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
		
		ArrayList<Individuo> nuevaPoblacion = new ArrayList<Individuo>(poblacion.size());
		Individuo nuevo;
		for(int i = 0; i < poblacion.size(); i++) {
			
			prob = Math.random();
			while(pos_super < poblacion.size() && prob > puntAcumulada[pos_super]) {
				pos_super++;
			}
			nuevo = poblacion.get(pos_super);
			addIndividuo(nuevaPoblacion, nuevo);
			pos_super = 0;
		}
		
		return nuevaPoblacion;
	}
	
	public void inicializaAptitudes(List<Individuo> poblacion) {
		
		aptitudes = 0.0;
		acum = 0.0;
		pos_super = 0;
		puntAcumulada = new double[poblacion.size()];
		
		for(Individuo ind : poblacion) {
			aptitudes += ind.getFitness();
		}
		
		for(int i = 0; i < poblacion.size(); i++) {
			acum += poblacion.get(i).getFitness() / aptitudes;
			puntAcumulada[i] = acum;
		}
	}
	
	
	/**************************** GETTERS & SETTERS ********************************/
}
