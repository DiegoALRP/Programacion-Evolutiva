package algoritmoGenetico.individuos;

import java.util.Random;

/**
 * Universidad Complutense de Madrid.
 * Programación Evolutiva.
 * Grupo A 2021.
 * Profesor:
 * 	-Carlos Cervigon Ruckauer.
 * 
 * Práctica 3
 * 
 * Clase Operando.
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodríguez Pereira.
 *
 */
public class Operando {

	/******************************** ATRIBUTTES ********************************/
	private final String terminales[] = {"AVANZA", "DERECHA", "IZQUIERDA"};
	private final String funciones[] = {"SIComida", "PROGN2", "PROGN3"};
	
	private final int numTerminales = 3;
	private final int numFunciones = 3;
	
	private  String operando;
	
	
	/******************************* CONSTRUCTOR ********************************/
	public Operando(String operando) {
		
		this.operando = operando;
	}
	
	public Operando(boolean isTerminal) {
		
		Random rand = new Random();
		int index = rand.nextInt(numTerminales);
		if (isTerminal) {
			
			this.operando = terminales[index];
		}
		else {
			
			this.operando = funciones[index];
		}
	}
	
	/********************************* METHODS *********************************/
	public boolean isTerminal() {
		
		boolean isTerminal = false;
		for (int i = 0; i < numTerminales; i++) {
			
			if (operando.equals(terminales[i])) {
				isTerminal = true;
			}
		}
		return isTerminal;
	}
	
	public boolean isFunction() {
		
		boolean isFunction = false;
		for (int i = 0; i < numFunciones; i++) {
			
			if (operando.equals(funciones[i])) {
				isFunction = true;
			}
		}
		return isFunction;
	}
	
	public String randomTerminal() {
		
		Random rand = new Random();
		return terminales[rand.nextInt(numTerminales)];
	}
	
	public String randomFunction() {
		
		Random rand = new Random();
		return funciones[rand.nextInt(numFunciones)];
	}
	
	public boolean equalsAvanza() {
		
		return operando.equals(terminales[0]);
	}
	
	public boolean equalsDerecha() {
		
		return operando.equals(terminales[1]);
	}
	
	public boolean equalsIzquierda() {
		
		return operando.equals(terminales[2]);
	}
	
	public boolean equalsSiComida() {
		
		return operando.equals(funciones[0]);
	}
	
	public boolean equalsProgN2() {
		
		return operando.equals(funciones[1]);
	}
	
	public boolean equalsProgN3() {
		
		return operando.equals(funciones[2]);
	}
	
	
	/**************************** GETTERS & SETTERS ****************************/
	public String getOperando() {
		
		return this.operando;
	}
	
}
