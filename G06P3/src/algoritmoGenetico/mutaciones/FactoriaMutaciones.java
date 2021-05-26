package algoritmoGenetico.mutaciones;

/**
 * Universidad Complutense de Madrid.
 * Programación Evolutiva.
 * Grupo A 2021.
 * Profesor:
 * 	-Carlos Cervigon Ruckauer.
 * 
 * Práctica 3.
 * 
 * Clase Abstracta Mutacion.
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
			case "Terminal simple":
				return new MutacionTerminalSimple();
			case "Funcional simple":
				return new MutacionFuncionalSimple();
			case "Arbol":
				return new MutacionArbol();
			case "Permutacion":
				return new MutacionPermutacion();
			case "Contracción":
				return new MutacionContraccion();
			case "Expansion":
				return new MutacionExpansion();
			case "Hoist":
				return new MutacionHoist();
			case "Subarbol":
				return new MutacionSubarbol();
			default:
				return new MutacionTerminalSimple();
		}
	}
}
