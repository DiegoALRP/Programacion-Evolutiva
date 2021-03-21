package algoritmoGenetico.seleccion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import algoritmoGenetico.individuos.FactoriaIndividuo;
import algoritmoGenetico.individuos.Individuo;

public class SeleccionTorneo extends Seleccion {
	
	private final int tamTorneo = 2;
	private int tamPoblacion;
	
	public SeleccionTorneo(int participantes) {
		
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
		int indexBestIndividual = 0;
		
		for (int i = 0; i < tamTorneo; i++) {
			
			double fitness = candidatos.get(i).getFitness();
			
			if (fitness > maxFitness) {
				
				maxFitness = fitness;
				indexBestIndividual = i;
			}
		}
		
		Individuo mejorIndividuo = candidatos.get(indexBestIndividual);
		Individuo nuevoIndividuo = FactoriaIndividuo.getIndividuo(mejorIndividuo.getId(), mejorIndividuo.getPrecision());
		
		ArrayList cromoPadre1 = mejorIndividuo.getCromosoma();
		ArrayList cromoHijo1 = new ArrayList();
		cromoHijo1.addAll(cromoPadre1);
		nuevoIndividuo.setCromosoma(cromoHijo1);
		
		nuevaPoblacion.add(nuevoIndividuo);
	}

}
