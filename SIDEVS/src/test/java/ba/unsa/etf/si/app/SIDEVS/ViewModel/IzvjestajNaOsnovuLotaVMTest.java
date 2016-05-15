package ba.unsa.etf.si.app.SIDEVS.ViewModel;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import ba.unsa.etf.si.app.SIDEVS.ViewModel.*;
import ba.unsa.etf.si.app.SIDEVS.Model.*;
import ba.unsa.etf.si.app.SIDEVS.Util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

public class IzvjestajNaOsnovuLotaVMTest {

	
	
	@Test
	public void lista_datumi_izlazaTest() {
		try
		{
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction trans = session.beginTransaction();
			Administrator k = null;
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
			List<String> datum = ba.unsa.etf.si.app.SIDEVS.ViewModel.IzvjestajNaOsnovuLotaVM.lista_datumi_izlaza(s, "1231231");
			assertTrue(datum.isEmpty());
			
			session.delete(k);
			trans.commit();
		}
		catch (Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}
	


}
