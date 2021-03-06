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
import javax.swing.JTextArea;
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
	
	private AlgoritmoGenetico AG;


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
		JLabel lblNewLabel_1 = new JLabel("Tama???o poblacion");
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
		JLabel lblNewLabel_2 = new JLabel("N???mero de generaciones");
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
		
		JLabel lblSeleccion = new JLabel("Tipo de selecci???n");
		seleccion_panel.add(lblSeleccion);
		
		JComboBox Seleccion_cbox = new JComboBox();
		
		Seleccion_cbox.setModel(new DefaultComboBoxModel(new String[] {"Metodo de la ruleta", "Universal Estocastico", "Torneo Deterministico", "Torneo Probabilistico", "Truncamiento", "Restos"}));
		seleccion_panel.add(Seleccion_cbox);
		
		TitledBorder title_Seleccion = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Selecci???n");
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
		Mutacion_cbox.setModel(new DefaultComboBoxModel(new String[] {"Mutaci???n basica"}));
		mutacion_panel.add(Mutacion_cbox);
		
		JLabel lblporcentajeMutacion = new JLabel("% Mutacion");
		mutacion_panel.add(lblporcentajeMutacion);
		
		JTextField textField_mutacion = new JTextField();
		textField_mutacion.setText("0.05");
		mutacion_panel.add(textField_mutacion);
		textField_mutacion.setColumns(10);
		
		TitledBorder borde_mutacion = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Mutacion b???sica");
		mutacion_panel.setBorder(borde_mutacion);

		////////////////////////////////////////////////		???LITE 
		JPanel elite_panel = new JPanel();
		controles.add(elite_panel);
		elite_panel.setLayout(new GridLayout(2, 1, 0, 5));
		
		JLabel porcentajeElite = new JLabel("% ???lite");
		elite_panel.add(porcentajeElite);
		
		JTextField textField_elite = new JTextField();
		textField_elite.setText("0.02");
		elite_panel.add(textField_elite);
		textField_elite.setColumns(10);
		
		TitledBorder title_elite = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "???lite");
		elite_panel.setBorder(title_elite);


		////////////////////////////////////////////////		GRAFICA
		graficas gr = new graficas(Integer.parseInt(nGeneraciones.getText()));
		frame.getContentPane().add(gr.getPlot(), BorderLayout.CENTER);
		
		////////////////////////////////////////////////
		
		JLabel paramN = new JLabel("		n: ");
		JTextField paramNText = new JTextField();
		paramNText.setText("6");
		Dimension dimensionN = new Dimension(50, 20);
		paramNText.setPreferredSize(dimensionN);
		
		
		JPanel problemas = new JPanel();
		frame.getContentPane().add(problemas, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Problema");
		problemas.add(lblNewLabel);
		
		JComboBox individuo_cbox = new JComboBox();
		individuo_cbox.setModel(new DefaultComboBoxModel(new String[] {"Funcion 1", "Funcion 1 (Reales)", "Funcion Schubert", "Funcion Schubert (Reales)",
				"Funcion Holder table", "Funcion Holder table (Reales)",
				"Funcion Michalewicz (Booleanos)", "Funcion Michalewicz (Reales)", "Funcion Bukin", "Funcion Matyas"}));
		individuo_cbox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				funcion = individuo_cbox.getSelectedItem().toString();
				if (individuo_cbox.getSelectedItem().toString().equals("Funcion Michalewicz (Booleanos)") || individuo_cbox.getSelectedItem().toString().equals("Funcion Michalewicz (Reales)")) {
					
					problemas.add(paramN);
					problemas.add(paramNText);
				}
				if(individuo_cbox.getSelectedItem().toString().equals("Funcion Michalewicz (Reales)") || individuo_cbox.getSelectedItem().toString().equals("Funcion Schubert (Reales)") ||
						 individuo_cbox.getSelectedItem().toString().equals("Funcion 1 (Reales)")) {
					
					Cruce_cbox.setModel(new DefaultComboBoxModel(new String[] {"Monopunto","Discreto Uniforme", "Aritmetico", "BLX-alpha"}));
					Mutacion_cbox.setModel(new DefaultComboBoxModel(new String[] {"Mutaci???n basica", "Mutacion Uniforme"}));
				} 
				else if (individuo_cbox.getSelectedItem().toString().equals("Funcion Holder table (Reales)")) {
					
					Cruce_cbox.setModel(new DefaultComboBoxModel(new String[] {"Monopunto","Discreto Uniforme", "Aritmetico"}));
					Mutacion_cbox.setModel(new DefaultComboBoxModel(new String[] {"Mutaci???n basica", "Mutacion Uniforme"}));
				}
				else {
					
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
		JTextArea solArea = new JTextArea();
		Dimension dimension = new Dimension();
		controles.add(solucion);
		controles.add(solArea);
		
		problemas.add(individuo_cbox);
		
		ejecutar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				int numGeneraciones = Integer.parseInt(nGeneraciones.getText());
				double elite = Double.parseDouble(textField_elite.getText());
				double porcCruce = Double.parseDouble(textField_cruce.getText());
				double porcMutacion = Double.parseDouble(textField_mutacion.getText());
				double precision = Double.parseDouble(textField_error.getText());
				int tamPoblacion = Integer.parseInt(n.getText());
				
				Seleccion metodoSeleccion = FactoriaSelecciones.getAlgoritmoDeSeleccion(Seleccion_cbox.getSelectedItem().toString());
				Cruce metodoCruce = FactoriaCruces.getAlgoritmoDeCruce(Cruce_cbox.getSelectedItem().toString());
				Mutacion metodoMutacion = FactoriaMutaciones.getAlgoritmoDeMutacion(Mutacion_cbox.getSelectedItem().toString());
				String tipoIndividuo = individuo_cbox.getSelectedItem().toString();
				int numN = 6;
				if (tipoIndividuo.equals("Funcion Michalewicz (Booleanos)") || tipoIndividuo.equals("Funcion Michalewicz (Reales)")) {
					
					numN = Integer.parseInt(paramNText.getText());
					dimension.setSize(500, 800);
					solArea.setPreferredSize(dimension);
				}
				AG = new AlgoritmoGenetico(tamPoblacion, numGeneraciones, precision, metodoSeleccion, metodoCruce, porcCruce, metodoMutacion, porcMutacion, elite, tipoIndividuo, numN);
				solArea.setText(AG.getSolucion());
				gr.actualiza(numGeneraciones, AG.getMejorAbsoluto(), AG.getMejorGeneracion(), AG.getMediaGeneracion());
			}
		});
		resetear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				nGeneraciones.setText("100");
				nGeneraciones.setText("100");
				textField_elite.setText("0.02");
				textField_cruce.setText("0.6");
				textField_mutacion.setText("0.05");
				textField_error.setText("0.0001");

				Seleccion_cbox.setSelectedIndex(0);
				Cruce_cbox.setSelectedIndex(0);
				Mutacion_cbox.setSelectedIndex(0);
				individuo_cbox.setSelectedIndex(0);
				
			}
		});
		////////////////////////////////////////////////		

		/*JPanel problemas = new JPanel();
		frame.getContentPane().add(problemas, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Problema");
		problemas.add(lblNewLabel);
		
		problemas.add(individuo_cbox);*/
	}

}
