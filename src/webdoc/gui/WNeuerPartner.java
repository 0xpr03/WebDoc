package webdoc.gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import net.miginfocom.swing.MigLayout;

import javax.swing.JButton;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyVetoException;

public class WNeuerPartner extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2791732649836492001L;
	private Logger logger = LogManager.getLogger();
	private JTextField textName;
	private JTextField textTitel;
	private JTextField textField_8;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private boolean editable;
	private JTextPane textPaneBemerkungen;
	private JPanel rollendaten;
	private JSpinner gebJahr;
	private JSpinner gebMonat;
	private JSpinner gebTag;
	private JTextField textVorname;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WNeuerPartner window = new WNeuerPartner(true);
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WNeuerPartner(boolean editable) {
		this.editable = editable;
		initialize();
		setFrameIcon(null);
		setIconifiable(true);
		setResizable(true);
		setClosable(true);
		setMaximizable(true);
	}

	/**
	 * Initialize the contents of the 
	 */
	private void initialize() {
		setTitle(editable ? "Neuer Patner" : "Patner");
		setBounds(100, 100, 610, 543);
		
		JPanel personenbezogeneDaten = new JPanel();
		
		JLabel labelBemerkungen = new JLabel("Bemerkungen:");
		
		JScrollPane scrollPane = new JScrollPane();
		
		rollendaten = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(personenbezogeneDaten, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(labelBemerkungen, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(rollendaten, GroupLayout.PREFERRED_SIZE, 315, GroupLayout.PREFERRED_SIZE)
					.addGap(276))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(rollendaten, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 492, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(personenbezogeneDaten, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(labelBemerkungen)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)))
					.addGap(60))
		);
		
		textPaneBemerkungen = new JTextPane();
		textPaneBemerkungen.setEditable(editable);
		textPaneBemerkungen.setBackground(Color.WHITE);
		scrollPane.setViewportView(textPaneBemerkungen);
		rollendaten.setLayout(null);
		
		JMenuBar menuBar_1 = new JMenuBar();
		menuBar_1.setBounds(0, 0, 97, 21);
		rollendaten.add(menuBar_1);
		
		JMenu mnRolle = new JMenu("Rolle");
		menuBar_1.add(mnRolle);
		
		JCheckBoxMenuItem chckbxmntmPatientenbesitzer = new JCheckBoxMenuItem("Patientenbesitzer");
		mnRolle.add(chckbxmntmPatientenbesitzer);
		
		JCheckBoxMenuItem chckbxmntmArzt = new JCheckBoxMenuItem("Arzt");
		mnRolle.add(chckbxmntmArzt);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 32, 292, 401);
		rollendaten.add(panel_3);
		
		JLabel lblAdresse = new JLabel("Adresse");
		
		JLabel lblPostleitzahl = new JLabel("Postleitzahl:");
		
		JLabel lblOrt = new JLabel("Ort:");
		
		JLabel lblOrtsteil = new JLabel("Ortsteil:");
		
		JLabel lblStrae = new JLabel("Straße:");
		
		JLabel lblHausnummer = new JLabel("Hausnummer:");
		
		JLabel lblZusatz = new JLabel("Zusatz:");
		
		JSeparator separator = new JSeparator();
		
		JLabel lblKomunikation = new JLabel("Komunikation");
		
		JLabel lblTelefon = new JLabel("Telefon");
		
		JLabel lblHandy = new JLabel("Handy");
		
		JLabel lblFax = new JLabel("Fax");
		
		JLabel lblEmail = new JLabel("E-Mail");
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		
		textField_10 = new JTextField();
		textField_10.setColumns(10);
		
		textField_11 = new JTextField();
		textField_11.setColumns(10);
		
		textField_12 = new JTextField();
		textField_12.setColumns(10);
		textField_12.setEditable(editable);
		textField_11.setEditable(editable);
		textField_10.setEditable(editable);
		textField_9.setEditable(editable);
		textField_8.setEditable(editable);
		textField_7.setEditable(editable);
		textField_6.setEditable(editable);
		textField_5.setEditable(editable);
		textField_4.setEditable(editable);
		
		textField_13 = new JTextField();
		textField_13.setColumns(10);
		textField_13.setEditable(editable);
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel_3.createSequentialGroup()
								.addGap(112)
								.addComponent(lblAdresse))
							.addComponent(separator, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 282, GroupLayout.PREFERRED_SIZE)
							.addGroup(gl_panel_3.createSequentialGroup()
								.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
									.addComponent(lblHausnummer)
									.addComponent(lblPostleitzahl)
									.addComponent(lblOrt)
									.addComponent(lblOrtsteil)
									.addComponent(lblStrae)
									.addComponent(lblZusatz))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
									.addComponent(textField_9, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
									.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
									.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
									.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
									.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
									.addComponent(textField_8, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_panel_3.createSequentialGroup()
							.addGap(90)
							.addComponent(lblKomunikation))
						.addGroup(gl_panel_3.createSequentialGroup()
							.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
								.addComponent(lblTelefon)
								.addComponent(lblHandy)
								.addComponent(lblFax)
								.addComponent(lblEmail))
							.addGap(34)
							.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
								.addComponent(textField_13, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_12, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_11, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_10, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addComponent(lblAdresse)
					.addGap(9)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPostleitzahl)
						.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblOrt)
						.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblOrtsteil)
						.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStrae)
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblHausnummer)
						.addComponent(textField_8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblZusatz)
						.addComponent(textField_9, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(14)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblKomunikation)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTelefon)
						.addComponent(textField_10, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblHandy)
						.addComponent(textField_11, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFax)
						.addComponent(textField_12, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmail)
						.addComponent(textField_13, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(58))
		);
		panel_3.setLayout(gl_panel_3);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 444, 305, 35);
		rollendaten.add(panel);
		panel.setLayout(new MigLayout("", "[44.00][][][][]", "[]"));
		
		JButton button = new JButton("Ok");
		panel.add(button, "cell 2 0");
		
		JButton bCancel = new JButton("Cancel");
		bCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exit();
			}
		});
		panel.add(bCancel, "cell 4 0");
		bCancel.setVisible(editable);
		
		JLabel lblNewLabel = new JLabel("Name:");
		
		JLabel lblVorname = new JLabel("Vorname:");
		
		JLabel lblTitel = new JLabel("Titel:");
		
		textName = new JTextField();
		textName.setColumns(10);
		textName.setEditable(editable);
		
		textVorname = new JTextField();
		textVorname.setColumns(10);
		textVorname.setEditable(editable);
		
		textTitel = new JTextField();
		textTitel.setColumns(10);
		textTitel.setEditable(editable);
		
		JLabel lblGeburtsdatum = new JLabel("Geburtsdatum:");
		
		gebTag = new JSpinner();
		gebTag.setEnabled(editable);
		
		gebMonat = new JSpinner();
		gebMonat.setEnabled(editable);
		
		gebJahr = new JSpinner();
		gebJahr.setEnabled(editable);
		GroupLayout gl_personenbezogeneDaten = new GroupLayout(personenbezogeneDaten);
		gl_personenbezogeneDaten.setHorizontalGroup(
			gl_personenbezogeneDaten.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_personenbezogeneDaten.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_personenbezogeneDaten.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_personenbezogeneDaten.createSequentialGroup()
							.addGroup(gl_personenbezogeneDaten.createParallelGroup(Alignment.LEADING)
								.addComponent(lblVorname, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel)
								.addComponent(lblTitel))
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(gl_personenbezogeneDaten.createParallelGroup(Alignment.LEADING)
								.addComponent(textTitel, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_personenbezogeneDaten.createParallelGroup(Alignment.LEADING, false)
									.addComponent(textName)
									.addComponent(textVorname, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE))))
						.addGroup(gl_personenbezogeneDaten.createSequentialGroup()
							.addComponent(lblGeburtsdatum)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(gebTag, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(gebMonat, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(gebJahr, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_personenbezogeneDaten.setVerticalGroup(
			gl_personenbezogeneDaten.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_personenbezogeneDaten.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_personenbezogeneDaten.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(textName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_personenbezogeneDaten.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblVorname)
						.addComponent(textVorname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_personenbezogeneDaten.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTitel)
						.addComponent(textTitel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_personenbezogeneDaten.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblGeburtsdatum)
						.addGroup(gl_personenbezogeneDaten.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_personenbezogeneDaten.createParallelGroup(Alignment.BASELINE)
								.addComponent(gebMonat, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(gebTag, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addComponent(gebJahr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(23, Short.MAX_VALUE))
		);
		personenbezogeneDaten.setLayout(gl_personenbezogeneDaten);
		getContentPane().setLayout(groupLayout);
		
	}
	private void exit(){
		try {
			this.setClosed(true);
		} catch (PropertyVetoException e) {
			logger.info(e);
		}
	}
}
