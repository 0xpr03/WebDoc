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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import net.miginfocom.swing.MigLayout;
import webdoc.gui.utils.ACElement;
import webdoc.gui.utils.ACElement.ElementType;
import webdoc.gui.utils.JSearchTextField;
import webdoc.gui.utils.WModelPane;
import webdoc.gui.utils.JSearchTextField.searchFieldAPI;
import webdoc.lib.Database;
import webdoc.lib.GUIManager;
import javax.swing.JFrame;

public class WNeueBehandlung extends WModelPane {

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
	private SpinnerDateModel date_model;
	private SpinnerDateModel time_model;
	private JSearchTextField searchTextField;

	public WNeueBehandlung(long animalID, long in_id, String animal_name) {
		super(serialVersionUID);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.animalID = animalID;
		this.id = in_id;
		editable = id == -1;
		setSize(new Dimension(450, 301));
		setTitle(editable ? "Neue Behandlung" : "Behandlung");
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
		spAnzahl.setPreferredSize(new Dimension(45, 20));
		spAnzahl.setModel(new SpinnerNumberModel(new Double(0), null, null, new Double(1)));

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
					addTreatment();
				} else if (editable) {
					updatePetThreatment();
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
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { searchTextField, tPErklaerung, spAnzahl,
				spDate, spTime, btnSpeichern, btnAbrechen, btnNeueBehandlungsart }));
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
				updateTreatment(element.getID());
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
			searchStm = Database.prepareTreatmentTypeSearchStm();
		} catch (SQLException e) {
			GUIManager.showDBErrorDialog(null, Database.DBExceptionConverter(e, true));
		}
		loadData();
	}

	protected void updatePetThreatment() {
		try {
			Database.editAnimalTreatment(id, threatmentID, (int) spAnzahl
					.getValue(), getDatetimeTimestamp(), tPErklaerung.getText());
			editable = false;
			setEditable();
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

	private void refreshBtn(boolean editable) {
		btnSpeichern.setText(editable ? "Speichern" : "Schließen");
		btnAbrechen.setText(editable ? "Cancel" : "Edit");
	}

	/**
	 * Return the date from spDate & spTim
	 * 
	 * @return
	 */
	private Timestamp getDatetimeTimestamp() {
		Calendar cal = Calendar.getInstance();
		cal.setTime((Date) spDate.getValue());

		Calendar cal_temp = Calendar.getInstance();
		cal_temp.setTime((Date) spTime.getValue());

		cal.set(Calendar.HOUR_OF_DAY, cal_temp.get(Calendar.HOUR_OF_DAY));
		cal.set(Calendar.MINUTE, cal_temp.get(Calendar.MINUTE));
		return new Timestamp(cal.getTimeInMillis());
	}

	private void loadData() {
		if (id != -1) {
			setGlassPaneVisible(true);
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					Thread t = new Thread(new Runnable() {
						public void run() {
							try {
								ResultSet rs = Database.getAnimalTreatment(id);
								rs.next();
								updateTreatment(rs.getLong(1));
								spAnzahl.setValue(rs.getDouble(2));
								spDate.setValue(rs.getTimestamp(3));
								spTime.setValue(rs.getTimestamp(3));
								tPErklaerung.setText(rs.getString(4));
								rs.close();
							} catch (SQLException e) {
								GUIManager.showDBErrorDialog(null, Database.DBExceptionConverter(e, true));
							}
							setGlassPaneVisible(false);
						}
					});
					t.start();
				}
			});
		}
	}

	private void addTreatment() {
		if (allSet()) {
			setGlassPaneVisible(true);
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					Thread t = new Thread(new Runnable() {
						public void run() {
							try {
								id = Database.insertAnimalTreatment(threatmentID, animalID, (double) spAnzahl
										.getValue(), getDatetimeTimestamp(), tPErklaerung.getText());
								editable = false;
								setEditable();
							} catch (SQLException e) {
								GUIManager.showDBErrorDialog(null, Database.DBExceptionConverter(e, true));
							}
							setGlassPaneVisible(false);
						}
					});
					t.start();
				}
			});
		} else {
			GUIManager.showFieldErrorDialog(getFrame());
		}
	}

	/**
	 * Update the treatment based on the id<br>
	 * !NOT the PETTreatment<br>
	 * Shows DBErrorDialog on fail, updates threatmentID
	 * 
	 * @param id
	 * @author "Aron Heinecke"
	 */
	private void updateTreatment(final long id) {
		setGlassPaneVisible(true,true);
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Thread t = new Thread(new Runnable() {
					public void run() {
						try {
							ResultSet rs = Database.getTreatment(id);
							rs.next();
							tFTName.setText(rs.getString(1));
							tFstückPreis.setText(rs.getString(2));
							
							threatmentID = id;
							rs.close();
						} catch (SQLException e) {
							GUIManager.showDBErrorDialog(null, Database.DBExceptionConverter(e, true));
						}
						setGlassPaneVisible(false);
					}
				});
				t.start();
			}
		});
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

	private boolean allSet() {
		if  (Double.parseDouble(spAnzahl.getValue().toString()) == 0.0){
			return false;}
		if (tFName.getText() == ""){
			return false;}
		return true;
	}
}
