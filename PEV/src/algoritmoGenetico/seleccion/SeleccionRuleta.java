package algoritmoGenetico.seleccion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
		
		ArrayList<Individuo> seleccionados = new ArrayList<Individuo>(poblacion.size());
		
		for(int i = 0; i < poblacion.size(); i++) {
			
			prob = Math.random();
			while(pos_super < poblacion.size() && prob > puntAcumulada[pos_super]) {
				pos_super++;
			}
			seleccionados.add(poblacion.get(pos_super));
			System.out.println(poblacion.get(pos_super).getFitness());
			pos_super = 0;
		}
		return seleccionados;
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

}
