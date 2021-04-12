package algoritmoGenetico.seleccion;

import java.util.ArrayList;
import java.util.Random;

import algoritmoGenetico.individuos.Individuo;

/**
 * Universidad Complutense de Madrid.
 * Programación Evolutiva.
 * Grupo A 2021.
 * Profesor:
 * 	-Carlos Cervigon Ruckauer.
 * 
 * Clase Seleccion por Torneo Determinista.
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodríguez Pereira.
 *
 */
public class SeleccionTorneo extends Seleccion {

	/**************************** ATRIBUTTES *******************************/
	private final int tamTorneo = 0;
	private int tamPoblacion;
	
	/**************************** CONSTRUCTOR *******************************/
	
	/***************************** METHODS ********************************/
	public ArrayList<Individuo> seleccionar(ArrayList<Individuo> poblacion){
		
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
		
		poblacion = nuevaPoblacion;
		return poblacion;
	}
	
	public void competenciaTorneo(ArrayList<Individuo> nuevaPoblacion, ArrayList<Individuo> candidatos) {
		
		double maxFitness = 0;
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
	
	/**************************** GET & SET ********************************/
}
