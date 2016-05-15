package ba.unsa.etf.si.app.SIDEVS.ViewModel;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import ba.unsa.etf.si.app.SIDEVS.Model.*;
import ba.unsa.etf.si.app.SIDEVS.Util.HibernateUtil;
import ba.unsa.etf.si.app.SIDEVS.Validation.Conversions;
import ba.unsa.etf.si.app.SIDEVS.Validation.Validator;
import ba.unsa.etf.si.app.SIDEVS.View.Radnik.EvidencijaLotova;

public class GlavneMetodeTest {

	@Test
	public void vratiSkladistaTest(){
		try{
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
		

		Sessions s = new Sessions ("adminTest","password");
		
		
		Transaction t = s.getSession().beginTransaction();
		Skladiste skladiste = new Skladiste();
		GlavneMetode g = new GlavneMetode();
		LotVM l = null;
		LijekVM lk;

			l = new LotVM(s);
			lk = new LijekVM(s);
			LijekVM.dodajLijek("lijekTest", "fr");	
			

		
		
		skladiste.setBroj_skladista(20);
		s.getSession().save(skladiste);
		t.commit();
		
		Lijek lijek = (Lijek) s.getSession().createCriteria(Lijek.class).add(Restrictions.eq("naziv", "lijekTest")).list().get(0);
		LotVM.dodajLot("555666", (Double)10.5, (Double)10.5, Conversions.stringToDate("12.12.2020"), 12, lijek, skladiste, 10);

		Lot lot = (Lot)s.getSession().createCriteria(Lot.class).add(Restrictions.eq("broj_lota", "555666")).list().get(0);
		System.out.println(lot.getPakovanja().size());
		Set<Skladiste> skladista = g.vratiSkladista(lot);
	
		
		assertTrue(skladista.contains(skladiste));
		s.getSession().delete(skladiste);
		s.getSession().delete(lijek);
		s.getSession().delete(lot);
		t.commit();
		
		session.delete(k);
		trans.commit();
		
	} catch (Exception ex) {
		System.out.println(ex);
	}
	
	}
}
