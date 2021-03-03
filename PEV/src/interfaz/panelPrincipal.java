package interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import org.math.plot.Plot2DPanel;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.Label;

public class panelPrincipal {

	private JFrame frame;
	private JTextField n;
	private JTextField nGeneraciones;


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
		frame.setBounds(100, 100, 1016, 884);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel controles = new JPanel();
		//controles.setBorder(BorderFactory.createLineBorder(Color.black));
		frame.getContentPane().add(controles, BorderLayout.WEST);
		controles.setLayout(new GridLayout(10, 1, 10, 10));
		
		JPanel poblacion_panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) poblacion_panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		JLabel lblNewLabel_1 = new JLabel("Tamaño poblacion");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		poblacion_panel.add(lblNewLabel_1);
		
		n = new JTextField();
		n.setText("0");
		poblacion_panel.add(n);
		n.setColumns(10);
		controles.add(poblacion_panel);
		
		JPanel generaciones = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) generaciones.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		JLabel lblNewLabel_2 = new JLabel("Número de generaciones");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		generaciones.add(lblNewLabel_2);
		
		nGeneraciones = new JTextField();
		nGeneraciones.setText("0");
		generaciones.add(nGeneraciones);
		nGeneraciones.setColumns(10);
		controles.add(generaciones);
		
		JPanel error_panel = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) error_panel.getLayout();
		flowLayout_2.setAlignment(FlowLayout.RIGHT);
		JLabel errorlbl = new JLabel("Valor de error");
		errorlbl.setHorizontalAlignment(SwingConstants.CENTER);
		error_panel.add(errorlbl);
		
		JTextField textField_error = new JTextField();
		textField_error.setText("0");
		error_panel.add(textField_error);
		textField_error.setColumns(10);
		controles.add(error_panel);
		////////////////////////////////////////////////		SELECCION 
		JPanel seleccion_panel = new JPanel();
		controles.add(seleccion_panel);
		seleccion_panel.setLayout(new GridLayout(2, 1, 0, 5));
		
		JLabel lblSeleccion = new JLabel("Tipo de selección");
		seleccion_panel.add(lblSeleccion);
		
		JComboBox metodoSeleccion = new JComboBox();
		metodoSeleccion.setModel(new DefaultComboBoxModel(new String[] {"Método de la ruleta", "Universal Estoc\u00E1stico", "Torneo", "Truncamiento", "Restos"}));
		seleccion_panel.add(metodoSeleccion);
		
		TitledBorder title_Seleccion = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Selección");
		seleccion_panel.setBorder(title_Seleccion);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		seleccion_panel.add(horizontalStrut);
		
		////////////////////////////////////////////////		CRUCE 
		JPanel cruce_panel = new JPanel();
		controles.add(cruce_panel);
		cruce_panel.setLayout(new GridLayout(2, 1, 0, 5));
		
		JLabel lblCruce = new JLabel("Tipo de cruce");
		cruce_panel.add(lblCruce);
		
		JComboBox metodoCruce = new JComboBox();
		metodoCruce.setModel(new DefaultComboBoxModel(new String[] {"Monopunto", "Discreto Uniforme", "Aritmético", "BLX-alpha "}));
		cruce_panel.add(metodoCruce);
		
		JLabel lblporcentajeCruce = new JLabel("% cruce");
		cruce_panel.add(lblporcentajeCruce);
		
		JTextField textField_cruce = new JTextField();
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
		
		JComboBox metodoMutacion = new JComboBox();
		metodoMutacion.setModel(new DefaultComboBoxModel(new String[] {"Mutación basica"}));
		mutacion_panel.add(metodoMutacion);
		
		JLabel lblporcentajeMutacion = new JLabel("% Mutacion");
		mutacion_panel.add(lblporcentajeMutacion);
		
		JTextField textField_mutacion = new JTextField();
		mutacion_panel.add(textField_mutacion);
		textField_mutacion.setColumns(10);
		
		TitledBorder borde_mutacion = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Mutacion básica");
		mutacion_panel.setBorder(borde_mutacion);
		
		////////////////////////////////////////////////		ÉLITE 
		JPanel elite_panel = new JPanel();
		controles.add(elite_panel);
		elite_panel.setLayout(new GridLayout(2, 1, 0, 5));
		
		JLabel porcentajeElite = new JLabel("% Élite");
		elite_panel.add(porcentajeElite);
		
		JTextField textField_elite = new JTextField();
		elite_panel.add(textField_elite);
		textField_elite.setColumns(10);
		
		TitledBorder title_elite = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Élite");
		elite_panel.setBorder(title_elite);
		
		////////////////////////////////////////////////		GRAFICA


		graficas gr = new graficas();
		frame.getContentPane().add(gr.getPlot(), BorderLayout.CENTER);
		
		////////////////////////////////////////////////		

		JPanel problemas = new JPanel();
		frame.getContentPane().add(problemas, BorderLayout.NORTH);
		
		JCheckBox chckbxIntroducir_variedad = new JCheckBox("Introducir variedad");
		problemas.add(chckbxIntroducir_variedad);
		
		JLabel lblNewLabel = new JLabel("Problema");
		problemas.add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Funcion 1", "Funcion Sch\u00FCbert", "Funcion H\u00F6lder table", "Funci\u00F3n Michalewicz"}));
		problemas.add(comboBox);
		

		
		
	}

}
