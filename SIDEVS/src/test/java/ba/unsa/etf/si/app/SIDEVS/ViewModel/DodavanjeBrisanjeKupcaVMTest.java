package ba.unsa.etf.si.app.SIDEVS.ViewModel;

import static org.junit.Assert.*;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ba.unsa.etf.si.app.SIDEVS.Model.Administrator;
import ba.unsa.etf.si.app.SIDEVS.Model.Korisnik;
import ba.unsa.etf.si.app.SIDEVS.Model.Sessions;
import ba.unsa.etf.si.app.SIDEVS.Util.HibernateUtil;

public class DodavanjeBrisanjeKupcaVMTest {

	private Sessions s;
	private Administrator admin;
	private String ime;
	private String prezime;
	private String maticniBroj;
	private String brojTelefona;
	private String email;
	private String radnoMjesto;
	private String datumPocetkaRada;
	private String adresa;

	@Before
	public void atBegin() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			// provjera ako admin vec postoji da se ne dodaje
			try {
				s = new Sessions("admin", "password");
			} catch (Exception e) {
				if (s == null) {
					admin = new Administrator();
					admin.setIme("User");
					admin.setPrezime("User");
					admin.setJmbg("1234567891234");
					admin.setAdresa("Adresa bb");
					admin.setEmail("admin");
					admin.setTelefon("012353451");
					admin.setDatum_polaska_rada(new Date());
					admin.setRadno_mjesto("admin");
					admin.setLozinka("password");
					Transaction t = session.beginTransaction();
					session.save(admin);
					t.commit();
					s = new Sessions("admin", "password");
				}
			}
			// dummy
			ime = "User";
			prezime = "Test";
			maticniBroj = "1234567891234";
			adresa = "Adresa bb";
			email = "user_test";
			brojTelefona = "5324231";
			radnoMjesto = "Pozicija";
			datumPocetkaRada = "12.12.2002";
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}

	@Test
	public void dodajBrisiKorisnikaMenadzer() {
		try {
			DodavanjeKorisnikaVM dkvm = new DodavanjeKorisnikaVM(s);
			String tipKorisnika = "Menadzer";
			dkvm.kreirajKorisnika(ime, prezime, maticniBroj, brojTelefona, email, radnoMjesto, datumPocetkaRada, adresa,
					tipKorisnika);
			assertTrue(true);
			BrisanjeKorisnikaVM bvm = new BrisanjeKorisnikaVM(s);
			bvm.BrisiKorisnika("user_test");
			assertTrue(true);
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
	@Test
	public void dodajBrisiKorisnikaRadnik() {
		try {
			DodavanjeKorisnikaVM dkvm = new DodavanjeKorisnikaVM(s);
			String tipKorisnika = "Radnik";
			dkvm.kreirajKorisnika(ime, prezime, maticniBroj, brojTelefona, email, radnoMjesto, datumPocetkaRada, adresa,
					tipKorisnika);
			assertTrue(true);
			BrisanjeKorisnikaVM bvm = new BrisanjeKorisnikaVM(s);
			bvm.BrisiKorisnika("user_test");
			assertTrue(true);
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
}
