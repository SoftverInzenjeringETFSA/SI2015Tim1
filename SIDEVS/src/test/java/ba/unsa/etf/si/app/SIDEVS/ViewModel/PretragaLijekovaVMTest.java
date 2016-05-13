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

public class PretragaLijekovaVMTest {

	@SuppressWarnings("deprecation")
	@Test
	public void kolicinaLijekaTest() {
		try
		{
			Sessions s = new Sessions ("admin","password");
			
			Transaction t = s.getSession().beginTransaction();
			Criteria criteria = s.getSession().createCriteria(Korisnik.class).add(Restrictions.like("ime", "menadzer").ignoreCase()).add(Restrictions.like("prezime", "menadzer").ignoreCase());
			List<Korisnik> lista = criteria.list();
			
			
			if (lista.size()!=0 && ba.unsa.etf.si.app.SIDEVS.ViewModel.DodavanjeKorisnikaVM.KreirajKorisnika(s, "menadzer", "menadzer", "1212987123123", "38762112233", "menadzer", "menadzer", "01.02.2003.", "adresa", "Menadzer"))
			{
				s.ubijSesiju();
				Sessions s_Menadzer = new Sessions ("menadzer","password");
				ba.unsa.etf.si.app.SIDEVS.ViewModel.LijekVM.dodajLijek((long)1, "Aspirin", "Bosnalijek");
				Skladiste skladiste = new Skladiste();
				skladiste.setBroj_skladista(1);
				Lijek lijek = new Lijek();
				lijek.setId((long)1); lijek.setNaziv("Aspirin"); lijek.setProizvodjac("Bosnalijek");
				ba.unsa.etf.si.app.SIDEVS.ViewModel.LotVM.dodajLot("123123", 0.5, 3.5, new Date(2020,6,6), 50, lijek, skladiste, 30);
				long ocekivano =30;
				long rezultat = ba.unsa.etf.si.app.SIDEVS.ViewModel.PretragaLijekovaVM.kolicinaLijeka(s_Menadzer, "Aspirin", "1");
				assertTrue(rezultat==ocekivano);
			}
		}
		catch (Exception ex)
		{
			System.out.println(ex.getMessage());
			fail ("Bacen izuzetak!");
		}
	}

}
