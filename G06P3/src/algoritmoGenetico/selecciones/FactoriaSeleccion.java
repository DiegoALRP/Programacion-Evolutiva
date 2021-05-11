package algoritmoGenetico.selecciones;

public class FactoriaSeleccion {

	
public static Seleccion getAlgoritmoDeSeleccion(String algoritmo) {
		
		switch(algoritmo) {
		case "Ruleta":
			return new SeleccionRuleta();
		case "Estocastico":
			return new SeleccionEstocastico();
		case "Torneo":
			return new SeleccionTorneo();
		case "Torneo Probabilistico":
			return new SeleccionTorneoProbabilistico();
		case "Truncamiento":
			return new SeleccionTruncamiento();
		case "Restos":
			return new SeleccionRestos();
		case "Ranking":
			return new SeleccionRanking();
		default:
			return new SeleccionRuleta();
	}
	}
}
