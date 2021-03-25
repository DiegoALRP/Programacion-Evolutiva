package algoritmoGenetico.individuos;

/**
 * Universidad Complutense de Madrid.
 * Programaci�n Evolutiva.
 * Grupo A 2021.
 * Profesor:
 * 	-Carlos Cervigon Ruckauer.
 * 
 * Clase Factoria Individuo.
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodr�guez Pereira.
 *
 */
public class FactoriaIndividuo {

	/**
	 * [ES] Patr�n Factoria.
	 * [EN] Factory pattern.
	 * 
	 * @param id	[ES] Funci�n/Tipo del Individuo.
	 * 				[EN] Individual's Function/Type.
	 * @param precision	[ES] Valor de precisi�n.
	 * 					[EN] Precision's value. 
	 * @param numN	[ES] N�mero de genes.
	 * 				[EN] Gen's number.
	 * @return	[ES] La instancia de la clase del tipo de individuo.
	 * 			[EN] The instance of the class's individual's type.
	 */
	public static Individuo<?> getIndividuo(String id, double precision, int numN) {
		
		switch(id) {
			
			case "Funcion 1": 
				return new IndividuoFuncion1(precision);
	        case "Funcion Schubert": 
	        	return new IndividuoFuncion2(precision);
	        case "Funcion Holder table": 
	        	return new IndividuoFuncion3(precision);
	        case "Funcion Michalewicz (Booleanos)": 
	        	return new IndividuoFuncion4(numN, precision);
	        case "Funcion Michalewicz (Reales)": 
	        	return new IndividuoFuncion4Double(numN, precision);
	        case "Funcion Bukin" : 
	        	return new IndividuoFuncionBukin();
	        case "Funcion Matyas" : 
	        	return new IndividuoFuncionMatyas();
	        default: 
	        	return new IndividuoFuncion1(precision);
		}
	}
}
