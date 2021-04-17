package algoritmoGenetico.seleccion;

/**
 * Universidad Complutense de Madrid.
 * Programaci�n Evolutiva.
 * Grupo A 2021.
 * Profesor:
 * 	-Carlos Cervigon Ruckauer.
 * 
 * Clase Factoria Seleccion.
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodr�guez Pereira.
 *
 */

public class FactoriaSeleccion {

	/**
	 * [ES] Patr�n Factoria.
	 * [EN] Factory pattern.
	 * 
	 * @param algoritmo	[ES] Funci�n/Tipo de selecci�n.
	 * 					[EN] Selection's Function/Type.
	 * 
	 * @return	[ES] La instancia de la clase del tipo de selecci�n.
	 * 			[EN] The instance of the class's selection type.
	 */
	public static Seleccion getAlgoritmoDeSeleccion(String algoritmo, double b) {
		
		switch(algoritmo) {
		case "Metodo de la ruleta":
			return new SeleccionRuleta();
		case "Universal Estocastico":
			return new SeleccionEstocastico();
		case "Torneo Deterministico":
			return new SeleccionTorneo();
		case "Torneo Probabilistico":
			return new SeleccionTorneoProbabilistico();
		case "Truncamiento":
			return new SeleccionTruncamiento();
		case "Restos":
			return new SeleccionRestos();
		case "Ranking":
			return new SeleccionRanking(b);
		default:
			return new SeleccionRuleta();
	}
	}
}
