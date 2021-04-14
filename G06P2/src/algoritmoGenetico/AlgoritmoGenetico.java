package algoritmoGenetico;

import java.util.ArrayList;

import algoritmoGenetico.cruces.Cruce;
import algoritmoGenetico.cruces.CrucePorOrden;
import algoritmoGenetico.individuos.Individuo;
import algoritmoGenetico.individuos.NGramas;
import algoritmoGenetico.individuos.Texto;
import algoritmoGenetico.mutaciones.Mutacion;
import algoritmoGenetico.mutaciones.MutacionIntercambio;
import algoritmoGenetico.seleccion.Seleccion;
import algoritmoGenetico.seleccion.SeleccionTorneo;

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
		
		this.tamPoblacion = 700;
		this.numGeneraciones = 600;
		this.metodoSeleccion = new SeleccionTorneo();
		this.metodoCruce = new CrucePorOrden();
		this.porcCruce = 0.8;
		this.metodoMutacion = new MutacionIntercambio();
		this.porcMutacion = 0.5;
		
		StringBuilder st = new StringBuilder("Gtunfwveqw: Mfdue fw gtd, mfdue fw zrtpr, twb xtue fw enr Wtefqwtx Xrtvor Rtue.");
		StringBuilder st2 = new StringBuilder("Gtunfwveqw: Mfdue fw gtd, mfdue fw zrtpr, twb xtue fw enr Wtefqwtx Xrtvor Rtue. Gtunfwveqw: Mfdue fw gtd, mfdue fw zrtpr, twb xtue fw enr Wtefqwtx Xrtvor Rtue.");
		
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
	
	public void startAlgorithm() {
		
		inicializaPoblacion();
		
		while (this.generacionActual < this.numGeneraciones) {
			
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
			imprimePoblacion();
			
			this.evaluaFitnessPoblacion();
			this.generacionActual++;
		}
	}
	
	private void inicializaPoblacion() {
		
		this.poblacion = new ArrayList<Individuo>(tamPoblacion);
		for (int i = 0; i < tamPoblacion; i++) {
			
			Individuo ind = new Individuo(claseTexto, ngramas);
			poblacion.add(ind);
		}
	}
	
	private void evaluaFitnessPoblacion() {
		
		for (Individuo ind : poblacion) {
			
			ind.calculateFitness();
		}
	}
	
	/**************************** GET & SET ********************************/

}
