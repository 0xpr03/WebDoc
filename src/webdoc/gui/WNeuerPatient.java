package webdoc.gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import webdoc.gui.utils.ACElement;
import webdoc.gui.utils.ACElement.ElementType;
import webdoc.gui.utils.GenderEnumObj;
import webdoc.gui.utils.GenderEnumObj.GenderType;
import webdoc.gui.utils.JSearchTextField;
import webdoc.gui.utils.JSearchTextField.searchFieldAPI;
import webdoc.lib.Database;
import webdoc.lib.Database.DBError;
import webdoc.lib.GUI;

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
	private JSpinner spinGewicht;
	private JTextField textIdentifizierung;
	private GenderEnumObj[] geschlecht_lokalisiert = {new GenderEnumObj("Bitte Auswählen", GenderType.UNKNOWN),new GenderEnumObj("Weiblich", GenderType.FEMALE),new GenderEnumObj("Männlich", GenderType.MALE) };
	public  boolean editable = true;
	private JComboBox<GenderEnumObj> enumGeschlecht;
	private JPanel allgemeineDaten;
	private JTextField strRufname;
	private JSpinner spinBirthdate;
	private JButton btnNeueAnamnese;
	private JPanel panelVerlauf;
	private JSearchTextField textPartnerSuche;
	private WHomescreen whs;
	private JSpinner.DateEditor dateEditor;
	private long id;
	private JTextPane txtBemerkung;
	private JList listVerlauf;
	private ACElement partner;
	private PreparedStatement searchRaceStm;
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
		getContentPane().setMinimumSize(new Dimension(600, 400));
		setPreferredSize(new Dimension(700, 400));
		setMinimumSize(new Dimension(1, 1));
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
		setBounds(100, 100, 963, 444);
		
		JPanel suche = new JPanel();
		
		textSuche = new JTextField();
		textSuche.setText("Suche");
		textSuche.setColumns(10);
		
		JPanel daten = new JPanel();
		
		JPanel panel = new JPanel();
		panel.setBounds(new Rectangle(1, 1, 1, 1));
		
		panelVerlauf = new JPanel();
		
		
		JPanel panelBemerkungen = new JPanel();
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(suche, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(daten, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelBemerkungen, GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
					.addGap(1)
					.addComponent(panelVerlauf, GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
					.addGap(11))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(suche, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(daten, GroupLayout.PREFERRED_SIZE, 286, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 248, Short.MAX_VALUE)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(panelVerlauf, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
								.addComponent(panelBemerkungen, GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE))
							.addContainerGap())))
		);
		
		JLabel lblBemerkungen = new JLabel("Bemerkungen:");
		
		JScrollPane sPaneBemerkungen = new JScrollPane();
		GroupLayout gl_panelBemerkungen = new GroupLayout(panelBemerkungen);
		gl_panelBemerkungen.setHorizontalGroup(
			gl_panelBemerkungen.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelBemerkungen.createSequentialGroup()
					.addGap(1)
					.addGroup(gl_panelBemerkungen.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelBemerkungen.createSequentialGroup()
							.addComponent(lblBemerkungen)
							.addContainerGap())
						.addGroup(gl_panelBemerkungen.createSequentialGroup()
							.addComponent(sPaneBemerkungen, GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
							.addGap(1))))
		);
		gl_panelBemerkungen.setVerticalGroup(
			gl_panelBemerkungen.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelBemerkungen.createSequentialGroup()
					.addComponent(lblBemerkungen)
					.addGap(1)
					.addComponent(sPaneBemerkungen, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
					.addGap(2))
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
					.addGap(1)
					.addGroup(gl_PanelVerlauf.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_PanelVerlauf.createSequentialGroup()
							.addComponent(Verlauf)
							.addContainerGap())
						.addGroup(gl_PanelVerlauf.createSequentialGroup()
							.addComponent(sPaneVerlauf, GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
							.addGap(1))))
		);
		gl_PanelVerlauf.setVerticalGroup(
			gl_PanelVerlauf.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_PanelVerlauf.createSequentialGroup()
					.addComponent(Verlauf)
					.addGap(1)
					.addComponent(sPaneVerlauf, GroupLayout.DEFAULT_SIZE, 496, Short.MAX_VALUE)
					.addGap(2))
		);
		
		listVerlauf = new JList();
		sPaneVerlauf.setViewportView(listVerlauf);
		panelVerlauf.setLayout(gl_PanelVerlauf);
		suche.setLayout(new BoxLayout(suche, BoxLayout.X_AXIS));
		suche.add(textSuche);
		panel.setLayout(new MigLayout("", "[29.00][42.00][][]", "[26.00]"));
		
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
		allgemeineDaten.setBorder(new TitledBorder(null, "Allgemeine Daten", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		allgemeineDaten.setAutoscrolls(true);
		GroupLayout gl_daten = new GroupLayout(daten);
		gl_daten.setHorizontalGroup(
			gl_daten.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_daten.createSequentialGroup()
					.addComponent(allgemeineDaten, GroupLayout.PREFERRED_SIZE, 282, Short.MAX_VALUE)
					.addGap(1))
		);
		gl_daten.setVerticalGroup(
			gl_daten.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_daten.createSequentialGroup()
					.addComponent(allgemeineDaten, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(56, Short.MAX_VALUE))
		);
		
		JLabel lblName = new JLabel("Name des Tieres:");
		
		JLabel lblRasse = new JLabel("Rasse:");
		
		JLabel lblGeschlecht = new JLabel("Geschlecht:");
		
		JLabel lblGeburtsdatum = new JLabel("Geburtsdatum:");
		
		JLabel lblHaarkleidfarbe = new JLabel("Haarkleid/Farbe:");
		
		JLabel lblGewicht = new JLabel("Gewicht:");
		
		JLabel lblIdentifizierung = new JLabel("Identifizierung:");
		
		strName = new JTextField();
		strName.setColumns(10);
		
		/**
		 * Default DataProvider for these kinds
		 * @author "Aron Heinecke"
		 */
		class RaceProvider implements searchFieldAPI{
			@Override
			public List<ACElement> getData(String text){
				List<ACElement> list = new ArrayList<ACElement>();
				try {
					searchRaceStm.setString(1, "%"+text+"%");
					ResultSet result = searchRaceStm.executeQuery();
					
					while(result.next()){
						list.add(new ACElement(result.getString(2),"", result.getLong(1), ElementType.RACE));
					}
					result.close();
					
				} catch (SQLException e) {
					GUI.showDBErrorDialog(null, Database.DBExceptionConverter(e,true));
				}
				return list;
			}

			@Override
			public void changedSelectionEvent(ACElement element) {
				logger.debug("Element chosen: {}",element);
				//doing nothing, we only want to provide a search for the existing types
			}

			@Override
			public String listRenderer(ACElement element) {
				return element.getName();
			}
		}
		
		textRasse = new JSearchTextField();
		textRasse.setColumns(10);
		textRasse.setAPI(new RaceProvider());
		
		strFarbe = new JTextField();
		strFarbe.setColumns(10);
		
		spinGewicht = new JSpinner();
		spinGewicht.setModel(new SpinnerNumberModel(new Double(0), null, null, new Double(1)));
		
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
		allgemeineDaten.setLayout(new MigLayout("", "[83px][18px][145px]", "[24px,center][24px][24px][24px][24px][24px][24px][24px][24px]"));
		allgemeineDaten.add(lblGeburtsdatum, "cell 0 4,alignx left,aligny center");
		allgemeineDaten.add(spinBirthdate, "cell 1 4 2 1,growx,aligny top");
		allgemeineDaten.add(lblRasse, "cell 0 2,alignx left,aligny center");
		allgemeineDaten.add(lblGeschlecht, "cell 0 3,alignx left,aligny center");
		allgemeineDaten.add(textRasse, "cell 1 2 2 1,growx,aligny top");
		allgemeineDaten.add(enumGeschlecht, "cell 1 3 2 1,grow");
		allgemeineDaten.add(lblRufname, "cell 0 1,alignx left,aligny center");
		allgemeineDaten.add(lblName, "cell 0 0,alignx left,aligny center");
		allgemeineDaten.add(strRufname, "cell 1 1 2 1,growx,aligny top");
		allgemeineDaten.add(strName, "cell 1 0 2 1,growx,aligny top");
		allgemeineDaten.add(lblHaarkleidfarbe, "cell 0 5,alignx left,aligny top");
		allgemeineDaten.add(strFarbe, "cell 1 5 2 1,growx,aligny top");
		allgemeineDaten.add(lblZugehrigerPatner, "cell 0 8 2 1,alignx left,aligny center");
		allgemeineDaten.add(textPartnerSuche, "cell 2 8,growx,aligny top");
		allgemeineDaten.add(lblIdentifizierung, "cell 0 7,alignx left,aligny center");
		allgemeineDaten.add(lblGewicht, "cell 0 6,alignx left,aligny center");
		allgemeineDaten.add(spinGewicht, "cell 1 6 2 1,growx,aligny top");
		allgemeineDaten.add(textIdentifizierung, "cell 1 7 2 1,growx,aligny top");
		daten.setLayout(gl_daten);
		getContentPane().setLayout(groupLayout);
		
		//panelBemerkungen.setVisible(!editable);
		panelVerlauf.setVisible(!editable);
		
		try {
			searchRaceStm = Database.prepareRaceSearchStm();
		} catch (SQLException e) {
			GUI.showDBErrorDialog(null, Database.DBExceptionConverter(e,true));
		}
		
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
				textIdentifizierung.setText(result.getString(3));
				strFarbe.setText(result.getString(4));
				spinGewicht.setValue(result.getDouble(5));
				spinBirthdate.setValue(result.getDate(6));
				enumGeschlecht.setSelectedItem(result.getBoolean(7) == true ? geschlecht_lokalisiert[0] : geschlecht_lokalisiert[1]);
				logger.debug("col: {}", result.getString(8));
				textRasse.setTextWithoutNotification(result.getString(8));
			} catch (SQLException e) {
				GUI.showDBErrorDialog(this, Database.DBExceptionConverter(e,true));
			}
		}
	}
	
	protected void neueAnamnese(JTextField strName2) {
		whs.callWNewAnamnesis();
	}
	
	/**
	 * ReSet editable for all input elements
	 * @author "Aron Heinecke"
	 */
	private void setEditable(){
		textSuche.setEditable(!editable);
		strName.setEditable(editable);
		strFarbe.setEditable(editable);
		textRasse.setEditable(editable);
		strRufname.setEditable(editable);
		spinBirthdate.setEnabled(editable);
		txtBemerkung.setEditable(editable);
		listVerlauf.setEnabled(editable);
		enumGeschlecht.setEditable(editable);
		enumGeschlecht.setEnabled(editable);
		spinGewicht.setEnabled(editable);
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
				try {
					Database.insertPatient(strName.getText(), strRufname.getText(), textIdentifizierung.getText(), strFarbe
							.getText(), (double)spinGewicht.getValue(), new java.sql.Date(((Date) spinBirthdate.getValue()).getTime()), gender
							.getType() == GenderType.MALE, textRasse.getText(), txtBemerkung.getText(), null);
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
