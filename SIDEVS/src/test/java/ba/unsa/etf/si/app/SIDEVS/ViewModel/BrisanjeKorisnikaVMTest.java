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

public class BrisanjeKorisnikaVMTest {

	@Test
	public void BrisanjeKorisnikaTest() {
		try
		{
			Session session = HibernateUtil.getSessionFactory().openSession();
				System.out.println("Kreiram!");
				Administrator k = new Administrator();
				k.setIme("Korisnik");
				k.setPrezime("Korisnicki");
				k.setJmbg("1234567891234");
				k.setAdresa("Adresa bb");
				k.setEmail("a");
				k.setTelefon("012353451");
				k.setDatum_polaska_rada(new Date());
				k.setRadno_mjesto("admin");
				k.setLozinka("p");
				Transaction t = session.beginTransaction();
				session.save(k);
				t.commit();
				System.out.println("Korisnik kreiran!");
				
			Sessions s = new Sessions ("a","p");
			if (ba.unsa.etf.si.app.SIDEVS.ViewModel.DodavanjeKorisnikaVM.KreirajKorisnika(s, "Ime", "Prezimenko", "1010991123123", "38761001123", "iprezimenko", "radnik", "12.03.2010.", "Adresa bb", "Radnik"))
			{
				assertTrue(ba.unsa.etf.si.app.SIDEVS.ViewModel.BrisanjeKorisnikaVM.BrisiKorisnika(s, "Ime", "Prezimenko"));
			}
			else assertFalse(true);
			
			session.delete(k);
		}
		catch (Exception ex)
		{
			fail("Bacen izuzetak!" + ex.getMessage());
		}
		
	}

}
