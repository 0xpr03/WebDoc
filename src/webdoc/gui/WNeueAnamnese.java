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
import java.util.Calendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DateEditor;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerDateModel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import webdoc.gui.utils.EnumObject;
import webdoc.gui.utils.EnumObject.EnumType;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class WNeueAnamnese extends JInternalFrame {

	private JFrame frame;
	private boolean editable;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JSpinner spinBirthdate_1;
	private DateEditor dateEditor;
	private JTextPane tpEndokrinium;
	private JTextPane tPSchilddruese;
	private JTextPane tPBauchspeicheldruese;
	private JTextPane tPZNS;
	private JTextPane textPane_17;
	private JTextPane textPane_16;
	private JTextPane textPane_15;
	private JEditorPane ePHauptproblem;
	private JEditorPane ePSchilderung;
	private JLabel label_1;
	private JLabel label_2;
	private JTextField textField;
	private JSeparator separator;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_5;
	private JScrollPane scrollPane_5;
	private JTextPane tPAenderungenFamilie;
	private JScrollPane scrollPane_3;
	private JTextPane textPane_3;
	private JScrollPane scrollPane_4;
	private JTextPane textPane_2;
	private JScrollPane scrollPane_2;
	private JTextPane textPane_4;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane_8;
	private JScrollPane scrollPane_6;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_7;
	private JScrollPane scrollPane_9;
	private JScrollPane scrollPane_10;
	private JScrollPane scrollPane_11;
	private JScrollPane scrollPane_12;
	private JScrollPane scrollPane_13;
	private JScrollPane scrollPane_14;
	private JScrollPane scrollPane_15;
	private JScrollPane scrollPane_16;
	private JScrollPane scrollPane_18;
	private JScrollPane scrollPane_19;
	private JScrollPane scrollPane_20;
	private JComboBox<EnumObject> cBSchmerzempfindlichkeit;
	private JComboBox<EnumObject> cBDenkenSchmerzen;
	private JScrollPane sPSchmerzmittel;
	private JScrollPane sPVerusachenSchmerzen;
	private JScrollPane sPFunktionenMotorik;
	private JComboBox<EnumObject> cBKöperteilBewegen;
	private JSpinner spGehstrecke;
	private JSpinner spGehzeit;
	private JComboBox<EnumObject> cBWitterung;
	private JComboBox<EnumObject> comboBoxZyklus;
	private JComboBox<EnumObject> comboBoxAuslauf;
	private JComboBox<EnumObject> comboBox_6;
	private JScrollPane sP_Bemerkungen;
	private long PATIENT_ID;
	private long anamnesis_id;
	private JButton btnEdit;
	private JButton btnOk;
	private JButton btnCancel;

	/**
	 * Launch the application.
	 */
	private void setEditable(){
		  textField_6.setEditable(editable);
		  textField_7.setEditable(editable);
		  textField_8.setEditable(editable);
		  spinBirthdate_1.setEnabled(editable);
		  tpEndokrinium.setEditable(editable);
		  tPSchilddruese.setEditable(editable);
		  tPBauchspeicheldruese.setEditable(editable);
		  tPZNS.setEditable(editable);
		  textPane_17.setEditable(editable);
		  textPane_16.setEditable(editable);
		  textPane_15.setEditable(editable);
		  ePHauptproblem.setEditable(editable);
		  ePSchilderung.setEditable(editable);
		  textField.setEditable(editable);
		  scrollPane_5.setEnabled(editable);
		  tPAenderungenFamilie.setEditable(editable);
		  scrollPane_3.setEnabled(editable);
		  textPane_3.setEditable(editable);
		  scrollPane_4.setEnabled(editable);
		  textPane_2.setEditable(editable);
		  scrollPane_2.setEnabled(editable);
		  textPane_4.setEditable(editable);
		  scrollPane_1.setEnabled(editable);
		  scrollPane_8.setEnabled(editable);
		  scrollPane_6.setEnabled(editable);;
		  scrollPane.setEnabled(editable);
		  scrollPane_7.setEnabled(editable);;
		  scrollPane_9.setEnabled(editable);
		  scrollPane_10.setEnabled(editable);;
		  scrollPane_11.setEnabled(editable);;
		  scrollPane_12.setEnabled(editable);
		  scrollPane_13.setEnabled(editable);
		  scrollPane_14.setEnabled(editable);
		  scrollPane_15.setEnabled(editable);
		  scrollPane_16.setEnabled(editable);
		  scrollPane_18.setEnabled(editable);;
		  scrollPane_19.setEnabled(editable);
		  scrollPane_20.setEnabled(editable);
		  cBSchmerzempfindlichkeit.setEditable(editable);
		  cBDenkenSchmerzen.setEditable(editable);
		  sPVerusachenSchmerzen.setEnabled(editable);
		  sPFunktionenMotorik.setEnabled(editable);
		  cBKöperteilBewegen.setEditable(editable);;;
		  sP_Bemerkungen.setEnabled(editable);
		  reloadBtn(editable);
	}
	private void reloadBtn(boolean editable) {
		btnOk.setText(editable ? "Speichern" : "Schließen");
		btnEdit.setVisible(anamnesis_id != -1);
		btnEdit.setEnabled(!editable);
	}
	/**
	 * Create the application.
	 */
	public WNeueAnamnese(final boolean is_editable, final long animal_id, final long anamnesis_id, final String patient_name) {
		this.editable = is_editable;
		initialize(patient_name);
		this.PATIENT_ID = animal_id;
		this.anamnesis_id = anamnesis_id;
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
				if(anamnesis_id == -1){
					//TODO: save
				}else if(editable){
					//TODO: change
				}else{
					dispose();
				}
			}
		});
		panel_3.add(btnOk, "cell 0 0");
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(anamnesis_id == -1){
					if (GUIFunctions.showIgnoreChangesDialog(getFrame()) == 0) {
						dispose();
					}
				}else if(editable){
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
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(18)
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(771, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(1)
					.addComponent(tabber, GroupLayout.DEFAULT_SIZE, 1034, Short.MAX_VALUE)
					.addGap(1))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(1)
					.addComponent(tabber, GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
					.addGap(1)
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		
		JPanel pContent = new JPanel();
		tabber.addTab("Allgemeine Anamnese", null, pContent, null);
		
		JPanel panel_2 = new JPanel();
		
		scrollPane_5 = new JScrollPane();
		scrollPane_5.setViewportBorder(new TitledBorder(UIManager.getBorder("CheckBoxMenuItem.border"), "\u00C4nderungen im Familiengef\u00FCge", TitledBorder.CENTER, TitledBorder.ABOVE_TOP, null, null));
		
		label_1 = new JLabel("Name:");
		
		label_2 = new JLabel("Rufname:");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		
		separator = new JSeparator();
		
		label_3 = new JLabel("Verwendungszweck:");
		
		label_4 = new JLabel("Im Besitz seit:");
		
		label_5 = new JLabel("Tier stammt von:");
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		spinBirthdate_1 = new JSpinner(); //Datumsmodell
		SpinnerDateModel model = new SpinnerDateModel();
		model.setCalendarField(Calendar.MINUTE);
		spinBirthdate_1.setEnabled(editable);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		
		
		scrollPane_5.setViewportView(tPAenderungenFamilie);
		
		JPanel panel_1 = new JPanel();
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setViewportBorder(new TitledBorder(UIManager.getBorder("CheckBoxMenuItem.border"), "Auslandsaufenthalte", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setViewportBorder(new TitledBorder(null, "Verhaltensauf\u00E4lligkeiten", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		
		scrollPane_3 = new JScrollPane();
		scrollPane_3.setViewportBorder(new TitledBorder(UIManager.getBorder("CheckBoxMenuItem.border"), "Verletzungen", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		
		scrollPane_4 = new JScrollPane();
		scrollPane_4.setViewportBorder(new TitledBorder(UIManager.getBorder("CheckBoxMenuItem.border"), "Narben", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		
		textPane_4 = new JTextPane();
		scrollPane_2.setViewportView(textPane_4);
		
		textPane_3 = new JTextPane();
		scrollPane_3.setViewportView(textPane_3);
		
		textPane_2 = new JTextPane();
		scrollPane_4.setViewportView(textPane_2);
		
		JTextPane textPane_1 = new JTextPane();
		scrollPane_1.setViewportView(textPane_1);
		
		JSplitPane splitPane = new JSplitPane();
		JSplitPane splitPane_2 = new JSplitPane();
		splitPane_2.setLeftComponent(splitPane);
		
		splitPane.setLeftComponent(panel_2);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addComponent(label_1)
					.addGap(18)
					.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_2.createSequentialGroup()
					.addComponent(label_2)
					.addGap(2)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE))
				.addComponent(separator, GroupLayout.PREFERRED_SIZE, 258, Short.MAX_VALUE)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addComponent(label_3)
					.addGap(4)
					.addComponent(textField_8, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_2.createSequentialGroup()
					.addComponent(label_4)
					.addGap(35)
					.addComponent(spinBirthdate_1, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
					.addGap(79))
				.addGroup(gl_panel_2.createSequentialGroup()
					.addComponent(label_5)
					.addGap(21)
					.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(10)
					.addComponent(scrollPane_5, GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
					.addGap(10))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(11)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(3)
							.addComponent(label_1))
						.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(3)
							.addComponent(label_2))
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 4, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(3)
							.addComponent(label_3))
						.addComponent(textField_8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(10)
							.addComponent(label_4))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(6)
							.addComponent(spinBirthdate_1, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(3)
							.addComponent(label_5))
						.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(26)
					.addComponent(scrollPane_5, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
					.addGap(18))
		);
		panel_2.setLayout(gl_panel_2);
		splitPane.setRightComponent(panel_1);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
					.addGap(2))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(14)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_4, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
						.addComponent(scrollPane_3, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 23, Short.MAX_VALUE)
							.addGap(2))))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
					.addGap(17)
					.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_3, GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_4, GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
					.addGap(29))
		);
		panel_1.setLayout(gl_panel_1);
		
		JPanel panel = new JPanel();
		splitPane_2.setRightComponent(panel);
		
		scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new TitledBorder(null, "Infektionserkrankungen", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		
		scrollPane_6 = new JScrollPane();
		scrollPane_6.setViewportBorder(new TitledBorder(null, "Regelm\u00E4\u00DFige Impfungen:", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		
		scrollPane_7 = new JScrollPane();
		scrollPane_7.setViewportBorder(new TitledBorder(null, "Herz/Kreislauf", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		
		scrollPane_8 = new JScrollPane();
		scrollPane_8.setViewportBorder(new TitledBorder(null, "Atmung", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		
		JTextPane tPHerzKreislauf = new JTextPane();
		scrollPane_7.setViewportView(tPHerzKreislauf);
		
		JTextPane tPImpfungen = new JTextPane();
		scrollPane_6.setViewportView(tPImpfungen);
		
		JTextPane tPInfektionen = new JTextPane();
		scrollPane.setViewportView(tPInfektionen);
		GroupLayout gl_pContent = new GroupLayout(pContent);
		gl_pContent.setHorizontalGroup(
			gl_pContent.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pContent.createSequentialGroup()
					.addGap(1)
					.addComponent(splitPane_2, GroupLayout.DEFAULT_SIZE, 1316, Short.MAX_VALUE)
					.addGap(1))
		);
		gl_pContent.setVerticalGroup(
			gl_pContent.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pContent.createSequentialGroup()
					.addGap(1)
					.addComponent(splitPane_2))
		);
		
		JTextPane tPAtmung = new JTextPane();
		scrollPane_8.setViewportView(tPAtmung);
		
		scrollPane_9 = new JScrollPane();
		scrollPane_9.setViewportBorder(new TitledBorder(null, "Verdauungstrakt", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		
		JTextPane tPVerdauung = new JTextPane();
		scrollPane_9.setViewportView(tPVerdauung);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(26)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_9, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addComponent(scrollPane_8, GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)
							.addGap(1))
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addComponent(scrollPane_6, GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)
							.addGap(1))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addComponent(scrollPane_7, GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)
							.addGap(1)))
					.addGap(7))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(7)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
					.addGap(4)
					.addComponent(scrollPane_6, GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_7, GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_8, GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_9, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
					.addGap(7))
		);
		panel.setLayout(gl_panel);
		pContent.setLayout(gl_pContent);
		
		JPanel panel_4 = new JPanel();
		tabber.addTab("Allgemeine Anamnese 2", null, panel_4, null);
		
		JPanel panel_5 = new JPanel();
		
		scrollPane_10 = new JScrollPane();
		scrollPane_10.setViewportBorder(new TitledBorder(null, "Endokrinium:", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		
		scrollPane_11 = new JScrollPane();
		scrollPane_11.setViewportBorder(new TitledBorder(null, "Schilddr\u00FCse", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		
		scrollPane_12 = new JScrollPane();
		scrollPane_12.setViewportBorder(new TitledBorder(null, "Bauchspeicheldr\u00FCse:", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		
		scrollPane_13 = new JScrollPane();
		scrollPane_13.setViewportBorder(new TitledBorder(null, "ZNS:", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		
		JLabel lblEpilemtiformeAnflle = new JLabel("Epilemtiforme Anfälle:");
		
		tpEndokrinium = new JTextPane();
		scrollPane_10.setViewportView(tpEndokrinium);
		
		tPSchilddruese = new JTextPane();
		scrollPane_11.setViewportView(tPSchilddruese);
		
		tPBauchspeicheldruese = new JTextPane();
		scrollPane_12.setViewportView(tPBauchspeicheldruese);
		
		tPZNS = new JTextPane();
		scrollPane_13.setViewportView(tPZNS);
		
		JComboBox<EnumObject> cBEpiAnfaelle = new JComboBox<EnumObject>();
		cBEpiAnfaelle.setModel(new DefaultComboBoxModel<EnumObject>(new EnumObject[] {new EnumObject("Keine", EnumType.UNKNOWN), new EnumObject("Gleichgewichtsstörungen",EnumType.A), new EnumObject("Vorübergehendes Schwanken",EnumType.B)}));
		
		JPanel panel_6 = new JPanel();
		
		scrollPane_14 = new JScrollPane();
		scrollPane_14.setViewportBorder(new TitledBorder(null, "Medikamente", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		
		scrollPane_15 = new JScrollPane();
		scrollPane_15.setViewportBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "R\u00F6ntgen", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		
		scrollPane_16 = new JScrollPane();
		scrollPane_16.setViewportBorder(new TitledBorder(null, "CT/MRT", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		
		textPane_17 = new JTextPane();
		scrollPane_14.setViewportView(textPane_17);
		
		textPane_16 = new JTextPane();
		scrollPane_15.setViewportView(textPane_16);
		
		textPane_15 = new JTextPane();
		scrollPane_16.setViewportView(textPane_15);
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addComponent(panel_5, GroupLayout.PREFERRED_SIZE, 523, Short.MAX_VALUE)
					.addGap(1)
					.addComponent(panel_6, GroupLayout.PREFERRED_SIZE, 471, GroupLayout.PREFERRED_SIZE)
					.addGap(34))
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_4.createSequentialGroup()
					.addGap(1)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_6, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
						.addComponent(panel_5, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
					.addGap(7))
		);
		GroupLayout gl_panel_6 = new GroupLayout(panel_6);
		gl_panel_6.setHorizontalGroup(
			gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_6.createSequentialGroup()
					.addGap(1)
					.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_14, GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
						.addComponent(scrollPane_15, GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
						.addGroup(gl_panel_6.createSequentialGroup()
							.addComponent(scrollPane_16, GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
							.addGap(1)))
					.addGap(1))
		);
		gl_panel_6.setVerticalGroup(
			gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_6.createSequentialGroup()
					.addGap(1)
					.addComponent(scrollPane_14, GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
					.addGap(1)
					.addComponent(scrollPane_15, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
					.addGap(1)
					.addComponent(scrollPane_16, GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
					.addGap(1))
		);
		panel_6.setLayout(gl_panel_6);
		GroupLayout gl_panel_5 = new GroupLayout(panel_5);
		gl_panel_5.setHorizontalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addGap(7)
					.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_10, GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
						.addComponent(scrollPane_11, GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
						.addComponent(scrollPane_12, GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
						.addComponent(scrollPane_13, GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
						.addGroup(gl_panel_5.createSequentialGroup()
							.addComponent(lblEpilemtiformeAnflle)
							.addGap(8)
							.addComponent(cBEpiAnfaelle, GroupLayout.PREFERRED_SIZE, 397, GroupLayout.PREFERRED_SIZE)))
					.addGap(7))
		);
		gl_panel_5.setVerticalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addGap(7)
					.addComponent(scrollPane_10, GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
					.addGap(4)
					.addComponent(scrollPane_11, GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
					.addGap(4)
					.addComponent(scrollPane_12, GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
					.addGap(4)
					.addComponent(scrollPane_13, GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
					.addGap(4)
					.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_5.createSequentialGroup()
							.addGap(3)
							.addComponent(lblEpilemtiformeAnflle))
						.addComponent(cBEpiAnfaelle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(11))
		);
		panel_5.setLayout(gl_panel_5);
		panel_4.setLayout(gl_panel_4);
		
		JPanel panel_8 = new JPanel();
		tabber.addTab("spezielle symptombezogene Anamnese", null, panel_8, null);
		
		JPanel panel_9 = new JPanel();
		
		scrollPane_18 = new JScrollPane();
		scrollPane_18.setViewportBorder(new TitledBorder(null, "Hauptproblem", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		
		scrollPane_19 = new JScrollPane();
		scrollPane_19.setViewportBorder(new TitledBorder(null, "Schilderung des Patientenbesitzers", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		
		scrollPane_20 = new JScrollPane();
		scrollPane_20.setViewportBorder(new TitledBorder(null, "Was wurde unternommen?:", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		
		JLabel label_9 = new JLabel("Schmerzempfindlichkeit:");
		
		cBSchmerzempfindlichkeit = new JComboBox<EnumObject>();
		cBSchmerzempfindlichkeit.setModel(new DefaultComboBoxModel<EnumObject>(new EnumObject[] {new EnumObject("Bitte Auswählen",EnumType.UNKNOWN), new EnumObject("Ja",EnumType.A), new EnumObject("Nein",EnumType.B)}));
		
		JLabel lblDenkenSieIhr = new JLabel("Denken sie ihr Tier hat Schmerzen?:");
		
		cBDenkenSchmerzen = new JComboBox<EnumObject>();
		cBDenkenSchmerzen.setModel(new DefaultComboBoxModel<EnumObject>(new EnumObject[] {new EnumObject("Bitte Äuswählen",EnumType.UNKNOWN), new EnumObject("Ja,immer",EnumType.A), new EnumObject("Gelegentlich",EnumType.B), new EnumObject("Nein",EnumType.C)}));
		
		ePHauptproblem = new JEditorPane();
		scrollPane_18.setViewportView(ePHauptproblem);
		
		ePSchilderung = new JEditorPane();
		scrollPane_19.setViewportView(ePSchilderung);
		
		JEditorPane ePUnternommen = new JEditorPane();
		scrollPane_20.setViewportView(ePUnternommen);
		
		JPanel panel_10 = new JPanel();
		
		sPSchmerzmittel = new JScrollPane();
		sPSchmerzmittel.setEnabled(false);
		sPSchmerzmittel.setViewportBorder(new TitledBorder(null, "Wie reagiert das Tier auf Schmerzmittel?", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		
		JEditorPane ePSchmerzmittel = new JEditorPane();
		sPSchmerzmittel.setViewportView(ePSchmerzmittel);
		GroupLayout gl_panel_8 = new GroupLayout(panel_8);
		gl_panel_8.setHorizontalGroup(
			gl_panel_8.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_8.createSequentialGroup()
					.addComponent(panel_9, GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_10, GroupLayout.PREFERRED_SIZE, 510, GroupLayout.PREFERRED_SIZE)
					.addGap(34))
		);
		gl_panel_8.setVerticalGroup(
			gl_panel_8.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_9, GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
				.addComponent(panel_10, GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
		);
		GroupLayout gl_panel_9 = new GroupLayout(panel_9);
		gl_panel_9.setHorizontalGroup(
			gl_panel_9.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_9.createSequentialGroup()
					.addGap(7)
					.addGroup(gl_panel_9.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_9.createSequentialGroup()
							.addComponent(label_9)
							.addGap(61)
							.addComponent(cBSchmerzempfindlichkeit, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_9.createSequentialGroup()
							.addComponent(lblDenkenSieIhr)
							.addGap(4)
							.addComponent(cBDenkenSchmerzen, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE)))
					.addGap(7))
				.addGroup(Alignment.TRAILING, gl_panel_9.createSequentialGroup()
					.addGap(1)
					.addComponent(scrollPane_20, GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
					.addGap(1))
				.addGroup(Alignment.TRAILING, gl_panel_9.createSequentialGroup()
					.addGap(1)
					.addGroup(gl_panel_9.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane_18, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
						.addComponent(scrollPane_19, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE))
					.addGap(1))
		);
		gl_panel_9.setVerticalGroup(
			gl_panel_9.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_9.createSequentialGroup()
					.addGap(1)
					.addComponent(scrollPane_18, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
					.addGap(1)
					.addComponent(scrollPane_19, GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
					.addGap(1)
					.addComponent(scrollPane_20, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
					.addGap(1)
					.addGroup(gl_panel_9.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_9.createSequentialGroup()
							.addGap(6)
							.addComponent(label_9))
						.addComponent(cBSchmerzempfindlichkeit, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(4)
					.addGroup(gl_panel_9.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_9.createSequentialGroup()
							.addGap(3)
							.addComponent(lblDenkenSieIhr))
						.addComponent(cBDenkenSchmerzen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(11))
		);
		panel_9.setLayout(gl_panel_9);
		
		sPVerusachenSchmerzen = new JScrollPane();
		sPVerusachenSchmerzen.setViewportBorder(new TitledBorder(null, "Welche Bewegungen verursachen den Schmerz?", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		
		JEditorPane ePVerusachenSchmerzen = new JEditorPane();
		sPVerusachenSchmerzen.setViewportView(ePVerusachenSchmerzen);
		GroupLayout gl_panel_10 = new GroupLayout(panel_10);
		gl_panel_10.setHorizontalGroup(
			gl_panel_10.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_10.createSequentialGroup()
					.addGap(1)
					.addComponent(sPVerusachenSchmerzen, GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
					.addGap(1))
				.addGroup(gl_panel_10.createSequentialGroup()
					.addGap(1)
					.addComponent(sPSchmerzmittel, GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
					.addGap(1))
		);
		gl_panel_10.setVerticalGroup(
			gl_panel_10.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_10.createSequentialGroup()
					.addGap(1)
					.addComponent(sPSchmerzmittel, GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
					.addGap(1)
					.addComponent(sPVerusachenSchmerzen, GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
					.addGap(5))
		);
		panel_10.setLayout(gl_panel_10);
		panel_8.setLayout(gl_panel_8);
		
		JPanel panel_14 = new JPanel();
		tabber.addTab("Funktionsanamnese", null, panel_14, null);
		
		JPanel panel_11 = new JPanel();
		
		JLabel lblIstDerPatient = new JLabel("Ist der Patient in der Lage, den betreffenden Körperteil zu benutzen?");
		
		cBKöperteilBewegen = new JComboBox<EnumObject>();
		cBKöperteilBewegen.setModel(new DefaultComboBoxModel<EnumObject>(new EnumObject[] {new EnumObject("Bitte Auswählen",EnumType.UNKNOWN), new EnumObject("Ja",EnumType.A), new EnumObject("Nein",EnumType.B)}));
		
		JLabel lblMglicheGehstreckeIn = new JLabel("Mögliche Gehstrecke in Killometer:");
		
		spGehstrecke = new JSpinner(); //Gehstrecke in Km
		
		JLabel label_13 = new JLabel("Mögliche Gehzeit:");
		
		spGehzeit = new JSpinner(); //Gehzeit im Format HH:MM (Stunden:Minuten)
		
		
		JLabel label_14 = new JLabel("Witterungsabhängikeit");
		
		cBWitterung = new JComboBox<EnumObject>();
		cBWitterung.setModel(new DefaultComboBoxModel<EnumObject>(new EnumObject[] {new EnumObject("Bitte Auswählen",EnumType.UNKNOWN), new EnumObject("Ja",EnumType.A), new EnumObject("Nein",EnumType.B)}));
		
		JLabel label_15 = new JLabel("Zykluskorrelation");
		
		comboBoxZyklus = new JComboBox<EnumObject>();
		comboBoxZyklus.setModel(new DefaultComboBoxModel<EnumObject>(new EnumObject[] {new EnumObject("Bitte Auswählen",EnumType.UNKNOWN), new EnumObject("Ja",EnumType.A), new EnumObject("Nein",EnumType.B)}));
		
		JLabel label_16 = new JLabel("Auslauf:");
		
		comboBoxAuslauf = new JComboBox<EnumObject>();
		comboBoxAuslauf.setModel(new DefaultComboBoxModel<EnumObject>(new EnumObject[] {new EnumObject("Bitte Auswählen",EnumType.UNKNOWN), new EnumObject("Ja",EnumType.A), new EnumObject("Nein",EnumType.B)}));
		
		JLabel lblWieVielZeit = new JLabel("Wie viel Zeit können Sie für ihr Tier aufbringen?");
		
		comboBox_6 = new JComboBox<EnumObject>();
		comboBox_6.setModel(new DefaultComboBoxModel<EnumObject>(new EnumObject[] {new EnumObject("Bitte Auswählen",EnumType.UNKNOWN), new EnumObject("Ja",EnumType.A), new EnumObject("Nein",EnumType.B)}));
		
		JPanel panel_12 = new JPanel();
		panel_12.setLayout(new BorderLayout(0, 0));
		
		sP_Bemerkungen = new JScrollPane();
		sP_Bemerkungen.setViewportBorder(new TitledBorder(null, "Sonstige Bemerkungen", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_12.add(sP_Bemerkungen, BorderLayout.CENTER);
		
		JTextPane tPBemerkungen = new JTextPane();
		sP_Bemerkungen.setViewportView(tPBemerkungen);
		
		sPFunktionenMotorik = new JScrollPane();
		sPFunktionenMotorik.setViewportBorder(new TitledBorder(null, "Welche Funktionen motorischer und anderer Art sind nicht beeinflusst?", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		
		JTextPane textPane_19 = new JTextPane();
		sPFunktionenMotorik.setViewportView(textPane_19);
		GroupLayout gl_panel_14 = new GroupLayout(panel_14);
		gl_panel_14.setHorizontalGroup(
			gl_panel_14.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_14.createSequentialGroup()
					.addGroup(gl_panel_14.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_11, GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE)
						.addComponent(sPFunktionenMotorik, GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE))
					.addGap(1)
					.addComponent(panel_12, GroupLayout.PREFERRED_SIZE, 451, GroupLayout.PREFERRED_SIZE)
					.addGap(31))
		);
		gl_panel_14.setVerticalGroup(
			gl_panel_14.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_14.createSequentialGroup()
					.addGap(1)
					.addComponent(sPFunktionenMotorik, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
					.addGap(1)
					.addComponent(panel_11, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(57))
				.addComponent(panel_12, GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
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
		panel_11.add(comboBoxZyklus, "cell 1 4,growx,aligny top");
		panel_11.add(label_16, "cell 0 5,alignx left,aligny center");
		panel_11.add(comboBoxAuslauf, "cell 1 5,growx,aligny top");
		panel_11.add(lblIstDerPatient, "cell 0 0,alignx left,aligny center");
		panel_11.add(lblWieVielZeit, "cell 0 6,alignx left,aligny center");
		panel_11.add(comboBox_6, "cell 1 6,growx,aligny top");
		panel_14.setLayout(gl_panel_14);
		
		
		getContentPane().setLayout(groupLayout);
		
		
		
		
		//////////////////////////////////////////
		
		btnEdit = new JButton("Bearbeiten");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(anamnesis_id != -1){
					editable = true;
					setEditable();
				}
			}
		});
		panel_3.add(btnEdit, "cell 2 0");
		setEditable();
		this.setVisible(true);
	}

	private void initialize(String strName) {
		frame = new JFrame();
		setTitle("Anamnese von " + strName);
		setBounds(100, 100, 1015, 507);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void loadData() {
		
	}
	
	
	private Component getFrame() {
		return this;
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
	private boolean allSet(){
		if(tPAenderungenFamilie.getText().length() <26)
			return false;
		return true;
	}
}
