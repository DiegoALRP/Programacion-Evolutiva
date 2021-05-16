package algoritmoGenetico.selecciones;

import java.util.ArrayList;

import algoritmoGenetico.individuos.Individuo;

public abstract class Seleccion {

	
	public abstract ArrayList<Individuo> seleccionar(ArrayList<Individuo> poblacion);

	
	public void addIndividuo(ArrayList<Individuo> nuevaPoblacion, Individuo individuo) {
		
		//Individuo nuevoIndividuo = new Individuo(individuo.copyFenotipe(), individuo.getMaxProf(), individuo.getComida());
		Individuo nuevoIndividuo = new Individuo(individuo.copyFenotipe(), individuo.getMetodoIni(), individuo.getMaxProf(), individuo.getMaxPasos(), individuo.getSantaFe());
		nuevaPoblacion.add(nuevoIndividuo);
	}
	
}
