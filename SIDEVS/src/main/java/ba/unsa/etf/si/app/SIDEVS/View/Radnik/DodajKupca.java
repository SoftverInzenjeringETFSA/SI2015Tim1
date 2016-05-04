package ba.unsa.etf.si.app.SIDEVS.View.Radnik;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import ba.unsa.etf.si.app.SIDEVS.Model.*;
import ba.unsa.etf.si.app.SIDEVS.ViewModel.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DodajKupca {

	/*
	 * uraditi jos:
	 * validacija
	 * autogenerisani id
	 * */
	
	private JFrame frmDodajKupca;
	private JTextField txtIme;
	private JTextField txtAdresa;

	private Sessions s;


	public DodajKupca(Sessions s) {
		initialize();
		this.s = s;
		frmDodajKupca.setVisible(true);
		//if (!s.daLiPostoji())
		//	throw new Exception("Sesija nije kreirana!");
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDodajKupca = new JFrame();
		frmDodajKupca.setTitle("Dodaj kupca");
		frmDodajKupca.setBounds(100, 100, 302, 205);
		frmDodajKupca.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDodajKupca.getContentPane().setLayout(null);
		
		JLabel lblImeIPrezime = new JLabel("Ime i prezime:");
		lblImeIPrezime.setBounds(10, 36, 91, 14);
		frmDodajKupca.getContentPane().add(lblImeIPrezime);
		
		JLabel lblNewLabel = new JLabel("Adresa:");
		lblNewLabel.setBounds(10, 61, 46, 14);
		frmDodajKupca.getContentPane().add(lblNewLabel);
		
		
		txtIme = new JTextField();
		txtIme.setBounds(111, 33, 141, 20);
		frmDodajKupca.getContentPane().add(txtIme);
		txtIme.setColumns(10);
		
		txtAdresa = new JTextField();
		txtAdresa.setBounds(111, 58, 141, 20);
		frmDodajKupca.getContentPane().add(txtAdresa);
		txtAdresa.setColumns(10);
		
		JButton btnDodaj = new JButton("Dodaj");
		
		final JLabel label = new JLabel("");
		label.setBounds(12, 131, 46, 14);
		frmDodajKupca.getContentPane().add(label);
		
		btnDodaj.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try{
					if(validirajPolja()){
						EvidencijaKupcaVM k= new EvidencijaKupcaVM(s);
						k.dodajKupca(Long.parseLong("123"), txtIme.getText(), txtAdresa.getText());
						txtIme.setText("");
						txtAdresa.setText("");
						label.setForeground(Color.green);
						label.setText("Uspješno ste kreirali lijek");
					}
				}
				catch(Exception ex){
					label.setForeground(Color.RED);
					label.setText(ex.getMessage());
				}
			}
		});
		btnDodaj.setBounds(111, 91, 141, 23);
		frmDodajKupca.getContentPane().add(btnDodaj);
		
		
		
		
	}
	
	private Boolean validirajPolja(){
		//TODO
		return true;
	}
}

