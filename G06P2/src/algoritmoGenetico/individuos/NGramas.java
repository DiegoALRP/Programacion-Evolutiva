package algoritmoGenetico.individuos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Universidad Complutense de Madrid.
 * Programación Evolutiva.
 * Grupo A 2021.
 * Profesor:
 * 	-Carlos Cervigon Ruckauer.
 * 
 * Clase NGramas.
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodríguez Pereira.
 *
 */

public class NGramas {

	
	/**************************** ATRIBUTTES *******************************/
	protected HashMap<String, Double> frecuenciaMonogramas;
	protected HashMap<String, Double> frecuenciaBigramas;
	protected HashMap<String, Double> frecuenciaTrigramas;
	protected HashMap<String, Double> frecuenciaCuadragramas;
	protected HashMap<String, Double> frecuenciaQuintagramas;
	protected HashMap<String, Double> frecuenciaPalabras;
	
	private final int tamMonograma = 26;
	private final int tamBigrama = 676;
	private final int tamTrigrama = 17556;
	private final int tamCuadragrama = 389373;
	private final int tamQuintagrama = 4354914;
	private final int tamPalabras = 706375;
	
	
	
	/**************************** CONSTRUCTOR *******************************/
	public NGramas() {
		
		this.frecuenciaMonogramas = new HashMap<String, Double>(tamMonograma);
		this.frecuenciaBigramas = new HashMap<String, Double>(tamBigrama);
		this.frecuenciaTrigramas = new HashMap<String, Double>(tamTrigrama);
		this.frecuenciaCuadragramas = new HashMap<String, Double>(tamCuadragrama);
		this.frecuenciaQuintagramas = new HashMap<String, Double>(tamQuintagrama);
		this.frecuenciaPalabras = new HashMap<String, Double>(tamPalabras);
	}
	
	
	
	/***************************** METHODS ********************************/
	public void loadNGrams() {
		
		try {
			
			this.loadMonograms();
			this.loadBigrams();
			this.loadTrigrams();
			this.loadQuadgrams();
			this.loadQuintgrams();
			this.loadWords();
			
			System.out.println("Terminado carga de TODOS los ficheros, gracias por la espera");
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
	}
	
	 /**
	  * 
	  * @throws FileNotFoundException
	  */
	private void loadMonograms() throws FileNotFoundException {
		
		System.out.println("Cargando Monogramas....");
		FileInputStream file = new FileInputStream("frecuencies/english_monograms_frecuencies.txt");
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(file);
		
		while (sc.hasNextLine()) {
			String[] st = sc.nextLine().split(" ");
			this.frecuenciaMonogramas.put(st[0], Double.parseDouble(st[1]));
		}
		
		System.out.println("Terminado carga Monogramas, número de palabras: " + this.frecuenciaMonogramas.size());
	}
	
	/**
	 * 
	 * @throws FileNotFoundException
	 */
	private void loadBigrams() throws FileNotFoundException {
		
		System.out.println("Cargando Bigramas....");
		FileInputStream file = new FileInputStream("frecuencies/english_bigrams_frecuencies.txt");
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(file);
		
		while (sc.hasNextLine()) {
			String[] st = sc.nextLine().split(" ");
			this.frecuenciaBigramas.put(st[0], Double.parseDouble(st[1]));
		}
		
		System.out.println("Terminado carga Bigramas , número de palabras: " + this.frecuenciaBigramas.size());
	}
	
	/**
	 * 
	 * @throws FileNotFoundException
	 */
	private void loadTrigrams() throws FileNotFoundException {
		
		System.out.println("Cargando Trigramas....");
		FileInputStream file = new FileInputStream("frecuencies/english_trigrams_frecuencies.txt");
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(file);
		
		while (sc.hasNextLine()) {
			String[] st = sc.nextLine().split(" ");
			this.frecuenciaTrigramas.put(st[0], Double.parseDouble(st[1]));
		}
		
		System.out.println("Terminado carga Trigramas , número de palabras: " + this.frecuenciaTrigramas.size());
	}

	/**
	 * 
	 * @throws FileNotFoundException
	 */
	private void loadQuadgrams() throws FileNotFoundException {
		
		System.out.println("Cargando Cuadragramas....");
		FileInputStream file = new FileInputStream("frecuencies/english_quadgrams_frecuencies.txt");
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(file);
		
		while (sc.hasNextLine()) {
			String[] st = sc.nextLine().split(" ");
			this.frecuenciaCuadragramas.put(st[0], Double.parseDouble(st[1]));
		}
		
		System.out.println("Terminado carga Cuadragramas , número de palabras: " + this.frecuenciaCuadragramas.size());
	}
	
	/**
	 * 
	 * @throws FileNotFoundException
	 */
	private void loadQuintgrams() throws FileNotFoundException {
		
		System.out.println("Cargando Quintagramas............. Por favor espere...");
		FileInputStream file = new FileInputStream("frecuencies/english_quintgrams_frecuencies.txt");
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(file);
		
		while (sc.hasNextLine()) {
			String[] st = sc.nextLine().split(" ");
			this.frecuenciaQuintagramas.put(st[0], Double.parseDouble(st[1]));
		}
		
		System.out.println("Terminado carga Quintagramas , número de palabras: " + this.frecuenciaQuintagramas.size());
	}
	
	/**
	 * 
	 * @throws FileNotFoundException
	 */
	private void loadWords() throws FileNotFoundException {
		
		System.out.println("Cargando Palabras del Inglés........ Por favor espere...");
		FileInputStream file = new FileInputStream("frecuencies/english_words_frecuencies.txt");
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(file);
		
		while (sc.hasNextLine()) {
			String[] st = sc.nextLine().split(" ");
			this.frecuenciaPalabras.put(st[0], Double.parseDouble(st[1]));
		}
		
		System.out.println("Terminado carga palabras, número de palabras: " + this.frecuenciaPalabras.size());
	}
	
	/**************************** GETTERS & SETTERS ********************************/
}
