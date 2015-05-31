/*******************************************************************************
 * Copyright (c) 2015 by the WebDoc group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the WebDoc license.
 * Available inside this binary and at http://webdoc.proctet.net/license.txt
 *******************************************************************************/
package webdoc.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.ActionMap;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DateEditor;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerDateModel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import net.miginfocom.swing.MigLayout;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import webdoc.gui.utils.ACElement;
import webdoc.gui.utils.ACElement.ElementType;
import webdoc.gui.utils.JSearchTextField;
import webdoc.gui.utils.JSearchTextField.searchFieldAPI;
import webdoc.gui.utils.RoleEnumObj;
import webdoc.gui.utils.RoleEnumObj.RoleType;
import webdoc.lib.Database;
import webdoc.lib.GUIManager;
import webdoc.webdoc.Config;

@SuppressWarnings("serial")
public class WNeuerPartner extends JInternalFrame {

	// private static final long serialVersionUID = -2791732649836492001L; DON'T
	// #22
	private Logger logger = LogManager.getLogger();
	private JTextField textName;
	private JTextField textTitel;
	private JTextField textHausnummer;
	private RoleEnumObj[] rolle_lokalisiert = { new RoleEnumObj("Privatperson", RoleType.PRIVATE),
			new RoleEnumObj("Unternehmer", RoleType.COMMERCIAL) };
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
	private long partnerroleid = -1;
	private JButton btnOk;
	private JButton btnCancelEdit;
	private JList<ACElement> JListTiere;
	private JPanel panel_2;
	private JTextPane textComment;
	private JScrollPane scrollPaneComment;
	private JSearchTextField animalSearchText;
	private PreparedStatement searchAnimalStm;
	private ACElement pickedAnimal;
	private JButton btnHinzufgen;
	private JPopupMenu listmenu;

	/**
	 * Create the application.
	 */
	public WNeuerPartner(boolean editable, long id) {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameActivated(InternalFrameEvent arg0) {
				loadAnimals();
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.editable = editable;
		this.id = id;
		initialize();
		loadData();
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
		setBounds(100, 100, 1088, 564);

		JPanel contentPanel = new JPanel();

		JPanel downPanel = new JPanel();
		downPanel.setLayout(new MigLayout("", "[][][][][]", "[]"));

		btnOk = new JButton();
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (id == -1 || editable) {
					entryPartner();
				} else {
					dispose();
					return;
				}
			}

		});
		downPanel.add(btnOk, "cell 0 0");

		btnCancelEdit = new JButton();
		btnCancelEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		downPanel.add(btnCancelEdit, "cell 2 0");

		JCheckBox chckbxKommentarAnzeigen = new JCheckBox("Kommentar anzeigen");
		chckbxKommentarAnzeigen.setSelected(true);
		chckbxKommentarAnzeigen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				commentViewAction(((AbstractButton) arg0.getSource()).isSelected());
			}
		});
		downPanel.add(chckbxKommentarAnzeigen, "cell 4 0");
		btnCancelEdit.setVisible(editable);
		
		listmenu = new JPopupMenu();
		JMenuItem pm = new JMenuItem("Entfernen");
		pm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				logger.debug("recived delete command");
				try {
					if(JListTiere.getSelectedValue().getID() > 0){
						Database.removeRelationship(id, JListTiere.getSelectedValue().getID());
						loadAnimals();
					}
				} catch (SQLException e1) {
					GUIManager.showDBErrorDialog(getParent(), Database.DBExceptionConverter(e1, true));
				}
			}
		});
		listmenu.add(pm);
		
		JListTiere = new JList<ACElement>(new DefaultListModel<ACElement>());
		JListTiere.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent mevent) {
				if(mevent.getButton() == MouseEvent.BUTTON1){
					if(mevent.getClickCount() >= 2){
						openPatient();
					}
				}else{
		            int row = JListTiere.locationToIndex(mevent.getPoint());
		            JListTiere.setSelectedIndex(row);
					listmenu.show(mevent.getComponent(), mevent.getX(), mevent.getY());
				}
			}
		});
		JListTiere.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER)
					openPatient();
			}
		});
		JListTiere.setBorder(new TitledBorder(UIManager.getBorder("CheckBoxMenuItem.border"), "Tiere",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		JListTiere.setBackground(Color.WHITE);

		JPanel personenbezogeneDaten = new JPanel();

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
		spinGebdatum = new JSpinner();

		enumRole = new JComboBox<RoleEnumObj>();
		enumRole.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					getPRID();
				} catch (SQLException e) {
					GUIManager.showDBErrorDialog(getFrame(), Database.DBExceptionConverter(e, true));
				}
				logger.debug("PartnerRoleID: {}",partnerroleid);
				updateEditable();
				if(partnerroleid != -1)
					loadData();
			}
		});
		enumRole.setModel(new DefaultComboBoxModel<RoleEnumObj>(rolle_lokalisiert));
		personenbezogeneDaten.setLayout(new MigLayout("", "[24px][5px][21px][4px][18px][4px][164px,center]",
				"[20px][20px][20px][20px][20px]"));
		personenbezogeneDaten.add(lblVorname, "cell 0 2 3 1,growx,aligny center");
		personenbezogeneDaten.add(lblNewLabel, "cell 0 1 3 1,alignx left,aligny center");
		personenbezogeneDaten.add(lblTitel, "cell 0 3,alignx left,aligny center");
		personenbezogeneDaten.add(textTitel, "cell 4 3 3 1,growx,aligny top");
		personenbezogeneDaten.add(textName, "cell 4 1 3 1,growx,aligny top");
		personenbezogeneDaten.add(textVorname, "cell 4 2 3 1,growx,aligny top");
		personenbezogeneDaten.add(lblGeburtsdatum, "cell 0 4 5 1,alignx left,aligny bottom");
		personenbezogeneDaten.add(spinGebdatum, "cell 6 4,growx,aligny top");
		personenbezogeneDaten.add(enumRole, "cell 2 0 5 1,growx,aligny top");

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
		gl_panel_2
				.setHorizontalGroup(gl_panel_2
						.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2
								.createSequentialGroup()
								.addGroup(gl_panel_2
										.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel_2
												.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_panel_2.createSequentialGroup().addGap(112)
														.addComponent(lblAdresse))
												.addComponent(separator, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
												.addGroup(gl_panel_2
														.createSequentialGroup()
														.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
																.addComponent(lblHausnummer)
																.addComponent(lblPostleitzahl).addComponent(lblOrt)
																.addComponent(lblOrtsteil).addComponent(lblStrae)
																.addComponent(lblZusatz))
														.addPreferredGap(ComponentPlacement.RELATED)
														.addGroup(gl_panel_2
																.createParallelGroup(Alignment.LEADING)
																.addComponent(textZusatz, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
																.addComponent(textPostleitzahl, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
																.addComponent(textOrt, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
																.addComponent(textOrtsteil, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
																.addComponent(textStraße, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
																.addComponent(textHausnummer, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE))))
										.addGroup(gl_panel_2.createSequentialGroup().addGap(90)
												.addComponent(lblKomunikation))
										.addGroup(gl_panel_2
												.createSequentialGroup()
												.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
														.addComponent(lblTelefon).addComponent(lblHandy)
														.addComponent(lblFax).addComponent(lblEmail))
												.addGap(34)
												.addGroup(gl_panel_2
														.createParallelGroup(Alignment.LEADING)
														.addComponent(textEmail, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
														.addComponent(textFax, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
														.addComponent(textHandy, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
														.addComponent(textTelefon, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE))))
								.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		gl_panel_2
				.setVerticalGroup(gl_panel_2
						.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2
								.createSequentialGroup()
								.addComponent(lblAdresse)
								.addGap(9)
								.addGroup(gl_panel_2
										.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblPostleitzahl)
										.addComponent(textPostleitzahl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(12)
								.addGroup(gl_panel_2
										.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblOrt)
										.addComponent(textOrt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(12)
								.addGroup(gl_panel_2
										.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblOrtsteil)
										.addComponent(textOrtsteil, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(12)
								.addGroup(gl_panel_2
										.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblStrae)
										.addComponent(textStraße, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(12)
								.addGroup(gl_panel_2
										.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblHausnummer)
										.addComponent(textHausnummer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(12)
								.addGroup(gl_panel_2
										.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblZusatz)
										.addComponent(textZusatz, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblKomunikation)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_panel_2
										.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblTelefon)
										.addComponent(textTelefon, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_panel_2
										.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblHandy)
										.addComponent(textHandy, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_panel_2
										.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblFax)
										.addComponent(textFax, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_panel_2
										.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblEmail)
										.addComponent(textEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(58)));
		panel_2.setLayout(gl_panel_2);

		scrollPaneComment = new JScrollPane();
		scrollPaneComment.setBackground(Color.WHITE);
		scrollPaneComment.setBorder(new TitledBorder(UIManager.getBorder("CheckBoxMenuItem.border"), "Bemerkung",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		animalSearchText = new JSearchTextField();
		
		btnHinzufgen = new JButton("Hinzufügen");
		btnHinzufgen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Database.insertRelationship(id, pickedAnimal.getID());
					btnHinzufgen.setEnabled(false);
					animalSearchText.setTextWithoutNotification("");
					loadAnimals();
				} catch (SQLException e) {
					GUIManager.showDBErrorDialog(getParent(), Database.DBExceptionConverter(e,true));
				}
			}
		});
		btnHinzufgen.setEnabled(false);
		GroupLayout gl_contentPane = new GroupLayout(contentPanel);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(personenbezogeneDaten, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(animalSearchText, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnHinzufgen))
						.addComponent(JListTiere, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPaneComment, GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
					.addGap(172))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(1)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPaneComment, GroupLayout.DEFAULT_SIZE, 517, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(personenbezogeneDaten, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 342, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(animalSearchText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnHinzufgen))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(JListTiere, GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)))
					.addContainerGap())
		);

		textComment = new JTextPane();
		textComment.setFont(GUIManager.getCommentFont());
		scrollPaneComment.setViewportView(textComment);
		contentPanel.setLayout(gl_contentPane);

		SpinnerDateModel model = new SpinnerDateModel();
		model.setCalendarField(Calendar.DAY_OF_MONTH);
		getContentPane().setLayout(new BorderLayout(0, 0));
		spinGebdatum.setModel(model);
		dateEditor = new JSpinner.DateEditor(spinGebdatum, "dd-MM-yyyy");
		spinGebdatum.setEditor(dateEditor);
		spinGebdatum.setValue(GUIFunctions.getDefaultDate());
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		getContentPane().add(downPanel, BorderLayout.SOUTH);
		
		class AnimalProvider implements searchFieldAPI {
			@Override
			public List<ACElement> getData(String text) {
				List<ACElement> list = new ArrayList<ACElement>();
				try {
					searchAnimalStm.setString(1, "%" + text + "%");
					searchAnimalStm.setString(2, "%" + text + "%");
					searchAnimalStm.setLong(3, id);
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
				pickedAnimal = element;
				btnHinzufgen.setEnabled(true);
				return true;
			}

			@Override
			public String listRenderer(ACElement element) {
				return element.getName() + " " + element.getOptname();
			}
		}
		
		animalSearchText.setAPI(new AnimalProvider());
		
		try {
			searchAnimalStm = Database.prepareStm(Database.getRelationshipAddableAnimalsSql());
		} catch (SQLException e) {
			GUIManager.showDBErrorDialog(this, Database.DBExceptionConverter(e, true));
		}
		pack();
		setEditable();
	}
	
	/**
	 * simple instance provider for events
	 * 
	 * @return
	 */
	private WNeuerPartner getFrame() {
		return this;
	}

	/**
	 * Loads the animal data if id not -1 (while id 0 is also never given out)
	 * 
	 * @author "Aron Heinecke"
	 */
	private void loadData() {
		if (id != -1) {
			try {
				{
					ResultSet rs = Database.getPartner(id);
					rs.next();
					textName.setText(rs.getString(1));
					textVorname.setText(rs.getString(2));
					spinGebdatum.setValue(rs.getDate(3));
					textTitel.setText(rs.getString(4));
					textComment.setText(rs.getString(5));
					rs.close();
				}
				getPRID();
				
				if(partnerroleid != -1) {
					{
						ResultSet rs = Database.getPartnerRoleDetails(partnerroleid);
						rs.next();
						textPostleitzahl.setText(String.valueOf(rs.getInt(1)));
						textOrt.setText(rs.getString(2));
						textOrtsteil.setText(rs.getString(3));
						textHausnummer.setText(String.valueOf(rs.getShort(4)));
						textStraße.setText(rs.getString(5));
						textZusatz.setText(rs.getString(6));
						textEmail.setText(rs.getString(7));
						rs.close();
					}
					{
						PreparedStatement stm = Database.prepareTelecommSelectStm();
						{
							stm.setLong(1, Config.getLongValue("COMM_PHONE_ID"));
							stm.setLong(2, partnerroleid);
							ResultSet rs = stm.executeQuery();
							rs.next();
							textTelefon.setText(rs.getString(1));
							rs.close();
						}
						{
							stm.clearParameters();
							stm.setLong(1, Config.getLongValue("COMM_MOBILE_ID"));
							stm.setLong(2, partnerroleid);
							ResultSet rs = stm.executeQuery();
							rs.next();
							textHandy.setText(rs.getString(1));
							rs.close();
						}
						{
							stm.clearParameters();
							stm.setLong(1, Config.getLongValue("COMM_FAX_ID"));
							stm.setLong(2, partnerroleid);
							ResultSet rs = stm.executeQuery();
							rs.next();
							textFax.setText(rs.getString(1));
							rs.close();
						}
					}
				}
				loadAnimals();
			} catch (SQLException e) {
				GUIManager.showDBErrorDialog(this, Database.DBExceptionConverter(e));
			}
		}
	}
	
	/**
	 * Load animals related to the partnerid
	 */
	private void loadAnimals(){
		try {
			ResultSet rs = Database.getPartnerAnimals(id);
			DefaultListModel<ACElement> model = (DefaultListModel<ACElement>) JListTiere.getModel();
			model.clear();
			while(rs.next()){
				logger.debug("found another linked animal");
				model.addElement(new ACElement(rs.getString(2), rs.getLong(1), ElementType.ANIMAL));
			}
		} catch (SQLException e) {
			GUIManager.showDBErrorDialog(this, Database.DBExceptionConverter(e, true));
		}
	}

	/**
	 * ReSet editable for all input elements
	 * 
	 * @author "Aron Heinecke"
	 */
	private void setEditable() {
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
		textComment.setEditable(editable);
		updateEditBtns();
	}

	private void updateEditable() {
		if (id == -1) {
			editable = true;
		} else if (partnerroleid == -1) {
			editable = true;
		} else {
			editable = false;
		}
		setEditable();
	}

	/**
	 * updates buttons that change on selected element change also
	 */
	private void updateEditBtns() {
		btnOk.setText(editable ? "Speichern" : "Schließen");
		btnCancelEdit.setVisible(id > -1 || editable);
		btnCancelEdit.setText(id == -1 || editable ? "Cancel" : "Editieren");
		animalSearchText.setEnabled(id > -1);
	}

	private void updateTitle(String name, String nachname) {
		this.setTitle("Partner - " + name + " " + nachname);
	}

	/**
	 * adds a partner
	 * @author "Aron Heinecke"
	 */
	protected void entryPartner() {
		if (allSet()) {
			try {
				getPRID();
				if (id == -1) {
					id = Database
							.insertPartner(textVorname.getText(), textName.getText(), textTitel.getText(), new java.sql.Date(
									((Date) spinGebdatum.getValue()).getTime()), textComment.getText());
				} else {
					Database.updatePartner(id, textVorname.getText(), textName.getText(), textTitel.getText(), new java.sql.Date(
							((Date) spinGebdatum.getValue()).getTime()), textComment.getText());
				}
				if (partnerroleid == -1) {
					partnerroleid = Database.insertPatnerRoleID(id, getRoleTypeID());
					Database.insertPartnerRoleDetails(partnerroleid, textTelefon.getText(), textHandy.getText(), textFax
							.getText(), textEmail.getText(), Integer.valueOf(textPostleitzahl.getText()), textOrt
							.getText(), Short.valueOf(textHausnummer.getText()), textStraße.getText(), textZusatz
							.getText(), textOrtsteil.getText());
				} else {
					Database.updatePartnerRoleDetails(partnerroleid, textTelefon.getText(), textHandy.getText(), textFax
							.getText(), textEmail.getText(), Integer.valueOf(textPostleitzahl.getText()), textOrt
							.getText(), Short.valueOf(textHausnummer.getText()), textStraße.getText(), textZusatz
							.getText(), textOrtsteil.getText());
				}
				editable = false;
				setEditable();
			} catch (SQLException e) {
				GUIManager.showDBErrorDialog(this, Database.DBExceptionConverter(e, true));
			}
		} else {
			GUIManager.showErrorDialog(this, "Es sind nicht alle Felder ausgefüllt!", "Fehlende Angaben");
		}
	}
	
	/**
	 * Helper class to open a patient fired by JList listeners
	 */
	private void openPatient() {
		GUIManager.addWNeuerPatient(false, JListTiere.getSelectedValue().getID());
	}

	private void commentViewAction(boolean show) {
		boolean ismaximized = this.isMaximum();
		Dimension size = this.getSize();
		scrollPaneComment.setVisible(show);
		pack();
		try {
			this.setMaximum(ismaximized);
		} catch (Exception e) {
		}
		if (!ismaximized)
			this.setSize(size);
	}

	private void getPRID() throws SQLException {
		partnerroleid = Database.getPartnerRoleID(id, getRoleTypeID());
		logger.debug("PartnerRoleID: {}", partnerroleid);
	}

	/**
	 * Retrives the current role id according to the role id types
	 * 
	 * @return
	 */
	private long getRoleTypeID() {
		if (((RoleEnumObj) enumRole.getSelectedItem()).getType() == RoleType.COMMERCIAL)
			return Config.getLongValue("PARTNER_COMMERCIAL_ID");
		else
			return Config.getLongValue("PARTNER_PRIVATE_ID");
	}

	@Override
	public void dispose() {
		if (editable) {
			if (GUIFunctions.showIgnoreChangesDialog(this) == 1)
				return;
		}
		Database.closePStatement(searchAnimalStm);
		((ActionMap) UIManager.getLookAndFeelDefaults().get("InternalFrame.actionMap")).remove("showSystemMenu");
		super.dispose();
		GUIManager.dropJID(this);
	}
	
	private boolean invalidInt(String s){
		try{
			Integer.parseInt(s);
			return false;
		}catch(NumberFormatException e){
			return true;
		}
	}
	
	private boolean allSet() {
		if (textVorname.equals(""))
			return false;
		if (textName.equals(""))
			return false;
		if(invalidInt(textHausnummer.getText()))
			return false;
		if(invalidInt(textPostleitzahl.getText()))
			return false;
		if (textEmail.getText().length() > 40)
			return false;
		if (textTitel.getText().length() > 10)
			return false;
		if(textComment.getText().toString().length() > 100)
			return false;
		if (invalidInt(textPostleitzahl.getText().toString()))
			return false;
		if (invalidInt(textHausnummer.getText().toString()))
			return false;
		if (textStraße.getText().length() > 20)
			return false;
		if (textZusatz.getText().length() > 50)
			return false;
		if (textOrtsteil.getText().length() > 20)
			return false;
		if(GUIFunctions.compareSpinnerDates(spinGebdatum.getValue())){
			return false;
		}
		return true;
	}
}