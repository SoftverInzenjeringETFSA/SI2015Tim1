package ba.unsa.etf.si.app.SIDEVS.ViewModel;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import ba.unsa.etf.si.app.SIDEVS.ViewModel.*;
import ba.unsa.etf.si.app.SIDEVS.Model.*;
import ba.unsa.etf.si.app.SIDEVS.Util.HibernateUtil;

import org.junit.Test;

public class IzvjestajZaKupcaVMTest {

	@Test
	public void vratiFaktureKupcaTest() {
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
			IzvjestajZaKupcaVM izv = new IzvjestajZaKupcaVM(s);
			EvidencijaKupca ek = new EvidencijaKupca (s);
			ek.dodajKupca("k", "a");
			
			Transaction t = s.getSession().beginTransaction();
			Criteria criteria = s.getSession().createCriteria(Kupac.class).add(Restrictions.like("naziv", "k").ignoreCase());
			List<Kupac> lista = criteria.list();
			Kupac k_ = lista.get(0);
			List<FakturaLot> l_fl = izv.vratiFaktureKupca(k_, "02.02.2015.", "03.03.2015.");
			s.getSession().delete(k);
			t.commit();
			assertTrue(l_fl.isEmpty());

			
		}
		catch (Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}
		
		@Test
		public void vratiLijekoveKupcaTest() {
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
				IzvjestajZaKupcaVM izv = new IzvjestajZaKupcaVM(s);
				EvidencijaKupca ek = new EvidencijaKupca (s);
				ek.dodajKupca("k", "a");
				
				Transaction t = s.getSession().beginTransaction();
				Criteria criteria = s.getSession().createCriteria(Kupac.class).add(Restrictions.like("naziv", "k").ignoreCase());
				List<Kupac> lista = criteria.list();
				Kupac k_ = lista.get(0);
				assertTrue(izv.vratiLijekoveKupca(izv.vratiFaktureKupca(k_, "02.02.2015.", "03.03.2015.")).isEmpty());
				s.getSession().delete(k);
				t.commit();
			}
			catch (Exception ex)
			{
				System.out.println(ex.getMessage());
			}
		}
			
		

}
