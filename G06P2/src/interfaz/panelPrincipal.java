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
import javax.swing.JScrollPane;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.Font;

public class panelPrincipal {

	private JFrame frame;
	private JTextField n;
	private JTextField nGeneraciones;
	private JTextField textField_n;
	private JTextField textField_nGeneraciones;
	private JTextField textField_error;
	private JTextField tf_n;
	private JTextField tf_generaciones;
	private JTextField tf_elitismo;
	private JTextField tf_probCruce;
	private JTextField tf_probMutacion;
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
		frame.setBounds(300, 300, 900, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//////////////////////////////////////////////////////////////////////////////////		CONTROLES

		JPanel control_panel = new JPanel();
		control_panel.setBounds(10, 10, 385, 503);
		control_panel.setBorder(BorderFactory.createLineBorder(Color.black));
		frame.getContentPane().add(control_panel);
		control_panel.setLayout(null);
		
		JLabel lbl_n = new JLabel("Tama\u00F1o Poblacion");
		lbl_n.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_n.setBounds(26, 21, 109, 37);
		control_panel.add(lbl_n);
		
		tf_n = new JTextField();
		tf_n.setBounds(157, 21, 109, 37);
		control_panel.add(tf_n);
		tf_n.setColumns(10);
		
		JLabel lbl_generaciones = new JLabel("Generaciones");
		lbl_generaciones.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_generaciones.setBounds(26, 68, 109, 35);
		control_panel.add(lbl_generaciones);
		
		tf_generaciones = new JTextField();
		tf_generaciones.setBounds(157, 68, 109, 35);
		control_panel.add(tf_generaciones);
		tf_generaciones.setColumns(10);
		
		JLabel lbl_elitismo = new JLabel("Elitismo");
		lbl_elitismo.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_elitismo.setBounds(66, 113, 69, 38);
		control_panel.add(lbl_elitismo);
		
		tf_elitismo = new JTextField();
		tf_elitismo.setBounds(157, 113, 109, 38);
		control_panel.add(tf_elitismo);
		tf_elitismo.setColumns(10);
		
		JLabel lbl_seleccion = new JLabel("Seleccion");
		lbl_seleccion.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_seleccion.setBounds(40, 229, 95, 17);
		control_panel.add(lbl_seleccion);
		
		JComboBox comboBox_seleccion = new JComboBox();
		comboBox_seleccion.setModel(new DefaultComboBoxModel(new String[] {"Ruleta","Torneo", "Torneo Probabilistico", "Estocastico", "Restos", "Truncamiento"," Ranking"}));
		comboBox_seleccion.setBounds(157, 225, 109, 21);
		control_panel.add(comboBox_seleccion);
		
		JLabel lbl_cruce = new JLabel("Cruce");
		lbl_cruce.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_cruce.setBounds(52, 276, 83, 17);
		control_panel.add(lbl_cruce);
		
		JComboBox comboBox_cruce = new JComboBox();
		comboBox_cruce.setModel(new DefaultComboBoxModel(new String[] {"PMX", "OX", "CX", "ERX", "CO"}));
		comboBox_cruce.setBounds(157, 274, 109, 19);
		control_panel.add(comboBox_cruce);
		
		tf_probCruce = new JTextField();
		tf_probCruce.setBounds(289, 275, 75, 19);
		control_panel.add(tf_probCruce);
		tf_probCruce.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Mutacion");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(40, 319, 95, 13);
		control_panel.add(lblNewLabel);
		
		JComboBox comboBox_mutacion = new JComboBox();
		comboBox_mutacion.setModel(new DefaultComboBoxModel(new String[] {"Inserci\u00F3n", "Intercambio", "Inversi\u00F3n", "Heur\u00EDstica"}));
		comboBox_mutacion.setBounds(157, 315, 109, 21);
		control_panel.add(comboBox_mutacion);
		
		tf_probMutacion = new JTextField();
		tf_probMutacion.setBounds(289, 316, 75, 19);
		control_panel.add(tf_probMutacion);
		tf_probMutacion.setColumns(10);
		
		JButton btn_ejecutar = new JButton("Ejecutar");
		btn_ejecutar.setBounds(40, 402, 120, 35);
		control_panel.add(btn_ejecutar);
		
		JButton btn_elegirFichero = new JButton("Elegir Fichero");
		btn_elegirFichero.setBounds(214, 402, 120, 35);
		control_panel.add(btn_elegirFichero);
		
		
		JLabel lblNewLabel_1 = new JLabel("Metodo");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(157, 202, 109, 13);
		control_panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Probabilidad");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(289, 202, 75, 13);
		control_panel.add(lblNewLabel_2);
		
		//////////////////////////////////////////////////////////////////////////////////		SOLUCION
		JPanel solution_panel = new JPanel();
		solution_panel.setBounds(10, 523, 385, 322);
		solution_panel.setBorder(BorderFactory.createLineBorder(Color.black));
		frame.getContentPane().add(solution_panel);
		solution_panel.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Mejor Respuesta");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(138, 30, 108, 23);
		solution_panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Fitness = uwu");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_4.setBounds(123, 68, 135, 36);
		solution_panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Cromosoma");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setBounds(150, 114, 83, 23);
		solution_panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("a b c d e f g h i j k l m n o p q r s t u v w x y z");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setBounds(87, 152, 236, 23);
		solution_panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("________________________________________");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setBounds(76, 185, 268, 23);
		solution_panel.add(lblNewLabel_7);
		
		JLabel lblNewLabel_6_1 = new JLabel("a b c d e f g h i j k l m n o p q r s t u v w x y z");
		lblNewLabel_6_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_6_1.setBounds(87, 226, 236, 23);
		solution_panel.add(lblNewLabel_6_1);
		
		//////////////////////////////////////////////////////////////////////////////////		GRAFICA

		JPanel graph_panel = new JPanel();
		graficas gr = new graficas();
		//graph_panel.setLayout(null);
		graph_panel.setBounds(400, 10, 1100, 503);
		graph_panel.setBorder(BorderFactory.createLineBorder(Color.black));
		graph_panel.add(gr.getPlot());
		frame.getContentPane().add(graph_panel);
		
		//////////////////////////////////////////////////////////////////////////////////		TEXT AREA

		JPanel text_panel = new JPanel();
		text_panel.setBounds(400, 523, 1100, 322);
		text_panel.setBorder(BorderFactory.createLineBorder(Color.black));
		frame.getContentPane().add(text_panel);
		text_panel.setLayout(null);
		
		JTextArea texto_original = new JTextArea();
		texto_original.setText("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
		texto_original.setBounds(54, 29, 410, 283);
		texto_original.setLineWrap(true);
		texto_original.setBorder(BorderFactory.createLineBorder(Color.black));
		text_panel.add(texto_original);
		
		JTextArea texto_traducido = new JTextArea();
		texto_traducido.setText("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
		texto_traducido.setBounds(550, 29, 410, 283);
		texto_traducido.setLineWrap(true);
		texto_traducido.setBorder(BorderFactory.createLineBorder(Color.black));
		text_panel.add(texto_traducido);

		
		btn_elegirFichero.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser jFileChooser = new JFileChooser();
				jFileChooser.setCurrentDirectory(new File(System.getProperty("user.dir") + System.getProperty("file.separator")+ "Pruebas"));
				
				int result = jFileChooser.showOpenDialog(new JFrame());
				
				if (result == JFileChooser.APPROVE_OPTION) {
		            File file = jFileChooser.getSelectedFile();
		            try {
		                BufferedReader in;
		                in = new BufferedReader(new FileReader(file));
		                String line = in.readLine();
		                while (line != null) {
		                    texto_original.setText(line + "\n");
		                    line = in.readLine();
		                }
		            } catch (Exception ex) {
		                
		            }
				}
				
			}
		});
		
		btn_ejecutar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				texto_traducido.setText("SE HA TRADUCIDO CORRECTAMENTE");
			}
		});
		
	}
}
