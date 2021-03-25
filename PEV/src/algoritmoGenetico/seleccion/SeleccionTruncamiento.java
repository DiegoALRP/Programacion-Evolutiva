package algoritmoGenetico.seleccion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
public class SeleccionTruncamiento extends Seleccion{

	private int trunc;
	private int num_copias;
	private int num_individuos;
	
	
	public SeleccionTruncamiento() {
		
		Random rand = new Random();
		trunc = rand.nextInt(4) + 1;
		seleccionaCopias();
	}

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
		
		ArrayList<Individuo> seleccionados = new ArrayList<Individuo>();
		Collections.sort(poblacion, new Comparator<Individuo>() {
			@Override
			public int compare(Individuo o1, Individuo o2) {
				return Double.compare(o2.getFitnessDesplazado(), o1.getFitnessDesplazado());
			}
			
		});
		
		for(int i = 0; i < num_copias; i++) {
			for(int j = 0; j < num_individuos; j++) {
				
				addIndividuo(poblacion, seleccionados, j);
			}
		}
		
		if(seleccionados.size() < poblacion.size()) {		// a�adir restantes si procede
			for(int i = 0; i < poblacion.size() - seleccionados.size(); i++) {
				
				addIndividuo(poblacion, seleccionados, i);
			}
		}
		
		return seleccionados;
	}

	
	private void seleccionaCopias() {
		switch(trunc) {
		case 1: num_copias = 10; num_individuos = 10;break;
		case 2: num_copias = 4; num_individuos = 20;break;
		case 3: num_copias = 3; num_individuos = 30;break;
		case 4: num_copias = 2; num_individuos = 40;break;
		case 5: num_copias = 2; num_individuos = 50;break;
		}
		
	}
}
