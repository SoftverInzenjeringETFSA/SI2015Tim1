package ba.unsa.etf.si.app.SIDEVS.ViewModel;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.JTextField;

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

public class DodavanjeKorisnikaVM {

	public static boolean KreirajKorisnika(Sessions ses, String ime, String prezime, String maticniBroj, String brojTelefona, String email, String radnoMjesto, String datumPocetkaRada, String adresa, String tipKorisnika) throws NoSuchAlgorithmException,InvalidKeySpecException {
		try{
			Transaction t = ses.getSession().beginTransaction();
			if(tipKorisnika == "Menadzer"){
				Menadzer m = new Menadzer();
				m.setIme(ime);
				m.setPrezime(prezime);
				m.setJmbg(maticniBroj);
				m.setAdresa(adresa);
				m.setEmail(email);
				m.setTelefon(brojTelefona);
				m.setDatum_polaska_rada(new Date()); //POTREBNO PODESITI NA datumPocetkaRada
				m.setRadno_mjesto(radnoMjesto);
				m.setLozinka("password");
				ses.getSession().save(m);
			}
			else if (tipKorisnika == "Radnik"){
				Radnik r = new Radnik();
				r.setIme(ime);
				r.setPrezime(prezime);
				r.setJmbg(maticniBroj);
				r.setAdresa("Adresa bb");
				r.setEmail(email);
				r.setTelefon(brojTelefona);
				r.setDatum_polaska_rada(new Date()); //POTREBNO PODESITI NA datumPocetkaRada
				r.setRadno_mjesto(radnoMjesto);
				r.setLozinka("password");
				ses.getSession().save(r);
			}		
			t.commit();		
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;		
	}
}
