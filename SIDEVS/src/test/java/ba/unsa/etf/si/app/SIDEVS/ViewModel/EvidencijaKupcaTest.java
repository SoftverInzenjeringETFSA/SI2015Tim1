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

public class EvidencijaKupcaTest {

	@Test
	public void EvidencijaKupca_Test() {
		try
		{
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
				
					if (k!=null)
					{
						Sessions s_admin = new Sessions ("adminTest","password");
				
						ba.unsa.etf.si.app.SIDEVS.ViewModel.EvidencijaKupca.dodajKupca("Apoteka 1", "Ul. Adresa bb");
				
						Transaction t = s_admin.getSession().beginTransaction();
						Criteria criteria = s_admin.getSession().createCriteria(Kupac.class).add(Restrictions.like("naziv", "Apoteka 1").ignoreCase());
						List<Kupac> lista = criteria.list();
				
						assertFalse(lista.isEmpty());
						
						ba.unsa.etf.si.app.SIDEVS.ViewModel.BrisanjeKupcaVM.BrisiKupca(s_admin, "Apoteka 1");
						session.delete(k);
						trans.commit();
					}
		}
		catch (Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		
	}

}
