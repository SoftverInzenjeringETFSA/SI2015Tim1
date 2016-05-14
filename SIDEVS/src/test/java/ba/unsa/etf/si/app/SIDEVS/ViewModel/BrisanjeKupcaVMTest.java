package ba.unsa.etf.si.app.SIDEVS.ViewModel;

import static org.junit.Assert.*;
import ba.unsa.etf.si.app.SIDEVS.ViewModel.*;
import ba.unsa.etf.si.app.SIDEVS.Model.*;
import org.junit.Test;

public class BrisanjeKupcaVMTest {

	@Test
	public void BrisanjeKupcaTest() {
		try
		{
			Sessions s = new Sessions ("admin","password");
			EvidencijaKupca ek = new EvidencijaKupca(s);
			ek.dodajKupca("Kupac", "Adresa kupca");
			assertTrue(ba.unsa.etf.si.app.SIDEVS.ViewModel.BrisanjeKupcaVM.BrisiKupca(s, "Kupac"));		
		}
		catch (Exception ex)
		{
			fail("Bacen izuzetak:" + ex.getMessage() + " !");
		}
	}

}
