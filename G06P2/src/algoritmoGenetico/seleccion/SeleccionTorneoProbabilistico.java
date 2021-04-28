package algoritmoGenetico.seleccion;

import java.util.ArrayList;
import java.util.Random;

import algoritmoGenetico.individuos.Individuo;

/**
 * Universidad Complutense de Madrid.
 * Programaciï¿½n Evolutiva.
 * Grupo A 2021.
 * Profesor:
 * 	-Carlos Cervigon Ruckauer.
 * 
 * Prï¿½ctica 2
 * 
 * Clase Seleccion por Torneo Probabilístico.
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodrï¿½guez Pereira.
 *
 */
public class SeleccionTorneoProbabilistico extends Seleccion {
	
	/**************************** ATRIBUTTES *******************************/
	private final int tamTorneo = 2;
	private int tamPoblacion;
	private final double probSelecPeor = 0.5;
	
	
	/**************************** CONSTRUCTOR *******************************/
	
	
	/***************************** METHODS ********************************/
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
