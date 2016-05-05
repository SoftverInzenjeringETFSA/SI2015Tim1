package ba.unsa.etf.si.app.SIDEVS.View.Admin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import ba.unsa.etf.si.app.SIDEVS.Model.Korisnik;
import ba.unsa.etf.si.app.SIDEVS.Util.HibernateUtil;
import ba.unsa.etf.si.app.SIDEVS.Util.Controls.AutoCompleteJComboBox;

import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class ModifikacijaKorisnika {

	private JFrame frmAdministratormodifikacijaKorisnika;
	private JTextField imeModifikacija;
	private JTextField prezimeModifikacija;
	private JTextField maticniBrojModifikacija;
	private JTextField brojTelefonaModifikacija;
	private JTextField emailModifikacija;
	private JTextField radnoMjestoModifikacija;
	private JTextField datumPocetkaRadaModifikacija;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModifikacijaKorisnika window = new ModifikacijaKorisnika();
					window.frmAdministratormodifikacijaKorisnika.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ModifikacijaKorisnika() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAdministratormodifikacijaKorisnika = new JFrame();
		frmAdministratormodifikacijaKorisnika.setTitle("Administrator-Modifikacija korisnika");
		frmAdministratormodifikacijaKorisnika.setBounds(100, 100, 394, 475);
		frmAdministratormodifikacijaKorisnika.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAdministratormodifikacijaKorisnika.getContentPane().setLayout(null);
		
		imeModifikacija = new JTextField();
		imeModifikacija.setColumns(10);
		imeModifikacija.setBounds(153, 74, 86, 20);
		frmAdministratormodifikacijaKorisnika.getContentPane().add(imeModifikacija);
		
		prezimeModifikacija = new JTextField();
		prezimeModifikacija.setColumns(10);
		prezimeModifikacija.setBounds(153, 117, 86, 20);
		frmAdministratormodifikacijaKorisnika.getContentPane().add(prezimeModifikacija);
		
		maticniBrojModifikacija = new JTextField();
		maticniBrojModifikacija.setColumns(10);
		maticniBrojModifikacija.setBounds(153, 154, 86, 20);
		frmAdministratormodifikacijaKorisnika.getContentPane().add(maticniBrojModifikacija);
		
		brojTelefonaModifikacija = new JTextField();
		brojTelefonaModifikacija.setColumns(10);
		brojTelefonaModifikacija.setBounds(153, 196, 86, 20);
		frmAdministratormodifikacijaKorisnika.getContentPane().add(brojTelefonaModifikacija);
		
		emailModifikacija = new JTextField();
		emailModifikacija.setColumns(10);
		emailModifikacija.setBounds(153, 242, 86, 20);
		frmAdministratormodifikacijaKorisnika.getContentPane().add(emailModifikacija);
		
		radnoMjestoModifikacija = new JTextField();
		radnoMjestoModifikacija.setColumns(10);
		radnoMjestoModifikacija.setBounds(153, 287, 86, 20);
		frmAdministratormodifikacijaKorisnika.getContentPane().add(radnoMjestoModifikacija);
		
		datumPocetkaRadaModifikacija = new JTextField();
		datumPocetkaRadaModifikacija.setColumns(10);
		datumPocetkaRadaModifikacija.setBounds(153, 333, 86, 20);
		frmAdministratormodifikacijaKorisnika.getContentPane().add(datumPocetkaRadaModifikacija);
		
		JLabel label = new JLabel("Ime");
		label.setBounds(153, 60, 119, 14);
		frmAdministratormodifikacijaKorisnika.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Prezime");
		label_1.setBounds(153, 100, 119, 14);
		frmAdministratormodifikacijaKorisnika.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("Maticni broj");
		label_2.setBounds(153, 142, 119, 14);
		frmAdministratormodifikacijaKorisnika.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("Broj telefona");
		label_3.setBounds(153, 182, 119, 14);
		frmAdministratormodifikacijaKorisnika.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("Email");
		label_4.setBounds(153, 226, 119, 14);
		frmAdministratormodifikacijaKorisnika.getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel("Radno mjesto");
		label_5.setBounds(153, 273, 119, 14);
		frmAdministratormodifikacijaKorisnika.getContentPane().add(label_5);
		
		JLabel label_6 = new JLabel("Datum pocetka rada");
		label_6.setBounds(153, 318, 119, 14);
		frmAdministratormodifikacijaKorisnika.getContentPane().add(label_6);
		
		JRadioButton radnikModifikacija = new JRadioButton("Radnik");
		radnikModifikacija.setBounds(80, 360, 109, 23);
		frmAdministratormodifikacijaKorisnika.getContentPane().add(radnikModifikacija);
		
		JRadioButton menadzerModifikacija = new JRadioButton("Menadzer");
		menadzerModifikacija.setBounds(221, 360, 109, 23);
		frmAdministratormodifikacijaKorisnika.getContentPane().add(menadzerModifikacija);
		
		JButton btnModifikacija = new JButton("Azuriraj");
		btnModifikacija.setBounds(129, 390, 89, 23);
		frmAdministratormodifikacijaKorisnika.getContentPane().add(btnModifikacija);
		
		//JComboBox listaKorisnikaModifikacija = new JComboBox();
		/*AutoCompleteJComboBox  listaKorisnikaModifikacija = new AutoCompleteJComboBox(HibernateUtil.getSessionFactory().openSession(), Korisnik.class);
		listaKorisnikaModifikacija.setBounds(129, 34, 143, 20);
		frmAdministratormodifikacijaKorisnika.getContentPane().add(listaKorisnikaModifikacija);*/
		
		JLabel lblOdaberiKorisnika = new JLabel("Odaberi korisnika");
		lblOdaberiKorisnika.setBounds(129, 9, 169, 14);
		frmAdministratormodifikacijaKorisnika.getContentPane().add(lblOdaberiKorisnika);
	}
}
