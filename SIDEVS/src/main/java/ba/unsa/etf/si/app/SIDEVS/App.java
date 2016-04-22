package ba.unsa.etf.si.app.SIDEVS;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ba.unsa.etf.si.app.SIDEVS.Klase.Skladiste;
import ba.unsa.etf.si.app.SIDEVS.Util.HibernateUtil;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		kreirajSkladiste(session, 1);
	}

	private static void kreirajSkladiste(Session session, int broj_skladista) {
		Transaction t = session.beginTransaction();
		// Provjera da li postoji skladiste
		if (((Skladiste) session.get(Skladiste.class, broj_skladista)) == null) {
			System.out.println("Pravim skaldište...");
			Skladiste s = new Skladiste();
			s.setBroj_skladista(2);
			Integer id = (Integer) session.save(s);
			System.out.println("Novo skladište sa brojem " + id);
			t.commit();
		}
		else{
			System.out.println("Skladište sa ovim brojem već postoji!");
		}
	}

}
