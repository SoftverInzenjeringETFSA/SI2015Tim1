package ba.unsa.etf.si.app.SIDEVS.Forms;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import ba.unsa.etf.si.app.SIDEVS.Class.Korisnik;
import ba.unsa.etf.si.app.SIDEVS.Util.HibernateUtil;

public abstract class Sessions {
	private Session session;

	public Session getSession() {
		return session;
	}

	private Transaction trasaction;

	public Transaction getTrasaction() {
		return trasaction;
	}

	public void setTrasaction(Transaction trasaction) {
		this.trasaction = trasaction;
	}

	public Sessions(String email, String password) throws Exception {
		this.session = HibernateUtil.getSessionFactory().openSession();
		this.trasaction = session.beginTransaction();
		Korisnik k = (Korisnik) session.createCriteria(Korisnik.class).add(Restrictions.eq("email", email))
				.uniqueResult();
		if (k != null) {
			System.out.println("Korisnik: " + k.getIme());
			kreirajSesiju(k, password);
		}
		else{
			throw new IllegalArgumentException("Pogrešan korisnički email");
		}
	}

	private void kreirajSesiju(Korisnik k, String password) throws Exception {
		try {
			if (!k.authenticate(password))
				throw new IllegalArgumentException("Pogrešna lozinka");
		} catch (Exception ex) {
			throw ex;
		}
	}

}
