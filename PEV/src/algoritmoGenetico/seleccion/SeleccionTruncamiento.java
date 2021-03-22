package algoritmoGenetico.seleccion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import algoritmoGenetico.individuos.FactoriaIndividuo;
import algoritmoGenetico.individuos.Individuo;

public class SeleccionTruncamiento extends Seleccion{

	private int trunc;
	private int num_copias;
	private int num_individuos;
	
	public SeleccionTruncamiento() {
		Random rand = new Random();
		trunc = rand.nextInt(4) + 1;
		seleccionaCopias();
	}

	@Override
	public List<Individuo> seleccionar(List<Individuo> poblacion) {
		
		ArrayList<Individuo> seleccionados = new ArrayList<Individuo>();
		Collections.sort(poblacion, new Comparator<Individuo>() {
			@Override
			public int compare(Individuo o1, Individuo o2) {
				return Double.compare(o2.getFitness(), o1.getFitness());
			}
			
		});
		for(int i = 0; i < num_copias; i++) {
			for(int j = 0; j < num_individuos; j++) {
				//seleccionados.add(poblacion.get(j));
				addIndividual(poblacion, seleccionados, j);
			}
		}
		
		if(seleccionados.size() < poblacion.size()) {		// añadir restantes si procede
			for(int i = 0; i < poblacion.size() - seleccionados.size(); i++) {
				//seleccionados.add(poblacion.get(i));
				addIndividual(poblacion, seleccionados, i);
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
