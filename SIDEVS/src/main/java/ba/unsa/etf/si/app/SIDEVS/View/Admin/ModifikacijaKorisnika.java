package ba.unsa.etf.si.app.SIDEVS.View.Admin;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JFrame;
import javax.swing.JTextField;

import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.Iterator;
import java.util.List;
import ba.unsa.etf.si.app.SIDEVS.Model.Korisnik;
import ba.unsa.etf.si.app.SIDEVS.Model.Sessions;
import ba.unsa.etf.si.app.SIDEVS.Util.HibernateUtil;
import ba.unsa.etf.si.app.SIDEVS.Util.Controls.AutoCompleteJComboBox;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
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
	
	public void resetContent(){
		imeModifikacija.setText("");
		prezimeModifikacija.setText("");
		maticniBrojModifikacija.setText("");
		brojTelefonaModifikacija.setText("");
		emailModifikacija.setText("");
		radnoMjestoModifikacija.setText("");
		datumPocetkaRadaModifikacija.setText("");
		adresa.setText("");
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
		
		final JRadioButton radnikModifikacija = new JRadioButton("Radnik");
		radnikModifikacija.setBounds(122, 438, 73, 23);
		frmAdministratormodifikacijaKorisnika.getContentPane().add(radnikModifikacija);
		
		final JRadioButton menadzerModifikacija = new JRadioButton("Menadzer");
		menadzerModifikacija.setBounds(205, 438, 94, 23);
		frmAdministratormodifikacijaKorisnika.getContentPane().add(menadzerModifikacija);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(radnikModifikacija);
		bg.add(menadzerModifikacija);
		
		final AutoCompleteJComboBox  listaKorisnikaModifikacija = new AutoCompleteJComboBox(s, Korisnik.class, "ime");
		listaKorisnikaModifikacija.setBounds(58, 33, 159, 20);
		frmAdministratormodifikacijaKorisnika.getContentPane().add(listaKorisnikaModifikacija);
		
	    JButton btnModifikacija = new JButton("Azuriraj");
		btnModifikacija.setBounds(153, 480, 86, 23);
		btnModifikacija.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{				
					//Ubaciti validaciju, bacanje exceptiona ukoliko je unos prazan
					
					//Baca izuzetak ukoliko ništa nije checkirano
					String tipKorisnika;
					if(menadzerModifikacija.isSelected()) tipKorisnika = "Menadzer";
					else if (radnikModifikacija.isSelected()) tipKorisnika = "Radnik";
					else throw new Exception();
					
					String txt = listaKorisnikaModifikacija.getSelectedItem().toString();	
					String[] parts = txt.split(" ");
					String part1 = parts[0];
					String part2 = parts[1];
					
					//Ukoliko kreiranje nije prošlo, baca exception
					boolean state = ba.unsa.etf.si.app.SIDEVS.ViewModel.ModifikacijaKorisnikaVM.ModifikujKorisnika(_sesija, imeModifikacija.getText(), prezimeModifikacija.getText(), maticniBrojModifikacija.getText(), brojTelefonaModifikacija.getText(), emailModifikacija.getText(), radnoMjestoModifikacija.getText(), datumPocetkaRadaModifikacija.getText(), adresa.getText(), tipKorisnika, part1, part2);
					if(!state) throw new Exception();
					
					JOptionPane.showMessageDialog(null, "Korisnik uspješno ažuriran", "InfoBox: " + "Success", JOptionPane.INFORMATION_MESSAGE);
				}
				catch(Exception ex){
					System.out.println(ex.toString());
					JOptionPane.showMessageDialog(null, "Došlo je do greške u dodavanju", "InfoBox: " + "Error", JOptionPane.INFORMATION_MESSAGE);		
				}
				resetContent();
				listaKorisnikaModifikacija.setSelectedItem("");
				radnikModifikacija.setSelected(false);
				menadzerModifikacija.setSelected(false);
			}
		});
		frmAdministratormodifikacijaKorisnika.getContentPane().add(btnModifikacija);
	
		JLabel lblOdaberiKorisnika = new JLabel("Odaberi korisnika");
		lblOdaberiKorisnika.setBounds(58, 13, 169, 14);
		frmAdministratormodifikacijaKorisnika.getContentPane().add(lblOdaberiKorisnika);
		
		JLabel lblAdresa = new JLabel("Adresa");
		lblAdresa.setBounds(153, 247, 56, 16);
		frmAdministratormodifikacijaKorisnika.getContentPane().add(lblAdresa);
		
		adresa = new JTextField();
		adresa.setBounds(153, 264, 83, 22);
		frmAdministratormodifikacijaKorisnika.getContentPane().add(adresa);
		adresa.setColumns(10);
		
		JButton btnUcitaj = new JButton("Ucitaj");
		btnUcitaj.setBounds(229, 31, 97, 23);
		frmAdministratormodifikacijaKorisnika.getContentPane().add(btnUcitaj);
		btnUcitaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{					
					String txt = listaKorisnikaModifikacija.getSelectedItem().toString();	
					String[] parts = txt.split(" ");
					String part1 = parts[0];
					String part2 = parts[1];
					
					Criteria criteria = _sesija.getSession().createCriteria(Korisnik.class).add(Restrictions.like("ime", part1).ignoreCase()).add(Restrictions.like("prezime", part2).ignoreCase());
					List<Korisnik> k = criteria.list();
					
					String ime2 = k.get(0).getIme();
					String prezime2 = k.get(0).getPrezime();
					String maticniBroj2 = k.get(0).getJmbg();
					String adresa2 = k.get(0).getAdresa();
					String email2 = k.get(0).getEmail();
					String brojTelefona2 = k.get(0).getTelefon();
					String radnoMjesto2 = k.get(0).getRadno_mjesto();
					//String datumPocetkaRada2 = datumPocetkaRada1.get(0);				
					//Dodati checkiranje menadzer/radnik radio buttona, preko DTYPE(?!)
					
					imeModifikacija.setText(ime2);
					prezimeModifikacija.setText(prezime2);
					maticniBrojModifikacija.setText(maticniBroj2);
					brojTelefonaModifikacija.setText(brojTelefona2);
					emailModifikacija.setText(email2);
					radnoMjestoModifikacija.setText(radnoMjesto2);
					//datumPocetkaRadaModifikacija.setText(datumPocetkaRada2);
					adresa.setText(adresa2);
				}
				catch(Exception ex){
					System.out.println("Greška pri pristupu bazi");
					System.out.println(ex.toString());
				}
			}
		});
	}
}
