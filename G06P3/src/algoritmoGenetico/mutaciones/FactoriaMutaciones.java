package algoritmoGenetico.mutaciones;

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
			default:
				return new MutacionTerminalSimple();
		}
	}
}
