package ba.unsa.etf.si.app.SIDEVS.ViewModel;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.JTextField;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ba.unsa.etf.si.app.SIDEVS.Model.Administrator;
import ba.unsa.etf.si.app.SIDEVS.Model.Korisnik;
import ba.unsa.etf.si.app.SIDEVS.Model.Menadzer;
import ba.unsa.etf.si.app.SIDEVS.Model.Radnik;
import ba.unsa.etf.si.app.SIDEVS.Model.Sessions;
import ba.unsa.etf.si.app.SIDEVS.Model.Skladiste;
import ba.unsa.etf.si.app.SIDEVS.Util.HibernateUtil;
import ba.unsa.etf.si.app.SIDEVS.View.Login;
import ba.unsa.etf.si.app.SIDEVS.View.Radnik.BrisanjeKupca;
import ba.unsa.etf.si.app.SIDEVS.Validation.Conversions;

public class DodavanjeKorisnikaVM {
	final static Logger logger = Logger.getLogger(DodavanjeKorisnikaVM.class);

	private Sessions s;
	
	public DodavanjeKorisnikaVM(Sessions s){
		this.s = s;
	}
	
	public  boolean kreirajKorisnika(String ime, String prezime, String maticniBroj, String brojTelefona, String email, String radnoMjesto, String datumPocetkaRada, String adresa, String tipKorisnika) throws NoSuchAlgorithmException,InvalidKeySpecException {
		try{
			Korisnik k;
			if(tipKorisnika == "Menadzer"){
				k = new Menadzer();
			} else if (tipKorisnika == "Radnik"){
				k = new Radnik();
			} else throw new Exception();
			k.setIme(ime);
			k.setPrezime(prezime);
			k.setJmbg(maticniBroj);
			k.setAdresa(adresa);
			k.setEmail(email);
			k.setTelefon(brojTelefona);
			k.setDatum_polaska_rada(Conversions.dajDatumZaBazu(datumPocetkaRada)); 
			k.setRadno_mjesto(radnoMjesto);
			k.setLozinka("password");
			s.getSession().beginTransaction();
			s.getSession().save(k);
			s.getTrasaction().commit();
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
		return true;		
	}
	
	
	

}
