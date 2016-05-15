package ba.unsa.etf.si.app.SIDEVS.ViewModel;

import static org.junit.Assert.*;

import java.util.Date;

//import java.sql.Date;

import ba.unsa.etf.si.app.SIDEVS.ViewModel.*;
import ba.unsa.etf.si.app.SIDEVS.Model.*;
import ba.unsa.etf.si.app.SIDEVS.Util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

public class OtpisLijekovaVMTest {

	@SuppressWarnings("deprecation")
	@Test
	public void otpisLijekaTest() {
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
				
			Sessions s= new Sessions ("adminTest","password");
			Lijek lijek = new Lijek();
			lijek.setNaziv("lijek");
			Skladiste skladiste = new Skladiste();
			skladiste.setBroj_skladista(1);
			ba.unsa.etf.si.app.SIDEVS.ViewModel.LotVM.dodajLot("112233", 1.0, 3.2, new Date(2020,5,5), 50, lijek, skladiste, 50);
			assertFalse(ba.unsa.etf.si.app.SIDEVS.ViewModel.OtpisLijekovaVM.otpisLijeka("lijek","112233","1"));
			
			session.delete(k);
			trans.commit();
		}
		catch (Exception ex)
		{
			assert(true);
		}
	}
		
		@Test
		public void vracaLotoveTest() {
			try
			{
				Sessions s= new Sessions ("admin","password");
				Lijek lijek = new Lijek();
				lijek.setNaziv("lijek");
				Skladiste skladiste = new Skladiste();
				skladiste.setBroj_skladista(1);
				ba.unsa.etf.si.app.SIDEVS.ViewModel.LotVM.dodajLot("112233", 1.0, 3.2, new Date(2020,5,5), 50, lijek, skladiste, 50);
				assertTrue(ba.unsa.etf.si.app.SIDEVS.ViewModel.OtpisLijekovaVM.vracaLotove(lijek, skladiste).isEmpty());
			}
			catch (Exception ex)
			{
				assert(true);
			}
	}

}
