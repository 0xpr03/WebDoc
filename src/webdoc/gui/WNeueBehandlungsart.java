/*******************************************************************************
 * Copyright (c) 2015 by the WebDoc group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the WebDoc license.
 * Available inside this binary and at http://webdoc.proctet.net/license.txt
 *******************************************************************************/
package webdoc.gui;

import java.awt.Rectangle;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ActionMap;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;

import net.miginfocom.swing.MigLayout;
import webdoc.gui.utils.JSearchTextField;
import webdoc.lib.Database;
import webdoc.lib.GUIManager;
import java.awt.ComponentOrientation;
import java.awt.Dimension;

public class WNeueBehandlungsart extends JInternalFrame {

	private static final long serialVersionUID = 4589284070560679651L;
	private JTextField txtBezeichnung;
	private boolean editable;
	private JScrollPane sPErklaerung;
	private JSpinner spPreis;
	private JSearchTextField searchTextField;
	private JButton btnSpeichern;
	private JButton btnEditieren;
	private JTextPane tPErklaerung;
	private long id;
	public WNeueBehandlungsart() {
		setSize(new Dimension(450, 304));
		setClosable(true);
		setTitle("Behandlungsart");
		JPanel panel = new JPanel();
		panel.setBounds(new Rectangle(1, 1, 1, 1));
		panel.setLayout(new MigLayout("", "[][][]", "[]"));
		searchTextField = new JSearchTextField(true);
		
		JLabel lblBezeichnung = new JLabel("Bezeichnung:");
		
		JLabel lblPreisProEinheit = new JLabel("Preis pro Einheit in €:");
		
		JLabel lblErklrung = new JLabel("Erklärung:");
		
		txtBezeichnung = new JTextField();
		txtBezeichnung.setColumns(1);
		spPreis = new JSpinner();
		
		sPErklaerung = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 318, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(searchTextField, GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblPreisProEinheit)
										.addComponent(lblBezeichnung)
										.addComponent(lblErklrung))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(sPErklaerung, GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
										.addComponent(spPreis, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtBezeichnung, GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE))))))
					.addGap(11))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(searchTextField, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addGap(28)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBezeichnung)
						.addComponent(txtBezeichnung, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPreisProEinheit)
						.addComponent(spPreis, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblErklrung)
						.addComponent(sPErklaerung, GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE))
					.addGap(12)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
		);
		
		tPErklaerung = new JTextPane();
		sPErklaerung.setViewportView(tPErklaerung);
		
		btnSpeichern = new JButton("Speichern");
		panel.add(btnSpeichern, "cell 1 0");
		spPreis.setModel(new SpinnerNumberModel(new Double(0), null, null, new Double(1)));
		btnEditieren = new JButton("Editieren");
		panel.add(btnEditieren, "cell 2 0");
		getContentPane().setLayout(groupLayout);
		
		setEditable(editable);
	}
	
	private void setEditable() {
		txtBezeichnung.setEditable(editable);
		spPreis.setEnabled(editable);
		sPErklaerung.setEnabled(editable);
		refreshBtn();
		
	}
	private void refreshBtn() {
		btnSpeichern.setText(editable ? "Speichern" : "Schließen");
		btnEditieren.setEnabled(!editable);
		
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
			try {
				ResultSet rs = Database.getThreatment(id);
				rs.next();
				txtBezeichnung.setText(rs.getString(1));
				spPreis.setValue(rs.getInt(2));
				tPErklaerung.setText(rs.getString(3));
				
				editable = false;
			} catch (SQLException e) {
				GUIManager.showDBErrorDialog(this, Database.DBExceptionConverter(e, true));
			}
		}
	}
	private void entryData(){
		if (allSet()) {
			try {
				Database.insertThreatment(name, preis, erklaerung);
			} catch (SQLException e) {
				GUIManager.showDBErrorDialog(this, Database.DBExceptionConverter(e, true));
			}
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