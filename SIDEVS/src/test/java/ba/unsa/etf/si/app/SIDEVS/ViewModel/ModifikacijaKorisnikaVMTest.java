package ba.unsa.etf.si.app.SIDEVS.ViewModel;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import ba.unsa.etf.si.app.SIDEVS.ViewModel.*;
import ba.unsa.etf.si.app.SIDEVS.Model.*;
import ba.unsa.etf.si.app.SIDEVS.Util.HibernateUtil;

import org.junit.Test;

public class ModifikacijaKorisnikaVMTest {

	@Test
	public void ModifikacijaKorisnikaTest() {
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
		assertTrue(kreirano);
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
}
