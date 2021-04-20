package algoritmoGenetico.seleccion;

import java.util.ArrayList;
import java.util.List;

import algoritmoGenetico.individuos.Individuo;

public class SeleccionRuleta extends Seleccion {

	private double[] puntAcumulada;		
	private double prob;
	private int pos_super;
	private double aptitudes;
	private double acum;
	
	@Override
	public ArrayList<Individuo> seleccionar(ArrayList<Individuo> poblacion) {
		
		inicializaAptitudes(poblacion);
		
		ArrayList<Individuo> nuevaPoblacion = new ArrayList<Individuo>(poblacion.size());
		Individuo nuevo;
		for(int i = 0; i < poblacion.size(); i++) {
			
			prob = Math.random();
			while(pos_super < poblacion.size() && prob > puntAcumulada[pos_super]) {
				pos_super++;
			}
			nuevo = poblacion.get(pos_super);
			addIndividuo(nuevaPoblacion, nuevo);
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
}
