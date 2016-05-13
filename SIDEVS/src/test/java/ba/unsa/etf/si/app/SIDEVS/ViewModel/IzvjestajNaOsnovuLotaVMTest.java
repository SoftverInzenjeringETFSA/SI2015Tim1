package ba.unsa.etf.si.app.SIDEVS.ViewModel;

import static org.junit.Assert.*;

import java.util.List;

import ba.unsa.etf.si.app.SIDEVS.ViewModel.*;
import ba.unsa.etf.si.app.SIDEVS.Model.*;
import org.junit.Test;

public class IzvjestajNaOsnovuLotaVMTest {

	@Test
	public void datum_ulazaTest() {
		try
		{
			Sessions s = new Sessions ("admin","password");
			String datum = ba.unsa.etf.si.app.SIDEVS.ViewModel.IzvjestajNaOsnovuLotaVM.datum_ulaza(s, "1231231");
			assertTrue(datum=="-");
		}
		catch (Exception ex)
		{
			fail("Bacen izuzetak: " + ex.getMessage()+" !");
		}
	}
	
	@Test
	public void kolicina_ulazaTest() {
		try
		{
			Sessions s = new Sessions ("admin","password");
			String datum = ba.unsa.etf.si.app.SIDEVS.ViewModel.IzvjestajNaOsnovuLotaVM.kolicina_ulaza(s, "1231231");
			assertTrue(datum=="0");
		}
		catch (Exception ex)
		{
			fail("Bacen izuzetak: " + ex.getMessage()+" !");
		}
	}
	
	@Test
	public void datum_otpisaTest() {
		try
		{
			Sessions s = new Sessions ("admin","password");
			String datum = ba.unsa.etf.si.app.SIDEVS.ViewModel.IzvjestajNaOsnovuLotaVM.datum_otpisa(s, "1231231");
			assertTrue(datum=="-");
		}
		catch (Exception ex)
		{
			fail("Bacen izuzetak: " + ex.getMessage() + " !");
		}
	}
	
	@Test
	public void kolicina_otpisaTest() {
		try
		{
			Sessions s = new Sessions ("admin","password");
			String datum = ba.unsa.etf.si.app.SIDEVS.ViewModel.IzvjestajNaOsnovuLotaVM.kolicina_otpisa(s, "1231231");
			assertTrue(datum=="0");
		}
		catch (Exception ex)
		{
			fail("Bacen izuzetak: " + ex.getMessage() + " !");
		}
	}
	
	@Test
	public void lista_datumi_izlazaTest() {
		try
		{
			Sessions s = new Sessions ("admin","password");
			List<String> datum = ba.unsa.etf.si.app.SIDEVS.ViewModel.IzvjestajNaOsnovuLotaVM.lista_datumi_izlaza(s, "1231231");
			assertTrue(datum.isEmpty());
		}
		catch (Exception ex)
		{
			fail("Bacen izuzetak: " + ex.getMessage() + " !");
		}
	}
	
	@Test
	public void lista_kolicine_izlazaTest() {
		try
		{
			Sessions s = new Sessions ("admin","password");
			List<String> kolicine = ba.unsa.etf.si.app.SIDEVS.ViewModel.IzvjestajNaOsnovuLotaVM.lista_kolicine_izlaza(s, "1231231");
			assertTrue(kolicine.isEmpty());
		}
		catch (Exception ex)
		{
			fail("Bacen izuzetak: " + ex.getMessage() + " !");
		}
	}
	

}
