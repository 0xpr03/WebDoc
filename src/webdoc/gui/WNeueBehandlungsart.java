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
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ActionMap;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import net.miginfocom.swing.MigLayout;
import webdoc.gui.utils.WModelPane;
import webdoc.lib.Database;
import webdoc.lib.GUIManager;

public class WNeueBehandlungsart extends WModelPane {

	private static final long serialVersionUID = 4589284070560679651L;
	private JTextField txtBezeichnung;
	private boolean editable;
	private JScrollPane sPErklaerung;
	private JSpinner spPreis;
	private JTextPane tPErklaerung;
	private long id;
	private JPanel panel_2;
	private JButton btnSave;
	private JButton btnEdit;
	public WNeueBehandlungsart(long in_id) {
		setResizable(true);
		this.id = in_id;
		editable = id == -1 ? true : false;
		setSize(new Dimension(450, 249));
		setClosable(true);
		setTitle("Behandlungsart");
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		
		JLabel lblPreisProEinheit = new JLabel("Preis pro Einheit in €:");
		
		JLabel lblBezeichnung = new JLabel("Bezeichnung:");
		
		JLabel lblErklrung = new JLabel("Erklärung:");
		
		sPErklaerung = new JScrollPane();
		
		tPErklaerung = new JTextPane();
		sPErklaerung.setViewportView(tPErklaerung);
		spPreis = new JSpinner();
		spPreis.setModel(new SpinnerNumberModel(new Double(0), null, null, new Double(1)));
		
		txtBezeichnung = new JTextField();
		txtBezeichnung.setColumns(1);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(2)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblBezeichnung)
							.addGap(41)
							.addComponent(txtBezeichnung, GroupLayout.PREFERRED_SIZE, 314, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblPreisProEinheit)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(spPreis, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblErklrung)
							.addGap(56)
							.addComponent(sPErklaerung, GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)))
					.addGap(13))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(3)
							.addComponent(lblBezeichnung))
						.addComponent(txtBezeichnung, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(9)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPreisProEinheit)
						.addComponent(spPreis, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblErklrung)
						.addComponent(sPErklaerung, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
					.addGap(3))
		);
		panel_1.setLayout(gl_panel_1);
		getContentPane().add(panel_1);
		
		panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new MigLayout("", "[][][][]", "[]"));
		
		btnSave = new JButton("New button");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(editable)
					entryData();
				else
					dispose();
			}
		});
		panel_2.add(btnSave, "cell 1 0");
		
		btnEdit = new JButton("New button");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(editable){
					if(id == -1)
						dispose();
					else{
						if (GUIFunctions.showIgnoreChangesDialog(getFrame()) == 0) {
							editable = false;
							setEditable();
							loadData();
						}
					}
				}else{
					editable = true;
					setEditable();
				}
			}
		});
		panel_2.add(btnEdit, "cell 3 0");
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtBezeichnung, spPreis, tPErklaerung, btnEdit, btnSave}));
		setEditable();
		loadData();
	}
	
	private void setEditable() {
		txtBezeichnung.setEditable(editable);
		spPreis.setEnabled(editable);
		tPErklaerung.setEnabled(editable);
		refreshBtn();
	}
	
	private void refreshBtn() {
		btnSave.setText(editable ? "Speichern" : "Schließen");
		btnEdit.setText(editable ? "Abbrechen" : "Editieren");
	}

	private boolean allSet(){
	    if (((double)spPreis.getValue()) == 0.0){
	    	if(GUIManager.showYesNoDialog(this, "Ohne Preis anlegen ?", JOptionPane.INFORMATION_MESSAGE, "Behandlung anlegen") == 1)
	    		return false;
	    }
        if (txtBezeichnung.getText() == "")
            return false;
        if (tPErklaerung.getText().toString().length() > 20)
        	return false;
		return true;
	}
	
	private void loadData(){
		if(id != -1){
			setGlassPaneVisible(true);
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					Thread t = new Thread(new Runnable() {
						public void run() {
							try {
								ResultSet rs = Database.getTreatment(id);
								rs.next();
								txtBezeichnung.setText(rs.getString(1));
								spPreis.setValue(rs.getInt(2));
								tPErklaerung.setText(rs.getString(3));
								
								editable = false;
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
	
	private void entryData(){
		if (allSet()) {
			setGlassPaneVisible(true);
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					Thread t = new Thread(new Runnable() {
						public void run() {
							try {
								// @formatter:off
								if(id == -1)
									id = Database.insertTreatment(txtBezeichnung.getText(), (double) spPreis.getValue(), tPErklaerung.getText());
								else
									id = Database.updateTreatment(id, txtBezeichnung.getText(), (double) spPreis.getValue(), tPErklaerung.getText());
								
								editable = false;
								setEditable();
								// @formatter:on
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
}