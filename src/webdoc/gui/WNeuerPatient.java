package webdoc.gui;

import java.awt.EventQueue;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

public class WNeuerPatient extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4647611743598708383L;
	private JTextField textField;
	private JTextField nameDesTieres;
	private JTextField rasse;
	private JTextField farbe;
	private JTextField gewicht;
	private JTextField identifizierung;
	public  boolean edit = true;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WNeuerPatient window = new WNeuerPatient();
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
	public WNeuerPatient() {
		setFrameIcon(null);
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setTitle("Neuer Patient");
		setBounds(100, 100, 705, 592);
		
		JPanel suche = new JPanel();
		
		textField = new JTextField();
		textField.setText("Suche");
		textField.setColumns(10);
		GroupLayout gl_suche = new GroupLayout(suche);
		gl_suche.setHorizontalGroup(
			gl_suche.createParallelGroup(Alignment.LEADING)
				.addGap(0, 846, Short.MAX_VALUE)
				.addGroup(gl_suche.createSequentialGroup()
					.addGap(119)
					.addComponent(textField, GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
					.addGap(129))
		);
		gl_suche.setVerticalGroup(
			gl_suche.createParallelGroup(Alignment.LEADING)
				.addGap(0, 35, Short.MAX_VALUE)
				.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		);
		suche.setLayout(gl_suche);
		
		JPanel daten = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(suche, GroupLayout.PREFERRED_SIZE, 694, GroupLayout.PREFERRED_SIZE)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(daten, GroupLayout.PREFERRED_SIZE, 684, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(172, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(suche, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addGap(4)
					.addComponent(daten, GroupLayout.PREFERRED_SIZE, 496, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		JPanel allgemeineDaten = new JPanel();
		GroupLayout gl_daten = new GroupLayout(daten);
		gl_daten.setHorizontalGroup(
			gl_daten.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_daten.createSequentialGroup()
					.addComponent(allgemeineDaten, GroupLayout.PREFERRED_SIZE, 249, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(435, Short.MAX_VALUE))
		);
		gl_daten.setVerticalGroup(
			gl_daten.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_daten.createSequentialGroup()
					.addComponent(allgemeineDaten, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(275, Short.MAX_VALUE))
		);
		
		JLabel lblName = new JLabel("Name des Tieres:");
		
		JLabel lblRasse = new JLabel("Rasse:");
		
		JLabel lblGeschlecht = new JLabel("Geschlecht:");
		
		JLabel lblGeburtsdatum = new JLabel("Geburtsdatum:");
		
		JLabel lblHaarkleidfarbe = new JLabel("Haarkleid/Farbe:");
		
		JLabel lblGewicht = new JLabel("Gewicht:");
		
		JLabel lblIdentifizierung = new JLabel("Identifizierung:");
		
		JLabel lblAllgemeineDaten = new JLabel("Allgemeine Daten");
		
		nameDesTieres = new JTextField();
		nameDesTieres.setColumns(10);
		
		rasse = new JTextField();
		rasse.setColumns(10);
		
		farbe = new JTextField();
		farbe.setColumns(10);
		
		gewicht = new JTextField();
		gewicht.setColumns(10);
		
		identifizierung = new JTextField();
		identifizierung.setColumns(10);
		
		JComboBox geschlecht = new JComboBox();
		GroupLayout gl_allgemeineDaten = new GroupLayout(allgemeineDaten);
		gl_allgemeineDaten.setHorizontalGroup(
			gl_allgemeineDaten.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_allgemeineDaten.createSequentialGroup()
					.addGroup(gl_allgemeineDaten.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_allgemeineDaten.createSequentialGroup()
							.addComponent(lblIdentifizierung)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(identifizierung))
						.addGroup(gl_allgemeineDaten.createSequentialGroup()
							.addGroup(gl_allgemeineDaten.createParallelGroup(Alignment.LEADING)
								.addComponent(lblName)
								.addComponent(lblRasse)
								.addComponent(lblGeschlecht))
							.addGap(3)
							.addGroup(gl_allgemeineDaten.createParallelGroup(Alignment.LEADING)
								.addComponent(geschlecht, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_allgemeineDaten.createParallelGroup(Alignment.LEADING, false)
									.addComponent(rasse)
									.addComponent(nameDesTieres, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE))))
						.addGroup(gl_allgemeineDaten.createSequentialGroup()
							.addGroup(gl_allgemeineDaten.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_allgemeineDaten.createParallelGroup(Alignment.LEADING)
									.addComponent(lblHaarkleidfarbe)
									.addComponent(lblGewicht))
								.addComponent(lblGeburtsdatum))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_allgemeineDaten.createParallelGroup(Alignment.LEADING)
								.addComponent(farbe)
								.addComponent(gewicht, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE))))
					.addContainerGap(40, Short.MAX_VALUE))
				.addGroup(gl_allgemeineDaten.createSequentialGroup()
					.addContainerGap(101, Short.MAX_VALUE)
					.addComponent(lblAllgemeineDaten)
					.addGap(65))
		);
		gl_allgemeineDaten.setVerticalGroup(
			gl_allgemeineDaten.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_allgemeineDaten.createSequentialGroup()
					.addComponent(lblAllgemeineDaten)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_allgemeineDaten.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName)
						.addComponent(nameDesTieres, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_allgemeineDaten.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRasse)
						.addComponent(rasse, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_allgemeineDaten.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGeschlecht)
						.addComponent(geschlecht, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_allgemeineDaten.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_allgemeineDaten.createSequentialGroup()
							.addComponent(lblGeburtsdatum)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblHaarkleidfarbe))
						.addComponent(farbe, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_allgemeineDaten.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGewicht)
						.addComponent(gewicht, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_allgemeineDaten.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblIdentifizierung)
						.addComponent(identifizierung, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(24, Short.MAX_VALUE))
		);
		allgemeineDaten.setLayout(gl_allgemeineDaten);
		daten.setLayout(gl_daten);
		getContentPane().setLayout(groupLayout);
	}
	private boolean getEdit(){
		return edit;
	}
	public void setEdit(boolean editr){
		if(editr = true){ 
			edit = true;
		}if(editr = false){
			edit =false;
		}
	}
}
