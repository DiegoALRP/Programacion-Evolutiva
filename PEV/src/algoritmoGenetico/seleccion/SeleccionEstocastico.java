package algoritmoGenetico.seleccion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import algoritmoGenetico.individuos.Individuo;

public class SeleccionEstocastico extends Seleccion{

	private double[] puntAcumulada;
	private double aptitudes;
	private double acum;

	@Override
	public List<Individuo> seleccionar(List<Individuo> poblacion) {
		
		inicializaAptitudes(poblacion);
		ArrayList<Individuo> seleccionados = new ArrayList<Individuo>(poblacion.size());
		
		Random r = new Random();
		double marca = r.nextInt(1/poblacion.size());
		double puntero;
		
		for(int i = 0; i < poblacion.size(); i++) {
			 puntero = (marca + i -1) / poblacion.size();
			 seleccionados.add(poblacion.get(getIndividuo(puntero)));
		}
		return seleccionados;
	}


	private int getIndividuo(double puntero) {
		int pivote = 0;
		while(puntAcumulada[pivote] < puntero) {
			pivote++;
		}	
		return pivote;
	}


	private void inicializaAptitudes(List<Individuo> poblacion) {
		
		aptitudes = 0.0;
		acum = 0.0;
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
