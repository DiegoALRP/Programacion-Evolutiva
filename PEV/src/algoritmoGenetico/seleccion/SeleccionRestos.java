package algoritmoGenetico.seleccion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import algoritmoGenetico.individuos.FactoriaIndividuo;
import algoritmoGenetico.individuos.Individuo;

public class SeleccionRestos extends Seleccion{

	private double[] puntAcumulada;
	private double[] pik;
	private double aptitudes;
	public SeleccionRestos() {
		// TODO Auto-generated constructor stub
	}
	
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
				//seleccionados.add(poblacion.get(i));
				addIndividual(poblacion, seleccionados, i);
				aux++;
			}
		}
		//	Seleccionamos el resto por estocastico universal
		for(int i = 0; i < poblacion.size() - aux; i++) {
			 puntero = (marca + i -1) / poblacion.size();
			 //seleccionados.add(poblacion.get(getIndividuo(puntero)));
			 addIndividual(poblacion, seleccionados, getIndividuo(puntero));
			 //aux++;
		}
		return seleccionados;
	}


	private int getIndividuo(double puntero) {
		int pivote = 0;
		while(pivote < puntAcumulada.length && puntAcumulada[pivote] < puntero) {
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
	
	/**
	 * Copia el individuo seleccionado (por el índice) de la poblacion inicial
	 * a la nueva poblacion.
	 * La copia es por valor
	 * 
	 * @param poblacion poblacion inicial
	 * @param nuevaPoblacion nueva poblacion
	 * @param index índice del individuo seleccionado
	 */
	public void addIndividual(List<Individuo> poblacion, ArrayList<Individuo> nuevaPoblacion, int index) {
		
		Individuo indSeleccionado = poblacion.get(index);
		Individuo nuevoIndividuo = FactoriaIndividuo.getIndividuo(indSeleccionado.getId(), indSeleccionado.getPrecision());
	
		ArrayList cromoPadre1 = indSeleccionado.getCromosoma();
		ArrayList cromoHijo1 = new ArrayList();
		cromoHijo1.addAll(cromoPadre1);
		nuevoIndividuo.setCromosoma(cromoHijo1);
		
		nuevaPoblacion.add(nuevoIndividuo);
	}
}
