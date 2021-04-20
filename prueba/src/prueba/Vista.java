package prueba;

import java.awt.Color;
import java.util.ArrayList;
/**
	 * 
	 */
public class Vista extends javax.swing.JFrame {
	private static final long serialVersionUID = 1L;
	
//	ArrayList<ArrayList<Double>> generaciones;
//	ArrayList<ArrayList<Double>> media_fitness;
//	ArrayList<ArrayList<Double>> mejor_fitness;
//	ArrayList<ArrayList<Double>> mejor_absoluto; 
	
	ArrayList<Double> generaciones;
	ArrayList<Double> media_fitness;
	ArrayList<Double> mejor_fitness;
	ArrayList<Double> mejor_absoluto; 
	
	
	
	
	
	/**
   * Creates new form Vista
   */
  public Vista() {
//  	generaciones   = new ArrayList<ArrayList<Double>>();
//		media_fitness  = new ArrayList<ArrayList<Double>>();
//		mejor_fitness  = new ArrayList<ArrayList<Double>>();
//		mejor_absoluto = new ArrayList<ArrayList<Double>>();
  	generaciones   = new ArrayList<Double>();
		media_fitness  = new ArrayList<Double>();
		mejor_fitness  = new ArrayList<Double>();
		mejor_absoluto = new ArrayList<Double>();
		initComponents();
      setVisible(true);
  }

  private void initComponents() {

      PanelPrincipal = new javax.swing.JPanel();
      PanelOpciones = new javax.swing.JPanel();
      LabelEvolutiva = new javax.swing.JLabel();
      Separador1 = new javax.swing.JSeparator();
      LabelPoblacion = new javax.swing.JLabel();
      TextFieldPoblacion = new javax.swing.JTextField();
      LabelGeneraciones = new javax.swing.JLabel();
      TextFieldGeneraciones = new javax.swing.JTextField();
      LabelElitismo = new javax.swing.JLabel();
      TextFieldElitismo = new javax.swing.JTextField();
      Separador2 = new javax.swing.JSeparator();
      LabelOpcionSeleccion = new javax.swing.JLabel();
      ComboBoxOpcionSeleccion = new javax.swing.JComboBox<>();
      TextFieldSeleccion = new javax.swing.JTextField();
      LabelOpcionCruces = new javax.swing.JLabel();
      ComboBoxOpcionCruces = new javax.swing.JComboBox<>();
      TextFieldCruces = new javax.swing.JTextField();
      LabelOpcionMutacion = new javax.swing.JLabel();
      ComboBoxOpcionMutacion = new javax.swing.JComboBox<>();
      TextFieldMutacion = new javax.swing.JTextField();
      Separador3 = new javax.swing.JSeparator();
      ButtonIniciar = new javax.swing.JButton();
      PanelDiagrama = new javax.swing.JPanel();
      TabbedPaneDiagrama = new javax.swing.JTabbedPane();
      plot = new org.math.plot.Plot2DPanel();
      PanelInformacion = new javax.swing.JPanel();
      ScrollPaneDesencriptar = new javax.swing.JScrollPane();
      TextAreaDesencriptar = new javax.swing.JTextArea();
      ScrollPanePlano = new javax.swing.JScrollPane();
      TextAreaPlano = new javax.swing.JTextArea();

      setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

      LabelEvolutiva.setText("Programacion Evolutiva");

      LabelPoblacion.setText("Poblacion");

      TextFieldPoblacion.setText("200");

      LabelGeneraciones.setText("Generaciones");

      TextFieldGeneraciones.setText("500");

      LabelElitismo.setText("Elitismo");

      TextFieldElitismo.setText("0.02");

      LabelOpcionSeleccion.setText("Seleccion");

      ComboBoxOpcionSeleccion.setModel(new javax.swing.DefaultComboBoxModel<>(
      		new String[] { "Estocastico",
      						"ManuEli",
      						"Restos", 
      						"Ruleta", 
      						"Torneo Deterministico",
      						"Torneo Probabilistico",
      						"Truncamiento" }));

      TextFieldSeleccion.setText("0.5");

      LabelOpcionCruces.setText("Cruce");

      ComboBoxOpcionCruces.setModel(new javax.swing.DefaultComboBoxModel<>(
      		new String[] { "Codificacion Ordinal", 
      						"ManuEli", 
      						"PMX" }));

      TextFieldCruces.setText("0.75");

      LabelOpcionMutacion.setText("Mutacion");

      ComboBoxOpcionMutacion.setModel(new javax.swing.DefaultComboBoxModel<>(
      		new String[] { "Heuristica", 
      						"Insercion", 
      						"Intercambio", 
      						"Inversion", 
      						"ManuEli" }));

      TextFieldMutacion.setText("0.1");
      
//      TextAreaDesencriptar.setText("Eqa ycwe aqqt aqcit v aqqtwecwb wecwb zn "
//				+ "v aqqtwecwb wqcit wecwb aqqt?  Zr aqcit wecwb vii "
//				+ "rep aqqt revr v aqqtwecwb wqcit, zn v aqqtwecwb "
//				+ "wqcit wecwb aqqt.\r\n");
      
      
//      TextAreaDesencriptar.setText("GNX NVMVNL XP G QUEVNLV NGSCV PM WYZGS GOXUEUXUVL US ONVGXUSC "
//      		+ "EULYGT, GYQUXPNH PN KVNMPNZUSC GNXUMGOXL. XWVLV GNXFPNBL VJKNVLL XWV GYXWPN'L UZGCUSGXUEV "
//      		+ "PN XVOWSUOGT LBUTT. GNX UL USXVSQVQ XP IV GKKNVOUGXVQ MPN UXL IVGYXH PN VZPXUPSGT KPFVN. "
//      		+ "US XWVUN ZPLX CVSVNGT MPNZ XWVLV GOXUEUXUVL USOTYQV XWV KNPQYOXUPS PM FPNBL PM GNX, XWV "
//      		+ "ONUXUOULZ PM GNX, XWV LXYQH PM XWV WULXPNH PM GNX, GSQ XWV GVLXWVXUO QULLVZUSGXUPS PM GNX.\r\n" 
//      		+ "GNX WGL WGQ G CNVGX SYZIVN PM QUMMVNVSX MYSOXUPSL XWNPYCWPYX UXL WULXPNH, ZGBUSC UXL KYNKPLV "
//      		+ "QUMMUOYTX XP GILXNGOX PN RYGSXUMH XP GSH LUSCTV OPSOVKX. XWUL QPVL SPX UZKTH XWGX XWV KYNKPLV "
//      		+ "PM GNX UL \"EGCYV\", IYX XWGX UX WGL WGQ ZGSH YSURYV, QUMMVNVSX NVGLPSL MPN IVUSC ONVGXVQ.\r\n" 
//      		+ "GNX OGS WGEV G KVNLPSGT MYSOXUPS, UX UL GS VJKNVLLUPS PM IGLUO WYZGS USLXUSOX MPN WGNZPSH, IGTGSOV, "
//      		+ "NWHXWZ. GNX GX XWUL TVEVT UL SPX GS GOXUPS PN GS PIDVOX, IYX GS USXVNSGT GKKNVOUGXUPS PM IGTGSOV "
//      		+ "GSQ WGNZPSH (IVGYXH), GSQ XWVNVMPNV GS GLKVOX PM IVUSC WYZGS IVHPSQ YXUTUXH. GNX GTLP KNPEUQVL G "
//      		+ "FGH XP VJKVNUVSOV PSV'L LVTM US NVTGXUPS XP XWV YSUEVNLV. XWUL VJKVNUVSOV ZGH PMXVS OPZV YSZPXUEGXVQ, "
//      		+ "GL PSV GKKNVOUGXVL GNX, ZYLUO PN KPVXNH.\r\n"
//      		+ "PS XWV PXWVN WGSQ GNX ZGH WGEV G LPOUGT MYSOXUPS. GX UXL LUZKTVLX, GNX UL G MPNZ PM OPZZYSUOGXUPS. "
//      		+ "UX LVVBL XP VSXVNXGUS GSQ INUSC GIPYX G KGNXUOYTGN VZPXUPS PN ZPPQ, MPN XWV KYNKPLV PM NVTGJUSC PN "
//      		+ "VSXVNXGUSUSC XWV EUVFVN. GNX ZGH GTLP IV GS VJKNVLLUPS PM LPOUGT KNPXVLX, LVVBUSC XP RYVLXUPS GLKVOXL "
//      		+ "PM LPOUVXH.\r\n" 
//      		+ "XWV PTQVLX MPNZ PM GNX GNV EULYGT GNXL, FWUOW USOTYQV ONVGXUPS PM UZGCVL PN PIDVOXL US MUVTQL USOTYQUSC "
//      		+ "KGUSXUSC, LOYTKXYNV, KNUSXZGBUSC, KWPXPCNGKWH, GSQ PXWVN EULYGT ZVQUG. GNOWUXVOXYNV UL PMXVS USOTYQVQ GL "
//      		+ "PSV PM XWV EULYGT GNXL; WPFVEVN, TUBV XWV QVOPNGXUEV GNXL, UX USEPTEVL XWV ONVGXUPS PM PIDVOXL FWVNV XWV "
//      		+ "KNGOXUOGT OPSLUQVNGXUPSL PM YLV GNV VLLVSXUGT, US G FGH XWGX XWVH YLYGTTH GNV SPX US G KGUSXUSC, MPN "
//      		+ "VJGZKTV.\r\n" + 
//      		"");
      
      TextAreaDesencriptar.setText("Oslv xesvh trc xhyhr ahtvx tqs slv otmkhvx zvslqkm osvmk sr mkpx "
      		+ "esrmprhrm t rhg rtmpsr, esrehpyhc pr Jpzhvma, trc chcpetmhc ms mkh dvsdsxpmpsr mktm "
      		+ "tjj ihr tvh evhtmhc hnltj. Rsg gh tvh hrqtqhc pr t qvhtm epypj gtv, mhxmprq gkhmkhv "
      		+ "mktm rtmpsr, sv tra rtmpsr, xs esrehpyhc trc xs chcpetmhc, etr jsrq hrclvh. Gh tvh "
      		+ "ihm sr t qvhtm ztmmjh-ophjc so mktm gtv. Gh ktyh esih ms chcpetmh t dsvmpsr so mktm "
      		+ "ophjc, tx t oprtj vhxmprq djteh osv mksxh gks khvh qtyh mkhpv jpyhx mktm mktm rtmpsr "
      		+ "ipqkm jpyh. Pm px tjmsqhmkhv opmmprq trc dvsdhv mktm gh xksljc cs mkpx. Zlm, pr t jtvqhv "
      		+ "xhrxh, gh etr rsm chcpetmh -- gh etr rsm esrxhevtmh -- gh etr rsm ktjjsg -- mkpx qvslrc. "
      		+ "Mkh zvtyh ihr, jpyprq trc chtc, gks xmvlqqjhc khvh, ktyh esrxhevtmhc pm, otv tzsyh slv "
      		+ "dssv dsghv ms tcc sv chmvtem. Mkh gsvjc gpjj jpmmjh rsmh, rsv jsrq vhihizhv gktm gh xta "
      		+ "khvh, zlm pm etr rhyhv osvqhm gktm mkha cpc khvh. Pm px osv lx mkh jpyprq, vtmkhv, ms zh "
      		+ "chcpetmhc khvh ms mkh lroprpxkhc gsvu gkpek mkha gks oslqkm khvh ktyh mklx otv xs rszja "
      		+ "tcytrehc. Pm px vtmkhv osv lx ms zh khvh chcpetmhc ms mkh qvhtm mtxu vhitprprq zhosvh lx "
      		+ "-- mktm ovsi mkhxh ksrsvhc chtc gh mtuh prevhtxhc chysmpsr ms mktm etlxh osv gkpek mkha "
      		+ "qtyh mkh jtxm oljj ihtxlvh so chysmpsr -- mktm gh khvh kpqkja vhxsjyh mktm mkhxh chtc xktjj "
      		+ "rsm ktyh cphc pr ytpr -- mktm mkpx rtmpsr, lrchv Qsc, xktjj ktyh t rhg zpvmk so ovhhcsi -- "
      		+ "trc mktm qsyhvrihrm so mkh dhsdjh, za mkh dhsdjh, osv mkh dhsdjh, xktjj rsm dhvpxk ovsi mkh "
      		+ "htvmk.");
      
//      	TextAreaDesencriptar.setText("HN ZQCL UQWCJHQV HU OEQVI JXM NHKM NOUJMUJ HV TMPHBXMLHVI JXM JMYJ, "
//      			+ "ZQC DHWW IMJ O PQTM OU O LMDOLT JXOJ DHWW UMLKM JQ HEBLQKM ZQCL ILOTM. JLZ JQ NHVM-JCVM "
//      			+ "JXM NHJVMUU NCVPJHQV UQ JXOJ JXM OWIQLHJXE DQLFU NOUJ. IQQT WCPF.");
      

      ButtonIniciar.setText("Iniciar");

      javax.swing.GroupLayout PanelOpcionesLayout = new javax.swing.GroupLayout(PanelOpciones);
      PanelOpciones.setLayout(PanelOpcionesLayout);
      PanelOpcionesLayout.setHorizontalGroup(
          PanelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(Separador1)
          .addGroup(PanelOpcionesLayout.createSequentialGroup()
              .addGap(27, 27, 27)
              .addGroup(PanelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(LabelPoblacion)
                  .addComponent(LabelGeneraciones)
                  .addComponent(LabelElitismo))
              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
              .addGroup(PanelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(TextFieldElitismo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addComponent(TextFieldGeneraciones, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addComponent(TextFieldPoblacion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
              .addGap(23, 23, 23))
          .addComponent(Separador2)
          .addComponent(Separador3)
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelOpcionesLayout.createSequentialGroup()
              .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
              .addComponent(ButtonIniciar)
              .addGap(63, 63, 63))
          .addGroup(PanelOpcionesLayout.createSequentialGroup()
              .addGroup(PanelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(PanelOpcionesLayout.createSequentialGroup()
                      .addGroup(PanelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                          .addGroup(PanelOpcionesLayout.createSequentialGroup()
                              .addGap(29, 29, 29)
                              .addComponent(ComboBoxOpcionSeleccion, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelOpcionesLayout.createSequentialGroup()
                              .addContainerGap()
                              .addGroup(PanelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                  .addComponent(ComboBoxOpcionCruces, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                  .addComponent(ComboBoxOpcionMutacion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))))
                      .addGap(18, 18, 18)
                      .addGroup(PanelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                          .addComponent(TextFieldCruces, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                          .addComponent(TextFieldSeleccion, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                          .addComponent(TextFieldMutacion, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                  .addGroup(PanelOpcionesLayout.createSequentialGroup()
                      .addContainerGap()
                      .addGroup(PanelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                          .addComponent(LabelOpcionSeleccion)
                          .addComponent(LabelOpcionCruces)
                          .addComponent(LabelOpcionMutacion)))
                  .addGroup(PanelOpcionesLayout.createSequentialGroup()
                      .addGap(39, 39, 39)
                      .addComponent(LabelEvolutiva)))
              .addContainerGap(18, Short.MAX_VALUE))
      );

      PanelOpcionesLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {TextFieldElitismo, TextFieldGeneraciones, TextFieldPoblacion});

      PanelOpcionesLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {ComboBoxOpcionCruces, ComboBoxOpcionMutacion, ComboBoxOpcionSeleccion});

      PanelOpcionesLayout.setVerticalGroup(
          PanelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(PanelOpcionesLayout.createSequentialGroup()
              .addGap(21, 21, 21)
              .addComponent(LabelEvolutiva)
              .addGap(18, 18, 18)
              .addComponent(Separador1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
              .addGroup(PanelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                  .addComponent(LabelPoblacion)
                  .addComponent(TextFieldPoblacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
              .addGroup(PanelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                  .addComponent(TextFieldGeneraciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addComponent(LabelGeneraciones))
              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
              .addGroup(PanelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                  .addComponent(TextFieldElitismo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addComponent(LabelElitismo))
              .addGap(18, 18, 18)
              .addComponent(Separador2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
              .addComponent(LabelOpcionSeleccion)
              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
              .addGroup(PanelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                  .addComponent(ComboBoxOpcionSeleccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addComponent(TextFieldSeleccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
              .addGap(18, 18, 18)
              .addComponent(LabelOpcionCruces)
              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
              .addGroup(PanelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                  .addComponent(ComboBoxOpcionCruces, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addComponent(TextFieldCruces, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
              .addGap(18, 18, 18)
              .addComponent(LabelOpcionMutacion)
              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
              .addGroup(PanelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                  .addComponent(ComboBoxOpcionMutacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addComponent(TextFieldMutacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
              .addGap(26, 26, 26)
              .addComponent(Separador3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addGap(35, 35, 35)
              .addComponent(ButtonIniciar)
              .addContainerGap(47, Short.MAX_VALUE))
      );

      PanelOpcionesLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {TextFieldElitismo, TextFieldGeneraciones, TextFieldPoblacion});

      TabbedPaneDiagrama.addTab("Ejercicio", plot);

      javax.swing.GroupLayout PanelDiagramaLayout = new javax.swing.GroupLayout(PanelDiagrama);
      PanelDiagrama.setLayout(PanelDiagramaLayout);
      PanelDiagramaLayout.setHorizontalGroup(
          PanelDiagramaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(PanelDiagramaLayout.createSequentialGroup()
              .addContainerGap()
              .addComponent(TabbedPaneDiagrama)
              .addContainerGap())
      );
      PanelDiagramaLayout.setVerticalGroup(
          PanelDiagramaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(PanelDiagramaLayout.createSequentialGroup()
              .addContainerGap()
              .addComponent(TabbedPaneDiagrama)
              .addContainerGap())
      );

      TextAreaDesencriptar.setColumns(20);
      TextAreaDesencriptar.setRows(5);
      TextAreaDesencriptar.setFont(new java.awt.Font("Lucida Console", 0, 11)); // NOI18N
      TextAreaDesencriptar.setEditable(true);
      TextAreaDesencriptar.setLineWrap(true);
      TextAreaDesencriptar.setWrapStyleWord(true);
      ScrollPaneDesencriptar.setViewportView(TextAreaDesencriptar);
      

      TextAreaPlano.setColumns(20);
      TextAreaPlano.setRows(5);
      TextAreaPlano.setFont(new java.awt.Font("Lucida Console", 0, 11)); // NOI18N
      TextAreaPlano.setLineWrap(true);
      TextAreaPlano.setWrapStyleWord(true);
//      TextAreaPlano.setSelectionStart(0);
//      TextAreaPlano.setSelectionEnd(0); 
      
//      TextAreaPlano.setAutoscrolls(false);
//      TextAreaPlano.setEditable(false);
      ScrollPanePlano.setViewportView(TextAreaPlano);
//      ScrollPanePlano.getVerticalScrollBar().setValue(ScrollPanePlano.getVerticalScrollBar().getMaximum());
//      ScrollPanePlano.getViewport().setViewPosition(new Point(0,0));
//      ScrollPanePlano.setAutoscrolls(false);
      ScrollPanePlano.setLocation(0, 0);

      
      javax.swing.GroupLayout PanelInformacionLayout = new javax.swing.GroupLayout(PanelInformacion);
      PanelInformacion.setLayout(PanelInformacionLayout);
      PanelInformacionLayout.setHorizontalGroup(
          PanelInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(PanelInformacionLayout.createSequentialGroup()
              .addContainerGap()
              .addComponent(ScrollPaneDesencriptar, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addGap(18, 18, 18)
              .addComponent(ScrollPanePlano, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
              .addContainerGap())
      );
      PanelInformacionLayout.setVerticalGroup(
          PanelInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelInformacionLayout.createSequentialGroup()
              .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
              .addGroup(PanelInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                  .addComponent(ScrollPanePlano, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                  .addComponent(ScrollPaneDesencriptar))
              .addContainerGap())
      );

      javax.swing.GroupLayout PanelPrincipalLayout = new javax.swing.GroupLayout(PanelPrincipal);
      PanelPrincipal.setLayout(PanelPrincipalLayout);
      PanelPrincipalLayout.setHorizontalGroup(
          PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(PanelPrincipalLayout.createSequentialGroup()
              .addComponent(PanelOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
              .addGroup(PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(PanelDiagrama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                  .addComponent(PanelInformacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
      );
      PanelPrincipalLayout.setVerticalGroup(
          PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(PanelOpciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addGroup(PanelPrincipalLayout.createSequentialGroup()
              .addComponent(PanelDiagrama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
              .addComponent(PanelInformacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
      );

      javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
      getContentPane().setLayout(layout);
      layout.setHorizontalGroup(
          layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(PanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
      );
      layout.setVerticalGroup(
          layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(PanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
      );

      pack();
  }// </editor-fold>                        


 
  // Variables declaration - do not modify                     
  private javax.swing.JButton ButtonIniciar;
  private javax.swing.JComboBox<String> ComboBoxOpcionCruces;
  private javax.swing.JComboBox<String> ComboBoxOpcionMutacion;
  private javax.swing.JComboBox<String> ComboBoxOpcionSeleccion;
  private javax.swing.JLabel LabelElitismo;
  private javax.swing.JLabel LabelEvolutiva;
  private javax.swing.JLabel LabelGeneraciones;
  private javax.swing.JLabel LabelOpcionCruces;
  private javax.swing.JLabel LabelOpcionMutacion;
  private javax.swing.JLabel LabelOpcionSeleccion;
  private javax.swing.JLabel LabelPoblacion;
  private javax.swing.JPanel PanelDiagrama;
  private javax.swing.JPanel PanelInformacion;
  private javax.swing.JPanel PanelOpciones;
  private javax.swing.JPanel PanelPrincipal;
  private javax.swing.JScrollPane ScrollPaneDesencriptar;
  private javax.swing.JScrollPane ScrollPanePlano;
  private javax.swing.JSeparator Separador1;
  private javax.swing.JSeparator Separador2;
  private javax.swing.JSeparator Separador3;
  private javax.swing.JTabbedPane TabbedPaneDiagrama;
  private javax.swing.JTextArea TextAreaDesencriptar;
  private javax.swing.JTextArea TextAreaPlano;
  private javax.swing.JTextField TextFieldCruces;
  private javax.swing.JTextField TextFieldElitismo;
  private javax.swing.JTextField TextFieldGeneraciones;
  private javax.swing.JTextField TextFieldPoblacion;
  private javax.swing.JTextField TextFieldSeleccion;
  private javax.swing.JTextField TextFieldMutacion;
  private org.math.plot.Plot2DPanel plot;
  // End of variables declaration  
  
  
  
  
	/*public void conectaControlador(AlgoritmoEvolutivo c){
		ButtonIniciar.addActionListener(c);
      ButtonIniciar.setActionCommand("INICIAR");
      plot.removeAllPlots();
//      new Thread() {
//      	@Override
//      	public void run() {
//      		generaciones.add(new ArrayList<Double>());
//      		media_fitness.add(new ArrayList<Double>());
//      		mejor_fitness.add(new ArrayList<Double>());
//      		mejor_absoluto.add(new ArrayList<Double>());
//      		
//      	}
//      }.start();
	}*/
	
	
	
	public void actualiza (double generacion, double media_fitness, 
			double mejor_fitness, double mejor_absoluto, String texto) {
		
		
		
		if (generacion == 0.0) {
			this.generaciones   = new ArrayList<Double>();
			this.media_fitness  = new ArrayList<Double>();
			this.mejor_fitness  = new ArrayList<Double>();
			this.mejor_absoluto = new ArrayList<Double>();
			plot.removeAllPlots();
		}
		
		this.generaciones.add(generacion);
		this.media_fitness.add(media_fitness);
		this.mejor_fitness.add(mejor_fitness);
		this.mejor_absoluto.add(mejor_absoluto);
		
//		this.generaciones.get(this.generaciones.size() - 1).add(generacion);
//		this.media_fitness.get(this.media_fitness.size() - 1).add(media_fitness);
//		this.mejor_fitness.get(this.mejor_fitness.size() - 1).add(mejor_fitness);
//		this.mejor_absoluto.get(this.mejor_absoluto.size() - 1).add(mejor_absoluto);
		
		javax.swing.SwingUtilities.invokeLater(() -> {
			try {
				plot.removeAllPlots();
				TextAreaPlano.setText("");

//				double[] array_generaciones   = this.generaciones.get(this.generaciones.size() - 1).stream().mapToDouble(d -> d).toArray();
//				double[] array_media_fitness  = this.media_fitness.get(this.media_fitness.size() - 1).stream().mapToDouble(d -> d).toArray();
//				double[] array_mejor_fitness  = this.mejor_fitness.get(this.mejor_fitness.size() - 1).stream().mapToDouble(d -> d).toArray();
//				double[] array_mejor_absoluto = this.mejor_absoluto.get(this.mejor_absoluto.size() - 1).stream().mapToDouble(d -> d).toArray();
				
				double[] array_generaciones   = this.generaciones.stream().mapToDouble(d -> d).toArray();
				double[] array_media_fitness  = this.media_fitness.stream().mapToDouble(d -> d).toArray();
				double[] array_mejor_fitness  = this.mejor_fitness.stream().mapToDouble(d -> d).toArray();
				double[] array_mejor_absoluto = this.mejor_absoluto.stream().mapToDouble(d -> d).toArray();
				
				
//				System.out.println("*********VISTA************+");
//				for (int i = 0; i < array_generaciones.length; i++) {
//					System.out.println("Generaciones: " + array_generaciones[i]);
//					System.out.println("Media: " + array_media_fitness[i]);
//					System.out.println("Mejor: " + array_mejor_fitness[i]);
//					System.out.println("Absoluto: " + array_mejor_absoluto[i]);
//				}
//				System.out.println("");
				
				
				
				TextAreaPlano.setText(texto.toString());

				plot.removeAllPlots();
				plot.addLegend("SOUTH");
				
				// add a line plot to the PlotPanel
				plot.addLinePlot("Mejor absoluto", Color.BLUE, array_generaciones, array_mejor_absoluto);
				plot.addLinePlot("Media fitness", Color.RED, array_generaciones, array_media_fitness);
				plot.addLinePlot("Mejor", Color.GREEN, array_generaciones, array_mejor_fitness);
				
				
			}
			catch (Exception ex){
				plot.removeAllPlots();
			}
		});
		

		this.repaint();
	}
	
	
	public void borrarDatos() {
		plot.removeAllPlots();
	}
	
	
	
	public void actualizarGrafica(double[] generaciones, double[] media_fitness, 
			double[] fitness_mejor,	double[] mejor_absoluto){
		
		System.out.println("Vista");
		javax.swing.SwingUtilities.invokeLater(() -> {
			try {
				plot.removeAllPlots();
//				TextAreaPlano.setText("");
				
				for (int i = 0; i < generaciones.length; i++) {
					System.out.println("Generaciones: " + generaciones[i]);
				}
				
				System.out.println("*********VISTA************+");
				for (int i = 0; i < media_fitness.length; i++) {
					System.out.println("Media: " + media_fitness[i]);
					System.out.println("Mejor: " + fitness_mejor[i]);
					System.out.println("Absoluto: " + mejor_absoluto[i]);
				}
				
				
				// define the legend position
				plot.addLegend("SOUTH");
				
				// add a line plot to the PlotPanel
				plot.addLinePlot("Mejor absoluto", Color.BLUE, generaciones, mejor_absoluto);
				plot.addLinePlot("Media fitness", Color.RED, generaciones, media_fitness);
				plot.addLinePlot("Mejor", Color.GREEN, generaciones, fitness_mejor);

//				TextAreaPlano.setText(texto.toString());
			}
			catch (Exception ex){
				
			}
		});
	  
	  
	}
	
  

	public javax.swing.JTextField getTextFieldPoblacion() {
		return TextFieldPoblacion;
	}

	public void setTextFieldPoblacion(javax.swing.JTextField textFieldPoblacion) {
		TextFieldPoblacion = textFieldPoblacion;
	}







	public javax.swing.JTextField getTextFieldGeneraciones() {
		return TextFieldGeneraciones;
	}

	public void setTextFieldGeneraciones(javax.swing.JTextField textFieldGeneraciones) {
		TextFieldGeneraciones = textFieldGeneraciones;
	}


	public javax.swing.JTextField getTextFieldSeleccion() {
		return TextFieldSeleccion;
	}

	public void setTextFieldSeleccion(javax.swing.JTextField textFieldSeleccion) {
		TextFieldSeleccion = textFieldSeleccion;
	}





	public javax.swing.JTextField getTextFieldCruces() {
		return TextFieldCruces;
	}

	public void setTextFieldCruces(javax.swing.JTextField textFieldCruces) {
		TextFieldCruces = textFieldCruces;
	}







	public javax.swing.JTextField getTextFieldMutacion() {
		return TextFieldMutacion;
	}

	public void setTextFieldMutacion(javax.swing.JTextField textFieldMutacion) {
		TextFieldMutacion = textFieldMutacion;
	}









	public javax.swing.JTextField getTextFieldElitismo() {
		return TextFieldElitismo;
	}

	public void setTextFieldElitismo(javax.swing.JTextField textFieldElitismo) {
		TextFieldElitismo = textFieldElitismo;
	}







	public javax.swing.JComboBox<String> getComboBoxOpcionSeleccion() {
		return ComboBoxOpcionSeleccion;
	}

	public void setComboBoxOpcionSeleccion(javax.swing.JComboBox<String> comboBoxOpcionSeleccion) {
		ComboBoxOpcionSeleccion = comboBoxOpcionSeleccion;
	}







	public javax.swing.JComboBox<String> getComboBoxOpcionCruce() {
		return ComboBoxOpcionCruces;
	}

	public void setComboBoxOpcionCruce(javax.swing.JComboBox<String> comboBoxOpcionCruce) {
		ComboBoxOpcionCruces = comboBoxOpcionCruce;
	}

	
	
	public javax.swing.JTextArea getTextAreaDesencriptar(){
		return this.TextAreaDesencriptar;
	}






	public javax.swing.JComboBox<String> getComboBoxOpcionMutacion() {
		return ComboBoxOpcionMutacion;
	}

	public void setComboBoxOpcionMutacion(javax.swing.JComboBox<String> comboBoxOpcionMutacion) {
		ComboBoxOpcionMutacion = comboBoxOpcionMutacion;
	}

	
	
  

}
