package algoritmoGenetico.selecciones;

import java.util.ArrayList;

import algoritmoGenetico.individuos.Individuo;

public abstract class Seleccion {

	
	public abstract ArrayList<Individuo> seleccionar(ArrayList<Individuo> poblacion);

	
	public void addIndividuo(ArrayList<Individuo> nuevaPoblacion, Individuo individuo) {
		
		Individuo nuevoIndividuo = new Individuo(individuo.getInicializacion(), individuo.getMaxProf(), individuo.getComida());
		nuevaPoblacion.add(nuevoIndividuo);
	}
	
}
