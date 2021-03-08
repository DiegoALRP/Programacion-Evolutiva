package algoritmoGenetico;

import java.util.ArrayList;
import java.util.List;

import algoritmoGenetico.individuos.Individuo;
import algoritmoGenetico.individuos.IndividuoFuncion1;
import algoritmoGenetico.seleccion.Seleccion;
import algoritmoGenetico.seleccion.SeleccionRuleta;

public class AlgoritmoGenetico {

	private double[] mejorAbsoluto;		//Array con el mejor absoluto (a lo largo de todas las generaciones)
	private double[] mejorGeneracion;	//Array con el mejor fitness de una generacion
	private double[] mediaGeneracion;	//Array con la media de fitness de cada generacion
	
	private int tamPoblacion;			//Tamanho de la poblacion
	
	/*
	 * crear poblacion inicial
	 * 
	 */
	public AlgoritmoGenetico() {
		
		// IndividuoFuncion1 ind1 = new IndividuoFuncion1();
		// ind1.inicializaIndividuo();
		// System.out.println(ind1.getFitness());
		
		List<Individuo> poblacion = new ArrayList<Individuo>();
		for(int i=0;i<5;i++) {
			IndividuoFuncion1 ind1 = new IndividuoFuncion1();
			ind1.inicializaIndividuo();
			poblacion.add(ind1);
		}
		
		Seleccion ruleta = new SeleccionRuleta(poblacion);
		ruleta.seleccionar(poblacion);
	}
}
