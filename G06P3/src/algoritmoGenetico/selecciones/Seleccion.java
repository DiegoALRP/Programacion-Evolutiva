package algoritmoGenetico.selecciones;

import java.util.ArrayList;

import algoritmoGenetico.individuos.Individuo;

public abstract class Seleccion {

	
	public abstract ArrayList<Individuo> seleccionar(ArrayList<Individuo> poblacion);

}
