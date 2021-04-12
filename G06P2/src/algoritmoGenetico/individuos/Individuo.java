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
	
	protected StringBuilder textoOriginal; 
	//protected char[] arrayTextoOriginal;
	//protected ArrayList<Character> textOriginalSinEsp;
	protected int tamTexto;
	protected StringBuilder textoAyuda;
	//protected char[] arrayTextoAyuda;
	//protected ArrayList<Character> textoAyudaSinEsp;
	protected int tamTextoAyuda;
	
	/*protected HashMap<String, Integer> frecuenciaMonograma;
	protected HashMap<String, Integer> frecuenciaBigrama;
	protected HashMap<String, Integer> frecuenciaTrigrama;
	protected HashMap<String, Integer> frecuenciaCuatrigrama;
	protected HashMap<String, Integer> frecuenciaQuintagrama;*/
	protected double fitness;
	
	protected NGramas ngramas;
	
	/************************** CONSTANT ATRIBUTTES *****************************/
	protected final int tam = 26;
	protected final int initLetter = 65;
	
	/**************************** CONSTRUCTOR *******************************/
	public Individuo(String textoOriginal, String textoAyuda, NGramas ngramas) {
		
		//this.tamTexto = textoOriginal.length();
		//this.tamTextoAyuda = textoAyuda.length();
		this.cromosoma = new ArrayList<Integer>(tam);
		this.initializeCromosome();
		
		this.claveDescifrado = new HashMap<Character, Character>(tam);
		this.fenotipo = new ArrayList<Character>(textoOriginal.length());
		
		this.textoOriginal = new StringBuilder(textoOriginal);
		this.textoAyuda = new StringBuilder(textoAyuda);
		this.tamTexto = textoOriginal.length();
		this.tamTextoAyuda = textoAyuda.length();
		//this.convertTextToArray();
		
		/*this.frecuenciaMonograma = new HashMap<String, Integer>(tam);
		this.frecuenciaBigrama = new HashMap<String, Integer>(tam);
		this.frecuenciaTrigrama = new HashMap<String, Integer>(tam);
		this.frecuenciaCuatrigrama = new HashMap<String, Integer>(tam);
		this.frecuenciaQuintagrama = new HashMap<String, Integer>(tam);*/
		
		this.calculateFenotipe();
		
		this.ngramas = ngramas;
		
		this.calculateFitness();
		
		//System.out.println("Fitness: " + this.fitness/(Math.log(this.tamTextoAyuda)));
		System.out.println("Fitness: " + this.fitness);
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
	
	/*private void convertTextToArray() {
		
		this.arrayTextoOriginal = new char[tamTexto];
		this.arrayTextoAyuda = new char[tamTextoAyuda];
		this.textOriginalSinEsp = new ArrayList<Character>(tamTexto);
		this.textoAyudaSinEsp = new ArrayList<Character>(tamTextoAyuda);
		
		for (int i = 0; i < tamTexto; i++) {
			
			char caracter = Character.toUpperCase(textoOriginal.charAt(i));
			char caracterAyuda = Character.toUpperCase(textoAyuda.charAt(i));
			int asciiChar = (int) caracter;
			int asciiCharAyuda = (int) caracterAyuda;
			
			this.arrayTextoOriginal[i] = caracter;
			this.arrayTextoAyuda[i] = caracterAyuda;
			
			if (this.isAZ(asciiChar)) {
				
				this.textOriginalSinEsp.add(caracter);
			}
			
			if (this.isAZ(asciiCharAyuda)) {
				
				this.textoAyudaSinEsp.add(caracterAyuda);
			}	
		}
		for (int i = tamTexto; i < tamTextoAyuda; i++) {
			
			char caracterAyuda = Character.toUpperCase(textoAyuda.charAt(i));
			int asciiCharAyuda = (int) caracterAyuda;
			
			this.arrayTextoAyuda[i] = caracterAyuda;
			
			if (this.isAZ(asciiCharAyuda)) {
				
				this.textoAyudaSinEsp.add(caracterAyuda);
			}	
		}
	}*/
	
	private boolean isAZ(int caracter) {
		
		return ((caracter >= 65 && caracter <= 90) || (caracter >= 97 && caracter <= 122));
	}
	
	private void decodificador() {
		
		for (int i = 0; i < this.tam; i++) {
			
			this.claveDescifrado.put((char) (this.cromosoma.get(i) + this.initLetter), (char) (i + this.initLetter));
		}
	}
	
	private double calculateFitness() {
		
		StringBuilder sb = new StringBuilder();
		StringBuilder word = new StringBuilder();
		
		int j = 0;
		for (int i = 0; i < this.textoAyuda.length(); i++) {
			
			char caracter = Character.toUpperCase(this.textoAyuda.charAt(i));
			
			if (this.isAZ(caracter)) {
				
				sb.append(caracter);
				word.append(caracter);
				j = sb.length();
				
				System.out.println(caracter);
				this.fitness += this.ngramas.frecuenciaMonogramas.get(Character.toString(caracter))/(Math.log(tamTextoAyuda)*2);
				//this.fitness += this.ngramas.frecuenciaMonogramas.get(Character.toString(caracter))/((Math.log(tamTextoAyuda)/Math.log(2))*2);
				
				if (j > 1) {
					
					this.fitness += this.ngramas.frecuenciaBigramas.get(sb.substring(j - 2, j));
				}
				if (j > 2) {
					
					this.fitness += this.ngramas.frecuenciaTrigramas.get(sb.substring(j - 3, j)) * 5;
				}
				if (j > 3) {
					
					if (this.ngramas.frecuenciaCuadragramas.containsKey(sb.substring(j - 4, j))) {
						
						this.fitness += this.ngramas.frecuenciaCuadragramas.get(sb.substring(j - 4, j)) * 5;
					}
					else {
						
						this.fitness -= 1;
					}
				}
				if (j > 4) {
					if (this.ngramas.frecuenciaQuintagramas.containsKey(sb.substring(j - 5, j))) {
						this.fitness += this.ngramas.frecuenciaQuintagramas.get(sb.substring(j - 5, j)) * 10;
					}
					else this.fitness -= 1;
				}
				
				if (this.ngramas.frecuenciaPalabras.containsKey(word.toString())) {
					this.fitness += this.ngramas.frecuenciaPalabras.get(word.toString()) * (word.length());
				}
			}
			else {
				if (this.ngramas.frecuenciaPalabras.containsKey(word.toString())) {
					this.fitness += this.ngramas.frecuenciaPalabras.get(word.toString()) * (word.length() * 2);
				}
				word = new StringBuilder();
			}
		}
		
		
		return (this.fitness/this.tamTextoAyuda);
	}
	
	private void calculateFenotipe() {
		
		this.decodificador();
		
		for (int i = 0; i < this.tamTexto; i++) {
			
			char caracter = this.textoOriginal.charAt(i);
			
			if (Character.isUpperCase(caracter)) {
				
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
					this.fenotipo.add(Character.toLowerCase(this.claveDescifrado.get(caracter)));
				}
				else {
					this.fenotipo.add(Character.toLowerCase(caracter));
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
