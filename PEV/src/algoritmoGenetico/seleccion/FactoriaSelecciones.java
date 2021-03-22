package algoritmoGenetico.seleccion;

public class FactoriaSelecciones {
	public static Seleccion getAlgoritmoDeSeleccion(String algoritmo, int participantes) {
		switch(algoritmo) {
			case "Metodo de la ruleta":
				return new SeleccionRuleta();
			case "Universal Estocastico":
				return new SeleccionEstocastico();
			case "Torneo Deterministico":
				return new SeleccionTorneo(participantes);
			case "Torneo Probabilistico":
				return new SeleccionTorneoProbabilistico(participantes);
			case "Truncamiento":
				return new SeleccionTruncamiento();
			case "Restos":
				return new SeleccionRestos();
			default:
				return new SeleccionRuleta();
		}
	}
}

