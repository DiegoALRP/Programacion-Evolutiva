package algoritmoGenetico.cruces;

public class FactoriaCruces {
	
	/**
	 * [ES] Patrón Factoria.
	 * [EN] Factory pattern.
	 * 
	 * @param algoritmo	[ES] Tipo de cruce.
	 * 					[EN] Crossover's type.
	 * @return 	[ES] La instancia de la clase del tipo de cruce.
	 * 			[EN] The instance of the class's crossover's type.
	 */
	public static Cruce getAlgoritmoDeCruce(String algoritmo) {
		switch(algoritmo) {
			case "Monopunto":
				return new CruceMonopunto();
			case "Discreto Uniforme":
				return new CruceUniforme();
			case "Aritmetico":
				return new CruceAritmetico();
			case "BLX-alpha":
				return new CruceBLX();
			default:
				return new CruceMonopunto();
		}
	}
}
