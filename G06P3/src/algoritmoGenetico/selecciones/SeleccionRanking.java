package algoritmoGenetico.selecciones;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import algoritmoGenetico.individuos.Individuo;

public class SeleccionRanking extends Seleccion{


	/**************************** ATRIBUTTES *******************************/
	private double beta;
	private double[] puntAcumulada;
	
	
	
	/**************************** CONSTRUCTOR *******************************/
	public SeleccionRanking() {
		beta = 2;
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
		
		
		ordenaPoblacionInicial(poblacion);
		inicializaRanking(poblacion);
		return seleccionRuleta(poblacion);
		
	}
	
	private ArrayList<Individuo> seleccionRuleta(ArrayList<Individuo> poblacion) {
		ArrayList<Individuo> nuevaPoblacion = new ArrayList<Individuo>(poblacion.size());


		for(int i = 0; i < poblacion.size(); i++) {
			
			double prob = Math.random();
			int pos_super = 0;
			while(pos_super < poblacion.size() && prob > puntAcumulada[pos_super]) {
				pos_super++;
			}
			
			Individuo Padre = poblacion.get(pos_super);
			Individuo individuo = new Individuo(Padre.getMetodoIni(), Padre.getMaxProf(), Padre.getMaxPasos(), Padre.getSantaFe());
			individuo.copyFenotipe(Padre.copyFenotipe());
			
			nuevaPoblacion.add(individuo);
			pos_super = 0;
		}
		
		return nuevaPoblacion;
	}
	
	private void ordenaPoblacionInicial(ArrayList<Individuo> poblacion) {
		Collections.sort(poblacion, new Comparator<Individuo>() {
			@Override
			public int compare(Individuo o1, Individuo o2) {
				return Double.compare(o2.getFitness(), o1.getFitness());
			}
			
		});
	}
	
	private void inicializaRanking(ArrayList<Individuo> poblacion) {
		
		double acum = 0.0;
		int k = poblacion.size();
		puntAcumulada = new double[k];
		
		for(int i = 0; i < poblacion.size(); i++) {
			double probOfIth = (double)i/k;
			probOfIth *= 2*(beta-1);
			probOfIth = beta - probOfIth;
			probOfIth = (double)probOfIth * ((double)1/k);
			
			poblacion.get(i).setFitnessRanking(probOfIth);
			acum += probOfIth;
			puntAcumulada[i] = acum;
		}
	}

	
	/**************************** GETTERS & SETTERS ********************************/

}
