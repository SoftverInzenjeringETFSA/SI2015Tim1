package ba.unsa.etf.si.app.SIDEVS.ViewModel;

import static org.junit.Assert.*;

import java.sql.Date;

import ba.unsa.etf.si.app.SIDEVS.ViewModel.*;
import ba.unsa.etf.si.app.SIDEVS.Model.*;
import org.junit.Test;

public class OtpisLijekovaVMTest {

	@SuppressWarnings("deprecation")
	@Test
	public void otpisLijekaTest() {
		try
		{
			Sessions s= new Sessions ("admin","password");
			Lijek lijek = new Lijek();
			lijek.setNaziv("lijek");
			Skladiste skladiste = new Skladiste();
			skladiste.setBroj_skladista(1);
			ba.unsa.etf.si.app.SIDEVS.ViewModel.LotVM.dodajLot("112233", 1.0, 3.2, new Date(2020,5,5), 50, lijek, skladiste, 50);
			assertFalse(ba.unsa.etf.si.app.SIDEVS.ViewModel.OtpisLijekovaVM.otpisLijeka("lijek","112233","1"));
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
