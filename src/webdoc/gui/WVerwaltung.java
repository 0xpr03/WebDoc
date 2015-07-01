/*******************************************************************************
 * Copyright (c) 2015 by the WebDoc group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the WebDoc license.
 * Available inside this binary and at http://webdoc.proctet.net/license.txt
 *******************************************************************************/
package webdoc.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ActionMap;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.TableRowSorter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import net.miginfocom.swing.MigLayout;
import webdoc.gui.utils.AdminTableModel;
import webdoc.gui.utils.EnumObject;
import webdoc.gui.utils.EnumObject.EnumType;
import webdoc.gui.utils.JSearchTextField;
import webdoc.gui.utils.PatientTableModel;
import webdoc.gui.utils.TDListElement;
import webdoc.gui.utils.TDListElement.LEType;
import webdoc.gui.utils.WModelPane;
import webdoc.lib.Database;
import webdoc.lib.GUIManager;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;

public class WVerwaltung extends WModelPane {

	private static final long serialVersionUID = 4589284070560679651L;
	private boolean editable;
	private JTable table;
	private AdminTableModel model = new AdminTableModel(EnumType.A);
	private JButton btnSchliesen;
	private JButton btnNeueBehandlungsart;
	private JPanel panel_2;
	private JComboBox<EnumObject> cBAuswahl;
	private JSearchTextField searchTextField;
	private Logger logger = LogManager.getLogger();
	
	public WVerwaltung() {
		super(serialVersionUID);
		setTitle("DB Admin Panel");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		setBounds(0, 0, 329, 543);
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
		
		table = new JTable(model);
		table.setShowGrid(false);
		table.setShowHorizontalLines(false);
		table.setShowVerticalLines(false);
		table.setRowMargin(0);
		table.setIntercellSpacing(new Dimension(0, 0));
		table.setFillsViewportHeight(true);
		TableRowSorter<AdminTableModel> sorter = new TableRowSorter<AdminTableModel>(
				model);
		table.setRowSorter(sorter);
		JScrollPane scrollPaneTable = new JScrollPane(table);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(1)
					.addComponent(scrollPaneTable, GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
					.addGap(1))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
					.addGap(1)
					.addComponent(scrollPaneTable, GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
					.addGap(1))
		);
		panel_1.setLayout(gl_panel_1);
		
		panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.NORTH);
		
		searchTextField = new JSearchTextField(false);
		
		cBAuswahl = new JComboBox<EnumObject>();
		class ItemChangeListener implements ItemListener {
			@Override
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					//loadData();
					model.setTableType(getTableType());
				}
			}
		}
		cBAuswahl.addItemListener(new ItemChangeListener());
		cBAuswahl.setModel((new DefaultComboBoxModel<EnumObject>(new EnumObject[] {new EnumObject("Behandlungsarten", EnumType.A), new EnumObject("Patienten", EnumType.B), new EnumObject("Partner", EnumType.C)})));
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(1)
					.addComponent(cBAuswahl, 0, 113, Short.MAX_VALUE)
					.addGap(1)
					.addComponent(searchTextField, GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(1)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE, false)
						.addComponent(searchTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cBAuswahl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(1))
		);
		panel_2.setLayout(gl_panel_2);
		
		setEditable(editable);
	}
	private void setEditable(boolean editable) {
		refreshBtn();
		
	}
	private void refreshBtn() {
		if (cBAuswahl.getSelectedItem().toString() == "Patienten"){
			btnNeueBehandlungsart.setText("Neuer Patient");
		}
		if (cBAuswahl.getSelectedItem().toString() == "Behandlungsarten"){
			btnNeueBehandlungsart.setText("Neue Behandlungsart");
		}
		if (cBAuswahl.getSelectedItem().toString() == "Partner"){
			btnNeueBehandlungsart.setText("Neuer Partner");
		}
	}
	
	private EnumType getTableType(){
		return ((EnumObject) cBAuswahl.getSelectedItem()).getType();
	}

	private void loadData() {
		setGlassPaneVisible(true);
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Thread t = new Thread(new Runnable() {
					public void run() {
						try{
							ResultSet rs = Database.getTableEntry(getTableType());
							if(rs != null){
								try{
									DefaultListModel<TDListElement> model = (DefaultListModel<TDListElement>) table.getModel();
									model.clear();
									while(rs.next()){
										model.addElement(new TDListElement(rs.getLong(1), rs.getString(2)));
									}
									rs.close();
								}catch(SQLException e){
									GUIManager.showDBErrorDialog(getFrame(), Database.DBExceptionConverter(e, true));
								}
							}
						}catch(SQLException e){
							GUIManager.showDBErrorDialog(getFrame(), Database.DBExceptionConverter(e, true));
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
		((ActionMap) UIManager.getLookAndFeelDefaults().get("InternalFrame.actionMap")).remove("showSystemMenu");
		super.dispose();
		GUIManager.dropJID(this);
	}
}