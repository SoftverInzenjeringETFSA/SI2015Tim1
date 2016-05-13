package ba.unsa.etf.si.app.SIDEVS.ViewModel;

import static org.junit.Assert.*;
import ba.unsa.etf.si.app.SIDEVS.ViewModel.*;
import ba.unsa.etf.si.app.SIDEVS.Model.*;
import org.junit.Test;

public class BrisanjeKorisnikaVMTest {

	@Test
	public void BrisanjeKorisnikaTest() {
		try
		{
			Sessions s = new Sessions ("admin","password");
			if (ba.unsa.etf.si.app.SIDEVS.ViewModel.DodavanjeKorisnikaVM.KreirajKorisnika(s, "Imenko", "Prezimenko", "1010991123123", "38761001123", "iprezimenko", "radnik", "12.03.2010.", "Adresa bb", "Radnik"))
			{
				assertTrue(ba.unsa.etf.si.app.SIDEVS.ViewModel.BrisanjeKorisnikaVM.BrisiKorisnika(s, "Imenko", "Prezimenko"));
			}
			assertFalse(false);
		}
		catch (Exception ex)
		{
			fail("Bacen izuzetak!");
		}
		
	}

}
