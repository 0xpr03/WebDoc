/*******************************************************************************
 * Copyright (c) 2015 by the WebDoc group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the WebDoc license.
 * Available inside this binary and at http://webdoc.proctet.net/license.txt
 *******************************************************************************/
package webdoc.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ActionMap;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.TableRowSorter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.miginfocom.swing.MigLayout;
import webdoc.gui.utils.AdminTableModel;
import webdoc.gui.utils.EnumObject;
import webdoc.gui.utils.EnumObject.EnumType;
import webdoc.gui.utils.TDListElement;
import webdoc.gui.utils.WModelPane;
import webdoc.lib.Database;
import webdoc.lib.GUIManager;

/**
 * WVerwaltung Pane
 * Table-Lookup Window
 * @author Aron
 */
public class WVerwaltung extends WModelPane {

	private static final long serialVersionUID = 4589284070560679651L;
	private JTable table;
	private AdminTableModel model = new AdminTableModel(EnumType.A);
	private JButton btnSchliesen;
	private JButton btnNeuerEintrag;
	private JPanel panel_2;
	private JComboBox<EnumObject> cBAuswahl;
	private JTextField searchTextField;
	private Logger logger = LogManager.getLogger();
	
	public WVerwaltung() {
		super(serialVersionUID);
		setMaximizable(true);
		setResizable(true);
		setTitle("DB Admin Panel");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		setBounds(0, 0, 341, 444);
		setClosable(true);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new MigLayout("", "[][][][]", "[]"));
		
		btnSchliesen = new JButton("Suchen");
		btnSchliesen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				loadData();
			}
		});
		panel.add(btnSchliesen, "cell 1 0");
		
		btnNeuerEintrag = new JButton("Neue Behandlungsart");
		btnNeuerEintrag.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				callExternalWindows(-1);
			}
		});
		panel.add(btnNeuerEintrag, "cell 3 0");
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		
		table = new JTable(model);
		table.setShowGrid(false);
		table.setShowHorizontalLines(false);
		table.setShowVerticalLines(false);
		table.setRowMargin(0);
		table.setIntercellSpacing(new Dimension(0, 0));
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		TableRowSorter<AdminTableModel> sorter = new TableRowSorter<AdminTableModel>(
				model);
		table.setRowSorter(sorter);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent mevent) {
				// @formatter:off
				if (mevent.getButton() == MouseEvent.BUTTON1 && table.getSelectedRow() != -1) {
					if (mevent.getClickCount() >= 2) {
						TDListElement elem = model.getTDLEAt(table.getSelectedRow());
						callExternalWindows(elem.getID());
					}
				}
				// @formatter:on
			}
		});
		
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
		
		searchTextField = new JTextField();
		
		cBAuswahl = new JComboBox<EnumObject>();
		class ItemChangeListener implements ItemListener {
			@Override
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					model.setTableType(getTableType());
					loadData();
					refreshBtn();
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
		
		refreshBtn();
	}
	
	private void refreshBtn() {
		switch(getTableType()){
		case A:
			btnNeuerEintrag.setText("Neue Behandlung");
			break;
		case B:
			btnNeuerEintrag.setText("Neuer Patient");
			break;
		case C:
			btnNeuerEintrag.setText("Neuer Partner");
			break;
		default:
			logger.error("UNDEFINED element!");
			break;
		}
	}
	
	private void callExternalWindows(long id){
		boolean editable = id == -1;
		switch(getTableType()){
		case A:
			GUIManager.callWNeueBehandlungsArt(id);
			break;
		case B:
			GUIManager.callWNeuerPatient(editable, id);
			break;
		case C:
			GUIManager.callWNewPartner(editable,id);
			break;
		default:
			logger.error("UNDEFINED element!");
			break;
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
					@SuppressWarnings("incomplete-switch")
					public void run() {
						try{
							
							EnumType type = getTableType();
							logger.debug("Type: {}",type);
							ResultSet rs = Database.getTableEntry(type,"%"+searchTextField.getText()+"%");
							if(rs != null){
								model.clearElements();
								while(rs.next()){
									switch(type){
									case A:
										model.add(new TDListElement(rs.getLong(1), rs.getString(2), rs.getString(3)));
										break;
									case B:
									case C:
										model.add(new TDListElement(rs.getLong(1), rs.getString(2),rs.getString(3),rs.getDate(4)));
										break;
									}
								}
								rs.close();
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