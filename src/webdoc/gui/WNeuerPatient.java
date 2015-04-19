package webdoc.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import net.miginfocom.swing.MigLayout;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import webdoc.gui.GenderEnumObj.GenderType;
import webdoc.lib.Database;
import webdoc.lib.Database.DBError;
import webdoc.lib.GUI;
import javax.swing.JSpinner;

public class WNeuerPatient extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4647611743598708383L;
	private JTextField textField;
	private Logger logger = LogManager.getLogger();
	private JTextField strName;
	private JTextField strRasse;
	private JTextField farbe;
	private JTextField gewicht;
	private JTextField identifizierung;
	private GenderEnumObj[] geschlecht_lokalisiert = {new GenderEnumObj("Bitte AuswÃ¤hlen", GenderType.UNKNOWN),new GenderEnumObj("Weiblich", GenderType.FEMALE),new GenderEnumObj("MÃ¤nnlich", GenderType.MALE) };
	public  boolean editable = true;
	private JComboBox<GenderEnumObj> enumGeschlecht;
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
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(editable){
					addPatient();
				}else{
					//TODO: add
				}
			}
		});
		panel.add(btnOk, "cell 1 0,alignx center");
		
		JButton buttonCancel = new JButton("Cancel");
		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				exit();
			}
		});
		
		panel.add(buttonCancel, "cell 2 0");
		buttonCancel.setVisible(editable);
		
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
		
		strName = new JTextField();
		strName.setColumns(10);
		strName.setEditable(editable);
		
		strRasse = new JTextField();
		strRasse.setEditable(editable);
		strRasse.setColumns(10);
		
		farbe = new JTextField();
		farbe.setColumns(10);
		farbe.setEditable(editable);
		
		gewicht = new JTextField();
		gewicht.setEditable(editable);
		gewicht.setColumns(10);
		
		identifizierung = new JTextField();
		identifizierung.setColumns(10);
		identifizierung.setEditable(editable);
		
		enumGeschlecht = new JComboBox<GenderEnumObj>();
		enumGeschlecht.setModel(new DefaultComboBoxModel<GenderEnumObj>(geschlecht_lokalisiert));
		enumGeschlecht.setEditable(editable);
		
		JSpinner spinner_1 = new JSpinner();
		
		JSpinner spinner = new JSpinner();
		
		JSpinner spinner_2 = new JSpinner();
		GroupLayout gl_allgemeineDaten = new GroupLayout(allgemeineDaten);
		gl_allgemeineDaten.setHorizontalGroup(
			gl_allgemeineDaten.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_allgemeineDaten.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_allgemeineDaten.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_allgemeineDaten.createSequentialGroup()
							.addComponent(lblIdentifizierung)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(identifizierung, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_allgemeineDaten.createSequentialGroup()
							.addGroup(gl_allgemeineDaten.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_allgemeineDaten.createSequentialGroup()
									.addGroup(gl_allgemeineDaten.createParallelGroup(Alignment.LEADING)
										.addComponent(lblName)
										.addComponent(lblRasse)
										.addComponent(lblGeschlecht))
									.addGap(3))
								.addGroup(gl_allgemeineDaten.createSequentialGroup()
									.addComponent(lblGeburtsdatum)
									.addPreferredGap(ComponentPlacement.UNRELATED)))
							.addGroup(gl_allgemeineDaten.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_allgemeineDaten.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(spinner, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(spinner_1, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(spinner_2, GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE))
								.addComponent(strRasse, GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
								.addComponent(strName, GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
								.addComponent(enumGeschlecht, 0, 147, Short.MAX_VALUE)))
						.addGroup(gl_allgemeineDaten.createSequentialGroup()
							.addGroup(gl_allgemeineDaten.createParallelGroup(Alignment.LEADING)
								.addComponent(lblHaarkleidfarbe)
								.addComponent(lblGewicht))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_allgemeineDaten.createParallelGroup(Alignment.LEADING)
								.addComponent(farbe, 145, 145, 145)
								.addComponent(gewicht, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))))
					.addContainerGap())
				.addGroup(gl_allgemeineDaten.createSequentialGroup()
					.addContainerGap(105, Short.MAX_VALUE)
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
						.addComponent(strName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_allgemeineDaten.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRasse)
						.addComponent(strRasse, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_allgemeineDaten.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGeschlecht)
						.addComponent(enumGeschlecht, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
					.addGroup(gl_allgemeineDaten.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGeburtsdatum)
						.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(spinner_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(spinner_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_allgemeineDaten.createParallelGroup(Alignment.BASELINE)
						.addComponent(farbe, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblHaarkleidfarbe))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_allgemeineDaten.createParallelGroup(Alignment.BASELINE)
						.addComponent(gewicht, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblGewicht))
					.addGap(9)
					.addGroup(gl_allgemeineDaten.createParallelGroup(Alignment.BASELINE)
						.addComponent(identifizierung, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblIdentifizierung))
					.addContainerGap())
		);
		allgemeineDaten.setLayout(gl_allgemeineDaten);
		daten.setLayout(gl_daten);
		getContentPane().setLayout(groupLayout);
	}
	
	private void exit(){
		this.dispose();
	}
	private void addPatient() {
		//TODO: add picture support
		if((GenderEnumObj)enumGeschlecht.getSelectedItem() != null) {
			GenderEnumObj gender = (GenderEnumObj) enumGeschlecht.getSelectedItem();
			if(gender.getType() != GenderType.UNKNOWN){
				String def = "DEFAULT";
				try {
					Database.insertPatient(strName.getText(), def, def, gender.getType() == GenderType.MALE, strRasse.getText(), def, null);
					this.dispose();
				} catch (SQLException e) {
					DBError error = Database.DBExceptionConverter(e);
					GUI.showErrorDialog(this, "Error during insertion: "+error, "Insertion error");
				}
			}else{
				logger.info("No Gender selected!");
			}
		}
	}
}
