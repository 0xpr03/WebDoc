/*******************************************************************************
 * Copyright (c) 2015 by the WebDoc group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the WebDoc license.
 * Available inside this binary and at http://webdoc.proctet.net/license.txt
 *******************************************************************************/
package webdoc.gui;

import java.sql.SQLException;

import javax.swing.ActionMap;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import net.miginfocom.swing.MigLayout;
import webdoc.lib.GUIManager;
import java.awt.Dimension;
import javax.swing.JList;
import java.awt.BorderLayout;

public class WVerwaltung extends JInternalFrame {

	private static final long serialVersionUID = 4589284070560679651L;
	private boolean editable;
	private long id;
	private JList list;
	private JButton btnSchliesen;
	private JButton btnNeueBehandlungsart;
	public WVerwaltung() {
		setSize(new Dimension(329, 543));
		setClosable(true);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new MigLayout("", "[][][][]", "[]"));
		
		btnSchliesen = new JButton("Schließen");
		panel.add(btnSchliesen, "cell 1 0");
		
		btnNeueBehandlungsart = new JButton("Neue Behandlungsart");
		panel.add(btnNeueBehandlungsart, "cell 3 0");
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		
		list = new JList();
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(1)
					.addComponent(list, GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
					.addGap(1))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
					.addGap(1)
					.addComponent(list, GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
					.addGap(1))
		);
		panel_1.setLayout(gl_panel_1);
		
		setEditable(editable);
	}
	private void setEditable(boolean editable) {
		refreshBtn();
		
	}
	private void refreshBtn() {
		
	}

	private boolean allSet(){
	   
		return true;
	}
	
	private void loadData() throws SQLException{
	}
	private void setData(String name, double preis, String erklaerung){
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