package ba.unsa.etf.si.app.SIDEVS.ViewModel;

import static org.junit.Assert.*;

import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import ba.unsa.etf.si.app.SIDEVS.Model.Kupac;
import ba.unsa.etf.si.app.SIDEVS.Model.Sessions;

public class EvidencijaKupcaTest {

	@Test
	public void DodajKupcaTest() {
		try
		{
			Sessions s= new Sessions("radnik","password");
			EvidencijaKupca ek= new EvidencijaKupca(s);
			String naziv="apoteka 1";
			String adresa="adresica";
		ek.dodajKupca(naziv, adresa);
		Boolean rezultat= false;
		if(s.getSession().createCriteria(Kupac.class).add(Restrictions.eq("naziv", naziv)).setProjection(Projections.property("naziv")).uniqueResult()!=null) 
			rezultat= true;
		assertTrue(rezultat);

		
		}
		catch(Exception e)
		{
			fail("Test pao!");
			
		}
		
		
	}

	
	

}