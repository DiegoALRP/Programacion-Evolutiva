package algoritmoGenetico.individuos;

public class FactoriaIndividuo {

	public static Individuo<?> getIndividuo(String id, double precision, int numN) {
		
		switch(id) {
			
		case "Funcion 1": return new IndividuoFuncion1(precision);
        case "Funcion Schubert": return new IndividuoFuncion2(precision);
        case "Funcion Holder table": return new IndividuoFuncion3(precision);
        case "Funcion Michalewicz (Booleanos)": return new IndividuoFuncion4(numN, precision);
        case "Funcion Michalewicz (Reales)": return new IndividuoFuncion4Double(numN, precision);
        case "Funcion Bukin" : return new IndividuoFuncionBukin();
        case "Funcion Matyas" : return new IndividuoFuncionMatyas();
        default: return new IndividuoFuncion1(precision);
		}
	}
}
