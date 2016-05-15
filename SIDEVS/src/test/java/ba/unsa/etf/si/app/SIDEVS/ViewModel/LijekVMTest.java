package ba.unsa.etf.si.app.SIDEVS.ViewModel;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ba.unsa.etf.si.app.SIDEVS.Model.Administrator;
import ba.unsa.etf.si.app.SIDEVS.Model.Korisnik;
import ba.unsa.etf.si.app.SIDEVS.Model.Kupac;
import ba.unsa.etf.si.app.SIDEVS.Model.Lijek;
import ba.unsa.etf.si.app.SIDEVS.Model.Menadzer;
import ba.unsa.etf.si.app.SIDEVS.Model.Sessions;
import ba.unsa.etf.si.app.SIDEVS.Util.HibernateUtil;

public class LijekVMTest {

	Menadzer k;
	//TESTOVI
	@Before 
	public void atBegin(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			k = new Menadzer();
			k.setIme("MAIDAB");
			k.setPrezime("Korisnicki");
			k.setJmbg("1234567891234");
			k.setAdresa("Adresa bb");
			k.setEmail("MAIDAB");
			k.setTelefon("012353451");
			k.setDatum_polaska_rada(new Date());
			k.setRadno_mjesto("radnik");
			k.setLozinka("password");
			Transaction t = session.beginTransaction();
			session.save(k);
			t.commit();
			session.flush();
			session.clear();
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
	@Test
	public void dodajLijekTest() {
		try{
			
			Sessions ses= new Sessions("MAIDAB", "password");
		String naziv="Brufen";
		String proizvodjac="Lekadol";
	
		LijekVM lijek= new LijekVM(ses);
		lijek.dodajLijek(naziv,proizvodjac);
		Boolean rezultat= false;
		if (ses.getSession().createCriteria(Lijek.class).add(Restrictions.eq("naziv", naziv)).setProjection(Projections.property("naziv")).uniqueResult() != null)
          rezultat= true;
		assertTrue(rezultat);

		Transaction t = ses.getSession().beginTransaction();
		Criteria criteria = ses.getSession().createCriteria(Lijek.class).add(Restrictions.like("naziv", naziv).ignoreCase());
		List<Lijek> lista = criteria.list();
		Lijek l = lista.get(0);
	   ses.getSession().delete(l);
		t.commit();
		}
		catch(Exception ex)
		{
			System.out.print(ex.getMessage());
		}
		
	}


	@After
	public void ending(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction trans = session.beginTransaction();
		session.delete(k);
		trans.commit();
		session.flush();
		session.clear();
	}
		
}