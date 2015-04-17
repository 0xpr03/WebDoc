package webdoc.gui;

import java.awt.EventQueue;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import net.miginfocom.swing.MigLayout;
import javax.swing.BoxLayout;

public class WNeuerPatient extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4647611743598708383L;
	private JTextField textField;
	private JTextField nameDesTieres;
	private JTextField rasse;
	private JTextField farbe;
	private JTextField gewicht;
	private JTextField identifizierung;
	public  boolean editable = true;
	private JComboBox geschlecht;
	private JPanel allgemeineDaten;

	/**
	 * Launch the application.
	 */
	public static void main(final boolean editable) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WNeuerPatient window = new WNeuerPatient(editable);
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WNeuerPatient(boolean editable) {
		this.editable = editable;
		initialize();
		setFrameIcon(null);
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setTitle(editable ? "Neuer Patient" : "Patient");
		setBounds(100, 100, 321, 340);
		
		JPanel suche = new JPanel();
		
		textField = new JTextField();
		textField.setText("Suche");
		textField.setColumns(10);
		
		JPanel daten = new JPanel();
		
		JPanel panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(daten, GroupLayout.PREFERRED_SIZE, 283, GroupLayout.PREFERRED_SIZE)
								.addComponent(suche, GroupLayout.PREFERRED_SIZE, 295, GroupLayout.PREFERRED_SIZE))))
					.addGap(389))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(suche, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(daten, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addGap(258))
		);
		suche.setLayout(new BoxLayout(suche, BoxLayout.X_AXIS));
		suche.add(textField);
		panel.setLayout(new MigLayout("", "[85.00][42.00][]", "[]"));
		
		JButton btnOk = new JButton("Ok");
		panel.add(btnOk, "cell 1 0,alignx center");
		
		JButton btnNewButton = new JButton("Cancel");
		panel.add(btnNewButton, "cell 2 0");
		btnNewButton.setVisible(editable);
		
		allgemeineDaten = new JPanel();
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
					.addComponent(allgemeineDaten, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(275, Short.MAX_VALUE))
		);
		
		JLabel lblName = new JLabel("Name des Tieres:");
		
		JLabel lblRasse = new JLabel("Rasse:");
		
		JLabel lblGeschlecht = new JLabel("Geschlecht:");
		
		JLabel lblGeburtsdatum = new JLabel("Geburtsdatum:");
		
		JLabel lblHaarkleidfarbe = new JLabel("Haarkleid/Farbe:");
		
		JLabel lblGewicht = new JLabel("Gewicht:");
		
		JLabel lblIdentifizierung = new JLabel("Identifizierung:");
		
		JLabel lblAllgemeineDaten = new JLabel("Allgemeine Daten");
		
		nameDesTieres = new JTextField();
		nameDesTieres.setColumns(10);
		nameDesTieres.setEditable(editable);
		
		rasse = new JTextField();
		rasse.setEditable(editable);
		rasse.setColumns(10);
		
		farbe = new JTextField();
		farbe.setColumns(10);
		farbe.setEditable(editable);
		
		gewicht = new JTextField();
		gewicht.setEditable(editable);
		gewicht.setColumns(10);
		
		identifizierung = new JTextField();
		identifizierung.setColumns(10);
		identifizierung.setEditable(editable);
		
		geschlecht = new JComboBox();
		geschlecht.setEditable(editable);
		GroupLayout gl_allgemeineDaten = new GroupLayout(allgemeineDaten);
		gl_allgemeineDaten.setHorizontalGroup(
			gl_allgemeineDaten.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_allgemeineDaten.createSequentialGroup()
					.addGroup(gl_allgemeineDaten.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_allgemeineDaten.createSequentialGroup()
							.addComponent(lblIdentifizierung)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(identifizierung))
						.addGroup(gl_allgemeineDaten.createSequentialGroup()
							.addGroup(gl_allgemeineDaten.createParallelGroup(Alignment.LEADING)
								.addComponent(lblName)
								.addComponent(lblRasse)
								.addComponent(lblGeschlecht))
							.addGap(3)
							.addGroup(gl_allgemeineDaten.createParallelGroup(Alignment.LEADING)
								.addComponent(geschlecht, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_allgemeineDaten.createParallelGroup(Alignment.LEADING, false)
									.addComponent(rasse)
									.addComponent(nameDesTieres, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE))))
						.addGroup(gl_allgemeineDaten.createSequentialGroup()
							.addGroup(gl_allgemeineDaten.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_allgemeineDaten.createParallelGroup(Alignment.LEADING)
									.addComponent(lblHaarkleidfarbe)
									.addComponent(lblGewicht))
								.addComponent(lblGeburtsdatum))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_allgemeineDaten.createParallelGroup(Alignment.LEADING)
								.addComponent(farbe)
								.addComponent(gewicht, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE))))
					.addContainerGap(40, Short.MAX_VALUE))
				.addGroup(gl_allgemeineDaten.createSequentialGroup()
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
						.addComponent(nameDesTieres, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_allgemeineDaten.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRasse)
						.addComponent(rasse, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_allgemeineDaten.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGeschlecht)
						.addComponent(geschlecht, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_allgemeineDaten.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_allgemeineDaten.createSequentialGroup()
							.addComponent(lblGeburtsdatum)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblHaarkleidfarbe))
						.addComponent(farbe, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_allgemeineDaten.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGewicht)
						.addComponent(gewicht, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_allgemeineDaten.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblIdentifizierung)
						.addComponent(identifizierung, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(24, Short.MAX_VALUE))
		);
		allgemeineDaten.setLayout(gl_allgemeineDaten);
		daten.setLayout(gl_daten);
		getContentPane().setLayout(groupLayout);
	}
}
