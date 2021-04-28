package interfaz;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;


import javax.swing.BorderFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import algoritmoGenetico.AlgoritmoGenetico;
import algoritmoGenetico.cruces.Cruce;
import algoritmoGenetico.cruces.FactoriaCruces;
import algoritmoGenetico.individuos.NGramas;
import algoritmoGenetico.individuos.Texto;
import algoritmoGenetico.mutaciones.FactoriaMutacion;
import algoritmoGenetico.mutaciones.Mutacion;
import algoritmoGenetico.seleccion.FactoriaSeleccion;
import algoritmoGenetico.seleccion.Seleccion;

import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import javax.swing.DefaultComboBoxModel;

public class panelPrincipal {

	private JFrame frame;
	private JTextField tf_n;
	private JTextField tf_generaciones;
	private JTextField tf_elite;
	private JTextField textField_probCruce;
	private JTextField textField_probMutacion;
	private String eleccion;
	
	private NGramas ngramas;
	private Texto claseTexto;
	private StringBuilder textoOriginal;
	private StringBuilder textoAyuda;
	
	private graficas gr_texto1;
	private graficas gr_texto2; 
	private graficas gr_texto3 ;
	private graficas gr_texto4;
	private int current_plot;
	/**
	 * Create the application.
	 */
	public panelPrincipal(NGramas ngramas) {
		
		this.ngramas = ngramas;
		
		initialize();
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1327, 743);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel control_panel = new JPanel();
		control_panel.setBorder(BorderFactory.createLineBorder(Color.black));

		JPanel solution_panel = new JPanel();
		solution_panel.setBorder(BorderFactory.createLineBorder(Color.black));

		JPanel graph_panel = new JPanel();
		graph_panel.setBorder(BorderFactory.createLineBorder(Color.black));

		JPanel text_panel = new JPanel();
		text_panel.setBorder(BorderFactory.createLineBorder(Color.black));

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(control_panel, GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
						.addComponent(solution_panel, GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(text_panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(graph_panel, GroupLayout.DEFAULT_SIZE, 988, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(12)
							.addComponent(graph_panel, GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(control_panel, GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)))
					.addGap(29)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(solution_panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(text_panel, GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE))
					.addContainerGap())
		);
		text_panel.setLayout(null);
		
		JTextArea texto_original = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(texto_original);
		scrollPane.setBounds(58, 31, 370, 273);
		texto_original.setBounds(58, 31, 370, 273);
		texto_original.setBorder(BorderFactory.createLineBorder(Color.black));
		texto_original.setLineWrap(true);
		text_panel.add(scrollPane);
		
		JTextArea texto_traducido = new JTextArea();
		JScrollPane scrollPane_traducido = new JScrollPane(texto_traducido);
		scrollPane_traducido.setBounds(578, 31, 370, 273);
		graph_panel.setLayout(null);
		texto_traducido.setBorder(BorderFactory.createLineBorder(Color.black));
		texto_traducido.setLineWrap(true);
		text_panel.add(scrollPane_traducido);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		gr_texto1 = new graficas();
		gr_texto2 = new graficas();
		gr_texto3 = new graficas();
		gr_texto4 = new graficas();
		tabbedPane.setBounds(18, 10, 939, 321);
		tabbedPane.addTab("Texto 1 - Trabalenguas", gr_texto1.getPlot());
		tabbedPane.addTab("Texto 2 - Lincon", gr_texto2.getPlot());
		tabbedPane.addTab("Texto 3 - Arte", gr_texto3.getPlot());
		tabbedPane.addTab("Texto 4 - Código", gr_texto4.getPlot());
		graph_panel.add(tabbedPane);
		solution_panel.setLayout(null);
		
		JLabel lblNewLabel_6 = new JLabel("Mejor Respuesta");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setBounds(10, 25, 267, 13);
		solution_panel.add(lblNewLabel_6);
		
		JLabel lbl_fitness = new JLabel("Fitness: ");
		lbl_fitness.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_fitness.setBounds(10, 61, 267, 13);
		solution_panel.add(lbl_fitness);
		
		JLabel lblNewLabel_8 = new JLabel("a b c d e f g h i j k l m n o p q r s t u v w x y z ");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setBounds(10, 126, 267, 13);
		solution_panel.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("______________________________");
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_9.setBounds(10, 154, 267, 13);
		solution_panel.add(lblNewLabel_9);
		
		JLabel lbl_cromosoma = new JLabel("");
		lbl_cromosoma.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_cromosoma.setBounds(10, 177, 267, 13);
		solution_panel.add(lbl_cromosoma);
		
		JLabel label = new JLabel("New label");
		label.setBounds(105, 84, 45, 0);
		solution_panel.add(label);
		
		JLabel lbl_mejorGeneracion = new JLabel("Generacion: ");
		lbl_mejorGeneracion.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_mejorGeneracion.setBounds(20, 93, 257, 13);
		solution_panel.add(lbl_mejorGeneracion);
		control_panel.setLayout(null);
		
		JLabel label_n = new JLabel("Tama\u00F1o Poblacion");
		label_n.setHorizontalAlignment(SwingConstants.RIGHT);
		label_n.setBounds(10, 24, 117, 13);
		control_panel.add(label_n);
		
		tf_n = new JTextField("120");
		tf_n.setBounds(137, 21, 96, 19);
		control_panel.add(tf_n);
		tf_n.setColumns(10);
		
		JLabel label_generaciones = new JLabel("Numero Generaciones");
		label_generaciones.setHorizontalAlignment(SwingConstants.RIGHT);
		label_generaciones.setBounds(10, 53, 117, 13);
		control_panel.add(label_generaciones);
		
		tf_generaciones = new JTextField("200");
		tf_generaciones.setBounds(137, 50, 96, 19);
		control_panel.add(tf_generaciones);
		tf_generaciones.setColumns(10);
		
		JLabel label_elite = new JLabel("Elite");
		label_elite.setHorizontalAlignment(SwingConstants.RIGHT);
		label_elite.setBounds(10, 85, 117, 13);
		control_panel.add(label_elite);
		
		tf_elite = new JTextField("2");
		tf_elite.setBounds(137, 82, 96, 19);
		control_panel.add(tf_elite);
		tf_elite.setColumns(10);
		
		JLabel label_seleccion = new JLabel("Selecci\u00F3n");
		label_seleccion.setBounds(10, 169, 67, 13);
		control_panel.add(label_seleccion);
		
		JComboBox comboBox_seleccion = new JComboBox();
		comboBox_seleccion.setModel(new DefaultComboBoxModel(new String[] {"Ruleta", "Torneo", "Torneo Probabilistico", "Estocastico", "Restos", "Truncamiento", " Ranking"}));
		comboBox_seleccion.setBounds(101, 165, 85, 21);
		control_panel.add(comboBox_seleccion);
		
		JLabel label_cruce = new JLabel("Cruce");
		label_cruce.setBounds(10, 202, 67, 13);
		control_panel.add(label_cruce);
		
		JComboBox comboBox_cruce = new JComboBox();
		comboBox_cruce.setModel(new DefaultComboBoxModel(new String[] {"PMX", "OX", "CX", "ERX", "CO", "OXPP"}));
		comboBox_cruce.setBounds(101, 198, 85, 21);
		control_panel.add(comboBox_cruce);
		
		textField_probCruce = new JTextField("60");
		textField_probCruce.setBounds(188, 199, 45, 19);
		control_panel.add(textField_probCruce);
		textField_probCruce.setColumns(10);
		
		JLabel labrl_mutacion = new JLabel("Mutacion");
		labrl_mutacion.setBounds(10, 239, 67, 13);
		control_panel.add(labrl_mutacion);
		
		JComboBox comboBox_mutacion = new JComboBox();
		comboBox_mutacion.setModel(new DefaultComboBoxModel(new String[] {"Inserci\u00F3n", "Intercambio", "Inversi\u00F3n", "Heur\u00EDstica", "Intercambio doble", "Incremento"}));
		comboBox_mutacion.setBounds(101, 235, 85, 21);
		control_panel.add(comboBox_mutacion);
		
		textField_probMutacion = new JTextField("40");
		textField_probMutacion.setBounds(188, 236, 45, 19);
		control_panel.add(textField_probMutacion);
		textField_probMutacion.setColumns(10);
		
		JButton ejecutar = new JButton("Ejecutar");
		ejecutar.setBounds(101, 309, 85, 21);
		control_panel.add(ejecutar);
		
		JCheckBox apocalipsisBox = new JCheckBox("Apocalipsis");
		apocalipsisBox.setBounds(26, 282, 102, 21);
		control_panel.add(apocalipsisBox);
		
		JLabel lblNewLabel = new JLabel("%");
		lblNewLabel.setBounds(243, 85, 26, 13);
		control_panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("%");
		lblNewLabel_1.setBounds(243, 202, 26, 13);
		control_panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("%");
		lblNewLabel_2.setBounds(243, 239, 26, 13);
		control_panel.add(lblNewLabel_2);
		
		JCheckBox desastreBox = new JCheckBox("Desastre Natural");
		desastreBox.setBounds(137, 282, 132, 21);
		control_panel.add(desastreBox);
		frame.getContentPane().setLayout(groupLayout);
		
		ejecutar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				texto_traducido.setText("SE HA TRADUCIDO CORRECTAMENTE");
				int n = Integer.parseInt(tf_n.getText());
				int numGeneraciones = Integer.parseInt(tf_generaciones.getText());
				double elite = Double.parseDouble(tf_elite.getText());
				
				Seleccion metodoSeleccion = FactoriaSeleccion.getAlgoritmoDeSeleccion(comboBox_seleccion.getSelectedItem().toString());
				Cruce metodoCruce = FactoriaCruces.getAlgoritmoDeCruce(comboBox_cruce.getSelectedItem().toString());
				Mutacion metodoMutacion = FactoriaMutacion.getAlgoritmoDeMutacion(comboBox_mutacion.getSelectedItem().toString());
				
				double probCruce = Double.parseDouble(textField_probCruce.getText());
				double probMutacion = Double.parseDouble(textField_probMutacion.getText());
				
				claseTexto = new Texto(textoOriginal, textoAyuda);
				AlgoritmoGenetico  AG = new AlgoritmoGenetico(n, numGeneraciones, metodoSeleccion, metodoCruce, probCruce, 
						metodoMutacion, probMutacion, elite, ngramas, claseTexto, apocalipsisBox.isSelected(), desastreBox.isSelected());
				//AlgoritmoGenetico ag = new AlgoritmoGenetico();
				AG.startAlgorithm();
				actualizaPlots(Integer.parseInt(tf_generaciones.getText()), AG.getArrayMejorAbsoluto(), AG.getMejorFitnessGeneracion(), AG.getMediaFitnessGeneracion(), AG.getPresionSelectivaArray());
				lbl_fitness.setText("Fitness = " + AG.getMejorFitnessAbsoluto());
				lbl_cromosoma.setText(AG.getMejorCromosomaAbsoluto().toString());
				texto_traducido.setText(AG.getMejorFenotipoAbsoluto().toString());
				lbl_mejorGeneracion.setText("Generacion: " + AG.getMejorGeneracion());
				
			}
		});
		
		tabbedPane.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				current_plot = tabbedPane.getSelectedIndex();
				seleccionaFichero(current_plot);
				texto_original.setText("");
				String pathString = System.getProperty("user.dir") + File.separator + eleccion + ".txt";
				String pathStringAyuda = System.getProperty("user.dir") + File.separator + eleccion + "-Ayuda.txt";
				
				File fichero = new File(pathString);
				File ficheroAyuda = new File(pathStringAyuda);
				
				 try {
				        BufferedReader in;
				        in = new BufferedReader(new FileReader(fichero));
				        
				        textoOriginal = new StringBuilder();
				        String line = in.readLine();
				        while (line != null) {
				        	texto_original.setText(texto_original.getText() + "\n" + line);
				        	textoOriginal.append(line);
				            line = in.readLine();
				        }
				        
				        BufferedReader inAyuda = new BufferedReader(new FileReader(ficheroAyuda));
				        textoAyuda = new StringBuilder();
				        String lineAyuda = inAyuda.readLine();
				        while (lineAyuda != null) {
				        	
				        	textoAyuda.append(lineAyuda);
				        	lineAyuda = inAyuda.readLine();
				        }
				    } catch (Exception ex) {
				        ex.printStackTrace();
				    }
			}
		});
	}
	
	
	private void seleccionaFichero(int index) {
		switch (index) {
			case 0: {
				eleccion = "Pruebas" + File.separator + "Uno";
				break;
			}
			case 1: {
				eleccion = "Pruebas" + File.separator + "Dos";
				break;
			}
			case 2: {
				eleccion = "Pruebas" + File.separator + "Tres";
				break;
			}
			case 3: {
				eleccion = "Pruebas" + File.separator + "Cuatro";
				break;
			}

		}
	}
	
	private void actualizaPlots(int numGeneraciones, double[] mejorAbsoluto, double[] mejorGeneracion, double[] mediaGeneracion, double[] presionSelectiva) {
		
		switch (current_plot) {
			case 0: {
				gr_texto1.actualiza(numGeneraciones, mejorAbsoluto, mejorGeneracion, mediaGeneracion, presionSelectiva);
				break;
			}case 1: {
				gr_texto2.actualiza(numGeneraciones, mejorAbsoluto, mejorGeneracion, mediaGeneracion, presionSelectiva);
				break;
			}case 2: {
				gr_texto3.actualiza(numGeneraciones, mejorAbsoluto, mejorGeneracion, mediaGeneracion, presionSelectiva);
				break;
			}case 3: {
				gr_texto4.actualiza(numGeneraciones, mejorAbsoluto, mejorGeneracion, mediaGeneracion, presionSelectiva);
				break;
			}
			
		}
	}
}
