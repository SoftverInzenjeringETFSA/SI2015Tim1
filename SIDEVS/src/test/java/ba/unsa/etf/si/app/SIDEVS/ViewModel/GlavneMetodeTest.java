package ba.unsa.etf.si.app.SIDEVS.ViewModel;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ba.unsa.etf.si.app.SIDEVS.Model.*;
import ba.unsa.etf.si.app.SIDEVS.Util.HibernateUtil;
import ba.unsa.etf.si.app.SIDEVS.Validation.Conversions;
import ba.unsa.etf.si.app.SIDEVS.Validation.Validator;
import ba.unsa.etf.si.app.SIDEVS.View.Radnik.EvidencijaLotova;

public class GlavneMetodeTest {

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
	public void TestirajSesiju(){
		try{
		Sessions s = new Sessions("MAIDAB","password");
		/*System.out.println(s.getKorisnik().getIme());
		
		Skladiste skladiste = new Skladiste();
		skladiste.setBroj_skladista(20);
		LijekVM lvm = new LijekVM(s);
		lvm.dodajLijek("lll", "fr");*/
		
		assertTrue(true);
		
		s.ubijSesiju();
		
		} catch (Exception ex) {
			fail("ovdje " + ex.getMessage());
		}	
	}
	

	
	@Test
	public void vratiSveLotoveTest(){
		try{
			Sessions s = new Sessions("MAIDAB","password");
			
			Transaction t = s.getSession().beginTransaction();
				Skladiste skladiste = new Skladiste();
				skladiste.setBroj_skladista(1000);
				LijekVM.dodajLijek("TESTNILIJEK", "fr");	
				s.getSession().save(skladiste);
			t.commit();
			
			Lijek lijek = (Lijek) s.getSession().createCriteria(Lijek.class).add(Restrictions.eq("naziv", "TESTNILIJEK")).list().get(0);
			LotVM.dodajLot("9999900000", (Double)10.5, (Double)10.5, Conversions.stringToDate("12.12.2020"), 12, lijek, skladiste, 10);
			Lot lot = (Lot)s.getSession().createCriteria(Lot.class).add(Restrictions.eq("broj_lota", "9999900000")).list().get(0);
		
			assertTrue(GlavneMetode.vratiSveLotove(s).contains(lot));
			
			s.getSession().delete(skladiste);
			s.getSession().delete(lijek);
			s.getSession().delete(lot);
			t.commit();
			s.ubijSesiju();
		} catch (Exception ex) {
			System.out.println(ex);
		}	
	}
	
	
	
	@Test
	public void vratiOtpisaneLotoveTest(){
		try{
			
			Sessions s = new Sessions("MAIDAB","password");
			

			Transaction t = s.getSession().beginTransaction();
				Skladiste skladiste = new Skladiste();
				skladiste.setBroj_skladista(20);
				LijekVM.dodajLijek("lijekTest", "fr");	
				s.getSession().save(skladiste);
			t.commit();
			
			Lijek lijek = (Lijek) s.getSession().createCriteria(Lijek.class).add(Restrictions.eq("naziv", "lijekTest")).list().get(0);
			LotVM.dodajLot("555666", (Double)10.5, (Double)10.5, Conversions.stringToDate("12.12.2020"), 12, lijek, skladiste, 10);
			Lot lot = (Lot)s.getSession().createCriteria(Lot.class).add(Restrictions.eq("broj_lota", "555666")).list().get(0);
		
			OtpisLijekovaVM.otpisiLijek("555666");
			
			List<Lot> lotovi = GlavneMetode.vratiOtpisaneLotove(s);
			
			assertTrue(lotovi.contains(lot));
			
			s.getSession().delete(skladiste);
			s.getSession().delete(lijek);
			s.getSession().delete(lot);
			t.commit();
			s.ubijSesiju();
		} catch (Exception ex) {
			System.out.println(ex);
		}	
	}
		
		@Test
		public void vratiNeotpisaneLotoveTest(){
			try{
				
				Sessions s = new Sessions("MAIDAB","password");
				
				Transaction t = s.getSession().beginTransaction();
					Skladiste skladiste = new Skladiste();
					skladiste.setBroj_skladista(20);
					LijekVM.dodajLijek("lijekTest", "fr");	
					s.getSession().save(skladiste);
				t.commit();
				
				Lijek lijek = (Lijek) s.getSession().createCriteria(Lijek.class).add(Restrictions.eq("naziv", "lijekTest")).list().get(0);
				LotVM.dodajLot("555666", (Double)10.5, (Double)10.5, Conversions.stringToDate("12.12.2020"), 12, lijek, skladiste, 10);
				Lot lot = (Lot)s.getSession().createCriteria(Lot.class).add(Restrictions.eq("broj_lota", "555666")).list().get(0);
				
				GlavneMetode vm = new GlavneMetode();
				List<Lot> lotovi = vm.vratiNeotpisaneLotove(s);
				
				assertTrue(lotovi.contains(lot));
				
				s.getSession().delete(skladiste);
				s.getSession().delete(lijek);
				s.getSession().delete(lot);
				t.commit();
				s.ubijSesiju();
				} catch (Exception ex) {
				System.out.println(ex);
			}	
		
		}
	
		@Test
		public void vratiSveLotoveOdDoTest(){
			try{
				
				Sessions s = new Sessions("MAIDAB","password");
	
				Transaction t = s.getSession().beginTransaction();
					Skladiste skladiste = new Skladiste();
					skladiste.setBroj_skladista(20);
					LijekVM.dodajLijek("lijekTest", "fr");	
					s.getSession().save(skladiste);
				t.commit();
				
				Lijek lijek = (Lijek) s.getSession().createCriteria(Lijek.class).add(Restrictions.eq("naziv", "lijekTest")).list().get(0);
				LotVM.dodajLot("555666", (Double)10.5, (Double)10.5, Conversions.stringToDate("12.12.2020"), 12, lijek, skladiste, 10);
				Lot lot = (Lot)s.getSession().createCriteria(Lot.class).add(Restrictions.eq("broj_lota", "555666")).list().get(0);
				
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, -7);
				Date d1 = cal.getTime();
				Calendar cal2 = Calendar.getInstance();
				cal2.add(Calendar.DATE, +7);
				Date d2 = cal2.getTime();
				
				List<Lot> lotovi = GlavneMetode.vratiSveLotoveOdDo(s, Conversions.dateToString(d1), Conversions.dateToString(d2));
				
				assertTrue(lotovi.contains(lot));
				
				s.getSession().delete(skladiste);
				s.getSession().delete(lijek);
				s.getSession().delete(lot);
				t.commit();
				Sessions.ubijSesiju();
			} catch (Exception ex) {
				System.out.println(ex);
			}	
		
	}
	
		@Test
		public void vratiLotoveOdDoTest(){
			try{
				
				Sessions s = new Sessions("MAIDAB","password");
				
				
				Transaction t = s.getSession().beginTransaction();
					Skladiste skladiste = new Skladiste();
					skladiste.setBroj_skladista(20);
					LijekVM.dodajLijek("lijekTest", "fr");	
					s.getSession().save(skladiste);
				t.commit();
				
				Lijek lijek = (Lijek) s.getSession().createCriteria(Lijek.class).add(Restrictions.eq("naziv", "lijekTest")).list().get(0);
				LotVM.dodajLot("555666", (Double)10.5, (Double)10.5, Conversions.stringToDate("12.12.2020"), 12, lijek, skladiste, 10);
				Lot lot = (Lot)s.getSession().createCriteria(Lot.class).add(Restrictions.eq("broj_lota", "555666")).list().get(0);
				
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, -7);
				Date d1 = cal.getTime();
				Calendar cal2 = Calendar.getInstance();
				cal2.add(Calendar.DATE, +7);
				Date d2 = cal2.getTime();
				
				List<Lot> lotovi = GlavneMetode.vratiLotoveOdDo(s, Conversions.dateToString(d1), Conversions.dateToString(d2));
				
				assertTrue(lotovi.contains(lot));
				
				s.getSession().delete(skladiste);
				s.getSession().delete(lijek);
				s.getSession().delete(lot);
				t.commit();
				Sessions.ubijSesiju();

			} catch (Exception ex) {
				System.out.println(ex);
			}	
		
	}
	
		@Test
		public void vratiOtpisaneLotoveOdDoTest(){
			try{
				
				Sessions s = new Sessions("MAIDAB","password");
				
				
				Transaction t = s.getSession().beginTransaction();
					Skladiste skladiste = new Skladiste();
					skladiste.setBroj_skladista(20);
					LijekVM.dodajLijek("lijekTest", "fr");	
					s.getSession().save(skladiste);
				t.commit();
				
				Lijek lijek = (Lijek) s.getSession().createCriteria(Lijek.class).add(Restrictions.eq("naziv", "lijekTest")).list().get(0);
				LotVM.dodajLot("555666", (Double)10.5, (Double)10.5, Conversions.stringToDate("12.12.2020"), 12, lijek, skladiste, 10);
				Lot lot = (Lot)s.getSession().createCriteria(Lot.class).add(Restrictions.eq("broj_lota", "555666")).list().get(0);
				
				OtpisLijekovaVM.otpisiLijek("555666");
				
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, -7);
				Date d1 = cal.getTime();
				Calendar cal2 = Calendar.getInstance();
				cal2.add(Calendar.DATE, +7);
				Date d2 = cal2.getTime();
				
				List<Lot> lotovi = GlavneMetode.vratiOtpisaneLotoveOdDo(s, Conversions.dateToString(d1), Conversions.dateToString(d2));
				
				assertTrue(lotovi.contains(lot));
				
				s.getSession().delete(skladiste);
				s.getSession().delete(lijek);
				s.getSession().delete(lot);
				t.commit();
				Sessions.ubijSesiju();
			} catch (Exception ex) {
				System.out.println(ex);
			}	
		
	}
		/////////////
		

		@Test
		public void vratiKolicinuIzlazaTest(){
			try{
				
				Sessions s = new Sessions("MAIDAB","password");
				
				
				Transaction t = s.getSession().beginTransaction();
					Skladiste skladiste = new Skladiste();
					skladiste.setBroj_skladista(20);
					LijekVM.dodajLijek("lijekTest", "fr");	
					s.getSession().save(skladiste);
				t.commit();
				
				Lijek lijek = (Lijek) s.getSession().createCriteria(Lijek.class).add(Restrictions.eq("naziv", "lijekTest")).list().get(0);
				LotVM.dodajLot("555666", (Double)10.5, (Double)10.5, Conversions.stringToDate("12.12.2020"), 12, lijek, skladiste, 10);
				List<Lot> lotovi = s.getSession().createCriteria(Lot.class).add(Restrictions.eq("broj_lota", "555666")).list();
				Lot lot = lotovi.get(0);
				
				OtpisLijekovaVM.otpisiLijek("555666");
				
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, -7);
				Date d1 = cal.getTime();
				Calendar cal2 = Calendar.getInstance();
				cal2.add(Calendar.DATE, +7);
				Date d2 = cal2.getTime();
				
				EvidencijaKupca.dodajKupca("maki", "adresa");
				Kupac kupac = (Kupac) s.getSession().createCriteria(Kupac.class).add(Restrictions.eq("naziv", "maki")).list().get(0);
				
				FakturaVM f = new FakturaVM(s);
				List<Integer> kolicine = new ArrayList<Integer>();
				kolicine.add(2);
				List<Skladiste> sk = new ArrayList<Skladiste>();
				sk.add(skladiste);
				List<Double> cijene = new ArrayList<Double>();
				cijene.add((double) 100);
				f.dodajFakturu(lotovi, kolicine, sk, kupac, cijene);
				
				assertEquals((Integer)2,GlavneMetode.vratiKolicinuIzlaza(lot, skladiste));
				
				s.getSession().delete(skladiste);
				s.getSession().delete(lijek);
				s.getSession().delete(lot);
				s.getSession().delete(kupac);
				t.commit();
				Sessions.ubijSesiju();
			} catch (Exception ex) {
				System.out.println(ex);
			}	
		
	}
		
		@Test
		public void vratiKolicinuIzlazaLotTest(){
			try{
				Sessions s = new Sessions("MAIDAB","password");
				
				
				Transaction t = s.getSession().beginTransaction();
					Skladiste skladiste = new Skladiste();
					skladiste.setBroj_skladista(20);
					LijekVM.dodajLijek("lijekTest", "fr");	
					s.getSession().save(skladiste);
				t.commit();
				
				Lijek lijek = (Lijek) s.getSession().createCriteria(Lijek.class).add(Restrictions.eq("naziv", "lijekTest")).list().get(0);
				LotVM.dodajLot("555666", (Double)10.5, (Double)10.5, Conversions.stringToDate("12.12.2020"), 12, lijek, skladiste, 10);
				List<Lot> lotovi = s.getSession().createCriteria(Lot.class).add(Restrictions.eq("broj_lota", "555666")).list();
				Lot lot = lotovi.get(0);
				
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, -7);
				Date d1 = cal.getTime();
				Calendar cal2 = Calendar.getInstance();
				cal2.add(Calendar.DATE, +7);
				Date d2 = cal2.getTime();
				
				EvidencijaKupca.dodajKupca("maki", "adresa");
				Kupac kupac = (Kupac) s.getSession().createCriteria(Kupac.class).add(Restrictions.eq("naziv", "maki")).list().get(0);
				
				FakturaVM f = new FakturaVM(s);
				List<Integer> kolicine = new ArrayList<Integer>();
				kolicine.add(2);
				List<Skladiste> sk = new ArrayList<Skladiste>();
				sk.add(skladiste);
				List<Double> cijene = new ArrayList<Double>();
				cijene.add((double) 100);
				f.dodajFakturu(lotovi, kolicine, sk, kupac, cijene);
				
				assertEquals((Integer)2,GlavneMetode.vratiKolicinuIzlaza(lot));
				
				s.getSession().delete(skladiste);
				s.getSession().delete(lijek);
				s.getSession().delete(lot);
				s.getSession().delete(kupac);
				t.commit();
				Sessions.ubijSesiju();

			} catch (Exception ex) {
				System.out.println(ex);
			}	
		
	}
		
	
		@Test
		public void vratiKolicinuUlazaTest(){
			try{
				
				Sessions s = new Sessions("MAIDAB","password");
				
				
				Transaction t = s.getSession().beginTransaction();
					Skladiste skladiste = new Skladiste();
					skladiste.setBroj_skladista(20);
					LijekVM.dodajLijek("lijekTest", "fr");	
					s.getSession().save(skladiste);
				t.commit();
				
				Lijek lijek = (Lijek) s.getSession().createCriteria(Lijek.class).add(Restrictions.eq("naziv", "lijekTest")).list().get(0);
				LotVM.dodajLot("555666", (Double)10.5, (Double)10.5, Conversions.stringToDate("12.12.2020"), 12, lijek, skladiste, 10);
				List<Lot> lotovi = s.getSession().createCriteria(Lot.class).add(Restrictions.eq("broj_lota", "555666")).list();
				Lot lot = lotovi.get(0);
				
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, -7);
				Date d1 = cal.getTime();
				Calendar cal2 = Calendar.getInstance();
				cal2.add(Calendar.DATE, +7);
				Date d2 = cal2.getTime();
				
				EvidencijaKupca.dodajKupca("maki", "adresa");
				Kupac kupac = (Kupac) s.getSession().createCriteria(Kupac.class).add(Restrictions.eq("naziv", "maki")).list().get(0);
				
				FakturaVM f = new FakturaVM(s);
				List<Integer> kolicine = new ArrayList<Integer>();
				kolicine.add(2);
				List<Skladiste> sk = new ArrayList<Skladiste>();
				sk.add(skladiste);
				List<Double> cijene = new ArrayList<Double>();
				cijene.add((double) 100);
				f.dodajFakturu(lotovi, kolicine, sk, kupac, cijene);
				
				assertEquals((Integer)10,GlavneMetode.vratiKolicinuUlaza(lot));
				
				s.getSession().delete(skladiste);
				s.getSession().delete(lijek);
				s.getSession().delete(lot);
				s.getSession().delete(kupac);
				t.commit();
				Sessions.ubijSesiju();
	
			} catch (Exception ex) {
				System.out.println(ex);
			}	
		
	}
		
		@Test
		public void vratiKolicinuUlazaLotTest(){
			try{
				
				Sessions s = new Sessions("MAIDAB","password");
				
				
				Transaction t = s.getSession().beginTransaction();
					Skladiste skladiste = new Skladiste();
					skladiste.setBroj_skladista(20);
					LijekVM.dodajLijek("lijekTest", "fr");	
					s.getSession().save(skladiste);
				t.commit();
				
				Lijek lijek = (Lijek) s.getSession().createCriteria(Lijek.class).add(Restrictions.eq("naziv", "lijekTest")).list().get(0);
				LotVM.dodajLot("555666", (Double)10.5, (Double)10.5, Conversions.stringToDate("12.12.2020"), 12, lijek, skladiste, 10);
				List<Lot> lotovi = s.getSession().createCriteria(Lot.class).add(Restrictions.eq("broj_lota", "555666")).list();
				Lot lot = lotovi.get(0);
				
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, -7);
				Date d1 = cal.getTime();
				Calendar cal2 = Calendar.getInstance();
				cal2.add(Calendar.DATE, +7);
				Date d2 = cal2.getTime();
				
				EvidencijaKupca.dodajKupca("maki", "adresa");
				Kupac kupac = (Kupac) s.getSession().createCriteria(Kupac.class).add(Restrictions.eq("naziv", "maki")).list().get(0);
				
				FakturaVM f = new FakturaVM(s);
				List<Integer> kolicine = new ArrayList<Integer>();
				kolicine.add(2);
				List<Skladiste> sk = new ArrayList<Skladiste>();
				sk.add(skladiste);
				List<Double> cijene = new ArrayList<Double>();
				cijene.add((double) 100);
				f.dodajFakturu(lotovi, kolicine, sk, kupac, cijene);
				
				assertEquals((Integer)2,GlavneMetode.vratiKolicinuUlaza(lot));
				
				s.getSession().delete(skladiste);
				s.getSession().delete(lijek);
				s.getSession().delete(lot);
				s.getSession().delete(kupac);
				t.commit();
				Sessions.ubijSesiju();

			} catch (Exception ex) {
				System.out.println(ex);
			}	
		
	}
		
		@Test
		public void vratiKolicinuOtpisanogTest(){
			try{
				
				Sessions s = new Sessions("MAIDAB","password");
				
				
				Transaction t = s.getSession().beginTransaction();
					Skladiste skladiste = new Skladiste();
					skladiste.setBroj_skladista(20);
					LijekVM.dodajLijek("lijekTest", "fr");	
					s.getSession().save(skladiste);
				t.commit();
				
				Lijek lijek = (Lijek) s.getSession().createCriteria(Lijek.class).add(Restrictions.eq("naziv", "lijekTest")).list().get(0);
				LotVM.dodajLot("555666", (Double)10.5, (Double)10.5, Conversions.stringToDate("12.12.2020"), 12, lijek, skladiste, 10);
				List<Lot> lotovi = s.getSession().createCriteria(Lot.class).add(Restrictions.eq("broj_lota", "555666")).list();
				Lot lot = lotovi.get(0);
				
				OtpisLijekovaVM.otpisiLijek("555666");
				
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, -7);
				Date d1 = cal.getTime();
				Calendar cal2 = Calendar.getInstance();
				cal2.add(Calendar.DATE, +7);
				Date d2 = cal2.getTime();
				
				EvidencijaKupca.dodajKupca("maki", "adresa");
				Kupac kupac = (Kupac) s.getSession().createCriteria(Kupac.class).add(Restrictions.eq("naziv", "maki")).list().get(0);
				
				FakturaVM f = new FakturaVM(s);
				List<Integer> kolicine = new ArrayList<Integer>();
				kolicine.add(2);
				List<Skladiste> sk = new ArrayList<Skladiste>();
				sk.add(skladiste);
				List<Double> cijene = new ArrayList<Double>();
				cijene.add((double) 100);
				f.dodajFakturu(lotovi, kolicine, sk, kupac, cijene);
				
				assertEquals((Integer)8,GlavneMetode.vratiKolicinuOtpisanog(lot, skladiste));
				
				s.getSession().delete(skladiste);
				s.getSession().delete(lijek);
				s.getSession().delete(lot);
				s.getSession().delete(kupac);
				t.commit();
				Sessions.ubijSesiju();

			} catch (Exception ex) {
				System.out.println(ex);
			}	
		
	}
	
		@Test
		public void vratiKolicinuOtpisanogLotaTest(){
			try{
				
				Sessions s = new Sessions("MAIDAB","password");
				
				
				Transaction t = s.getSession().beginTransaction();
					Skladiste skladiste = new Skladiste();
					skladiste.setBroj_skladista(20);
					LijekVM.dodajLijek("lijekTest", "fr");	
					s.getSession().save(skladiste);
				t.commit();
				
				Lijek lijek = (Lijek) s.getSession().createCriteria(Lijek.class).add(Restrictions.eq("naziv", "lijekTest")).list().get(0);
				LotVM.dodajLot("555666", (Double)10.5, (Double)10.5, Conversions.stringToDate("12.12.2020"), 12, lijek, skladiste, 10);
				List<Lot> lotovi = s.getSession().createCriteria(Lot.class).add(Restrictions.eq("broj_lota", "555666")).list();
				Lot lot = lotovi.get(0);
				
				
				
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, -7);
				Date d1 = cal.getTime();
				Calendar cal2 = Calendar.getInstance();
				cal2.add(Calendar.DATE, +7);
				Date d2 = cal2.getTime();
				
				EvidencijaKupca.dodajKupca("maki", "adresa");
				Kupac kupac = (Kupac) s.getSession().createCriteria(Kupac.class).add(Restrictions.eq("naziv", "maki")).list().get(0);
				
				FakturaVM f = new FakturaVM(s);
				List<Integer> kolicine = new ArrayList<Integer>();
				kolicine.add(2);
				List<Skladiste> sk = new ArrayList<Skladiste>();
				sk.add(skladiste);
				List<Double> cijene = new ArrayList<Double>();
				cijene.add((double) 100);
				f.dodajFakturu(lotovi, kolicine, sk, kupac, cijene);
				OtpisLijekovaVM.otpisiLijek("555666");
				
				System.out.println(GlavneMetode.vratiKolicinuOtpisanog(lot));
				
				assertEquals(108,(int)GlavneMetode.vratiKolicinuOtpisanog(lot));
				
				s.getSession().delete(skladiste);
				s.getSession().delete(lijek);
				s.getSession().delete(lot);
				s.getSession().delete(kupac);
				t.commit();
				Sessions.ubijSesiju();

			} catch (Exception ex) {
				System.out.println(ex);
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
