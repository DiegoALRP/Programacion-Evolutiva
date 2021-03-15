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
		ArrayList<Individuo> noSeleccionados = new ArrayList<Individuo>();
		ArrayList<Individuo> candidatos = new ArrayList<Individuo>();
		Random rand = new Random();
		
		System.out.println("Poblacion Inicial");
		for (int i = 0; i < tamPoblacion; i++) {
			
			System.out.println("Individuo: " + i + " " + poblacion.get(i).printCromosoma() + " Fitness " + poblacion.get(i).getFitness());
		}
		
		int count = 0;
		while (count < tamPoblacion) {
			
			candidatos.clear();
			for (int i = 0; i < tamTorneo; i++) {
				
				//candidatos.add(poblacion.get(rand.nextInt(tamPoblacion)));
				candidatos.add(poblacion.get(i));
			}
			
			competenciaTorneo(candidatos, nuevaPoblacion);
			count++;
		}
		
		ArrayList<Individuo> nuevaPoblacion1 = new ArrayList<Individuo>();
		nuevaPoblacion1.addAll(nuevaPoblacion);
		
		System.out.println("Muta primer individuo:");
		
		ArrayList<Boolean> mod = new ArrayList<Boolean>();
		for (int i = 0; i < nuevaPoblacion.get(0).getLongitudCromosoma(); i++) {
			
			mod.add(false);
		}
		nuevaPoblacion1.get(0).setCromosoma(mod);
		
		System.out.println("Poblacion Final");
		for (int i = 0; i < tamPoblacion; i++) {
			
			System.out.println("Individuo: " + i + " " + nuevaPoblacion1.get(i).printCromosoma() + " Fitness " + nuevaPoblacion1.get(i).getFitness());
		}
		
		return null;
	}
	
	public void competenciaTorneo(ArrayList<Individuo> candidatos, ArrayList<Individuo> nuevaPoblacion, ArrayList<Individuo> noSeleccionados){
		
		double maxFitness = 0;
		int indexBestIndividual = 0;
		int indexPeorIndividuo = 0;
		
		for (int i = 0; i < tamTorneo; i++) {
			
			double fitness = candidatos.get(i).getFitness();
			
			if (fitness > maxFitness) {
				
				maxFitness = fitness;
				indexBestIndividual = i;
			}
		}
		for (int i = 0; i < tamTorneo; i++) {
			
			double fitness = candidatos.get(i).getFitness();
			
			if (fitness <= maxFitness) {
				
				indexPeorIndividuo = i;
			}
		}
		
		noSeleccionados.add(candidatos.get(indexPeorIndividuo));
		nuevaPoblacion.add(candidatos.get(indexBestIndividual));
	}

}
