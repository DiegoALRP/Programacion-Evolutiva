package algoritmoGenetico.cruces;

/**
 * Universidad Complutense de Madrid.
 * Programación Evolutiva.
 * Grupo A 2021.
 * Profesor:
 * 	-Carlos Cervigon Ruckauer.
 * 
 * Práctica 2
 * 
 * Clase Factoria Cruces.
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodríguez Pereira.
 *
 */

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
			case "PMX":
				return new CrucePMX();
			case "OX":
				return new CrucePorOrden();
			case "CX":
				return new CruceCX();
			case "ERX":
				return new CruceERX();
			case "CO":
				return new CruceCO();
			case "OXPP":
				return new CruceOXPP();
			default:
				return new CrucePMX();
			}
	}
}
