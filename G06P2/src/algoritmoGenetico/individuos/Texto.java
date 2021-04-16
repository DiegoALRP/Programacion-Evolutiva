package algoritmoGenetico.individuos;

/**
 * Universidad Complutense de Madrid.
 * Programaci�n Evolutiva.
 * Grupo A 2021.
 * Profesor:
 * 	-Carlos Cervigon Ruckauer.
 * 
 * Clase Texto.
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodr�guez Pereira.
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
