package prueba;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class vista {

  private JFrame frame;
  private JTextField textField;
  private JTextField textField_1;
  private JTextField textField_2;
  private JTextField textField_3;
  private JTextField textField_4;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          vista window = new vista();
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
  public vista() {
    initialize();
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
    frame = new JFrame();
    frame.setBounds(100, 100, 1327, 743);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    JPanel control = new JPanel();
    
    JPanel panel_1 = new JPanel();
    
    JPanel panel_2 = new JPanel();
    
    JPanel panel_3 = new JPanel();
    GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
    groupLayout.setHorizontalGroup(
      groupLayout.createParallelGroup(Alignment.LEADING)
        .addGroup(groupLayout.createSequentialGroup()
          .addGap(10)
          .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
            .addComponent(control, GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
            .addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE))
          .addGap(18)
          .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
            .addComponent(panel_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 988, Short.MAX_VALUE))
          .addContainerGap())
    );
    groupLayout.setVerticalGroup(
      groupLayout.createParallelGroup(Alignment.LEADING)
        .addGroup(groupLayout.createSequentialGroup()
          .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(groupLayout.createSequentialGroup()
              .addGap(12)
              .addComponent(panel_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
              .addGap(29))
            .addGroup(groupLayout.createSequentialGroup()
              .addContainerGap()
              .addComponent(control, GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
              .addGap(18)))
          .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
            .addComponent(panel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE))
          .addContainerGap())
    );
    panel_2.setLayout(null);
    
    JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
    graficas gr = new graficas();
    tabbedPane.setBounds(60, 10, 970, 333);
    tabbedPane.addTab("grafica 1", gr.getPlot());
    panel_2.add(tabbedPane);
    panel_1.setLayout(null);
    
    JLabel lblNewLabel_6 = new JLabel("New label");
    lblNewLabel_6.setBounds(106, 21, 64, 13);
    panel_1.add(lblNewLabel_6);
    
    JLabel lblNewLabel_7 = new JLabel("New label");
    lblNewLabel_7.setBounds(106, 56, 64, 13);
    panel_1.add(lblNewLabel_7);
    
    JLabel lblNewLabel_8 = new JLabel("a b c d e f g h i j k l m n o p q r s t u v w x y z ");
    
    lblNewLabel_8.setBounds(20, 101, 222, 13);
    panel_1.add(lblNewLabel_8);
    
    JLabel lblNewLabel_9 = new JLabel("______________________________");
    lblNewLabel_9.setBounds(46, 124, 186, 13);
    panel_1.add(lblNewLabel_9);
    
    JLabel lblNewLabel_8_1 = new JLabel("a b c d e f g h i j k l m n o p q r s t u v w x y z ");
    lblNewLabel_8_1.setBounds(20, 147, 222, 13);
    panel_1.add(lblNewLabel_8_1);
    control.setLayout(null);
    
    JLabel lblNewLabel = new JLabel("New label");
    lblNewLabel.setBounds(10, 24, 67, 13);
    control.add(lblNewLabel);
    
    textField = new JTextField();
    textField.setBounds(101, 21, 96, 19);
    control.add(textField);
    textField.setColumns(10);
    
    JLabel lblNewLabel_1 = new JLabel("New label");
    lblNewLabel_1.setBounds(10, 53, 67, 13);
    control.add(lblNewLabel_1);
    
    textField_1 = new JTextField();
    textField_1.setBounds(101, 50, 96, 19);
    control.add(textField_1);
    textField_1.setColumns(10);
    
    JLabel lblNewLabel_2 = new JLabel("New label");
    lblNewLabel_2.setBounds(10, 85, 67, 13);
    control.add(lblNewLabel_2);
    
    textField_2 = new JTextField();
    textField_2.setBounds(101, 79, 96, 19);
    control.add(textField_2);
    textField_2.setColumns(10);
    
    JLabel lblNewLabel_3 = new JLabel("New label");
    lblNewLabel_3.setBounds(10, 169, 67, 13);
    control.add(lblNewLabel_3);
    
    JComboBox comboBox = new JComboBox();
    comboBox.setBounds(101, 165, 85, 21);
    control.add(comboBox);
    
    JLabel lblNewLabel_4 = new JLabel("New label");
    lblNewLabel_4.setBounds(10, 202, 67, 13);
    control.add(lblNewLabel_4);
    
    JComboBox comboBox_1 = new JComboBox();
    comboBox_1.setBounds(101, 198, 85, 21);
    control.add(comboBox_1);
    
    textField_3 = new JTextField();
    textField_3.setBounds(188, 199, 45, 19);
    control.add(textField_3);
    textField_3.setColumns(10);
    
    JLabel lblNewLabel_5 = new JLabel("New label");
    lblNewLabel_5.setBounds(10, 239, 67, 13);
    control.add(lblNewLabel_5);
    
    JComboBox comboBox_2 = new JComboBox();
    comboBox_2.setBounds(101, 235, 85, 21);
    control.add(comboBox_2);
    
    textField_4 = new JTextField();
    textField_4.setBounds(188, 236, 45, 19);
    control.add(textField_4);
    textField_4.setColumns(10);
    
    JButton btnNewButton = new JButton("New button");
    btnNewButton.setBounds(78, 285, 96, 21);
    control.add(btnNewButton);
    frame.getContentPane().setLayout(groupLayout);
  }
}