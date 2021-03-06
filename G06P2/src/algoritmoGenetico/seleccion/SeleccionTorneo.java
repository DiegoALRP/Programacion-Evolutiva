package algoritmoGenetico.seleccion;

import java.util.ArrayList;
import java.util.Random;

import algoritmoGenetico.individuos.Individuo;

/**
 * Universidad Complutense de Madrid.
 * Programaci?n Evolutiva.
 * Grupo A 2021.
 * Profesor:
 * 	-Carlos Cervigon Ruckauer.
 * 
 * Pr?ctica 2
 * 
 * Clase Seleccion por Torneo Determinista.
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodr?guez Pereira.
 *
 */
public class SeleccionTorneo extends Seleccion {

	/**************************** ATRIBUTTES *******************************/
	private final int tamTorneo = 2;
	private int tamPoblacion;
	
	/**************************** CONSTRUCTOR ******************************/
	
	
	
	/***************************** METHODS *********************************/
	
	/**
	 * [ES]	Esta funci?n es la principal de la clase de Selecci?n.
	 * Esta funci?n selecciona a los individuos de una poblaci?n para generar
	 * una poblaci?n nueva/auxiliar con los individuos elegidos.
	 * Ambas poblaciones son del mismo tama?o.
	 * [EN] This is the main function of the Selection's class.
	 * This function selects the individuals of a population to generate a new/auxiliar
	 * population with the individuals chosen.
	 * Both population are of the same size.
	 * 
	 * @param poblacion	[ES] Poblaci?n de la cual se van a seleccionar los individuos.
	 * 					[EN] Population form which it's going to be selected the individuals.
	 * 
	 * @return	[ES] La nueva poblaci?n con los individuos seleccionados.
	 * 			[EN] The new population with the selected individuals.
	 */
	@Override
	public ArrayList<Individuo> seleccionar(ArrayList<Individuo> poblacion) {
		
		this.tamPoblacion = poblacion.size();
		ArrayList<Individuo> nuevaPoblacion = new ArrayList<Individuo>();
		ArrayList<Individuo> candidatos = new ArrayList<Individuo>();
		
		Random rand = new Random();
		int count = 0;
		while(count < tamPoblacion) {
			
			candidatos.clear();
			
			for (int i = 0; i < tamTorneo; i++) {
				
				candidatos.add(poblacion.get(rand.nextInt(tamPoblacion)));
			}
			
			competenciaTorneo(nuevaPoblacion, candidatos);
			count++;
		}
		
		return nuevaPoblacion;
	}
	
	public void competenciaTorneo(ArrayList<Individuo> nuevaPoblacion, ArrayList<Individuo> candidatos) {
		
		double maxFitness = -Double.MAX_VALUE;
		int indexBestIndividual = 0;
		
		for (int i = 0; i < tamTorneo; i++) {
			
			double fitness = candidatos.get(i).getFitness();
			
			if (fitness > maxFitness) {
				
				maxFitness = fitness;
				indexBestIndividual = i;
			}
		}
		
		addIndividuo(nuevaPoblacion, candidatos.get(indexBestIndividual));
	}
	
	/**************************** GETTERS & SETTERS ********************************/
}
