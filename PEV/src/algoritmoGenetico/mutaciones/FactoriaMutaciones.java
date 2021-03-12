package algoritmoGenetico.mutaciones;

import algoritmoGenetico.cruces.Cruce;
import algoritmoGenetico.cruces.CruceMonopunto;

public class FactoriaMutaciones {
	public static Mutacion getAlgoritmoDeMutacion(String algoritmo, int participantes) {
		switch(algoritmo) {
			case "Mutacion Uniforme":
				return new MutacionBasica();
			default:
				return new MutacionBasica();
		}
	}
}
