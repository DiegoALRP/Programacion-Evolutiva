package algoritmoGenetico.mutaciones;

/**
 * Universidad Complutense de Madrid.
 * Programación Evolutiva.
 * Grupo A 2021.
 * Profesor:
 * 	-Carlos Cervigon Ruckauer.
 * 
 * Clase Factoria Mutación.
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodríguez Pereira.
 *
 */
public class FactoriaMutaciones {
	
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
		
			case "Mutacion Basica":
				return new MutacionBasica();
			case "Mutacion Uniforme":
				return new MutacionBasica();
			default:
				return new MutacionBasica();
		}
	}
}
