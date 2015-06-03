/*******************************************************************************
 * Copyright (c) 2015 by the WebDoc group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the WebDoc license.
 * Available inside this binary and at http://webdoc.proctet.net/license.txt
 *******************************************************************************/
package webdoc.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import webdoc.gui.utils.ACElement;
import webdoc.gui.utils.ACElement.ElementType;
import webdoc.gui.utils.CustomTreeObj;
import webdoc.gui.utils.JSearchTextField;
import webdoc.gui.utils.JSearchTextField.searchFieldAPI;
import webdoc.lib.Database;
import webdoc.lib.GUIManager;

/**
 * Mainframe of the GUI
 * @author "Aron Heinecke"
 */
public final class WHomescreen extends JFrame {

	private static final long serialVersionUID = 4091113544481728677L;
	private JSearchTextField txtSuche;
	private WNeuerPartner FNeuerPartner = new WNeuerPartner(true, -1);
	private WNeuerPatient FNeuerPatient = new WNeuerPatient(true, -1);
	private WNeueBehandlungsart FNeueBEhandlungsart =new WNeueBehandlungsart();
	private JTree navigationsbaum;
	private Logger logger = LogManager.getLogger();
	private JDesktopPane desktopPane;
	private PreparedStatement searchStm = null;
	private test FTest = new test();

	/**
	 * Create the application.
	 */
	public WHomescreen() {
		initialize();
		this.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setTitle("WebDoc Home");
		setBounds(100, 100, 1936, 980);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setBackground(Color.WHITE);
		
		JPanel navigation = new JPanel();
		splitPane.setLeftComponent(navigation);
		
		JPanel secPanel = new JPanel();
		splitPane.setRightComponent(secPanel);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(splitPane, GroupLayout.DEFAULT_SIZE, 1362, Short.MAX_VALUE)
					.addGap(0))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(splitPane, GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
					.addGap(1))
		);
		
		JPanel suchPanel = new JPanel();
		
		txtSuche = new JSearchTextField(false);
		//TODO: add placeholder function
		//txtSuche.setText("Suche"); WONT WORK ATM
		txtSuche.setColumns(10);
		txtSuche.clearOnFocus(true);
		GroupLayout gl_suchPanel = new GroupLayout(suchPanel);
		gl_suchPanel.setHorizontalGroup(
			gl_suchPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_suchPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(txtSuche, GroupLayout.DEFAULT_SIZE, 775, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_suchPanel.setVerticalGroup(
			gl_suchPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_suchPanel.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(txtSuche, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		suchPanel.setLayout(gl_suchPanel);
		
		JPanel desktopPanel = new JPanel();
		
		desktopPane = new JDesktopPane();
		FNeuerPartner.setLocation(86, 85);
		desktopPane.add(FNeuerPartner);
		desktopPane.add(FNeuerPatient);
		desktopPane.add(FTest);
		desktopPanel.add(FNeueBEhandlungsart);
		desktopPane.setBackground(Color.WHITE);
		GroupLayout gl_secPanel = new GroupLayout(secPanel);
		gl_secPanel.setHorizontalGroup(
			gl_secPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_secPanel.createSequentialGroup()
					.addGap(1)
					.addComponent(suchPanel, GroupLayout.DEFAULT_SIZE, 1205, Short.MAX_VALUE)
					.addGap(1))
				.addGroup(gl_secPanel.createSequentialGroup()
					.addGap(0)
					.addComponent(desktopPanel, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
					.addGap(0))
		);
		gl_secPanel.setVerticalGroup(
			gl_secPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_secPanel.createSequentialGroup()
					.addComponent(suchPanel, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addGap(0)
					.addComponent(desktopPanel, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
					.addGap(1))
		);
		GroupLayout gl_desktopPanel = new GroupLayout(desktopPanel);
		gl_desktopPanel.setHorizontalGroup(
			gl_desktopPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(desktopPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 1207, Short.MAX_VALUE)
		);
		gl_desktopPanel.setVerticalGroup(
			gl_desktopPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(desktopPane, GroupLayout.DEFAULT_SIZE, 645, Short.MAX_VALUE)
		);
		desktopPanel.setLayout(gl_desktopPanel);
		secPanel.setLayout(gl_secPanel);
		navigation.setLayout(new BorderLayout(0, 0));
		
		navigationsbaum = new JTree();
		navigationsbaum.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				mouseClickAction(arg0);
			}
		});
		navigationsbaum.setModel(GUIFunctions.Navi());
		navigation.add(navigationsbaum, BorderLayout.CENTER);
		getContentPane().setLayout(groupLayout);
		
		/**
		 * Default DataProvider for these kinds
		 * @author "Aron Heinecke"
		 */
		class Provider implements searchFieldAPI{
			@Override
			public List<ACElement> getData(String text){
				List<ACElement> list = new ArrayList<ACElement>();
				try {
					text = "%"+text+"%";
					searchStm.setString(1, text);
					searchStm.setString(2, text);
					searchStm.setString(3, text);
					searchStm.setString(4, text);
					ResultSet result = searchStm.executeQuery();
					
					while(result.next()){
						list.add(new ACElement(result.getString(1),result.getString(2),result.getLong(3), result.getInt(4) == 0 ? ElementType.ANIMAL : ElementType.PARTNER));
					}
					result.close();
					
				} catch (SQLException e) {
					GUIManager.showDBErrorDialog(null, Database.DBExceptionConverter(e,true));
				}
				return list;
			}

			@Override
			public boolean changedSelectionEvent(ACElement element) {
				logger.debug("Element chosen: {}",element);
				if(element != null){
					if(element.getType() == ElementType.ANIMAL){
						addWNeuerPatient(false, element.getID());
					}else{
						addWNeuerPartner(false, element.getID());
					}
					txtSuche.setTextWithoutNotification("");
					return true;
				}
				return false;
			}

			@Override
			public String listRenderer(ACElement element) {
				return "<html>" + element.getName() + " " + element.getOptname() + " <font size=-2  color=\""
						+ (element.getType() == ElementType.ANIMAL ? "black" : "blue")+"\"><i>"
						+ element.getType() + "</i></font></html>";
			}
		}
		
		txtSuche.setAPI(new Provider());
		txtSuche.setFont(GUIManager.getSearchMainFont());
		
		try {
			searchStm = Database.prepareMultiSearchStm();
		} catch (SQLException e) {
			GUIManager.showDBErrorDialog(this, Database.DBExceptionConverter(e,true));
		}
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnMenu = new JMenu("Menu");
		menuBar.add(mnMenu);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		mnMenu.add(mntmExit);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new WAbout();
			}
		});
		
		JMenuItem mntmPerformanceTest = new JMenuItem("Performance test");
		mntmPerformanceTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						Thread t = new Thread(new Runnable() {
							public void run() {
								GUIManager.closeMemoryTest();
							}
						});
						t.start();
					}
				});
			}
		});
		
		JMenuItem mntmFreeMemory = new JMenuItem("Free memory");
		mntmFreeMemory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				remAllDebug();
			}
		});
		mnHelp.add(mntmFreeMemory);
		mnHelp.add(mntmPerformanceTest);
		
		JSeparator separator = new JSeparator();
		mnHelp.add(separator);
		mnHelp.add(mntmAbout);
	}
	
	private void mouseClickAction(MouseEvent mevent){
		int row = navigationsbaum.getRowForLocation(mevent.getX(), mevent.getY());
		TreePath selPath = navigationsbaum.getPathForLocation(mevent.getX(), mevent.getY());
		if(row != -1){
			// detect doubleklick
			if(mevent.getClickCount() >= 2){
				CustomTreeObj selMenu = (CustomTreeObj) ((DefaultMutableTreeNode) selPath.getLastPathComponent()).getUserObject();
				logger.debug("menu: {}", selMenu.getType());
				switch(selMenu.getType()){
				case HAUPTMENÜ:
					//TODO: show main menu					
					break;
				case N_PATIENT:
					if(!jifToFront(FNeuerPatient)){
						FNeuerPatient = new WNeuerPatient(true, -1);
						FNeuerPatient.setVisible(true);
						desktopPane.add(FNeuerPatient);
						FNeuerPatient.toFront();
					}
					break;
				case N_PARTNER:
					if(!jifToFront(FNeuerPartner)){
						FNeuerPartner = new WNeuerPartner(true, -1);
						FNeuerPartner.setVisible(true);
						desktopPane.add(FNeuerPartner);
						FNeuerPartner.toFront();
					}
					break;
				case PARTNER:
					addWNeuerPartner(false, -1);
					//TODO GUIManager.addIFrame(GUIManager.getNewFrameId(),  new WNeuerPartner(false, -1));
					break;
				case PATIENT:
					addWNeuerPatient(false, -1);
					break;
				case N_BEHANDLUNGSART:
					if(!jifToFront(FNeueBEhandlungsart)){
						FNeueBEhandlungsart = new WNeueBehandlungsart();
						FNeueBEhandlungsart.setVisible(true);
						FNeueBEhandlungsart.getContentPane().add(FNeueBEhandlungsart);
					}
					break;
				case TEST:
					if(!jifToFront(FTest)){
						FTest = new test();
						FTest.setVisible(true);
						desktopPane.add(FTest);
					}
					break;
				case N_BEHANDLUNG:
					//TODO: show new behandlung
					break;
				default:
					
				}
			}
		}
	}

	/**
	 * Brings a JInternalFrame to front, else return false
	 * @param jif
	 * @return
	 */
	private boolean jifToFront(JInternalFrame jif){
		if(jif == null){
			return false;
		}
		if(jif.isDisplayable()){
			try {
				jif.setIcon(false);
			} catch (PropertyVetoException e) {
				logger.debug(e);
			}
			jif.setVisible(true);
			jif.toFront();
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public void dispose(){
		logger.debug("disposing main..");
		if(FNeuerPatient.isVisible()) FNeuerPatient.dispose();
		if(FNeuerPartner.isVisible()) FNeuerPartner.dispose();
		
		if(!FNeuerPatient.isVisible() && !FNeuerPartner.isVisible() ){
			logger.debug("all closed..");
			Database.closePStatement(searchStm);
			super.dispose();
			System.exit(1);
		}
	}
	
	private void remAllDebug(){
		for(JInternalFrame cmp: desktopPane.getAllFrames()){
			cmp.dispose();
		}
		
	}
	
	/**
	 * Creates a WNeuerPatient window
	 * @param editable
	 * @param id
	 */
	public void addWNeuerPatient(boolean editable, long id){
		addFrame(new WNeuerPatient(editable, id));
	}
	
	/**
	 * Creates a WNeuerPartner window
	 * @param editable
	 * @param id
	 */
	public void addWNeuerPartner(boolean editable, long id){
		addFrame(new WNeuerPartner(editable, id));
	}
	
	public void addWNewAnamnesis(boolean editable, long animal_id, long anamnesis_id, String name,String callname){
		addFrame(new WNeueAnamnese(editable, animal_id, anamnesis_id, name,callname));
	}
	
	/**
	 * removes the JInternalFrame from the desktopPane
	 * @param jif
	 */
	public void removeJIF(JInternalFrame jif){
		desktopPane.remove(jif);
	}
	
	private void addFrame(JInternalFrame frame){
		frame.setVisible(true);
		desktopPane.add(frame);
		frame.toFront();
		try {
			frame.setSelected(true);
		} catch (PropertyVetoException e) {
			logger.error(e);
		}
	}
	
	/**
	 * returns the stored internalframes of the desktop pane
	 * @return
	 */
	public JInternalFrame[] getJIFs(){
		return desktopPane.getAllFrames();
	}
	
	/**
	 * adds a JInternalFrame to the desktopPane
	 * @param jif
	 */
	public void addJIF(JInternalFrame jif){
		desktopPane.add(jif);
	}
}
