package webdoc.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.LayoutStyle.ComponentPlacement;

import webdoc.gui.GUIMethoden;
import javax.swing.JLabel;
import javax.swing.JComboBox;

public class WNeuerPatient {

	private JFrame frmNeuerPatient;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WNeuerPatient window = new WNeuerPatient();
					window.frmNeuerPatient.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WNeuerPatient() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNeuerPatient = new JFrame();
		frmNeuerPatient.setTitle("Neuer Patient");
		frmNeuerPatient.setBounds(100, 100, 882, 592);
		frmNeuerPatient.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frmNeuerPatient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmNeuerPatient.setJMenuBar(menuBar);
		menuBar.add(GUIMethoden.menus(menuBar));
		
		JPanel navigation = new JPanel();
		
		JPanel suche = new JPanel();
		
		textField = new JTextField();
		textField.setText("Suche");
		textField.setColumns(10);
		GroupLayout gl_suche = new GroupLayout(suche);
		gl_suche.setHorizontalGroup(
			gl_suche.createParallelGroup(Alignment.LEADING)
				.addGap(0, 846, Short.MAX_VALUE)
				.addGroup(gl_suche.createSequentialGroup()
					.addGap(119)
					.addComponent(textField, GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
					.addGap(129))
		);
		gl_suche.setVerticalGroup(
			gl_suche.createParallelGroup(Alignment.LEADING)
				.addGap(0, 35, Short.MAX_VALUE)
				.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		);
		suche.setLayout(gl_suche);
		
		JPanel daten = new JPanel();
		GroupLayout groupLayout = new GroupLayout(frmNeuerPatient.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(suche, GroupLayout.DEFAULT_SIZE, 846, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(navigation, GroupLayout.PREFERRED_SIZE, 156, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(daten, GroupLayout.PREFERRED_SIZE, 684, GroupLayout.PREFERRED_SIZE)
							.addGap(1)))
					.addGap(3))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(suche, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(navigation, GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)
							.addGap(4))
						.addComponent(daten, GroupLayout.PREFERRED_SIZE, 496, GroupLayout.PREFERRED_SIZE))
					.addGap(0))
		);
		
		JPanel allgemeineDaten = new JPanel();
		GroupLayout gl_daten = new GroupLayout(daten);
		gl_daten.setHorizontalGroup(
			gl_daten.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_daten.createSequentialGroup()
					.addComponent(allgemeineDaten, GroupLayout.PREFERRED_SIZE, 249, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(435, Short.MAX_VALUE))
		);
		gl_daten.setVerticalGroup(
			gl_daten.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_daten.createSequentialGroup()
					.addComponent(allgemeineDaten, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(286, Short.MAX_VALUE))
		);
		
		JLabel lblName = new JLabel("Name des Tieres:");
		
		JLabel lblRasse = new JLabel("Rasse:");
		
		JLabel lblGeschlecht = new JLabel("Geschlecht:");
		
		JLabel lblGeburtsdatum = new JLabel("Geburtsdatum:");
		
		JLabel lblHaarkleidfarbe = new JLabel("Haarkleid/Farbe:");
		
		JLabel lblGewicht = new JLabel("Gewicht:");
		
		JLabel lblIdentifizierung = new JLabel("Identifizierung:");
		
		JLabel lblAllgemeineDaten = new JLabel("Allgemeine Daten");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		GroupLayout gl_allgemeineDaten = new GroupLayout(allgemeineDaten);
		gl_allgemeineDaten.setHorizontalGroup(
			gl_allgemeineDaten.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_allgemeineDaten.createSequentialGroup()
					.addGroup(gl_allgemeineDaten.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblGeburtsdatum)
						.addGroup(gl_allgemeineDaten.createSequentialGroup()
							.addComponent(lblIdentifizierung)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textField_5))
						.addGroup(gl_allgemeineDaten.createSequentialGroup()
							.addGroup(gl_allgemeineDaten.createParallelGroup(Alignment.LEADING)
								.addComponent(lblHaarkleidfarbe)
								.addComponent(lblGewicht))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_allgemeineDaten.createParallelGroup(Alignment.LEADING)
								.addComponent(textField_4, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
								.addComponent(textField_3)))
						.addGroup(gl_allgemeineDaten.createSequentialGroup()
							.addGroup(gl_allgemeineDaten.createParallelGroup(Alignment.LEADING)
								.addComponent(lblName)
								.addComponent(lblRasse)
								.addComponent(lblGeschlecht))
							.addGap(3)
							.addGroup(gl_allgemeineDaten.createParallelGroup(Alignment.LEADING)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_allgemeineDaten.createParallelGroup(Alignment.LEADING, false)
									.addComponent(textField_2)
									.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)))))
					.addContainerGap(40, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_allgemeineDaten.createSequentialGroup()
					.addContainerGap(101, Short.MAX_VALUE)
					.addComponent(lblAllgemeineDaten)
					.addGap(65))
		);
		gl_allgemeineDaten.setVerticalGroup(
			gl_allgemeineDaten.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_allgemeineDaten.createSequentialGroup()
					.addComponent(lblAllgemeineDaten)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_allgemeineDaten.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_allgemeineDaten.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRasse)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_allgemeineDaten.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_allgemeineDaten.createSequentialGroup()
							.addGroup(gl_allgemeineDaten.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblGeschlecht)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblGeburtsdatum)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblHaarkleidfarbe))
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_allgemeineDaten.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGewicht)
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_allgemeineDaten.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblIdentifizierung)
						.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(50, Short.MAX_VALUE))
		);
		allgemeineDaten.setLayout(gl_allgemeineDaten);
		daten.setLayout(gl_daten);
		JTree navigationsbaum = new JTree();
		navigationsbaum.setModel(GUIMethoden.Navi());
		GroupLayout gl_navigation = new GroupLayout(navigation);
		gl_navigation.setHorizontalGroup(
			gl_navigation.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_navigation.createSequentialGroup()
					.addGap(4)
					.addComponent(navigationsbaum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_navigation.setVerticalGroup(
			gl_navigation.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_navigation.createSequentialGroup()
					.addGap(5)
					.addComponent(navigationsbaum, GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE))
		);
		navigation.setLayout(gl_navigation);
		frmNeuerPatient.getContentPane().setLayout(groupLayout);
	}
}
