package algoritmoGenetico.seleccion;

import java.util.ArrayList;
import java.util.List;

import algoritmoGenetico.individuos.Individuo;

public abstract class Seleccion {
	
	public abstract List<Individuo> seleccionar(List<Individuo> poblacion);
}
