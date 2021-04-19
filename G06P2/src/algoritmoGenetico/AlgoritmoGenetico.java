package algoritmoGenetico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;

import algoritmoGenetico.cruces.Cruce;
import algoritmoGenetico.cruces.CruceCO;
import algoritmoGenetico.cruces.CruceCX;
import algoritmoGenetico.cruces.CruceERX;
import algoritmoGenetico.cruces.CruceOXPP;
import algoritmoGenetico.cruces.CrucePMX;
import algoritmoGenetico.cruces.CrucePorOrden;
import algoritmoGenetico.individuos.Individuo;
import algoritmoGenetico.individuos.NGramas;
import algoritmoGenetico.individuos.Texto;
import algoritmoGenetico.mutaciones.Mutacion;
import algoritmoGenetico.mutaciones.MutacionHeuristica;
import algoritmoGenetico.mutaciones.MutacionInsersion;
import algoritmoGenetico.mutaciones.MutacionIntercambio;
import algoritmoGenetico.mutaciones.MutacionInversion;
import algoritmoGenetico.seleccion.Seleccion;
import algoritmoGenetico.seleccion.SeleccionTorneo;
import algoritmoGenetico.seleccion.SeleccionTorneoProbabilistico;

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
	private StringBuilder texto;
	
	private ArrayList<Individuo> poblacion;
	private ArrayList<Individuo> elite;
	private HashMap<Individuo, Double> plebe;
	private int generacionActual;
	
	private Individuo[] mejorIndividuoGeneracion;
	private Individuo mejorIndividuoAbsoluto;
	private ArrayList<ArrayList<Character>> mejorFenotipoGeneracion;
	private ArrayList<Character> mejorFenotipoAbsoluto;
	private double[] mejorFitnessGeneracion;
	private double mejorFitnessAbsoluto;
	private double[] peorFitnessGeneracion;
	private double peorFitnessAbsoluto;
	private double[] mediaFitnessGeneracion;
	private double mediaFitnessTotal;
	
	/**************************** CONSTRUCTOR *******************************/
	public AlgoritmoGenetico() {
		
		this.tamPoblacion = 100;
		this.numGeneraciones = 100;
		this.metodoSeleccion = new SeleccionTorneoProbabilistico();
		this.metodoCruce = new CruceOXPP();
		this.porcCruce = 0.8;
		this.metodoMutacion = new MutacionIntercambio();
		this.porcMutacion = 0.8;
		this.porcElite = 0.01;
		
		//StringBuilder st = new StringBuilder("Gtunfwveqw: Mfdue fw gtd, mfdue fw zrtpr, twb xtue fw enr Wtefqwtx Xrtvor Rtue.");
		//StringBuilder st2 = new StringBuilder("Gtunfwveqw: Mfdue fw gtd, mfdue fw zrtpr, twb xtue fw enr Wtefqwtx Xrtvor Rtue. Gtunfwveqw: Mfdue fw gtd, mfdue fw zrtpr, twb xtue fw enr Wtefqwtx Xrtvor Rtue.");
		
		/*StringBuilder st = new StringBuilder("Oslv xesvh trc xhyhr ahtvx tqs slv otmkhvx zvslqkm osvmk sr mkpx esrmprhrm t rhg rtmpsr, esrehpyhc pr Jpzhvma, trc chcpetmhc ms mkh dvsdsxpmpsr mktm tjj ihr tvh evhtmhc hnltj. " +  
				" Rsg gh tvh hrqtqhc pr t qvhtm epypj gtv, mhxmprq gkhmkhv mktm rtmpsr, sv tra rtmpsr, xs esrehpyhc trc xs chcpetmhc, etr jsrq hrclvh. Gh tvh ihm sr t qvhtm ztmmjh-ophjc so mktm gtv. Gh ktyh esih ms chcpetmh t dsvmpsr so mktm ophjc, tx t oprtj vhxmprq djteh osv mksxh gks khvh qtyh mkhpv jpyhx mktm mktm rtmpsr ipqkm jpyh. Pm px tjmsqhmkhv opmmprq trc dvsdhv mktm gh xksljc cs mkpx. " +  
				" Zlm, pr t jtvqhv xhrxh, gh etr rsm chcpetmh -- gh etr rsm esrxhevtmh -- gh etr rsm ktjjsg -- mkpx qvslrc. Mkh zvtyh ihr, jpyprq trc chtc, gks xmvlqqjhc khvh, ktyh esrxhevtmhc pm, otv tzsyh slv dssv dsghv ms tcc sv chmvtem. Mkh gsvjc gpjj jpmmjh rsmh, rsv jsrq vhihizhv gktm gh xta khvh, zlm pm etr rhyhv osvqhm gktm mkha cpc khvh. Pm px osv lx mkh jpyprq, vtmkhv, ms zh chcpetmhc khvh ms mkh lroprpxkhc gsvu gkpek mkha gks oslqkm khvh ktyh mklx otv xs rszja tcytrehc. Pm px vtmkhv osv lx ms zh khvh chcpetmhc ms mkh qvhtm mtxu vhitprprq zhosvh lx -- mktm ovsi mkhxh ksrsvhc chtc gh mtuh prevhtxhc chysmpsr ms mktm etlxh osv gkpek mkha qtyh mkh jtxm oljj ihtxlvh so chysmpsr -- mktm gh khvh kpqkja vhxsjyh mktm mkhxh chtc xktjj rsm ktyh cphc pr ytpr -- mktm mkpx rtmpsr, lrchv Qsc, xktjj ktyh t rhg zpvmk so ovhhcsi -- trc mktm qsyhvrihrm so mkh dhsdjh, za mkh dhsdjh, osv mkh dhsdjh, xktjj rsm dhvpxk ovsi mkh htvmk.");
		StringBuilder st2 = new StringBuilder("Oslv xesvh trc xhyhr ahtvx tqs slv otmkhvx zvslqkm osvmk sr mkpx esrmprhrm t rhg rtmpsr, esrehpyhc pr Jpzhvma, trc chcpetmhc ms mkh dvsdsxpmpsr mktm tjj ihr tvh evhtmhc hnltj. " + 
				" Rsg gh tvh hrqtqhc pr t qvhtm epypj gtv, mhxmprq gkhmkhv mktm rtmpsr, sv tra rtmpsr, xs esrehpyhc trc xs chcpetmhc, etr jsrq hrclvh. Gh tvh ihm sr t qvhtm ztmmjh-ophjc so mktm gtv. Gh ktyh esih ms chcpetmh t dsvmpsr so mktm ophjc, tx t oprtj vhxmprq djteh osv mksxh gks khvh qtyh mkhpv jpyhx mktm mktm rtmpsr ipqkm jpyh. Pm px tjmsqhmkhv opmmprq trc dvsdhv mktm gh xksljc cs mkpx. " + 
				" Zlm, pr t jtvqhv xhrxh, gh etr rsm chcpetmh -- gh etr rsm esrxhevtmh -- gh etr rsm ktjjsg -- mkpx qvslrc. Mkh zvtyh ihr, jpyprq trc chtc, gks xmvlqqjhc khvh, ktyh esrxhevtmhc pm, otv tzsyh slv dssv dsghv ms tcc sv chmvtem. Mkh gsvjc gpjj jpmmjh rsmh, rsv jsrq vhihizhv gktm gh xta khvh, zlm pm etr rhyhv osvqhm gktm mkha cpc khvh. Pm px osv lx mkh jpyprq, vtmkhv, ms zh chcpetmhc khvh ms mkh lroprpxkhc gsvu gkpek mkha gks oslqkm khvh ktyh mklx otv xs rszja tcytrehc. Pm px vtmkhv osv lx ms zh khvh chcpetmhc ms mkh qvhtm mtxu vhitprprq zhosvh lx -- mktm ovsi mkhxh ksrsvhc chtc gh mtuh prevhtxhc chysmpsr ms mktm etlxh osv gkpek mkha qtyh mkh jtxm oljj ihtxlvh so chysmpsr -- mktm gh khvh kpqkja vhxsjyh mktm mkhxh chtc xktjj rsm ktyh cphc pr ytpr -- mktm mkpx rtmpsr, lrchv Qsc, xktjj ktyh t rhg zpvmk so ovhhcsi -- trc mktm qsyhvrihrm so mkh dhsdjh, za mkh dhsdjh, osv mkh dhsdjh, xktjj rsm dhvpxk ovsi mkh htvmk." +
				"Oslv xesvh trc xhyhr ahtvx tqs slv otmkhvx zvslqkm osvmk sr mkpx esrmprhrm t rhg rtmpsr, esrehpyhc pr Jpzhvma, trc chcpetmhc ms mkh dvsdsxpmpsr mktm tjj ihr tvh evhtmhc hnltj. " +
				"Rsg gh tvh hrqtqhc pr t qvhtm epypj gtv, mhxmprq gkhmkhv mktm rtmpsr, sv tra rtmpsr, xs esrehpyhc trc xs chcpetmhc, etr jsrq hrclvh. Gh tvh ihm sr t qvhtm ztmmjh-ophjc so mktm gtv. Gh ktyh esih ms chcpetmh t dsvmpsr so mktm ophjc, tx t oprtj vhxmprq djteh osv mksxh gks khvh qtyh mkhpv jpyhx mktm mktm rtmpsr ipqkm jpyh. Pm px tjmsqhmkhv opmmprq trc dvsdhv mktm gh xksljc cs mkpx. " +
				"Zlm, pr t jtvqhv xhrxh, gh etr rsm chcpetmh -- gh etr rsm esrxhevtmh -- gh etr rsm ktjjsg -- mkpx qvslrc. Mkh zvtyh ihr, jpyprq trc chtc, gks xmvlqqjhc khvh, ktyh esrxhevtmhc pm, otv tzsyh slv dssv dsghv ms tcc sv chmvtem. Mkh gsvjc gpjj jpmmjh rsmh, rsv jsrq vhihizhv gktm gh xta khvh, zlm pm etr rhyhv osvqhm gktm mkha cpc khvh. Pm px osv lx mkh jpyprq, vtmkhv, ms zh chcpetmhc khvh ms mkh lroprpxkhc gsvu gkpek mkha gks oslqkm khvh ktyh mklx otv xs rszja tcytrehc. Pm px vtmkhv osv lx ms zh khvh chcpetmhc ms mkh qvhtm mtxu vhitprprq zhosvh lx -- mktm ovsi mkhxh ksrsvhc chtc gh mtuh prevhtxhc chysmpsr ms mktm etlxh osv gkpek mkha qtyh mkh jtxm oljj ihtxlvh so chysmpsr -- mktm gh khvh kpqkja vhxsjyh mktm mkhxh chtc xktjj rsm ktyh cphc pr ytpr -- mktm mkpx rtmpsr, lrchv Qsc, xktjj ktyh t rhg zpvmk so ovhhcsi -- trc mktm qsyhvrihrm so mkh dhsdjh, za mkh dhsdjh, osv mkh dhsdjh, xktjj rsm dhvpxk ovsi mkh htvmk.");
		*/
		
		/*StringBuilder st = new StringBuilder("GNX NVMVNL XP G QUEVNLV NGSCV PM WYZGS GOXUEUXUVL US ONVGXUSC EULYGT, GYQUXPNH PN KVNMPNZUSC GNXUMGOXL. XWVLV GNXFPNBL VJKNVLL XWV GYXWPN'L UZGCUSGXUEV PN XVOWSUOGT LBUTT. GNX UL USXVSQVQ XP IV GKKNVOUGXVQ MPN UXL IVGYXH PN VZPXUPSGT KPFVN. US XWVUN ZPLX CVSVNGT MPNZ XWVLV GOXUEUXUVL USOTYQV XWV KNPQYOXUPS PM FPNBL PM GNX, XWV ONUXUOULZ PM GNX, XWV LXYQH PM XWV WULXPNH PM GNX, GSQ XWV GVLXWVXUO QULLVZUSGXUPS PM GNX. " + 
				"GNX WGL WGQ G CNVGX SYZIVN PM QUMMVNVSX MYSOXUPSL XWNPYCWPYX UXL WULXPNH, ZGBUSC UXL KYNKPLV QUMMUOYTX XP GILXNGOX PN RYGSXUMH XP GSH LUSCTV OPSOVKX. XWUL QPVL SPX UZKTH XWGX XWV KYNKPLV PM GNX UL \"EGCYV\", IYX XWGX UX WGL WGQ ZGSH YSURYV, QUMMVNVSX NVGLPSL MPN IVUSC ONVGXVQ. " + 
				"GNX OGS WGEV G KVNLPSGT MYSOXUPS, UX UL GS VJKNVLLUPS PM IGLUO WYZGS USLXUSOX MPN WGNZPSH, IGTGSOV, NWHXWZ. GNX GX XWUL TVEVT UL SPX GS GOXUPS PN GS PIDVOX, IYX GS USXVNSGT GKKNVOUGXUPS PM IGTGSOV GSQ WGNZPSH (IVGYXH), GSQ XWVNVMPNV GS GLKVOX PM IVUSC WYZGS IVHPSQ YXUTUXH. GNX GTLP KNPEUQVL G FGH XP VJKVNUVSOV PSV'L LVTM US NVTGXUPS XP XWV YSUEVNLV. XWUL VJKVNUVSOV ZGH PMXVS OPZV YSZPXUEGXVQ, GL PSV GKKNVOUGXVL GNX, ZYLUO PN KPVXNH. " + 
				"PS XWV PXWVN WGSQ GNX ZGH WGEV G LPOUGT MYSOXUPS. GX UXL LUZKTVLX, GNX UL G MPNZ PM OPZZYSUOGXUPS. UX LVVBL XP VSXVNXGUS GSQ INUSC GIPYX G KGNXUOYTGN VZPXUPS PN ZPPQ, MPN XWV KYNKPLV PM NVTGJUSC PN VSXVNXGUSUSC XWV EUVFVN. GNX ZGH GTLP IV GS VJKNVLLUPS PM LPOUGT KNPXVLX, LVVBUSC XP RYVLXUPS GLKVOXL PM LPOUVXH. " + 
				"XWV PTQVLX MPNZ PM GNX GNV EULYGT GNXL, FWUOW USOTYQV ONVGXUPS PM UZGCVL PN PIDVOXL US MUVTQL USOTYQUSC KGUSXUSC, LOYTKXYNV, KNUSXZGBUSC, KWPXPCNGKWH, GSQ PXWVN EULYGT ZVQUG. GNOWUXVOXYNV UL PMXVS USOTYQVQ GL PSV PM XWV EULYGT GNXL; WPFVEVN, TUBV XWV QVOPNGXUEV GNXL, UX USEPTEVL XWV ONVGXUPS PM PIDVOXL FWVNV XWV KNGOXUOGT OPSLUQVNGXUPSL PM YLV GNV VLLVSXUGT, US G FGH XWGX XWVH YLYGTTH GNV SPX US G KGUSXUSC, MPN VJGZKTV. ");
		*/
		
		//StringBuilder st = new StringBuilder("QY CIND TIVNMQIO QT ZBIOG MRW YQSW YZTMWTM QO UWFQHRWDQOG MRW MWKM, CIN EQVV GWM Z FIUW ZT Z DWEZDU MRZM EQVV TWDSW MI QBHDISW CIND GDZUW. MDC MI YQOW-MNOW MRW YQMOWTT YNOFMQIO TI MRZM MRW ZVGIDQMRB EIDJT YZTM. GIIU VNFJ.");
		
		StringBuilder st = new StringBuilder("Eqa ycwe aqqt aqcit v aqqtwecwb wecwb zn v aqqtwecwb wqcit wecwb aqqt?  Zr aqcit wecwb vii rep aqqt revr v aqqtwecwb wqcit, zn v aqqtwecwb wqcit wecwb aqqt.");
		StringBuilder st2 = new StringBuilder();
		st2.append(st);
		st2.append(" ");
		st2.append(st);
		//StringBuilder st2 = new StringBuilder("qy cind tivnmqio qt zbiog mrw yqsw yztmwtm qo uwfqhrwdqog mrw mwkm, cin eqvv gwm z fiuw zt z dwezdu mrzm eqvv twdsw mi qbhdisw cind gdzuw. mdc mi yqow-mnow mrw yqmowtt ynofmqio ti mrzm mrw zvgidqmrb eidjt yztm. giiu vnfj. mrw iy zou mi antm vqjw iswd mrwb cwzd undqog lzfj cind gzbw lwyidw tfriiv riewswd mrdingr lwmewwo zdinou joie giswdobwom ydib bidw hwihvw hvzfw hnlvqf yqdtm yzbqvc yivvieqog");
		this.ngramas = new NGramas();
		this.ngramas.loadHashs();
		this.claseTexto = new Texto(st, st2);
		
	}
	
	public AlgoritmoGenetico(int tamPoblacion, int numGeneraciones, Seleccion metodoSeleccion, 
			Cruce metodoCruce, double porcCruce, Mutacion metodoMutacion, double porcMutacion, double porcElite) {
		
		this.tamPoblacion = tamPoblacion;
		this.numGeneraciones = numGeneraciones;
		this.metodoSeleccion = metodoSeleccion;
		this.metodoCruce = metodoCruce;
		this.porcCruce = porcCruce;
		this.metodoMutacion = metodoMutacion;
		this.porcMutacion = porcMutacion;
		this.porcElite = porcElite;
	}
	
	/***************************** METHODS ********************************/
	
	public void startAlgorithm() {
		
		inicializaPoblacion();
		
		while (this.generacionActual < this.numGeneraciones) {
			
			generaElite();
			//System.out.println("Antes de seleccionar: ");
			//imprimePoblacion();
			this.poblacion = this.metodoSeleccion.seleccionar(poblacion);
			//System.out.println("Seleccion-Cruce: ");
			//imprimePoblacion();
			this.metodoCruce.cruza(poblacion, porcCruce);
			//System.out.println("Cruce-Mutacion: ");
			//imprimePoblacion();
			this.metodoMutacion.muta(poblacion, porcMutacion);
			//System.out.println("Despues de mutacion: ");
			//imprimePoblacion();
			
			this.desastreNatural();
			//this.evaluaFitnessPoblacion();
			reintroduceElite();
			this.evaluaFitnessPoblacion();
			imprimePoblacion();
			this.generacionActual++;
		}
		
		imprimeMejor();
	}
	
	private void inicializaPoblacion() {
		
		this.poblacion = new ArrayList<Individuo>(tamPoblacion);
		for (int i = 0; i < tamPoblacion; i++) {
			
			Individuo ind = new Individuo(claseTexto, ngramas);
			poblacion.add(ind);
		}
	}
	
	private void imprimePoblacion() {
		
		double maxFitness = 0;
		int index = 0;
		
		for (Individuo ind : this.poblacion) {
			
			/*System.out.println(ind.getFenotipe());
			System.out.println(ind.getFitness());*/
			double fitness = ind.getFitness();
			if(fitness > maxFitness) {
				
				maxFitness = fitness;
				index = poblacion.indexOf(ind);
			}
		}
		
		System.out.println(poblacion.get(index).getFenotipe());
		System.out.println(poblacion.get(index).getFitness());
	}
	
	private void imprimeMejor() {
		
		double maxFitness = 0;
		int index = 0;
		
		for (Individuo ind : this.poblacion) {
			
			/*System.out.println(ind.getFenotipe());
			System.out.println(ind.getFitness());*/
			double fitness = ind.getFitness();
			if(fitness > maxFitness) {
				
				maxFitness = fitness;
				index = poblacion.indexOf(ind);
			}
		}
		
		System.out.println(poblacion.get(index).getFenotipe());
		System.out.println(poblacion.get(index).getFitness());
		System.out.println(poblacion.get(index).getCromosoma());
	}
	
	private void evaluaFitnessPoblacion() {
		
		for (Individuo ind : poblacion) {
			
			ind.calculateFitness();
		}
	}
	
	private void desastreNatural() {
		
		ArrayList<Individuo> array = new ArrayList<Individuo>(5);
		double menorFitness = 0;
		double mayorFitness = 0;
		double fitness;
		for (Individuo ind : this.poblacion) {
			
			fitness = ind.calculateFitness();
			
			if (fitness >= (menorFitness - (20+Math.log(generacionActual))) && fitness <= (mayorFitness + (20+Math.log(generacionActual)))) {
				
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
						System.out.println("RESTART");
					}
				}
			}
		}
	}
	
	public void generaElite() {
		
		int numElite = (int) Math.ceil(this.tamPoblacion*this.porcElite);
		int numPlebe = numElite*2;
		
		this.elite = new ArrayList<Individuo>(numElite);
		this.plebe = new HashMap<Individuo, Double>(numPlebe);
		
		ArrayList<Individuo> poblacionAuxiliar = new ArrayList<Individuo>(this.poblacion);
		Collections.sort(poblacionAuxiliar, new Comparator<Individuo>() {

			@Override
			public int compare(Individuo o1, Individuo o2) {
				
				return Double.compare(o2.getFitness(), o1.getFitness());
			}
		});
		
		for (int i = 0; i < numElite; i++) {
			
			elite.add(poblacionAuxiliar.get(i));
			//System.out.println(poblacionAuxiliar.get(i).getCromosoma());
			//System.out.println("F1 :" + poblacionAuxiliar.get(i).calculateFitness());
			//System.out.println("F2 :" + poblacionAuxiliar.get(i).calculateFitness());
			//System.out.println("F3 :" + poblacionAuxiliar.get(i).calculateFitness());
		}
		for (int i = 0; i < numPlebe; i++) {
			
			//Individuo ind = poblacionAuxiliar.get(tamPoblacion/2 - i - 1);
			Individuo ind = poblacionAuxiliar.get(i);
			plebe.put(ind, ind.getFitness());
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
			//System.out.println(eli.getCromosoma());
			//double fitnessEli = eli.getFitness();
			double fitnessEli = eli.calculateFitness();
			Individuo ple = this.poblacion.get(index);
			double fitnessPle = ple.getFitness();
			if (fitnessEli > fitnessPle) {
				
				ple.setCromosoma(eli.getCromosoma());
				ple.calculateFitness();
				
				numAdded++;
			}
		}
		
		for (Map.Entry<Individuo, Double> ind : this.plebe.entrySet()) {
			
			double oldFitness = ind.getValue();
			double newFitness = ind.getKey().getFitness();
			//System.out.println(ind);
			//System.out.println(ind.getKey());
			//System.out.println(ind.getKey().getCromosoma());
			
			for (Individuo indP : this.poblacion) {
				
				if (indP.equals(ind.getKey())) {
					System.out.println("!!!");
				}
			}
			if (this.poblacion.contains(ind.getKey())) {
				System.out.println("SI");
			}
			if (newFitness < oldFitness) {
				
				Individuo newInd = ind.getKey();
				newInd = new Individuo(claseTexto, ngramas);
			}
		}
	}
	/**************************** GET & SET ********************************/

}
