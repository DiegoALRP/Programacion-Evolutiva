package interfaz;

import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class panelPrincipal {

	private JFrame frame;
	private JTextField n;
	private JTextField nGeneraciones;
	private JTextField textField_n;
	private JTextField textField_nGeneraciones;
	private JTextField textField_error;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					panelPrincipal window = new panelPrincipal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public panelPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel controles = new JPanel();
		frame.getContentPane().add(controles);
		controles.setSize(new Dimension(200, 900));
		controles.setLayout(new GridLayout(10, 1, 0, 0));

		////////////////////////////////////////////////		POBLACION 

		JLabel lbl_n = new JLabel("Tama\u00F1o poblacion");
		lbl_n.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_n.setBounds(100, 100, 100, 200);
		controles.add(lbl_n);
		
		textField_n = new JTextField();
		textField_n.setHorizontalAlignment(SwingConstants.LEFT);
		textField_n.setBounds(110, 110, 200, 80);
		controles.add(textField_n);
		textField_n.setColumns(10);
		
		////////////////////////////////////////////////		GENERACIONES 

		JLabel lbl_nGeneraciones = new JLabel("Numero de generaciones");
		controles.add(lbl_nGeneraciones);
		
		textField_nGeneraciones = new JTextField();
		controles.add(textField_nGeneraciones);
		textField_nGeneraciones.setColumns(10);
		
		////////////////////////////////////////////////		ERROR
		JLabel lbl_error = new JLabel("Valor de error");
		controles.add(lbl_error);
		
		textField_error = new JTextField();
		controles.add(textField_error);
		textField_error.setColumns(10);
		
		////////////////////////////////////////////////		SELECCION 

		JPanel seleccion_panel = new JPanel();
		controles.add(seleccion_panel);
		seleccion_panel.setLayout(new GridLayout(2, 1, 0, 5));
		
		JLabel lblSeleccion = new JLabel("Tipo de seleccion");
		seleccion_panel.add(lblSeleccion);
		
		JComboBox Seleccion_cbox = new JComboBox();
		
		Seleccion_cbox.setModel(new DefaultComboBoxModel(new String[] {"Ruleta", "Torneo deterministico", "Torneo probabilistico", "Estocástico", "Restos", "Truncamiento", "Ranking"}));
		seleccion_panel.add(Seleccion_cbox);
		
		TitledBorder title_Seleccion = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Seleccion");
		seleccion_panel.setBorder(title_Seleccion);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		seleccion_panel.add(horizontalStrut);
		
		////////////////////////////////////////////////		CRUCE
		JPanel cruce_panel = new JPanel();
		controles.add(cruce_panel);
		cruce_panel.setLayout(new GridLayout(2, 1, 0, 5));
		
		JLabel lblCruce = new JLabel("Tipo de cruce");
		cruce_panel.add(lblCruce);
		
		JComboBox Cruce_cbox = new JComboBox();
		Cruce_cbox.setModel(new DefaultComboBoxModel(new String[] {"PMX", "OX", "CX", "ERX", "CO"}));
		cruce_panel.add(Cruce_cbox);
		
		JLabel lblporcentajeCruce = new JLabel("% cruce");
		cruce_panel.add(lblporcentajeCruce);
		
		JTextField textField_cruce = new JTextField();
		textField_cruce.setText("0.6");
		cruce_panel.add(textField_cruce);
		textField_cruce.setColumns(10);
		
		TitledBorder title_cruce = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Cruce");
		cruce_panel.setBorder(title_cruce);
		////////////////////////////////////////////////		MUTACION
		JPanel mutacion_panel = new JPanel();
		controles.add(mutacion_panel);
		mutacion_panel.setLayout(new GridLayout(2, 1, 0, 5));
		
		JLabel lbltipoMutacion = new JLabel("Tipo de mutacion");
		mutacion_panel.add(lbltipoMutacion);
		
		JComboBox Mutacion_cbox = new JComboBox();
		Mutacion_cbox.setModel(new DefaultComboBoxModel(new String[] {"Inserción", "Intercambio", "Inversión", "Heurística"}));
		mutacion_panel.add(Mutacion_cbox);
		
		JLabel lblporcentajeMutacion = new JLabel("% Mutacion");
		mutacion_panel.add(lblporcentajeMutacion);
		
		JTextField textField_mutacion = new JTextField();
		textField_mutacion.setText("0.05");
		mutacion_panel.add(textField_mutacion);
		textField_mutacion.setColumns(10);
		
		TitledBorder borde_mutacion = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Mutacion");
		mutacion_panel.setBorder(borde_mutacion);
		////////////////////////////////////////////////		ELITE
		JPanel elite_panel = new JPanel();
		controles.add(elite_panel);
		elite_panel.setLayout(new GridLayout(2, 1, 0, 5));
		
		JLabel porcentajeElite = new JLabel("Elite");
		elite_panel.add(porcentajeElite);
		
		JTextField textField_elite = new JTextField();
		textField_elite.setText("0.02");
		elite_panel.add(textField_elite);
		textField_elite.setColumns(10);
		
		TitledBorder title_elite = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Elite");
		elite_panel.setBorder(title_elite);

		////////////////////////////////////////////////	GRAFICA

		JPanel informacion_panel = new JPanel();
		informacion_panel.setLayout(new GridLayout(0, 1, 0, 0));
		graficas graficas = new graficas();
		informacion_panel.add(graficas.getPlot());
		
		////////////////////////////////////////////////	TEXTO ORIGINAL Y TRADUCIDO

		JPanel textArea_panel = new JPanel();
		informacion_panel.add(textArea_panel);
		//frame.getContentPane().add(textArea_panel, BorderLayout.SOUTH);
		textArea_panel.setLayout(new GridLayout(3, 1, 0, 10));
		
		JTextArea texto_original = new JTextArea();
		texto_original.setLineWrap(true);
		//texto_original.setSize(100, 100);
		textArea_panel.add(texto_original);
		
		
		JTextArea texto_traducido = new JTextArea();
		texto_traducido.setLineWrap(true);
		textArea_panel.add(texto_traducido);
		frame.getContentPane().add(informacion_panel);
		
		Border blackline = BorderFactory.createLineBorder(Color.black);
		texto_original.setBorder(blackline);
		texto_traducido.setBorder(blackline);
		
		////////////////////////////////////////////////		ELECCION DE FICHERO A TRADUCIR
		JFileChooser file = new JFileChooser();
		File workingDirectory = new File(System.getProperty("user.dir") + System.getProperty("file.separator") + "Pruebas");
		file.setCurrentDirectory(workingDirectory);
		int result = file.showOpenDialog(controles);
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = file.getSelectedFile();
			
			 try {
			        BufferedReader in;
			        in = new BufferedReader(new FileReader(selectedFile));
			        String line = in.readLine();
			        while (line != null) {
			        	texto_original.setText(texto_original.getText() + "\n" + line);
			            line = in.readLine();
			        }
			    } catch (Exception ex) {
			    }
		}

		
		
		frame.pack();
		/*frame.getContentPane().setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel control_panel = new JPanel();
		control_panel.setLayout(new GridLayout(0, 1, 0, 0));
		control_panel.setSize(1, 0);
		
		JPanel informacion_panel = new JPanel();
		informacion_panel.setLayout(new BoxLayout(informacion_panel,  BoxLayout.Y_AXIS));
		
		frame.getContentPane().add(control_panel);
		
		////////////////////////////////////////////////		POBLACION 
		
		JPanel poblacion_panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) poblacion_panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		JLabel lblNewLabel_1 = new JLabel("Tamaï¿½o poblacion");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		poblacion_panel.add(lblNewLabel_1);
		
		n = new JTextField();
		n.setText("100");
		poblacion_panel.add(n);
		n.setColumns(10);
		control_panel.add(poblacion_panel);
		
		////////////////////////////////////////////////		GENERACIONES 
		JPanel generaciones = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) generaciones.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		JLabel lblNewLabel_2 = new JLabel("Nï¿½mero de generaciones");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		generaciones.add(lblNewLabel_2);
		
		nGeneraciones = new JTextField();
		nGeneraciones.setText("100");
		generaciones.add(nGeneraciones);
		nGeneraciones.setColumns(10);
		control_panel.add(generaciones);
		
		////////////////////////////////////////////////		PRECISION
		JPanel error_panel = new JPanel();
		error_panel.setBounds(75, 75, 75, 75);
		FlowLayout flowLayout_2 = (FlowLayout) error_panel.getLayout();
		flowLayout_2.setAlignment(FlowLayout.RIGHT);
		JLabel errorlbl = new JLabel("Valor de error");
		errorlbl.setHorizontalAlignment(SwingConstants.CENTER);
		error_panel.add(errorlbl);
		
		JTextField textField_error = new JTextField();
		textField_error.setText("0.0001");
		error_panel.add(textField_error);
		textField_error.setColumns(10);
		control_panel.add(error_panel);
		
		////////////////////////////////////////////////		SELECCION 
		JPanel seleccion_panel = new JPanel();
		control_panel.add(seleccion_panel);
		seleccion_panel.setLayout(new GridLayout(2, 1, 0, 5));
		
		JLabel lblSeleccion = new JLabel("Tipo de selecciï¿½n");
		seleccion_panel.add(lblSeleccion);
		
		JComboBox Seleccion_cbox = new JComboBox();
		
		Seleccion_cbox.setModel(new DefaultComboBoxModel(new String[] {"Metodo de la ruleta", "Universal Estocastico", "Torneo Deterministico", "Torneo Probabilistico", "Truncamiento", "Restos"}));
		seleccion_panel.add(Seleccion_cbox);
		
		TitledBorder title_Seleccion = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Selecciï¿½n");
		seleccion_panel.setBorder(title_Seleccion);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		seleccion_panel.add(horizontalStrut);
		
		
		////////////////////////////////////////////////		CRUCE 
		JPanel cruce_panel = new JPanel();
		control_panel.add(cruce_panel);
		cruce_panel.setLayout(new GridLayout(2, 1, 0, 5));
		
		JLabel lblCruce = new JLabel("Tipo de cruce");
		cruce_panel.add(lblCruce);
		
		JComboBox Cruce_cbox = new JComboBox();
		Cruce_cbox.setModel(new DefaultComboBoxModel(new String[] {"Monopunto", "Discreto Uniforme"}));
		cruce_panel.add(Cruce_cbox);
		
		JLabel lblporcentajeCruce = new JLabel("% cruce");
		cruce_panel.add(lblporcentajeCruce);
		
		JTextField textField_cruce = new JTextField();
		textField_cruce.setText("0.6");
		cruce_panel.add(textField_cruce);
		textField_cruce.setColumns(10);
		
		TitledBorder title_cruce = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Cruce");
		cruce_panel.setBorder(title_cruce);
		
		
		////////////////////////////////////////////////		MUTACION 
		JPanel mutacion_panel = new JPanel();
		control_panel.add(mutacion_panel);
		mutacion_panel.setLayout(new GridLayout(2, 1, 0, 5));
		
		JLabel lbltipoMutacion = new JLabel("Tipo de mutacion");
		mutacion_panel.add(lbltipoMutacion);
		
		JComboBox Mutacion_cbox = new JComboBox();
		Mutacion_cbox.setModel(new DefaultComboBoxModel(new String[] {"Mutaciï¿½n basica"}));
		mutacion_panel.add(Mutacion_cbox);
		
		JLabel lblporcentajeMutacion = new JLabel("% Mutacion");
		mutacion_panel.add(lblporcentajeMutacion);
		
		JTextField textField_mutacion = new JTextField();
		textField_mutacion.setText("0.05");
		mutacion_panel.add(textField_mutacion);
		textField_mutacion.setColumns(10);
		
		TitledBorder borde_mutacion = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Mutacion bï¿½sica");
		mutacion_panel.setBorder(borde_mutacion);
		
		////////////////////////////////////////////////		ELITE
		JPanel elite_panel = new JPanel();
		control_panel.add(elite_panel);
		elite_panel.setLayout(new GridLayout(2, 1, 0, 5));
		
		JLabel porcentajeElite = new JLabel("% ï¿½lite");
		elite_panel.add(porcentajeElite);
		
		JTextField textField_elite = new JTextField();
		textField_elite.setText("0.02");
		elite_panel.add(textField_elite);
		textField_elite.setColumns(10);
		
		TitledBorder title_elite = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "ï¿½lite");
		elite_panel.setBorder(title_elite);
		
		////////////////////////////////////////////////		SOLUCION

		JLabel solucion = new JLabel("Solucion :");
		JTextArea solArea = new JTextArea();
		Dimension dimension = new Dimension();
		control_panel.add(solucion);
		control_panel.add(solArea);
		informacion_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		////////////////////////////////////////////////		GRAFICAS
		
		JPanel graficas_panel = new JPanel();
		//graficas_panel.setBounds(200, 200, 400, 400);
		graficas gr = new graficas();
		graficas_panel.add(gr.getPlot());
		informacion_panel.add(graficas_panel);

		//frame.getContentPane().add(gr.getPlot(), BorderLayout.CENTER);
		//frame.getContentPane().add(graficas_panel, BorderLayout.CENTER);
		
		////////////////////////////////////////////////		TEXT AREA

		JPanel textArea_panel = new JPanel();
		informacion_panel.add(textArea_panel);
		//frame.getContentPane().add(textArea_panel, BorderLayout.SOUTH);
		textArea_panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JTextArea texto_original = new JTextArea();
		texto_original.setLineWrap(true);
		//texto_original.setSize(100, 100);
		texto_original.setText("Zlm, pr t jtvqhv xhrxh, gh etr rsm chcpetmh -- gh etr rsm esrxhevtmh -- gh etr rsm ktjjsg -- mkpx qvslrc. Mkh zvtyh ihr, jpyprq trc chtc, gks xmvlqqjhc khvh, ktyh esrxhevtmhc pm, otv tzsyh slv dssv dsghv ms tcc sv chmvtem. Mkh gsvjc gpjj jpmmjh rsmh, rsv jsrq vhihizhv gktm gh xta khvh, zlm pm etr rhyhv osvqhm gktm mkha cpc khvh. Pm px osv lx mkh jpyprq, vtmkhv, ms zh chcpetmhc khvh ms mkh lroprpxkhc gsvu gkpek mkha gks oslqkm khvh ktyh mklx otv xs rszja tcytrehc. Pm px vtmkhv osv lx ms zh khvh chcpetmhc ms mkh qvhtm mtxu vhitprprq zhosvh lx -- mktm ovsi mkhxh ksrsvhc chtc gh mtuh prevhtxhc chysmpsr ms mktm etlxh osv gkpek mkha qtyh mkh jtxm oljj ihtxlvh so chysmpsr -- mktm gh khvh kpqkja vhxsjyh mktm mkhxh chtc xktjj rsm ktyh cphc pr ytpr -- mktm mkpx rtmpsr, lrchv Qsc, xktjj ktyh t rhg zpvmk so ovhhcsi -- trc mktm qsyhvrihrm so mkh dhsdjh, za mkh dhsdjh, osv mkh dhsdjh, xktjj rsm dhvpxk ovsi mkh htvmk.");
		textArea_panel.add(texto_original);
		
		JTextArea texto_traducido = new JTextArea();
		texto_traducido.setLineWrap(true);
		texto_traducido.setText("Eqa ycwe aqqt aqcit v aqqtwecwb wecwb zn v aqqtwecwb wqcit wecwb aqqt?  Zr aqcit wecwb vii rep aqqt revr v aqqtwecwb wqcit, zn v aqqtwecwb wqcit wecwb aqqt.");
		textArea_panel.add(texto_traducido);
		
		
		frame.getContentPane().add(informacion_panel);*/
	}

}
