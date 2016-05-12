package ba.unsa.etf.si.app.SIDEVS.Model;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import ba.unsa.etf.si.app.SIDEVS.Util.HibernateUtil;

public final class Sessions {
	
	private static Sessions instance = null;
	
	private static Session session;
	public Session getSession() {
		return session;
	}
	private static Transaction trasaction;
	public Transaction getTrasaction() {
		return trasaction;
	}
	private static Korisnik korisnik;
	public Korisnik getKorisnik() {
		return korisnik;
	}
	public Sessions(String email, String password) throws Exception{
		System.out.println("Setup new session");
		session = HibernateUtil.getSessionFactory().openSession();
		trasaction = session.beginTransaction();
		korisnik = (Korisnik) session.createCriteria(Korisnik.class).add(Restrictions.eq("email", email)).uniqueResult();
		if (korisnik != null) {
			System.out.println("Korisnik: " + korisnik.getIme());
			kreirajSesiju(password);
		}
		else{
			throw new IllegalArgumentException("Pogrešan korisnički email");
		}
	}
	
	public static Sessions getInstance(String email, String password) throws Exception {
		if(instance == null){
			instance = new Sessions(email, password);
		}
		return instance;
	}
	private static void kreirajSesiju(String password) throws Exception {
		try {
			if (!korisnik.authenticate(password))
				throw new IllegalArgumentException("Pogrešna lozinka");
		} catch (Exception ex) {
			throw ex;
		}
	}
	public Boolean daLiPostoji(){
		return (instance != null);
	}
	
	public void ubijSesiju(){
		//trasaction.commit();
		session.close();
		korisnik = null;
		instance = null;
	}
	

}
