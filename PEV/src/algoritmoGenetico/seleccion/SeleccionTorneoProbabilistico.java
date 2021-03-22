package algoritmoGenetico.seleccion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import algoritmoGenetico.individuos.FactoriaIndividuo;
import algoritmoGenetico.individuos.Individuo;

public class SeleccionTorneoProbabilistico extends Seleccion{

	private final int tamTorneo = 2;
	private final double probSelecWorst = 0.5;
	private int tamPoblacion;
	
	public SeleccionTorneoProbabilistico(int participantes) {
		
	}

	@Override
	public List<Individuo> seleccionar(List<Individuo> poblacion) {
	
		tamPoblacion = poblacion.size(); //Cogemos el tamaño de la población
		ArrayList<Individuo> nuevaPoblacion = new ArrayList<Individuo>();	//Creamos un ArrayList para la nueva población
		ArrayList<Individuo> candidatos = new ArrayList<Individuo>();
		
		Random rand = new Random();
		int count = 0;
		while (count < tamPoblacion) {
			
			candidatos.clear();
			for (int i = 0; i < tamTorneo; i++) {
				
				candidatos.add(poblacion.get(rand.nextInt(tamPoblacion)));
			}
			
			competenciaTorneo(candidatos, nuevaPoblacion);
			count++;
		}
		
		return nuevaPoblacion;
	}
	
	public void competenciaTorneo(ArrayList<Individuo> candidatos, ArrayList<Individuo> nuevaPoblacion){
		
		double maxFitness = 0;
		double minFitness = Double.MAX_VALUE;
		int indexBestIndividual = 0;
		int indexWorstIndividual = 0;
		
		for (int i = 0; i < tamTorneo; i++) {
			
			double fitness = candidatos.get(i).getFitnessDesplazado();
			
			if (fitness > maxFitness) {
				
				maxFitness = fitness;
				indexBestIndividual = i;
			}
			if (fitness < minFitness) {
				
				minFitness = fitness;
				indexWorstIndividual = i;
			}
		}
		
		Individuo indSeleccionado = candidatos.get(indexBestIndividual);
		Random rand = new Random();
		if (rand.nextDouble() < this.probSelecWorst) {
			indSeleccionado = candidatos.get(indexWorstIndividual);
		}
		
		Individuo nuevoIndividuo = FactoriaIndividuo.getIndividuo(indSeleccionado.getId(), indSeleccionado.getPrecision());
		
		ArrayList cromoPadre1 = indSeleccionado.getCromosoma();
		ArrayList cromoHijo1 = new ArrayList();
		cromoHijo1.addAll(cromoPadre1);
		nuevoIndividuo.setCromosoma(cromoHijo1);
		
		nuevaPoblacion.add(nuevoIndividuo);
	}
}
