package ba.unsa.etf.si.app.SIDEVS.View.Admin;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextField;

import com.mysql.cj.api.Session;

import ba.unsa.etf.si.app.SIDEVS.Model.Sessions;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

public class DodavanjeKorisnika {
	
	private Sessions _sesija;
	private JFrame frmAdministratorDodavanjeKorisnika;
	private JTextField ime;
	private JTextField prezime;
	private JTextField maticniBroj;
	private JTextField brojTelefona;
	private JTextField email;
	private JTextField radnoMjesto;
	private JTextField datumPocetkaRada; 
	private JTextField adresa;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DodavanjeKorisnika window = new DodavanjeKorisnika();
					window.frmAdministratorDodavanjeKorisnika.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	
	public DodavanjeKorisnika(){
		initialize();
		frmAdministratorDodavanjeKorisnika.setVisible(true);
	}
	
	public DodavanjeKorisnika(Sessions s) throws Exception {
		initialize();
		frmAdministratorDodavanjeKorisnika.setVisible(true);
		if(!s.daLiPostoji()){
			throw new Exception("Sesija nije kreirana!");
		}
		_sesija = s;
	}
	
	public void resetContent(){
		ime.setText("");
		prezime.setText("");
		maticniBroj.setText("");
		brojTelefona.setText("");
		email.setText("");
		radnoMjesto.setText("");
		datumPocetkaRada.setText(""); 	
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAdministratorDodavanjeKorisnika = new JFrame();
		frmAdministratorDodavanjeKorisnika.setTitle("Administrator- Dodavanje korisnika");
		frmAdministratorDodavanjeKorisnika.setBounds(100, 100, 383, 530);
		frmAdministratorDodavanjeKorisnika.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAdministratorDodavanjeKorisnika.getContentPane().setLayout(null);
		frmAdministratorDodavanjeKorisnika.setLocationRelativeTo(null);
		
		ime = new JTextField();
		ime.setBounds(129, 26, 86, 20);
		frmAdministratorDodavanjeKorisnika.getContentPane().add(ime);
		ime.setColumns(10);
		
		prezime = new JTextField();
		prezime.setBounds(129, 68, 86, 20);
		frmAdministratorDodavanjeKorisnika.getContentPane().add(prezime);
		prezime.setColumns(10);
		
		maticniBroj = new JTextField();
		maticniBroj.setBounds(129, 114, 86, 20);
		frmAdministratorDodavanjeKorisnika.getContentPane().add(maticniBroj);
		maticniBroj.setColumns(10);
		
		brojTelefona = new JTextField();
		brojTelefona.setBounds(129, 163, 86, 20);
		frmAdministratorDodavanjeKorisnika.getContentPane().add(brojTelefona);
		brojTelefona.setColumns(10);
		
		email = new JTextField();
		email.setBounds(129, 261, 86, 20);
		frmAdministratorDodavanjeKorisnika.getContentPane().add(email);
		email.setColumns(10);
		
		radnoMjesto = new JTextField();
		radnoMjesto.setBounds(129, 310, 86, 20);
		frmAdministratorDodavanjeKorisnika.getContentPane().add(radnoMjesto);
		radnoMjesto.setColumns(10);
		
		datumPocetkaRada = new JTextField();
		datumPocetkaRada.setBounds(129, 361, 86, 20);
		frmAdministratorDodavanjeKorisnika.getContentPane().add(datumPocetkaRada);
		datumPocetkaRada.setColumns(10);
		
		adresa = new JTextField();
		adresa.setBounds(129, 213, 86, 20);
		frmAdministratorDodavanjeKorisnika.getContentPane().add(adresa);
		adresa.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Ime");
		lblNewLabel.setBounds(129, 11, 119, 14);
		frmAdministratorDodavanjeKorisnika.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Prezime");
		lblNewLabel_1.setBounds(129, 51, 119, 14);
		frmAdministratorDodavanjeKorisnika.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Maticni broj");
		lblNewLabel_2.setBounds(129, 93, 119, 14);
		frmAdministratorDodavanjeKorisnika.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Broj telefona");
		lblNewLabel_3.setBounds(129, 147, 119, 14);
		frmAdministratorDodavanjeKorisnika.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Email");
		lblNewLabel_4.setBounds(129, 246, 119, 14);
		frmAdministratorDodavanjeKorisnika.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Radno mjesto");
		lblNewLabel_5.setBounds(129, 294, 119, 14);
		frmAdministratorDodavanjeKorisnika.getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Datum pocetka rada");
		lblNewLabel_6.setBounds(129, 343, 119, 14);
		frmAdministratorDodavanjeKorisnika.getContentPane().add(lblNewLabel_6);
		
		final JRadioButton radnik = new JRadioButton("Radnik");
		radnik.setBounds(96, 400, 72, 23);
		frmAdministratorDodavanjeKorisnika.getContentPane().add(radnik);
		
		final JRadioButton menadzer = new JRadioButton("Menadzer");
		menadzer.setBounds(181, 400, 86, 23);
		frmAdministratorDodavanjeKorisnika.getContentPane().add(menadzer);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(radnik);
		bg.add(menadzer);
		
		JButton btnDodaj = new JButton("Dodaj");
		btnDodaj.setBounds(129, 445, 86, 23);
		btnDodaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					
					//Ubaciti validaciju, bacanje exceptiona ukoliko je unos prazan
					
					//Baca izuzetak ukoliko ništa nije checkirano
					String tipKorisnika;
					if(menadzer.isSelected()) tipKorisnika = "Menadzer";
					else if (radnik.isSelected()) tipKorisnika = "Radnik";
					else throw new Exception();
					
					// Ukoliko kreiranje nije prošlo, baca exception
					boolean state = ba.unsa.etf.si.app.SIDEVS.ViewModel.DodavanjeKorisnikaVM.KreirajKorisnika(_sesija, ime.getText(), prezime.getText(), maticniBroj.getText(), brojTelefona.getText(), email.getText(), radnoMjesto.getText(), datumPocetkaRada.getText(), adresa.getText(), tipKorisnika);
					if(!state) throw new Exception();
					
					//Javlja da je korisnik kreiran ukoliko je sve prošlo ok
					JOptionPane.showMessageDialog(null, "Korisnik uspješno dodan", "InfoBox: " + "Success", JOptionPane.INFORMATION_MESSAGE);	
				}
				catch(Exception ex){
					JOptionPane.showMessageDialog(null, "Došlo je do greške u dodavanju", "InfoBox: " + "Error", JOptionPane.INFORMATION_MESSAGE);		
				}
				//Brisanje vrijednosti iz boxova
				resetContent();
			}
		});
		frmAdministratorDodavanjeKorisnika.getContentPane().add(btnDodaj);
		
		JLabel lblAdresa = new JLabel("Adresa");
		lblAdresa.setBounds(129, 196, 56, 16);
		frmAdministratorDodavanjeKorisnika.getContentPane().add(lblAdresa);
		
	}
}
