package algoritmoGenetico.seleccion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import algoritmoGenetico.individuos.FactoriaIndividuo;
import algoritmoGenetico.individuos.Individuo;

public class SeleccionRuleta extends Seleccion {
	
	private double[] puntAcumulada;		//Puntuacion Acumulada
	private double prob;
	private int pos_super;
	private double aptitudes;
	private double acum;
	
	@Override
	public List<Individuo> seleccionar(List<Individuo> poblacion) {
		
		inicializaAptitudes(poblacion);
		
		ArrayList<Individuo> nuevaPoblacion = new ArrayList<Individuo>(poblacion.size());
		
		for(int i = 0; i < poblacion.size(); i++) {
			
			prob = Math.random();
			while(pos_super < poblacion.size() && prob > puntAcumulada[pos_super]) {
				pos_super++;
			}
			
			addIndividual(poblacion, nuevaPoblacion);
			//System.out.println("Individuo: " + poblacion.get(pos_super).printCromosoma() + " Fitness: " + poblacion.get(pos_super).getFitness());
			pos_super = 0;
		}
		
		return nuevaPoblacion;
	}
	
	public void inicializaAptitudes(List<Individuo> poblacion) {
		
		aptitudes = 0.0;
		acum = 0.0;
		pos_super = 0;
		puntAcumulada = new double[poblacion.size()];
		
		for(Individuo ind : poblacion) {
			aptitudes += ind.getFitness();
		}
		
		for(int i = 0; i < poblacion.size(); i++) {
			acum += poblacion.get(i).getFitness() / aptitudes;
			puntAcumulada[i] = acum;
		}
	}
	
	/**
	 * Copia el individuo seleccionado (por el índice) de la poblacion inicial
	 * a la nueva poblacion.
	 * La copia es por valor
	 * 
	 * @param poblacion poblacion inicial
	 * @param nuevaPoblacion nueva poblacion
	 * @param index índice del individuo seleccionado
	 */
	public void addIndividual(List<Individuo> poblacion, ArrayList<Individuo> nuevaPoblacion) {
		
		Individuo indSeleccionado = poblacion.get(pos_super);
		Individuo nuevoIndividuo = FactoriaIndividuo.getIndividuo(indSeleccionado.getId());
	
		ArrayList cromoPadre1 = indSeleccionado.getCromosoma();
		ArrayList cromoHijo1 = new ArrayList();
		cromoHijo1.addAll(cromoPadre1);
		nuevoIndividuo.setCromosoma(cromoHijo1);
		
		nuevaPoblacion.add(nuevoIndividuo);
	}
}
