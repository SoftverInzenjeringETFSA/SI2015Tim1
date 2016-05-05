package ba.unsa.etf.si.app.SIDEVS.View.Admin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import org.hibernate.Transaction;

import java.util.Iterator;
import java.util.List;
import ba.unsa.etf.si.app.SIDEVS.Model.Korisnik;
import ba.unsa.etf.si.app.SIDEVS.Model.Sessions;
import ba.unsa.etf.si.app.SIDEVS.Util.HibernateUtil;
import ba.unsa.etf.si.app.SIDEVS.Util.Controls.AutoCompleteJComboBox;

import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class ModifikacijaKorisnika {
	
	private Sessions _sesija;
	private JFrame frmAdministratormodifikacijaKorisnika;
	private JTextField imeModifikacija;
	private JTextField prezimeModifikacija;
	private JTextField maticniBrojModifikacija;
	private JTextField brojTelefonaModifikacija;
	private JTextField emailModifikacija;
	private JTextField radnoMjestoModifikacija;
	private JTextField datumPocetkaRadaModifikacija;
	private JTextField adresa;

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
		initialize(_sesija);
	}
	
	public ModifikacijaKorisnika(Sessions s) throws Exception {
		_sesija = s;
		initialize(_sesija);
		frmAdministratormodifikacijaKorisnika.setVisible(true);
		if(!s.daLiPostoji()){
			throw new Exception("Sesija nije kreirana!");
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Sessions s) {
		
		Transaction t = s.getSession().beginTransaction();
		
		frmAdministratormodifikacijaKorisnika = new JFrame();
		frmAdministratormodifikacijaKorisnika.setTitle("Administrator-Modifikacija korisnika");
		frmAdministratormodifikacijaKorisnika.setBounds(100, 100, 425, 579);
		frmAdministratormodifikacijaKorisnika.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAdministratormodifikacijaKorisnika.getContentPane().setLayout(null);
		frmAdministratormodifikacijaKorisnika.setLocationRelativeTo(null);
		
		imeModifikacija = new JTextField();
		imeModifikacija.setColumns(10);
		imeModifikacija.setBounds(153, 84, 86, 20);
		frmAdministratormodifikacijaKorisnika.getContentPane().add(imeModifikacija);
		
		prezimeModifikacija = new JTextField();
		prezimeModifikacija.setColumns(10);
		prezimeModifikacija.setBounds(153, 130, 86, 20);
		frmAdministratormodifikacijaKorisnika.getContentPane().add(prezimeModifikacija);
		
		maticniBrojModifikacija = new JTextField();
		maticniBrojModifikacija.setColumns(10);
		maticniBrojModifikacija.setBounds(153, 173, 86, 20);
		frmAdministratormodifikacijaKorisnika.getContentPane().add(maticniBrojModifikacija);
		
		brojTelefonaModifikacija = new JTextField();
		brojTelefonaModifikacija.setColumns(10);
		brojTelefonaModifikacija.setBounds(153, 214, 86, 20);
		frmAdministratormodifikacijaKorisnika.getContentPane().add(brojTelefonaModifikacija);
		
		emailModifikacija = new JTextField();
		emailModifikacija.setColumns(10);
		emailModifikacija.setBounds(153, 309, 86, 20);
		frmAdministratormodifikacijaKorisnika.getContentPane().add(emailModifikacija);
		
		radnoMjestoModifikacija = new JTextField();
		radnoMjestoModifikacija.setColumns(10);
		radnoMjestoModifikacija.setBounds(153, 359, 86, 20);
		frmAdministratormodifikacijaKorisnika.getContentPane().add(radnoMjestoModifikacija);
		
		datumPocetkaRadaModifikacija = new JTextField();
		datumPocetkaRadaModifikacija.setColumns(10);
		datumPocetkaRadaModifikacija.setBounds(153, 409, 86, 20);
		frmAdministratormodifikacijaKorisnika.getContentPane().add(datumPocetkaRadaModifikacija);
		
		JLabel label = new JLabel("Ime");
		label.setBounds(153, 67, 119, 14);
		frmAdministratormodifikacijaKorisnika.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Prezime");
		label_1.setBounds(153, 114, 119, 14);
		frmAdministratormodifikacijaKorisnika.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("Maticni broj");
		label_2.setBounds(153, 157, 119, 14);
		frmAdministratormodifikacijaKorisnika.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("Broj telefona");
		label_3.setBounds(153, 197, 119, 14);
		frmAdministratormodifikacijaKorisnika.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("Email");
		label_4.setBounds(153, 292, 119, 14);
		frmAdministratormodifikacijaKorisnika.getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel("Radno mjesto");
		label_5.setBounds(153, 342, 119, 14);
		frmAdministratormodifikacijaKorisnika.getContentPane().add(label_5);
		
		JLabel label_6 = new JLabel("Datum pocetka rada");
		label_6.setBounds(153, 392, 119, 14);
		frmAdministratormodifikacijaKorisnika.getContentPane().add(label_6);
		
		JRadioButton radnikModifikacija = new JRadioButton("Radnik");
		radnikModifikacija.setBounds(122, 438, 73, 23);
		frmAdministratormodifikacijaKorisnika.getContentPane().add(radnikModifikacija);
		
		JRadioButton menadzerModifikacija = new JRadioButton("Menadzer");
		menadzerModifikacija.setBounds(205, 438, 94, 23);
		frmAdministratormodifikacijaKorisnika.getContentPane().add(menadzerModifikacija);
		
		JButton btnModifikacija = new JButton("Azuriraj");
		btnModifikacija.setBounds(153, 480, 86, 23);
		frmAdministratormodifikacijaKorisnika.getContentPane().add(btnModifikacija);
		
		//JComboBox listaKorisnikaModifikacija = new JComboBox();
		//AutoCompleteJComboBox  listaKorisnikaModifikacija = new AutoCompleteJComboBox(s.getSession(), Korisnik.class);
		List<Korisnik> listKorisnika = s.getSession().createCriteria(Korisnik.class).list();
		AutoCompleteJComboBox  listaKorisnikaModifikacija = new AutoCompleteJComboBox(s.getSession(), Korisnik.class);
		listaKorisnikaModifikacija.setBounds(129, 34, 159, 20);
		for (Iterator iterator = listKorisnika.iterator(); iterator.hasNext();) {
			Korisnik korisnik = (Korisnik) iterator.next();
			listaKorisnikaModifikacija.addItem(korisnik.getIme() + " " + korisnik.getPrezime());
			System.out.println(korisnik.getIme() + " " + korisnik.getPrezime());
		}
		frmAdministratormodifikacijaKorisnika.getContentPane().add(listaKorisnikaModifikacija);
		
		JLabel lblOdaberiKorisnika = new JLabel("Odaberi korisnika");
		lblOdaberiKorisnika.setBounds(153, 13, 169, 14);
		frmAdministratormodifikacijaKorisnika.getContentPane().add(lblOdaberiKorisnika);
		
		JLabel lblAdresa = new JLabel("Adresa");
		lblAdresa.setBounds(153, 247, 56, 16);
		frmAdministratormodifikacijaKorisnika.getContentPane().add(lblAdresa);
		
		adresa = new JTextField();
		adresa.setBounds(156, 264, 83, 22);
		frmAdministratormodifikacijaKorisnika.getContentPane().add(adresa);
		adresa.setColumns(10);
	}
}
