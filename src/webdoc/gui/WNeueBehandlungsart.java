/*******************************************************************************
 * Copyright (c) 2015 by the WebDoc group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the WebDoc license.
 * Available inside this binary and at http://webdoc.proctet.net/license.txt
 *******************************************************************************/
package webdoc.gui;

import java.awt.Rectangle;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import net.miginfocom.swing.MigLayout;
import webdoc.gui.utils.JSearchTextField;

@SuppressWarnings("serial")
public class WNeueBehandlungsart extends JInternalFrame {
	private JTextField textField;
	private boolean editable;
	private JScrollPane scrollPane;
	private JSpinner spinner;
	private JSearchTextField searchTextField;
	private JButton btnSpeichern;
	private JButton btnEditieren;
	public WNeueBehandlungsart() {
		
		JPanel panel = new JPanel();
		panel.setBounds(new Rectangle(1, 1, 1, 1));
		panel.setLayout(new MigLayout("", "[][][]", "[]"));
		
		searchTextField = new JSearchTextField();
		
		JLabel lblBezeichnung = new JLabel("Bezeichnung:");
		
		JLabel lblPreisProEinheit = new JLabel("Preis pro Einheit:");
		
		JLabel lblErklrung = new JLabel("Erklärung:");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		spinner = new JSpinner();
		
		scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 318, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(searchTextField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblPreisProEinheit)
										.addComponent(lblBezeichnung)
										.addComponent(lblErklrung))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
											.addComponent(textField, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addComponent(spinner, Alignment.LEADING)))))))
					.addContainerGap(178, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(searchTextField, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addGap(28)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBezeichnung)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPreisProEinheit)
						.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblErklrung)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
		);
		
		JTextPane textPane = new JTextPane();
		scrollPane.setViewportView(textPane);
		
		btnSpeichern = new JButton("Speichern");
		panel.add(btnSpeichern, "cell 1 0");
		
		btnEditieren = new JButton("Editieren");
		panel.add(btnEditieren, "cell 2 0");
		getContentPane().setLayout(groupLayout);
		
		
		////////////////////////////////////////////
		setEditable(editable);
	}
	private void setEditable(boolean editable) {
		textField.setEditable(editable);
		spinner.setEnabled(editable);
		scrollPane.setEnabled(editable);
		refreshBtn();
		
	}
	private void refreshBtn() {
		btnSpeichern.setText(editable ? "Speichern" : "Schließen");
		btnEditieren.setEnabled(!editable);
		
	}
}
