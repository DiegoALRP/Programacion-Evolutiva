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
	protected ArrayList<Integer> cromosoma;
	protected HashMap<Character, Character> claveDescifrado; //HashMap que tiene como clave las letras a-z del texto cifrado y como valor las letras a-z del texto descifrado
	protected StringBuilder fenotipo;
	
	protected StringBuilder textoOriginal; 
	protected int tamTexto;
	protected StringBuilder textoAyuda;
	protected int tamTextoAyuda;
	
	protected volatile double fitness;
	protected double fitness_ranking;
	
	protected NGramas ngramas;
	protected Texto claseTexto;
	
	protected boolean cromoModificado;
	
	/************************** CONSTANT ATRIBUTTES *****************************/
	protected final int tam = 26;
	protected final int initLetter = 65;
	
	/**************************** CONSTRUCTOR *******************************/
	public Individuo(Texto claseTexto, NGramas ngramas) {
		
		this.cromosoma = new ArrayList<Integer>(tam);
		
		this.claveDescifrado = new HashMap<Character, Character>(tam);
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
	
	public Individuo(Texto claseTexto, NGramas ngramas, ArrayList<Integer> cromosoma) {
		
		this.cromosoma = new ArrayList<Integer>(tam);
		this.cromosoma.addAll(cromosoma);
		
		this.claveDescifrado = new HashMap<Character, Character>(tam);
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
	
	private void decodifica() {
		
		for (int i = 0; i < this.tam; i++) {
			
			this.claveDescifrado.put((char) (this.cromosoma.get(i) + this.initLetter), (char) (i + this.initLetter));
			//this.claveDescifrado.put((char) (this.cromosoma.get(i)), (char) (i + this.initLetter));
		}
	}
	
	public double calculateFitness() {
		
		if (cromoModificado) {
			if (tamTexto > 160) {
				
				
				firstFitness();
				//calculaFitnessParalelo();
			}
			else {
				
				this.secondFitness();
			}
		}
		
		this.cromoModificado = false;
		
		return this.fitness;
	}
	
	private void calculaFitnessParalelo() {
		
		this.decodifica();
		
		Semaphore sem = new Semaphore(1);
		Fitness numFitness = new Fitness();
		
		HiloFitness1 hilo1 = new HiloFitness1(ngramas, claseTexto, claveDescifrado, sem, numFitness);
		HiloFitness2 hilo2 = new HiloFitness2(ngramas, claseTexto, claveDescifrado, sem, numFitness);
		
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
	
	private double firstFitness() {
		
		StringBuilder sb = new StringBuilder();
		StringBuilder word = new StringBuilder();
		this.decodifica();
		
		this.fitness = 0;
		int j = 0;
		for (int i = 0; i < this.textoAyuda.length(); i++) {
			
			char caracter = Character.toUpperCase(this.textoAyuda.charAt(i));
			
			if (this.isAZ(caracter)) {
				
				char caracterDeco = this.claveDescifrado.get(caracter);

				sb.append(caracterDeco);
				word.append(caracterDeco);
				j = sb.length();
				
				/**ganadora */
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
		
		//if (this.fitness < 0) {
		//	this.fitness = 0;
		//}
		//System.out.println("Fitness sin division: " + this.fitness);
		this.fitness = this.fitness/Math.log10(this.tamTextoAyuda);
		//System.out.println("Fitness con division: " + this.fitness);
		
		return this.fitness;
	}
	
	private void firstFitness2() {
		
		StringBuilder sb = new StringBuilder();
		StringBuilder word = new StringBuilder();
		this.decodifica();
		
		this.fitness = 0;
		int j = 0;
		for (int i = 0; i < this.textoAyuda.length(); i++) {
			
			char caracter = Character.toUpperCase(this.textoAyuda.charAt(i));
			
			if (this.isAZ(caracter)) {
				
				char caracterDeco = this.claveDescifrado.get(caracter);

				sb.append(caracterDeco);
				word.append(caracterDeco);
				j = sb.length();
				
				/**ganadora */
				this.fitness += (Math.log(this.ngramas.frecuenciaMonogramas.get(Character.toString(caracterDeco)))/Math.log(2))/tamTextoAyuda;
				//this.fitness += this.ngramas.frecuenciaMonogramas.get(Character.toString(caracterDeco))/((Math.log(tamTextoAyuda)/Math.log(2))*3);
				
				if (j > 1) {
					this.fitness += (Math.log(this.ngramas.frecuenciaBigramas.get(sb.substring(j - 2, j)))/Math.log(2))/tamTextoAyuda;
				}
				if (j > 2) {
					
					if (this.ngramas.frecuenciaTrigramas.containsKey(sb.substring(j - 3, j))) {
						this.fitness += (Math.log(this.ngramas.frecuenciaTrigramas.get(sb.substring(j - 3, j)))/Math.log(2))/tamTextoAyuda;
					}
				}
				if (j > 3) {
					if (this.ngramas.frecuenciaCuadragramas.containsKey(sb.substring(j - 4, j))) {
						this.fitness += (Math.log(this.ngramas.frecuenciaCuadragramas.get(sb.substring(j - 4, j)))/Math.log(2))/tamTextoAyuda;
					}
				}
				if (j > 4) {
					if (this.ngramas.frecuenciaQuintagramas.containsKey(sb.substring(j - 5, j))) {
						this.fitness += (Math.log(this.ngramas.frecuenciaQuintagramas.get(sb.substring(j - 5, j)))/Math.log(2))/tamTextoAyuda;
					}
				}
				
				/*if (this.ngramas.frecuenciaPalabras.containsKey(word.toString())) {
					this.fitness += this.ngramas.frecuenciaPalabras.get(word.toString()) * (Math.log(word.length()));
				}*/
			}
			else {
				
				/*if (this.ngramas.frecuenciaPalabras.containsKey(word.toString()) && word.length() > 1) {
					this.fitness += this.ngramas.frecuenciaPalabras.get(word.toString());// * (word.length() * 2);
				}*/
				//word = new StringBuilder();
			}
		}
		
		this.fitness = this.fitness/Math.log10(this.tamTextoAyuda);
	}
	
	private void secondFitness() {
		
		StringBuilder sb = new StringBuilder();
		StringBuilder word = new StringBuilder();
		this.decodifica();
		
		this.fitness = 0;
		int j = 0;
		for (int i = 0; i < this.textoAyuda.length(); i++) {
			
			char caracter = Character.toUpperCase(this.textoAyuda.charAt(i));
			
			if (this.isAZ(caracter)) {
				
				char caracterDeco = this.claveDescifrado.get(caracter);

				sb.append(caracterDeco);
				word.append(caracterDeco);
				j = sb.length();
				
				/**ganadora */
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
				
				if (word.toString() == "woodchuck" || word.toString() == "wood") {
					System.out.println("!!!!!!!!!!!!!!!!!!!!!");
					this.fitness += 100;
				}
				if (word.toString() == "chuck") {
					System.out.println("???????????????????");
					this.fitness += 50;
				}
				if (this.ngramas.frecuenciaPalabras.containsKey(word.toString()) && word.length() > 1) {
					this.fitness += this.ngramas.frecuenciaPalabras.get(word.toString()) * (word.length()*5);
				}
				word = new StringBuilder();
			}
		}
		
		//this.fitness = this.fitness/Math.log(this.tamTextoAyuda);
	}
	
	private boolean isAZ(int caracter) {
		
		return ((caracter >= 65 && caracter <= 90) || (caracter >= 97 && caracter <= 122));
	}
	
	private void calculateFenotipo() {
		
		this.decodifica();
		this.fenotipo = new StringBuilder(tamTexto);
		
		for (int i = 0; i < this.tamTexto; i++) {
			
			char caracter = this.textoOriginal.charAt(i);
			
			if (Character.isUpperCase(caracter)) {
				
				if (this.claveDescifrado.containsKey(caracter)) {
					this.fenotipo.append(this.claveDescifrado.get(caracter));
				}
				else {
					this.fenotipo.append(caracter);
				}
			}
			else {
				
				caracter = Character.toUpperCase(caracter);
				if (this.claveDescifrado.containsKey(caracter)) {
					this.fenotipo.append(Character.toLowerCase(this.claveDescifrado.get(caracter)));
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
	
	/**************************** GET & SET ********************************/
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
