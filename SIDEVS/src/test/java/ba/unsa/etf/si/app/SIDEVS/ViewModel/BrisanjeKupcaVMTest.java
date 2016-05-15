package ba.unsa.etf.si.app.SIDEVS.ViewModel;

import static org.junit.Assert.*;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ba.unsa.etf.si.app.SIDEVS.ViewModel.*;
import ba.unsa.etf.si.app.SIDEVS.Model.*;
import ba.unsa.etf.si.app.SIDEVS.Util.HibernateUtil;

import org.junit.Test;

public class BrisanjeKupcaVMTest {

	@Test
	public void BrisanjeKupcaTest() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Administrator k = new Administrator();
			k.setIme("adminBazaa");
			k.setPrezime("Korisnicki");
			k.setJmbg("1234567891234");
			k.setAdresa("Adresa bb");
			k.setEmail("adminBazaa");
			k.setTelefon("012353451");
			k.setDatum_polaska_rada(new Date());
			k.setRadno_mjesto("radnik");
			k.setLozinka("password");
			Transaction t = session.beginTransaction();
			session.save(k);
			t.commit();


		Sessions s = new Sessions ("adminBazaa","password");
		
		DodavanjeKorisnikaVM dk = new DodavanjeKorisnikaVM();
		boolean kreirano = dk.KreirajKorisnika(s, "k", "k", "1234567890123", "062/111-333", "k@si.com", "Radnik", "01.05.2011.", "adresa", "Radnik");
		if (kreirano)
		{
			Sessions ses=new Sessions ("k@si.com","password");
			EvidencijaKupca ek = new EvidencijaKupca(ses);
			ek.dodajKupca("naziiv", "adresssaaa");
			BrisanjeKupcaVM bk = new BrisanjeKupcaVM();
			assertTrue(bk.BrisiKupca(ses, "naziiv"));
			
		}
		session.delete(k);
		t.commit();
			
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
	
	@Test
	public void BrisanjeKupcaGreskaTest() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Administrator k = new Administrator();
			k.setIme("adminBazaa");
			k.setPrezime("Korisnicki");
			k.setJmbg("1234567891234");
			k.setAdresa("Adresa bb");
			k.setEmail("adminBazaa");
			k.setTelefon("012353451");
			k.setDatum_polaska_rada(new Date());
			k.setRadno_mjesto("radnik");
			k.setLozinka("password");
			Transaction t = session.beginTransaction();
			session.save(k);
			t.commit();


		Sessions s = new Sessions ("adminBazaa","password");
		
		DodavanjeKorisnikaVM dk = new DodavanjeKorisnikaVM();
		boolean kreirano = dk.KreirajKorisnika(s, "k", "k", "1234567890123", "062/111-333", "k@si.com", "Radnik", "01.05.2011.", "adresa", "Radnik");
		if (kreirano)
		{
			Sessions ses=new Sessions ("k@si.com","password");
			EvidencijaKupca ek = new EvidencijaKupca(ses);
			ek.dodajKupca("naziiv", "adresssaaa");
			BrisanjeKupcaVM bk = new BrisanjeKupcaVM();
			assertFalse(bk.BrisiKupca(ses, "naziv_"));
			
		}
		session.delete(k);
		t.commit();
			
		} catch (Exception ex) {
			assertTrue(true);
		}
	}

}
