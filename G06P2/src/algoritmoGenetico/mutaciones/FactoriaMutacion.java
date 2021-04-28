package algoritmoGenetico.mutaciones;

/**
 * Universidad Complutense de Madrid.
 * Programación Evolutiva.
 * Grupo A 2021.
 * Profesor:
 * 	-Carlos Cervigon Ruckauer.
 * 
 * Clase Factoria Mutacion.
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodríguez Pereira.
 *
 */

public class FactoriaMutacion {

	/**
	 * [ES] Patrón Factoria.
	 * [EN] Factory pattern.
	 * 
	 * @param algoritmo	[ES] Función/Tipo de mutación.
	 * 					[EN] Mutation's Function/Type.
	 * 
	 * @return	[ES] La instancia de la clase del tipo de mutacion.
	 * 			[EN] The instance of the class's mutation's type.
	 */
	public static Mutacion getAlgoritmoDeMutacion(String algoritmo) {
		
		switch(algoritmo) {
			case "Insercion":
				return new MutacionInsercion();
			case "Intercambio":
				return new MutacionIntercambio();
			case "Inversion":
				return new MutacionInversion();
			case "Heuristica":
				return new MutacionHeuristica();
			case "Intercambio doble":
				return new MutacionIntercambioDoble();
			case "Incremento":
				return new MutacionIncremento();
			default:
				return new MutacionIntercambio();
		}
	}
}
