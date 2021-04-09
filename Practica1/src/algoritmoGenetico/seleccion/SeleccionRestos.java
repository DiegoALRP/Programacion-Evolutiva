package algoritmoGenetico.seleccion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import algoritmoGenetico.individuos.FactoriaIndividuo;
import algoritmoGenetico.individuos.Individuo;

/**
 * Universidad Complutense de Madrid.
 * Programación Evolutiva.
 * Grupo A 2021.
 * Profesor:
 * 	-Carlos Cervigon Ruckauer.
 * 
 * Clase Seleccion por Restos.
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodríguez Pereira.
 *
 */
public class SeleccionRestos extends Seleccion{

	private double[] puntAcumulada;
	private double[] pik;
	private double aptitudes;
	
	
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
	public List<Individuo> seleccionar(List<Individuo> poblacion) {
		
		ArrayList<Individuo> seleccionados = new ArrayList<Individuo>(poblacion.size());
		Random r = new Random();
		double marca = (1/poblacion.size()) * r.nextDouble();
		double puntero;
		int aux = 0;
		inicializaAptitudes(poblacion);
		
		for(int i = 0; i < pik.length; i++) {
			
			if(pik[i] > 1) {

				addIndividuo(poblacion, seleccionados, i);
				aux++;
			}
		}
		
		//	Seleccionamos el resto por estocastico universal
		for(int i = 0; i < poblacion.size() - aux; i++) {
			
			 puntero = (marca + i -1) / poblacion.size();
			 addIndividuo(poblacion, seleccionados, getIndividuo(puntero));
		}
		return seleccionados;
	}


	private int getIndividuo(double puntero) {
		
		int pivote = 0;
		while(pivote < puntAcumulada.length - 1 && puntAcumulada[pivote] < puntero) {
			pivote++;
		}	
		return pivote;
	}
	
	private void inicializaAptitudes(List<Individuo> poblacion) {
		
		puntAcumulada = new double[poblacion.size()];
		pik = new double[poblacion.size()];
		
		for(Individuo ind : poblacion) {
			aptitudes += ind.getFitnessDesplazado();
		}
		
		for(int i = 0; i < poblacion.size(); i++) {
			double fitness = poblacion.get(i).getFitnessDesplazado() / aptitudes;
			puntAcumulada[i] = (i == 0)? fitness : puntAcumulada[i-1] + fitness;
			pik[i] = fitness * poblacion.size();
		}
	}
}
