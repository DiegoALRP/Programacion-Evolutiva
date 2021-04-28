package algoritmoGenetico.seleccion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import algoritmoGenetico.individuos.Individuo;

/**
 * Universidad Complutense de Madrid.
 * Programación Evolutiva.
 * Grupo A 2021.
 * Profesor:
 * 	-Carlos Cervigon Ruckauer.
 * 
 * Clase Selección por Ranking.
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodríguez Pereira.
 *
 */
public class SeleccionRanking extends Seleccion{

	
	/**************************** ATRIBUTTES *******************************/
	private double beta;
	private double[] puntAcumulada;
	
	
	/**************************** CONSTRUCTOR *******************************/
	public SeleccionRanking() {
		beta = 2;
	}
	
	/***************************** METHODS ********************************/
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
			Individuo individuo = new Individuo(Padre.getTexto(), Padre.getNGrama());	
			individuo.setCromosoma(Padre.getCromosoma());
			
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
