package ba.unsa.etf.si.app.SIDEVS.Interfejsi;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JButton;

public class AdminDodavanjeKorisnika {

	private JFrame frmAdministratorDodavanjeKorisnika;
	private JTextField ime;
	private JTextField prezime;
	private JTextField maticniBroj;
	private JTextField brojTelefona;
	private JTextField email;
	private JTextField radnoMjesto;
	private JTextField datumPocetkaRada;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminDodavanjeKorisnika window = new AdminDodavanjeKorisnika();
					window.frmAdministratorDodavanjeKorisnika.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AdminDodavanjeKorisnika() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAdministratorDodavanjeKorisnika = new JFrame();
		frmAdministratorDodavanjeKorisnika.setTitle("Administrator- Dodavanje korisnika");
		frmAdministratorDodavanjeKorisnika.setBounds(100, 100, 376, 416);
		frmAdministratorDodavanjeKorisnika.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAdministratorDodavanjeKorisnika.getContentPane().setLayout(null);
		
		ime = new JTextField();
		ime.setBounds(129, 25, 86, 20);
		frmAdministratorDodavanjeKorisnika.getContentPane().add(ime);
		ime.setColumns(10);
		
		prezime = new JTextField();
		prezime.setBounds(129, 68, 86, 20);
		frmAdministratorDodavanjeKorisnika.getContentPane().add(prezime);
		prezime.setColumns(10);
		
		maticniBroj = new JTextField();
		maticniBroj.setBounds(129, 105, 86, 20);
		frmAdministratorDodavanjeKorisnika.getContentPane().add(maticniBroj);
		maticniBroj.setColumns(10);
		
		brojTelefona = new JTextField();
		brojTelefona.setBounds(129, 147, 86, 20);
		frmAdministratorDodavanjeKorisnika.getContentPane().add(brojTelefona);
		brojTelefona.setColumns(10);
		
		email = new JTextField();
		email.setBounds(129, 193, 86, 20);
		frmAdministratorDodavanjeKorisnika.getContentPane().add(email);
		email.setColumns(10);
		
		radnoMjesto = new JTextField();
		radnoMjesto.setBounds(129, 238, 86, 20);
		frmAdministratorDodavanjeKorisnika.getContentPane().add(radnoMjesto);
		radnoMjesto.setColumns(10);
		
		datumPocetkaRada = new JTextField();
		datumPocetkaRada.setBounds(129, 284, 86, 20);
		frmAdministratorDodavanjeKorisnika.getContentPane().add(datumPocetkaRada);
		datumPocetkaRada.setColumns(10);
		
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
		lblNewLabel_3.setBounds(129, 133, 119, 14);
		frmAdministratorDodavanjeKorisnika.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Email");
		lblNewLabel_4.setBounds(129, 177, 119, 14);
		frmAdministratorDodavanjeKorisnika.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Radno mjesto");
		lblNewLabel_5.setBounds(129, 224, 119, 14);
		frmAdministratorDodavanjeKorisnika.getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Datum pocetka rada");
		lblNewLabel_6.setBounds(129, 269, 119, 14);
		frmAdministratorDodavanjeKorisnika.getContentPane().add(lblNewLabel_6);
		
		JRadioButton radnik = new JRadioButton("Radnik");
		radnik.setBounds(56, 311, 109, 23);
		frmAdministratorDodavanjeKorisnika.getContentPane().add(radnik);
		
		JRadioButton menadzer = new JRadioButton("Menadzer");
		menadzer.setBounds(187, 311, 109, 23);
		frmAdministratorDodavanjeKorisnika.getContentPane().add(menadzer);
		
		JButton btnDodaj = new JButton("Dodaj");
		btnDodaj.setBounds(105, 341, 89, 23);
		frmAdministratorDodavanjeKorisnika.getContentPane().add(btnDodaj);
	}
}
