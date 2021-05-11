package algoritmoGenetico.seleccion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

import algoritmoGenetico.individuos.Individuo;


/**
 * Universidad Complutense de Madrid.
 * Programación Evolutiva.
 * Grupo A 2021.
 * Profesor:
 * 	-Carlos Cervigon Ruckauer.
 * 
 * Clase Selección por Truncamiento.
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodríguez Pereira.
 *
 */
public class SeleccionTruncamiento extends Seleccion{

	/**************************** ATRIBUTTES *******************************/
	private int trunc;
	private int num_copias;
	private int num_individuos;
	
	
	
	/**************************** CONSTRUCTOR *******************************/
	public SeleccionTruncamiento() {
		
		Random rand = new Random();
		trunc = rand.nextInt(4) + 1;
		seleccionaCopias();
	}
	
	
	
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
		ArrayList<Individuo> seleccionados = new ArrayList<Individuo>();
		Individuo nuevo;
		Collections.sort(poblacion, new Comparator<Individuo>() {
			@Override
			public int compare(Individuo o1, Individuo o2) {
				return Double.compare(o2.getFitness(), o1.getFitness());
			}
			
		});
		
		for(int i = 0; i < num_copias; i++) {
			for(int j = 0; j < num_individuos; j++) {
				nuevo = poblacion.get(i);
				addIndividuo(seleccionados, nuevo);
			}
		}
		
		if(seleccionados.size() < poblacion.size()) {		// añadir restantes si procede
			int sel = seleccionados.size();
			for(int i = 0; i < poblacion.size() - sel ; i++) {
				nuevo = poblacion.get(i);
				addIndividuo(seleccionados, nuevo);
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

	
	/**************************** GETTERS & SETTERS ********************************/
}
