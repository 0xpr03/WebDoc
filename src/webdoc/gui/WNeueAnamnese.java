/*******************************************************************************
 * Copyright (c) 2015 by the WebDoc group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the WebDoc license.
 * Available inside this binary and at http://webdoc.proctet.net/license.txt
 *******************************************************************************/
package webdoc.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ActionMap;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import net.miginfocom.swing.MigLayout;
import webdoc.gui.utils.EnumObject;
import webdoc.gui.utils.EnumObject.EnumType;
import webdoc.gui.utils.WModelPane;
import webdoc.lib.AnamnesisBP;
import webdoc.lib.Database;
import webdoc.lib.GUIManager;

public class WNeueAnamnese extends WModelPane {

	private static final long serialVersionUID = -2774049861485506927L;
	private Logger logger = LogManager.getLogger();
	private JFrame frame;
	private boolean editable;
	private JTextField tFName;
	private JTextField tPTierStammtVon;
	private JTextField tPVerwendungszweck;
	private JSpinner spinBirthdate_1;
	private JTextPane tpEndokrinium;
	private JTextPane tPSchilddruese;
	private JTextPane tPBauchspeicheldruese;
	private JTextPane tPZNS;
	private JTextPane tPMedikamente;
	private JTextPane tPRoentgen;
	private JTextPane tPCTMRT;
	private JTextPane tPHauptproblem;
	private JTextPane tPSchilderung;
	private JLabel label_1;
	private JLabel label_2;
	private JTextField tFRufname;
	private JSeparator separator;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_5;
	private JScrollPane sPAenderungenFamilie;
	private JTextPane tPAenderungenFamilie;
	private JScrollPane sPVerletzungen;
	private JTextPane tPVerletzungen;
	private JScrollPane sPNarben;
	private JTextPane tPNarben;
	private JScrollPane sPVerhaltensaufaeligkeiten;
	private JTextPane tPVerhaltensaufaeligkeiten;
	private JScrollPane sPAuslandsaufenthalte;
	private JScrollPane sPAtmung;
	private JScrollPane sPImpfungen;
	private JScrollPane sPInfektionen;
	private JScrollPane sPHerzKreislauf;
	private JScrollPane sPVerdauung;
	private JScrollPane sPEndokrinium;
	private JScrollPane sPSchilddruese;
	private JScrollPane SPBauchspeicheldruese;
	private JScrollPane sPZNS;
	private JScrollPane sPMedikamente;
	private JScrollPane sPRoentgen;
	private JScrollPane sPCTMRT;
	private JScrollPane sPHauptproblem;
	private JScrollPane sPSchilderung;
	private JScrollPane sPUnternommen;
	private JComboBox<EnumObject> cBSchmerzempfindlichkeit;
	private JComboBox<EnumObject> cBDenkenSchmerzen;
	private JScrollPane sPSchmerzmittel;
	private JScrollPane sPVerusachenSchmerzen;
	private JScrollPane sPFunktionenMotorik;
	private JComboBox<EnumObject> cBKöperteilBewegen;
	private JSpinner spGehstrecke;
	private JSpinner spGehzeit;
	private JComboBox<EnumObject> cBWitterung;
	private JComboBox<EnumObject> cBZyklus;
	private JSpinner spAuslauf;
	private JSpinner spavailTimeCons;
	private JScrollPane sP_Bemerkungen;
	private long animalID;
	private long anamnesisID;
	private JButton btnEdit;
	private JButton btnOk;
	private JButton btnCancel;
	private JTextPane tPAuslandsaufenthalte;
	private JTextPane tPInfektionen;
	private JTextPane tPImpfungen;
	private JTextPane tPHerzKreislauf;
	private JTextPane tPAtmung;
	private JTextPane tPVerdauung;
	private JComboBox<EnumObject> cBEpiAnfaelle;
	private JTextPane tPUnternommen;
	private JTextPane tPVerusachenSchmerzen;
	private JTextPane tPSchmerzmittel;
	private JTextPane tPFunktionenMotorik;
	private JTextPane tPBemerkungen;
	private JLabel lblHaltung;
	private JTextField tFHalltung;
	
	

	/**
	 * Launch the application.
	 */
	private void setEditable() {
		 logger.debug("anamnesisid: {}\nanimalid: {}\neditable: {}", anamnesisID, animalID, editable);
		 tpEndokrinium.setEditable(editable);
		 tPSchilddruese.setEditable(editable);
		 tPBauchspeicheldruese.setEditable(editable);
		 tPZNS.setEditable(editable);
		 tPMedikamente.setEditable(editable);
		 tPRoentgen.setEditable(editable);
		 tPCTMRT.setEditable(editable);
		 spAuslauf.setEnabled(editable);
		 tPHauptproblem.setEditable(editable);
		 tPAenderungenFamilie.setEditable(editable);
		 tPVerletzungen.setEditable(editable);
		 tPNarben.setEditable(editable);
		 tPVerhaltensaufaeligkeiten.setEditable(editable);
		 tPAuslandsaufenthalte.setEditable(editable);
		 tPInfektionen.setEditable(editable);
		 tPImpfungen.setEditable(editable);
		 tPHerzKreislauf.setEditable(editable);
		 tPAtmung.setEditable(editable);
		 tPVerdauung.setEditable(editable);
		 tPUnternommen.setEditable(editable);
		 tPVerusachenSchmerzen.setEditable(editable);
		 tPSchmerzmittel.setEditable(editable);
		 tPFunktionenMotorik.setEditable(editable);
		 tPBemerkungen.setEditable(editable);
		 tFHalltung.setEditable(editable);
		 tPVerwendungszweck.setEditable(editable);
		 tPTierStammtVon.setEditable(editable);
		 spavailTimeCons.setEnabled(editable);
		 spavailTimeCons.setEnabled(editable);
		 spGehstrecke.setEnabled(editable);
		 spGehzeit.setEnabled(editable);
		 tPSchilderung.setEditable(editable);
		 spinBirthdate_1.setEnabled(editable);
		 cBDenkenSchmerzen.setEnabled(editable);
		 cBEpiAnfaelle.setEnabled(editable);
		 cBZyklus.setEnabled(editable);
		 cBEpiAnfaelle.setEnabled(editable);
		 cBKöperteilBewegen.setEnabled(editable);
		 cBSchmerzempfindlichkeit.setEnabled(editable);
		 cBWitterung.setEnabled(editable);
		reloadBtn(editable);
	}

	private void reloadBtn(boolean editable) {
		btnOk.setText(editable ? "Speichern" : "Schließen");
		btnEdit.setVisible(anamnesisID != -1);
		btnEdit.setEnabled(!editable);
		btnCancel.setVisible(editable);
	}

	/**
	 * Create the application.
	 */
	public WNeueAnamnese(boolean is_editable, long animal_id, long anamnesis_id,
			 String patient_name, String patient_callname) {
		logger.debug("anamnesisid: {}\nanimalid: {}\neditable: {}", anamnesis_id, animal_id, is_editable);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.editable = is_editable;
		initialize(patient_name);
		this.animalID = animal_id;
		this.anamnesisID = anamnesis_id;
		setFrameIcon(null);
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		tPAenderungenFamilie = new JTextPane();

		JPanel panel_3 = new JPanel();
		panel_3.setLayout(new MigLayout("", "[][][]", "[]"));

		btnOk = new JButton("Speichern");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				logger.debug("btnOk fired..\nanamnesisid: {}", anamnesisID);
				if (anamnesisID == -1) {
					saveData(true);
				} else if (editable) {
					saveData(false);
				} else {
					dispose();
				}
			}
		});
		panel_3.add(btnOk, "cell 0 0");

		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (anamnesisID == -1) {
					dispose();
				} else if (editable) {
					if (GUIFunctions.showIgnoreChangesDialog(getFrame()) == 0) {
						editable = false;
						setEditable();
						loadData();
					}
				}
			}

		});
		panel_3.add(btnCancel, "cell 1 0");

		JTabbedPane tabber = new JTabbedPane();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addGap(18)
								.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup().addGap(1)
								.addComponent(tabber, GroupLayout.PREFERRED_SIZE, 1000, Short.MAX_VALUE))).addGap(1)));
		groupLayout
				.setVerticalGroup(groupLayout
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout
								.createSequentialGroup()
								.addGap(1)
								.addComponent(tabber, GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
								.addGap(1)
								.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)));

		JPanel pContent = new JPanel();
		tabber.addTab("Allgemeine Anamnese", null, pContent, null);

		JPanel panel_2 = new JPanel();

		sPAenderungenFamilie = new JScrollPane();
		sPAenderungenFamilie.setViewportBorder(new TitledBorder(UIManager.getBorder("CheckBoxMenuItem.border"),
				"\u00C4nderungen im Familiengef\u00FCge", TitledBorder.CENTER, TitledBorder.ABOVE_TOP, null, null));

		label_1 = new JLabel("Name:");

		label_2 = new JLabel("Rufname:");

		tFRufname = new JTextField(patient_callname);
		tFRufname.setColumns(10);

		tFName = new JTextField(patient_name);
		tFName.setColumns(10);

		separator = new JSeparator();

		label_3 = new JLabel("Verwendungszweck:");

		label_4 = new JLabel("Im Besitz seit:");

		label_5 = new JLabel("Tier stammt von:");

		tPTierStammtVon = new JTextField();
		tPTierStammtVon.setColumns(1);

		spinBirthdate_1 = new JSpinner(); // Datumsmodell
		SpinnerDateModel model = new SpinnerDateModel();
		spinBirthdate_1.setModel(model);
		spinBirthdate_1.setEditor(new JSpinner.DateEditor(spinBirthdate_1, "dd-MM-yyyy"));
		model.setCalendarField(Calendar.MINUTE);
		spinBirthdate_1.setEnabled(editable);

		tPVerwendungszweck = new JTextField();
		tPVerwendungszweck.setColumns(1);

		sPAenderungenFamilie.setViewportView(tPAenderungenFamilie);

		JPanel panel_1 = new JPanel();

		sPAuslandsaufenthalte = new JScrollPane();
		sPAuslandsaufenthalte.setViewportBorder(new TitledBorder(UIManager.getBorder("CheckBoxMenuItem.border"),
				"Auslandsaufenthalte", TitledBorder.CENTER, TitledBorder.TOP, null, null));

		sPVerhaltensaufaeligkeiten = new JScrollPane();
		sPVerhaltensaufaeligkeiten.setViewportBorder(new TitledBorder(null, "Verhaltensauf\u00E4lligkeiten",
				TitledBorder.CENTER, TitledBorder.TOP, null, null));

		sPVerletzungen = new JScrollPane();
		sPVerletzungen.setViewportBorder(new TitledBorder(UIManager.getBorder("CheckBoxMenuItem.border"),
				"Verletzungen", TitledBorder.CENTER, TitledBorder.TOP, null, null));

		sPNarben = new JScrollPane();
		sPNarben.setViewportBorder(new TitledBorder(UIManager.getBorder("CheckBoxMenuItem.border"), "Narben",
				TitledBorder.CENTER, TitledBorder.TOP, null, null));

		tPVerhaltensaufaeligkeiten = new JTextPane();
		sPVerhaltensaufaeligkeiten.setViewportView(tPVerhaltensaufaeligkeiten);

		tPVerletzungen = new JTextPane();
		sPVerletzungen.setViewportView(tPVerletzungen);

		tPNarben = new JTextPane();
		sPNarben.setViewportView(tPNarben);

		tPAuslandsaufenthalte = new JTextPane();
		sPAuslandsaufenthalte.setViewportView(tPAuslandsaufenthalte);

		JSplitPane splitPane = new JSplitPane();
		JSplitPane splitPane_2 = new JSplitPane();
		splitPane_2.setLeftComponent(splitPane);
		splitPane.setLeftComponent(panel_2);

		lblHaltung = new JLabel("Haltung:");

		tFHalltung = new JTextField();
		tFHalltung.setColumns(1);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(1)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(label_1)
							.addGap(24)
							.addComponent(tFName, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(label_2)
							.addGap(8)
							.addComponent(tFRufname, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(separator, GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
							.addGap(4))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(label_3)
							.addGap(8)
							.addComponent(tPVerwendungszweck, GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(label_4)
							.addGap(39)
							.addComponent(spinBirthdate_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(lblHaltung)
							.addGap(65)
							.addComponent(tFHalltung, GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(label_5)
							.addGap(25)
							.addComponent(tPTierStammtVon, GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE))
						.addComponent(sPAenderungenFamilie, GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(7)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(3)
							.addComponent(label_1))
						.addComponent(tFName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(4)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(3)
							.addComponent(label_2))
						.addComponent(tFRufname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(4)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 4, GroupLayout.PREFERRED_SIZE)
					.addGap(4)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(3)
							.addComponent(label_3))
						.addComponent(tPVerwendungszweck, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(4)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(4)
							.addComponent(label_4))
						.addComponent(spinBirthdate_1, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addGap(4)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(3)
							.addComponent(lblHaltung))
						.addComponent(tFHalltung, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(4)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(3)
							.addComponent(label_5))
						.addComponent(tPTierStammtVon, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(1)
					.addComponent(sPAenderungenFamilie, GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
					.addGap(1))
		);
		panel_2.setLayout(gl_panel_2);
		splitPane.setRightComponent(panel_1);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(1)
					.addComponent(sPAuslandsaufenthalte, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
					.addGap(1))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(1)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(sPNarben, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
						.addComponent(sPVerletzungen, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(sPVerhaltensaufaeligkeiten, GroupLayout.PREFERRED_SIZE, 153, Short.MAX_VALUE)
							.addGap(1))))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(1)
					.addComponent(sPAuslandsaufenthalte, GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
					.addGap(1)
					.addComponent(sPVerhaltensaufaeligkeiten, GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
					.addGap(1)
					.addComponent(sPVerletzungen, GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
					.addGap(1)
					.addComponent(sPNarben, GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
					.addGap(1))
		);
		splitPane.setDividerLocation(0.5);
		panel_1.setLayout(gl_panel_1);

		JPanel panel = new JPanel();
		splitPane_2.setRightComponent(panel);

		sPInfektionen = new JScrollPane();
		sPInfektionen.setViewportBorder(new TitledBorder(null, "Infektionserkrankungen", TitledBorder.CENTER,
				TitledBorder.TOP, null, null));

		sPImpfungen = new JScrollPane();
		sPImpfungen.setViewportBorder(new TitledBorder(null, "Regelm\u00E4\u00DFige Impfungen:", TitledBorder.CENTER,
				TitledBorder.TOP, null, null));

		sPHerzKreislauf = new JScrollPane();
		sPHerzKreislauf.setViewportBorder(new TitledBorder(null, "Herz/Kreislauf", TitledBorder.CENTER,
				TitledBorder.TOP, null, null));

		sPAtmung = new JScrollPane();
		sPAtmung.setViewportBorder(new TitledBorder(null, "Atmung", TitledBorder.CENTER, TitledBorder.TOP, null, null));

		tPHerzKreislauf = new JTextPane();
		sPHerzKreislauf.setViewportView(tPHerzKreislauf);

		tPImpfungen = new JTextPane();
		sPImpfungen.setViewportView(tPImpfungen);

		tPInfektionen = new JTextPane();
		sPInfektionen.setViewportView(tPInfektionen);
		GroupLayout gl_pContent = new GroupLayout(pContent);
		gl_pContent.setHorizontalGroup(
			gl_pContent.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pContent.createSequentialGroup()
					.addGap(1)
					.addComponent(splitPane_2, GroupLayout.DEFAULT_SIZE, 993, Short.MAX_VALUE)
					.addGap(1))
		);
		gl_pContent.setVerticalGroup(
			gl_pContent.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pContent.createSequentialGroup()
					.addGap(1)
					.addComponent(splitPane_2, GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE))
		);

		tPAtmung = new JTextPane();
		sPAtmung.setViewportView(tPAtmung);

		sPVerdauung = new JScrollPane();
		sPVerdauung.setViewportBorder(new TitledBorder(null, "Verdauungstrakt", TitledBorder.CENTER, TitledBorder.TOP,
				null, null));

		tPVerdauung = new JTextPane();
		sPVerdauung.setViewportView(tPVerdauung);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(1)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(sPVerdauung, GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(sPAtmung, GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
							.addGap(1))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(sPImpfungen, GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
							.addGap(1))
						.addComponent(sPInfektionen, GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(sPHerzKreislauf, GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
							.addGap(1)))
					.addGap(1))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(1)
					.addComponent(sPInfektionen, GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
					.addGap(1)
					.addComponent(sPImpfungen, GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
					.addGap(1)
					.addComponent(sPHerzKreislauf, GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
					.addGap(1)
					.addComponent(sPAtmung, GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
					.addGap(1)
					.addComponent(sPVerdauung, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
					.addGap(1))
		);
		panel.setLayout(gl_panel);
		pContent.setLayout(gl_pContent);

		JPanel panel_4 = new JPanel();
		tabber.addTab("Allgemeine Anamnese 2", null, panel_4, null);

		JPanel panel_5 = new JPanel();

		sPEndokrinium = new JScrollPane();
		sPEndokrinium.setViewportBorder(new TitledBorder(null, "Endokrinium:", TitledBorder.CENTER, TitledBorder.TOP,
				null, null));

		sPSchilddruese = new JScrollPane();
		sPSchilddruese.setViewportBorder(new TitledBorder(null, "Schilddr\u00FCse", TitledBorder.CENTER,
				TitledBorder.TOP, null, null));

		SPBauchspeicheldruese = new JScrollPane();
		SPBauchspeicheldruese.setViewportBorder(new TitledBorder(null, "Bauchspeicheldr\u00FCse:", TitledBorder.CENTER,
				TitledBorder.TOP, null, null));

		sPZNS = new JScrollPane();
		sPZNS.setViewportBorder(new TitledBorder(null, "ZNS:", TitledBorder.CENTER, TitledBorder.TOP, null, null));

		JLabel lblEpilemtiformeAnflle = new JLabel("Epilemtiforme Anfälle:");

		tpEndokrinium = new JTextPane();
		sPEndokrinium.setViewportView(tpEndokrinium);

		tPSchilddruese = new JTextPane();
		sPSchilddruese.setViewportView(tPSchilddruese);

		tPBauchspeicheldruese = new JTextPane();
		SPBauchspeicheldruese.setViewportView(tPBauchspeicheldruese);

		tPZNS = new JTextPane();
		sPZNS.setViewportView(tPZNS);

		cBEpiAnfaelle = new JComboBox<EnumObject>();
		cBEpiAnfaelle.setModel(new DefaultComboBoxModel<EnumObject>(new EnumObject[] {
				new EnumObject("Keine", EnumType.A), new EnumObject("Gleichgewichtsstörungen", EnumType.B),
				new EnumObject("Vorübergehendes Schwanken", EnumType.C) }));

		JPanel panel_6 = new JPanel();

		sPMedikamente = new JScrollPane();
		sPMedikamente.setViewportBorder(new TitledBorder(null, "Medikamente", TitledBorder.CENTER, TitledBorder.TOP,
				null, null));

		sPRoentgen = new JScrollPane();
		sPRoentgen.setViewportBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "R\u00F6ntgen",
				TitledBorder.CENTER, TitledBorder.TOP, null, null));

		sPCTMRT = new JScrollPane();
		sPCTMRT.setViewportBorder(new TitledBorder(null, "CT/MRT", TitledBorder.CENTER, TitledBorder.TOP, null, null));

		tPMedikamente = new JTextPane();
		sPMedikamente.setViewportView(tPMedikamente);

		tPRoentgen = new JTextPane();
		sPRoentgen.setViewportView(tPRoentgen);

		tPCTMRT = new JTextPane();
		sPCTMRT.setViewportView(tPCTMRT);
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addComponent(panel_5, GroupLayout.PREFERRED_SIZE, 523, Short.MAX_VALUE)
					.addGap(1)
					.addComponent(panel_6, GroupLayout.PREFERRED_SIZE, 471, Short.MAX_VALUE)
					.addGap(1))
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addGap(1)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_6, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
						.addComponent(panel_5, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
					.addGap(1))
		);
		GroupLayout gl_panel_6 = new GroupLayout(panel_6);
		gl_panel_6.setHorizontalGroup(
			gl_panel_6.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_6.createSequentialGroup()
					.addGap(1)
					.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_6.createSequentialGroup()
							.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
								.addComponent(sPMedikamente, GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE)
								.addComponent(sPRoentgen, GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE))
							.addGap(1))
						.addGroup(gl_panel_6.createSequentialGroup()
							.addComponent(sPCTMRT, GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
							.addGap(1))))
		);
		gl_panel_6.setVerticalGroup(
			gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_6.createSequentialGroup()
					.addGap(1)
					.addComponent(sPMedikamente, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
					.addGap(1)
					.addComponent(sPRoentgen, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
					.addGap(1)
					.addComponent(sPCTMRT, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))
		);
		panel_6.setLayout(gl_panel_6);
		GroupLayout gl_panel_5 = new GroupLayout(panel_5);
		gl_panel_5.setHorizontalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addGap(7)
					.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
						.addComponent(sPEndokrinium, GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
						.addComponent(sPSchilddruese, GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
						.addComponent(SPBauchspeicheldruese, GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
						.addComponent(sPZNS, GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
						.addGroup(gl_panel_5.createSequentialGroup()
							.addComponent(lblEpilemtiformeAnflle)
							.addGap(1)
							.addComponent(cBEpiAnfaelle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(1))
		);
		gl_panel_5.setVerticalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addGap(1)
					.addComponent(sPEndokrinium, GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
					.addGap(1)
					.addComponent(sPSchilddruese, GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
					.addGap(1)
					.addComponent(SPBauchspeicheldruese, GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
					.addGap(1)
					.addComponent(sPZNS, GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
					.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_5.createSequentialGroup()
							.addGap(4)
							.addComponent(lblEpilemtiformeAnflle))
						.addGroup(gl_panel_5.createSequentialGroup()
							.addGap(1)
							.addComponent(cBEpiAnfaelle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(1))
		);
		panel_5.setLayout(gl_panel_5);
		panel_4.setLayout(gl_panel_4);

		JPanel panel_8 = new JPanel();
		tabber.addTab("spezielle symptombezogene Anamnese", null, panel_8, null);

		JPanel panel_9 = new JPanel();

		sPHauptproblem = new JScrollPane();
		sPHauptproblem.setViewportBorder(new TitledBorder(null, "Hauptproblem", TitledBorder.CENTER, TitledBorder.TOP,
				null, null));

		sPSchilderung = new JScrollPane();
		sPSchilderung.setViewportBorder(new TitledBorder(null, "Schilderung des Patientenbesitzers",
				TitledBorder.CENTER, TitledBorder.TOP, null, null));

		sPUnternommen = new JScrollPane();
		sPUnternommen.setViewportBorder(new TitledBorder(null, "Was wurde unternommen?:", TitledBorder.CENTER,
				TitledBorder.TOP, null, null));

		JLabel label_9 = new JLabel("Schmerzempfindlichkeit:");

		cBSchmerzempfindlichkeit = new JComboBox<EnumObject>();
		cBSchmerzempfindlichkeit.setModel(new DefaultComboBoxModel<EnumObject>(new EnumObject[] {
				new EnumObject("Bitte Auswählen", EnumType.UNKNOWN), new EnumObject("Ja", EnumType.A),
				new EnumObject("Nein", EnumType.B) }));

		JLabel lblDenkenSieIhr = new JLabel("Denken sie ihr Tier hat Schmerzen?:");

		cBDenkenSchmerzen = new JComboBox<EnumObject>();
		cBDenkenSchmerzen.setModel(new DefaultComboBoxModel<EnumObject>(new EnumObject[] {
				new EnumObject("Bitte Äuswählen", EnumType.UNKNOWN), new EnumObject("Ja,immer", EnumType.A),
				new EnumObject("Gelegentlich", EnumType.B), new EnumObject("Nein", EnumType.C) }));

		tPHauptproblem = new JTextPane();
		sPHauptproblem.setViewportView(tPHauptproblem);

		tPSchilderung = new JTextPane();
		sPSchilderung.setViewportView(tPSchilderung);

		tPUnternommen = new JTextPane();
		sPUnternommen.setViewportView(tPUnternommen);

		JPanel panel_10 = new JPanel();

		sPSchmerzmittel = new JScrollPane();
		sPSchmerzmittel.setViewportBorder(new TitledBorder(null, "Wie reagiert das Tier auf Schmerzmittel?",
				TitledBorder.CENTER, TitledBorder.TOP, null, null));

		tPSchmerzmittel = new JTextPane();
		sPSchmerzmittel.setViewportView(tPSchmerzmittel);
		GroupLayout gl_panel_8 = new GroupLayout(panel_8);
		gl_panel_8.setHorizontalGroup(
			gl_panel_8.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_8.createSequentialGroup()
					.addComponent(panel_9, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(1)
					.addComponent(panel_10, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(1))
		);
		gl_panel_8.setVerticalGroup(
			gl_panel_8.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_9, GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
				.addComponent(panel_10, GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
		);
		GroupLayout gl_panel_9 = new GroupLayout(panel_9);
		gl_panel_9.setHorizontalGroup(
			gl_panel_9.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_9.createSequentialGroup()
					.addGroup(gl_panel_9.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_9.createSequentialGroup()
							.addGap(7)
							.addGroup(gl_panel_9.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDenkenSieIhr)
								.addComponent(label_9))
							.addGap(1)
							.addGroup(gl_panel_9.createParallelGroup(Alignment.LEADING)
								.addComponent(cBSchmerzempfindlichkeit, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(cBDenkenSchmerzen, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel_9.createSequentialGroup()
							.addGap(1)
							.addComponent(sPUnternommen, GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE))
						.addGroup(gl_panel_9.createSequentialGroup()
							.addGap(1)
							.addGroup(gl_panel_9.createParallelGroup(Alignment.TRAILING)
								.addComponent(sPHauptproblem, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
								.addComponent(sPSchilderung, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE))))
					.addGap(1))
		);
		gl_panel_9.setVerticalGroup(
			gl_panel_9.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_9.createSequentialGroup()
					.addGap(1)
					.addComponent(sPHauptproblem, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
					.addGap(1)
					.addComponent(sPSchilderung, GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
					.addGap(1)
					.addComponent(sPUnternommen, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
					.addGap(7)
					.addComponent(label_9)
					.addGap(1)
					.addGroup(gl_panel_9.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_9.createSequentialGroup()
							.addGap(3)
							.addComponent(lblDenkenSieIhr))
						.addComponent(cBDenkenSchmerzen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(1))
				.addGroup(gl_panel_9.createSequentialGroup()
					.addContainerGap(367, Short.MAX_VALUE)
					.addComponent(cBSchmerzempfindlichkeit, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(24))
		);
		panel_9.setLayout(gl_panel_9);

		sPVerusachenSchmerzen = new JScrollPane();
		sPVerusachenSchmerzen.setViewportBorder(new TitledBorder(null, "Welche Bewegungen verursachen den Schmerz?",
				TitledBorder.CENTER, TitledBorder.TOP, null, null));

		tPVerusachenSchmerzen = new JTextPane();
		sPVerusachenSchmerzen.setViewportView(tPVerusachenSchmerzen);
		GroupLayout gl_panel_10 = new GroupLayout(panel_10);
		gl_panel_10.setHorizontalGroup(
			gl_panel_10.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_10.createSequentialGroup()
					.addGap(1)
					.addGroup(gl_panel_10.createParallelGroup(Alignment.LEADING)
						.addComponent(sPVerusachenSchmerzen, GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
						.addComponent(sPSchmerzmittel, GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE))
					.addGap(1))
		);
		gl_panel_10.setVerticalGroup(
			gl_panel_10.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_10.createSequentialGroup()
					.addGap(1)
					.addComponent(sPSchmerzmittel, GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
					.addGap(1)
					.addComponent(sPVerusachenSchmerzen, GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
					.addGap(1))
		);
		panel_10.setLayout(gl_panel_10);
		panel_8.setLayout(gl_panel_8);

		JPanel panel_14 = new JPanel();
		tabber.addTab("Funktionsanamnese", null, panel_14, null);

		JPanel panel_11 = new JPanel();

		JLabel lblIstDerPatient = new JLabel("Ist der Patient in der Lage, den betreffenden Körperteil zu benutzen?");

		cBKöperteilBewegen = new JComboBox<EnumObject>();
		cBKöperteilBewegen.setModel(new DefaultComboBoxModel<EnumObject>(new EnumObject[] {
				new EnumObject("Bitte Auswählen", EnumType.UNKNOWN), new EnumObject("Ja", EnumType.A),
				new EnumObject("Nein", EnumType.B) }));

		JLabel lblMglicheGehstreckeIn = new JLabel("Mögliche Gehstrecke in Killometer:");

		spGehstrecke = new JSpinner(); // Gehstrecke in Km

		JLabel label_13 = new JLabel("Mögliche Gehzeit:");

		spGehzeit = new JSpinner(); // Gehzeit im Format HH:MM (Stunden:Minuten)
		SpinnerDateModel time_model = new SpinnerDateModel();
		time_model.setCalendarField(Calendar.MINUTE);
		spGehzeit.setModel(time_model);
		spGehzeit.setEditor(new JSpinner.DateEditor(spGehzeit, "HH:mm"));

		JLabel label_14 = new JLabel("Witterungsabhängikeit");

		cBWitterung = new JComboBox<EnumObject>();
		cBWitterung.setModel(new DefaultComboBoxModel<EnumObject>(new EnumObject[] {
				new EnumObject("Bitte Auswählen", EnumType.UNKNOWN), new EnumObject("Ja", EnumType.A),
				new EnumObject("Nein", EnumType.B) }));

		JLabel label_15 = new JLabel("Zykluskorrelation");

		cBZyklus = new JComboBox<EnumObject>();
		cBZyklus.setModel(new DefaultComboBoxModel<EnumObject>(new EnumObject[] {
				new EnumObject("Bitte Auswählen", EnumType.UNKNOWN), new EnumObject("Ja", EnumType.A),
				new EnumObject("Nein", EnumType.B) }));

		JLabel label_16 = new JLabel("Auslauf:");

		spAuslauf = new JSpinner();
		spAuslauf.setModel(new SpinnerNumberModel(new Double(0), null, null, new Double(1)));

		JLabel lblWieVielZeit = new JLabel("Wie viel Zeit können Sie für ihr Tier aufbringen?");

		spavailTimeCons = new JSpinner();
		SpinnerDateModel time_model2 = new SpinnerDateModel();
		time_model2.setCalendarField(Calendar.MINUTE);
		spavailTimeCons.setModel(time_model2);
		spavailTimeCons.setEditor(new JSpinner.DateEditor(spavailTimeCons, "HH:mm"));

		JPanel panel_12 = new JPanel();
		panel_12.setLayout(new BorderLayout(0, 0));

		sP_Bemerkungen = new JScrollPane();
		sP_Bemerkungen.setViewportBorder(new TitledBorder(null, "Sonstige Bemerkungen", TitledBorder.CENTER,
				TitledBorder.TOP, null, null));
		panel_12.add(sP_Bemerkungen, BorderLayout.CENTER);

		tPBemerkungen = new JTextPane();
		sP_Bemerkungen.setViewportView(tPBemerkungen);

		sPFunktionenMotorik = new JScrollPane();
		sPFunktionenMotorik.setViewportBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Welche Funktionen motorischer und anderer Art sind beeinflusst?", TitledBorder.CENTER, TitledBorder.TOP, null, null));

		tPFunktionenMotorik = new JTextPane();
		sPFunktionenMotorik.setViewportView(tPFunktionenMotorik);
		GroupLayout gl_panel_14 = new GroupLayout(panel_14);
		gl_panel_14.setHorizontalGroup(
			gl_panel_14.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_14.createSequentialGroup()
					.addGroup(gl_panel_14.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_11, GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
						.addComponent(sPFunktionenMotorik, GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE))
					.addGap(1)
					.addComponent(panel_12, GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
					.addGap(1))
		);
		gl_panel_14.setVerticalGroup(
			gl_panel_14.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_14.createSequentialGroup()
					.addGap(1)
					.addComponent(sPFunktionenMotorik, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
					.addGap(1)
					.addComponent(panel_11, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(57))
				.addComponent(panel_12, GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
		);
		panel_11.setLayout(new MigLayout("", "[333px][109px]", "[20px][20px][20px][20px][20px][20px][20px]"));
		panel_11.add(cBKöperteilBewegen, "cell 1 0,alignx left,aligny top");
		panel_11.add(lblMglicheGehstreckeIn, "cell 0 1,alignx left,aligny center");
		panel_11.add(spGehstrecke, "cell 1 1,growx,aligny top");
		panel_11.add(label_13, "cell 0 2,alignx left,aligny center");
		panel_11.add(spGehzeit, "cell 1 2,growx,aligny top");
		panel_11.add(label_14, "cell 0 3,alignx left,aligny center");
		panel_11.add(cBWitterung, "cell 1 3,growx,aligny top");
		panel_11.add(label_15, "cell 0 4,alignx left,aligny center");
		panel_11.add(cBZyklus, "cell 1 4,growx,aligny top");
		panel_11.add(label_16, "cell 0 5,alignx left,aligny center");
		panel_11.add(spAuslauf, "cell 1 5,growx,aligny top");
		panel_11.add(lblIstDerPatient, "cell 0 0,alignx left,aligny center");
		panel_11.add(lblWieVielZeit, "cell 0 6,alignx left,aligny center");
		panel_11.add(spavailTimeCons, "cell 1 6,growx,aligny top");
		panel_14.setLayout(gl_panel_14);

		tFName.setEditable(false);
		tFRufname.setEditable(false);
		
		spGehstrecke.setModel(new SpinnerNumberModel(new Double(0), null, null, new Double(1)));
		
		spinBirthdate_1.setValue(GUIFunctions.getDefaultDate());
		spGehzeit.setValue(GUIFunctions.getDefaultDate());
		spavailTimeCons.setValue(GUIFunctions.getDefaultDate());
		
		getContentPane().setLayout(groupLayout);

		btnEdit = new JButton("Bearbeiten");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (anamnesisID != -1) {
					editable = true;
					setEditable();
				}
			}
		});
		panel_3.add(btnEdit, "cell 2 0");
		
		splitPane_2.setResizeWeight(0.5);
		splitPane.setResizeWeight(0.5);
		
		tpEndokrinium.setFont(GUIManager.getCommentFont());
		tPSchilddruese.setFont(GUIManager.getCommentFont());
		tPBauchspeicheldruese.setFont(GUIManager.getCommentFont());
		tPZNS.setFont(GUIManager.getCommentFont());
		tPMedikamente.setFont(GUIManager.getCommentFont());
		tPRoentgen.setFont(GUIManager.getCommentFont());
		tPCTMRT.setFont(GUIManager.getCommentFont());
		spAuslauf.setFont(GUIManager.getCommentFont());
		tPHauptproblem.setFont(GUIManager.getCommentFont());
		tPAenderungenFamilie.setFont(GUIManager.getCommentFont());
		tPVerletzungen.setFont(GUIManager.getCommentFont());
		tPNarben.setFont(GUIManager.getCommentFont());
		tPVerhaltensaufaeligkeiten.setFont(GUIManager.getCommentFont());
		tPAuslandsaufenthalte.setFont(GUIManager.getCommentFont());
		tPInfektionen.setFont(GUIManager.getCommentFont());
		tPImpfungen.setFont(GUIManager.getCommentFont());
		tPHerzKreislauf.setFont(GUIManager.getCommentFont());
		tPAtmung.setFont(GUIManager.getCommentFont());
		tPVerdauung.setFont(GUIManager.getCommentFont());
		tPUnternommen.setFont(GUIManager.getCommentFont());
		tPVerusachenSchmerzen.setFont(GUIManager.getCommentFont());
		tPSchmerzmittel.setFont(GUIManager.getCommentFont());
		tPFunktionenMotorik.setFont(GUIManager.getCommentFont());
		tPBemerkungen.setFont(GUIManager.getCommentFont());
		tFHalltung.setFont(GUIManager.getCommentFont());
		tPVerwendungszweck.setFont(GUIManager.getCommentFont());
		tPTierStammtVon.setFont(GUIManager.getCommentFont());
		spavailTimeCons.setFont(GUIManager.getCommentFont());
		spavailTimeCons.setFont(GUIManager.getCommentFont());
		spGehstrecke.setFont(GUIManager.getCommentFont());
		spGehzeit.setFont(GUIManager.getCommentFont());
		tPSchilderung.setFont(GUIManager.getCommentFont());
		spinBirthdate_1.setFont(GUIManager.getCommentFont());
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{tPVerwendungszweck, spinBirthdate_1, tFHalltung, tPTierStammtVon, cBEpiAnfaelle, cBDenkenSchmerzen, cBKöperteilBewegen, spGehstrecke, spavailTimeCons, spGehzeit, cBWitterung, cBZyklus}));
		
		setEditable();
		loadData();
		this.setVisible(true);
	}

	private void initialize(String strName) {
		frame = new JFrame();
		setTitle("Anamnese von " + strName);
		setBounds(100, 100, 1018, 507);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void loadData() {
		if (anamnesisID != -1) {
			setGlassPaneVisible(true);
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					Thread t = new Thread(new Runnable() {
						public void run() {
							try {
								ResultSet rs = Database.getAnamnesis(anamnesisID);
								if (rs.next()) {
									tPVerwendungszweck.setText(rs.getString(1));
									int n = 2;
									tFHalltung.setText(rs.getString(n));
									n++;
									spinBirthdate_1.setValue(rs.getDate(n));
									n++;
									tPTierStammtVon.setText(rs.getString(n));
									n++;
									tPAenderungenFamilie.setText(rs.getString(n));
									n++;
									tPAuslandsaufenthalte.setText(rs.getString(n));
									n++;
									tPVerhaltensaufaeligkeiten.setText(rs.getString(n));
									n++;
									tPVerletzungen.setText(rs.getString(n));
									n++;
									tPNarben.setText(rs.getString(n));
									n++;
									tPInfektionen.setText(rs.getString(n));
									n++;
									tPImpfungen.setText(rs.getString(n));
									n++;
									tPAtmung.setText(rs.getString(n));
									n++;
									tPVerdauung.setText(rs.getString(n));
									n++;
									tpEndokrinium.setText(rs.getString(n));
									n++;
									tPSchilddruese.setText(rs.getString(n));
									n++;
									tPBauchspeicheldruese.setText(rs.getString(n));
									n++;
									tPZNS.setText(rs.getString(n));
									n++;
									cBEpiAnfaelle.setSelectedIndex(rs.getInt(n)-1);
									n++;
									tPMedikamente.setText(rs.getString(n));
									n++;
									tPRoentgen.setText(rs.getString(n));
									n++;
									tPCTMRT.setText(rs.getString(n));
									n++;
									tPHauptproblem.setText(rs.getString(n));
									n++;
									tPSchilderung.setText(rs.getString(n));
									n++;
									tPUnternommen.setText(rs.getString(n));
									n++;
									cBSchmerzempfindlichkeit.setSelectedIndex(rs.getInt(n));
									n++;
									cBDenkenSchmerzen.setSelectedIndex(rs.getInt(n));
									n++;
									tPSchmerzmittel.setText(rs.getString(n));
									n++;
									tPVerusachenSchmerzen.setText(rs.getString(n));
									n++;
									tPFunktionenMotorik.setText(rs.getString(n));
									n++;
									cBKöperteilBewegen.setSelectedIndex(rs.getInt(n));
									n++;
									spGehstrecke.setValue(rs.getDouble(n));
									n++;
									spGehzeit.setValue(rs.getTime(n));
									n++;
									cBWitterung.setSelectedIndex(rs.getInt(n));
									n++;
									cBZyklus.setSelectedIndex(rs.getInt(n));
									n++;
									spAuslauf.setValue(rs.getDouble(n));
									n++;
									spavailTimeCons.setValue(rs.getTime(n));
									n++;
									tPBemerkungen.setText(rs.getString(n));
									n++;
									tPHerzKreislauf.setText(rs.getString(n));
									n++;
								}
								rs.close();
							} catch (SQLException e) {
								GUIManager.showDBErrorDialog(getFrame(), Database.DBExceptionConverter(e, true));
							}
						setGlassPaneVisible(false);
					}
				});
				t.start();
			}
		});
		}
	}

	/**
	 * Insert & edit method caller
	 * 
	 * @author "Aron Heinecke"
	 * @param insert
	 */
	private void saveData(final boolean insert) {
		if (allSet()) {
			setGlassPaneVisible(true);
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					Thread t = new Thread(new Runnable() {
						public void run() {
							try {
								AnamnesisBP anamnesis = new AnamnesisBP.Builder(animalID).purpose(tPVerwendungszweck.getText())
										.keeping(tFHalltung.getText())
										.anamnesisID(anamnesisID)
										.possesionsince(new java.sql.Date(((Date) spinBirthdate_1.getValue()).getTime()))
										.origin(tPTierStammtVon.getText()).familystrchanges(tPAenderungenFamilie.getText())
										.abroadstays(tPAuslandsaufenthalte.getText())
										.attitudeconspicuity(tPVerhaltensaufaeligkeiten.getText()).injurys(tPVerletzungen.getText())
										.scars(tPNarben.getText()).infectiousDisease(tPInfektionen.getText())
										.regularVaccinations(tPImpfungen.getText()).breathing(tPAtmung.getText())
										.digestiveTract(tPVerdauung.getText()).endocrineSystem(tpEndokrinium.getText())
										.hyperthyroidism(tPSchilddruese.getText()).pancreas(tPBauchspeicheldruese.getText())
										.ZNS(tPZNS.getText()).epileptiformAttacks(getEnumType(cBEpiAnfaelle.getSelectedItem()))
										.xray(tPRoentgen.getText()).medication(tPMedikamente.getText()).CT_MRT(tPCTMRT.getText())
										.mainproblem(tPHauptproblem.getText()).descrPatientOwner(tPSchilderung.getText())
										.wasUndertaken(tPUnternommen.getText())
										.painSensitivity(getEnumType(cBSchmerzempfindlichkeit.getSelectedItem()))
										.patientHasPain(getEnumType(cBDenkenSchmerzen.getSelectedItem()))
										.painkillerReaction(tPSchmerzmittel.getText())
										.motionCausingPain(tPVerusachenSchmerzen.getText())
										.motorInterference(tPFunktionenMotorik.getText())
										.bodyPartUsagePossible(getEnumType(cBKöperteilBewegen.getSelectedItem()))
										.possibleWalkDistance((double) spGehstrecke.getValue())
										.possibleWalkDuration(new java.sql.Time(((Date) spGehzeit.getValue()).getTime()))
										.weatherDependent(getEnumType(cBWitterung.getSelectedItem()))
										.cycleCorrelation(getEnumType(cBZyklus.getSelectedItem()))
										.outlet((double) spAuslauf.getValue())
										.availableTimeCons(new java.sql.Time(((Date) spavailTimeCons.getValue())
										.getTime()))
										.comment(tPBemerkungen.getText()).circulation(tPHerzKreislauf.getText())
										.build();
								if (insert)
									anamnesisID = Database.insertAnamnesis(anamnesis);
								else
									Database.updateAnamnesis(anamnesis);
								editable = false;
								setEditable();
							} catch (SQLException e) {
								GUIManager.showDBErrorDialog(getFrame(), Database.DBExceptionConverter(e, true));
							}
						setGlassPaneVisible(false);
					}
				});
				t.start();
			}
			});
		} else {
			GUIManager.showFieldErrorDialog(this);
		}
	}

	@Override
	public void dispose() {
		if (editable) {
			if (GUIFunctions.showIgnoreChangesDialog(this) == 1)
				return;
		}
		((ActionMap) UIManager.getLookAndFeelDefaults().get("InternalFrame.actionMap")).remove("showSystemMenu");
		super.dispose();
		GUIManager.dropJID(this);
	}

	private int getEnumType(Object obj) {
		return ((EnumObject) obj).getType().getType();
	}


	private boolean allSet() {
		if(tFHalltung.getText().length() > 50)
			return false;
		if(tPVerwendungszweck.getText().length() > 25)
			return false;
		if(tPTierStammtVon.getText().length() > 250)
			return false;
		if (invalidDouble(spGehstrecke.getValue().toString()))
			return false;
		if (invalidDouble(spAuslauf.getValue().toString()))
			return false;
		return true;
	}

}