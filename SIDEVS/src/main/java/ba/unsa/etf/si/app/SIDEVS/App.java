package ba.unsa.etf.si.app.SIDEVS;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ba.unsa.etf.si.app.SIDEVS.Class.Administrator;
import ba.unsa.etf.si.app.SIDEVS.Class.Korisnik;
import ba.unsa.etf.si.app.SIDEVS.Class.Radnik;
import ba.unsa.etf.si.app.SIDEVS.Class.Skladiste;
import ba.unsa.etf.si.app.SIDEVS.Util.HibernateUtil;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		/*
	 	kreirajSkladiste(session, 3);
		try {
			Radnik k = new Radnik();
			k.setIme("Korisnik");
			k.setPrezime("Korisnicki");
			k.setJmbg("1234567891234");
			k.setAdresa("Adresa bb");
			k.setEmail("example@test.com");
			k.setTelefon("012353451");
			k.setDatum_polaska_rada(new Date());
			k.setRadno_mjesto("radnik");
			k.setLozinka("password");
			kreirajKorisnikaAdmin(session, k);
			System.out.println("Korisnik kreiran!");
		} catch (Exception ex) {
			System.out.println(ex);
		}
		*/
		dajSveKorisnike(session);
	}

	private static void kreirajKorisnikaAdmin(Session session, Radnik a) {
		Transaction t = session.beginTransaction();
		session.save(a);
		t.commit();
	}
	
	private static void dajSveKorisnike(Session session){
		Transaction t = session.beginTransaction();
		List<Korisnik> listKorisnika = session.createCriteria(Korisnik.class).list();
		for (Iterator iterator = listKorisnika.iterator(); iterator.hasNext();) {
			Korisnik korisnik = (Korisnik) iterator.next();
			System.out.println(korisnik.getIme());
		}
	}

	private static void kreirajSkladiste(Session session, int broj_skladista) {
		Transaction t = session.beginTransaction();
		// Provjera da li postoji skladiste
		if (((Skladiste) session.get(Skladiste.class, broj_skladista)) == null) {
			System.out.println("Pravim skaldište...");
			Skladiste s = new Skladiste();
			s.setBroj_skladista(broj_skladista);
			Integer id = (Integer) session.save(s);
			System.out.println("Novo skladište sa brojem " + id);
			t.commit();
		} else {
			System.out.println("Skladište sa ovim brojem već postoji!");
		}
	}

}
