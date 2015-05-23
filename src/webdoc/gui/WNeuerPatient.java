/*******************************************************************************
 * Copyright (c) 2015 by the WebDoc group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the WebDoc license.
 * Available inside this binary and at http://webdoc.proctet.net/license.txt
 *******************************************************************************/
package webdoc.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.ActionMap;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.TableRowSorter;

import net.miginfocom.swing.MigLayout;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import webdoc.gui.utils.ACElement;
import webdoc.gui.utils.ACElement.ElementType;
import webdoc.gui.utils.GenderEnumObj;
import webdoc.gui.utils.GenderEnumObj.GenderType;
import webdoc.gui.utils.JSearchTextField;
import webdoc.gui.utils.JSearchTextField.searchFieldAPI;
import webdoc.gui.utils.PatientTableModel;
import webdoc.gui.utils.TDListElement;
import webdoc.gui.utils.TDListElement.LEType;
import webdoc.lib.Database;
import webdoc.lib.Database.DBError;
import webdoc.lib.GUIManager;

@SuppressWarnings("serial")
public class WNeuerPatient extends JInternalFrame {
	// private static final long serialVersionUID = -4647611743598708383L; DON'T
	// #22
	private JSearchTextField textAnimalSuche;
	private Logger logger = LogManager.getLogger();
	private JTextField strName;
	private JSearchTextField textRasse;
	private JTextField strFarbe;
	private JSpinner spinGewicht;
	private JTextField textIdentifizierung;
	private GenderEnumObj[] geschlecht_lokalisiert = { new GenderEnumObj("Bitte Auswählen", GenderType.UNKNOWN),
			new GenderEnumObj("Weiblich", GenderType.FEMALE), new GenderEnumObj("Männlich", GenderType.MALE) };
	private boolean editable = true;
	private JComboBox<GenderEnumObj> enumGeschlecht;
	private JPanel allgemeineDaten;
	private JTextField strRufname;
	private JSpinner spinBirthdate;
	private JButton btnNeueAnamnese;
	private JPanel panelVerlauf;
	private JSpinner.DateEditor dateEditor;
	private long id;
	private JTextPane txtBemerkung;
	private PreparedStatement searchRaceStm;
	private PreparedStatement searchAnimalStm;
	private JButton btnOk;
	private JButton buttonCancelEdit;
	private JButton btnNeueBehandlung;
	private JPanel contentPanel;
	private JCheckBox chckbxBemerkungAnzeigen;
	private JPanel panelBemerkungen;
	private JTable table;
	private PatientTableModel model = new PatientTableModel();

	/**
	 * Create the application.
	 */
	public WNeuerPatient(boolean editable, long id) {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosing(InternalFrameEvent arg0) {
				dispose();
			}
		});
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.editable = editable;
		this.id = id;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setFrameIcon(null);
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);

		getContentPane().setMinimumSize(new Dimension(600, 400));
		setPreferredSize(new Dimension(700, 400));
		setMinimumSize(new Dimension(1, 1));

		setTitle(editable ? "Neuer Patient" : "Patient");
		setBounds(100, 100, 756, 400);

		JPanel downPanel = new JPanel();
		downPanel.setBounds(new Rectangle(1, 1, 1, 1));

		contentPanel = new JPanel();

		JPanel daten = new JPanel();

		allgemeineDaten = new JPanel();
		allgemeineDaten.setBorder(new TitledBorder(null, "Allgemeine Daten", TitledBorder.CENTER, TitledBorder.TOP,
				null, null));
		allgemeineDaten.setAutoscrolls(true);
		GroupLayout gl_daten = new GroupLayout(daten);
		gl_daten.setHorizontalGroup(gl_daten.createParallelGroup(Alignment.LEADING).addGroup(gl_daten
				.createSequentialGroup()
				.addComponent(allgemeineDaten, GroupLayout.PREFERRED_SIZE, 282, Short.MAX_VALUE).addGap(1)));
		gl_daten.setVerticalGroup(gl_daten
				.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_daten
						.createSequentialGroup()
						.addComponent(allgemeineDaten, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(56, Short.MAX_VALUE)));

		JLabel lblName = new JLabel("Name des Tieres:");

		JLabel lblRasse = new JLabel("Rasse:");

		JLabel lblGeschlecht = new JLabel("Geschlecht:");

		JLabel lblGeburtsdatum = new JLabel("Geburtsdatum:");

		JLabel lblHaarkleidfarbe = new JLabel("Haarkleid/Farbe:");

		JLabel lblGewicht = new JLabel("Gewicht:");

		JLabel lblIdentifizierung = new JLabel("Identifizierung:");

		strName = new JTextField();
		strName.setColumns(10);

		textRasse = new JSearchTextField();
		textRasse.setColumns(10);

		strFarbe = new JTextField();
		strFarbe.setColumns(10);

		spinGewicht = new JSpinner();

		textIdentifizierung = new JTextField();
		textIdentifizierung.setColumns(10);

		enumGeschlecht = new JComboBox<GenderEnumObj>();
		enumGeschlecht.setModel(new DefaultComboBoxModel<GenderEnumObj>(geschlecht_lokalisiert));
		spinBirthdate = new JSpinner();
		
		spinBirthdate.setEnabled(editable);

		strRufname = new JTextField();
		strRufname.setColumns(10);

		JLabel lblRufname = new JLabel("Rufname:");
		allgemeineDaten.setLayout(new MigLayout("", "[83px][18px][145px]", "[24px,center][24px][24px][24px][24px][24px][24px][24px]"));
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
		allgemeineDaten.add(lblIdentifizierung, "cell 0 7,alignx left,aligny center");
		allgemeineDaten.add(lblGewicht, "cell 0 6,alignx left,aligny center");
		allgemeineDaten.add(spinGewicht, "cell 1 6 2 1,growx,aligny top");
		allgemeineDaten.add(textIdentifizierung, "cell 1 7 2 1,growx,aligny top");
		daten.setLayout(gl_daten);

		textAnimalSuche = new JSearchTextField();
		textAnimalSuche.setColumns(10);

		panelVerlauf = new JPanel();
		panelVerlauf.setBorder(new TitledBorder(UIManager.getBorder("CheckBoxMenuItem.border"), "Verlauf", TitledBorder.CENTER, TitledBorder.TOP, null, null));

		panelVerlauf.setVisible(!editable);

		panelBemerkungen = new JPanel();
		panelBemerkungen.setFont(GUIManager.getCommentFont());

		JLabel lblBemerkungen = new JLabel("Bemerkungen:");

		JScrollPane sPaneBemerkungen = new JScrollPane();
		GroupLayout gl_panelBemerkungen = new GroupLayout(panelBemerkungen);
		gl_panelBemerkungen.setHorizontalGroup(
			gl_panelBemerkungen.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelBemerkungen.createSequentialGroup()
					.addGap(2)
					.addComponent(sPaneBemerkungen, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE))
				.addGroup(gl_panelBemerkungen.createSequentialGroup()
					.addGap(1)
					.addComponent(lblBemerkungen, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
					.addGap(1))
		);
		gl_panelBemerkungen.setVerticalGroup(
			gl_panelBemerkungen.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelBemerkungen.createSequentialGroup()
					.addComponent(lblBemerkungen)
					.addGap(2)
					.addComponent(sPaneBemerkungen, GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE))
		);

		txtBemerkung = new JTextPane();
		txtBemerkung
				.setFont(new Font("SansSerif", txtBemerkung.getFont().getStyle(), txtBemerkung.getFont().getSize()));
		sPaneBemerkungen.setViewportView(txtBemerkung);
		panelBemerkungen.setLayout(gl_panelBemerkungen);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(textAnimalSuche, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(daten, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelVerlauf, GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
					.addGap(1)
					.addComponent(panelBemerkungen, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(1))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(panelVerlauf, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
						.addComponent(panelBemerkungen, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(textAnimalSuche, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(daten, GroupLayout.PREFERRED_SIZE, 286, GroupLayout.PREFERRED_SIZE)))
					.addGap(0))
		);
		panelVerlauf.setLayout(new BorderLayout(0, 0));
		
		table = new JTable(model);
		table.setShowGrid(false);
		table.setShowHorizontalLines(false);
		table.setShowVerticalLines(false);
		table.setRowMargin(0);
		table.setIntercellSpacing(new Dimension(0,0));
		table.setFillsViewportHeight(true);
		TableRowSorter<PatientTableModel> sorter = new TableRowSorter<PatientTableModel>(model);
		table.setRowSorter(sorter);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent mevent) {
				if(mevent.getButton() == MouseEvent.BUTTON1){
					if(mevent.getClickCount() >= 2){
						GUIManager.callWNewAnamnesis(true, id, model.getTDLEAt(table.getSelectedRow()).getID(), strName.getText());
					}
				}
			}
		});
		panelVerlauf.add(table);
		contentPanel.setLayout(gl_contentPanel);
		downPanel.setLayout(new MigLayout("", "[29.00][42.00][][left][left][left]", "[26.00]"));

		btnOk = new JButton();
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (id == -1 && editable) {
					addPatient();
				} else if (editable) {
					updatePatient();
				} else {
					dispose();
					return;
				}
			}
		});
		downPanel.add(btnOk, "cell 1 0,alignx center");

		buttonCancelEdit = new JButton("Cancel");
		buttonCancelEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (id == -1) {
					dispose();
				} else if (editable) {
					if (GUIFunctions.showIgnoreChangesDialog(getFrame()) == 0) {
						editable = false;
						setEditable();
						loadData();
					}
				} else {
					editable = true;
					setEditable();
				}
			}
		});

		downPanel.add(buttonCancelEdit, "cell 2 0");

		btnNeueAnamnese = new JButton("Neue Anamnese");
		btnNeueAnamnese.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUIManager.callWNewAnamnesis(true,id, -1,strName.getText());
			}
		});
		downPanel.add(btnNeueAnamnese, "cell 3 0");

		btnNeueBehandlung = new JButton("Neue Behandlung");
		downPanel.add(btnNeueBehandlung, "cell 4 0");

		buttonCancelEdit.setVisible(editable);

		/**
		 * Default DataProvider for these kinds
		 * 
		 * @author "Aron Heinecke"
		 */
		class RaceProvider implements searchFieldAPI {
			@Override
			public List<ACElement> getData(String text) {
				List<ACElement> list = new ArrayList<ACElement>();
				try {
					searchRaceStm.setString(1, "%" + text + "%");
					ResultSet result = searchRaceStm.executeQuery();

					while (result.next()) {
						list.add(new ACElement(result.getString(2), "", result.getLong(1), ElementType.RACE));
					}
					result.close();

				} catch (SQLException e) {
					GUIManager.showDBErrorDialog(null, Database.DBExceptionConverter(e, true));
				}
				return list;
			}

			@Override
			public boolean changedSelectionEvent(ACElement element) {
				logger.debug("Element chosen: {}", element);
				// doing nothing, we only want to provide a search for the
				// existing types
				return true;
			}

			@Override
			public String listRenderer(ACElement element) {
				return element.getName();
			}
		}
		textRasse.setAPI(new RaceProvider());
		/**
		 * Default DataProvider for these kinds
		 * 
		 * @author "Aron Heinecke"
		 */
		class AnimalProvider implements searchFieldAPI {
			@Override
			public List<ACElement> getData(String text) {
				List<ACElement> list = new ArrayList<ACElement>();
				try {
					searchAnimalStm.setString(1, "%" + text + "%");
					searchAnimalStm.setString(2, "%" + text + "%");
					ResultSet result = searchAnimalStm.executeQuery();

					while (result.next()) {
						list.add(new ACElement(result.getString(1), result.getString(2), result.getLong(3),
								ElementType.ANIMAL));
					}
					result.close();

				} catch (SQLException e) {
					GUIManager.showDBErrorDialog(null, Database.DBExceptionConverter(e, true));
				}
				return list;
			}

			@Override
			public boolean changedSelectionEvent(ACElement element) {
				if (editable) {
					if (GUIManager
							.showYesNoDialog(getFrame(), "Änderungen verwerfen ?", JOptionPane.WARNING_MESSAGE, "Änderungen verwerfen..") == 0)
						return false;
				}
				loadData(element.getID());
				updateEditBtns();
				return true;
			}

			@Override
			public String listRenderer(ACElement element) {
				return element.getName() + " " + element.getOptname();
			}
		}
		textAnimalSuche.setAPI(new AnimalProvider());

		SpinnerDateModel model = new SpinnerDateModel();
		spinBirthdate.setModel(model);
		dateEditor = new JSpinner.DateEditor(spinBirthdate, "dd-MM-yyyy");
		spinBirthdate.setEditor(dateEditor);
		spinGewicht.setModel(new SpinnerNumberModel(new Double(0), null, null, new Double(1)));
		model.setCalendarField(Calendar.MINUTE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		getContentPane().add(downPanel, BorderLayout.SOUTH);
		
		chckbxBemerkungAnzeigen = new JCheckBox("Bemerkung anzeigen");
		chckbxBemerkungAnzeigen.setSelected(true);
		chckbxBemerkungAnzeigen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelBemerkungen.setVisible(chckbxBemerkungAnzeigen.isSelected());
			}
		});
		downPanel.add(chckbxBemerkungAnzeigen, "cell 5 0");
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		try {
			searchRaceStm = Database.prepareRaceSearchStm();
		} catch (SQLException e) {
			GUIManager.showDBErrorDialog(null, Database.DBExceptionConverter(e, true));
		}

		setEditable();

		this.pack();
		try {
			searchAnimalStm = Database.prepareStm(Database.getAnimalSearchStm());
		} catch (SQLException e) {
			GUIManager.showDBErrorDialog(this, Database.DBExceptionConverter(e, true));
		}

		loadData();
	}

	private void updateTitle(String name, String rufname) {
		this.setTitle("Patient - " + name + " " + rufname);
	}
	
	private void loadHistoryData(){
		try {
			ResultSet rs = Database.getPatientRData(id);
			while(rs.next()){
				model.add(new TDListElement(rs.getLong(1), rs.getInt(3) == 0 ? LEType.TYPE_A : LEType.TYPE_B, rs.getDate(2)));
			}
			rs.close();
		} catch (SQLException e) {
			GUIManager.showDBErrorDialog(this, Database.DBExceptionConverter(e, true));
		}
	}

	/**
	 * Loads the animal data if id not -1 (while id 0 is also never given out)
	 * 
	 * @author "Aron Heinecke"
	 */
	private void loadData() {
		if (id != -1) {
			try {
				ResultSet result = Database.getAnimal(id);
				result.next();
				strName.setText(result.getString(1));
				strRufname.setText(result.getString(2));
				textIdentifizierung.setText(result.getString(3));
				strFarbe.setText(result.getString(4));
				spinGewicht.setValue(result.getDouble(5));
				spinBirthdate.setValue(result.getDate(6));
				enumGeschlecht.setSelectedItem(result.getBoolean(7) == true ? geschlecht_lokalisiert[1]
						: geschlecht_lokalisiert[2]);
				logger.debug("picID: {}", result.getString(9));
				txtBemerkung.setText(result.getString(8));
				textRasse.setTextWithoutNotification(result.getString(8));
				updateTitle(result.getString(1), result.getString(2));
				result.close();
				loadHistoryData();
			} catch (SQLException e) {
				GUIManager.showDBErrorDialog(this, Database.DBExceptionConverter(e, true));
			}
		}
	}

	/**
	 * simple instance provider for events
	 * 
	 * @return
	 */
	private WNeuerPatient getFrame() {
		return this;
	}

	/**
	 * Load data with a new ID, wrapper for search event handler
	 * 
	 * @param id
	 * @author "Aron Heinecke"
	 */
	private void loadData(long id) {
		this.id = id;
		loadData();
	}

	/**
	 * ReSet editable for all input elements
	 * @author "Aron Heinecke"
	 */
	private void setEditable() {
		logger.debug("ID internal {}", id);
		textAnimalSuche.setEditable(!editable);
		strName.setEditable(editable);
		strFarbe.setEditable(editable);
		textRasse.setEditable(editable);
		strRufname.setEditable(editable);
		spinBirthdate.setEnabled(editable);
		txtBemerkung.setEditable(editable);
		enumGeschlecht.setEditable(editable);
		enumGeschlecht.setEnabled(editable);
		spinGewicht.setEnabled(editable);
		textIdentifizierung.setEditable(editable);
		updateEditBtns();
	}

	/**
	 * updates buttons that change on selected element change also
	 */
	private void updateEditBtns() {
		btnNeueAnamnese.setEnabled(id != -1);
		btnNeueBehandlung.setEnabled(id != -1);
		btnOk.setText(editable ? "Speichern" : "Schließen");
		buttonCancelEdit.setVisible(id > -1 || editable);
		buttonCancelEdit.setText(id == -1 || editable ? "Cancel" : "Editieren");
	}

	@Override
	public void dispose() {
		if (editable) {
			if (GUIFunctions.showIgnoreChangesDialog(this) == 1)
				return;
		}
		Database.closePStatement(searchRaceStm);
		Database.closePStatement(searchAnimalStm);
		((ActionMap) UIManager.getLookAndFeelDefaults().get("InternalFrame.actionMap")).remove("showSystemMenu");
		super.dispose();
		GUIManager.dropJID(this);
	}
	
	/**
	 * add a patient
	 * 
	 * @author "Aron Heinecke"
	 */
	private void addPatient() {
		// TODO: add picture support
		if ((GenderEnumObj) enumGeschlecht.getSelectedItem() != null) {
			GenderEnumObj gender = (GenderEnumObj) enumGeschlecht.getSelectedItem();
			if (gender.getType() != GenderType.UNKNOWN) {
				try {
					id = Database
							.insertPatient(strName.getText(), strRufname.getText(), textIdentifizierung.getText(), strFarbe
									.getText(), (double) spinGewicht.getValue(), new java.sql.Date(
									((Date) spinBirthdate.getValue()).getTime()), gender.getType() == GenderType.FEMALE, textRasse
									.getText(), txtBemerkung.getText(), null);
					editable = false;
					setEditable();
				} catch (SQLException e) {
					DBError error = Database.DBExceptionConverter(e);
					GUIManager.showErrorDialog(this, "Error during insertion: " + error, "Insertion error");
				}
			} else {
				JOptionPane.showMessageDialog(textRasse, "Kein Geschlecht ausgewählt!");
				logger.info("No Gender selected!");
			}
		}
	}

	/**
	 * updates the patient
	 * 
	 * @author "Aron Heinecke"
	 */
	private void updatePatient() {
		if ((GenderEnumObj) enumGeschlecht.getSelectedItem() != null) {
			GenderEnumObj gender = (GenderEnumObj) enumGeschlecht.getSelectedItem();
			if (gender.getType() != GenderType.UNKNOWN) {
				try {
					Database.updatePatient(id, strName.getText(), strRufname.getText(), textIdentifizierung.getText(), strFarbe
							.getText(), (double) spinGewicht.getValue(), new java.sql.Date(((Date) spinBirthdate
							.getValue()).getTime()), gender.getType() == GenderType.FEMALE, textRasse.getText(), txtBemerkung
							.getText(), null);
					editable = false;
					setEditable();
				} catch (SQLException e) {
					DBError error = Database.DBExceptionConverter(e);
					GUIManager.showErrorDialog(this, "Error during insertion: " + error, "Insertion error");
				}
			} else {
				JOptionPane.showMessageDialog(textRasse, "Kein Geschlecht ausgewählt!");
				logger.info("No Gender selected!");
			}
		}
	}
	
	private boolean allSet(){
		if(strName.getText().isEmpty())
			return false;
		if(textRasse.getText().equals(""))
			return false;
		if(enumGeschlecht.equals(GenderType.UNKNOWN))
			return false;
		if (invalidDouble(spinGewicht.getValue().toString()))
		    return false;
	    if (Double.parseDouble(spinGewicht.getValue().toString()) == 0.0)
	        return false;
		if (strRufname.getText().equals(""))
			return false;
		if (strRufname.getText().length() > 20)
		    return false;
	    if (strName.getText().length() > 50)
	        return false;
        if (strFarbe.getText().length() > 20)
            return false;
        if (textIdentifizierung.getText().length() > 30)
            return false;
        if (txtBemerkung.getText().toString().length() > 100)
            return false;
		return true;
	}
	
	private boolean invalidInt(String s){
		try{
			int i = Integer.parseInt(s);
			return false;
		}catch(NumberFormatException e){
			return true;
		}	
	}
	
	private boolean invalidDouble(String s){
		try{
		double d = Double.parseDouble(s);
		return false;
		}catch(NumberFormatException e){
			return true;
		}	
	}
}