package algoritmoGenetico.individuos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Universidad Complutense de Madrid.
 * Programación Evolutiva.
 * Grupo A 2021.
 * Profesor:
 * 	-Carlos Cervigon Ruckauer.
 * 
 * Clase Individuo.
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodríguez Pereira.
 *
 */

public class Individuo {

	/**************************** ATRIBUTTES *******************************/
	protected ArrayList<Integer> cromosoma;
	protected HashMap<Character, Character> claveDescifrado; //HashMap que tiene como clave las letras a-z del texto cifrado y como valor las letras a-z del texto descifrado
	protected ArrayList<Character> fenotipo;
	protected char[] textoOriginal;
	protected int tamTexto;
	protected HashMap<String, Integer> frecuenciaMonograma;
	protected HashMap<String, Integer> frecuenciaBigrama;
	protected HashMap<String, Integer> frecuenciaTrigrama;
	protected HashMap<String, Integer> frecuenciaCuatrigrama;
	protected HashMap<String, Integer> frecuenciaQuintagrama;
	protected double fitness;
	
	/************************** CONSTANT ATRIBUTTES *****************************/
	protected final int tam = 26;
	protected final int initLetter = 97;
	
	/**************************** CONSTRUCTOR *******************************/
	public Individuo(String textoOriginal) {
		
		this.tamTexto = textoOriginal.length();
		this.cromosoma = new ArrayList<Integer>(tam);
		this.initializeCromosome();
		this.claveDescifrado = new HashMap<Character, Character>(tam);
		this.fenotipo = new ArrayList<Character>(textoOriginal.length());
		this.textoOriginal = textoOriginal.toCharArray();
		this.frecuenciaMonograma = new HashMap<String, Integer>(tam);
		this.frecuenciaBigrama = new HashMap<String, Integer>(tam);
		this.frecuenciaTrigrama = new HashMap<String, Integer>(tam);
		this.frecuenciaCuatrigrama = new HashMap<String, Integer>(tam);
		this.frecuenciaQuintagrama = new HashMap<String, Integer>(tam);
		
		this.calculateFenotipe();
	}
	/***************************** METHODS ********************************/
	
	private void initializeCromosome() {
		
		/*this.cromosoma.add(19);this.cromosoma.add(7);this.cromosoma.add(15);this.cromosoma.add(1);
		this.cromosoma.add(17);this.cromosoma.add(12);this.cromosoma.add(21);this.cromosoma.add(13);
		this.cromosoma.add(5);this.cromosoma.add(8);this.cromosoma.add(24);this.cromosoma.add(23);
		this.cromosoma.add(9);this.cromosoma.add(22);this.cromosoma.add(16);this.cromosoma.add(25);
		this.cromosoma.add(18);this.cromosoma.add(3);this.cromosoma.add(20);this.cromosoma.add(4);
		this.cromosoma.add(14);this.cromosoma.add(11);this.cromosoma.add(6);this.cromosoma.add(0);
		this.cromosoma.add(10);this.cromosoma.add(2);*/
		Set<Integer> numSeleccionados = new HashSet<Integer>(this.tam);
		Random rand = new Random();
		
		for (int i = 0; i < this.tam; i++) {
			
			int num = rand.nextInt(this.tam);
			while (numSeleccionados.contains(num)) {
				num = rand.nextInt(this.tam);
			}
			this.cromosoma.add(num);
			numSeleccionados.add(num);
		}
	}
	
	private double calculateFitness() {
		
		return this.fitness;
	}
	
	private void calculateFenotipe() {
		
		for (int i = 0; i < this.tam; i++) {
			
			this.claveDescifrado.put((char) (this.cromosoma.get(i) + this.initLetter), (char) (i + this.initLetter));
		}
		
		for (int i = 0; i < this.tamTexto; i++) {
			
			char caracter = this.textoOriginal[i];
			
			if (Character.isLowerCase(caracter)) {
				
				if (this.claveDescifrado.containsKey(caracter)) {
					this.fenotipo.add(this.claveDescifrado.get(caracter));
				}
				else {
					this.fenotipo.add(caracter);
				}
			}
			else {
				
				caracter = Character.toLowerCase(caracter);
				if (this.claveDescifrado.containsKey(caracter)) {
					this.fenotipo.add(Character.toUpperCase(this.claveDescifrado.get(caracter)));
				}
				else {
					this.fenotipo.add(Character.toUpperCase(caracter));
				}
			}
		}
	}
	/**************************** GET & SET ********************************/
	public String getFenotipe() {
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < this.tamTexto; i++) {
			
			sb.append(this.fenotipo.get(i));
		}
		
		System.out.println(this.cromosoma);
		System.out.println(this.claveDescifrado);
		return sb.toString();
	}
}
