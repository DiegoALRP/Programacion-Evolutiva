package algoritmoGenetico.individuos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Semaphore;

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
	protected ArrayList<Integer> cromosoma;						//Cromosoma del individuo. Tiene tamaño 26 y está formado por números que representan el índice de las letras.
	protected HashMap<Character, Character> decodificador; 		//HashMap que tiene como clave las letras a-z del texto cifrado y como valor las letras a-z del texto descifrado.
	protected StringBuilder fenotipo;							//Fenotipo del individuo.
	
	protected StringBuilder textoOriginal; 		//Texto original que vamos a descrifrar
	protected int tamTexto;						//Tamaño del texto original
	protected StringBuilder textoAyuda;			//Texto adicional de ayuda para poder descifrar más rápidamente
	protected int tamTextoAyuda;				//Tamaño del texto de ayuda
	
	protected volatile double fitness;			//Valor de aptitud/Fitness del individuo
	protected double fitness_ranking;			//Valor de aptitud/Fitness del individuo para la selección de ranking
	
	protected NGramas ngramas;					//Clase que contiene los n-gramas
	protected Texto claseTexto;					//Clase que contiene el texto
	
	protected boolean cromoModificado;			//Booleano que nos indica si el individuo ha sido cruzado o mutado, o en su defecto, que su cromosoma ha sido modificado
	
	
	/************************** CONSTANT ATRIBUTTES *****************************/
	protected final int tam = 26;			//Tamaño del cromosoma, 26, dado que el abecedario (en el inglés consta de 26 palabras).
	protected final int initLetter = 65;	//Valor de la letra A en ASCII
	
	
	
	/**************************** CONSTRUCTOR *******************************/
	
	/**
	 * [ES] Constructora que genera un nuevo individuo con valores nuevos y el cromosoma generado aleatoriamente.
	 * [EN] Constructor that generates a new individual with new values and the cromosome generated randomly.
	 * 
	 * @param claseTexto	[ES] Clase que contiene los textos.
	 * 						[EN] Class that contains the texts.
	 * 
	 * @param ngramas		[ES] Clase que contiene los n-gramas.
	 * 						[EN] Class that contains the n-grams.
	 */
	public Individuo(Texto claseTexto, NGramas ngramas) {
		
		this.cromosoma = new ArrayList<Integer>(tam);
		
		this.decodificador = new HashMap<Character, Character>(tam);
		this.fenotipo = new StringBuilder(tamTexto);
		
		this.textoOriginal = claseTexto.getTextoOriginal();
		this.textoAyuda = claseTexto.getTextoAyuda();
		this.tamTexto = textoOriginal.length();
		this.tamTextoAyuda = textoAyuda.length();

		this.ngramas = ngramas;
		this.claseTexto = claseTexto;
		
		this.cromoModificado = true;
		
		this.initializeCromosome();
		
		this.calculateFenotipo();
		
		this.calculateFitness();
	}
	
	
	/**
	 * [ES] Constructora que crea un nuevo individuo con un cromosoma predefinido.
	 * Esta constructora nos permite generar individuos con el cromosoma que deseamos,
	 * es especialmente útil para duplicar individuos o copiar individuos.
	 * 
	 * [EN] Constructor that creates a new individual with a predefined cromosome.
	 * This constructor allow us to generate individuals with the cromosome we want,
	 * this is specially useful to duplicate or copy individuals.
	 * 
	 * @param claseTexto 	[ES] Clase que contiene los textos.
	 * 						[EN] Class that contains the texts.
	 * 
	 * @param ngramas	[ES] Clase que contiene los n-gramas.
	 * 					[EN] Class that contains the n-grams.
	 * 
	 * @param cromosoma	[ES] Cromosoma que le queremos poner al individuo.
	 * 					[EN] Cromosome that we want to put to the individual.
	 */
	public Individuo(Texto claseTexto, NGramas ngramas, ArrayList<Integer> cromosoma) {
		
		this.cromosoma = new ArrayList<Integer>(tam);
		this.cromosoma.addAll(cromosoma);
		
		this.decodificador = new HashMap<Character, Character>(tam);
		this.fenotipo = new StringBuilder(tamTexto);
		
		this.textoOriginal = claseTexto.getTextoOriginal();
		this.textoAyuda = claseTexto.getTextoAyuda();
		this.tamTexto = textoOriginal.length();
		this.tamTextoAyuda = textoAyuda.length();
		
		this.ngramas = ngramas;
		this.claseTexto = claseTexto;
		
		this.cromoModificado = true;
		
		this.calculateFitness();
	}
	
	
	
	/***************************** METHODS ********************************/
	
	/**
	 * [ES] Inicializa el cromosoma del individuo.
	 * Es generado aleatoriamente.
	 * 
	 * [EN] Initializes the individual's cromosome.
	 * It's randomly generated.
	 * 
	 */
	private void initializeCromosome() {
		
		//Ejemplo profesor (Es para debuggear, pero solo funciona con el ejemplo del enunciado de la práctica).
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
	
	
	/**
	 * [ES] Reinicia el cromosoma del individuo.
	 * Sustituye el cromosoma que tiene actualmente por un cromosoma
	 * generado aleatoriamente.
	 * 
	 * [EN] Restarts the individual's cromosome.
	 * Replaces the actual cromosome for a random generated cromosome.
	 * 
	 */
	public void restartCromosome() {
		
		this.cromosoma.clear();
		
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
		
		this.cromoModificado = true;
		this.calculateFitness();
	}
	
	
	/**
	 * [ES] Función que traduce el cromosoma que es en números, a un decodificador que contiene
	 * la letra origial con la letra cifrada.
	 * 
	 * [EN] Function that translates the cromosome that is in numbers, to a decoder that contains
	 * the original letter with the decipher letter.
	 * 
	 */
	private void decodifica() {
		
		for (int i = 0; i < this.tam; i++) {
			
			this.decodificador.put((char) (this.cromosoma.get(i) + this.initLetter), (char) (i + this.initLetter));
		}
	}
	
	/**
	 * [ES] Función que calcula el valor de aptitud/fitness del individuo.
	 * 
	 * [EN] Function that calculates the individual's fitness value.
	 * 
	 * @return 	[ES] Valor de aptitud/fitness
	 * 			[EN] Fitness value.
	 */
	public double calculateFitness() {
		
		if (cromoModificado) {
			if (tamTexto > 160) {
				
				
				firstFitness();
				
				//Descomentar la siguiente línea para utilizar concurrencia en el cálculo del fitness.
				//calculaFitnessParalelo();
			}
			else {
				
				this.secondFitness();
			}
		}
		
		this.cromoModificado = false;
		
		return this.fitness;
	}
	
	/**
	 * [ES] Función auxiliar que calcula el fitness de manera concurrente.
	 * Se crean dos hilos y se divide el texto por la mitad.
	 * De esta manera se analiza las dos mitades del texto al mismo tiempo y se reduce los tiempos
	 * de ejecución.
	 * Hecha para textos muy largos.
	 * 
	 * [EN] Auxiliary function that calculates concurrently the fitness value.
	 * It creates to threads and divides the text in half.
	 * With this, it can analyzes the two half of the texto in the same time, and
	 * reduce the execution time.
	 * Made for very large texts.
	 * 
	 */
	@SuppressWarnings("unused")
	private void calculaFitnessParalelo() {
		
		this.decodifica();
		
		Semaphore sem = new Semaphore(1);
		Fitness numFitness = new Fitness();
		
		HiloFitness1 hilo1 = new HiloFitness1(ngramas, claseTexto, decodificador, sem, numFitness);
		HiloFitness2 hilo2 = new HiloFitness2(ngramas, claseTexto, decodificador, sem, numFitness);
		
		hilo1.start();
		hilo2.start();
		
		try {
			hilo1.join();
			hilo2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.fitness = numFitness.getFitness();
		
		this.fitness = this.fitness/Math.log10(this.tamTextoAyuda);
	}
	
	
	/**
	 * [ES] Función auxiliar que calcula el fitness para textos que no sean cortos.
	 * Utiliza la frecuencia de los n-gramas para el cálculo.
	 * 
	 * [EN] Auxiliary function that calculates the fitness values for texts that are
	 * not short.
	 * It uses the frequencies of the n-gram to calculate the fitness.
	 * 
	 * @return 	[ES] Valor de Fitness.
	 * 			[EN] Fitness value.
	 */
	private double firstFitness() {
		
		StringBuilder sb = new StringBuilder();
		StringBuilder word = new StringBuilder();
		this.decodifica();
		
		this.fitness = 0;
		int j = 0;
		for (int i = 0; i < this.textoAyuda.length(); i++) {
			
			char caracter = Character.toUpperCase(this.textoAyuda.charAt(i));
			
			if (this.isAZ(caracter)) {
				
				char caracterDeco = this.decodificador.get(caracter);

				sb.append(caracterDeco);
				word.append(caracterDeco);
				j = sb.length();
				
				this.fitness += this.ngramas.frecuenciaMonogramas.get(Character.toString(caracterDeco))/((Math.log(tamTextoAyuda)/Math.log(2))*2);
				
				if (j > 1) {
					this.fitness += this.ngramas.frecuenciaBigramas.get(sb.substring(j - 2, j))/((Math.log(tamTextoAyuda)/Math.log(2))*2);
				}
				if (j > 2) {
					
					if (this.ngramas.frecuenciaTrigramas.containsKey(sb.substring(j - 3, j))) {
						this.fitness += this.ngramas.frecuenciaTrigramas.get(sb.substring(j - 3, j)) /((Math.log(tamTextoAyuda)/Math.log(2))*2);
					}
				}
				if (j > 3) {
					if (this.ngramas.frecuenciaCuadragramas.containsKey(sb.substring(j - 4, j))) {
						this.fitness += this.ngramas.frecuenciaCuadragramas.get(sb.substring(j - 4, j))/((Math.log(tamTextoAyuda)/Math.log(2)));
					}
				}
				if (j > 4) {
					if (this.ngramas.frecuenciaQuintagramas.containsKey(sb.substring(j - 5, j))) {
						this.fitness += this.ngramas.frecuenciaQuintagramas.get(sb.substring(j - 5, j)) /((Math.log(tamTextoAyuda)/Math.log(2)));
					}
				}
			}
			else {
				
				if (this.ngramas.frecuenciaPalabras.containsKey(word.toString()) && word.length() > 1) {
					//this.fitness += (this.ngramas.frecuenciaPalabras.get(word.toString()) * (Math.log(word.length())/Math.log(2)))/((Math.log(tamTextoAyuda)/Math.log(2)));
					this.fitness += (this.ngramas.frecuenciaPalabras.get(word.toString()) * word.length())/((Math.log(tamTextoAyuda)/Math.log(2)));
				}
				word = new StringBuilder();
			}
		}
		
		this.fitness = this.fitness/Math.log10(this.tamTextoAyuda);
		
		return this.fitness;
	}
	
	
	/**
	 * [ES] Función auxiliar que calcula el fitness para textos que sean cortos.
	 * Utiliza la frecuencia de los n-gramas para el cálculo.
	 * 
	 * [EN] Auxiliary function that calculates the fitness values for texts that are
	 * short.
	 * It uses the frequencies of the n-gram to calculate the fitness.
	 * 
	 */
	private void secondFitness() {
		
		StringBuilder sb = new StringBuilder();
		StringBuilder word = new StringBuilder();
		this.decodifica();
		
		this.fitness = 0;
		int j = 0;
		for (int i = 0; i < this.textoAyuda.length(); i++) {
			
			char caracter = Character.toUpperCase(this.textoAyuda.charAt(i));
			
			if (this.isAZ(caracter)) {
				
				char caracterDeco = this.decodificador.get(caracter);

				sb.append(caracterDeco);
				word.append(caracterDeco);
				j = sb.length();
				
				if (j > 1) {
					this.fitness += this.ngramas.frecuenciaBigramas.get(sb.substring(j - 2, j))/((Math.log(tamTextoAyuda)/Math.log(2))*2);
				}
				if (j > 2) {
					
					if (this.ngramas.frecuenciaTrigramas.containsKey(sb.substring(j - 3, j))) {
						this.fitness += this.ngramas.frecuenciaTrigramas.get(sb.substring(j - 3, j)) /((Math.log(tamTextoAyuda)/Math.log(2))*2);
					}
				}
				if (j > 3) {
					if (this.ngramas.frecuenciaCuadragramas.containsKey(sb.substring(j - 4, j))) {
						this.fitness += this.ngramas.frecuenciaCuadragramas.get(sb.substring(j - 4, j))/((Math.log(tamTextoAyuda)/Math.log(2)));
					}
				}
				if (j > 4) {
					if (this.ngramas.frecuenciaQuintagramas.containsKey(sb.substring(j - 5, j))) {
						this.fitness += this.ngramas.frecuenciaQuintagramas.get(sb.substring(j - 5, j)) /((Math.log(tamTextoAyuda)/Math.log(2)));
					}
				}
			}
			else {
				
				if (this.ngramas.frecuenciaPalabras.containsKey(word.toString()) && word.length() > 1) {
					this.fitness += this.ngramas.frecuenciaPalabras.get(word.toString()) * (word.length()*5);
				}
				word = new StringBuilder();
			}
		}
	}
	
	
	/**
	 * [ES] Verifica que la letra que es leía se encuentra entre los carácteres a-z o A-Z.
	 * 
	 * [EN] Verifies that the read letter is between a-z or A-Z.
	 * 
	 * @param caracter	[ES] Carácter que queremos verificar.
	 * 					[EN] Character that we want to verify.
	 * 
	 * @return	[ES] True, si se encuentra entre los carácteres a-z o A-Z. False, en otro caso.
	 * 			[EN] True, if is between a-z or A-Z. False, otherwise.
	 */
	private boolean isAZ(int caracter) {
		
		return ((caracter >= 65 && caracter <= 90) || (caracter >= 97 && caracter <= 122));
	}
	
	
	/**
	 * [ES] Función que calcula el fenotipo del individuo.
	 * 
	 * [EN] Function that calculates the individual's fenotipe.
	 * 
	 */
	private void calculateFenotipo() {
		
		this.decodifica();
		this.fenotipo = new StringBuilder(tamTexto);
		
		for (int i = 0; i < this.tamTexto; i++) {
			
			char caracter = this.textoOriginal.charAt(i);
			
			if (Character.isUpperCase(caracter)) {
				
				if (this.decodificador.containsKey(caracter)) {
					this.fenotipo.append(this.decodificador.get(caracter));
				}
				else {
					this.fenotipo.append(caracter);
				}
			}
			else {
				
				caracter = Character.toUpperCase(caracter);
				if (this.decodificador.containsKey(caracter)) {
					this.fenotipo.append(Character.toLowerCase(this.decodificador.get(caracter)));
				}
				else {
					this.fenotipo.append(Character.toLowerCase(caracter));
				}
			}
		}
	}
	
	public void avisoCromoModificado() {
		
		this.cromoModificado = true;
	}
	
	
	
	/**************************** GETTERS & SETTERS ********************************/
	public NGramas getNGrama() {
		
		return this.ngramas;
	}
	
	public Texto getTexto() {
		
		return this.claseTexto;
	}
	
	public ArrayList<Integer> getCromosoma(){
		
		return this.cromosoma;
	}
	
	public void setCromosoma(ArrayList<Integer> cromosoma) {
		
		this.cromosoma.clear();
		this.cromosoma.addAll(cromosoma);
		this.cromoModificado = true;
	}
	
	public String getFenotipe() {
		
		this.calculateFenotipo();
		return this.fenotipo.toString();
	}
	
	public void setFitnessRanking(double f) {
		this.fitness_ranking = f;
	}
	
	public double getFitness() {	
		return this.fitness;
	}
	
	public double getFitnessRanking() {
		return this.fitness_ranking;
	}
	
	public StringBuilder getCromosomaLetra() {
		StringBuilder cromosoma = new StringBuilder();
		for(int i = 0; i < this.cromosoma.size(); i++) {
			Character letra = (char) (this.cromosoma.get(i) + this.initLetter);
			cromosoma.append(Character.toLowerCase(letra) + " ");
		}
		
		return cromosoma;
	}
}
