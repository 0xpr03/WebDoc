/*******************************************************************************
 * Copyright (c) 2015 by Aron Heinecke & Jonathan Peper
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the WebDoc license.
 * Available inside this binary and at http://webdoc.proctet.net/license.txt
 *******************************************************************************/
package webdoc.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingUtilities;

import net.miginfocom.swing.MigLayout;
import webdoc.gui.utils.WModelPane;
import webdoc.lib.Database;
import webdoc.lib.GUIManager;

public class WNeueUntersuchung extends WModelPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1755313522984992683L;
	private JTextField tFName;
	private JTextPane tPBefund;
	private JSpinner spDate;
	private JButton btnSpeichern;
	private JButton btnCancel;
	private Boolean editable;
	private long id;
	private long animalID;

	public WNeueUntersuchung(String petName,long in_id,long in_animalID) {
		super(serialVersionUID);
		this.id = in_id;
		this.animalID = in_animalID;
		initialize(petName);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String petName) {
		editable = id == -1 ? true : false;
		setBounds(100, 100, 450, 335);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new MigLayout("", "[][][][]", "[]"));
		
		btnSpeichern = new JButton("Speichern");
		btnSpeichern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (editable) {
					entryExamination();
				} else {
					dispose();
					return;
				}
			}
		});
		panel.add(btnSpeichern, "cell 1 0");
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
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
		panel.add(btnCancel, "cell 2 0");
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		
		JLabel lblNameDesTieres = new JLabel("Name des Tieres:");
		
		tFName = new JTextField(petName);
		tFName.setColumns(10);
		
		JLabel lblDatum = new JLabel("Datum:");
		
		spDate = new JSpinner();
		spDate.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_YEAR));
		spDate.setEditor(new JSpinner.DateEditor(spDate, "dd-MM-yyyy"));
		panel_1.setLayout(new MigLayout("", "[83px][333px,grow]", "[20px][20px][grow]"));
		panel_1.add(lblNameDesTieres, "cell 0 0,alignx left,aligny center");
		panel_1.add(tFName, "cell 1 0,growx,aligny top");
		panel_1.add(lblDatum, "cell 0 1,alignx left,aligny center");
		panel_1.add(spDate, "cell 1 1,alignx left,aligny top");
		
		JLabel lblBefund = new JLabel("Befund");
		panel_1.add(lblBefund, "cell 0 2");
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane, "cell 1 2,grow");
		
		tPBefund = new JTextPane();
		scrollPane.setViewportView(tPBefund);
		tFName.setEditable(false);
		setEditable();
		loadData();
	}

	private void setEditable() {
		tPBefund.setEditable(editable);
		spDate.setEnabled(editable);
		setTitle(editable ? "Neue Untersuchung" : "Untersuchung");
		refreshBtn();
	}

	private void refreshBtn() {
		btnSpeichern.setText(editable ? "Speichern" : "Schließen");
		btnCancel.setText(editable ? "Cancel" : "Edit");		
	}
	
	private boolean allSet(){
		return tPBefund.getText() != "";
	}
	
	private void entryExamination() {
		if (allSet()) {
			setGlassPaneVisible(true);
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					Thread t = new Thread(new Runnable() {
						public void run() {
							try {
								if(id == -1)
									id = Database.insertExamination(animalID,new java.sql.Date( ((Date)spDate.getValue()).getTime()), tPBefund.getText().toString());
								else
									Database.updateExamination(id,new java.sql.Date( ((Date)spDate.getValue()).getTime()), tPBefund.getText().toString());
								editable = false;
								setEditable();
							} catch (SQLException e) {
								GUIManager.showDBErrorDialog(null, Database.DBExceptionConverter(e, true));
							}
							setGlassPaneVisible(false);
						}}
					);
					t.start();
				}
			});
		} else {
			GUIManager.showFieldErrorDialog(getFrame());
		}
	}
	
	private void loadData(){
		if(id != -1){
			try {
				ResultSet rs = Database.getExamination(id);
				rs.next();
				spDate.setValue(rs.getDate(1));
				tPBefund.setText(rs.getString(2));
				editable = false;
				setEditable();
			} catch (SQLException e) {
				GUIManager.showDBErrorDialog(null, Database.DBExceptionConverter(e, true));
			}
		}
	}
}
