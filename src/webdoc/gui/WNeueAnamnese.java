/*******************************************************************************
 * Copyright (c) 2015 by the WebDoc group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the WebDoc license.
 * Available inside this binary and at http://webdoc.proctet.net/license.txt
 *******************************************************************************/
package webdoc.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.BoxLayout;

import java.awt.BorderLayout;
import java.util.Calendar;

import javax.swing.JSpinner.DateEditor;
import javax.swing.JSplitPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SpinnerDateModel;

import net.miginfocom.swing.MigLayout;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.DefaultComboBoxModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WNeueAnamnese extends JInternalFrame {

	private JFrame frame;
	private boolean editabel;
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
	private JTextPane textPane;
	private JLabel lblVerletzungen;
	private JScrollPane scrollPane_3;
	private JTextPane textPane_3;
	private JLabel lblNarben;
	private JScrollPane scrollPane_4;
	private JTextPane textPane_2;
	private JLabel lblVerhaltensauflligkeiten;
	private JScrollPane scrollPane_2;
	private JTextPane textPane_4;
	private JScrollPane scrollPane_1;
	private JLabel lblAtmung;
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
	private JComboBox CBSchmerzempfindlichkeit;
	private JComboBox cBDenkenSchmerzen;
	private JScrollPane sPSchmerzmittel;
	private JScrollPane sPVerusachenSchmerzen;
	private JScrollPane sPFunktionenMotorik;
	private JComboBox cBKöperteilBewegen;
	private JSpinner spGehstrecke;
	private JSpinner spGehzeit;
	private JComboBox cBWitterung;
	private JComboBox comboBoxZyklus;
	private JComboBox comboBoxAuslauf;
	private JComboBox comboBox_6;
	private JScrollPane sP_Bemerkungen;

	/**
	 * Launch the application.
	 */
	private void setEditable(boolean editable ){

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
		  textPane.setEditable(editable);
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
		  CBSchmerzempfindlichkeit.setEditable(editable);
		  cBDenkenSchmerzen.setEditable(editable);
		  sPSchmerzmittel.setEnabled(editable);
		  sPVerusachenSchmerzen.setEnabled(editable);
		  sPFunktionenMotorik.setEnabled(editable);
		  cBKöperteilBewegen.setEditable(editable);
		  spGehstrecke.setEnabled(editable);;
		  spGehzeit.setEnabled(editable);;
		  cBWitterung.setEditable(editable);
		  comboBoxZyklus.setEditable(editable);
		  comboBoxAuslauf.setEditable(editable);
		  comboBox_6.setEditable(editable);
		  sP_Bemerkungen.setEnabled(editable);
		
	}
	public static void main(final boolean editable) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WNeueAnamnese window = new WNeueAnamnese(editable, null);
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
	public WNeueAnamnese(boolean editable,String strName) {
		this.editabel = editable;
		initialize(editable, strName);
		setFrameIcon(null);
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(new MigLayout("", "[][]", "[]"));
		
		JButton btnOk = new JButton("Speichern");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		panel_3.add(btnOk, "cell 0 0");
		
		JButton btnCancel = new JButton("Cancel");
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
		
		JLabel label = new JLabel("Änderungen im Familiengefüge:");
		
		scrollPane_5 = new JScrollPane();
		
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
		spinBirthdate_1 = new JSpinner();
		SpinnerDateModel model = new SpinnerDateModel();
		model.setCalendarField(Calendar.MINUTE);
		spinBirthdate_1.setEnabled(editable);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		
		textPane = new JTextPane();
		scrollPane_5.setViewportView(textPane);
		
		JPanel panel_1 = new JPanel();
		
		JLabel lblAuslandsaufenthalte = new JLabel("Auslandsaufenthalte:");
		
		scrollPane_1 = new JScrollPane();
		
		lblVerhaltensauflligkeiten = new JLabel("Verhaltensaufälligkeiten:");
		
		scrollPane_2 = new JScrollPane();
		
		lblVerletzungen = new JLabel("Verletzungen:");
		
		scrollPane_3 = new JScrollPane();
		
		lblNarben = new JLabel("Narben:");
		
		scrollPane_4 = new JScrollPane();
		
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
				.addComponent(label)
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
					.addGap(6)
					.addComponent(label)
					.addGap(6)
					.addComponent(scrollPane_5, GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
					.addGap(18))
		);
		panel_2.setLayout(gl_panel_2);
		splitPane.setRightComponent(panel_1);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblAuslandsaufenthalte)
							.addGap(18)
							.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
							.addGap(2))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblVerhaltensauflligkeiten)
							.addGap(4)
							.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
							.addGap(2))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblVerletzungen)
							.addGap(56)
							.addComponent(scrollPane_3, GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblNarben)
							.addGap(84)
							.addComponent(scrollPane_4, GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE))))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(11)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblAuslandsaufenthalte)
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE))
					.addGap(17)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblVerhaltensauflligkeiten)
						.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))
					.addGap(6)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(13)
							.addComponent(lblVerletzungen))
						.addComponent(scrollPane_3, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))
					.addGap(6)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(27)
							.addComponent(lblNarben))
						.addComponent(scrollPane_4, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE))
					.addGap(29))
		);
		panel_1.setLayout(gl_panel_1);
		
		JPanel panel = new JPanel();
		splitPane_2.setRightComponent(panel);
		
		JLabel lblInfektionserkrankungen = new JLabel("Infektionserkrankungen:");
		
		scrollPane = new JScrollPane();
		
		JLabel lblRegelmigeImpfungen = new JLabel("Regelmäßige Impfungen:");
		
		scrollPane_6 = new JScrollPane();
		
		JLabel lblHerzkreislauf = new JLabel("Herz/Kreislauf:");
		
		scrollPane_7 = new JScrollPane();
		
		lblAtmung = new JLabel("Atmung:");
		
		scrollPane_8 = new JScrollPane();
		
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
		
		JLabel lblVerdauungstrakt = new JLabel("Verdauungstrakt:");
		
		scrollPane_9 = new JScrollPane();
		
		JTextPane tPVerdauung = new JTextPane();
		scrollPane_9.setViewportView(tPVerdauung);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(7)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblInfektionserkrankungen)
							.addGap(14)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblRegelmigeImpfungen, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(scrollPane_6, GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
							.addGap(1))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblHerzkreislauf)
							.addGap(61)
							.addComponent(scrollPane_7, GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
							.addGap(1))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblAtmung)
							.addGap(91)
							.addComponent(scrollPane_8, GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
							.addGap(1))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblVerdauungstrakt)
							.addGap(48)
							.addComponent(scrollPane_9, GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
							.addGap(1)))
					.addGap(7))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(7)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblInfektionserkrankungen)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))
					.addGap(4)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblRegelmigeImpfungen)
						.addComponent(scrollPane_6, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))
					.addGap(4)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblHerzkreislauf)
						.addComponent(scrollPane_7, GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE))
					.addGap(4)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblAtmung)
						.addComponent(scrollPane_8, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))
					.addGap(4)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(9)
							.addComponent(lblVerdauungstrakt))
						.addComponent(scrollPane_9, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
					.addGap(7))
		);
		panel.setLayout(gl_panel);
		pContent.setLayout(gl_pContent);
		
		JPanel panel_4 = new JPanel();
		tabber.addTab("Allgemeine Anamnese 2", null, panel_4, null);
		
		JPanel panel_5 = new JPanel();
		
		JLabel lblEndokrinium = new JLabel("Endokrinium:");
		
		scrollPane_10 = new JScrollPane();
		
		JLabel lblSchilddrse = new JLabel("Schilddrüse:");
		
		scrollPane_11 = new JScrollPane();
		
		JLabel lblBauchspeicheldrse = new JLabel("Bauchspeicheldrüse:");
		
		scrollPane_12 = new JScrollPane();
		
		JLabel lblZns = new JLabel("ZNS:");
		
		scrollPane_13 = new JScrollPane();
		
		JLabel lblEpilemtiformeAnflle = new JLabel("Epilemtiforme Anfälle:");
		
		tpEndokrinium = new JTextPane();
		scrollPane_10.setViewportView(tpEndokrinium);
		
		tPSchilddruese = new JTextPane();
		scrollPane_11.setViewportView(tPSchilddruese);
		
		tPBauchspeicheldruese = new JTextPane();
		scrollPane_12.setViewportView(tPBauchspeicheldruese);
		
		tPZNS = new JTextPane();
		scrollPane_13.setViewportView(tPZNS);
		
		JComboBox cBEpiAnfaelle = new JComboBox();
		cBEpiAnfaelle.setModel(new DefaultComboBoxModel(new String[] {"Keine", "Gleichgewichtsstörungen", "Vorübergehendes Schwanken"}));
		
		JPanel panel_6 = new JPanel();
		
		JLabel lblMedikamente = new JLabel("Medikamente:");
		
		scrollPane_14 = new JScrollPane();
		
		JLabel lblRntgen = new JLabel("Röntgen:");
		
		scrollPane_15 = new JScrollPane();
		
		JLabel lblCtmrt = new JLabel("CT/MRT:");
		
		scrollPane_16 = new JScrollPane();
		
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
					.addComponent(panel_6, GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
					.addGap(1))
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addGap(1)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_5, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
						.addComponent(panel_6, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE))
					.addGap(7))
		);
		panel_6.setLayout(new MigLayout("", "[67px][426px]", "[109px][109px][109px]"));
		panel_6.add(lblMedikamente, "cell 0 0,alignx left,aligny top");
		panel_6.add(scrollPane_14, "cell 1 0,grow");
		panel_6.add(lblRntgen, "cell 0 1,alignx left,aligny top");
		panel_6.add(scrollPane_15, "cell 1 1,grow");
		panel_6.add(lblCtmrt, "cell 0 2,alignx left,aligny top");
		panel_6.add(scrollPane_16, "cell 1 2,grow");
		panel_5.setLayout(new MigLayout("", "[111px][408px,grow]", "[60.00px,grow][67.00px,grow][73.00px,grow][88.00px,grow][20px,grow,baseline]"));
		panel_5.add(lblEpilemtiformeAnflle, "cell 0 4,alignx right,aligny center");
		panel_5.add(cBEpiAnfaelle, "cell 1 4,growx,aligny top");
		panel_5.add(lblEndokrinium, "cell 0 0,alignx left,aligny top");
		panel_5.add(scrollPane_10, "cell 1 0,grow");
		panel_5.add(lblZns, "cell 0 3,alignx left,aligny center");
		panel_5.add(lblBauchspeicheldrse, "cell 0 2,alignx left,aligny top");
		panel_5.add(lblSchilddrse, "cell 0 1,alignx left,aligny top");
		panel_5.add(scrollPane_11, "cell 1 1,grow");
		panel_5.add(scrollPane_12, "cell 1 2,grow");
		panel_5.add(scrollPane_13, "cell 1 3,grow");
		panel_4.setLayout(gl_panel_4);
		
		JPanel panel_8 = new JPanel();
		tabber.addTab("spezielle symptombezogene Anamnese", null, panel_8, null);
		
		JPanel panel_9 = new JPanel();
		
		JLabel label_6 = new JLabel("Hauptproblem:");
		
		scrollPane_18 = new JScrollPane();
		
		JLabel label_7 = new JLabel("Schilderung des Patientenbesitzers:");
		
		scrollPane_19 = new JScrollPane();
		
		JLabel label_8 = new JLabel("Was wurde unternommen?:");
		
		scrollPane_20 = new JScrollPane();
		
		JLabel label_9 = new JLabel("Schmerzempfindlichkeit:");
		
		CBSchmerzempfindlichkeit = new JComboBox();
		CBSchmerzempfindlichkeit.setModel(new DefaultComboBoxModel(new String[] {"Bitte Auswählen", "Ja", "Nein"}));
		
		JLabel lblDenkenSieIhr = new JLabel("Denken sie ihr Tier hat Schmerzen?:");
		
		cBDenkenSchmerzen = new JComboBox();
		cBDenkenSchmerzen.setModel(new DefaultComboBoxModel(new String[] {"Bitte Äuswählen", "Ja,immer", "Gelegentlich", "Nein"}));
		panel_9.setLayout(new MigLayout("", "[173px][452px]", "[128px][127px][127px][21px][20px]"));
		panel_9.add(label_6, "cell 0 0,alignx left,aligny top");
		panel_9.add(scrollPane_18, "cell 1 0,grow");
		
		ePHauptproblem = new JEditorPane();
		scrollPane_18.setViewportView(ePHauptproblem);
		panel_9.add(label_7, "cell 0 1,alignx left,aligny top");
		panel_9.add(scrollPane_19, "cell 1 1,grow");
		
		ePSchilderung = new JEditorPane();
		scrollPane_19.setViewportView(ePSchilderung);
		panel_9.add(label_8, "cell 0 2,alignx left,aligny top");
		panel_9.add(scrollPane_20, "cell 1 2,grow");
		
		JEditorPane ePUnternommen = new JEditorPane();
		scrollPane_20.setViewportView(ePUnternommen);
		panel_9.add(label_9, "cell 0 3,alignx left,aligny bottom");
		panel_9.add(CBSchmerzempfindlichkeit, "cell 1 3,alignx left,aligny top");
		panel_9.add(lblDenkenSieIhr, "cell 0 4,alignx left,aligny center");
		panel_9.add(cBDenkenSchmerzen, "cell 1 4,growx,aligny top");
		
		JPanel panel_10 = new JPanel();
		
		JLabel label_11 = new JLabel("Wie reagiert das Tier auf Schmerzmittel?");
		
		JLabel label_12 = new JLabel("Welche Bewegungen verursachen den Schmerz?");
		panel_10.setLayout(new MigLayout("", "[232px][452px,grow]", "[70px,grow][70px,grow]"));
		
		sPSchmerzmittel = new JScrollPane();
		panel_10.add(sPSchmerzmittel, "cell 1 0,grow");
		
		JEditorPane ePSchmerzmittel = new JEditorPane();
		sPSchmerzmittel.setViewportView(ePSchmerzmittel);
		panel_10.add(label_12, "cell 0 1,alignx left,aligny top");
		panel_10.add(label_11, "cell 0 0,alignx center,aligny top");
		
		sPVerusachenSchmerzen = new JScrollPane();
		panel_10.add(sPVerusachenSchmerzen, "cell 1 1,grow");
		
		JEditorPane ePVerusachenSchmerzen = new JEditorPane();
		sPVerusachenSchmerzen.setViewportView(ePVerusachenSchmerzen);
		GroupLayout gl_panel_8 = new GroupLayout(panel_8);
		gl_panel_8.setHorizontalGroup(
			gl_panel_8.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_8.createSequentialGroup()
					.addComponent(panel_9, GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
					.addComponent(panel_10, GroupLayout.DEFAULT_SIZE, 687, Short.MAX_VALUE)
					.addGap(1))
		);
		gl_panel_8.setVerticalGroup(
			gl_panel_8.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_9, GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
				.addComponent(panel_10, GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
		);
		panel_8.setLayout(gl_panel_8);
		
		JPanel panel_14 = new JPanel();
		tabber.addTab("Funktionsanamnese", null, panel_14, null);
		
		JPanel panel_11 = new JPanel();
		
		JLabel lblWelcheFunktionenMotorischer = new JLabel("Welche Funktionen motorischer und anderer Art sind nicht beeinflusst?");
		
		JLabel lblIstDerPatient = new JLabel("Ist der Patient in der Lage, den betreffenden Körperteil zu benutzen?");
		panel_11.setLayout(new MigLayout("", "[338px][28px][220px]", "[128px][20px][20px][20px][20px][20px][20px][20px]"));
		
		sPFunktionenMotorik = new JScrollPane();
		panel_11.add(sPFunktionenMotorik, "cell 2 0,grow");
		
		JTextPane textPane_19 = new JTextPane();
		sPFunktionenMotorik.setViewportView(textPane_19);
		
		cBKöperteilBewegen = new JComboBox();
		cBKöperteilBewegen.setModel(new DefaultComboBoxModel(new String[] {"Bitte Auswählen", "Ja", "Nein"}));
		panel_11.add(cBKöperteilBewegen, "cell 2 1,alignx left,aligny top");
		
		JLabel label_10 = new JLabel("Mögliche Gehstrecke:");
		panel_11.add(label_10, "cell 0 2,alignx left,aligny center");
		
		spGehstrecke = new JSpinner();
		panel_11.add(spGehstrecke, "cell 2 2,growx,aligny top");
		
		JLabel label_13 = new JLabel("Mögliche Gehzeit:");
		panel_11.add(label_13, "cell 0 3,alignx left,aligny center");
		
		spGehzeit = new JSpinner();
		panel_11.add(spGehzeit, "cell 2 3,growx,aligny top");
		
		JLabel label_14 = new JLabel("Witterungsabhängikeit");
		panel_11.add(label_14, "cell 0 4,alignx left,aligny center");
		
		cBWitterung = new JComboBox();
		cBWitterung.setModel(new DefaultComboBoxModel(new String[] {"Bitte Auswählen", "Ja", "Nein"}));
		panel_11.add(cBWitterung, "cell 2 4,growx,aligny top");
		
		JLabel label_15 = new JLabel("Zykluskorrelation");
		panel_11.add(label_15, "cell 0 5,alignx left,aligny center");
		
		comboBoxZyklus = new JComboBox();
		comboBoxZyklus.setModel(new DefaultComboBoxModel(new String[] {"Bitte Auswählen", "Ja", "Nein"}));
		panel_11.add(comboBoxZyklus, "cell 2 5,growx,aligny top");
		
		JLabel label_16 = new JLabel("Auslauf:");
		panel_11.add(label_16, "cell 0 6,alignx left,aligny center");
		
		comboBoxAuslauf = new JComboBox();
		comboBoxAuslauf.setModel(new DefaultComboBoxModel(new String[] {"Bitte Auswählen", "Ja", "Nein"}));
		panel_11.add(comboBoxAuslauf, "cell 2 6,growx,aligny top");
		panel_11.add(lblWelcheFunktionenMotorischer, "cell 0 0,alignx left,aligny top");
		panel_11.add(lblIstDerPatient, "cell 0 1,alignx center,aligny center");
		
		JLabel lblWieVielZeit = new JLabel("Wie viel Zeit können Sie für ihr Tier aufbringen?");
		panel_11.add(lblWieVielZeit, "cell 0 7");
		
		comboBox_6 = new JComboBox();
		comboBox_6.setModel(new DefaultComboBoxModel(new String[] {"Bitte Auswählen", "Ja", "Nein"}));
		panel_11.add(comboBox_6, "cell 2 7,growx,aligny top");
		
		JPanel panel_12 = new JPanel();
		panel_12.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Sonstige Bemerkungen", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_12.setLayout(new BorderLayout(0, 0));
		
		sP_Bemerkungen = new JScrollPane();
		panel_12.add(sP_Bemerkungen, BorderLayout.CENTER);
		
		JTextPane tPBemerkungen = new JTextPane();
		sP_Bemerkungen.setViewportView(tPBemerkungen);
		GroupLayout gl_panel_14 = new GroupLayout(panel_14);
		gl_panel_14.setHorizontalGroup(
			gl_panel_14.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_14.createSequentialGroup()
					.addComponent(panel_11, GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE)
					.addComponent(panel_12, GroupLayout.DEFAULT_SIZE, 709, Short.MAX_VALUE)
					.addGap(1))
		);
		gl_panel_14.setVerticalGroup(
			gl_panel_14.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_14.createSequentialGroup()
					.addGap(49)
					.addComponent(panel_11, GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE))
				.addComponent(panel_12, GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
		);
		panel_14.setLayout(gl_panel_14);
		
		
		getContentPane().setLayout(groupLayout);
		
		
		
		
		//////////////////////////////////////////
		btnOk.setText(editable ? "Speichern" : "Schließen");
		setEditable(editable);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(boolean editable, String strName) {
		frame = new JFrame();
		setTitle("Anamnese von " + strName);
		setBounds(100, 100, 1052, 518);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
