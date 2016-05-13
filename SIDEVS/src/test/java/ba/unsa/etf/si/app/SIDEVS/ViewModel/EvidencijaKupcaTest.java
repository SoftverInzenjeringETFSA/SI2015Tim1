package ba.unsa.etf.si.app.SIDEVS.ViewModel;

import static org.junit.Assert.*;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import ba.unsa.etf.si.app.SIDEVS.ViewModel.*;
import ba.unsa.etf.si.app.SIDEVS.Model.*;

import org.junit.Test;

public class EvidencijaKupcaTest {

	@Test
	public void EvidencijaKupca_Test() {
		try
		{
			Sessions s_admin = new Sessions ("admin","password");
			if (ba.unsa.etf.si.app.SIDEVS.ViewModel.DodavanjeKorisnikaVM.KreirajKorisnika(s_admin, "radnik", "radnik", "1212965000123", "38762110033", "radnik", "radnik", "01.05.2015.", "adresa bb", "Radnik"))
			{
				Sessions s_radnik = new Sessions ("radnik","password");
				ba.unsa.etf.si.app.SIDEVS.ViewModel.EvidencijaKupca.dodajKupca("Apoteka 1","Ul. Adresa bb");
				
				Transaction t = s_radnik.getSession().beginTransaction();
				Criteria criteria = s_radnik.getSession().createCriteria(Kupac.class).add(Restrictions.like("naziv", "Apoteka 1").ignoreCase());
				List<Kupac> lista = criteria.list();
				
				assertTrue(lista.get(0).getNaziv()=="Apoteka 1" && lista.get(0).getAdresa()=="Ul. Adresa bb");
			}
			assertFalse(false);
		}
		catch (Exception ex)
		{
			fail("Bacen izuzetak");
		}
		
	}

}
