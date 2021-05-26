package algoritmoGenetico.mutaciones;

import java.util.Random;

import algoritmoGenetico.individuos.Individuo;
import algoritmoGenetico.individuos.Operando;

/**
 * Universidad Complutense de Madrid.
 * Programaci�n Evolutiva.
 * Grupo A 2021.
 * Profesor:
 * 	-Carlos Cervigon Ruckauer.
 * 
 * Pr�ctica 3
 * 
 * Clase Mutaci�n Funcional Simple.
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodr�guez Pereira.
 *
 */
public class MutacionFuncionalSimple extends Mutacion{

	
	/******************************** ATRIBUTTES ********************************/
	
	/******************************* CONSTRUCTOR ********************************/
	
	/********************************* METHODS *********************************/
	@Override
	public void mutaIndividuo(Individuo individuo) {
		
		individuo.mutaFuncionSimple(this.probMutacion);
		
		//Nota:
		/*
		 * Al igual que para mutaci�n terminal simple, 
		 * para este m�todo hemos decidido encargar la mutaci�n a la clase �rbol.
		 * Para que de esta manera esa clase tenga control total de los operadores.
		 * Y b�sicamente lo que hace es buscar un nodo funci�n con probabilidad
		 * igual a la probabilidad de mutaci�n para seleccionar uno de los nodos 
		 * y lo cambia por otro operador distinto. 
		 * Hay que tener en cuenta que este cambio se tiene que generar entre operadores
		 * de la misma anidad
		 * 
		 * Dejamos a continuaci�n la parte del c�digo que realiza la clase �rbol
		 * 
		 */
		
		/*public void mutaFuncionSimple(double probMutacion, boolean up) {
			
			if (this.raiz.isTerminal()) {
				this.padre.mutaFuncionSimple(probMutacion, true);
			}
			else {
				if (up) {
					boolean encontrado = false;
					for (int i = 0; i < numHijos && !encontrado; i++) {
						if (this.raiz.equalsProgN2()) {
							this.raiz = new Operando("SIComida");
							encontrado = true;
						}
						else if (this.raiz.equalsSiComida()) {
							this.raiz = new Operando("PROGN2");
							encontrado = true;
						}
					}
					if (!encontrado && this.padre != null) {
						this.padre.mutaFuncionSimple(probMutacion, true);
					}
				}
				else {
					Random rand = new Random();
					if (this.raiz.equalsProgN3() || (rand.nextDouble() > probMutacion)) {
						this.hijos.get(rand.nextInt(numHijos)).mutaFuncionSimple(probMutacion, up);
					}
					else {
						if (this.raiz.equalsProgN2()) {
							this.raiz = new Operando("SIComida");
						}
						else if (this.raiz.equalsSiComida()) {	
							this.raiz = new Operando("PROGN2");
						}
					}
				}
			}
		}*/
	}

	/***************************** AUXILIARY METHODS *****************************/
	
	/**************************** GETTERS & SETTERS ****************************/
}
