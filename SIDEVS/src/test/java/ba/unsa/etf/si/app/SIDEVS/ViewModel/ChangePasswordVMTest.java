package ba.unsa.etf.si.app.SIDEVS.ViewModel;

import static org.junit.Assert.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ba.unsa.etf.si.app.SIDEVS.Model.*;
import org.junit.Test;

public class ChangePasswordVMTest {

	@Test
	public void ChangePasswordTest() {
		try
		{
			char[] newPassword= "newPassword".toCharArray();
			char[] oldPassword="password".toCharArray();
			
			assertTrue(ba.unsa.etf.si.app.SIDEVS.ViewModel.ChangePasswordVM.ChangePassword("admin",oldPassword,newPassword,newPassword));
			assertTrue(ba.unsa.etf.si.app.SIDEVS.ViewModel.ChangePasswordVM.ChangePassword("admin", newPassword, oldPassword, oldPassword));
		}
		catch (Exception ex)
		{
			fail("Bacen izuzetak!");
		}
	}

}
