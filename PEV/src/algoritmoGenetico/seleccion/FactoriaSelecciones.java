package algoritmoGenetico.seleccion;

public class FactoriaSelecciones {
	public static Seleccion getAlgoritmoDeSeleccion(String algoritmo, int participantes) {
		switch(algoritmo) {
			case "Metodo de la ruleta":
				return new SeleccionRuleta();
			case "Universal Estocastico":
				return new SeleccionEstocastico();
			case "Torneo":
				return new SeleccionTorneo(participantes);
			case "Torneo Probabilistico":
				return new SeleccionTorneoProb(participantes);
			case "Ranking":
				return new SeleccionRanking();
			default:
				return new SeleccionRuleta();
		}
	}
}

