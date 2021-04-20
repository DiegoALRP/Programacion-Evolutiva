package algoritmoGenetico.mutaciones;

import algoritmoGenetico.seleccion.SeleccionEstocastico;
import algoritmoGenetico.seleccion.SeleccionRanking;
import algoritmoGenetico.seleccion.SeleccionRestos;
import algoritmoGenetico.seleccion.SeleccionRuleta;
import algoritmoGenetico.seleccion.SeleccionTorneo;
import algoritmoGenetico.seleccion.SeleccionTorneoProbabilistico;
import algoritmoGenetico.seleccion.SeleccionTruncamiento;

/**
 * Universidad Complutense de Madrid.
 * Programaci�n Evolutiva.
 * Grupo A 2021.
 * Profesor:
 * 	-Carlos Cervigon Ruckauer.
 * 
 * Clase Factoria Mutacion.
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodr�guez Pereira.
 *
 */

public class FactoriaMutacion {

	/**
	 * [ES] Patr�n Factoria.
	 * [EN] Factory pattern.
	 * 
	 * @param algoritmo	[ES] Funci�n/Tipo de mutaci�n.
	 * 					[EN] Mutation's Function/Type.
	 * 
	 * @return	[ES] La instancia de la clase del tipo de mutacion.
	 * 			[EN] The instance of the class's mutation's type.
	 */
	public static Mutacion getAlgoritmoDeMutacion(String algoritmo) {
		
		switch(algoritmo) {
			case "Insercion":
				return new MutacionInsersion();
			case "Intercambio":
				return new MutacionIntercambio();
			case "Inversion":
				return new MutacionInversion();
			case "Heuristica":
				return new MutacionHeuristica();
			default:
				return new MutacionInsersion();
		}
	}
}
