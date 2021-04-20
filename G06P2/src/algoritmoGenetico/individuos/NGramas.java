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
 * Clase Mutacion.
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
	
	public void loadHashs() {
		
		try {
			
			this.loadMonograms();
			this.loadBigrams();
			this.loadTrigrams();
			this.loadQuadgrams();
			this.loadQuintgrams();
			this.loadWords();
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
	}
	
	 /**
	  * 
	  * @throws FileNotFoundException
	  */
	public void loadMonograms() throws FileNotFoundException {
		
		FileInputStream file = new FileInputStream("frecuencies/english_monograms_frecuencies.txt");
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(file);
		
		while (sc.hasNextLine()) {
			String[] st = sc.nextLine().split(" ");
			this.frecuenciaMonogramas.put(st[0], Double.parseDouble(st[1]));
		}
		
		System.out.println("Monosize: " + this.frecuenciaMonogramas.size());
	}
	
	/**
	 * 
	 * @throws FileNotFoundException
	 */
	public void loadBigrams() throws FileNotFoundException {
		
		FileInputStream file = new FileInputStream("frecuencies/english_bigrams_frecuencies.txt");
		Scanner sc = new Scanner(file);
		
		while (sc.hasNextLine()) {
			String[] st = sc.nextLine().split(" ");
			this.frecuenciaBigramas.put(st[0], Double.parseDouble(st[1]));
		}
		
		System.out.println("Bisize: " + this.frecuenciaBigramas.size());
	}
	
	/**
	 * 
	 * @throws FileNotFoundException
	 */
	public void loadTrigrams() throws FileNotFoundException {
		
		FileInputStream file = new FileInputStream("frecuencies/english_trigrams_frecuencies.txt");
		Scanner sc = new Scanner(file);
		
		while (sc.hasNextLine()) {
			String[] st = sc.nextLine().split(" ");
			this.frecuenciaTrigramas.put(st[0], Double.parseDouble(st[1]));
		}
		
		System.out.println("Trisize: " + this.frecuenciaTrigramas.size());
	}

	/**
	 * 
	 * @throws FileNotFoundException
	 */
	public void loadQuadgrams() throws FileNotFoundException {
		
		FileInputStream file = new FileInputStream("frecuencies/english_quadgrams_frecuencies.txt");
		Scanner sc = new Scanner(file);
		
		while (sc.hasNextLine()) {
			String[] st = sc.nextLine().split(" ");
			this.frecuenciaCuadragramas.put(st[0], Double.parseDouble(st[1]));
		}
		
		System.out.println("Quadsize: " + this.frecuenciaCuadragramas.size());
	}
	
	/**
	 * 
	 * @throws FileNotFoundException
	 */
	public void loadQuintgrams() throws FileNotFoundException {
		
		FileInputStream file = new FileInputStream("frecuencies/english_quintgrams_frecuencies.txt");
		Scanner sc = new Scanner(file);
		
		while (sc.hasNextLine()) {
			String[] st = sc.nextLine().split(" ");
			this.frecuenciaQuintagramas.put(st[0], Double.parseDouble(st[1]));
		}
		
		System.out.println("Quintsize: " + this.frecuenciaQuintagramas.size());
	}
	
	/**
	 * 
	 * @throws FileNotFoundException
	 */
	public void loadWords() throws FileNotFoundException {
		
		FileInputStream file = new FileInputStream("frecuencies/english_words_frecuencies.txt");
		Scanner sc = new Scanner(file);
		
		while (sc.hasNextLine()) {
			String[] st = sc.nextLine().split(" ");
			this.frecuenciaPalabras.put(st[0], Double.parseDouble(st[1]));
		}
		
		System.out.println("Wordssize: " + this.frecuenciaPalabras.size());
	}
	
	/**************************** GET & SET ********************************/
}
