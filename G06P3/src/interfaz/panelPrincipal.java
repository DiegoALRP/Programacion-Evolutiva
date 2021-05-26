package interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;


import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import algoritmoGenetico.AlgoritmoGenetico;
import algoritmoGenetico.cruces.Cruce;
import algoritmoGenetico.individuos.RastroSantaFe;
import algoritmoGenetico.mutaciones.FactoriaMutaciones;
import algoritmoGenetico.mutaciones.Mutacion;
import algoritmoGenetico.selecciones.FactoriaSeleccion;
import algoritmoGenetico.selecciones.Seleccion;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import javax.swing.SpinnerNumberModel;
import javax.swing.DefaultComboBoxModel;

public class panelPrincipal {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField porc_cruce;

	private RastroSantaFe santaFe;
	
	private panelHormiga hormiga;
	/**
	 * Create the application.
	 */
	public panelPrincipal(RastroSantaFe sf) {
		this.santaFe = sf;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(20, 20, 1004, 788);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setVisible(true);
		
		JPanel controlPanel = new JPanel();
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(controlPanel, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 673, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(23, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(21)
							.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 725, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(controlPanel, GroupLayout.PREFERRED_SIZE, 671, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		controlPanel.setLayout(null);
		
		JLabel n = new JLabel("Tama\u00F1o Poblaci\u00F3n");
		n.setHorizontalAlignment(SwingConstants.RIGHT);
		n.setBounds(10, 33, 109, 19);
		controlPanel.add(n);
		
		textField = new JTextField("100");
		textField.setBounds(149, 33, 96, 19);
		controlPanel.add(textField);
		textField.setColumns(10);
		
		JLabel numGeneraciones = new JLabel("Num generaciones");
		numGeneraciones.setHorizontalAlignment(SwingConstants.RIGHT);
		numGeneraciones.setBounds(10, 83, 109, 19);
		controlPanel.add(numGeneraciones);
		
		textField_1 = new JTextField("100");
		textField_1.setBounds(149, 83, 96, 19);
		controlPanel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel elite = new JLabel("Elite");
		elite.setHorizontalAlignment(SwingConstants.RIGHT);
		elite.setBounds(30, 134, 89, 19);
		controlPanel.add(elite);
		
		textField_2 = new JTextField("2");
		textField_2.setBounds(149, 134, 96, 19);
		controlPanel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("%");
		lblNewLabel.setBounds(246, 137, 10, 13);
		controlPanel.add(lblNewLabel);
		
		JLabel seleccion = new JLabel("Seleccion");
		seleccion.setHorizontalAlignment(SwingConstants.RIGHT);
		seleccion.setBounds(10, 340, 66, 17);
		controlPanel.add(seleccion);
		
		JComboBox comboBox_seleccion = new JComboBox();
		comboBox_seleccion.setModel(new DefaultComboBoxModel(new String[] {"Ruleta", "Torneo", "Torneo Probabilistico", "Estocastico", "Restos", "Truncamiento", "Ranking"}));
		comboBox_seleccion.setBounds(97, 339, 109, 21);
		controlPanel.add(comboBox_seleccion);
		
		JLabel cruce = new JLabel("Cruce");
		cruce.setHorizontalAlignment(SwingConstants.RIGHT);
		cruce.setBounds(10, 386, 66, 17);
		controlPanel.add(cruce);
		
		JComboBox comboBox_cruce = new JComboBox();
		comboBox_cruce.setModel(new DefaultComboBoxModel(new String[] {"Unico"}));
		comboBox_cruce.setBounds(97, 384, 109, 21);
		controlPanel.add(comboBox_cruce);
		
		JTextField porc_mutacion = new JTextField("40");
		porc_mutacion.setBounds(216, 425, 45, 19);
		controlPanel.add(porc_mutacion);
		porc_mutacion.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("%");
		lblNewLabel_1.setBounds(264, 428, 10, 13);
		controlPanel.add(lblNewLabel_1);
		
		porc_cruce = new JTextField("60");
		porc_cruce.setColumns(10);
		porc_cruce.setBounds(216, 385, 45, 19);
		controlPanel.add(porc_cruce);
		
		JLabel lblNewLabel_1_1 = new JLabel("%");
		lblNewLabel_1_1.setBounds(264, 388, 10, 13);
		controlPanel.add(lblNewLabel_1_1);
		
		JLabel mutacion = new JLabel("Mutacion");
		mutacion.setHorizontalAlignment(SwingConstants.RIGHT);
		mutacion.setBounds(10, 426, 66, 17);
		controlPanel.add(mutacion);
		
		JComboBox comboBox_mutacion = new JComboBox();
		comboBox_mutacion.setModel(new DefaultComboBoxModel(new String[] {"Terminal simple", "Funcional simple", "Arbol", "Permutacion", "Contraccion", "Expansion", "Hoist", "Subarbol"}));
		comboBox_mutacion.setBounds(97, 424, 109, 21);
		controlPanel.add(comboBox_mutacion);
		
		JLabel ProfundidadArbol = new JLabel("Profundidad Arbol");
		ProfundidadArbol.setHorizontalAlignment(SwingConstants.RIGHT);
		ProfundidadArbol.setBounds(10, 185, 109, 19);
		controlPanel.add(ProfundidadArbol);
		
		SpinnerNumberModel spinner_model = new SpinnerNumberModel(3, 1, 4, 1);
		JSpinner spinner = new JSpinner(spinner_model);
		spinner.setBounds(149, 185, 42, 20);
		controlPanel.add(spinner);
		
		JLabel numPasos = new JLabel("Num Pasos");
		numPasos.setHorizontalAlignment(SwingConstants.RIGHT);
		numPasos.setBounds(10, 230, 109, 19);
		controlPanel.add(numPasos);
		
		JTextField numPasos_tf = new JTextField("400");
		numPasos_tf.setBounds(149, 230, 96, 19);
		controlPanel.add(numPasos_tf);
		numPasos_tf.setColumns(10);
		
		JLabel Inicializacion = new JLabel("Inicializacion");
		Inicializacion.setHorizontalAlignment(SwingConstants.RIGHT);
		Inicializacion.setBounds(0, 303, 76, 17);
		controlPanel.add(Inicializacion);
		
		JComboBox comboBox_inicializacion = new JComboBox();
		comboBox_inicializacion.setModel(new DefaultComboBoxModel(new String[] {"Completo", "Creciente", "Ramped and Half"}));
		comboBox_inicializacion.setBounds(97, 301, 109, 21);
		controlPanel.add(comboBox_inicializacion);
		
		JLabel labelBloating = new JLabel("Bloating");
		labelBloating.setHorizontalAlignment(SwingConstants.RIGHT);
		labelBloating.setBounds(0, 470, 76, 17);
		controlPanel.add(labelBloating);
		
		JComboBox comboBox_Bloating = new JComboBox();
		comboBox_Bloating.setModel(new DefaultComboBoxModel(new String[] {"Tarpeian", "Penalizacion bien fundamentada", "Casera"}));
		comboBox_Bloating.setBounds(97, 470, 170, 21);
		controlPanel.add(comboBox_Bloating);
		
		JButton start_buton = new JButton("START");
		start_buton.setBounds(97, 530, 102, 21);
		controlPanel.add(start_buton);
		frame.getContentPane().setLayout(groupLayout);
		
		JLabel fitness = new JLabel("La hormiga ha comido: ");
		fitness.setBounds(7, 580, 262, 21);
		controlPanel.add(fitness);
		
		///////////////////////////////////////////////////// 			TABBED PANE
		
		hormiga = new panelHormiga(santaFe.getTablero(), new ArrayList<>());
		graficas gr = new graficas();
		tabbedPane.addTab("Hormiga", hormiga);
		tabbedPane.addTab("Grafica", gr.getPlot());
		
		
		start_buton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int tam = Integer.parseInt(textField.getText());
				int generaciones = Integer.parseInt(textField_1.getText());
				int elite = Integer.parseInt(textField_2.getText());
				int numeroPasos = Integer.parseInt(numPasos_tf.getText());
				
				Seleccion metodoSeleccion = FactoriaSeleccion.getAlgoritmoDeSeleccion(comboBox_seleccion.getSelectedItem().toString());
				Cruce metodoCruce = new Cruce();
				Mutacion metodoMutacion = FactoriaMutaciones.getAlgoritmoDeMutacion(comboBox_mutacion.getSelectedItem().toString());
	
				double probCruce = Double.parseDouble(porc_cruce.getText());
				double probMutacion = Double.parseDouble(porc_mutacion.getText());
				
				String inicializacion = (String) comboBox_inicializacion.getSelectedItem();
				String bloating = (String) comboBox_Bloating.getSelectedItem();
				int profundidad = (int) spinner.getValue();
				
				AlgoritmoGenetico AG = new AlgoritmoGenetico(tam, generaciones, metodoSeleccion, metodoCruce, probCruce,
										metodoMutacion, probMutacion, elite, inicializacion, bloating, profundidad, numeroPasos, santaFe);
				
				
				AG.startAlgorithm();
				
				hormiga.setCaminoHormiga(AG.getCaminoHormiga());
				SwingUtilities.windowForComponent(start_buton).repaint();
				
				gr.actualiza(Integer.parseInt(textField_1.getText()), AG.getArrayMejorAbsoluto(), AG.getMejorFitnessGeneracion(), AG.getMediaFitnessGeneracion(), AG.getPresionSelectiva());

				fitness.setText("La hormiga ha comido: " + (int)AG.getMejorFitness() + "/89 piezas de comida");
			}
		});
	
	}

}
