/*******************************************************************************
 * Copyright (c) 2015 by the WebDoc group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the WebDoc license.
 * Available inside this binary and at http://webdoc.proctet.net/license.txt
 *******************************************************************************/
package webdoc.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import net.miginfocom.swing.MigLayout;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import webdoc.gui.utils.DisabledGlassPane;
import webdoc.lib.Database;
import webdoc.lib.GUIManager;
import webdoc.lib.Database.DBError;
import webdoc.webdoc.Config;

/**
 * Setup window
 * @author "Aron Heinecke"
 */
public class WDBConnect extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9180933401774432315L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtIP;
	private JTextField txtPort;
	private JTextField txtDB;
	private JTextField txtUser;
	private JPasswordField txtPassword;
	private Logger logger = LogManager.getLogger();
	private JButton okButton;
	private JButton cancelButton;
	private JProgressBar progressBar;
	private JCheckBox chckbxErstelleNutzer;
	private boolean createDB = false;
	private WDBConnect parent;
	private JCheckBox chckbxOverwriteTables;
	private DisabledGlassPane glassPane = new DisabledGlassPane();
	private JCheckBox chckbxCreateDB;
	private JTextField txtZugriffsIPs;
	private JPanel panel_1;
	private JTextField txtAdminUser;
	private JPasswordField txtAdminPW;
	private JCheckBox chckbxAdv;
	
	/**
	 * Create the dialog.
	 * @wbp.parser.constructor
	 */
	public WDBConnect(String db,String ip,int port,String user,String password) {
		setLocationByPlatform(true);
		parent = this;
		setTitle("WebDoc Setup");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				exit();
			}
		});
		setModal(true);
		setBounds(100, 100, 458, 434);
		setLocationRelativeTo(null); // center it
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setFocusable(false);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel lblNetterInfotext = new JLabel("<html>Bitte geben Sie die Logindaten und die Datenbank an.<br><br>\r\n\r\nNutzer & DB selbst erstellen: (bei Erstinstallation) Hier muss unter User und Password ein Nutzer mit höheren Rechten angegeben werden (z.B.: root ).<br>\r\nWebDoc wird sich dann den Nutzer selbst erstellen & gibt die Logindaten nach erfolgreicher Konfiguration aus.");
		lblNetterInfotext.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNetterInfotext.setRequestFocusEnabled(false);
		lblNetterInfotext.setVerifyInputWhenFocusTarget(false);
		setGlassPane(glassPane);
		
		JPanel panel = new JPanel();
		
		JLabel lblNewLabel = new JLabel("<html>Willkommen beim Setup für den Datenbankserver</html>");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setHorizontalTextPosition(SwingConstants.LEADING);
		
		panel_1 = new JPanel();
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNetterInfotext, GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE))
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNetterInfotext, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					.addGap(0, 0, Short.MAX_VALUE))
		);
		
		chckbxCreateDB = new JCheckBox("Erstelle DB");
		chckbxCreateDB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adminInputChange();
			}
		});
		
		JLabel lblZugriffsIp = new JLabel("Zugriffs IP:");
		
		txtZugriffsIPs = new JTextField();
		txtZugriffsIPs.setToolTipText("IPs die sich mit dem Nutzer einloggen dürfen. % = wildcard");
		txtZugriffsIPs.setEnabled(false);
		txtZugriffsIPs.setText("%");
		txtZugriffsIPs.setColumns(10);
		//		txtPassword.getDocument().addDocumentListener(new TextDocListener()); DEBUG
				
				chckbxErstelleNutzer = new JCheckBox("Erstelle Nutzer (überscheibend)");
				chckbxErstelleNutzer.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						adminInputChange();
					}
				});
		
		chckbxOverwriteTables = new JCheckBox("Überschreibe Tabellen");
		chckbxOverwriteTables.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				adminInputChange();
			}
		});
		
		JLabel lblAdminUser = new JLabel("Admin User:");
		
		txtAdminUser = new JTextField();
		txtAdminUser.setEnabled(false);
		txtAdminUser.setColumns(10);
		
		JLabel lblAdminPw = new JLabel("Admin PW");
		
		txtAdminPW = new JPasswordField();
		txtAdminPW.setEnabled(false);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblAdminUser)
						.addComponent(lblZugriffsIp)
						.addComponent(chckbxCreateDB))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
						.addComponent(txtAdminUser)
						.addComponent(txtZugriffsIPs, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(chckbxErstelleNutzer)
						.addComponent(chckbxOverwriteTables)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblAdminPw)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtAdminPW, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(38, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxOverwriteTables)
						.addComponent(chckbxCreateDB))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblZugriffsIp)
						.addComponent(chckbxErstelleNutzer)
						.addComponent(txtZugriffsIPs, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAdminUser)
						.addComponent(txtAdminUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAdminPw)
						.addComponent(txtAdminPW, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		JLabel lblIp = new JLabel("IP:");
		
		class TextDocListener implements DocumentListener {
		    public void insertUpdate(DocumentEvent e) {
		        updateLog(e);
		    }
		    public void removeUpdate(DocumentEvent e) {
		        updateLog(e);
		    }
		    public void changedUpdate(DocumentEvent e) {
		        //Plain text components do not fire these events
		    }

		    public void updateLog(DocumentEvent e) {
		        Document doc = (Document)e.getDocument();
		        okButton.setEnabled(doc.getLength() > 0);
		    }
		}
		
		class IntDocListener implements DocumentListener {
		    public void insertUpdate(DocumentEvent e) {
		        updateLog(e);
		    }
		    public void removeUpdate(DocumentEvent e) {
		        updateLog(e);
		    }
		    public void changedUpdate(DocumentEvent e) {
		        //Plain text components do not fire these events
		    }

		    public void updateLog(DocumentEvent de) {
		        Document doc = (Document)de.getDocument();
		        try {
					Integer.valueOf(doc.getText(0, doc.getLength()));
					okButton.setEnabled(true);
				} catch (NumberFormatException | BadLocationException e) {
					okButton.setEnabled(false);
				}
		    }
		}
		
		txtIP = new JTextField();
		txtIP.setText(ip);
		txtIP.setColumns(10);
		txtIP.getDocument().addDocumentListener(new TextDocListener());
		
		JLabel lblPort = new JLabel("Port:");
		
		txtPort = new JTextField();
		txtPort.setText(String.valueOf(port));
		txtPort.getDocument().addDocumentListener(new IntDocListener());
		
		JLabel lblDb = new JLabel("DB:");
		
		txtDB = new JTextField();
		txtDB.setBackground(Color.WHITE);
		txtDB.setText(db);
		txtDB.setColumns(10);
		txtDB.getDocument().addDocumentListener(new TextDocListener());
		
		JLabel lblUser = new JLabel("User:");
		
		JLabel lblPassword = new JLabel("Password:");
		
		txtUser = new JTextField();
		txtUser.setText(user);
		txtUser.setColumns(10);
		txtUser.getDocument().addDocumentListener(new TextDocListener());
		
		txtPassword = new JPasswordField();
		txtPassword.setText(password);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblIp)
								.addComponent(lblPort))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(txtIP, GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
								.addComponent(txtPort, GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblDb)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtDB, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)))
					.addGap(58)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblUser)
						.addComponent(lblPassword))
					.addGap(10)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(txtPassword)
						.addComponent(txtUser, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE))
					.addGap(29))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblUser)
								.addComponent(txtUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPassword)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblIp)
								.addComponent(txtIP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(txtPort, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPort))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblDb)
								.addComponent(txtDB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(95, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setMinimumSize(new Dimension(50, 50));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			okButton = new JButton("OK");
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					okAction();
				}
			});
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
			{
				cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						exit();
					}
				});
				cancelButton.setActionCommand("Cancel");
			}
			
			buttonPane.setLayout(new MigLayout("", "[146px][75px,right][75px,right]", "[23px]"));
			
			progressBar = new JProgressBar();
			progressBar.setVisible(false);
			
			progressBar.setIndeterminate(true);
			glassPane.add(progressBar);
			
			chckbxAdv = new JCheckBox("Adv Settings");
			chckbxAdv.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					advSettingsChange(parent,chckbxAdv.isSelected());
				}
			});
			buttonPane.add(chckbxAdv, "cell 0 0");
			buttonPane.add(okButton, "cell 1 0,growx,aligny center");
			buttonPane.add(cancelButton, "cell 2 0,growx,aligny center");
			buttonPane.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{okButton, cancelButton}));
			logger.debug("Size: {}",panel_1.getSize().getHeight());
			advSettingsChange(this,false);
		}
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtIP, txtPort, txtDB, txtUser, txtPassword, chckbxAdv, chckbxCreateDB, chckbxOverwriteTables, chckbxErstelleNutzer, txtZugriffsIPs, txtAdminUser, txtAdminPW, okButton, cancelButton}));
	}
	
	private void adminInputChange(){
		boolean state = chckbxErstelleNutzer.isSelected() || chckbxCreateDB.isSelected() || chckbxOverwriteTables.isSelected() ? true : false;
		txtAdminUser.setEnabled(state);
		txtAdminPW.setEnabled(state);
		txtZugriffsIPs.setEnabled(chckbxErstelleNutzer.isSelected());
	}
	
	private void advSettingsChange(WDBConnect wdbc,boolean selected) {
		panel_1.setVisible(selected);
		this.pack();
	}
	
	private void exit(){
		if(GUIManager.showErrorYesNoDialog("Wollen Sie das Programm beenden ?", "Beenden")==0){
			logger.debug("Exiting..");
			System.exit(0);
		}
	}
	
	public void close() {
	    this.dispose();
	}
	
	/**
	 * Main runner, call this function
	 */
	public WDBConnect(String db,String ip,int port,String user,String password, boolean main){
		WDBConnect dialog = new WDBConnect(db,ip,port,user,password);
		dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		dialog.setVisible(true);
	}
	
	/**
	 * Action performed on "ok" button press
	 * @author "Aron Heinecke"
	 */
	private void okAction() {
		progressBar.setVisible(true);
		setDisabledLayer(true);
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Thread t = new Thread(new Runnable() {
					public void run() {
						Config.setValue("createDB", chckbxCreateDB.isSelected());
						Config.setValue("createUser", chckbxErstelleNutzer.isSelected());
						Config.setValue("overwriteDB", chckbxOverwriteTables.isSelected());
						if(Config.getBoolValue("createUser"))
							Config.setValue("userIPs", txtZugriffsIPs.getText());
						if(Config.getBoolValue("createDB") || Config.getBoolValue("createUser") || Config.getBoolValue("overwriteDB")){
							Config.setValue("r_user", txtAdminUser.getText());
							Config.setValue("r_password", String.valueOf(txtAdminPW.getPassword()));
						}
						logger.debug("crateDB: {}", createDB);
						Config.setValue("user", txtUser.getText());
						Config.setValue("password", String.valueOf(txtPassword.getPassword()));
						Config.setValue("ip", txtIP.getText());
						Config.setIntValue("port", txtPort.getText());
						Config.setValue("db", txtDB.getText());
						
						DBError success = Database.connect(!Config.getBoolValue("createDB"), Config.getBoolValue("createDB") || Config.getBoolValue("createUser") || Config.getBoolValue("overwriteDB"));
						logger.debug("DB connection return: {}", success);
						progressBar.setVisible(false);
						setDisabledLayer(false);
						switch (success) {
						case INVALID_LOGIN:
							GUIManager.showErrorDialog(parent, "Die Logindaten stimmen nicht!", "Fehler beim Verbinden");
							break;
						case NOCONNECTION:
							GUIManager.showErrorDialog(parent, "Die IP und/oder der Port stimmen nicht!", "Fehler beim Verbinden");
							break;
						case NOERROR:
							if(Config.getBoolValue("overwriteDB")){
								if(GUIManager.showErrorYesNoDialog(parent, "Wollen Sie wirklich alle Datensätze überschreiben ?\nDies führt zum Verlust aller bestehenden Daten!", "Tabellen überschreiben..") == 1){
									break;
								}
							}
							close();
							break;
						case NO_DB_OR_NO_PERM:
							GUIManager.showErrorDialog(parent, "Die angegebene Datendank existiert nicht\noder dem Benutzer fehlen die Zugriffsrechte!", "Fehler beim Verbinden");
							break;
						default:
							break;
						}
					}
				});
				t.start();
			}
		});

	}
	
	private void setDisabledLayer(boolean visible){
		if(visible){
			glassPane.activate(null);
		}else {
			glassPane.deactivate();
		}
	}
}