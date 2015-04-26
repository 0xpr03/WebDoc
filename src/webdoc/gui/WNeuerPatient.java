package webdoc.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.LayoutStyle.ComponentPlacement;

import net.miginfocom.swing.MigLayout;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import webdoc.gui.utils.ACElement;
import webdoc.gui.utils.GenderEnumObj;
import webdoc.gui.utils.GenderEnumObj.GenderType;
import webdoc.lib.Database;
import webdoc.lib.Database.DBError;
import webdoc.lib.GUI;

import javax.swing.JList;

import webdoc.gui.utils.JSearchTextField;

public class WNeuerPatient extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4647611743598708383L;
	private JTextField textSuche;
	private Logger logger = LogManager.getLogger();
	private JTextField strName;
	private JSearchTextField textRasse;
	private JTextField strFarbe;
	private JTextField strGewicht;
	private JTextField textIdentifizierung;
	private GenderEnumObj[] geschlecht_lokalisiert = {new GenderEnumObj("Bitte Auswählen", GenderType.UNKNOWN),new GenderEnumObj("Weiblich", GenderType.FEMALE),new GenderEnumObj("Männlich", GenderType.MALE) };
	public  boolean editable = true;
	private JComboBox<GenderEnumObj> enumGeschlecht;
	private JPanel allgemeineDaten;
	private JTextField strRufname;
	private JSpinner spinBirthdate;
	private String birthdate;
	private JButton btnNeueAnamnese;
	private JPanel panelVerlauf;
	private JSearchTextField textPartnerSuche;
	private WHomescreen whs;
	private JSpinner.DateEditor dateEditor;
	private long id;
	private JTextPane txtBemerkung;
	private JList listVerlauf;
	private ACElement partner;
	/**
	 * Launch the application.
	 */
	public static void main(final boolean editable, final WHomescreen whs, final long id) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WNeuerPatient window = new WNeuerPatient(editable, whs, id);
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
	public WNeuerPatient(boolean editable, WHomescreen whs, long id) {
		this.editable = editable;
		this.whs = whs;
		this.id = id;
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
		setBounds(100, 100, 911, 491);
		
		JPanel suche = new JPanel();
		
		textSuche = new JTextField();
		textSuche.setText("Suche");
		textSuche.setColumns(10);
		
		JPanel daten = new JPanel();
		
		JPanel panel = new JPanel();
		
		panelVerlauf = new JPanel();
		
		
		JPanel panelBemerkungen = new JPanel();
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(suche, GroupLayout.PREFERRED_SIZE, 295, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(daten, GroupLayout.PREFERRED_SIZE, 283, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(panelBemerkungen, GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
									.addGap(11)
									.addComponent(panelVerlauf, GroupLayout.PREFERRED_SIZE, 276, GroupLayout.PREFERRED_SIZE)))
							.addGap(11))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(27)
					.addComponent(suche, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(daten, GroupLayout.PREFERRED_SIZE, 341, GroupLayout.PREFERRED_SIZE)
						.addComponent(panelVerlauf, GroupLayout.PREFERRED_SIZE, 341, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panelBemerkungen, GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
							.addGap(1)))
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
		
		txtBemerkung = new JTextPane();
		sPaneBemerkungen.setViewportView(txtBemerkung);
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
		
		listVerlauf = new JList();
		sPaneVerlauf.setViewportView(listVerlauf);
		panelVerlauf.setLayout(gl_PanelVerlauf);
		suche.setLayout(new BoxLayout(suche, BoxLayout.X_AXIS));
		suche.add(textSuche);
		panel.setLayout(new MigLayout("", "[85.00][42.00][][]", "[]"));
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(editable){
					addPatient();
				}else{
					return;
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
					.addGap(26))
		);
		gl_daten.setVerticalGroup(
			gl_daten.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_daten.createSequentialGroup()
					.addComponent(allgemeineDaten, GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
					.addContainerGap())
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
		
		textRasse = new JSearchTextField();
		textRasse.setColumns(10);
		
		strFarbe = new JTextField();
		strFarbe.setColumns(10);
		
		strGewicht = new JTextField();
		strGewicht.setColumns(10);
		
		textIdentifizierung = new JTextField();
		textIdentifizierung.setColumns(10);
		
		enumGeschlecht = new JComboBox<GenderEnumObj>();
		enumGeschlecht.setModel(new DefaultComboBoxModel<GenderEnumObj>(geschlecht_lokalisiert));
		
		SpinnerDateModel model = new SpinnerDateModel();
		model.setCalendarField(Calendar.MINUTE);
		spinBirthdate = new JSpinner();
		spinBirthdate.setModel(model);
		dateEditor = new JSpinner.DateEditor(spinBirthdate, "dd-MM-yyyy");
		spinBirthdate.setEditor(dateEditor);
		spinBirthdate.setEnabled(editable);
		
		strRufname = new JTextField();
		strRufname.setColumns(10);
	
		
		JLabel lblRufname = new JLabel("Rufname:");
		
		JLabel lblZugehrigerPatner = new JLabel("Zugehöriger Partner:");
		
		textPartnerSuche = new JSearchTextField();
		textPartnerSuche.setColumns(10);
		
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
									.addComponent(textIdentifizierung, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))
								.addGroup(gl_allgemeineDaten.createSequentialGroup()
									.addGroup(gl_allgemeineDaten.createParallelGroup(Alignment.LEADING)
										.addComponent(lblHaarkleidfarbe)
										.addComponent(lblGewicht)
										.addComponent(lblGeburtsdatum))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_allgemeineDaten.createParallelGroup(Alignment.LEADING)
										.addComponent(spinBirthdate, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
										.addComponent(strFarbe, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
										.addComponent(strGewicht, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))))
							.addContainerGap())
						.addGroup(gl_allgemeineDaten.createSequentialGroup()
							.addGroup(gl_allgemeineDaten.createParallelGroup(Alignment.LEADING)
								.addComponent(lblRasse)
								.addComponent(lblGeschlecht))
							.addGap(30)
							.addGroup(gl_allgemeineDaten.createParallelGroup(Alignment.LEADING)
								.addComponent(textRasse, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
								.addComponent(enumGeschlecht, 0, 151, Short.MAX_VALUE))
							.addGap(10))))
				.addGroup(gl_allgemeineDaten.createSequentialGroup()
					.addGap(70)
					.addComponent(lblAllgemeineDaten)
					.addContainerGap(104, Short.MAX_VALUE))
				.addGroup(gl_allgemeineDaten.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_allgemeineDaten.createParallelGroup(Alignment.LEADING)
						.addComponent(lblRufname)
						.addComponent(lblName))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_allgemeineDaten.createParallelGroup(Alignment.TRAILING)
						.addComponent(strRufname, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
						.addComponent(strName, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
					.addContainerGap())
				.addGroup(gl_allgemeineDaten.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblZugehrigerPatner)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textPartnerSuche, GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
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
					.addGap(25)
					.addGroup(gl_allgemeineDaten.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRasse)
						.addComponent(textRasse, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_allgemeineDaten.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGeschlecht)
						.addComponent(enumGeschlecht, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_allgemeineDaten.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGeburtsdatum)
						.addComponent(spinBirthdate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
						.addComponent(textIdentifizierung, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblIdentifizierung))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_allgemeineDaten.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblZugehrigerPatner)
						.addComponent(textPartnerSuche, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(36, Short.MAX_VALUE))
		);
		allgemeineDaten.setLayout(gl_allgemeineDaten);
		daten.setLayout(gl_daten);
		getContentPane().setLayout(groupLayout);
		
		//panelBemerkungen.setVisible(!editable);
		panelVerlauf.setVisible(!editable);
		
		setEditable();
		
		this.pack();
		loadData();
	}
	
	/**
	 * Loads the animal data if id not -1
	 * (while id 0 is also never given out)
	 * @author "Aron Heinecke"
	 */
	private void loadData(){
		if(id != -1){
			try {
				ResultSet result = Database.getAnimal(id);
				result.next();
				strName.setText(result.getString(1));
				strRufname.setText(result.getString(2));
				spinBirthdate.setValue(result.getDate(3));
				enumGeschlecht.setSelectedItem(result.getBoolean(4) == true ? geschlecht_lokalisiert[0] : geschlecht_lokalisiert[1]);
				logger.debug("col: {}", result.getString(8));
				textRasse.setText(result.getString(8));
			} catch (SQLException e) {
				GUI.showDBErrorDialog(this, Database.DBExceptionConverter(e,true));
			}
		}
	}
	
	protected void neueAnamnese(JTextField strName2) {
		whs.test();
	}
	
	/**
	 * ReSet editable for all input elements
	 * @author "Aron Heinecke"
	 */
	private void setEditable(){
		strName.setEditable(editable);
		strFarbe.setEditable(editable);
		strGewicht.setEditable(editable);
		textRasse.setEditable(editable);
		strRufname.setEditable(editable);
		spinBirthdate.setEnabled(editable);
		txtBemerkung.setEditable(editable);
		listVerlauf.setEnabled(editable);
		enumGeschlecht.setEditable(editable);
		enumGeschlecht.setEnabled(editable);
		textIdentifizierung.setEditable(editable);
		textPartnerSuche.setEditable(editable);
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
					Database.insertPatient(strName.getText(), strRufname.getText(), def, gender.getType() == GenderType.MALE, textRasse.getText(), def, null);
					this.dispose();
				} catch (SQLException e) {
					DBError error = Database.DBExceptionConverter(e);
					GUI.showErrorDialog(this, "Error during insertion: "+error, "Insertion error");
				}
			}else{
				JOptionPane.showMessageDialog(textRasse, "Kein Geschlecht ausgewählt!");
				logger.info("No Gender selected!");
			}
		}
	}
}
