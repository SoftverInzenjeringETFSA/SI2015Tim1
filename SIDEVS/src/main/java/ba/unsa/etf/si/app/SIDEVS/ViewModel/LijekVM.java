package ba.unsa.etf.si.app.SIDEVS.ViewModel;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import ba.unsa.etf.si.app.SIDEVS.Model.Lijek;
import ba.unsa.etf.si.app.SIDEVS.Model.Sessions;

public final class LijekVM {
	final static Logger logger = Logger.getLogger(LijekVM.class);

	private static Sessions s;

	public Sessions getSessions() {
		return this.s;
	}
	public LijekVM(Sessions s) throws Exception {
		this.s = s;
	}

	public static Boolean daLiPostojiSesija() {
		if (s == null)
			return false;
		return true;
	}

	public static void dodajLijek(String naziv, String proizvodjac) throws Exception {
		if (daLiPostojiSesija()) {
			try {
				List<Lijek> sviLijekovi = s.getSession().createCriteria(Lijek.class).add(Restrictions.like("naziv", naziv)).list();
				Lijek l = new Lijek();
				l.setNaziv(naziv);
				l.setProizvodjac(proizvodjac);
				if (sviLijekovi.size() == 0) {
					s.getSession().beginTransaction();
					s.getSession().save(l);
					s.getTrasaction().commit();
				} else
					throw new Exception("Lijek sa ovim nazivom već postoji");
			} catch (Exception ex) {
				logger.error(ex);
				throw ex;
			}

		} else
			throw new Exception("Niste prijavljeni!");
	}
}
