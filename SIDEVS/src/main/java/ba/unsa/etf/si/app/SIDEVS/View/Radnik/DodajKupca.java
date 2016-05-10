package ba.unsa.etf.si.app.SIDEVS.View.Radnik;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import ba.unsa.etf.si.app.SIDEVS.Model.*;
import ba.unsa.etf.si.app.SIDEVS.ViewModel.*;
import java.awt.Color;

public class DodajKupca {

	public JFrame frmDodajKupca;
	private JTextField ime_txt;
	private JTextField adresa_txt;
	private JLabel obavijest_lbl;
	
	private Sessions s;

	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DodajKupca window = new DodajKupca();
					window.frmDodajKupca.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/


	public DodajKupca(Sessions s) throws Exception{
		this.s = s;
		initialize();
		frmDodajKupca.setVisible(true);
		if (!s.daLiPostoji()) {
			throw new Exception("Sesija nije kreirana!");
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDodajKupca = new JFrame();
		frmDodajKupca.setTitle("Dodaj kupca");
		frmDodajKupca.setBounds(100, 100, 302, 196);
		frmDodajKupca.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDodajKupca.getContentPane().setLayout(null);
		
		JLabel lblImeIPrezime = new JLabel("Ime i prezime:");
		lblImeIPrezime.setBounds(10, 36, 91, 14);
		frmDodajKupca.getContentPane().add(lblImeIPrezime);
		
		JLabel lblNewLabel = new JLabel("Adresa:");
		lblNewLabel.setBounds(10, 61, 46, 14);
		frmDodajKupca.getContentPane().add(lblNewLabel);
		
		ime_txt = new JTextField();
		ime_txt.setBounds(111, 33, 141, 20);
		frmDodajKupca.getContentPane().add(ime_txt);
		ime_txt.setColumns(10);
		
		adresa_txt = new JTextField();
		adresa_txt.setBounds(111, 58, 141, 20);
		frmDodajKupca.getContentPane().add(adresa_txt);
		adresa_txt.setColumns(10);
		
		obavijest_lbl = new JLabel("");
		obavijest_lbl.setForeground(Color.WHITE);
		obavijest_lbl.setBounds(10, 132, 219, 14);
		frmDodajKupca.getContentPane().add(obavijest_lbl);
		
		
		JButton btnDodaj = new JButton("Dodaj");
		btnDodaj.addMouseListener(new MouseAdapter() {
			//klikom na mis kreiramo novog kupca:
			public void mouseClicked(MouseEvent arg0) {
				try{
					if (validirajPolja()){
						
						String nazivKupca = ime_txt.getText();
						String adresaKupca = adresa_txt.getText();
						
						EvidencijaKupca k = new EvidencijaKupca(s);
						k.dodajKupca(nazivKupca, adresaKupca);
						refreshFields();
						obavijest_lbl.setForeground(Color.decode("#008000"));
						obavijest_lbl.setText("Novi kupac je uspješno unesen u bazu");
					}
				}
				catch(Exception e){
					obavijest_lbl.setForeground(Color.red);
					obavijest_lbl.setText(e.getMessage());
				}
			}

			
		});
		
		
		btnDodaj.setBounds(111, 103, 141, 23);
		frmDodajKupca.getContentPane().add(btnDodaj);
	}
	
	
	protected void refreshFields() {
		ime_txt.setText("");
		adresa_txt.setText("");
		
	}

	private boolean validirajPolja() {
		
		
		
		
		return true;
	}
	
	
}
