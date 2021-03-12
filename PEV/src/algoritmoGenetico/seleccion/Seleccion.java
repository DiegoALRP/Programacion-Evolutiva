package algoritmoGenetico.seleccion;

import java.util.ArrayList;
import java.util.List;

import algoritmoGenetico.individuos.Individuo;

public abstract class Seleccion {
	
	public abstract <T> List<Individuo<T>> seleccionar(List<Individuo<T>> poblacion);
}
