package ba.unsa.etf.si.app.SIDEVS.ViewModel;


import static org.junit.Assert.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.junit.Test;

public class ChangePasswordVMTest {

	@Test
	public void ChangePasswordTest() throws NoSuchAlgorithmException, InvalidKeySpecException {

			char[] newPassword= "newPassword".toCharArray();
			char[] oldPassword="password".toCharArray();
			
			assertTrue(ba.unsa.etf.si.app.SIDEVS.ViewModel.ChangePasswordVM.ChangePassword("admin",oldPassword,newPassword,newPassword));
			assertTrue(ba.unsa.etf.si.app.SIDEVS.ViewModel.ChangePasswordVM.ChangePassword("admin", newPassword, oldPassword, oldPassword));
		

	}

}
