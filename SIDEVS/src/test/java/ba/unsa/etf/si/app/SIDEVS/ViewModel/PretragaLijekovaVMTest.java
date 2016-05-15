package ba.unsa.etf.si.app.SIDEVS.ViewModel;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction trans = session.beginTransaction();
			Administrator k = null;
			//kreiranje administratora
				k = new Administrator();
				k.setIme("adminTest");
				k.setPrezime("Korisnicki");
				k.setJmbg("1234567891234");
				k.setAdresa("Adresa bb");
				k.setEmail("adminTest");
				k.setTelefon("012353451");
				k.setDatum_polaska_rada(new Date());
				k.setRadno_mjesto("radnik");
				k.setLozinka("password");
				
				session.save(k);
				trans.commit();
				
				Sessions s = new Sessions ("adminTest","password");
				if (ba.unsa.etf.si.app.SIDEVS.ViewModel.DodavanjeKorisnikaVM.KreirajKorisnika(s, "menadzer", "menadzer", "0123456789123", "061/999-258", "menadzer@s.ba", "menadzer", "02.05.2015.", "ad", "Menadzer"))
				{

					Sessions s_Menadzer = new Sessions ("menadzer@s.ba","password");
					ba.unsa.etf.si.app.SIDEVS.ViewModel.LijekVM.dodajLijek("Aspirin", "Bosnalijek");
					Skladiste skladiste = new Skladiste();
					skladiste.setBroj_skladista(1);
					Lijek lijek = new Lijek();
					lijek.setNaziv("Aspirin"); lijek.setProizvodjac("Bosnalijek");
					ba.unsa.etf.si.app.SIDEVS.ViewModel.LotVM.dodajLot("123123", 0.5, 3.5, new Date(2020,6,6), 50, lijek, skladiste, 30);
					Map<Integer, Integer> rezultat = ba.unsa.etf.si.app.SIDEVS.ViewModel.PretragaLijekovaVM.dajKolicinuLijekaUSkladistu("Aspirin");
					assertFalse(rezultat.isEmpty());
				}
			session.delete(k);
			trans.commit();
		}
		catch (Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}

}
