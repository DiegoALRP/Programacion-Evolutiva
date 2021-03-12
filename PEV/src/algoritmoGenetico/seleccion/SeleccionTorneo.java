package algoritmoGenetico.seleccion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import algoritmoGenetico.individuos.Individuo;

public class SeleccionTorneo extends Seleccion {
	
	private final int tamTorneo = 2;
	private int tamPoblacion;
	
	public SeleccionTorneo(int participantes) {
		
	}

	@Override
	public List<Individuo> seleccionar(List<Individuo> poblacion) {
	
		tamPoblacion = poblacion.size();
		ArrayList<Individuo> nuevaPoblacion = new ArrayList<Individuo>();
		ArrayList<Individuo> candidatos = new ArrayList<Individuo>();
		Random rand = new Random();
		
		int count = 0;
		while (count < tamPoblacion) {
			
			candidatos.clear();
			for (int i = 0; i < tamTorneo; i++) {
				
				candidatos.add(poblacion.get(rand.nextInt(tamPoblacion)));
			}
			
			//competenciaTorneo(candidatos, nuevaPoblacion);
		}
		
		return null;
	}
	
	public <T> Individuo<T> competenciaTorneo(ArrayList<Individuo<T>> candidatos, ArrayList<Individuo<T>> nuevaPoblacion){
		
		double maxFitness = 0;
		int indexBestIndividual = 0;
		
		for (int i = 0; i < tamTorneo; i++) {
			
			double fitness = candidatos.get(i).getFitness();
			
			if (fitness > maxFitness) {
				
				maxFitness = fitness;
				indexBestIndividual = i;
			}
		}
		
		return null;
	}

}
