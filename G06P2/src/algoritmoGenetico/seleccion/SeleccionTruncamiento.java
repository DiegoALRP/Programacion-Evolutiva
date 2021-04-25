package algoritmoGenetico.seleccion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

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
	public ArrayList<Individuo> seleccionar(ArrayList<Individuo> poblacion) {
		ArrayList<Individuo> seleccionados = new ArrayList<Individuo>();
		Individuo nuevo;
		Collections.sort(poblacion, new Comparator<Individuo>() {
			@Override
			public int compare(Individuo o1, Individuo o2) {
				return Double.compare(o2.getFitness(), o1.getFitness());
			}
			
		});
		
		for(int i = 0; i < num_copias; i++) {
			for(int j = 0; j < num_individuos; j++) {
				nuevo = poblacion.get(i);
				addIndividuo(seleccionados, nuevo);
			}
		}
		
		if(seleccionados.size() < poblacion.size()) {		// añadir restantes si procede
			int sel = seleccionados.size();
			for(int i = 0; i < poblacion.size() - sel ; i++) {
				nuevo = poblacion.get(i);
				addIndividuo(seleccionados, nuevo);
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

}
