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
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import net.miginfocom.swing.MigLayout;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import webdoc.gui.utils.GenderEnumObj;
import webdoc.gui.utils.GenderEnumObj.GenderType;
import webdoc.lib.Database;
import webdoc.lib.Database.DBError;
import webdoc.lib.GUI;

public class WNeuerPatient extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4647611743598708383L;
	private JTextField textField;
	private Logger logger = LogManager.getLogger();
	private JTextField strName;
	private JTextField strRasse;
	private JTextField strFarbe;
	private JTextField strGewicht;
	private JTextField identifizierung;
	private GenderEnumObj[] geschlecht_lokalisiert = {new GenderEnumObj("Bitte AuswÃ¤hlen", GenderType.UNKNOWN),new GenderEnumObj("Weiblich", GenderType.FEMALE),new GenderEnumObj("MÃ¤nnlich", GenderType.MALE) };
	public  boolean editable = true;
	private JComboBox<GenderEnumObj> enumGeschlecht;
	private JPanel allgemeineDaten;
	private JTextField strRufname;
	private JSpinner spinGebDay;
	private JSpinner spinGebMonth;
	private JSpinner spinGebYear;
	private String birthdate;
	private JButton btnNeueAnamnese;
	private JPanel panelVerlauf;
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
		setBounds(100, 100, 911, 480);
		
		JPanel suche = new JPanel();
		
		textField = new JTextField();
		textField.setText("Suche");
		textField.setColumns(10);
		
		JPanel daten = new JPanel();
		
		JPanel panel = new JPanel();
		
		panelVerlauf = new JPanel();
		
		JPanel panelBemerkungen = new JPanel();
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(suche, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 295, GroupLayout.PREFERRED_SIZE)
								.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
									.addComponent(daten, GroupLayout.PREFERRED_SIZE, 283, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(panelBemerkungen, GroupLayout.PREFERRED_SIZE, 298, GroupLayout.PREFERRED_SIZE)
									.addGap(11)
									.addComponent(panelVerlauf, GroupLayout.PREFERRED_SIZE, 276, GroupLayout.PREFERRED_SIZE)))
							.addGap(11))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(23)
					.addComponent(suche, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(daten, GroupLayout.PREFERRED_SIZE, 341, GroupLayout.PREFERRED_SIZE)
						.addComponent(panelVerlauf, GroupLayout.PREFERRED_SIZE, 341, GroupLayout.PREFERRED_SIZE)
						.addComponent(panelBemerkungen, GroupLayout.PREFERRED_SIZE, 340, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addGap(143))
		);
		
		JLabel lblBemerkungen = new JLabel("Bemerkungen:");
		
		JScrollPane sPaneBemerkungen = new JScrollPane();
		GroupLayout gl_panelBemerkungen = new GroupLayout(panelBemerkungen);
		gl_panelBemerkungen.setHorizontalGroup(
			gl_panelBemerkungen.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelBemerkungen.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelBemerkungen.createParallelGroup(Alignment.LEADING)
						.addComponent(lblBemerkungen)
						.addComponent(sPaneBemerkungen, GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE))
					.addGap(8))
		);
		gl_panelBemerkungen.setVerticalGroup(
			gl_panelBemerkungen.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelBemerkungen.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblBemerkungen)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(sPaneBemerkungen, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
					.addGap(10))
		);
		
		JTextPane textPane_1 = new JTextPane();
		sPaneBemerkungen.setViewportView(textPane_1);
		panelBemerkungen.setLayout(gl_panelBemerkungen);
		
		JLabel Verlauf = new JLabel("Verlauf:");
		
		JScrollPane sPaneVerlauf = new JScrollPane();
		GroupLayout gl_PanelVerlauf = new GroupLayout(panelVerlauf);
		gl_PanelVerlauf.setHorizontalGroup(
			gl_PanelVerlauf.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_PanelVerlauf.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_PanelVerlauf.createParallelGroup(Alignment.LEADING)
						.addComponent(sPaneVerlauf, GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
						.addComponent(Verlauf))
					.addContainerGap())
		);
		gl_PanelVerlauf.setVerticalGroup(
			gl_PanelVerlauf.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_PanelVerlauf.createSequentialGroup()
					.addContainerGap()
					.addComponent(Verlauf)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(sPaneVerlauf, GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JTextPane textPane = new JTextPane();
		sPaneVerlauf.setViewportView(textPane);
		panelVerlauf.setLayout(gl_PanelVerlauf);
		suche.setLayout(new BoxLayout(suche, BoxLayout.X_AXIS));
		suche.add(textField);
		panel.setLayout(new MigLayout("", "[85.00][42.00][][]", "[]"));
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(editable){
					birthdate = " " + spinGebDay.getValue().toString()+ "." + spinGebMonth.getValue().toString() + "." + spinGebYear.getValue().toString() + "" ;
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
		
		btnNeueAnamnese = new JButton("Neue Anamnese");
		btnNeueAnamnese.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				neueAnamnese(strName);
			}

		});
		panel.add(btnNeueAnamnese, "cell 3 0");
		btnNeueAnamnese.setVisible(!editable);
		
		
		
		buttonCancel.setVisible(editable);
		
		allgemeineDaten = new JPanel();
		GroupLayout gl_daten = new GroupLayout(daten);
		gl_daten.setHorizontalGroup(
			gl_daten.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_daten.createSequentialGroup()
					.addComponent(allgemeineDaten, GroupLayout.PREFERRED_SIZE, 257, Short.MAX_VALUE)
					.addGap(34))
		);
		gl_daten.setVerticalGroup(
			gl_daten.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_daten.createSequentialGroup()
					.addComponent(allgemeineDaten, GroupLayout.PREFERRED_SIZE, 279, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(164, Short.MAX_VALUE))
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
		
		strFarbe = new JTextField();
		strFarbe.setColumns(10);
		strFarbe.setEditable(editable);
		
		strGewicht = new JTextField();
		strGewicht.setEditable(editable);
		strGewicht.setColumns(10);
		
		identifizierung = new JTextField();
		identifizierung.setColumns(10);
		identifizierung.setEditable(editable);
		
		enumGeschlecht = new JComboBox<GenderEnumObj>();
		enumGeschlecht.setModel(new DefaultComboBoxModel<GenderEnumObj>(geschlecht_lokalisiert));
		enumGeschlecht.setEditable(editable);
		
		spinGebMonth = new JSpinner();
		
		spinGebDay = new JSpinner();
		
		spinGebYear = new JSpinner();
		
		strRufname = new JTextField();
		strRufname.setColumns(10);
		strRufname.setEditable(editable);
	
		
		JLabel lblRufname = new JLabel("Rufname:");
		GroupLayout gl_allgemeineDaten = new GroupLayout(allgemeineDaten);
		gl_allgemeineDaten.setHorizontalGroup(
			gl_allgemeineDaten.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_allgemeineDaten.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_allgemeineDaten.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_allgemeineDaten.createSequentialGroup()
							.addGroup(gl_allgemeineDaten.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_allgemeineDaten.createSequentialGroup()
									.addComponent(lblIdentifizierung)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(identifizierung, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_allgemeineDaten.createSequentialGroup()
									.addComponent(lblGeburtsdatum)
									.addGap(18)
									.addComponent(spinGebDay, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(spinGebMonth, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(spinGebYear, GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE))
								.addGroup(gl_allgemeineDaten.createSequentialGroup()
									.addGroup(gl_allgemeineDaten.createParallelGroup(Alignment.LEADING)
										.addComponent(lblHaarkleidfarbe)
										.addComponent(lblGewicht))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_allgemeineDaten.createParallelGroup(Alignment.LEADING)
										.addComponent(strFarbe, 145, 145, 145)
										.addComponent(strGewicht, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))))
							.addContainerGap())
						.addGroup(gl_allgemeineDaten.createSequentialGroup()
							.addGroup(gl_allgemeineDaten.createParallelGroup(Alignment.LEADING)
								.addComponent(lblRasse)
								.addComponent(lblGeschlecht))
							.addGap(30)
							.addGroup(gl_allgemeineDaten.createParallelGroup(Alignment.LEADING)
								.addComponent(strRasse, GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
								.addComponent(enumGeschlecht, 0, 157, Short.MAX_VALUE))
							.addGap(10))))
				.addGroup(gl_allgemeineDaten.createSequentialGroup()
					.addGap(70)
					.addComponent(lblAllgemeineDaten)
					.addContainerGap(100, Short.MAX_VALUE))
				.addGroup(gl_allgemeineDaten.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_allgemeineDaten.createParallelGroup(Alignment.LEADING)
						.addComponent(lblRufname)
						.addComponent(lblName))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_allgemeineDaten.createParallelGroup(Alignment.TRAILING)
						.addComponent(strRufname, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
						.addComponent(strName, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_allgemeineDaten.setVerticalGroup(
			gl_allgemeineDaten.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_allgemeineDaten.createSequentialGroup()
					.addComponent(lblAllgemeineDaten)
					.addGap(18)
					.addGroup(gl_allgemeineDaten.createParallelGroup(Alignment.BASELINE)
						.addComponent(strName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblName))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_allgemeineDaten.createParallelGroup(Alignment.BASELINE)
						.addComponent(strRufname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRufname))
					.addPreferredGap(ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
					.addGroup(gl_allgemeineDaten.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRasse)
						.addComponent(strRasse, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_allgemeineDaten.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGeschlecht)
						.addComponent(enumGeschlecht, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_allgemeineDaten.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGeburtsdatum)
						.addComponent(spinGebMonth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(spinGebYear, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(spinGebDay, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_allgemeineDaten.createParallelGroup(Alignment.BASELINE)
						.addComponent(strFarbe, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblHaarkleidfarbe))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_allgemeineDaten.createParallelGroup(Alignment.BASELINE)
						.addComponent(strGewicht, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
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
		
		//panelBemerkungen.setVisible(!editable);
		panelVerlauf.setVisible(!editable);
		
		
		this.pack();
	}
	
	protected void neueAnamnese(JTextField strName2) {
		//TODO: Öffne neue Anamnese Fenster
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
