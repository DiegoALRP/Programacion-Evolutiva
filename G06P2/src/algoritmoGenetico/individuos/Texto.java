package algoritmoGenetico.individuos;

/**
 * Universidad Complutense de Madrid.
 * Programación Evolutiva.
 * Grupo A 2021.
 * Profesor:
 * 	-Carlos Cervigon Ruckauer.
 * 
 * Clase Texto.
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodríguez Pereira.
 *
 */
public class Texto {

	/**************************** ATRIBUTTES *******************************/
	protected StringBuilder textoOriginal;
	protected StringBuilder textoAyuda;
	
	/**************************** CONSTRUCTOR *******************************/
	
	public Texto(StringBuilder textoOriginal, StringBuilder textoAyuda) {
		
		this.textoOriginal = textoOriginal;
		this.textoAyuda = textoAyuda;
	}
	/***************************** METHODS ********************************/
	/**************************** GET & SET ********************************/
	public StringBuilder getTextoOriginal() {
		
		return this.textoOriginal;
	}
	public StringBuilder getTextoAyuda() {
		
		return this.textoAyuda;
	}
}
