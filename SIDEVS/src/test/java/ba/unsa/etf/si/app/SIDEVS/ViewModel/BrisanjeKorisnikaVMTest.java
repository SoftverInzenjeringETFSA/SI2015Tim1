package ba.unsa.etf.si.app.SIDEVS.ViewModel;

import static org.junit.Assert.*;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import ba.unsa.etf.si.app.SIDEVS.ViewModel.*;
import ba.unsa.etf.si.app.SIDEVS.Model.*;
import org.junit.Test;

public class BrisanjeKorisnikaVMTest {

	@Test
	public void BrisanjeKorisnikaTest() {
		try
		{
			Sessions s = new Sessions ("admin","password");
			if (ba.unsa.etf.si.app.SIDEVS.ViewModel.DodavanjeKorisnikaVM.KreirajKorisnika(s, "Ime", "Prezimenko", "1010991123123", "38761001123", "iprezimenko", "radnik", "12.03.2010.", "Adresa bb", "Radnik"))
			{
				assertTrue(ba.unsa.etf.si.app.SIDEVS.ViewModel.BrisanjeKorisnikaVM.BrisiKorisnika(s, "Ime", "Prezimenko"));
				/*
				Transaction t = s.getSession().beginTransaction();
				Criteria criteria = s.getSession().createCriteria(Korisnik.class).add(Restrictions.like("email", "iprezimenko").ignoreCase());
				
				List<Korisnik> lista = criteria.list();
				Korisnik k = lista.get(0);
				
				s.getSession().delete(k);
				t.commit();*/
			}
			else assertFalse(true);
		}
		catch (Exception ex)
		{
			fail("Bacen izuzetak!" + ex.getMessage());
		}
		
	}

}
