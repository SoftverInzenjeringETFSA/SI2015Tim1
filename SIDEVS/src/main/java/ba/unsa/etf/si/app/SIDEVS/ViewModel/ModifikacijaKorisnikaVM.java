package ba.unsa.etf.si.app.SIDEVS.ViewModel;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.JTextField;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import ba.unsa.etf.si.app.SIDEVS.Model.Administrator;
import ba.unsa.etf.si.app.SIDEVS.Model.Korisnik;
import ba.unsa.etf.si.app.SIDEVS.Model.Menadzer;
import ba.unsa.etf.si.app.SIDEVS.Model.Radnik;
import ba.unsa.etf.si.app.SIDEVS.Model.Sessions;
import ba.unsa.etf.si.app.SIDEVS.Model.Skladiste;
import ba.unsa.etf.si.app.SIDEVS.Util.HibernateUtil;
import ba.unsa.etf.si.app.SIDEVS.Validation.Conversions;
import ba.unsa.etf.si.app.SIDEVS.View.Login;

public class ModifikacijaKorisnikaVM {
	final static Logger logger = Logger.getLogger(ModifikacijaKorisnikaVM.class);
	public static boolean ModifikujKorisnika(Sessions ses, String ime, String prezime, String maticniBroj, String brojTelefona, String email, String radnoMjesto, String datumPocetkaRada, String adresa, String tipKorisnika, String imeTxt, String prezimeTxt) throws NoSuchAlgorithmException,InvalidKeySpecException {
		try{
			
			Transaction t = ses.getSession().beginTransaction();
			Criteria criteria = ses.getSession().createCriteria(Korisnik.class).add(Restrictions.like("ime", imeTxt).ignoreCase()).add(Restrictions.like("prezime", prezimeTxt).ignoreCase());
			
			List<Korisnik> lista = criteria.list();
			Korisnik k = lista.get(0);
				
			//Dodati dio za promjenu Radnik/Menadžer
			//Nije moguća eksplicitna konverzija, pa se mora obrisati postojeći unos i zamijeniti novim
			
			k.setIme(ime);		
			k.setPrezime(prezime);
			k.setJmbg(maticniBroj);
			k.setAdresa(adresa);
			k.setEmail(email);
			k.setTelefon(brojTelefona);
			k.setDatum_polaska_rada(Conversions.dajDatumZaBazu(datumPocetkaRada));
			k.setRadno_mjesto(radnoMjesto);
			
			ses.getSession().update(k);		
			t.commit();
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
			return false;
		}
		return true;		
	}
	
	
	
}
