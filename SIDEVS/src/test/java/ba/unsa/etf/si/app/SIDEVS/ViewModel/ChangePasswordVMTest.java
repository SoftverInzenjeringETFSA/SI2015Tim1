package ba.unsa.etf.si.app.SIDEVS.ViewModel;

import static org.junit.Assert.*;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ba.unsa.etf.si.app.SIDEVS.Model.*;
import ba.unsa.etf.si.app.SIDEVS.Util.HibernateUtil;

import org.junit.Test;

public class ChangePasswordVMTest {

	@Test
	public void ChangePasswordTest() {
		try {
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
				
				Sessions s_admin = new Sessions ("adminTest","password");
				ba.unsa.etf.si.app.SIDEVS.ViewModel.DodavanjeKorisnikaVM.KreirajKorisnika(s_admin, "ime", "ime", "1234567893210", "061/789-321", "ime@sitim.com", "radnik", "01.05.2010.", "adresa", "Radnik");
			    s_admin.ubijSesiju();
				char[] newPassword= "newPassword".toCharArray();
			char[] oldPassword="password".toCharArray();
			 
			boolean promjena= ChangePasswordVM.ChangePassword("ime", oldPassword, newPassword, newPassword);
			ba.unsa.etf.si.app.SIDEVS.ViewModel.BrisanjeKorisnikaVM.BrisiKorisnika(s_admin, "ime", "ime");
			assertTrue(promjena);
			
			session.delete(k);
			trans.commit();
		}
		catch (Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		}
	}

