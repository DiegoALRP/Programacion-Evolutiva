package algoritmoGenetico.individuos;

import java.util.HashMap;
import java.util.concurrent.Semaphore;

public class HiloFitness2 extends Thread{

	private double fitness;
	private NGramas ngramas;
	private Texto claseTexto;
	private HashMap<Character, Character> claveDescifrado;
	private StringBuilder textoAyuda;
	private int tamTextoAyuda;
	Semaphore sem;
	Fitness numFitness;

	public HiloFitness2(NGramas ngramas, Texto claseTexto, HashMap<Character, Character> claveDescifrado, Semaphore sem, Fitness numFitness) {
		
		this.fitness = 0;
		this.ngramas = ngramas;
		this.claseTexto = claseTexto;
		this.claveDescifrado = claveDescifrado;
		this.textoAyuda = claseTexto.textoAyuda;
		this.tamTextoAyuda = textoAyuda.length();
		this.sem = sem;
		this.numFitness = numFitness;
	}
	
	public void run() {
		
		this.calculaFitness();
	}
	
	public void calculaFitness() {
		
		StringBuilder sb = new StringBuilder();
		StringBuilder word = new StringBuilder();
		
		this.fitness = 0;
		int j = 0;
		for (int i = (this.claseTexto.puntoMedio - 5); i < this.textoAyuda.length(); i++) {
			
			char caracter = Character.toUpperCase(this.textoAyuda.charAt(i));
			
			if (this.isAZ(caracter)) {
				
				char caracterDeco = this.claveDescifrado.get(caracter);

				sb.append(caracterDeco);
				word.append(caracterDeco);
				j = sb.length();
				
				/**ganadora */
				this.fitness += this.ngramas.frecuenciaMonogramas.get(Character.toString(caracterDeco))/((Math.log(tamTextoAyuda)/Math.log(2))*2);
				//this.fitness += this.ngramas.frecuenciaMonogramas.get(Character.toString(caracterDeco))/((Math.log(tamTextoAyuda)/Math.log(2))*3);
				
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
				
				/*if (this.ngramas.frecuenciaPalabras.containsKey(word.toString())) {
					this.fitness += this.ngramas.frecuenciaPalabras.get(word.toString()) * (Math.log(word.length()));
				}*/
			}
			else {
				
				if (this.ngramas.frecuenciaPalabras.containsKey(word.toString()) && word.length() > 1) {
					this.fitness += this.ngramas.frecuenciaPalabras.get(word.toString()) * (word.length() * 2);
				}
				word = new StringBuilder();
			}
			
			//System.out.println(i);
		}
		
		this.addFitness();
	}
	
	private void addFitness() {
		
		try {
			this.sem.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.numFitness.addFitness(fitness);
		this.sem.release();
	}
		
	private boolean isAZ(int caracter) {
		
		return ((caracter >= 65 && caracter <= 90) || (caracter >= 97 && caracter <= 122));
	}
	
	public double getFitness() {
		return this.fitness;
	}
}
