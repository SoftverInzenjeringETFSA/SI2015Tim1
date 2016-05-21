package ba.unsa.etf.si.app.SIDEVS.View.Admin;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import javax.swing.JTextField;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import Exceptions.WrongInputException;

import java.util.Date;
import java.util.List;
import ba.unsa.etf.si.app.SIDEVS.Model.Korisnik;
import ba.unsa.etf.si.app.SIDEVS.Model.Menadzer;
import ba.unsa.etf.si.app.SIDEVS.Model.Radnik;
import ba.unsa.etf.si.app.SIDEVS.Model.Sessions;
import ba.unsa.etf.si.app.SIDEVS.Util.Controls.AutoCompleteJComboBox;
import ba.unsa.etf.si.app.SIDEVS.Validation.EmailFormatter;
import ba.unsa.etf.si.app.SIDEVS.Validation.Validator;
import ba.unsa.etf.si.app.SIDEVS.View.Masks;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

public class ModifikacijaKorisnika {
	
	final static Logger logger = Logger.getLogger(ModifikacijaKorisnika.class);
	
	private Sessions _sesija;
	private JFrame frmAdministratormodifikacijaKorisnika;
	private JTextField imeModifikacija;
	private JTextField prezimeModifikacija;
	private JFormattedTextField maticniBrojModifikacija;
	private JFormattedTextField brojTelefonaModifikacija;
	private JFormattedTextField emailModifikacija;
	private JTextField radnoMjestoModifikacija;
	private JTextField adresa;
	private JFormattedTextField datumPocetkaRadaModifikacija;

	private AutoCompleteJComboBox listaKorisnikaModifikacija;

	private JRadioButton radnikModifikacija;

	private JRadioButton menadzerModifikacija;
	private JLabel label_obavijest;
	private JLabel lblNameexamplecom;

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
					logger.error(e);
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
	
	public void ugasi(){
		frmAdministratormodifikacijaKorisnika.dispose();
	}
	
	public void resetContent(){
		imeModifikacija.setText("");
		prezimeModifikacija.setText("");
		maticniBrojModifikacija.setValue("");
		brojTelefonaModifikacija.setValue("");
		emailModifikacija.setValue("");
		radnoMjestoModifikacija.setText("");
		datumPocetkaRadaModifikacija.setValue("");
		adresa.setText("");
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Sessions s) {
		
		frmAdministratormodifikacijaKorisnika = new JFrame();
		frmAdministratormodifikacijaKorisnika.setTitle("Administrator-Modifikacija korisnika");
		frmAdministratormodifikacijaKorisnika.setBounds(100, 100, 385, 585);
		frmAdministratormodifikacijaKorisnika.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmAdministratormodifikacijaKorisnika.getContentPane().setLayout(null);
		frmAdministratormodifikacijaKorisnika.setLocationRelativeTo(null);
		
		imeModifikacija = new JTextField();
		imeModifikacija.setColumns(10);
		imeModifikacija.setBounds(51, 88, 260, 20);
		frmAdministratormodifikacijaKorisnika.getContentPane().add(imeModifikacija);
		
		prezimeModifikacija = new JTextField();
		prezimeModifikacija.setColumns(10);
		prezimeModifikacija.setBounds(51, 139, 260, 20);
		frmAdministratormodifikacijaKorisnika.getContentPane().add(prezimeModifikacija);
		
		maticniBrojModifikacija = new JFormattedTextField(Masks.vratiMaskuZaJMBG());
		maticniBrojModifikacija.setColumns(10);
		maticniBrojModifikacija.setBounds(51, 189, 260, 20);
		frmAdministratormodifikacijaKorisnika.getContentPane().add(maticniBrojModifikacija);
		
		brojTelefonaModifikacija = new JFormattedTextField(Masks.vratiMaskuZaTelefon());
		brojTelefonaModifikacija.setColumns(10);
		brojTelefonaModifikacija.setBounds(51, 239, 260, 20);
		frmAdministratormodifikacijaKorisnika.getContentPane().add(brojTelefonaModifikacija);
		
		emailModifikacija = new JFormattedTextField(new EmailFormatter());
		emailModifikacija.setColumns(10);
		emailModifikacija.setBounds(51, 339, 260, 20);
		frmAdministratormodifikacijaKorisnika.getContentPane().add(emailModifikacija);
		
		radnoMjestoModifikacija = new JTextField();
		radnoMjestoModifikacija.setColumns(10);
		radnoMjestoModifikacija.setBounds(51, 389, 260, 20);
		frmAdministratormodifikacijaKorisnika.getContentPane().add(radnoMjestoModifikacija);
		
		JLabel label = new JLabel("Ime");
		label.setBounds(51, 73, 119, 14);
		frmAdministratormodifikacijaKorisnika.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Prezime");
		label_1.setBounds(51, 121, 119, 14);
		frmAdministratormodifikacijaKorisnika.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("Maticni broj");
		label_2.setBounds(51, 172, 119, 14);
		frmAdministratormodifikacijaKorisnika.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("Broj telefona");
		label_3.setBounds(51, 222, 119, 14);
		frmAdministratormodifikacijaKorisnika.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("Email");
		label_4.setBounds(51, 324, 73, 14);
		frmAdministratormodifikacijaKorisnika.getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel("Radno mjesto");
		label_5.setBounds(51, 372, 119, 14);
		frmAdministratormodifikacijaKorisnika.getContentPane().add(label_5);
		
		JLabel label_6 = new JLabel("Datum pocetka rada");
		label_6.setBounds(51, 422, 119, 14);
		frmAdministratormodifikacijaKorisnika.getContentPane().add(label_6);
		
		radnikModifikacija = new JRadioButton("Radnik");
		radnikModifikacija.setBounds(51, 479, 73, 23);
		frmAdministratormodifikacijaKorisnika.getContentPane().add(radnikModifikacija);
		
		menadzerModifikacija = new JRadioButton("Menadzer");
		menadzerModifikacija.setBounds(128, 479, 86, 23);
		frmAdministratormodifikacijaKorisnika.getContentPane().add(menadzerModifikacija);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(radnikModifikacija);
		bg.add(menadzerModifikacija);
		
		listaKorisnikaModifikacija = new AutoCompleteJComboBox(s, Korisnik.class, "email");
		listaKorisnikaModifikacija.setBounds(50, 40, 159, 20);
		frmAdministratormodifikacijaKorisnika.getContentPane().add(listaKorisnikaModifikacija);
		
	    JButton btnModifikacija = new JButton("Ažuriraj");
		btnModifikacija.setBounds(222, 479, 86, 23);
		btnModifikacija.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{				
					if (validirajPolja()){					
						String tipKorisnika="Administrator";
						if (radnikModifikacija.isSelected()) tipKorisnika = "Radnik";
						else tipKorisnika = "Menadzer";
						//Ukoliko kreiranje nije prošlo, baca exception
						boolean state = ba.unsa.etf.si.app.SIDEVS.ViewModel.ModifikacijaKorisnikaVM.ModifikujKorisnika(
								_sesija, 
								imeModifikacija.getText(), 
								prezimeModifikacija.getText(), 
								maticniBrojModifikacija.getText(), 
								brojTelefonaModifikacija.getText(), 
								emailModifikacija.getText(), 
								radnoMjestoModifikacija.getText(), 
								datumPocetkaRadaModifikacija.getText(), 
								adresa.getText(), 
								tipKorisnika,
								imeModifikacija.getText(), 
								prezimeModifikacija.getText());
						if(!state) throw new Exception();
						
						label_obavijest.setForeground(Color.decode("#008000"));
						label_obavijest.setText("Korisnik je uspješno ažuriran");
						resetContent();
						}
					}
				catch(Exception ex){
					label_obavijest.setForeground(Color.decode("#008000"));
					label_obavijest.setText(ex.getMessage());
					logger.error(ex);
					
				}
				//resetContent();
				listaKorisnikaModifikacija.setSelectedItem("");
				radnikModifikacija.setSelected(false);
				menadzerModifikacija.setSelected(false);
			}
		});
		frmAdministratormodifikacijaKorisnika.getContentPane().add(btnModifikacija);
	
		JLabel lblOdaberiKorisnika = new JLabel("Odaberi korisnika");
		lblOdaberiKorisnika.setBounds(50, 23, 169, 14);
		frmAdministratormodifikacijaKorisnika.getContentPane().add(lblOdaberiKorisnika);
		
		JLabel lblAdresa = new JLabel("Adresa");
		lblAdresa.setBounds(51, 270, 56, 16);
		frmAdministratormodifikacijaKorisnika.getContentPane().add(lblAdresa);
		
		adresa = new JTextField();
		adresa.setBounds(51, 289, 260, 22);
		frmAdministratormodifikacijaKorisnika.getContentPane().add(adresa);
		adresa.setColumns(10);
		
		JButton btnUcitaj = new JButton("Ucitaj");
		btnUcitaj.setBounds(214, 39, 97, 23);
		frmAdministratormodifikacijaKorisnika.getContentPane().add(btnUcitaj);
		
		
		

		try {
			datumPocetkaRadaModifikacija = new JFormattedTextField(Masks.vratiMaskuZaDatum());
		} catch (WrongInputException e) {
			logger.error(e);
		}
		datumPocetkaRadaModifikacija.setBounds(50, 440, 102, 20);
		frmAdministratormodifikacijaKorisnika.getContentPane().add(datumPocetkaRadaModifikacija);
		
		label_obavijest = new JLabel("");
		label_obavijest.setBounds(10, 521, 320, 25);
		frmAdministratormodifikacijaKorisnika.getContentPane().add(label_obavijest);
		
		lblNameexamplecom = new JLabel("name@example.com");
		lblNameexamplecom.setForeground(Color.DARK_GRAY);
		lblNameexamplecom.setBounds(212, 324, 128, 14);
		frmAdministratormodifikacijaKorisnika.getContentPane().add(lblNameexamplecom);
		
		
		btnUcitaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{					
					if (validirajOdabir()){
						String txt = listaKorisnikaModifikacija.getSelectedItem().toString();							
						Criteria criteria = _sesija.getSession().createCriteria(Korisnik.class).add(Restrictions.like("email", txt).ignoreCase());
						List<Korisnik> k = criteria.list();
						
						
						String ime2 = k.get(0).getIme();
						String prezime2 = k.get(0).getPrezime();
						String maticniBroj2 = k.get(0).getJmbg();
						String adresa2 = k.get(0).getAdresa();
						String email2 = k.get(0).getEmail();
						String brojTelefona2 = k.get(0).getTelefon();
						String radnoMjesto2 = k.get(0).getRadno_mjesto();
						String datumPocetkaRada2 = k.get(0).getDatum_polaska_rada().toString();
						
						Object obj = k.get(0);
						if (obj.getClass().equals(Menadzer.class))
							menadzerModifikacija.setSelected(true);
						else radnikModifikacija.setSelected(true);
						
						imeModifikacija.setText(ime2);
						prezimeModifikacija.setText(prezime2);
						maticniBrojModifikacija.setText(maticniBroj2);
						brojTelefonaModifikacija.setText(brojTelefona2);
						adresa.setText(adresa2);
						emailModifikacija.setText(email2);
						radnoMjestoModifikacija.setText(radnoMjesto2);
						
						datumPocetkaRada2 = datumPocetkaRada2.substring(0,datumPocetkaRada2.length()-11);
						DateFormat originalFormat = new SimpleDateFormat("yyyy-mm-dd");
						DateFormat targetFormat = new SimpleDateFormat("dd.mm.yyyy");
						Date date = originalFormat.parse(datumPocetkaRada2);
						String formattedDate = targetFormat.format(date);
						datumPocetkaRadaModifikacija.setText(formattedDate);
						datumPocetkaRada2 = formattedDate;
					}
				}
				catch(Exception ex){
					logger.error(ex);
				}
			}
		});
	}
	
	public Boolean validirajPolja() throws WrongInputException{
		String msg = "";
		label_obavijest.setForeground(Color.red);
		if (imeModifikacija.getText().isEmpty()) msg = "Morate unijeti ime";
		else if (prezimeModifikacija.getText().isEmpty()) msg = "Morate unijeti prezime";
		else if (!Validator.validirajMaticniBroj(maticniBrojModifikacija.getText())) msg = "Uneseni JMBG nije ispravan";
		else if (!Validator.validirajTelefonskiBroj(brojTelefonaModifikacija.getText())) msg = "Telefonski broj nije ispravan";
		else if (adresa.getText().isEmpty()) msg = "Morate unijeti adresu";
		else if (!Validator.validirajEmail(emailModifikacija.getText())) msg = "Neispravan email";
		else if (radnoMjestoModifikacija.getText().isEmpty()) msg = "Morate unijeti radno mjesto";
		else if (!Validator.isDateValid(datumPocetkaRadaModifikacija.getText())) msg = "Datum nije validan";
		else if (!radnikModifikacija.isSelected() && !menadzerModifikacija.isSelected()) msg = "Morate odabrati radnu poziciju";
		if(msg != ""){
			label_obavijest.setText(msg);
			return false;
		}
		return true;
	}
	
	private boolean validirajOdabir() {
		String msg = "";
		if (listaKorisnikaModifikacija.getSelectedItem()==null) msg="Morate unijeti kupca";
		else msg = Validator.validirajKorisnika(_sesija, listaKorisnikaModifikacija.getSelectedItem().toString());
		if(msg != ""){
			label_obavijest.setForeground(Color.red);
			label_obavijest.setText(msg);
			return false;
		}
		return true;
	}
	
	public void prikazi() {
		frmAdministratormodifikacijaKorisnika.setVisible(true);
	}
}
