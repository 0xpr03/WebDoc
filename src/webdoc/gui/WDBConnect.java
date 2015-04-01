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

import webdoc.lib.Database;
import webdoc.lib.Database.DBError;
import webdoc.webdoc.Config;

/**
 * Setup window
 * @author "Aron Heinecke"
 *
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
	private JCheckBox chckbxUeberschreibeTabellen;
	private DisabledGlassPane glassPane = new DisabledGlassPane();
	
	/**
	 * Create the dialog.
	 * @wbp.parser.constructor
	 */
	public WDBConnect(String db,String ip,int port,String user,String password) {
		setLocationByPlatform(true);
		parent = this;
		setTitle("WebDoc Setup");
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				exit();
			}
		});
		setModal(true);
		setBounds(100, 100, 450, 333);
		setLocationRelativeTo(null); // center it
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setFocusable(false);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel lblNetterInfotext = new JLabel("<html>Bitte geben Sie die Logindaten und die Datenbank an.<br><br>\r\n\r\nNutzer & DB selbst erstellen: (bei erstinstallation) Hier muss unter User und Password ein Nutzer mit höheren Rechte angegeben werden (bpsw. root ).<br>\r\nWebDoc erzeugt sich dann den Nutzer selbst & gibt die Logindaten nach Durchfürhung der Konfiguration aus.");
		lblNetterInfotext.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNetterInfotext.setRequestFocusEnabled(false);
		lblNetterInfotext.setVerifyInputWhenFocusTarget(false);
		setGlassPane(glassPane);
		
		JPanel panel = new JPanel();
		
		JLabel lblNewLabel = new JLabel("<html>Willkommen beim Setup für den Datenbankserver</html>");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setHorizontalTextPosition(SwingConstants.LEADING);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(10)
							.addComponent(lblNetterInfotext, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE))
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNetterInfotext, GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
		);
		
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
//		txtPassword.getDocument().addDocumentListener(new TextDocListener()); DEBUG
		
		chckbxErstelleNutzer = new JCheckBox("Erstelle Nutzer & DB");
		
		chckbxUeberschreibeTabellen = new JCheckBox("Überschreibe Tabellen");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblIp)
								.addComponent(lblPort))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(txtIP, GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
								.addComponent(txtPort, GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblDb)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtDB, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)))
					.addGap(58)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(chckbxUeberschreibeTabellen)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
							.addGroup(gl_panel.createSequentialGroup()
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
									.addComponent(lblUser)
									.addComponent(lblPassword))
								.addGap(10)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
									.addComponent(txtPassword)
									.addComponent(txtUser, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)))
							.addComponent(chckbxErstelleNutzer)))
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
								.addComponent(lblPassword))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(chckbxErstelleNutzer)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(chckbxUeberschreibeTabellen))
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
					.addContainerGap(19, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		panel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtIP, txtPort, txtDB, txtUser, txtPassword, chckbxErstelleNutzer, chckbxUeberschreibeTabellen}));
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
			buttonPane.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{okButton, cancelButton}));
			{
				cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						exit();
					}
				});
				cancelButton.setActionCommand("Cancel");
			}
			
			progressBar = new JProgressBar();
			progressBar.setVisible(false);
			buttonPane.setLayout(new MigLayout("", "[146px][75px,right][75px,right]", "[23px]"));
			progressBar.setIndeterminate(true);
			glassPane.add(progressBar);
			buttonPane.add(okButton, "cell 1 0,growx,aligny center");
			buttonPane.add(cancelButton, "cell 2 0,growx,aligny center");
		}
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtIP, txtPort, txtDB, txtUser, txtPassword, chckbxErstelleNutzer, chckbxUeberschreibeTabellen, okButton, cancelButton}));
		
		
	}
	
	private void exit(){
		if(GUI.showErrorYesNoDialog("Wollen Sie das Programm beenden ?", "Beenden")==0){
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
	
	private void okAction() {
		progressBar.setVisible(true);
		setDisabledLayer(true);
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Thread t = new Thread(new Runnable() {
					public void run() {
						logger.entry();
						progressBar.setVisible(true);
						Config.setValue("createDB", chckbxErstelleNutzer.isSelected());
						Config.setValue("createUser", chckbxErstelleNutzer.isSelected());
						Config.setValue("overwriteDB", chckbxUeberschreibeTabellen.isSelected());
						logger.debug("crateDB: {}", createDB);
						Config.setValue("user", txtUser.getText());
						Config.setValue("password", String.valueOf(txtPassword.getPassword()));
						Config.setValue("ip", txtIP.getText());
						Config.setIntValue("port", txtPort.getText());
						Config.setValue("db", txtDB.getText());
						DBError success = Database.connect(!Config.getBoolValue("createDB"));
						logger.debug("DB connection return: {}", success);
						progressBar.setVisible(false);
						setDisabledLayer(false);
						switch (success) {
						case INVALID_LOGIN:
							GUI.showErrorDialog(parent, "Die Logindaten stimmen nicht!", "Fehler beim Verbinden");
							break;
						case NOCONNECTION:
							GUI.showErrorDialog(parent, "Die IP und/oder der Port stimmen nicht!", "Fehler beim Verbinden");
							break;
						case NOERROR:
							close();
							break;
						case NO_DB_OR_NO_PERM:
							GUI.showErrorDialog(parent, "Die angegebene Datendank existiert nicht\noder dem Benutzer fehlen die Zugriffsrechte!", "Fehler beim Verbinden");
							break;
						default:
							break;
						}
						logger.exit();
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