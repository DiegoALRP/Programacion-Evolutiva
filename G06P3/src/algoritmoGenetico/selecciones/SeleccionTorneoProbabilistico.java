package algoritmoGenetico.selecciones;

import java.util.ArrayList;
import java.util.Random;

import algoritmoGenetico.individuos.Individuo;

public class SeleccionTorneoProbabilistico extends Seleccion{

	/**************************** ATRIBUTTES *******************************/
	private final int tamTorneo = 2;
	private int tamPoblacion;
	private final double probSelecPeor = 0.5;
	
	
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
		double minFitness = Double.MAX_VALUE;
		int indexBestIndividual = 0;
		int indexWorstIndividual = 0;
		
		for (int i = 0; i < tamTorneo; i++) {
			
			double fitness = candidatos.get(i).getFitness();
			
			if (fitness > maxFitness) {
				
				maxFitness = fitness;
				indexBestIndividual = i;
			}
			if (fitness < minFitness) {
				minFitness = fitness;
				indexWorstIndividual = i;
			}
		}
		
		Random rand = new Random();
		if (rand.nextDouble() < this.probSelecPeor) {
			addIndividuo(nuevaPoblacion, candidatos.get(indexWorstIndividual));
		}
		else {
			addIndividuo(nuevaPoblacion, candidatos.get(indexBestIndividual));
		}
	}

	/**************************** GETTERS & SETTERS ********************************/

}
