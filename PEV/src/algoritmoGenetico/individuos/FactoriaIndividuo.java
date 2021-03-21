package algoritmoGenetico.individuos;

public class FactoriaIndividuo {

	public static Individuo<?> getIndividuo(String id, double precision) {
		
		switch(id) {
			
		case "Funcion 1": return new IndividuoFuncion1(precision);
        case "Funcion Schubert": return new IndividuoFuncion2(precision);
        case "Funcion Holder table": return new IndividuoFuncion3(precision);
        case "Funcion Michalewicz (Booleanos)": return new IndividuoFuncion4(6, precision);
        case "Funcion Michalewicz (Reales)": return new IndividuoFuncion4(6, precision);
        default: return new IndividuoFuncion1(precision);
		}
	}
}
