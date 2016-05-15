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
				Administrator k = new Administrator();
				k.setIme("administrator");
				k.setPrezime("Korisnicki");
				k.setJmbg("1234567891234");
				k.setAdresa("Adresa bb");
				k.setEmail("administrator");
				k.setTelefon("012353451");
				k.setDatum_polaska_rada(new Date());
				k.setRadno_mjesto("radnik");
				k.setLozinka("password");
				Transaction t = session.beginTransaction();
				session.save(k);
				t.commit();
				
			char[] newPassword= "newPassword".toCharArray();
			char[] oldPassword="password".toCharArray();
			
			boolean promjena= ChangePasswordVM.ChangePassword("administrator", oldPassword, newPassword, newPassword);
			assertTrue(promjena);
			
			session.delete(k);
			t.commit();
		}
		catch (Exception ex)
		{
			fail("Bacen izuzetak " + ex.getMessage() + "!");
		}
		}
	}

