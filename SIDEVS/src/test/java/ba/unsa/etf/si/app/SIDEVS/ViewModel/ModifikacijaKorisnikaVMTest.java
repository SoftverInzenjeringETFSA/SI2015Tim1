package ba.unsa.etf.si.app.SIDEVS.ViewModel;

import static org.junit.Assert.*;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import ba.unsa.etf.si.app.SIDEVS.ViewModel.*;
import ba.unsa.etf.si.app.SIDEVS.Model.*;
import org.junit.Test;

public class ModifikacijaKorisnikaVMTest {

	@Test
	public void ModifikacijaKorisnikaTest() {
		try
		{
			Sessions s = new Sessions ("admin","password");
			Transaction t = s.getSession().beginTransaction();
			Criteria criteria = s.getSession().createCriteria(Korisnik.class).add(Restrictions.like("ime", "korisnik").ignoreCase()).add(Restrictions.like("prezime", "korisnik").ignoreCase());
			
			List<Korisnik> lista = criteria.list();
			
			if (!lista.isEmpty())
			{
				Korisnik k = lista.get(0);
				assertTrue(ba.unsa.etf.si.app.SIDEVS.ViewModel.ModifikacijaKorisnikaVM.ModifikujKorisnika(s, "korisnikNovo", k.getPrezime(), k.getJmbg(), k.getTelefon(), k.getEmail(), k.getRadno_mjesto(), k.getDatum_polaska_rada().toString(), k.getAdresa(), k.getTelefon(), "korisnik", "korisnik"));
			}
			else
			{
				boolean kreirano = ba.unsa.etf.si.app.SIDEVS.ViewModel.DodavanjeKorisnikaVM.KreirajKorisnika(s, "korisnik", "korisnik", "1212992123123", "38761111222", "korsnik", "Radnik", "01.03.2013.", "adresa", "Radnik");
				if (kreirano)
				{
					Transaction t1 = s.getSession().beginTransaction();
					Criteria criteria1 = s.getSession().createCriteria(Korisnik.class).add(Restrictions.like("ime", "korisnik").ignoreCase()).add(Restrictions.like("prezime", "korisnik").ignoreCase());
					
					List<Korisnik> lista1 = criteria.list();
					Korisnik k = lista1.get(0);
					assertTrue(ba.unsa.etf.si.app.SIDEVS.ViewModel.ModifikacijaKorisnikaVM.ModifikujKorisnika(s, "korisnikNovo", k.getPrezime(), k.getJmbg(), k.getTelefon(), k.getEmail(), k.getRadno_mjesto(), k.getDatum_polaska_rada().toString(), k.getAdresa(), k.getTelefon(), "korisnik", "korisnik"));
				}		
			}
		}
		catch (Exception ex)
		{
			fail ("Bacen izuzetak: " + ex.getMessage()+" !");
		}
	}
	
	@Test
	public void ModifikacijaKorisnikaExceptionTest() {
		try
		{
			Sessions s=new Sessions("admin","password");
			Transaction t = s.getSession().beginTransaction();
			Criteria criteria = s.getSession().createCriteria(Korisnik.class).add(Restrictions.like("ime", "korisnik").ignoreCase()).add(Restrictions.like("prezime", "korisnik").ignoreCase());
			
			List<Korisnik> lista = criteria.list();
			
			if (!lista.isEmpty())
			{
				ba.unsa.etf.si.app.SIDEVS.ViewModel.BrisanjeKorisnikaVM.BrisiKorisnika(s, "korisnik", "korisnik");
				Transaction t1 = s.getSession().beginTransaction();
				Criteria criteria1 = s.getSession().createCriteria(Korisnik.class).add(Restrictions.like("ime", "korisnik").ignoreCase()).add(Restrictions.like("prezime", "korisnik").ignoreCase());
				List<Korisnik> lista1 = criteria.list();
				Korisnik k = lista.get(0);
				assertFalse(ba.unsa.etf.si.app.SIDEVS.ViewModel.ModifikacijaKorisnikaVM.ModifikujKorisnika(s, "korisnikNovo", k.getPrezime(), k.getJmbg(), k.getTelefon(), k.getEmail(), k.getRadno_mjesto(), k.getDatum_polaska_rada().toString(), k.getAdresa(), k.getTelefon(), "korisnik", "korisnik"));
			}
			else
			{
				Korisnik k = lista.get(0);
				assertFalse(ba.unsa.etf.si.app.SIDEVS.ViewModel.ModifikacijaKorisnikaVM.ModifikujKorisnika(s, "korisnikNovo", k.getPrezime(), k.getJmbg(), k.getTelefon(), k.getEmail(), k.getRadno_mjesto(), k.getDatum_polaska_rada().toString(), k.getAdresa(), k.getTelefon(), "korisnik", "korisnik"));	
			}
		}
		catch (Exception ex)
		{
			assertTrue(true);
		}
	}

}
