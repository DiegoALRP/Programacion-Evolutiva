package algoritmoGenetico.seleccion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import algoritmoGenetico.individuos.FactoriaIndividuo;
import algoritmoGenetico.individuos.Individuo;

/**
 * Universidad Complutense de Madrid.
 * Programaci�n Evolutiva.
 * Grupo A 2021.
 * Profesor:
 * 	-Carlos Cervigon Ruckauer.
 * 
 * Clase Factoria Mutaci�n.
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodr�guez Pereira.
 *
 */
public class SeleccionTorneoProbabilistico extends Seleccion{

	private final int tamTorneo = 2;
	private final double probSelecWorst = 0.5;
	private int tamPoblacion;

	/**
	 * [ES]	Esta funci�n es la principal de la clase de Selecci�n.
	 * Esta funci�n selecciona a los individuos de una poblaci�n para generar
	 * una poblaci�n nueva/auxiliar con los individuos elegidos.
	 * Ambas poblaciones son del mismo tama�o.
	 * [EN] This is the main function of the Selection's class.
	 * This function selects the individuals of a population to generate a new/auxiliar
	 * population with the individuals chosen.
	 * Both population are of the same size.
	 * 
	 * @param poblacion	[ES] Poblaci�n de la cual se van a seleccionar los individuos.
	 * 					[EN] Population form which it's going to be selected the individuals.
	 * 
	 * @return	[ES] La nueva poblaci�n con los individuos seleccionados.
	 * 			[EN] The new population with the selected individuals.
	 */
	@Override
	public List<Individuo> seleccionar(List<Individuo> poblacion) {
	
		tamPoblacion = poblacion.size(); //Cogemos el tama�o de la poblaci�n
		ArrayList<Individuo> nuevaPoblacion = new ArrayList<Individuo>();	//Creamos un ArrayList para la nueva poblaci�n
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
		
		Individuo nuevoIndividuo = FactoriaIndividuo.getIndividuo(indSeleccionado.getId(), indSeleccionado.getPrecision() , indSeleccionado.getNumGenes());
		
		ArrayList cromoPadre1 = indSeleccionado.getCromosoma();
		ArrayList cromoHijo1 = new ArrayList();
		cromoHijo1.addAll(cromoPadre1);
		nuevoIndividuo.setCromosoma(cromoHijo1);
		
		nuevaPoblacion.add(nuevoIndividuo);
	}
}
