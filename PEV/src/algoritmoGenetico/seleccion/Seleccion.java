package algoritmoGenetico.seleccion;

import java.util.ArrayList;

import algoritmoGenetico.individuos.Individuo;

public abstract class Seleccion {
	
	protected abstract ArrayList<Individuo> seleccionar(ArrayList<Individuo> individuos);
}
