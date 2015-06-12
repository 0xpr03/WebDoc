package webdoc.gui;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.BoxLayout;

import net.miginfocom.swing.MigLayout;

import java.awt.CardLayout;
import java.awt.BorderLayout;

import webdoc.gui.utils.JSearchTextField;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JSpinner;
import javax.swing.JButton;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import com.sun.corba.se.impl.encoding.CodeSetConversion.BTCConverter;

import java.awt.Component;

import javax.swing.LayoutStyle.ComponentPlacement;

public class WNeueBehandlung extends JInternalFrame {
	private JTextField spPreis;
	private JTextField tFBezeichnung;
	private JTextPane tPErklaerung;
	private JSpinner spAnzahl;
	private JButton btnSpeichern;
	private JButton btnNeueBehandlungsart;
	private JButton btnAbrechen;
	private boolean editable;
	private JSpinner spDate;
	private JSpinner spTime;
	private long id;
	public WNeueBehandlung() {
		setTitle("Neue Behandlung");
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		
		JLabel lblBezeichnnung = new JLabel("Bezeichnnung:");
		
		tFBezeichnung = new JTextField();
		tFBezeichnung.setColumns(10);
		
		JLabel lblPreisProEinheit = new JLabel("Preis pro Einheit in €:");
		
		spPreis = new JTextField();
		
		JLabel lblErklrung = new JLabel(" Erklärung:");
		
		JScrollPane scrollPane = new JScrollPane();
		
		tPErklaerung = new JTextPane();
		scrollPane.setViewportView(tPErklaerung);
		
		JLabel lblAnzahlDerEinheiten = new JLabel("Anzahl der Einheiten:");
		
		spAnzahl = new JSpinner();
		
		JLabel lblDatum = new JLabel("Datum:");
		
		spDate = new JSpinner();
		
		JLabel lblUhrzeit = new JLabel("Uhrzeit:");
		
		spTime = new JSpinner();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(39)
							.addComponent(lblBezeichnnung)
							.addGap(4)
							.addComponent(tFBezeichnung, GroupLayout.PREFERRED_SIZE, 281, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(8)
							.addComponent(lblPreisProEinheit)
							.addGap(4)
							.addComponent(spPreis, GroupLayout.PREFERRED_SIZE, 281, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(7)
							.addComponent(lblErklrung)
							.addGap(54)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 281, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(7)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblUhrzeit)
									.addGap(68)
									.addComponent(spTime))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblDatum)
									.addGap(71)
									.addComponent(spDate))
								.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
									.addComponent(lblAnzahlDerEinheiten)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(spAnzahl, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)))))
					.addGap(7))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(7)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(3)
							.addComponent(lblBezeichnnung))
						.addComponent(tFBezeichnung, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(4)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(3)
							.addComponent(lblPreisProEinheit))
						.addComponent(spPreis, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(4)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(103)
							.addComponent(lblErklrung))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(7)
							.addComponent(lblAnzahlDerEinheiten))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(4)
							.addComponent(spAnzahl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(9)
							.addComponent(lblDatum))
						.addGroup(gl_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(spDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(9)
							.addComponent(lblUhrzeit))
						.addGroup(gl_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(spTime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		
		JSearchTextField searchTextField = new JSearchTextField(false);
		getContentPane().add(searchTextField, BorderLayout.NORTH);
		
		JPanel pButtons = new JPanel();
		getContentPane().add(pButtons, BorderLayout.SOUTH);
		pButtons.setLayout(new MigLayout("", "[][][][][]", "[]"));
		
		btnSpeichern = new JButton("Speichern");
		pButtons.add(btnSpeichern, "cell 1 0");
		
		btnAbrechen = new JButton("Abrechen");
		pButtons.add(btnAbrechen, "cell 2 0");
		
		btnNeueBehandlungsart = new JButton("Neue Behandlungsart");
		pButtons.add(btnNeueBehandlungsart, "cell 4 0");
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{spAnzahl, searchTextField, btnSpeichern, btnAbrechen, btnNeueBehandlungsart}));
		setEditable(editable);
	}
	private void setEditable(boolean editable) {
		tFBezeichnung.setEditable(false);
		spPreis.setEnabled(false);
		tPErklaerung.setEditable(false);
		spAnzahl.setEnabled(editable);
		spDate.setEnabled(editable);
		spTime.setEnabled(editable);
		refreshBtn(editable);
		
	}
	private void refreshBtn(boolean  editable) {
		btnSpeichern.setText(editable ? "Speichern" : "Schließen");
		btnAbrechen.setEnabled(true);
		btnNeueBehandlungsart.setEnabled(true);
	} 
	private boolean allSet(){
		if (Double.parseDouble(spAnzahl.getValue().toString()) == 0.0)
			return false;
		return true;
	}
}
