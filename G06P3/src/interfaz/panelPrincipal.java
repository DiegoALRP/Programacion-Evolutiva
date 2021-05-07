package interfaz;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;


import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JProgressBar;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import misc.Pair;

import javax.swing.SpinnerNumberModel;
import javax.swing.DefaultComboBoxModel;

public class panelPrincipal {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField porc_cruce;

	private ArrayList<Pair> comida;


	/**
	 * Create the application.
	 */
	public panelPrincipal(ArrayList<Pair> comida) {
		this.comida = comida;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1004, 788);
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
		
		textField = new JTextField();
		textField.setBounds(149, 33, 96, 19);
		controlPanel.add(textField);
		textField.setColumns(10);
		
		JLabel numGeneraciones = new JLabel("Num generaciones");
		numGeneraciones.setHorizontalAlignment(SwingConstants.RIGHT);
		numGeneraciones.setBounds(10, 83, 109, 19);
		controlPanel.add(numGeneraciones);
		
		textField_1 = new JTextField();
		textField_1.setBounds(149, 83, 96, 19);
		controlPanel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel elite = new JLabel("Elite");
		elite.setHorizontalAlignment(SwingConstants.RIGHT);
		elite.setBounds(30, 134, 89, 19);
		controlPanel.add(elite);
		
		textField_2 = new JTextField();
		textField_2.setBounds(149, 134, 96, 19);
		controlPanel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("%");
		lblNewLabel.setBounds(246, 137, 10, 13);
		controlPanel.add(lblNewLabel);
		
		JLabel seleccion = new JLabel("Seleccion");
		seleccion.setHorizontalAlignment(SwingConstants.RIGHT);
		seleccion.setBounds(10, 311, 66, 17);
		controlPanel.add(seleccion);
		
		JComboBox comboBox_seleccion = new JComboBox();
		comboBox_seleccion.setModel(new DefaultComboBoxModel(new String[] {"Ruleta", "Torneo", "Torneo Probabilistico", "Estocastico", "Restos", "Truncamiento", "Ranking"}));
		comboBox_seleccion.setBounds(97, 309, 109, 21);
		controlPanel.add(comboBox_seleccion);
		
		JLabel cruce = new JLabel("Cruce");
		cruce.setHorizontalAlignment(SwingConstants.RIGHT);
		cruce.setBounds(10, 356, 66, 17);
		controlPanel.add(cruce);
		
		JComboBox comboBox_cruce = new JComboBox();
		comboBox_cruce.setModel(new DefaultComboBoxModel(new String[] {"Unico"}));
		comboBox_cruce.setBounds(97, 354, 109, 21);
		controlPanel.add(comboBox_cruce);
		
		JTextField porc_mutacion = new JTextField();
		porc_mutacion.setBounds(216, 395, 45, 19);
		controlPanel.add(porc_mutacion);
		porc_mutacion.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("%");
		lblNewLabel_1.setBounds(264, 398, 10, 13);
		controlPanel.add(lblNewLabel_1);
		
		porc_cruce = new JTextField();
		porc_cruce.setColumns(10);
		porc_cruce.setBounds(216, 355, 45, 19);
		controlPanel.add(porc_cruce);
		
		JLabel lblNewLabel_1_1 = new JLabel("%");
		lblNewLabel_1_1.setBounds(264, 358, 10, 13);
		controlPanel.add(lblNewLabel_1_1);
		
		JLabel mutacion = new JLabel("Mutacion");
		mutacion.setHorizontalAlignment(SwingConstants.RIGHT);
		mutacion.setBounds(10, 396, 66, 17);
		controlPanel.add(mutacion);
		
		JComboBox comboBox_mutacion = new JComboBox();
		comboBox_mutacion.setModel(new DefaultComboBoxModel(new String[] {"Terminal simple", "Funcional simple", "Arbol", "Permutacion"}));
		comboBox_mutacion.setBounds(97, 394, 109, 21);
		controlPanel.add(comboBox_mutacion);
		
		JLabel ProfundidadArbol = new JLabel("Profundidad Arbol");
		ProfundidadArbol.setHorizontalAlignment(SwingConstants.RIGHT);
		ProfundidadArbol.setBounds(10, 185, 109, 19);
		controlPanel.add(ProfundidadArbol);
		
		SpinnerNumberModel spinner_model = new SpinnerNumberModel(3, 1, 4, 1);
		JSpinner spinner = new JSpinner(spinner_model);
		spinner.setBounds(149, 185, 42, 20);
		controlPanel.add(spinner);
		
		JLabel Inicializacion = new JLabel("Inicializacion");
		Inicializacion.setHorizontalAlignment(SwingConstants.RIGHT);
		Inicializacion.setBounds(0, 273, 76, 17);
		controlPanel.add(Inicializacion);
		
		JComboBox comboBox_inicializacion = new JComboBox();
		comboBox_inicializacion.setModel(new DefaultComboBoxModel(new String[] {"Completo", "Creciente", "Ramped and Half"}));
		comboBox_inicializacion.setBounds(97, 271, 109, 21);
		controlPanel.add(comboBox_inicializacion);
		
		JButton start_buton = new JButton("START");
		start_buton.setBounds(97, 472, 102, 21);
		controlPanel.add(start_buton);
		frame.getContentPane().setLayout(groupLayout);
		
		
		///////////////////////////////////////////////////// 			TABBED PANE

		panelHormiga hormiga = new panelHormiga(comida);
		
		ArrayList<Pair> a = new ArrayList<Pair>();
		a.add(new Pair(4,5));
		
		hormiga.setCaminoHormiga(a);
		graficas gr = new graficas();
		tabbedPane.addTab("Hormiga", hormiga);
		tabbedPane.addTab("Grafica", gr.getPlot());
	
	}

}
