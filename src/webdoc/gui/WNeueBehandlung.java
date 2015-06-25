/*******************************************************************************
 * Copyright (c) 2015 by the WebDoc group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the WebDoc license.
 * Available inside this binary and at http://webdoc.proctet.net/license.txt
 *******************************************************************************/
package webdoc.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.ActionMap;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SpinnerDateModel;
import javax.swing.UIManager;

import net.miginfocom.swing.MigLayout;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import webdoc.gui.utils.ACElement;
import webdoc.gui.utils.JSearchTextField;
import webdoc.gui.utils.ACElement.ElementType;
import webdoc.gui.utils.JSearchTextField.searchFieldAPI;
import webdoc.lib.Database;
import webdoc.lib.GUIManager;
import webdoc.lib.Database.DBError;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WNeueBehandlung extends JInternalFrame {
	
	private static final long serialVersionUID = -6343632608398217935L;
	private JTextField tFstückPreis;
	private JTextField tFTName;
	private JTextPane tPErklaerung;
	private JSpinner spAnzahl;
	private JButton btnSpeichern;
	private JButton btnNeueBehandlungsart;
	private JButton btnAbrechen;
	private boolean editable;
	private JSpinner spDate;
	private JSpinner spTime;
	private long id;
	private long animalID;
	private long threatmentID;
	private JTextField tFName;
	private PreparedStatement searchStm;
	private Logger logger = LogManager.getLogger();
	private SpinnerDateModel date_model;
	private SpinnerDateModel time_model;
	private JSearchTextField searchTextField;
	
	public WNeueBehandlung(long animalID,long in_id, String animal_name) {
		this.animalID = animalID;
		this.id = in_id;
		editable = id == -1;
		setSize(new Dimension(450, 301));
		setTitle("Neue Behandlung");
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		
		JLabel lblBezeichnnung = new JLabel("Bezeichnnung:");
		
		tFTName = new JTextField();
		tFTName.setColumns(10);
		
		JLabel lblPreisProEinheit = new JLabel("Preis pro Einheit in €:");
		
		tFstückPreis = new JTextField();
		
		JLabel lblErklrung = new JLabel(" Erklärung:");
		
		JScrollPane scrollPane = new JScrollPane();
		
		tPErklaerung = new JTextPane();
		scrollPane.setViewportView(tPErklaerung);
		
		JLabel lblAnzahlDerEinheiten = new JLabel("Anzahl der Einheiten:");
		
		spAnzahl = new JSpinner();
		
		JLabel lblDatum = new JLabel("Datum:");
		
		spDate = new JSpinner();
		date_model = new SpinnerDateModel();
		date_model.setCalendarField(Calendar.DAY_OF_MONTH);
		spDate.setModel(date_model);
		spDate.setEditor(new JSpinner.DateEditor(spDate, "dd-MM-yyyy"));
		
		JLabel lblUhrzeit = new JLabel("Uhrzeit:");
		
		spTime = new JSpinner();
		time_model = new SpinnerDateModel();
		time_model.setCalendarField(Calendar.HOUR_OF_DAY);
		spTime.setModel(time_model);
		spTime.setEditor(new JSpinner.DateEditor(spTime, "HH:mm"));
		panel.setLayout(new MigLayout("", "[102px][281px,grow]", "[][20px][20px][20px][20px][20px][219px]"));
		
		JLabel lblNameDesPatienten = new JLabel("Name des Patienten:");
		panel.add(lblNameDesPatienten, "cell 0 0,alignx trailing");
		
		tFName = new JTextField();
		tFName.setText(animal_name);
		panel.add(tFName, "cell 1 0,growx");
		panel.add(lblBezeichnnung, "cell 0 1,alignx right,aligny center");
		panel.add(tFTName, "cell 1 1,growx,aligny top");
		panel.add(lblPreisProEinheit, "cell 0 2,alignx right,aligny center");
		panel.add(tFstückPreis, "cell 1 2,growx,aligny top");
		panel.add(lblErklrung, "cell 0 6,alignx left,aligny center");
		panel.add(scrollPane, "cell 1 6,grow");
		panel.add(lblUhrzeit, "cell 0 5,alignx left,aligny center");
		panel.add(spTime, "cell 1 5,alignx left,aligny top");
		panel.add(lblDatum, "cell 0 4,alignx left,aligny center");
		panel.add(spDate, "cell 1 4,alignx left,aligny top");
		panel.add(lblAnzahlDerEinheiten, "cell 0 3,alignx left,aligny center");
		panel.add(spAnzahl, "cell 1 3,alignx left,aligny top");
		
		searchTextField = new JSearchTextField(false);
		getContentPane().add(searchTextField, BorderLayout.NORTH);
		
		JPanel pButtons = new JPanel();
		getContentPane().add(pButtons, BorderLayout.SOUTH);
		pButtons.setLayout(new MigLayout("", "[][][][][]", "[]"));
		
		btnSpeichern = new JButton("Speichern");
		btnSpeichern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (id == -1 && editable) {
					addThreatment();
				} else if (editable) {
					logger.error("Not implemented!");
//					updateThreatment();
				} else {
					dispose();
					return;
				}
			}
		});
		pButtons.add(btnSpeichern, "cell 1 0");
		
		btnAbrechen = new JButton("Abrechen");
		btnAbrechen.addActionListener(new ActionListener() {
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
		pButtons.add(btnAbrechen, "cell 2 0");
		btnNeueBehandlungsart = new JButton("Neue Behandlungsart");
		btnNeueBehandlungsart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GUIManager.callWNeueBehandlungsArt(-1);
			}
		});
		pButtons.add(btnNeueBehandlungsart, "cell 4 0");
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{searchTextField, tPErklaerung, spAnzahl, spDate, spTime, btnSpeichern, btnAbrechen, btnNeueBehandlungsart}));
		setEditable();
		
		class ThreatmentProvider implements searchFieldAPI {
			@Override
			public List<ACElement> getData(String text) {
				List<ACElement> list = new ArrayList<ACElement>();
				try {
					searchStm.setString(1, "%" + text + "%");
					ResultSet result = searchStm.executeQuery();

					while (result.next()) {
						list.add(new ACElement(result.getString(2), result.getLong(1), ElementType.RACE));
					}
					result.close();

				} catch (SQLException e) {
					GUIManager.showDBErrorDialog(null, Database.DBExceptionConverter(e, true));
				}
				return list;
			}

			@Override
			public boolean changedSelectionEvent(ACElement element) {
				try{
					ResultSet rs = Database.getThreatment(element.getID());
					rs.next();
					tFTName.setText(rs.getString(1));
					tFstückPreis.setText(rs.getString(2));
					
					threatmentID = element.getID();
					rs.close();
				} catch (SQLException e) {
					GUIManager.showDBErrorDialog(null, Database.DBExceptionConverter(e, true));
				}
				return true;
			}

			@Override
			public String listRenderer(ACElement element) {
				return element.getName();
			}
		}
		
		searchTextField.setAPI(new ThreatmentProvider());
		
		tFTName.setEditable(false);
		tFstückPreis.setEnabled(false);
		tFName.setEditable(false);
		btnAbrechen.setEnabled(true);
		btnNeueBehandlungsart.setEnabled(true);
		
		try {
			searchStm = Database.prepareThreatmentTypeSearchStm();
		} catch (SQLException e) {
			GUIManager.showDBErrorDialog(null, Database.DBExceptionConverter(e, true));
		}
		
	}
	
	/**
	 * Update buttons
	 */
	private void setEditable() {
		spAnzahl.setEnabled(editable);
		spDate.setEnabled(editable);
		spTime.setEnabled(editable);
		tPErklaerung.setEditable(editable);
		searchTextField.setEditable(editable);
		refreshBtn(editable);
	}
	private void refreshBtn(boolean  editable) {
		btnSpeichern.setText(editable ? "Speichern" : "Schließen");
		btnAbrechen.setText(editable ? "Cancel" : "Edit");
	}
	
	/**
	 * simple instance provider for events
	 * 
	 * @return
	 */
	private WNeueBehandlung getFrame() {
		return this;
	}
	
	/**
	 * Return the date from spDate & spTim
	 * @return
	 */
	private Timestamp getDatetimeTimestamp(){
		Calendar cal = Calendar.getInstance();
		cal.setTime((Date) spDate.getValue());
		
		Calendar cal_temp = Calendar.getInstance();
		cal_temp.setTime((Date) spTime.getValue());
		
		cal.set(Calendar.HOUR_OF_DAY,cal_temp.get(Calendar.HOUR_OF_DAY));
		cal.set(Calendar.MINUTE,cal_temp.get(Calendar.MINUTE));
		return new Timestamp(cal.getTimeInMillis());
	}
	
	private void loadData() {
		// TODO Auto-generated method stub
		
	}
	
	private void addThreatment() {
		if (allSet()) {
			try{
				Database.insertAnimalThreatment(threatmentID, animalID, (int) spAnzahl.getValue(),getDatetimeTimestamp(), tPErklaerung.getText());
				editable = false;
				setEditable();
			} catch (SQLException e) {
				DBError error = Database.DBExceptionConverter(e);
				GUIManager.showErrorDialog(this, "Error during insertion: " + error, "Insertion error");
			}
		}else{
			GUIManager.showFieldErrorDialog(this);
		}
	}
	
	@Override
	public void dispose() {
		if (editable) {
			if (GUIFunctions.showIgnoreChangesDialog(this) == 1)
				return;
		}
		Database.closePStatement(searchStm);
		((ActionMap) UIManager.getLookAndFeelDefaults().get("InternalFrame.actionMap")).remove("showSystemMenu");
		super.dispose();
		GUIManager.dropJID(this);
	}
	
	private boolean allSet(){
		return Double.parseDouble(spAnzahl.getValue().toString()) != 0.0;
	}
}
