package algoritmoGenetico.individuos;

public class Fitness {

	private volatile double fitness;
	
	public Fitness() {
		
		fitness = 0;
	}
	
	public void addFitness(double fitness) {
		
		this.fitness += fitness;
	}
	
	public double getFitness() {
		return this.fitness;
	}
}
