package webdoc.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.util.Calendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DateEditor;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerDateModel;

import net.miginfocom.swing.MigLayout;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import webdoc.gui.utils.RoleEnumObj;
import webdoc.gui.utils.RoleEnumObj.RoleType;
import webdoc.lib.Database;

public class WNeuerPartner extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2791732649836492001L;
	private Logger logger = LogManager.getLogger();
	private JTextField textName;
	private JTextField textTitel;
	private JTextField textHausnummer;
	private RoleEnumObj[] rolle_lokalisiert = {new RoleEnumObj("Bitte Auswählen", RoleType.UNKNOWN),new RoleEnumObj("Patientenbesitzer", RoleType.PETOWNER),new RoleEnumObj("Arzt", RoleType.MEDIC) };
	private JComboBox<RoleEnumObj> enumRole;
	private JTextField textStraße;
	private JTextField textOrtsteil;
	private JTextField textOrt;
	private JTextField textPostleitzahl;
	private JTextField textZusatz;
	private JTextField textTelefon;
	private JTextField textFax;
	private JTextField textEmail;
	private boolean editable;
	private JTextPane textPaneComment;
	private JPanel rollendaten;
	private JSpinner spinGebdatum;
	private JTextField textVorname;
	private JTextField textHandy;
	private DateEditor dateEditor;

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
		setBounds(100, 100, 909, 543);
		
		JPanel personenbezogeneDaten = new JPanel();
		
		JLabel labelBemerkungen = new JLabel("Bemerkungen:");
		
		JScrollPane scrollPane = new JScrollPane();
		
		rollendaten = new JPanel();
		
		JPanel pVerlauf = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(personenbezogeneDaten, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE)
						.addComponent(labelBemerkungen, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(rollendaten, GroupLayout.PREFERRED_SIZE, 315, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(pVerlauf, GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(pVerlauf, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)
						.addComponent(rollendaten, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 492, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(personenbezogeneDaten, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(labelBemerkungen)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)))
					.addGap(60))
		);
		pVerlauf.setVisible(!editable);
		
		JScrollPane sPaneVerlauf = new JScrollPane();
		
		
		JLabel label = new JLabel("Verlauf:");
		GroupLayout gl_pVerlauf = new GroupLayout(pVerlauf);
		gl_pVerlauf.setHorizontalGroup(
			gl_pVerlauf.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pVerlauf.createSequentialGroup()
					.addGroup(gl_pVerlauf.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pVerlauf.createSequentialGroup()
							.addContainerGap()
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
						.addComponent(sPaneVerlauf, GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE))
					.addGap(1))
		);
		gl_pVerlauf.setVerticalGroup(
			gl_pVerlauf.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pVerlauf.createSequentialGroup()
					.addComponent(label)
					.addGap(2)
					.addComponent(sPaneVerlauf, GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)
					.addGap(1))
		);
		
		JTextPane textPaneVerlauf = new JTextPane();
		textPaneVerlauf.setEditable(false);
		textPaneVerlauf.setBackground(Color.WHITE);
		sPaneVerlauf.setViewportView(textPaneVerlauf);
		pVerlauf.setLayout(gl_pVerlauf);
		
		textPaneComment = new JTextPane();
		
		textPaneComment.setBackground(Color.WHITE);
		scrollPane.setViewportView(textPaneComment);
		rollendaten.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 32, 292, 361);
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
		
		textHausnummer = new JTextField();
		textHausnummer.setColumns(10);
		
		textStraße = new JTextField();
		textStraße.setColumns(10);
		
		textOrtsteil = new JTextField();
		textOrtsteil.setColumns(10);
		
		textOrt = new JTextField();
		textOrt.setColumns(10);
		
		textPostleitzahl = new JTextField();
		textPostleitzahl.setColumns(10);
		
		textZusatz = new JTextField();
		textZusatz.setColumns(10);
		
		textTelefon = new JTextField();
		textTelefon.setColumns(10);
		
		textHandy = new JTextField();
		textHandy.setColumns(10);
		
		textFax = new JTextField();
		textFax.setColumns(10);
		
		
		textEmail = new JTextField();
		textEmail.setColumns(10);
		
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
									.addComponent(textZusatz, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
									.addComponent(textPostleitzahl, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
									.addComponent(textOrt, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
									.addComponent(textOrtsteil, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
									.addComponent(textStraße, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
									.addComponent(textHausnummer, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE))))
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
								.addComponent(textEmail, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFax, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
								.addComponent(textHandy, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
								.addComponent(textTelefon, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addComponent(lblAdresse)
					.addGap(9)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPostleitzahl)
						.addComponent(textPostleitzahl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblOrt)
						.addComponent(textOrt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblOrtsteil)
						.addComponent(textOrtsteil, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStrae)
						.addComponent(textStraße, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblHausnummer)
						.addComponent(textHausnummer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblZusatz)
						.addComponent(textZusatz, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(14)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblKomunikation)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTelefon)
						.addComponent(textTelefon, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblHandy)
						.addComponent(textHandy, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFax)
						.addComponent(textFax, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmail)
						.addComponent(textEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(58))
		);
		panel_3.setLayout(gl_panel_3);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 444, 292, 37);
		rollendaten.add(panel_1);
		
		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout("", "[][][]", "[]"));
		
		JButton button = new JButton("Ok");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addPatner();
			}

		});
		panel.add(button, "cell 0 0");
		
		JButton bCancel = new JButton("Cancel");
		bCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exit();
			}
		});
		panel.add(bCancel, "cell 2 0");
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
					.addContainerGap(76, Short.MAX_VALUE)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(59))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		enumRole = new JComboBox<RoleEnumObj>();
		enumRole.setModel(new DefaultComboBoxModel<RoleEnumObj>(rolle_lokalisiert));
		enumRole.setBounds(10, 11, 175, 20);
		rollendaten.add(enumRole);
		bCancel.setVisible(editable);
		
		JLabel lblNewLabel = new JLabel("Name:");
		
		JLabel lblVorname = new JLabel("Vorname:");
		
		JLabel lblTitel = new JLabel("Titel:");
		
		textName = new JTextField();
		textName.setColumns(10);
		
		
		textVorname = new JTextField();
		textVorname.setColumns(10);
		
		
		textTitel = new JTextField();
		textTitel.setColumns(10);
		
		
		JLabel lblGeburtsdatum = new JLabel("Geburtsdatum:");
		
		spinGebdatum = new JSpinner();
		
		spinGebdatum.setEnabled(editable);
		SpinnerDateModel model = new SpinnerDateModel();
		model.setCalendarField(Calendar.MINUTE);
		spinGebdatum = new JSpinner();
		spinGebdatum.setModel(model);
		dateEditor = new JSpinner.DateEditor(spinGebdatum, "dd-MM-yyyy");
		spinGebdatum.setEditor(dateEditor);
		
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
							.addComponent(spinGebdatum, GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)))
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
						.addComponent(spinGebdatum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(23, Short.MAX_VALUE))
		);
		personenbezogeneDaten.setLayout(gl_personenbezogeneDaten);
		getContentPane().setLayout(groupLayout);
		
		setEditable();
	}
	
	private void setEditable(){
		textPaneComment.setEditable(editable);
		textFax.setEditable(editable);
		textHandy.setEditable(editable);
		textTelefon.setEditable(editable);
		textZusatz.setEditable(editable);
		textHausnummer.setEditable(editable);
		textPostleitzahl.setEditable(editable);
		textOrt.setEditable(editable);
		textOrtsteil.setEditable(editable);
		textStraße.setEditable(editable);
		textEmail.setEditable(editable);
		textName.setEditable(editable);
		textVorname.setEditable(editable);
		textTitel.setEditable(editable);
		spinGebdatum.setEnabled(editable);
	}

	protected void addPatner() {
		Database.insertPatner();
	}

	private void exit(){
		try {
			this.setClosed(true);
		} catch (PropertyVetoException e) {
			logger.info(e);
		}
	}
}
