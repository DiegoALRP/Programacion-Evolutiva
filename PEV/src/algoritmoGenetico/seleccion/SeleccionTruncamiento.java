package algoritmoGenetico.seleccion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


import algoritmoGenetico.individuos.Individuo;

public class SeleccionTruncamiento extends Seleccion{

	private double trunc;
	private int num_copias;
	private int num_individuos;
	
	public SeleccionTruncamiento(double umbral) {
		trunc = umbral;
		seleccionaCopias();
	}

	@Override
	public List<Individuo> seleccionar(List<Individuo> poblacion) {
		
		List<Individuo> seleccionados = new ArrayList<Individuo>();
		Collections.sort(poblacion, new Comparator<Individuo>() {
			@Override
			public int compare(Individuo o1, Individuo o2) {
				return Double.compare(o1.getFitness(), o2.getFitness());
			}
			
		});
		for(int i = 0; i < num_copias; i++) {
			for(int j = 0; j < num_individuos; j++) {
				seleccionados.add(poblacion.get(j));
			}
		}
		
		if(seleccionados.size() < poblacion.size()) {		// añadir restantes si procede
			for(int i = 0; i < poblacion.size() - seleccionados.size(); i++) {
				seleccionados.add(poblacion.get(i));
			}
		}
		
		return seleccionados;
	}

	
	private void seleccionaCopias() {
		switch((int) trunc* 10) {
		case 1: num_copias = 10; num_individuos = 10;break;
		case 2: num_copias = 4; num_individuos = 20;break;
		case 3: num_copias = 3; num_individuos = 30;break;
		case 4: num_copias = 2; num_individuos = 40;break;
		case 5: num_copias = 2; num_individuos = 50;break;
		}
		
	}
}
