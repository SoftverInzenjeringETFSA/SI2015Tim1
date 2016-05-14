package ba.unsa.etf.si.app.SIDEVS.View.Admin;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

import Exceptions.WrongInputException;
import ba.unsa.etf.si.app.SIDEVS.Model.Sessions;
import ba.unsa.etf.si.app.SIDEVS.Validation.EmailFormatter;
import ba.unsa.etf.si.app.SIDEVS.Validation.Validator;
import ba.unsa.etf.si.app.SIDEVS.View.Masks;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

public class DodavanjeKorisnika {
	
	final static Logger logger = Logger.getLogger(DodavanjeKorisnika.class);
	
	private Sessions _sesija;
	private JFrame frmAdministratorDodavanjeKorisnika;
	private JTextField ime;
	private JTextField prezime;
	private JTextField radnoMjesto;
	private JTextField adresa;
	private JFormattedTextField datumPocetkaRada;
	private JFormattedTextField maticniBroj;
	private JFormattedTextField brojTelefona;
	private JLabel label_obavijest;
	private JRadioButton radnik;
	private JRadioButton menadzer;
	private JFormattedTextField email;
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
					logger.error(e);
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
		adresa.setText("");
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAdministratorDodavanjeKorisnika = new JFrame();
		frmAdministratorDodavanjeKorisnika.setTitle("Dodavanje korisnika");
		frmAdministratorDodavanjeKorisnika.setBounds(100, 100, 255, 560);
		frmAdministratorDodavanjeKorisnika.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmAdministratorDodavanjeKorisnika.getContentPane().setLayout(null);
		frmAdministratorDodavanjeKorisnika.setLocationRelativeTo(null);
		
		ime = new JTextField();
		ime.setBounds(45, 41, 140, 20);
		frmAdministratorDodavanjeKorisnika.getContentPane().add(ime);
		ime.setColumns(10);
		
		prezime = new JTextField();
		prezime.setBounds(45, 89, 140, 20);
		frmAdministratorDodavanjeKorisnika.getContentPane().add(prezime);
		prezime.setColumns(10);
		
		radnoMjesto = new JTextField();
		radnoMjesto.setBounds(45, 339, 140, 20);
		frmAdministratorDodavanjeKorisnika.getContentPane().add(radnoMjesto);
		radnoMjesto.setColumns(10);
		
		adresa = new JTextField();
		adresa.setBounds(45, 236, 140, 20);
		frmAdministratorDodavanjeKorisnika.getContentPane().add(adresa);
		adresa.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Ime");
		lblNewLabel.setBounds(45, 23, 119, 14);
		frmAdministratorDodavanjeKorisnika.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Prezime");
		lblNewLabel_1.setBounds(45, 74, 119, 14);
		frmAdministratorDodavanjeKorisnika.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Maticni broj");
		lblNewLabel_2.setBounds(45, 122, 119, 14);
		frmAdministratorDodavanjeKorisnika.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Broj telefona");
		lblNewLabel_3.setBounds(45, 170, 119, 14);
		frmAdministratorDodavanjeKorisnika.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Email");
		lblNewLabel_4.setBounds(45, 276, 119, 14);
		frmAdministratorDodavanjeKorisnika.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Radno mjesto");
		lblNewLabel_5.setBounds(45, 325, 119, 14);
		frmAdministratorDodavanjeKorisnika.getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Datum pocetka rada");
		lblNewLabel_6.setBounds(45, 372, 119, 14);
		frmAdministratorDodavanjeKorisnika.getContentPane().add(lblNewLabel_6);
		
		
		
		radnik = new JRadioButton("Radnik");
		radnik.setBounds(29, 417, 72, 23);
		frmAdministratorDodavanjeKorisnika.getContentPane().add(radnik);
		
		menadzer = new JRadioButton("Menadzer");
		menadzer.setBounds(105, 417, 86, 23);
		frmAdministratorDodavanjeKorisnika.getContentPane().add(menadzer);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(radnik);
		bg.add(menadzer);
		
		JButton btnDodaj = new JButton("Dodaj");
		btnDodaj.setBounds(45, 461, 140, 23);
		btnDodaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					if (validirajPolja()){
						
						label_obavijest.setText("");
						
						//Baca izuzetak ukoliko ništa nije checkirano
						String tipKorisnika;
						if(menadzer.isSelected()) tipKorisnika = "Menadzer";
						else if (radnik.isSelected()) tipKorisnika = "Radnik";
						else throw new Exception();
											String s = email.getText();
						//Ukoliko kreiranje nije prošlo, baca exception
						boolean state = ba.unsa.etf.si.app.SIDEVS.ViewModel.DodavanjeKorisnikaVM.KreirajKorisnika(
								_sesija, 
								ime.getText(), 
								prezime.getText(), 
								maticniBroj.getText(), 
								brojTelefona.getText(), 
								email.getText(), 
								radnoMjesto.getText(), 
								datumPocetkaRada.getText(), 
								adresa.getText(), 
								tipKorisnika);
						if(!state) {
							throw new Exception();
						}
						
						//Javlja da je korisnik kreiran ukoliko je sve prošlo ok
						label_obavijest.setForeground(Color.decode("#008000"));
						label_obavijest.setText("Korisnik je uspješno dodan");
						resetContent();
						}
					}
				catch(Exception ex){
					label_obavijest.setForeground(Color.decode("#008000"));
					label_obavijest.setText(ex.getMessage());
					logger.error(ex);
					
				}

			}
		});
		frmAdministratorDodavanjeKorisnika.getContentPane().add(btnDodaj);
		
		JLabel lblAdresa = new JLabel("Adresa");
		lblAdresa.setBounds(45, 219, 56, 14);
		frmAdministratorDodavanjeKorisnika.getContentPane().add(lblAdresa);
		
		
		try {
			datumPocetkaRada = new JFormattedTextField(Masks.vratiMaskuZaDatum());
		} catch (WrongInputException e) {
			logger.error(e);
		}
		
		
		datumPocetkaRada.setBounds(45, 390, 140, 20);
		frmAdministratorDodavanjeKorisnika.getContentPane().add(datumPocetkaRada);
		
		maticniBroj = new JFormattedTextField(Masks.vratiMaskuZaJMBG());
		maticniBroj.setBounds(45, 139, 140, 20);
		frmAdministratorDodavanjeKorisnika.getContentPane().add(maticniBroj);
		
		label_obavijest = new JLabel("");
		label_obavijest.setBounds(10, 498, 189, 20);
		frmAdministratorDodavanjeKorisnika.getContentPane().add(label_obavijest);
		
		brojTelefona = new JFormattedTextField(Masks.vratiMaskuZaTelefon());
		brojTelefona.setBounds(45, 195, 140, 20);
		frmAdministratorDodavanjeKorisnika.getContentPane().add(brojTelefona);
		
		email = new JFormattedTextField(new EmailFormatter());
		email.setBounds(45, 289, 140, 23);
		frmAdministratorDodavanjeKorisnika.getContentPane().add(email);
		
	}
	public Boolean validirajPolja() throws WrongInputException{
		String msg = "";
		label_obavijest.setForeground(Color.red);
		if (ime.getText().isEmpty()) msg = "Morate unijeti ime";
		else if (prezime.getText().isEmpty()) msg = "Morate unijeti prezime";
		else if (!Validator.validirajMaticniBroj(maticniBroj.getText())) msg = "Uneseni JMBG nije ispravan";
		else if (!Validator.validirajTelefonskiBroj(brojTelefona.getText())) msg = "Telefonski broj nije ispravan";
		else if (adresa.getText().isEmpty()) msg = "Morate unijeti adresu";
		else if (!Validator.validirajEmail(email.getText())) msg = "Neispravan email";
		else if (radnoMjesto.getText().isEmpty()) msg = "Morate unijeti radno mjesto";
		else if (!Validator.isDateValid(datumPocetkaRada.getText())) msg = "Datum nije validan";
		else if (!radnik.isSelected() && !menadzer.isSelected()) msg = "Morate odabrati radnu poziciju";
		if(msg != ""){
			label_obavijest.setText(msg);
			return false;
		}
		return true;
	}

	
	public void prikazi() {
		frmAdministratorDodavanjeKorisnika.setVisible(true);
	}
}
