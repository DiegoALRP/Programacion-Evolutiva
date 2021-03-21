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
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.text.JTextComponent;

import org.math.plot.Plot2DPanel;

import algoritmoGenetico.AlgoritmoGenetico;
import algoritmoGenetico.cruces.Cruce;
import algoritmoGenetico.cruces.FactoriaCruces;
import algoritmoGenetico.mutaciones.FactoriaMutaciones;
import algoritmoGenetico.mutaciones.Mutacion;
import algoritmoGenetico.seleccion.FactoriaSelecciones;
import algoritmoGenetico.seleccion.Seleccion;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Label;

public class panelPrincipal {

	private JFrame frame;
	private JTextField n;
	private JTextField nGeneraciones;
	
	private String seleccion;
	private String cruce;
	private String mutacion;
	private String funcion;


	/**
	 * Create the application.
	 */
	public panelPrincipal() {
		initialize();
		frame.setVisible(true);
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
		controles.setLayout(new GridLayout(12, 1, 10, 10));
		
		////////////////////////////////////////////////		POBLACION 

		JPanel poblacion_panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) poblacion_panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		JLabel lblNewLabel_1 = new JLabel("Tamaño poblacion");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		poblacion_panel.add(lblNewLabel_1);
		
		n = new JTextField();
		n.setText("100");
		poblacion_panel.add(n);
		n.setColumns(10);
		controles.add(poblacion_panel);

		////////////////////////////////////////////////		GENERACIONES 
		JPanel generaciones = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) generaciones.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		JLabel lblNewLabel_2 = new JLabel("Número de generaciones");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		generaciones.add(lblNewLabel_2);
		
		nGeneraciones = new JTextField();
		nGeneraciones.setText("100");
		generaciones.add(nGeneraciones);
		nGeneraciones.setColumns(10);
		controles.add(generaciones);

		////////////////////////////////////////////////		PRECISION
		JPanel error_panel = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) error_panel.getLayout();
		flowLayout_2.setAlignment(FlowLayout.RIGHT);
		JLabel errorlbl = new JLabel("Valor de error");
		errorlbl.setHorizontalAlignment(SwingConstants.CENTER);
		error_panel.add(errorlbl);
		
		JTextField textField_error = new JTextField();
		textField_error.setText("0.0001");
		error_panel.add(textField_error);
		textField_error.setColumns(10);
		controles.add(error_panel);

		////////////////////////////////////////////////		SELECCION 
		JPanel seleccion_panel = new JPanel();
		controles.add(seleccion_panel);
		seleccion_panel.setLayout(new GridLayout(2, 1, 0, 5));
		
		JLabel lblSeleccion = new JLabel("Tipo de selección");
		seleccion_panel.add(lblSeleccion);
		
		JComboBox Seleccion_cbox = new JComboBox();
		
		Seleccion_cbox.setModel(new DefaultComboBoxModel(new String[] {"Metodo de la ruleta", "Universal Estocastico", "Torneo", "Truncamiento", "Restos"}));
		seleccion_panel.add(Seleccion_cbox);
		
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
		controles.add(mutacion_panel);
		mutacion_panel.setLayout(new GridLayout(2, 1, 0, 5));
		
		JLabel lbltipoMutacion = new JLabel("Tipo de mutacion");
		mutacion_panel.add(lbltipoMutacion);
		
		JComboBox Mutacion_cbox = new JComboBox();
		Mutacion_cbox.setModel(new DefaultComboBoxModel(new String[] {"Mutación basica"}));
		mutacion_panel.add(Mutacion_cbox);
		
		JLabel lblporcentajeMutacion = new JLabel("% Mutacion");
		mutacion_panel.add(lblporcentajeMutacion);
		
		JTextField textField_mutacion = new JTextField();
		textField_mutacion.setText("0.05");
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
		textField_elite.setText("0.02");
		elite_panel.add(textField_elite);
		textField_elite.setColumns(10);
		
		TitledBorder title_elite = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Élite");
		elite_panel.setBorder(title_elite);


		////////////////////////////////////////////////		GRAFICA
		graficas gr = new graficas(Integer.parseInt(nGeneraciones.getText()));
		frame.getContentPane().add(gr.getPlot(), BorderLayout.CENTER);
		
		////////////////////////////////////////////////
		
		JComboBox individuo_cbox = new JComboBox();
		individuo_cbox.setModel(new DefaultComboBoxModel(new String[] {"Funcion 1", "Funcion Schubert", "Funcion Holder table", "Funcion Michalewicz (Booleanos)", "Funcion Michalewicz (Reales)", "Funcion Bukin", "Funcion Matyas"}));
		individuo_cbox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				funcion = individuo_cbox.getSelectedItem().toString();
				if(individuo_cbox.getSelectedItem().toString().equals("Función Michalewicz")) {
					Cruce_cbox.setModel(new DefaultComboBoxModel(new String[] {"Monopunto","Discreto Uniforme", "Aritmetico", "BLX-alpha"}));
					Mutacion_cbox.setModel(new DefaultComboBoxModel(new String[] {"Mutación basica", "Mutacion Uniforme"})); 
				} else {
					Cruce_cbox.setModel(new DefaultComboBoxModel(new String[] {"Monopunto","Discreto Uniforme"}));
					Mutacion_cbox.setModel(new DefaultComboBoxModel(new String[] {"Mutacion basica"})); 
				}
			}
		});
		
		JButton resetear = new JButton("Resetear");
		JButton ejecutar = new JButton("Ejecutar");
		controles.add(ejecutar);
		controles.add(resetear);
		
		JLabel solucion = new JLabel("Solucion :");
		JTextField textField_solucion = new JTextField();
		textField_solucion.setText("");
		controles.add(solucion);
		controles.add(textField_solucion);

		
		ejecutar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				int numGeneraciones = Integer.parseInt(nGeneraciones.getText());
				double elite = Double.parseDouble(textField_elite.getText());
				double porcCruce = Double.parseDouble(textField_cruce.getText());
				double porcMutacion = Double.parseDouble(textField_mutacion.getText());
				double precision = Double.parseDouble(textField_error.getText());
				int tamPoblacion = Integer.parseInt(n.getText());
				
				Seleccion metodoSeleccion = FactoriaSelecciones.getAlgoritmoDeSeleccion(Seleccion_cbox.getSelectedItem().toString(), numGeneraciones);
				Cruce metodoCruce = FactoriaCruces.getAlgoritmoDeCruce(Cruce_cbox.getSelectedItem().toString(), numGeneraciones);
				Mutacion metodoMutacion = FactoriaMutaciones.getAlgoritmoDeMutacion(Mutacion_cbox.getSelectedItem().toString(), numGeneraciones);
				String tipoIndividuo = individuo_cbox.getSelectedItem().toString();
				AlgoritmoGenetico AG = new AlgoritmoGenetico(tamPoblacion, Integer.parseInt(nGeneraciones.getText()), precision, metodoSeleccion, metodoCruce, porcCruce, metodoMutacion, porcMutacion, elite, tipoIndividuo);
				textField_solucion.setText(AG.getSolucion());
				gr.actualiza(numGeneraciones, AG.getMejorAbsoluto(), AG.getMejorGeneracion(), AG.getMediaGeneracion());
			}
		});
		////////////////////////////////////////////////		

		JPanel problemas = new JPanel();
		frame.getContentPane().add(problemas, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Problema");
		problemas.add(lblNewLabel);
		
		problemas.add(individuo_cbox);
		
		JLabel solucion = new JLabel("Soluion");
		problemas.add(solucion);
	}

}
