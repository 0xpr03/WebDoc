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

public class WNeueAnamnese extends JInternalFrame {

	private JFrame frame;
	private boolean editabel;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JSpinner spinBirthdate_1;
	private DateEditor dateEditor;
	private JTextPane textPane_10;
	private JTextPane textPane_11;
	private JTextPane textPane_12;
	private JTextPane textPane_13;
	private JTextPane textPane_17;
	private JTextPane textPane_16;
	private JTextPane textPane_15;
	private JTextPane textPane_14;
	private JEditorPane editorPane;
	private JEditorPane editorPane_1;
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

	/**
	 * Launch the application.
	 */
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
		
		JPanel pHeader = new JPanel();
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(new MigLayout("", "[][]", "[]"));
		
		JButton btnOk = new JButton("Ok");
		panel_3.add(btnOk, "cell 0 0");
		
		JButton btnCancel = new JButton("Cancel");
		panel_3.add(btnCancel, "cell 1 0");
		
		JTabbedPane tabber = new JTabbedPane();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(pHeader, GroupLayout.DEFAULT_SIZE, 1333, Short.MAX_VALUE)
					.addGap(2))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(18)
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(1070, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(tabber, GroupLayout.DEFAULT_SIZE, 1315, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(pHeader, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tabber, GroupLayout.DEFAULT_SIZE, 585, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
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
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(label_1)
						.addComponent(label_2))
					.addGap(2)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING, false)
						.addComponent(textField)
						.addComponent(textField_6, GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE))
					.addContainerGap(163, Short.MAX_VALUE))
				.addComponent(separator, GroupLayout.PREFERRED_SIZE, 421, Short.MAX_VALUE)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(label_3)
						.addComponent(label_4)
						.addComponent(label_5))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING, false)
						.addComponent(spinBirthdate_1)
						.addComponent(textField_7, GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
						.addComponent(textField_8))
					.addGap(167))
				.addGroup(gl_panel_2.createSequentialGroup()
					.addComponent(label)
					.addContainerGap())
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane_5, GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 4, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_3)
						.addComponent(textField_8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_4)
						.addComponent(spinBirthdate_1, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_5)
						.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_5, GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE))
		);
		
		textPane = new JTextPane();
		scrollPane_5.setViewportView(textPane);
		panel_2.setLayout(gl_panel_2);
		
		JPanel panel_1 = new JPanel();
		
		JLabel lblAuslandsaufenthalte = new JLabel("Auslandsaufenthalte:");
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		lblVerhaltensauflligkeiten = new JLabel("Verhaltensaufälligkeiten:");
		
		scrollPane_2 = new JScrollPane();
		
		lblVerletzungen = new JLabel("Verletzungen:");
		
		scrollPane_3 = new JScrollPane();
		
		lblNarben = new JLabel("Narben:");
		
		scrollPane_4 = new JScrollPane();
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblVerletzungen)
							.addGap(56)
							.addComponent(scrollPane_3, GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
							.addComponent(lblNarben)
							.addGap(84)
							.addComponent(scrollPane_4, GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(lblVerhaltensauflligkeiten)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(lblAuslandsaufenthalte)
									.addGap(18)
									.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)))
							.addGap(12))))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblAuslandsaufenthalte)
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE))
					.addGap(17)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblVerhaltensauflligkeiten)
						.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(19)
							.addComponent(lblVerletzungen))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(6)
							.addComponent(scrollPane_3, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)))
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(33)
							.addComponent(lblNarben))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane_4, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		
		textPane_4 = new JTextPane();
		scrollPane_2.setViewportView(textPane_4);
		
		textPane_3 = new JTextPane();
		scrollPane_3.setViewportView(textPane_3);
		
		textPane_2 = new JTextPane();
		scrollPane_4.setViewportView(textPane_2);
		
		JTextPane textPane_1 = new JTextPane();
		scrollPane_1.setViewportView(textPane_1);
		panel_1.setLayout(gl_panel_1);
		
		JPanel panel = new JPanel();
		
		JLabel lblInfektionserkrankungen = new JLabel("Infektionserkrankungen:");
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblRegelmigeImpfungen = new JLabel("Regelmäßige Impfungen:");
		
		JScrollPane scrollPane_6 = new JScrollPane();
		
		JLabel lblHerzkreislauf = new JLabel("Herz/Kreislauf:");
		
		JScrollPane scrollPane_7 = new JScrollPane();
		
		JLabel lblAtmung = new JLabel("Atmung:");
		
		JScrollPane scrollPane_8 = new JScrollPane();
		
		JTextPane textPane_7 = new JTextPane();
		scrollPane_7.setViewportView(textPane_7);
		
		JTextPane textPane_6 = new JTextPane();
		scrollPane_6.setViewportView(textPane_6);
		
		JTextPane textPane_5 = new JTextPane();
		scrollPane.setViewportView(textPane_5);
		GroupLayout gl_pContent = new GroupLayout(pContent);
		gl_pContent.setHorizontalGroup(
			gl_pContent.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pContent.createSequentialGroup()
					.addGap(1)
					.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
					.addGap(1)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE)
					.addGap(1)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
					.addGap(1))
		);
		gl_pContent.setVerticalGroup(
			gl_pContent.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pContent.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pContent.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE)
						.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE)))
		);
		panel.setLayout(new MigLayout("", "[128px][299px,grow]", "[104px][104px][104px][104px][grow]"));
		panel.add(lblAtmung, "cell 0 3,alignx left,aligny top");
		panel.add(scrollPane_8, "cell 1 3,grow");
		
		JTextPane textPane_8 = new JTextPane();
		scrollPane_8.setViewportView(textPane_8);
		panel.add(lblInfektionserkrankungen, "cell 0 0,alignx left,aligny top");
		panel.add(lblRegelmigeImpfungen, "cell 0 1,growx,aligny top");
		panel.add(lblHerzkreislauf, "cell 0 2,alignx left,aligny top");
		panel.add(scrollPane_6, "cell 1 1,grow");
		panel.add(scrollPane, "cell 1 0,grow");
		panel.add(scrollPane_7, "cell 1 2,grow");
		
		JLabel lblVerdauungstrakt = new JLabel("Verdauungstrakt:");
		panel.add(lblVerdauungstrakt, "cell 0 4");
		
		JScrollPane scrollPane_9 = new JScrollPane();
		panel.add(scrollPane_9, "cell 1 4,grow");
		
		JTextPane textPane_9 = new JTextPane();
		scrollPane_9.setViewportView(textPane_9);
		pContent.setLayout(gl_pContent);
		
		JPanel panel_4 = new JPanel();
		tabber.addTab("Allgemeine Anamnese 2", null, panel_4, null);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.X_AXIS));
		
		JPanel panel_5 = new JPanel();
		panel_4.add(panel_5);
		
		JLabel lblEndokrinium = new JLabel("Endokrinium:");
		
		JScrollPane scrollPane_10 = new JScrollPane();
		
		JLabel lblSchilddrse = new JLabel("Schilddrüse:");
		
		JScrollPane scrollPane_11 = new JScrollPane();
		
		JLabel lblBauchspeicheldrse = new JLabel("Bauchspeicheldrüse:");
		
		JScrollPane scrollPane_12 = new JScrollPane();
		
		JLabel lblZns = new JLabel("ZNS:");
		
		JScrollPane scrollPane_13 = new JScrollPane();
		
		JLabel lblEpilemtiformeAnflle = new JLabel("Epilemtiforme Anfälle:");
		panel_5.setLayout(new MigLayout("", "[104px][335px,grow]", "[104px][105px][104px][104px][20px]"));
		panel_5.add(lblEndokrinium, "cell 0 0,alignx left,aligny top");
		panel_5.add(scrollPane_10, "cell 1 0,grow");
		
		textPane_10 = new JTextPane();
		scrollPane_10.setViewportView(textPane_10);
		panel_5.add(lblSchilddrse, "cell 0 1,alignx left,aligny top");
		panel_5.add(scrollPane_11, "cell 1 1,grow");
		
		textPane_11 = new JTextPane();
		scrollPane_11.setViewportView(textPane_11);
		panel_5.add(lblBauchspeicheldrse, "cell 0 2,alignx left,aligny top");
		panel_5.add(scrollPane_12, "cell 1 2,grow");
		
		textPane_12 = new JTextPane();
		scrollPane_12.setViewportView(textPane_12);
		panel_5.add(lblZns, "cell 0 3,alignx left,aligny top");
		panel_5.add(scrollPane_13, "cell 1 3,grow");
		
		textPane_13 = new JTextPane();
		scrollPane_13.setViewportView(textPane_13);
		panel_5.add(lblEpilemtiformeAnflle, "cell 0 4,alignx trailing,aligny center");
		
		JComboBox comboBox_7 = new JComboBox();
		comboBox_7.setModel(new DefaultComboBoxModel(new String[] {"Keine", "Gleichgewichtsstörungen", "Vorübergehendes Schwanken"}));
		panel_5.add(comboBox_7, "cell 1 4,growx");
		
		JPanel panel_6 = new JPanel();
		panel_4.add(panel_6);
		
		JLabel lblMedikamente = new JLabel("Medikamente:");
		
		JScrollPane scrollPane_14 = new JScrollPane();
		
		JLabel lblRntgen = new JLabel("Röntgen:");
		
		JScrollPane scrollPane_15 = new JScrollPane();
		
		JLabel lblCtmrt = new JLabel("CT/MRT:");
		
		JScrollPane scrollPane_16 = new JScrollPane();
		
		JLabel lblSonstiges = new JLabel("Sonstiges:");
		panel_6.setLayout(new MigLayout("", "[67px][335px,grow]", "[104px][104px][104px][14px,grow]"));
		panel_6.add(lblMedikamente, "cell 0 0,alignx left,aligny top");
		panel_6.add(scrollPane_14, "cell 1 0,grow");
		
		textPane_17 = new JTextPane();
		scrollPane_14.setViewportView(textPane_17);
		panel_6.add(lblRntgen, "cell 0 1,alignx left,aligny top");
		panel_6.add(scrollPane_15, "cell 1 1,grow");
		
		textPane_16 = new JTextPane();
		scrollPane_15.setViewportView(textPane_16);
		panel_6.add(lblCtmrt, "cell 0 2,alignx left,aligny top");
		panel_6.add(scrollPane_16, "cell 1 2,grow");
		
		textPane_15 = new JTextPane();
		scrollPane_16.setViewportView(textPane_15);
		panel_6.add(lblSonstiges, "cell 0 3,alignx left,aligny top");
		
		JScrollPane scrollPane_17 = new JScrollPane();
		panel_6.add(scrollPane_17, "cell 1 3,grow");
		
		textPane_14 = new JTextPane();
		scrollPane_17.setViewportView(textPane_14);
		
		JPanel panel_7 = new JPanel();
		panel_4.add(panel_7);
		GroupLayout gl_panel_7 = new GroupLayout(panel_7);
		gl_panel_7.setHorizontalGroup(
			gl_panel_7.createParallelGroup(Alignment.LEADING)
				.addGap(0, 219, Short.MAX_VALUE)
		);
		gl_panel_7.setVerticalGroup(
			gl_panel_7.createParallelGroup(Alignment.LEADING)
				.addGap(0, 557, Short.MAX_VALUE)
		);
		panel_7.setLayout(gl_panel_7);
		
		JPanel panel_8 = new JPanel();
		tabber.addTab("spezielle symptombezogene Anamnese", null, panel_8, null);
		panel_8.setLayout(new BoxLayout(panel_8, BoxLayout.X_AXIS));
		
		JPanel panel_9 = new JPanel();
		panel_8.add(panel_9);
		
		JLabel label_6 = new JLabel("Hauptproblem:");
		
		JScrollPane scrollPane_18 = new JScrollPane();
		
		JLabel label_7 = new JLabel("Schilderung des Patientenbesitzers:");
		
		JScrollPane scrollPane_19 = new JScrollPane();
		
		JLabel label_8 = new JLabel("Was wurde unternommen?:");
		
		JScrollPane scrollPane_20 = new JScrollPane();
		
		JLabel label_9 = new JLabel("Schmerzempfindlichkeit:");
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Bitte Auswählen", "Ja", "Nein"}));
		
		JLabel lblDenkenSieIhr = new JLabel("Denken sie ihr Tier hat Schmerzen?:");
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Bitte Äuswählen", "Ja,immer", "Gelegentlich", "Nein"}));
		panel_9.setLayout(new MigLayout("", "[173px][452px]", "[128px][127px][127px][21px][20px]"));
		panel_9.add(label_6, "cell 0 0,alignx left,aligny top");
		panel_9.add(scrollPane_18, "cell 1 0,grow");
		
		editorPane = new JEditorPane();
		scrollPane_18.setViewportView(editorPane);
		panel_9.add(label_7, "cell 0 1,alignx left,aligny top");
		panel_9.add(scrollPane_19, "cell 1 1,grow");
		
		editorPane_1 = new JEditorPane();
		scrollPane_19.setViewportView(editorPane_1);
		panel_9.add(label_8, "cell 0 2,alignx left,aligny top");
		panel_9.add(scrollPane_20, "cell 1 2,grow");
		
		JEditorPane editorPane_2 = new JEditorPane();
		scrollPane_20.setViewportView(editorPane_2);
		panel_9.add(label_9, "cell 0 3,alignx left,aligny bottom");
		panel_9.add(comboBox, "cell 1 3,alignx left,aligny top");
		panel_9.add(lblDenkenSieIhr, "cell 0 4,alignx left,aligny center");
		panel_9.add(comboBox_1, "cell 1 4,growx,aligny top");
		
		JPanel panel_10 = new JPanel();
		panel_8.add(panel_10);
		
		JLabel label_11 = new JLabel("Wie reagiert das Tier auf Schmerzmittel?");
		
		JLabel label_12 = new JLabel("Welche Bewegungen verursachen den Schmerz?");
		panel_10.setLayout(new MigLayout("", "[232px][452px,grow]", "[70px,grow][70px,grow]"));
		
		JScrollPane scrollPane_21 = new JScrollPane();
		panel_10.add(scrollPane_21, "cell 1 0,grow");
		
		JEditorPane editorPane_3 = new JEditorPane();
		scrollPane_21.setViewportView(editorPane_3);
		panel_10.add(label_12, "cell 0 1,alignx left,aligny top");
		panel_10.add(label_11, "cell 0 0,alignx center,aligny top");
		
		JScrollPane scrollPane_22 = new JScrollPane();
		panel_10.add(scrollPane_22, "cell 1 1,grow");
		
		JEditorPane editorPane_4 = new JEditorPane();
		scrollPane_22.setViewportView(editorPane_4);
		
		JPanel panel_14 = new JPanel();
		tabber.addTab("Funktionsanamnese", null, panel_14, null);
		panel_14.setLayout(new BoxLayout(panel_14, BoxLayout.X_AXIS));
		
		JPanel panel_11 = new JPanel();
		panel_14.add(panel_11);
		
		JLabel lblWelcheFunktionenMotorischer = new JLabel("Welche Funktionen motorischer und anderer Art sind nicht beeinflusst?");
		
		JLabel lblIstDerPatient = new JLabel("Ist der Patient in der Lage, den betreffenden Körperteil zu benutzen?");
		panel_11.setLayout(new MigLayout("", "[338px][28px][220px]", "[128px][20px][20px][20px][20px][20px][20px][20px]"));
		
		JScrollPane scrollPane_24 = new JScrollPane();
		panel_11.add(scrollPane_24, "cell 2 0,grow");
		
		JTextPane textPane_19 = new JTextPane();
		scrollPane_24.setViewportView(textPane_19);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"Bitte Auswählen", "Ja", "Nein"}));
		panel_11.add(comboBox_2, "cell 2 1,alignx left,aligny top");
		
		JLabel label_10 = new JLabel("Mögliche Gehstrecke:");
		panel_11.add(label_10, "cell 0 2,alignx left,aligny center");
		
		JSpinner spinner_1 = new JSpinner();
		panel_11.add(spinner_1, "cell 2 2,growx,aligny top");
		
		JLabel label_13 = new JLabel("Mögliche Gehzeit:");
		panel_11.add(label_13, "cell 0 3,alignx left,aligny center");
		
		JSpinner spinner_2 = new JSpinner();
		panel_11.add(spinner_2, "cell 2 3,growx,aligny top");
		
		JLabel label_14 = new JLabel("Witterungsabhängikeit");
		panel_11.add(label_14, "cell 0 4,alignx left,aligny center");
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"Bitte Auswählen", "Ja", "Nein"}));
		panel_11.add(comboBox_3, "cell 2 4,growx,aligny top");
		
		JLabel label_15 = new JLabel("Zykluskorrelation");
		panel_11.add(label_15, "cell 0 5,alignx left,aligny center");
		
		JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setModel(new DefaultComboBoxModel(new String[] {"Bitte Auswählen", "Ja", "Nein"}));
		panel_11.add(comboBox_4, "cell 2 5,growx,aligny top");
		
		JLabel label_16 = new JLabel("Auslauf:");
		panel_11.add(label_16, "cell 0 6,alignx left,aligny center");
		
		JComboBox comboBox_5 = new JComboBox();
		comboBox_5.setModel(new DefaultComboBoxModel(new String[] {"Bitte Auswählen", "Ja", "Nein"}));
		panel_11.add(comboBox_5, "cell 2 6,growx,aligny top");
		panel_11.add(lblWelcheFunktionenMotorischer, "cell 0 0,alignx left,aligny top");
		panel_11.add(lblIstDerPatient, "cell 0 1,alignx center,aligny center");
		
		JLabel lblWieVielZeit = new JLabel("Wie viel Zeit können Sie für ihr Tier aufbringen?");
		panel_11.add(lblWieVielZeit, "cell 0 7");
		
		JComboBox comboBox_6 = new JComboBox();
		comboBox_6.setModel(new DefaultComboBoxModel(new String[] {"Bitte Auswählen", "Ja", "Nein"}));
		panel_11.add(comboBox_6, "cell 2 7,growx,aligny top");
		
		JPanel panel_12 = new JPanel();
		panel_12.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Sonstige Bemerkungen", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_14.add(panel_12);
		panel_12.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_23 = new JScrollPane();
		panel_12.add(scrollPane_23, BorderLayout.CENTER);
		
		JTextPane textPane_18 = new JTextPane();
		scrollPane_23.setViewportView(textPane_18);
		
		
		getContentPane().setLayout(groupLayout);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(boolean editable, String strName) {
		frame = new JFrame();
		setTitle("Anamnese von " + strName);
		setBounds(100, 100, 1351, 687);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
