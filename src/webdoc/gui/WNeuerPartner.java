/*******************************************************************************
 * Copyright (c) 2015 by the WebDoc group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the WebDoc license.
 * Available inside this binary and at http://webdoc.proctet.net/license.txt
 *******************************************************************************/
package webdoc.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ActionMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.UIManager;
import javax.swing.JSpinner.DateEditor;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.SpinnerDateModel;

import net.miginfocom.swing.MigLayout;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import webdoc.gui.utils.RoleEnumObj;
import webdoc.gui.utils.RoleEnumObj.RoleType;
import webdoc.lib.Database;
import webdoc.lib.GUIManager;

@SuppressWarnings("serial")
public class WNeuerPartner extends JInternalFrame {

	/**
	 * 
	 */
	//private static final long serialVersionUID = -2791732649836492001L; DON'T #22
	private Logger logger = LogManager.getLogger();
	private JTextField textName;
	private JTextField textTitel;
	private JTextField textHausnummer;
	private RoleEnumObj[] rolle_lokalisiert = {new RoleEnumObj("Bitte Auswählen", RoleType.UNKNOWN),new RoleEnumObj("Patientenbesitzer", RoleType.PETOWNER),new RoleEnumObj("Arzt", RoleType.MEDIC) };
	private JComboBox<RoleEnumObj> enumRole;
	private JTextField textStraße;
	private JTextField textOrtsteil;
	private JTextField textOrt;
	private JTextField textPostleitzahl;
	private JTextField textZusatz;
	private JTextField textTelefon;
	private JTextField textFax;
	private JTextField textEmail;
	private boolean editable;
	private JSpinner spinGebdatum;
	private JTextField textVorname;
	private JTextField textHandy;
	private DateEditor dateEditor;
	private long id;
	private JButton btnOk;
	private JButton btnCancelEdit;
	private JTextPane textPaneVerlauf;
	private JPanel panel_2;

	/**
	 * Create the application.
	 */
	public WNeuerPartner(boolean editable, long id) {
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
	 * Initialize the contents of the 
	 */
	private void initialize() {
		setFrameIcon(null);
		setIconifiable(true);
		setResizable(true);
		setClosable(true);
		setMaximizable(true);
		
		setTitle(editable ? "Neuer Partner" : "Partner");
		setBounds(100, 100, 909, 567);
		
		JPanel personenbezogeneDaten = new JPanel();
		
		JPanel pVerlauf = new JPanel();
		
		panel_2 = new JPanel();
		
		JLabel lblAdresse = new JLabel("Adresse");
		
		JLabel lblPostleitzahl = new JLabel("Postleitzahl:");
		
		JLabel lblOrt = new JLabel("Ort:");
		
		JLabel lblOrtsteil = new JLabel("Ortsteil:");
		
		JLabel lblStrae = new JLabel("Straße:");
		
		JLabel lblHausnummer = new JLabel("Hausnummer:");
		
		JLabel lblZusatz = new JLabel("Zusatz:");
		
		JSeparator separator = new JSeparator();
		
		JLabel lblKomunikation = new JLabel("Komunikation");
		
		JLabel lblTelefon = new JLabel("Telefon");
		
		JLabel lblHandy = new JLabel("Handy");
		
		JLabel lblFax = new JLabel("Fax");
		
		JLabel lblEmail = new JLabel("E-Mail");
		
		textHausnummer = new JTextField();
		textHausnummer.setColumns(10);
		
		textStraße = new JTextField();
		textStraße.setColumns(10);
		
		textOrtsteil = new JTextField();
		textOrtsteil.setColumns(10);
		
		textOrt = new JTextField();
		textOrt.setColumns(10);
		
		textPostleitzahl = new JTextField();
		textPostleitzahl.setColumns(10);
		
		textZusatz = new JTextField();
		textZusatz.setColumns(10);
		
		textTelefon = new JTextField();
		textTelefon.setColumns(10);
		
		textHandy = new JTextField();
		textHandy.setColumns(10);
		
		textFax = new JTextField();
		textFax.setColumns(10);
		
		
		textEmail = new JTextField();
		textEmail.setColumns(10);
		
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel_2.createSequentialGroup()
								.addGap(112)
								.addComponent(lblAdresse))
							.addComponent(separator, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 282, GroupLayout.PREFERRED_SIZE)
							.addGroup(gl_panel_2.createSequentialGroup()
								.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
									.addComponent(lblHausnummer)
									.addComponent(lblPostleitzahl)
									.addComponent(lblOrt)
									.addComponent(lblOrtsteil)
									.addComponent(lblStrae)
									.addComponent(lblZusatz))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
									.addComponent(textZusatz, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
									.addComponent(textPostleitzahl, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
									.addComponent(textOrt, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
									.addComponent(textOrtsteil, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
									.addComponent(textStraße, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
									.addComponent(textHausnummer, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(90)
							.addComponent(lblKomunikation))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addComponent(lblTelefon)
								.addComponent(lblHandy)
								.addComponent(lblFax)
								.addComponent(lblEmail))
							.addGap(34)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addComponent(textEmail, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFax, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
								.addComponent(textHandy, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
								.addComponent(textTelefon, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addComponent(lblAdresse)
					.addGap(9)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPostleitzahl)
						.addComponent(textPostleitzahl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblOrt)
						.addComponent(textOrt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblOrtsteil)
						.addComponent(textOrtsteil, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStrae)
						.addComponent(textStraße, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblHausnummer)
						.addComponent(textHausnummer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblZusatz)
						.addComponent(textZusatz, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(14)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblKomunikation)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTelefon)
						.addComponent(textTelefon, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblHandy)
						.addComponent(textHandy, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFax)
						.addComponent(textFax, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmail)
						.addComponent(textEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(58))
		);
		panel_2.setLayout(gl_panel_2);
		
		JPanel panel_1 = new JPanel();
		
		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout("", "[][][]", "[]"));
		
		btnOk = new JButton();
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(id == -1){
					addPartner();
				}else if(editable){
					updatePartner();
				}else{
					dispose();
					return;
				}
			}

		});
		panel.add(btnOk, "cell 0 0");
		
		btnCancelEdit = new JButton();
		btnCancelEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(id == -1){
					dispose();
				}else if(editable){
					if(GUIFunctions.showIgnoreChangesDialog(getFrame())==0){
						editable = false;
						setEditable();
						loadData();
					}
				}else{
					editable = true;
					setEditable();
				}
			}
		});
		panel.add(btnCancelEdit, "cell 2 0");
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
					.addContainerGap(76, Short.MAX_VALUE)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(59))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		btnCancelEdit.setVisible(editable);
		
		JLabel label_1 = new JLabel("Bemerkungen:");
		
		JScrollPane scrollPane = new JScrollPane();
		
		
		JLabel label = new JLabel("Verlauf:");
		
		textPaneVerlauf = new JTextPane();
		textPaneVerlauf.setEditable(false);
		textPaneVerlauf.setBackground(Color.WHITE);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(1)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 324, GroupLayout.PREFERRED_SIZE)
						.addComponent(personenbezogeneDaten, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(31)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(label)
						.addComponent(textPaneVerlauf, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE))
					.addGap(271)
					.addComponent(pVerlauf, GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 259, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(1)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(label)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textPaneVerlauf, GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE)
							.addGap(11))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(53)
							.addComponent(label_1)
							.addPreferredGap(ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 293, GroupLayout.PREFERRED_SIZE)
							.addGap(58))
						.addComponent(pVerlauf, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(personenbezogeneDaten, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
							.addGap(1)
							.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 361, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)))
					.addGap(0))
		);
		pVerlauf.setVisible(!editable);
		GroupLayout gl_pVerlauf = new GroupLayout(pVerlauf);
		gl_pVerlauf.setHorizontalGroup(
			gl_pVerlauf.createParallelGroup(Alignment.TRAILING)
				.addGap(0, 24, Short.MAX_VALUE)
		);
		gl_pVerlauf.setVerticalGroup(
			gl_pVerlauf.createParallelGroup(Alignment.LEADING)
				.addGap(0, 538, Short.MAX_VALUE)
		);
		pVerlauf.setLayout(gl_pVerlauf);
		
		JLabel lblNewLabel = new JLabel("Name:");
		
		JLabel lblVorname = new JLabel("Vorname:");
		
		JLabel lblTitel = new JLabel("Titel:");
		
		textName = new JTextField();
		textName.setColumns(10);
		
		
		textVorname = new JTextField();
		textVorname.setColumns(10);
		
		
		textTitel = new JTextField();
		textTitel.setColumns(10);
		
		
		JLabel lblGeburtsdatum = new JLabel("Geburtsdatum:");
		
		SpinnerDateModel model = new SpinnerDateModel();
		model.setCalendarField(Calendar.MINUTE);
		spinGebdatum = new JSpinner();
		spinGebdatum.setModel(model);
		dateEditor = new JSpinner.DateEditor(spinGebdatum, "dd-MM-yyyy");
		spinGebdatum.setEditor(dateEditor);
		
		enumRole = new JComboBox<RoleEnumObj>();
		enumRole.setModel(new DefaultComboBoxModel<RoleEnumObj>(rolle_lokalisiert));
		personenbezogeneDaten.setLayout(new MigLayout("", "[24px][5px][21px][4px][18px][4px][164px,center]", "[20px][20px][20px][20px][20px]"));
		personenbezogeneDaten.add(lblVorname, "cell 0 2 3 1,growx,aligny center");
		personenbezogeneDaten.add(lblNewLabel, "cell 0 1 3 1,alignx left,aligny center");
		personenbezogeneDaten.add(lblTitel, "cell 0 3,alignx left,aligny center");
		personenbezogeneDaten.add(textTitel, "cell 4 3 3 1,growx,aligny top");
		personenbezogeneDaten.add(textName, "cell 4 1 3 1,growx,aligny top");
		personenbezogeneDaten.add(textVorname, "cell 4 2 3 1,growx,aligny top");
		personenbezogeneDaten.add(lblGeburtsdatum, "cell 0 4 5 1,alignx left,aligny bottom");
		personenbezogeneDaten.add(spinGebdatum, "cell 6 4,growx,aligny top");
		personenbezogeneDaten.add(enumRole, "cell 2 0 5 1,growx,aligny top");
		getContentPane().setLayout(groupLayout);
		
		setEditable();
	}
	
	/**
	 * simple instance provider for events
	 * @return 
	 */
	private WNeuerPartner getFrame(){
		return this;
	}
	
	/**
	 * Load data with a new ID, wrapper for search event handler
	 * @param id
	 * @author "Aron Heinecke"
	 */
	private void loadData(long id){
		this.id = id;
		loadData();
	}
	
	/**
	 * Loads the animal data if id not -1
	 * (while id 0 is also never given out)
	 * @author "Aron Heinecke"
	 */
	private void loadData(){
		if(id != -1){
			logger.debug("currently not implemented");
		}
	}
	
	/**
	 * ReSet editable for all input elements
	 * @author "Aron Heinecke"
	 */
	private void setEditable(){
		textFax.setEditable(editable);
		textHandy.setEditable(editable);
		textTelefon.setEditable(editable);
		textZusatz.setEditable(editable);
		textHausnummer.setEditable(editable);
		textPostleitzahl.setEditable(editable);
		textOrt.setEditable(editable);
		textOrtsteil.setEditable(editable);
		textStraße.setEditable(editable);
		textEmail.setEditable(editable);
		textName.setEditable(editable);
		textVorname.setEditable(editable);
		textTitel.setEditable(editable);
		spinGebdatum.setEnabled(editable);
		updateEditBtns();
	}
	
	/**
	 * updates buttons that change on selected element change also
	 */
	private void updateEditBtns(){
		btnOk.setText(editable ? "Speichern" : "Schließen");
		btnCancelEdit.setVisible(id > -1 || editable);
		btnCancelEdit.setText(id == -1 || editable ? "Cancel" : "Editieren");
	}
	
	private void updateTitle(String name, String nachname){
		this.setTitle("Partner - "+name+" "+nachname);
	}
	
	/**
	 * adds a partner
	 */
	protected void addPartner() {
		if(allSet()){
			try {
				id = Database.insertPartner(textVorname.getText(), textName.getText(), textTitel.getText(), new java.sql.Date(((Date) spinGebdatum.getValue()).getTime()),textPaneComment.getText());
				editable = false;
				setEditable();
			} catch (SQLException e) {
				GUIManager.showDBErrorDialog(this, Database.DBExceptionConverter(e,true));
			}
		}else{
			GUIManager.showErrorDialog(this, "Es sind nicht alle Felder ausgefüllt!", "Fehlende Angaben");
		}
	}
	
	/**
	 * updates a partner
	 */
	private void updatePartner() {
		logger.debug("no updating implemented!");
		editable = false;
		setEditable();
	}
	
	@Override
	public void dispose()
	{
		if(editable){
			if(GUIFunctions.showIgnoreChangesDialog(this)==1)
				return;
		}
		((ActionMap)UIManager.getLookAndFeelDefaults().get("InternalFrame.actionMap")).remove("showSystemMenu");
		super.dispose();
		GUIManager.dropJID(this);
	}
	
	private boolean allSet(){
		if(textVorname.equals(""))
			return false;
		if(textName.equals(""))
			return false;
		
		return true;
	}
	

	private void exit(){
		this.dispose();
		if(id != -1)
			GUIManager.dropJID(this);
	}
}
