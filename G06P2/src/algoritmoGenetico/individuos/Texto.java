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
	protected int puntoMedio;
	
	/**************************** CONSTRUCTOR *******************************/
	
	public Texto(StringBuilder textoOriginal, StringBuilder textoAyuda) {
		
		this.textoOriginal = textoOriginal;
		this.textoAyuda = textoAyuda;
		this.calculaPuntoMedio();
	}
	/***************************** METHODS ********************************/
	/**************************** GET & SET ********************************/
	public StringBuilder getTextoOriginal() {
		
		return this.textoOriginal;
	}
	public StringBuilder getTextoAyuda() {
		
		return this.textoAyuda;
	}
	
	private void calculaPuntoMedio() {
		
		int i = (this.textoAyuda.length()/2);
		boolean encontrado = false;
		while (i < this.textoAyuda.length() && !encontrado) {
			
			int caracter = (int) Character.toLowerCase(textoAyuda.charAt(i));
			if (caracter < 97 || caracter > 122) {
				
				this.puntoMedio = i + 1;
				//System.out.println("Punto Medio: " + this.puntoMedio);
				encontrado = true;
			}
			i++;
		}
	}
}
