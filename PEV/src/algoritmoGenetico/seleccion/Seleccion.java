package algoritmoGenetico.seleccion;

import java.util.ArrayList;
import java.util.List;

import algoritmoGenetico.individuos.FactoriaIndividuo;
import algoritmoGenetico.individuos.Individuo;

/**
 * Universidad Complutense de Madrid.
 * Programaci�n Evolutiva.
 * Grupo A 2021.
 * Profesor:
 * 	-Carlos Cervigon Ruckauer.
 * 
 * Clase Abstracta Seleccion
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodr�guez Pereira.
 *
 */
public abstract class Seleccion {
	
	/**
	 * [ES]	Esta funci�n es la principal de la clase de Selecci�n.
	 * Esta funci�n selecciona a los individuos de una poblaci�n para generar
	 * una poblaci�n nueva/auxiliar con los individuos elegidos.
	 * Ambas poblaciones son del mismo tama�o.
	 * La manera en la que se seleccionan depende del tipo de algoritmo y de como
	 * este est� implementado.
	 * [EN] This is the main function of the Selection's class.
	 * This function selects the individuals of a population to generate a new/auxiliar
	 * population with the individuals chosen.
	 * Both population are of the same size.
	 * The way that the individuals are selected depends of the algorith and how it's
	 * implemented.
	 * 
	 * @param poblacion	[ES] Poblaci�n de la cual se van a seleccionar los individuos.
	 * 					[EN] Population form which it's going to be selected the individuals.
	 * 
	 * @return	[ES] La nueva poblaci�n con los individuos seleccionados.
	 * 			[EN] The new population with the selected individuals.
	 */
	public abstract List<Individuo> seleccionar(List<Individuo> poblacion);
	
	
	/**
	 * [ES] Copia el individuo seleccionado (por el �ndice) de la poblaci�n inicial
	 * a la nueva poblaci�n.
	 * La copia es por valor (no por referencia).
	 * [EN] Copies the selected individual (by index) from the initial population
	 * to the new population.
	 * The copy is by value (not by reference).
	 * 
	 * @param poblacion	[ES] Poblaci�n inicial.
	 * 					[EN] Initial population.
	 * @param nuevaPoblacion 	[ES] Nueva poblaci�n.
	 * 							[EN] New population.
	 * @param index	[ES] �ndice del individuo seleccionado.
	 * 				[EN] Index of the selected individual.
	 */
	public void addIndividuo(List<Individuo> poblacion, ArrayList<Individuo> nuevaPoblacion, int index) {
		
		Individuo indSeleccionado = poblacion.get(index);
		Individuo nuevoIndividuo = FactoriaIndividuo.getIndividuo(indSeleccionado.getId(), indSeleccionado.getPrecision(), indSeleccionado.getNumGenes());
	
		ArrayList cromoPadre1 = indSeleccionado.getCromosoma();
		ArrayList cromoHijo1 = new ArrayList();
		cromoHijo1.addAll(cromoPadre1);
		nuevoIndividuo.setCromosoma(cromoHijo1);
		
		nuevaPoblacion.add(nuevoIndividuo);
	}
}
