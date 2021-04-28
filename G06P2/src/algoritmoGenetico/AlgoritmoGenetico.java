package algoritmoGenetico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Random;

import algoritmoGenetico.cruces.Cruce;
import algoritmoGenetico.cruces.CrucePMX;
import algoritmoGenetico.individuos.Individuo;
import algoritmoGenetico.individuos.NGramas;
import algoritmoGenetico.individuos.Texto;
import algoritmoGenetico.mutaciones.Mutacion;
import algoritmoGenetico.mutaciones.MutacionIntercambio;
import algoritmoGenetico.seleccion.Seleccion;
import algoritmoGenetico.seleccion.SeleccionTorneo;

/**
 * Universidad Complutense de Madrid.
 * Programación Evolutiva.
 * Grupo A 2021.
 * Profesor:
 * 	-Carlos Cervigon Ruckauer.
 * 
 * Práctica 2
 * 
 * Clase Algoritmo Genético.
 * 
 * @author 
 * Grupo G06:
 * 	-Miguel Robledo.
 * 	-Diego Alejandro Rodríguez Pereira.
 *
 */
public class AlgoritmoGenetico {
	
	/**************************** ATRIBUTTES *******************************/
	private int tamPoblacion;
	private int numGeneraciones;
	private Seleccion metodoSeleccion;
	private Cruce metodoCruce;
	private double porcCruce;
	private Mutacion metodoMutacion;
	private double porcMutacion;
	private double porcElite;
	
	private NGramas ngramas;
	private Texto claseTexto;
	
	private ArrayList<Individuo> poblacion;
	private ArrayList<Individuo> elite;
	private ArrayList<Individuo> plebe;
	private int generacionActual;
	private int generacionSolucion;
	
	/** Individuo **/
	//Mejor Individuo Absoluto
	private Individuo mejorIndividuoAbsoluto;
	private String mejorCromosomaAbsoluto;
	private StringBuilder mejorFenotipoAbsoluto;
	private double mejorFitnessAbsoluto;
	private double[] arrayMejorAbsoluto;
	
	//Mejor Individuo Generacion
	private Individuo[] mejorIndividuoGeneracion;
	private ArrayList<ArrayList<Integer>> mejorCromosomaGeneracion;
	private StringBuilder[] mejorFenotipoGeneracion;
	private double[] mejorFitnessGeneracion;
	
	//Peor Individuo
	private double peorFitnessAbsoluto;
	private double[] peorFitnessGeneracion;
	
	//Media Individuo
	private double mediaFitnessTotal;
	private double[] mediaFitnessGeneracion;
	
	//Presion Selecctiva
	private double presionSelecitiva;
	private double[] presionSelectivaArray;
	
	//Apocalipsis
	private boolean apocalipsis;
	private int contadorApocalipsis;
	private final int numGeneracionesApocalipsis = 50;
	
	//Pruebas
	private boolean plebeBool;
	
	//Desastre Natural
	private boolean desastre;
	
	/**************************** CONSTRUCTOR *******************************/
	public AlgoritmoGenetico(NGramas ngrama, boolean desastre, boolean plebeBool) {
		
		this.tamPoblacion = 120;
		this.numGeneraciones = 200;
		this.metodoSeleccion = new SeleccionTorneo();
		this.metodoCruce = new CrucePMX();
		this.porcCruce = 0.8;
		this.metodoMutacion = new MutacionIntercambio();
		this.porcMutacion = 0.7;
		this.porcElite = 0.02;
		
		/** Texto 2*/
		/*StringBuilder st = new StringBuilder("Oslv xesvh trc xhyhr ahtvx tqs slv otmkhvx zvslqkm osvmk sr mkpx esrmprhrm t rhg rtmpsr, esrehpyhc pr Jpzhvma, trc chcpetmhc ms mkh dvsdsxpmpsr mktm tjj ihr tvh evhtmhc hnltj." + 
				" Rsg gh tvh hrqtqhc pr t qvhtm epypj gtv, mhxmprq gkhmkhv mktm rtmpsr, sv tra rtmpsr, xs esrehpyhc trc xs chcpetmhc, etr jsrq hrclvh. Gh tvh ihm sr t qvhtm ztmmjh-ophjc so mktm gtv. Gh ktyh esih ms chcpetmh t dsvmpsr so mktm ophjc, tx t oprtj vhxmprq djteh osv mksxh gks khvh qtyh mkhpv jpyhx mktm mktm rtmpsr ipqkm jpyh. Pm px tjmsqhmkhv opmmprq trc dvsdhv mktm gh xksljc cs mkpx." + 
				" Zlm, pr t jtvqhv xhrxh, gh etr rsm chcpetmh -- gh etr rsm esrxhevtmh -- gh etr rsm ktjjsg -- mkpx qvslrc. Mkh zvtyh ihr, jpyprq trc chtc, gks xmvlqqjhc khvh, ktyh esrxhevtmhc pm, otv tzsyh slv dssv dsghv ms tcc sv chmvtem. Mkh gsvjc gpjj jpmmjh rsmh, rsv jsrq vhihizhv gktm gh xta khvh, zlm pm etr rhyhv osvqhm gktm mkha cpc khvh. Pm px osv lx mkh jpyprq, vtmkhv, ms zh chcpetmhc khvh ms mkh lroprpxkhc gsvu gkpek mkha gks oslqkm khvh ktyh mklx otv xs rszja tcytrehc. Pm px vtmkhv osv lx ms zh khvh chcpetmhc ms mkh qvhtm mtxu vhitprprq zhosvh lx -- mktm ovsi mkhxh ksrsvhc chtc gh mtuh prevhtxhc chysmpsr ms mktm etlxh osv gkpek mkha qtyh mkh jtxm oljj ihtxlvh so chysmpsr -- mktm gh khvh kpqkja vhxsjyh mktm mkhxh chtc xktjj rsm ktyh cphc pr ytpr -- mktm mkpx rtmpsr, lrchv Qsc, xktjj ktyh t rhg zpvmk so ovhhcsi -- trc mktm qsyhvrihrm so mkh dhsdjh, za mkh dhsdjh, osv mkh dhsdjh, xktjj rsm dhvpxk ovsi mkh htvmk.");
		*/
		/** Texto 3*/
		StringBuilder st = new StringBuilder("GNX NVMVNL XP G QUEVNLV NGSCV PM WYZGS GOXUEUXUVL US ONVGXUSC EULYGT, GYQUXPNH PN KVNMPNZUSC GNXUMGOXL. XWVLV GNXFPNBL VJKNVLL XWV GYXWPN'L UZGCUSGXUEV PN XVOWSUOGT LBUTT. GNX UL USXVSQVQ XP IV GKKNVOUGXVQ MPN UXL IVGYXH PN VZPXUPSGT KPFVN. US XWVUN ZPLX CVSVNGT MPNZ XWVLV GOXUEUXUVL USOTYQV XWV KNPQYOXUPS PM FPNBL PM GNX, XWV ONUXUOULZ PM GNX, XWV LXYQH PM XWV WULXPNH PM GNX, GSQ XWV GVLXWVXUO QULLVZUSGXUPS PM GNX." + 
				"GNX WGL WGQ G CNVGX SYZIVN PM QUMMVNVSX MYSOXUPSL XWNPYCWPYX UXL WULXPNH, ZGBUSC UXL KYNKPLV QUMMUOYTX XP GILXNGOX PN RYGSXUMH XP GSH LUSCTV OPSOVKX. XWUL QPVL SPX UZKTH XWGX XWV KYNKPLV PM GNX UL \"EGCYV\", IYX XWGX UX WGL WGQ ZGSH YSURYV, QUMMVNVSX NVGLPSL MPN IVUSC ONVGXVQ." + 
				"GNX OGS WGEV G KVNLPSGT MYSOXUPS, UX UL GS VJKNVLLUPS PM IGLUO WYZGS USLXUSOX MPN WGNZPSH, IGTGSOV, NWHXWZ. GNX GX XWUL TVEVT UL SPX GS GOXUPS PN GS PIDVOX, IYX GS USXVNSGT GKKNVOUGXUPS PM IGTGSOV GSQ WGNZPSH (IVGYXH), GSQ XWVNVMPNV GS GLKVOX PM IVUSC WYZGS IVHPSQ YXUTUXH. GNX GTLP KNPEUQVL G FGH XP VJKVNUVSOV PSV'L LVTM US NVTGXUPS XP XWV YSUEVNLV. XWUL VJKVNUVSOV ZGH PMXVS OPZV YSZPXUEGXVQ, GL PSV GKKNVOUGXVL GNX, ZYLUO PN KPVXNH." + 
				"PS XWV PXWVN WGSQ GNX ZGH WGEV G LPOUGT MYSOXUPS. GX UXL LUZKTVLX, GNX UL G MPNZ PM OPZZYSUOGXUPS. UX LVVBL XP VSXVNXGUS GSQ INUSC GIPYX G KGNXUOYTGN VZPXUPS PN ZPPQ, MPN XWV KYNKPLV PM NVTGJUSC PN VSXVNXGUSUSC XWV EUVFVN. GNX ZGH GTLP IV GS VJKNVLLUPS PM LPOUGT KNPXVLX, LVVBUSC XP RYVLXUPS GLKVOXL PM LPOUVXH." + 
				"XWV PTQVLX MPNZ PM GNX GNV EULYGT GNXL, FWUOW USOTYQV ONVGXUPS PM UZGCVL PN PIDVOXL US MUVTQL USOTYQUSC KGUSXUSC, LOYTKXYNV, KNUSXZGBUSC, KWPXPCNGKWH, GSQ PXWVN EULYGT ZVQUG. GNOWUXVOXYNV UL PMXVS USOTYQVQ GL PSV PM XWV EULYGT GNXL; WPFVEVN, TUBV XWV QVOPNGXUEV GNXL, UX USEPTEVL XWV ONVGXUPS PM PIDVOXL FWVNV XWV KNGOXUOGT OPSLUQVNGXUPSL PM YLV GNV VLLVSXUGT, US G FGH XWGX XWVH YLYGTTH GNV SPX US G KGUSXUSC, MPN VJGZKTV.");
		
		/** texto 4*/
		/*StringBuilder st = new StringBuilder("QY CIND TIVNMQIO QT ZBIOG MRW YQSW YZTMWTM QO UWFQHRWDQOG MRW MWKM, CIN EQVV GWM Z FIUW ZT Z DWEZDU MRZM EQVV TWDSW MI QBHDISW CIND GDZUW. " + 
				"MDC MI YQOW-MNOW MRW YQMOWTT YNOFMQIO TI MRZM MRW ZVGIDQMRB EIDJT YZTM. GIIU VNFJ.");
		StringBuilder st2 = new StringBuilder("qy cind tivnmqio qt zbiog mrw yqsw yztmwtm qo uwfqhrwdqog mrw mwkm, cin eqvv gwm z fiuw zt z dwezdu mrzm eqvv twdsw mi qbhdisw cind gdzuw. mdc mi yqow-mnow mrw yqmowtt ynofmqio ti mrzm mrw zvgidqmrb eidjt yztm. giiu vnfj. mrw iy zou mi antm vqjw iswd mrwb cwzd undqog lzfj cind gzbw lwyidw tfriiv riewswd mrdingr lwmewwo zdinou joie giswdobwom ydib bidw hwihvw hvzfw hnlvqf yqdtm yzbqvc yivvieqog noqswdtqmc finomc zvezct hivqfw fwomwd twdsqfw yivvieqog");
		*/
		StringBuilder st2 = new StringBuilder(st);
		
		this.ngramas = ngrama;
		this.claseTexto = new Texto(st, st2);
		this.desastre = desastre;
		this.plebeBool = plebeBool;
		
		this.inicializaVariables();
	}
	
	public AlgoritmoGenetico(int tamPoblacion, int numGeneraciones, Seleccion metodoSeleccion, 
			Cruce metodoCruce, double porcCruce, Mutacion metodoMutacion, double porcMutacion, double porcElite,
			NGramas ngramas, Texto claseTexto, boolean apocalipsis, boolean desastre) {
		
		this.tamPoblacion = tamPoblacion;
		this.numGeneraciones = numGeneraciones;
		this.metodoSeleccion = metodoSeleccion;
		this.metodoCruce = metodoCruce;
		this.porcCruce = porcCruce/100;
		this.metodoMutacion = metodoMutacion;
		this.porcMutacion = porcMutacion/100;
		this.porcElite = porcElite/100;
		
		this.ngramas = ngramas;
		this.claseTexto = claseTexto;
		
		this.apocalipsis = apocalipsis;
		this.desastre = desastre;
		if (claseTexto.getTextoOriginal().length() < 160) {
			
			this.porcCruce *= 2;
			this.porcMutacion *= 2;
		}
		this.plebeBool = true;
		
		this.inicializaVariables();
	}
	
	/********************************** METHODS **********************************/
	
	/**
	 * [ES] Método principal del algoritmo genético.
	 * Aquí se ejecutan:
	 * 	-Selección
	 * 	-Cruce
	 * 	-Mutación
	 * 	-Elitismo (si procede)
	 * 	-Desaste Natural (si procede)
	 *  -Apocalipsis (si procede)
	 *  
	 * [EN] This is the main method of genetic algorithm
	 * Here we execute:
	 * 	-Selection
	 * 	-Crossover
	 * 	-Mutation
	 * 	-Elitism (if applicable)
	 * 	-Natural Disaster (if applicable)
	 * 	-Apocalipsis (if applicable)
	 * 
	 */
	public void startAlgorithm() {
		
		inicializaPoblacion();
		while (this.generacionActual < this.numGeneraciones) {
			
			generaElite();
			this.poblacion = this.metodoSeleccion.seleccionar(poblacion);
			this.metodoCruce.cruza(poblacion, porcCruce);
			this.metodoMutacion.muta(poblacion, porcMutacion);
			
			if (desastre) {
				this.desastreNatural();
			}
			else {
				this.evaluaFitnessPoblacion();
			}
			reintroduceElite();
			this.evaluaFitnessPoblacion();
			
			/*System.out.println("Feno: " + this.mejorFenotipoGeneracion[generacionActual]);
			System.out.println("Fitness: " + this.mejorFitnessGeneracion[generacionActual]);
			System.out.println("Presion: " + this.presionSelectivaArray[generacionActual]);
			System.out.println("Media: " + this.mediaFitnessGeneracion[generacionActual]);
			System.out.println("Peor: " + this.peorFitnessGeneracion[generacionActual]);*/
			
			if (this.apocalipsis) {
				this.apocalipsis();
			}
			this.generacionActual++;
		}
		
		/*System.out.println("Cromosoma: " + this.mejorIndividuoAbsoluto.getCromosomaLetra());
		System.out.println("FitnessMaximo: " + this.mejorFitnessAbsoluto);
		System.out.println("PeorFitness: " + this.peorFitnessAbsoluto);
		this.mediaFitnessTotal = mediaFitnessTotal/2;
		this.mediaFitnessTotal = mediaFitnessTotal/numGeneraciones;
		System.out.println("MediaFitness: " + this.mediaFitnessTotal);
		System.out.println("NUMERO DE CRUCES: " + this.metodoCruce.getNumCruces());
		System.out.println("NUMERO DE MUTACIONES: " + this.metodoMutacion.getNumMutaciones());*/
		
	}
	
	/**
	 * [ES] Este metodo evalua el valor de aptitud/fitness de la población.
	 * Para ello primero se actualiza el valor de fitness de cada individuo.
	 * Una vez actualizado, buscamos el mejor individuo de toda la población.
	 * Y por último guardamos el valor del mejor individuo en los respectivos 
	 * atributos de clase. (MejorIndividuoGeneracion, MejorIndividuoAbsoluto, ...)
	 * 
	 * [EN] This method evaluates population's fitness value.
	 * It updates the fitness value of each individual.
	 * Once updated, we search for the best individual in all the population.
	 * Y last, we store the value of the best individual in the respective class's
	 * attributes. (MejorIndividuoGeneracion, MejorIndividuoAbsoluto, ...)
	 * 
	 */
	private void evaluaFitnessPoblacion() {
		
		double mejorGeneracion = -Double.MAX_VALUE;
		Individuo mejorIndividuo = null;
		double peorGeneracion = Double.MAX_VALUE;
		double mediaGeneracion = 0;
		
		double fitness;
		for (Individuo ind : poblacion) {
			
			fitness = ind.calculateFitness();
			
			//Mejor Generacion
			if (fitness > mejorGeneracion) {
				mejorGeneracion = fitness;
				mejorIndividuo = ind;
				
			}
			//Peor Generacion
			if (fitness < peorGeneracion) {
				peorGeneracion = fitness;
			}
			
			mediaGeneracion += fitness;
		}
		
		this.mejorFitnessGeneracion[generacionActual] = mejorGeneracion;
		this.mejorCromosomaGeneracion.add(new ArrayList<Integer>(mejorIndividuo.getCromosoma()));
		this.mejorFenotipoGeneracion[generacionActual] = new StringBuilder(mejorIndividuo.getFenotipe());
		
		this.mejorIndividuoGeneracion[generacionActual] = new Individuo(claseTexto, ngramas, mejorIndividuo.getCromosoma());
		
		this.mediaFitnessGeneracion[generacionActual] = mediaGeneracion/tamPoblacion;
		this.mediaFitnessTotal += this.mediaFitnessGeneracion[generacionActual];
		
		this.presionSelecitiva = mejorGeneracion/(mediaGeneracion/tamPoblacion);
		this.presionSelectivaArray[generacionActual] = mejorGeneracion/(mediaGeneracion/tamPoblacion);
		
		if (this.generacionActual == 0 || 
				mejorGeneracion > this.arrayMejorAbsoluto[generacionActual - 1]) {
			this.arrayMejorAbsoluto[generacionActual] = mejorGeneracion;

			if(this.generacionActual != 0 && (arrayMejorAbsoluto[this.generacionActual] - arrayMejorAbsoluto[this.generacionActual -1 ]) > 1)
					this.generacionSolucion = this.generacionActual;

			this.mejorFitnessAbsoluto = mejorGeneracion;
			
			this.mejorCromosomaAbsoluto = mejorIndividuo.getCromosomaLetra().toString();
			this.mejorFenotipoAbsoluto = new StringBuilder(mejorIndividuo.getFenotipe());
			this.mejorFitnessAbsoluto = mejorGeneracion;
			
			this.mejorIndividuoAbsoluto = new Individuo(claseTexto, ngramas, mejorIndividuo.getCromosoma());
		}
		else {
			
			this.arrayMejorAbsoluto[generacionActual] = this.arrayMejorAbsoluto[generacionActual - 1];
		}
		
		if (this.generacionActual == 0 || peorGeneracion < this.peorFitnessAbsoluto) {
			this.peorFitnessAbsoluto = peorGeneracion;
		}
		this.peorFitnessGeneracion[generacionActual] = peorGeneracion;
	}
	
	/**
	 * [ES] Desastre natural reinicia individuos que estén consecutivos y
	 * que tengan valores cercanos de fitness.
	 * Para seleccionar los individuos, se 
	 * 
	 */
	private void desastreNatural() {
		
		ArrayList<Individuo> array = new ArrayList<Individuo>(5);
		double menorFitness = 0;
		double mayorFitness = 0;
		double fitness;
		for (Individuo ind : this.poblacion) {
			
			fitness = ind.calculateFitness();
			
			if (fitness >= (menorFitness - (15*this.presionSelecitiva+Math.log(generacionActual))) && fitness <= (mayorFitness + (15*this.presionSelecitiva+Math.log(generacionActual)))) {
				
				array.add(ind);
				
				if (fitness < menorFitness) {
					
					menorFitness = fitness;
				}
				if (fitness > mayorFitness) {
					mayorFitness = fitness;
				}
			}
			else {
				
				array.clear();
				menorFitness = fitness;
				mayorFitness = fitness;
			}
			
			if (array.size() == 4) {
				
				Random rand = new Random();
				for (int i = 0; i < 4; i++) {
					
					if (rand.nextBoolean()) {
						
						array.get(i).restartCromosome();
					}
				}
			}
		}
	}
	
	private void apocalipsis() {
		
		if (this.generacionActual == 0 || this.mejorFitnessGeneracion[generacionActual] == this.mejorFitnessGeneracion[generacionActual - 1]) {
			
			this.contadorApocalipsis += 1;
		}
		else {
			
			this.contadorApocalipsis = 0;
		}
		
		if (this.contadorApocalipsis != 0 && this.contadorApocalipsis%this.numGeneracionesApocalipsis == 0) {
			
			System.out.println("\n APOCALIPSIS \n");
			
			Collections.sort(this.poblacion, new Comparator<Individuo>() {

				@Override
				public int compare(Individuo o1, Individuo o2) {
					
					return Double.compare(o2.getFitness(), o1.getFitness());
				}
			});
			
			int contador = 0;
			for (int i = 0; i < this.tamPoblacion; i++) {
				
				if (i < this.tamPoblacion*this.porcElite*2) {
					
					this.poblacion.get(i).restartCromosome();
				}
				else {
					
					if (contador < 5) {
						
						this.poblacion.get(i).restartCromosome();
						contador++;
					}
					else {
						
						contador = 0;
					}
				}
			}
			
			this.contadorApocalipsis = 0;
		}
	}
	
	/**
	 * [ES] Función que genera la élite de la población.
	 * Recorre toda la población y se queda con los mejores individuos.
	 * Aquí hemos agregado nuestra propia creación que hemos llamados "La Plebe"
	 * Es básicamente lo mismo que 
	 * 
	 * 
	 */
	private void generaElite() {
		
		int numElite = (int) Math.ceil(this.tamPoblacion*this.porcElite);
		int numPlebe = numElite*3;
		
		this.elite = new ArrayList<Individuo>(numElite);
		this.plebe = new ArrayList<Individuo>(numPlebe);
		
		ArrayList<Individuo> poblacionAuxiliar = new ArrayList<Individuo>(this.poblacion);
		Collections.sort(poblacionAuxiliar, new Comparator<Individuo>() {

			@Override
			public int compare(Individuo o1, Individuo o2) {
				
				return Double.compare(o2.getFitness(), o1.getFitness());
			}
		});
		
		for (int i = 0; i < numElite; i++) {
			
			elite.add(poblacionAuxiliar.get(i));
		}
		for (int i = 0; i < numPlebe; i++) {
			
			plebe.add(poblacionAuxiliar.get(tamPoblacion - i - 1));
		}
	}
	
	public void reintroduceElite() {
		
		int numElite = (int) Math.ceil(this.tamPoblacion*this.porcElite);
		HashSet<Integer> indexAdded = new HashSet<Integer>(numElite);
		int numAdded = 0;
		int index;
		Random rand = new Random();
		while (numAdded < numElite && indexAdded.size() < this.tamPoblacion) {
			
			index = rand.nextInt(this.tamPoblacion);
			while (indexAdded.contains(index)) {
				
				index = rand.nextInt(this.tamPoblacion);
			}
			
			indexAdded.add(index);
			
			Individuo eli = this.elite.get(numAdded);
			double fitnessEli = eli.calculateFitness();
			Individuo ple = this.poblacion.get(index);
			double fitnessPle = ple.getFitness();
			if (fitnessEli > fitnessPle) {
				
				ple.setCromosoma(eli.getCromosoma());
				ple.calculateFitness();
				
				numAdded++;
			}
		}
		
		for (Individuo indPlebe : this.plebe) {
			
			index = rand.nextInt(this.tamPoblacion);
			while (indexAdded.contains(index)) {
				
				index = rand.nextInt(this.tamPoblacion);
			}
			
			indexAdded.add(index);
			
			double fitnessPlebe = indPlebe.getFitness();
			Individuo indComparado = this.poblacion.get(index);
			double fitnessComparado = indComparado.getFitness();
			
			if (fitnessPlebe > fitnessComparado && this.plebeBool) {
				
				indComparado.setCromosoma(indPlebe.getCromosoma());
				indComparado.calculateFitness();
			}
		}
	}
	
	/***************************** AUXILIAR METHODS ********************************/
	
	/**
	 * [ES] Esta función auxiliar inicializa los atributos de la clase.
	 * [EN] This auxiliary function initialize the class's attributes.
	 * 
	 */
	private void inicializaVariables() {
		
		arrayMejorAbsoluto = new double[numGeneraciones];
		
		mejorIndividuoGeneracion = new Individuo[numGeneraciones];
		mejorCromosomaGeneracion = new ArrayList<ArrayList<Integer>>(numGeneraciones);
		mejorFenotipoGeneracion = new StringBuilder[numGeneraciones];
		mejorFitnessGeneracion = new double[numGeneraciones];
		
		peorFitnessGeneracion = new double[numGeneraciones];
		
		mediaFitnessGeneracion = new double[numGeneraciones];
		
		this.presionSelectivaArray = new double[numGeneraciones];
	}
	
	/**
	 * [ES] Inicializa la población, creando tantos nuevos individuos como el
	 * tamaño de la población.
	 * 
	 * [EN] Initializes the population, creating as many new individual as
	 * the size of the population.
	 * 
	 */
	private void inicializaPoblacion() {
		
		this.poblacion = new ArrayList<Individuo>(tamPoblacion);
		for (int i = 0; i < tamPoblacion; i++) {
			
			Individuo ind = new Individuo(claseTexto, ngramas);
			poblacion.add(ind);
		}
	}
	
	/**************************** GETTERS & SETTERS ********************************/

	public int getGeneracionActual() {
		return generacionActual;
	}

	public Individuo getMejorIndividuoAbsoluto() {
		return mejorIndividuoAbsoluto;
	}

	public String getMejorCromosomaAbsoluto() {
		return mejorCromosomaAbsoluto;
	}

	public StringBuilder getMejorFenotipoAbsoluto() {
		return mejorFenotipoAbsoluto;
	}

	public double getMejorFitnessAbsoluto() {
		return mejorFitnessAbsoluto;
	}

	public double[] getArrayMejorAbsoluto() {
		return arrayMejorAbsoluto;
	}

	public Individuo[] getMejorIndividuoGeneracion() {
		return mejorIndividuoGeneracion;
	}

	public ArrayList<ArrayList<Integer>> getMejorCromosomaGeneracion() {
		return mejorCromosomaGeneracion;
	}

	public StringBuilder[] getMejorFenotipoGeneracion() {
		return mejorFenotipoGeneracion;
	}

	public double[] getMejorFitnessGeneracion() {
		return mejorFitnessGeneracion;
	}

	public double getPeorFitnessAbsoluto() {
		return peorFitnessAbsoluto;
	}

	public double[] getPeorFitnessGeneracion() {
		return peorFitnessGeneracion;
	}

	public double getMediaFitnessTotal() {
		return mediaFitnessTotal;
	}

	public double[] getMediaFitnessGeneracion() {
		return mediaFitnessGeneracion;
	}
	
	public double[] getPresionSelectivaArray() {
		return presionSelectivaArray;
	}
	
	public String getMejorGeneracion() {
		return "" + this.generacionSolucion;
	}

}
